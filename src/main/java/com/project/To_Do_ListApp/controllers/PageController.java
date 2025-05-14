package com.project.To_Do_ListApp.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.project.To_Do_ListApp.entities.ToDo;
import com.project.To_Do_ListApp.services.ToDoService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PageController implements ErrorController {
    @Autowired
    private ToDoService toDoService;

    @GetMapping("/")
    public String home(Model model) {
        Iterable<ToDo> toDoList = this.toDoService.getAllToDos();

        model.addAttribute("toDoList", toDoList);

        return "index";
    }

    @GetMapping("/add-item")
    public String addItem(Model model) {
        model.addAttribute("todo", new ToDo()); // matches th:object=""
        
        return "add-edit-item";
    }

    @PostMapping("/todos")
    public String createToDo(@ModelAttribute("todo") ToDo toDo) {
        toDoService.createToDo(toDo);
        return "redirect:/"; // send user back
    }
    
    //Edit item
    @GetMapping("/edit-item/{id}")
    public String editItem(@PathVariable("id") Integer id, Model model) {
        Optional<ToDo> selectedItem = toDoService.getToDoById(id);
        
        model.addAttribute("todo", selectedItem);
        return "add-edit-item";
    }

    @PostMapping("/delete-item/{id}")
    public String deleteItem(@PathVariable("id") Integer id) {
        toDoService.deleteToDo(id);

        return "redirect:/";
    }

    @PostMapping("/clear")
    public String deleteAllItems() {
        toDoService.deleteAll();
        return "redirect:/";
    }

    @GetMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error/404"; // this resolves to templates/error/404.html
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "error/500";
            }
        }

        return "error";
    }
}
