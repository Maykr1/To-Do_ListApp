package com.project.To_Do_ListApp.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.To_Do_ListApp.entity.ToDo;
import com.project.To_Do_ListApp.exception.ResourceNotFoundException;
import com.project.To_Do_ListApp.repository.ToDoRepository;
import com.project.To_Do_ListApp.service.ToDoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ToDoServiceImpl implements ToDoService {
    @Autowired
    private ToDoRepository toDoRepository;

    @Override
    public Iterable<ToDo> getAllToDos() {
        Iterable<ToDo> allToDos = this.toDoRepository.findAll();
        log.info("Got all toDos");
        return allToDos;
    }

    @Override
    public Optional<ToDo> getToDoById(Integer id) {
        Optional<ToDo> toDo = this.toDoRepository.findById(id);
        if (!toDo.isPresent()) {
            throw new ResourceNotFoundException("ToDo not found with id: " + id);
        }
        log.info("Got toDo at Id: {}", toDo.get().getId());
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

    @Override
    public void deleteAll() {
        this.toDoRepository.deleteAll();
    }
}
