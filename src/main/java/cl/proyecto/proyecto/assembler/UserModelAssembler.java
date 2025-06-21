package cl.proyecto.proyecto.assembler;

import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import cl.proyecto.proyecto.controller.UserController;
import cl.proyecto.proyecto.dto.User;
import cl.proyecto.proyecto.dto.UserModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class UserModelAssembler implements RepresentationModelAssembler<User, UserModel> {

    @Override
    public UserModel toModel(User user) {
        UserModel model = new UserModel();
        model.setId(user.getId());
        model.setNombre(user.getNombre());
        model.setApPaterno(user.getApPaterno());
        model.setApMaterno(user.getApMaterno());
        model.setRun(user.getRun());
        model.setMail(user.getMail());
        model.setRol(user.getRol());

        model.add(linkTo(methodOn(UserController.class).buscarPorId(user.getId())).withSelfRel());
        model.add(linkTo(methodOn(UserController.class).listarUsuarios()).withRel("usuarios"));
        model.add(linkTo(methodOn(UserController.class).eliminar(user.getId())).withRel("eliminar"));
        model.add(linkTo(methodOn(UserController.class).actualizar(user.getId(), user)).withRel("actualizar"));

        return model;
    }
}
