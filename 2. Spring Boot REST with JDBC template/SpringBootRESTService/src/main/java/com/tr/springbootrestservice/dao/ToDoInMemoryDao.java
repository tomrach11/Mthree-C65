package com.tr.springbootrestservice.dao;

import com.tr.springbootrestservice.model.ToDo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

public class ToDoInMemoryDao implements ToDoDao {

    private static final List<ToDo> todos = new ArrayList<>();

    @Override
    public ToDo add(ToDo todo) {

        //find next id
        int nextId = todos.stream()
                .mapToInt(i -> i.getId())
                .max()
                .orElse(0) + 1;

        //use the next id
        todo.setId(nextId);
        //add to list
        todos.add(todo);

        return todo;
    }

    @Override
    public List<ToDo> getAll() {
        return new ArrayList<>(todos);
    }

    @Override
    public ToDo getById(int id) {
        return todos.stream()
                .filter(i -> i.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean update(ToDo todo) {
        int index = 0;

        //loop to find index of To-do object looking for
        while (index < todos.size() && todos.get(index).getId() != todo.getId()) {
            index++;
        }
        //replace with new to-do
        if (index < todos.size()) {
            todos.set(index, todo);
        }

        return index < todos.size();
    }

    @Override
    public boolean deleteById(int id) {
        return todos.removeIf(i -> i.getId() == id);
    }
}
