package com.example.hospital.controller;

import com.example.hospital.model.entity.Consulta;
import com.example.hospital.model.entity.Medico;
import com.example.hospital.model.repository.ConsultaRepository;
import com.example.hospital.model.repository.MedicoRepository;
import com.example.hospital.model.repository.PacienteRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Transactional
@Controller
@RequestMapping("consultas")
public class ConsultaController {
    @Autowired
    ConsultaRepository repository;
    @Autowired
    PacienteRepository prepository;
    @Autowired
    MedicoRepository mrepository;

    @PostMapping("/buscar")
    public ModelAndView buscarData(@RequestParam("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String data, ModelMap model) {
        if(data.isEmpty()){
            model.addAttribute("consultas", repository.consultas());
        }else {
            LocalDate localDate = LocalDate.parse(data);
            model.addAttribute("consultas", repository.buscarData(localDate));
        }
        return new ModelAndView("consultas/list", model);
    }

    @GetMapping("/list")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("consultas", repository.consultas());
        return new ModelAndView("consultas/list", model);
    }
    @GetMapping("/form")
    public ModelAndView listarpaciente(ModelMap model){
        model.addAttribute("consulta", new Consulta());
        model.addAttribute("pacientes", prepository.pacientes());
        model.addAttribute("medicos", mrepository.medicos());
        return new ModelAndView("consultas/form");
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid Consulta consulta, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            ModelAndView modelAndView = new ModelAndView("/consultas/form");
            modelAndView.addObject("pacientes", prepository.pacientes());
            modelAndView.addObject("medicos", mrepository.medicos());
            modelAndView.addObject("consulta", consulta);
            modelAndView.addObject("bindingResult", bindingResult);
            return modelAndView;
        }
        repository.save(consulta);
        return new ModelAndView("redirect:/consultas/list");
    }

    @PostMapping("/update")
    public ModelAndView update(@Valid Consulta consulta, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            ModelAndView modelAndView = new ModelAndView("/consultas/form");
            modelAndView.addObject("pacientes", prepository.pacientes());
            modelAndView.addObject("medicos", mrepository.medicos());
            modelAndView.addObject("consulta", consulta);
            modelAndView.addObject("bindingResult", bindingResult);
            return modelAndView;
        }
        repository.update(consulta);
        return new ModelAndView("redirect:/consultas/list");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("consulta", repository.consulta(id));
        model.addAttribute("pacientes", prepository.pacientes());
        model.addAttribute("medicos", mrepository.medicos());
        return new ModelAndView("consultas/form", model);
    }

    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id) {
        repository.remove(id);
        return new ModelAndView("redirect:/consultas/list");
    }
    @GetMapping("/mostrar/{id}")
    public ModelAndView mostrar(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("consulta", repository.consulta(id));
        return new ModelAndView("consultas/consulta", model);
    }
    @GetMapping("/listarpaciente/{id}")
    public ModelAndView listarpaciente(@PathVariable("id") Long id, ModelMap model){
        model.addAttribute("consultas", repository.consultasPaciente(id));
        return new ModelAndView("consultas/list");
    }
    @GetMapping("/listarmedico/{id}")
    public ModelAndView listarmedico(@PathVariable("id") Long id, ModelMap model){
        model.addAttribute("consultas", repository.consultasMedico(id));
        return new ModelAndView("consultas/list");
    }
}
