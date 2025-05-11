package com.project.To_Do_ListApp.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.To_Do_ListApp.entities.ToDo;
import com.project.To_Do_ListApp.services.ToDoService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/ToDo")
public class ToDoController {
    @Autowired
    private ToDoService toDoService;

    @GetMapping("")
    public Iterable<ToDo> getAllToDos() {
        return this.toDoService.getAllToDos();
    }

    @GetMapping("/{id}")
    public Optional<ToDo> getToDoById(@PathVariable("id") Integer id) {
        return this.toDoService.getToDoById(id);
    }
    
    @PostMapping("")
    public ToDo createToDo(@RequestBody ToDo toDo) {
        return this.toDoService.createToDo(toDo);
    }
    
    //Can also update completion status
    @PutMapping("/{id}")
    public ToDo updateToDo(@PathVariable("id") Integer id, @RequestBody ToDo toDo) {
        return this.toDoService.updateToDo(id, toDo);
    }

    @DeleteMapping("/{id}")
    public ToDo deleteToDo(@PathVariable("id") Integer id) {
        return toDoService.deleteToDo(id);
    }
}
