package com.jetbrains.teamcity.anewtodolist;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
public class TodoListController {


    public List<Todo> todos = new CopyOnWriteArrayList<>(Arrays.asList(new Todo("never done", false)));

    public static final class Todo {
        private final String name;
        private final Boolean done;

        public Todo(String name, Boolean done) {
            this.name = name;
            this.done = done;
        }

        public String name() {
            return name;
        }

        public Boolean done() {
            return done;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            var that = (Todo) obj;
            return Objects.equals(this.name, that.name) &&
                    Objects.equals(this.done, that.done);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, done);
        }

        @Override
        public String toString() {
            return "Todo[" +
                    "name=" + name + ", " +
                    "done=" + done + ']';
        }

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
