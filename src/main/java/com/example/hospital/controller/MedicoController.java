package com.example.hospital.controller;

import com.example.hospital.model.entity.Medico;
import com.example.hospital.model.repository.MedicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Transactional
@Controller
@RequestMapping("medicos")
public class MedicoController {
    @Autowired
    MedicoRepository repository;

    @PostMapping("/buscar")
    public ModelAndView buscarMedico(@RequestParam("nome") String nome, ModelMap model) {
        model.addAttribute("medicos", repository.buscarNome(nome));
        return new ModelAndView("medicos/list", model);
    }


    @GetMapping("/list")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("medicos", repository.medicos());
        return new ModelAndView("medicos/list", model);
    }
    @GetMapping("/form")
    public String form(Medico medico){
        return "/medicos/form";
    }
    @PostMapping("/save")
    public ModelAndView save(@Valid Medico medico, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            ModelAndView modelAndView = new ModelAndView("/medicos/form");
            modelAndView.addObject("medico", medico);
            modelAndView.addObject("bindingResult", bindingResult);
            return modelAndView;
        }
        repository.save(medico);
        return new ModelAndView("redirect:/medicos/list");
    }
    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id){
        repository.remove(id);
        return new ModelAndView("redirect:/medicos/list");
    }
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("medico", repository.medico(id));
        return new ModelAndView("/medicos/form", model);
    }
    @PostMapping("/update")
    public ModelAndView update(@Valid Medico medico, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            ModelAndView modelAndView = new ModelAndView("/medicos/form");
            modelAndView.addObject("medico", medico);
            modelAndView.addObject("bindingResult", bindingResult);
            return modelAndView;
        }
        repository.update(medico);
        return new ModelAndView("redirect:/medicos/list");
    }
}
