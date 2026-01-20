package com.example.clinica.servicios;

import com.example.clinica.modelos.Paciente;
import com.example.clinica.repositorios.IPacienteRepositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ServicioPaciente {

    @Autowired //Inyeccion de dependencia
    private IPacienteRepositorio repositorio;

    //metodo(servicio web) para guardar un paciente en BD
    public Paciente guardarPaciente(Paciente datosPaciente){

        //revisar los datos que me enviaron (validar los datos de entrada)
        //nombre no este vacio
        if(datosPaciente.getNombreCompleto()==null || datosPaciente.getNombreCompleto()== "" || datosPaciente.getNombreCompleto().length()<4){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "El nombre ingresado no cumple con los criterios de nuestro servicio"
            );
        }

        //si el correo o el nombre que ingresan ya esta registrado
        //revisar si el correo si es un correo valido


        //Ejecuta si todas las validaciones pasaron la consulta de guardar
        return this.repositorio.save(datosPaciente);

    }

    //Metodo para buscar Paciente

    //Funcion para buscar todos los pacientes
    public List<Paciente> buscarTodos(){
        return this.repositorio.findAll();
    }

    //funcion para buscar un paciente por ID
    public Paciente buscarPorId(Integer id){
        
        Optional<Paciente> pacienteBuscado=this.repositorio.findById(id);
        if(!pacienteBuscado.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "No hemos encontrado el paciente con el id ingresado"
            );
        }
        return pacienteBuscado.get();
    }
    



}
