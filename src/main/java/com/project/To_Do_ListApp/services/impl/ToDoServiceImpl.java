package com.project.To_Do_ListApp.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.To_Do_ListApp.entities.ToDo;
import com.project.To_Do_ListApp.exceptions.ResourceNotFoundException;
import com.project.To_Do_ListApp.repositories.ToDoRepository;
import com.project.To_Do_ListApp.services.ToDoService;

@Service
public class ToDoServiceImpl implements ToDoService {
    @Autowired
    private ToDoRepository toDoRepository;

    @Override
    public Iterable<ToDo> getAllToDos() {
        Iterable<ToDo> allToDos = this.toDoRepository.findAll();
        if (!allToDos.iterator().hasNext()) {
            throw new ResourceNotFoundException("Cannot find any ToDos");
        }

        return allToDos;
    }

    @Override
    public Optional<ToDo> getToDoById(Integer id) {
        Optional<ToDo> toDo = this.toDoRepository.findById(id);
        if (!toDo.isPresent()) {
            throw new ResourceNotFoundException("ToDo not found with id: " + id);
        }

        return toDo;
    }

    @Override
    public ToDo createToDo(ToDo toDo) {
        return toDoRepository.save(toDo);
    }

    @Override 
    public ToDo updateToDo(Integer id, ToDo toDo) {
        Optional<ToDo> toDoToUpdateOptional = this.toDoRepository.findById(id);
        if(!toDoToUpdateOptional.isPresent()) {
            throw new ResourceNotFoundException("ToDo not found with id: " + id);
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

    @Override
    public ToDo deleteToDo(Integer id) {
        Optional<ToDo> toDoToDeleteOptional = this.toDoRepository.findById(id);

        if (!toDoToDeleteOptional.isPresent()) {
            throw new ResourceNotFoundException("ToDo not found with id: " + id);
        }

        ToDo toDoToDelete = toDoToDeleteOptional.get();
        this.toDoRepository.delete(toDoToDelete);
        return toDoToDelete;
    }
}
