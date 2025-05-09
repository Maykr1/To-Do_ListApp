package com.project.To_Do_ListApp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.project.To_Do_ListApp.entities.ToDo;

public interface ToDoRepository extends CrudRepository<ToDo, Integer> {
    
}
