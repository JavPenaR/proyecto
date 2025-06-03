package cl.proyecto.proyecto;

import cl.proyecto.proyecto.controller.userController;
import cl.proyecto.proyecto.model.user;
import cl.proyecto.proyecto.service.userService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(userController.class)
public class userControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private userService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("GET /api/v0/usuarios devuelve lista de usuarios")
    public void testListarUsuarios() throws Exception {
        List<user> lista = Arrays.asList(
            new user(1, "Juan", "Pérez", "Gómez", "12345678-9", "juan@mail.com", "USER"),
            new user(2, "Ana", "López", "Soto", "98765432-1", "ana@mail.com", "ADMIN")
        );

        when(service.findAll()).thenReturn(lista);

        mockMvc.perform(get("/api/v0/usuarios/"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(2))
            .andExpect(jsonPath("$[0].nombre").value("Juan"));
    }

    @Test
    @DisplayName("GET /api/v0/usuarios cuando lista vacía devuelve 404")
    public void testListarUsuariosVacio() throws Exception {
        when(service.findAll()).thenReturn(List.of());

        mockMvc.perform(get("/api/v0/usuarios/"))
            .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("POST /api/v0/usuarios crea un usuario")
    public void testAgregarUsuario() throws Exception {
        user nuevo = new user(null, "Luis", "Martínez", "Rojas", "11111111-1", "luis@mail.com", "USER");
        user guardado = new user(3, "Luis", "Martínez", "Rojas", "11111111-1", "luis@mail.com", "USER");

        when(service.saveUser(any(user.class))).thenReturn(guardado);

        mockMvc.perform(post("/api/v0/usuarios/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nuevo)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").value(3))
            .andExpect(jsonPath("$.nombre").value("Luis"));
    }

    @Test
    @DisplayName("GET /api/v0/usuarios/{id} encuentra usuario")
    public void testBuscarPorIdExistente() throws Exception {
        user usr = new user(4, "Clara", "Fuentes", "Díaz", "22222222-2", "clara@mail.com", "ADMIN");

        when(service.findById(4)).thenReturn(usr);

        mockMvc.perform(get("/api/v0/usuarios/4"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.nombre").value("Clara"));
    }

    @Test
    @DisplayName("GET /api/v0/usuarios/{id} no existe")
    public void testBuscarPorIdNoExistente() throws Exception {
        when(service.findById(99)).thenReturn(null);

        mockMvc.perform(get("/api/v0/usuarios/99"))
            .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("PUT /api/v0/usuarios/{id} actualiza usuario")
    public void testActualizarUsuario() throws Exception {
        user actualizado = new user(5, "Marco", "Vega", "Núñez", "33333333-3", "marco@mail.com", "USER");

        when(service.update(any(user.class), eq(5))).thenReturn(actualizado);

        mockMvc.perform(put("/api/v0/usuarios/5")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(actualizado)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.nombre").value("Marco"));
    }

    @Test
    @DisplayName("PUT /api/v0/usuarios/{id} no encuentra usuario")
    public void testActualizarUsuarioNoEncontrado() throws Exception {
        when(service.update(any(user.class), eq(88))).thenReturn(null);

        mockMvc.perform(put("/api/v0/usuarios/88")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new user())))
            .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("DELETE /api/v0/usuarios/{id} elimina usuario")
    public void testEliminarUsuario() throws Exception {
        doNothing().when(service).deleteUsr(6);

        mockMvc.perform(delete("/api/v0/usuarios/6"))
            .andExpect(status().isNoContent());
    }
}
