package com.project.To_Do_ListApp.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.To_Do_ListApp.entities.ToDo;
import com.project.To_Do_ListApp.repositories.ToDoRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/ToDo")
public class ToDoController {
    @Autowired
    private ToDoRepository toDoRepository;

    @GetMapping("")
    public Iterable<ToDo> getAllToDos() {
        return this.toDoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<ToDo> getToDoById(@PathVariable("id") Integer id) {
        return this.toDoRepository.findById(id);
    }
    
    @PostMapping("")
    public ToDo createToDo(@RequestBody ToDo toDo) {
        ToDo newToDo = this.toDoRepository.save(toDo);
        return newToDo;
    }
    
    //Can also update completion status
    @PutMapping("/{id}")
    public ToDo updateToDo(@PathVariable("id") Integer id, @RequestBody ToDo toDo) {
        Optional<ToDo> toDoToUpdateOptional = this.toDoRepository.findById(id);
        if(!toDoToUpdateOptional.isPresent()) {
            return null;
        }

        ToDo toDoToUpdate = toDoToUpdateOptional.get();

        if (toDo.getTitle() != null) {
            toDoToUpdate.setTitle(toDo.getTitle());
        }
        if (toDo.getDescription() != null) {
            toDoToUpdate.setDescription(toDo.getDescription());
        }
        if (toDo.getDueDate() != null) {
            toDoToUpdate.setDueDate(toDo.getDueDate());
        }
        if (toDo.getCompleted() != null) {
            toDoToUpdate.setCompleted(toDo.getCompleted());
        }

        return this.toDoRepository.save(toDoToUpdate);
    }

    @DeleteMapping("/{id}")
    public ToDo deleteToDo(@PathVariable("id") Integer id) {
        Optional<ToDo> toDoToDeleteOptional = this.toDoRepository.findById(id);

        if (!toDoToDeleteOptional.isPresent()) {
            return null;
        }

        ToDo toDoToDelete = toDoToDeleteOptional.get();
        this.toDoRepository.delete(toDoToDelete);
        return toDoToDelete;
    }
}
