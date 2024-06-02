package com.notepad.project.controllers;


import com.notepad.project.models.Usuario;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.notepad.project.models.Nota;
import com.notepad.project.service.NotaService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/anotepad")
public class NotaController {
    private static final Logger logger = LoggerFactory.getLogger(NotaController.class);

    @Autowired
    private NotaService notaService;

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
        List<Nota> notas = notaService.findAllNotas();
        model.addAttribute("notasLista", notas);
        return "anotepad"; //nos retorna el archivo anotepad
    }

    /*@GetMapping("/anotepad")
    public String pantallaPrincipal(Model model) {
        List<Nota> notas = notaService.findAllNotas();
        model.addAttribute("notasLista", notas);
        return "anotepad"; // Nombre de la vista principal de Anotepad
    }*/

   /* @GetMapping("/listar")
    public String listarNotas(@RequestParam("titulo") String titulo, Model model) {
        //List<Nota> notas = notaService.findAllNotas();
        List<Nota> notas = notaService.findByTitulo(titulo);
        model.addAttribute("NotasLista", notas); //key-valor
        return "anotepad"; //nos retorna el archivo anotepad
    }*/

    @GetMapping("/listar")
    public String listarNotas(@RequestParam(value = "titulo", required = false) String titulo, Model model) {
        logger.info("Parámetro titulo recibido: {}", titulo); // Verificar el valor recibido
        List<Nota> notas;
        if (titulo != null && !titulo.isEmpty()) {
            notas = notaService.findByTitulo(titulo);
        } else {
            //notas = notaService.findAllNotas();
            notas = new ArrayList<>();
        }
        model.addAttribute("NotasLista", notas);
        return "listar";
    }

    @GetMapping("/detalle/{id}")
    public String mostrarDetalle(@PathVariable("id") Long id, Model model) {
        Nota nota = notaService.findById(id);
        if (nota == null) {
            // Manejo de error si la nota no existe
            return "funciones"; // Página de error personalizada
        }
        model.addAttribute("nota", nota);
        return "detalle"; // Nombre de la vista de detalle
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

    @GetMapping("/notasDeUsuario")
    public String listarNotasDeUsuario(Model model, HttpSession session) {
        Usuario usuarioAutenticado = (Usuario) session.getAttribute("usuarioAutenticado");
        if (usuarioAutenticado != null) {
            List<Nota> notas = usuarioAutenticado.getNotas();
            model.addAttribute("NotasLista", notas); //key-valor
            return "notasDeUsuario";
        } else {
            return "redirect:/usuario/login"; // Redirigir al login si no hay usuario autenticado
        }
    }

}
