package com.dancoghlan.service;

import com.dancoghlan.model.Todo;

import java.util.Date;
import java.util.List;

public interface TodoService {

    List<Todo> retrieveTodos(String user);

    Todo retrieveTodo(int id);

    void updateTodo(Todo todo);

    void addTodo(String name, String description, Date targetDate, boolean isDone);

    void deleteTodo(int id);

}