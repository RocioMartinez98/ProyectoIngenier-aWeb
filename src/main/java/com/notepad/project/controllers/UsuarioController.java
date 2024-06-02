package com.notepad.project.controllers;
import com.notepad.project.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.notepad.project.models.Usuario;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(path = "/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("usuarios", usuarioService.findAllUsuarios());
        return "listar";
    }

    @GetMapping("/nuevoUsuario")
    public String mostrarFormularioNuevoUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "crearUsuario";
    }

    @PostMapping("/nuevo")
    public String guardarNuevoUsuario(@ModelAttribute Usuario usuario) {
        usuarioService.create(usuario);
        return "redirect:/anotepad";
    }

    @GetMapping("/editarUsuario/{id}")
    public String mostrarFormularioEditarUsuario(@PathVariable Long id, Model model) {
        Usuario usuario = usuarioService.findById(id);
        model.addAttribute("usuario", usuario);
        return "editarUsuario";
    }

    @PostMapping("/editarUsuario/{id}")
    public String actualizarUsuario(@PathVariable Long id, @ModelAttribute Usuario usuario) {
        usuarioService.update(id, usuario);
        return "redirect:/usuario";
    }

    @GetMapping("/eliminarUsuario/{id}")
    public String eliminarUsuario(@PathVariable Long id) {
        usuarioService.delete(id);
        return "redirect:/usuario";
    }

    @GetMapping("/login")
    public String mostrarFormularioLogin(Model model, HttpSession session) {
        session.setAttribute("usuarioAutenticado", null); // Limpiar la sesión
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String correo, @RequestParam String clave, Model model, HttpSession session) {
        Usuario usuario = usuarioService.findByCorreoAndClave(correo, clave);
        if (usuario != null) {
            session.setAttribute("usuarioAutenticado", usuario);
            return "redirect:/anotepad/notasDeUsuario";
        } else {
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            return "login";
        }
    }


}
