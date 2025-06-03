package cl.proyecto.proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.proyecto.proyecto.model.user;
import cl.proyecto.proyecto.service.userService;

@RestController
@RequestMapping("/api/v0/usuarios/")
public class userController {

    @Autowired
    private userService service;

    @GetMapping("")
    public ResponseEntity<List<user>> listarUsuarios(){
        List<user> usuarios = service.findAll();
        if (usuarios.size() == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuarios);
    }
    @PostMapping("")
    public ResponseEntity<user> agregarUsuario(@RequestBody user usr){
        return new ResponseEntity<user>(service.saveUser(usr),HttpStatus.CREATED);
    }


    @GetMapping("{id}")
    public ResponseEntity<user> buscarPorId(@PathVariable Integer id){
        user aux = service.findById(id);
        if(aux == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(aux);
    }

    
    @PutMapping("{id}")
    public ResponseEntity<user> actualizar(@PathVariable Integer id, @RequestBody user usr){
        user aux = service.update(usr, id);
        if(aux == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(aux);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Object> eliminar(@PathVariable Integer id){
        service.deleteUsr(id);
        return ResponseEntity.noContent().build();
    }
}
