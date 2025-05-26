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

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/ToDo")
@Tag(name = "To Do") // Change name of controller
public class ToDoController {
    @Autowired
    private ToDoService toDoService;

    
    @GetMapping("")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Success/Fetched All ToDos"),
        @ApiResponse(responseCode = "400", description = "Bad Request"),
        @ApiResponse(responseCode = "404", description = "Not Found/ToDo not Found")
    })
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
