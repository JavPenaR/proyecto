package cl.proyecto.proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.hateoas.RepresentationModel;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel extends RepresentationModel<UserModel> {
    
    private Integer id;
    private String nombre;
    private String apPaterno;
    private String apMaterno;
    private String run;
    private String mail;
    private String rol;
}
