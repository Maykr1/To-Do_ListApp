package com.project.To_Do_ListApp.service;

import java.util.Optional;

import com.project.To_Do_ListApp.entity.ToDo;

public interface ToDoService {
    public abstract Iterable<ToDo> getAllToDos();
    public abstract Optional<ToDo> getToDoById(Integer id);
    public abstract ToDo createToDo(ToDo toDo);
    public abstract ToDo updateToDo(Integer id, ToDo toDo);
    public abstract ToDo deleteToDo(Integer id);
    public abstract void deleteAll();
}
