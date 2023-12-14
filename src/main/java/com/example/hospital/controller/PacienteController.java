package com.example.hospital.controller;

import com.example.hospital.model.entity.Medico;
import com.example.hospital.model.entity.Paciente;
import com.example.hospital.model.repository.PacienteRepository;
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
@RequestMapping("pacientes")
public class PacienteController {
    @Autowired
    PacienteRepository repository;

    @PostMapping("/buscar")
    public ModelAndView buscarPaciente(@RequestParam("nome") String nome, ModelMap model) {
        model.addAttribute("pacientes", repository.buscarNome(nome));
        return new ModelAndView("pacientes/list", model);
    }

    @GetMapping("/list")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("pacientes", repository.pacientes());
        return new ModelAndView("pacientes/list", model);
    }
    @GetMapping("/form")
    public String form(Paciente paciente){
        return "/pacientes/form";
    }
    @PostMapping("/save")
    public ModelAndView save(@Valid Paciente paciente, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            ModelAndView modelAndView = new ModelAndView("/pacientes/form");
            modelAndView.addObject("paciente", paciente);
            modelAndView.addObject("bindingResult", bindingResult);
            return modelAndView;
        }
        repository.save(paciente);
        return new ModelAndView("redirect:/pacientes/list");
    }
    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id){
        repository.remove(id);
        return new ModelAndView("redirect:/pacientes/list");
    }
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("paciente", repository.paciente(id));
        return new ModelAndView("/pacientes/form", model);
    }
    @PostMapping("/update")
    public ModelAndView update(@Valid Paciente paciente, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            ModelAndView modelAndView = new ModelAndView("/pacientes/form");
            modelAndView.addObject("paciente", paciente);
            modelAndView.addObject("bindingResult", bindingResult);
            return modelAndView;
        }
        repository.update(paciente);
        return new ModelAndView("redirect:/pacientes/list");
    }
}
