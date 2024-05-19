package controllers;


import models.Nota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import service.NotaService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api")
public class NotaController {

    @Autowired
    private NotaService notaService;


    //Devuelve todas las notas
    @GetMapping("/listar")
    public String listar(Model model) {
        List<Nota> notas = notaService.findAllNotas();
        model.addAttribute("notas", notas);
        return "anotepad"; // Este es el nombre de la plantilla Thymeleaf que debe existir en src/main/resources/templates
    }

    //Muestra una nota por id
    @GetMapping("/notas/{id}")
    public String show(@PathVariable long id){
        Nota nota = null;
        Map<String, Object> response = new HashMap<>();
        try{
            nota = notaService.findById(id);
        } catch(DataAccessException e){
            response.put("mensaje", "Error al realizar la consulta en la based e datos");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
        }
        if (nota == null) {
            String mensaje = "La nota ID: " + id + " no existe en la base de datos";
            response.put("mensaje", mensaje);
        }
        return "mostrarNota"; // Este es el nombre de la plantilla Thymeleaf que debe existir en src/main/resources/templates
    }

    //Crea una nota
    @PostMapping("/notas")
    public String create(@RequestBody Nota nota, BindingResult result){
        Nota notaNew = null;
        Map<String, Object> response = new HashMap<>();

        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors().stream().map(err ->
                            "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors",errors);
        }
        try {
            notaNew = notaService.create(nota);
        }catch(DataAccessException e){
            response.put("mensaje", "Error al insertar en la base de datos");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return "anotepad"; // Volver al formulario con los errores
        }
        response.put("mensaje","La nota ha sido creada con éxito");
        response.put("nota",notaNew);
        return "redirect:/anotepad"; //Redirigir a anotepad despues de crear nota
    }


    //Actualiza una nota
    @PutMapping("/notas/{id}")
    public String update(@RequestBody Nota nota, BindingResult result , @PathVariable Long id){
        Nota notaActual = notaService.findById(id);;
        Nota notaActualizada = null;
        Map<String, Object> response = new HashMap<>();

        if(result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream().map(err ->
                            "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors",errors);
            return "anotepad";
        }

        if (notaActual == null) {
            String mensaje = "Error, la nota ID: " + id + " no existe en la base de datos";
            response.put("mensaje", mensaje);
            return "anotepad";
        }
        try {
            notaActual.setTitulo(nota.getTitulo());
            notaActual.setTexto(nota.getTexto());
            notaActualizada = notaService.create(notaActual);
        }catch(DataAccessException e){
            response.put("mensaje", "Error al actualizar en la base de datos");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return "anotepad";
        }
        response.put("mensaje","La nota ha sido actualizado con éxito");
        response.put("nota", notaActualizada);
        return "redirect:anotepad";
    }


    //Elimina una nota por id
    @DeleteMapping("/notas/{id}")
    public String delete(@PathVariable long id){
        Nota notaActual = notaService.findById(id);
        Map<String, Object> response = new HashMap<>();

        if (notaActual == null) {
            String mensaje = "Error, la nota ID: " + id + " no existe en la base de datos";
            response.put("mensaje", mensaje);
            return "anotepad";
        }

        try{
            notaService.delete(id);
        }catch(DataAccessException e){
            response.put("mensaje", "Error al eliminar en la base de datos");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return "anotepad";
        }
        response.put("mensaje","La nota ha sido eliminada con éxito");
        response.put("nota", notaActual);
        return "redirect:/anotepad";

    }

}
