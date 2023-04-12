package com.jetbrains.teamcity.anewtodolist;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
public class TodoListController {


    public List<Todo> todos = new CopyOnWriteArrayList<>(Arrays.asList(new Todo("never done", false)));

    public record Todo(String name, Boolean done) {
    }


    @GetMapping("/todos")
    public List<Todo> todos() {
        return todos;
    }


    @PostMapping("/todos")
    public Todo todos(@RequestBody Todo todo) {
        todos.add(todo);
        return todo;
    }



}
