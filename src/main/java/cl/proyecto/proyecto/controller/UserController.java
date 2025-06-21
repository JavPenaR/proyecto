package cl.proyecto.proyecto.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cl.proyecto.proyecto.assembler.UserModelAssembler;
import cl.proyecto.proyecto.dto.User;
import cl.proyecto.proyecto.dto.UserModel;
import cl.proyecto.proyecto.service.UserService;

@RestController
@RequestMapping("/api/v0/usuarios/")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private UserModelAssembler assembler;

    @GetMapping("")
    public ResponseEntity<CollectionModel<UserModel>> listarUsuarios(){
        List<User> usuarios = service.findAll();
        if (usuarios.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<UserModel> models = usuarios.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(models));
    }

    @PostMapping("")
    public ResponseEntity<UserModel> agregarUsuario(@RequestBody User usr){
        User saved = service.saveUser(usr);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(assembler.toModel(saved));
    }

    @GetMapping("{id}")
    public ResponseEntity<UserModel> buscarPorId(@PathVariable Integer id){
        User aux = service.findById(id);
        if(aux == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(aux));
    }

    @PutMapping("{id}")
    public ResponseEntity<UserModel> actualizar(@PathVariable Integer id, @RequestBody User usr){
        User aux = service.update(usr, id);
        if(aux == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(aux));
    }

    @GetMapping("mail/{mail}")
    public ResponseEntity<UserModel> buscarPorMail(@PathVariable String mail) {
        User aux = service.findByMail(mail);
        if(aux == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(aux));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> eliminar(@PathVariable Integer id){
        service.deleteUsr(id);
        return ResponseEntity.noContent().build();
    }
}
