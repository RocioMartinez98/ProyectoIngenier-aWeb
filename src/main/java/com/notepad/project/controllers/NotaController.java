package com.notepad.project.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.notepad.project.models.Nota;
import com.notepad.project.service.NotaService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping(path = "/anotepad")
public class NotaController {

    @Autowired
    private NotaService notaService;

    /*@GetMapping
    public List<Nota> listar() {
        return notaService.findAllNotas();
    }*/

    @GetMapping("/header")
    public String header() {
        return "header";
    }

    @GetMapping("/footer")
    public String footer() {
        return "footer";
    }

    @GetMapping("/funciones")
    public String pantallaFunciones() {
        return "funciones";
    }

    @GetMapping("/acercaDe")
    public String pantallaAcercaDe() {
        return "acercaDe";
    }

    @GetMapping("/intimidad")
    public String pantallaIntimidad() {
        return "intimidad";
    }

    @GetMapping("/reportarAbuso")
    public String pantallaReportarAbuso() {
        return "reportarAbuso";
    }

    @GetMapping
    public String pantallaPrincipal(Model model) {
        model.addAttribute("Nota", new Nota());
        model.addAttribute("accion", "");
        return "anotepad"; //nos retorna el archivo anotepad
    }

    @GetMapping("/listar")
    public String listarNotas(Model model) {
        List<Nota> notas = notaService.findAllNotas();
        model.addAttribute("NotasLista", notas); //key-valor
        return "anotepad"; //nos retorna el archivo anotepad
    }

    @PostMapping("/nueva")
    public String guardarNuevaNota(@ModelAttribute Nota nota) {
        this.notaService.create(nota);
        return "redirect:/anotepad";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarNota(@PathVariable Long id, @ModelAttribute Nota nota, Model model) {
        model.addAttribute("nota", nota);
        model.addAttribute("accion", "/nota/editar/" + id);
        return "anotepad";
    }

    @PostMapping("/editar/{id}")
    public String actualizarNota(@PathVariable Long id, @ModelAttribute Nota nota) {
        notaService.update(id, nota);
        return "redirect:/anotepad";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarNota(@PathVariable Long id) {
        notaService.delete(id);
        return "redirect:/anotepad";
    }

}
