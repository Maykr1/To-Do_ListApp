package com.project.To_Do_ListApp.repository;

import org.springframework.data.repository.CrudRepository;

import com.project.To_Do_ListApp.entity.ToDo;

public interface ToDoRepository extends CrudRepository<ToDo, Integer> {
    
}
