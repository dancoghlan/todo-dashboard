package com.dancoghlan.rest;

import com.dancoghlan.model.Todo;
import reactor.core.publisher.Flux;

public interface TodoRestClient {

    Flux<Todo> getTodos(String user, String bearerToken);

    void addTodo(Todo todo, String bearerToken);

    void deleteTodo(int id, String bearerToken);
}
