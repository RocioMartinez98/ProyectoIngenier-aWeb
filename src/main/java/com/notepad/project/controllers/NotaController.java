package com.notepad.project.controllers;

import com.notepad.project.models.Usuario;
import com.notepad.project.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.notepad.project.models.Nota;
import com.notepad.project.service.NotaService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/anotepad")
public class NotaController {
    private static final Logger logger = LoggerFactory.getLogger(NotaController.class);

    @Autowired
    private NotaService notaService;
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/notaSeleccionada")
    public String pantallanotaSeleccionada() {
        return "notaSeleccionada";
    }

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

    @GetMapping("/detalle/{id}")
    public String mostrarNota(Model model) {
        // Aquí puedes agregar la lógica para obtener la información de la nota
        String titulo = "Título de la nota";
        String texto = "Contenido de la nota";

        // Agregar los atributos al modelo para que estén disponibles en la plantilla Thymeleaf
        model.addAttribute("titulo", titulo);
        model.addAttribute("texto", texto);

        // Devolver el nombre de la plantilla Thymeleaf que representa esta página HTML
        return "detalle";
    }

    @GetMapping
    public String pantallaPrincipal(Model model) {
        model.addAttribute("Nota", new Nota());
        model.addAttribute("accion", "");
        List<Nota> notas = notaService.findAllNotas();
        model.addAttribute("notasLista", notas);
        return "anotepad"; //nos retorna el archivo anotepad
    }

    @GetMapping("/listar")
    public String listarNotas(@RequestParam(value = "titulo", required = false) String titulo, Model model) {
        logger.info("Parámetro titulo recibido: {}", titulo); // Verificar el valor recibido
        List<Nota> notas;
        if (titulo != null && !titulo.isEmpty()) {
            notas = notaService.findByTitulo(titulo);
        } else {
            notas = new ArrayList<>();
        }
        model.addAttribute("NotasLista", notas);
        return "listar";
    }

    @GetMapping("/notaSeleccionada/{id}")
    public String mostrarDetalle(@PathVariable("id") Long id, Model model) {
        List<Nota> notas = new ArrayList<>();
        Nota nota = notaService.findById(id);
        if (nota == null) {
            // Manejo de error si la nota no existe
            return "funciones";
        }
        model.addAttribute("nota", nota);
        model.addAttribute("accion", "/anotepad/editar/" + id);
        notas = notaService.findAllNotas();
        model.addAttribute("notasLista", notas);
        return "notaSeleccionada";
    }


    @PostMapping("/nueva")
    public String guardarNuevaNota(@ModelAttribute Nota nota) {
        this.notaService.create(nota);
        return "redirect:/anotepad";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarNota(@PathVariable Long id, Model model, HttpSession session) {
        Usuario usuarioAutenticado = (Usuario) session.getAttribute("usuarioAutenticado");
        if (usuarioAutenticado != null) {
            Nota nota = notaService.findById(id);
            if (nota == null) {
                // Manejo de error si la nota no existe
                return "funciones"; // Página de error personalizada
            }
            model.addAttribute("nota", nota);
            model.addAttribute("accion", "/anotepad/editar/" + id);
            return "notaSeleccionada"; // Nombre de la vista de detalle/edición
        } else {
            return "redirect:/usuario/login"; // Redirigir al login si no hay usuario autenticado
        }
    }

    @PostMapping("/editar/{id}")
    public String actualizarNota(@PathVariable Long id, @ModelAttribute Nota nota, HttpSession session) {
        Usuario usuarioAutenticado = (Usuario) session.getAttribute("usuarioAutenticado");
        if (usuarioAutenticado != null) {
            notaService.update(id, nota);
            return "redirect:/anotepad/notasDeUsuarioReload?reload=true";
        } else {
            return "redirect:/usuario/login";
        }
    }


    @GetMapping("/eliminarNota/{notaId}")
    public String eliminarNota(@PathVariable Long notaId, HttpSession session) {
        Usuario usuarioAutenticado = (Usuario) session.getAttribute("usuarioAutenticado");
        if (usuarioAutenticado != null) {
            notaService.eliminarNota(usuarioAutenticado.getId(), notaId);
            usuarioAutenticado = usuarioService.findById(usuarioAutenticado.getId());
            session.setAttribute("usuarioAutenticado", usuarioAutenticado);
            session.setAttribute("NotasLista", usuarioAutenticado.getNotas());
            return "redirect:/anotepad/notasDeUsuario";
        } else {
            return "redirect:/usuario/login";
        }
    }

    @GetMapping("/notasDeUsuario")
    public String listarNotasDeUsuario(Model model, HttpSession session) {
        model.addAttribute("Nota", new Nota());
        Usuario usuarioAutenticado = (Usuario) session.getAttribute("usuarioAutenticado");
        if (usuarioAutenticado != null) {
            List<Nota> notas = usuarioAutenticado.getNotas();
            model.addAttribute("NotasLista", notas); //key-valor
            return "notasDeUsuario";
        } else {
            return "redirect:/usuario/login";
        }
    }

    @GetMapping("/notasDeUsuarioReload")
    public String listarNotasDeUsuario(Model model, HttpSession session, @RequestParam(value = "reload", required = false) Boolean reload) {
        model.addAttribute("Nota", new Nota());
        Usuario usuarioAutenticado = (Usuario) session.getAttribute("usuarioAutenticado");
        if (usuarioAutenticado != null) {
            if (Boolean.TRUE.equals(reload)) {
                // Recargar la lista de notas
                List<Nota> notas = usuarioAutenticado.getNotas();
                model.addAttribute("NotasLista", notas);
            } else {
                // Si reload es null o false, obtenemos las notas del usuario autenticado
                List<Nota> notas = usuarioAutenticado.getNotas();
                model.addAttribute("NotasLista", notas);
            }
            return "notasDeUsuario";
        } else {
            return "redirect:/usuario/login"; // Redirigir al login si no hay usuario autenticado
        }
    }


    @PostMapping("/notasDeUsuario")
    public String guardarNuevaNota(@ModelAttribute Nota nota, HttpSession session) {
        Usuario usuarioAutenticado = (Usuario) session.getAttribute("usuarioAutenticado");
        if (usuarioAutenticado != null) {
            // Asigna la nota al usuario
            usuarioAutenticado.getNotas().add(nota);
            // Guarda la nota
            notaService.create(nota);
            // Actualiza el usuario en la base de datos
            usuarioService.update(usuarioAutenticado.getId(), usuarioAutenticado);
        }
        return "redirect:/anotepad/notasDeUsuario";
    }




}
