package com.github.SarahTumenas.springwebmvc.controller;

import com.github.SarahTumenas.springwebmvc.model.Jedi;
import com.github.SarahTumenas.springwebmvc.repository.JediRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class JediController {

    @Autowired
    private JediRepository repository;

    @GetMapping ("/jedi")
    public ModelAndView jedi(){
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jedi");

        modelAndView.addObject("allJedi", repository.getAllJedi());

        return modelAndView;
    }

    @GetMapping ("/new-jedi")
    public String createJedi(Model model) {

        model.addAttribute("jedi", new Jedi());

        return "new-jedi";
    }

    @PostMapping("/jedi")
    public String createJedi(@Valid @ModelAttribute Jedi jedi, BindingResult result, RedirectAttributes redirect){

        if(result.hasErrors()){
            return "new-jedi";
        }

        repository.add(jedi);
        redirect.addFlashAttribute("message", "Jedi successfully created.");

        return "redirect:jedi";
    }
}
