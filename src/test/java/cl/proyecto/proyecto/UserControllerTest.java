package cl.proyecto.proyecto;

import cl.proyecto.proyecto.controller.UserController;
import cl.proyecto.proyecto.dto.User;
import cl.proyecto.proyecto.dto.UserModel;
import cl.proyecto.proyecto.assembler.UserModelAssembler;
import cl.proyecto.proyecto.service.UserService;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService service;

    @MockitoBean
    private UserModelAssembler assembler;

    @Autowired
    private ObjectMapper objectMapper;

    // Dummy model para simular respuestas HATEOAS
    public static class DummyUserModel extends UserModel {
        public DummyUserModel(User usr) {
            this.setId(usr.getId());
            this.setNombre(usr.getNombre());
            this.setApPaterno(usr.getApPaterno());
            this.setApMaterno(usr.getApMaterno());
            this.setRun(usr.getRun());
            this.setMail(usr.getMail());
            this.setRol(usr.getRol());
            this.add(Link.of("http://localhost/api/v0/usuarios/" + usr.getId()).withSelfRel());
        }
    }

    @Test
    @DisplayName("GET /api/v0/usuarios retorna 404 si no hay usuarios")
    public void testListarUsuariosVacio() throws Exception {
        when(service.findAll()).thenReturn(List.of());

        mockMvc.perform(get("/api/v0/usuarios/"))
            .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("GET /api/v0/usuarios/{id} retorna 404 si no existe")
    public void testBuscarPorIdNoExistente() throws Exception {
        when(service.findById(99)).thenReturn(null);

        mockMvc.perform(get("/api/v0/usuarios/99"))
            .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("DELETE /api/v0/usuarios/{id} elimina usuario existente")
    public void testEliminarUsuario() throws Exception {
        doNothing().when(service).deleteUsr(6);

        mockMvc.perform(delete("/api/v0/usuarios/6"))
            .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("POST /api/v0/usuarios crea un usuario")
    public void testAgregarUsuario() throws Exception {
        User nuevo = new User(null, "Luis", "Martínez", "Rojas", "11111111-1", "luis@mail.com", "USER");
        User guardado = new User(3, "Luis", "Martínez", "Rojas", "11111111-1", "luis@mail.com", "USER");

        when(service.saveUser(any(User.class))).thenReturn(guardado);
        when(assembler.toModel(any(User.class))).thenReturn(new DummyUserModel(guardado));

        mockMvc.perform(post("/api/v0/usuarios/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nuevo)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.nombre").value("Luis"))
            .andExpect(jsonPath("$.id").value(3));
    }

    @Test
    @DisplayName("GET /api/v0/usuarios/{id} retorna usuario existente")
    public void testBuscarPorIdExistente() throws Exception {
        User usr = new User(4, "Clara", "Fuentes", "Díaz", "22222222-2", "clara@mail.com", "ADMIN");

        when(service.findById(4)).thenReturn(usr);
        when(assembler.toModel(any(User.class))).thenReturn(new DummyUserModel(usr));

        mockMvc.perform(get("/api/v0/usuarios/4"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.nombre").value("Clara"));
    }

    @Test
    @DisplayName("PUT /api/v0/usuarios/{id} actualiza usuario existente")
    public void testActualizarUsuario() throws Exception {
        User actualizado = new User(5, "Marco", "Vega", "Núñez", "33333333-3", "marco@mail.com", "USER");

        when(service.update(any(User.class), eq(5))).thenReturn(actualizado);
        when(assembler.toModel(any(User.class))).thenReturn(new DummyUserModel(actualizado));

        mockMvc.perform(put("/api/v0/usuarios/5")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(actualizado)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.nombre").value("Marco"));
    }

    @Test
    @DisplayName("PUT /api/v0/usuarios/{id} retorna 404 si no se encuentra usuario")
    public void testActualizarUsuarioNoEncontrado() throws Exception {
        when(service.update(any(User.class), eq(88))).thenReturn(null);

        mockMvc.perform(put("/api/v0/usuarios/88")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new User())))
            .andExpect(status().isNotFound());
    }
}
