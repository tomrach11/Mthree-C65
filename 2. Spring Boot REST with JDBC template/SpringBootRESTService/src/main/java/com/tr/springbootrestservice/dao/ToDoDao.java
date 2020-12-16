package com.tr.springbootrestservice.dao;

import com.tr.springbootrestservice.model.ToDo;

import java.util.List;

public interface ToDoDao {
    ToDo add(ToDo todo);

    List<ToDo> getAll();

    ToDo getById(int id);

    boolean update(ToDo todo);

    boolean deleteById(int id);
}
