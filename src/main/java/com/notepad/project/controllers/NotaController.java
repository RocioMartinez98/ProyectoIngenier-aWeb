package com.notepad.project.controllers;


import com.notepad.project.models.Nota;
import com.notepad.project.service.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/anotepad")
public class NotaController {

    @Autowired
    private NotaService notaService;

    @GetMapping
    public List<Nota> listar() {
        return notaService.findAllNotas();
    }

    @GetMapping("/listar")
    public String listarNotas(Model model) {
        List<Nota> notas = notaService.findAllNotas();
        model.addAttribute("NotasLista", notas); //key-valor
        return "anotepad";
    }

    //Crea una nota
    @GetMapping("/nueva")
    public String mostrarFormularioDeNuevaNota(Model model) {
        model.addAttribute("Nota", new Nota());
        model.addAttribute("accion", "");
        return "anotepad";
    }

    @PostMapping("/nueva")
    public String guardarNuevaPersona(@ModelAttribute Nota nota) {
        notaService.create(nota);
        return "redirect:/anotepad";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarNota(@PathVariable Long id, @ModelAttribute Nota nota, Model model) {
        model.addAttribute("nota", nota);
        model.addAttribute("accion", "/nota/editar/" + id);
        return "anotepad";
    }

    @PostMapping("/editar/{id}")
    public String actualizarPersona(@PathVariable Long id, @ModelAttribute Nota nota) {
        notaService.update(id, nota);
        return "redirect:/anotepad";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarNota(@PathVariable Long id) {
        notaService.delete(id);
        return "redirect:/anotepad";
    }

}
