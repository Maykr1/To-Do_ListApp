package com.project.To_Do_ListApp.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.project.To_Do_ListApp.entities.ToDo;
import com.project.To_Do_ListApp.repositories.ToDoRepository;

import org.springframework.web.bind.annotation.PostMapping;



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

    @GetMapping("/add-item")
    public String addItem(Model model) {
        model.addAttribute("todo", new ToDo()); // matches th:object=""
        
        return "add-item";
    }

    @PostMapping("/todos")
    public String createToDo(@ModelAttribute("todo") ToDo toDo) {
        toDoRepository.save(toDo);
        return "redirect:/"; // send user back
    }
    
    //Edit item
    @GetMapping("/edit-item/{id}")
    public String editItem(@PathVariable("id") Integer id, Model model) {
        Optional<ToDo> selectedItem = toDoRepository.findById(id);
        
        model.addAttribute("todo", selectedItem);
        return "edit-item";
    }

    //Update item after editing
    @PostMapping("/update-item")
    public String updateItem(@ModelAttribute("todo") ToDo toDo) {
        toDoRepository.save(toDo);
        return "redirect:/";
    }

    @PostMapping("/delete-item/{id}")
    public String deleteItem(@PathVariable("id") Integer id) {
        toDoRepository.deleteById(id);

        return "redirect:/";
    }

    @PostMapping("/clear")
    public String deleteAllItems() {
        toDoRepository.deleteAll();
        return "redirect:/";
    }   
}
