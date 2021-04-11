package com.dancoghlan.service;

import com.dancoghlan.model.Todo;
import com.dancoghlan.rest.TodoRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Date;
import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {
    private final TodoRestClient todoRestClient;
    private final TokenService tokenService;

    @Autowired
    public TodoServiceImpl(TodoRestClient todoRestClient, TokenService tokenService) {
        this.todoRestClient = todoRestClient;
        this.tokenService = tokenService;
    }

    @Override
    public List<Todo> retrieveTodos(String userName) {
        String bearerToken = tokenService.getBearerToken();
        Flux<Todo> flux = todoRestClient.getTodos(userName, bearerToken);
        return flux
                .collectList()
                .block();
    }

    @Override
    public Todo retrieveTodo(int id) {
        return null;
    }

    @Override
    public void updateTodo(Todo todo) {

    }

    @Override
    public void addTodo(String name, String description, Date targetDate, boolean isDone) {
        Todo todo = new Todo(name, description, targetDate, isDone);
        String bearerToken = tokenService.getBearerToken();
        todoRestClient.addTodo(todo, bearerToken);
    }

    @Override
    public void deleteTodo(int id) {
        String bearerToken = tokenService.getBearerToken();
        todoRestClient.deleteTodo(id, bearerToken);
    }

}