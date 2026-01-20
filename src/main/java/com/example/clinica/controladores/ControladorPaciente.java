package com.example.clinica.controladores;

import com.example.clinica.modelos.Paciente;
import com.example.clinica.servicios.ServicioPaciente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/saludapi/v1/pacientes")
public class ControladorPaciente {

    //Inyectando la dependencia al servicio
    @Autowired
    ServicioPaciente servicio;

    //Por cada funcion del servicio
    //se programa una funcion en el controlador
    @PostMapping
    public ResponseEntity<Paciente>guardar(@RequestBody Paciente datos){
        Paciente respuestaDelApi=this.servicio.guardarPaciente(datos);
        return ResponseEntity.status(HttpStatus.OK).body(respuestaDelApi);
    }

    //llamemos al servicio para activar la busqueda de todos los pacientes
    @GetMapping
    public ResponseEntity<List<Paciente>>buscarTodos(){
         return ResponseEntity.status(HttpStatus.OK).body(this.servicio.buscarTodos());
    }

    //llamemos al servicio para activar la busqueda por ID
    @GetMapping(value = "/{id}")
    public ResponseEntity<Paciente>buscarPorId(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(this.servicio.buscarPorId(id));
    }
    

}
