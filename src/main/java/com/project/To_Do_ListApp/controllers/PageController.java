package com.project.To_Do_ListApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.To_Do_ListApp.entities.ToDo;
import com.project.To_Do_ListApp.repositories.ToDoRepository;

@Controller
public class PageController {
    @Autowired
    private ToDoRepository toDoRepository;

    @GetMapping("/")
    public String home(Model model) {
        Iterable<ToDo> toDoList = this.toDoRepository.findAll();

        model.addAttribute("toDoList", toDoList);

        return "index";
    }
}
