package com.dancoghlan.rest;

import com.dancoghlan.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class TodoRestClientImpl implements TodoRestClient {
    private final WebClient webClient;

    @Autowired
    public TodoRestClientImpl(WebClient todoWebClient) {
        this.webClient = todoWebClient;
    }

    @Override
    public Flux<Todo> getTodos(String userName, String bearerToken) {
        Flux<Todo> todos = webClient
                .get()
                .uri("/get/{userName}", "in28Minutes")
                .headers(headers -> headers.setBearerAuth(bearerToken))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.error(new RuntimeException()))
                .onStatus(HttpStatus::is5xxServerError, serverResponse -> Mono.error(new RuntimeException()))
                .bodyToFlux(Todo.class);
        return todos;
    }

    @Override
    public void addTodo(Todo todo, String bearerToken) {
        webClient
                .post()
                .uri("/add")
                .headers(headers -> headers.setBearerAuth(bearerToken))
                .body(BodyInserters.fromValue(todo))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.error(new RuntimeException()))
                .onStatus(HttpStatus::is5xxServerError, serverResponse -> Mono.error(new RuntimeException()));
    }

    @Override
    public void deleteTodo(int id, String bearerToken) {
        webClient
                .delete()
                .uri("/delete/{id}", id)
                .headers(headers -> headers.setBearerAuth(bearerToken))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.error(new RuntimeException()))
                .onStatus(HttpStatus::is5xxServerError, serverResponse -> Mono.error(new RuntimeException()));
    }

}
