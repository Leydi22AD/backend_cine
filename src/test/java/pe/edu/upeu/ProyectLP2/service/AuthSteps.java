package pe.edu.upeu.ProyectLP2.service;

import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import pe.edu.upeu.ProyectLP2.CucumberSpringConfig;
import pe.edu.upeu.ProyectLP2.domain.model.Usuario;
import pe.edu.upeu.ProyectLP2.domain.port.on.UsuarioRepositoryPort;
import pe.edu.upeu.ProyectLP2.infraestructure.adapter.controller.dto.AuthDto;
// 💡 Asegúrate de importar tu AuthController real aquí
import pe.edu.upeu.ProyectLP2.infraestructure.adapter.controller.AuthController;
import pe.edu.upeu.ProyectLP2.infraestructure.entity.Rol;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AuthSteps extends CucumberSpringConfig {

    // 💡 Cambiamos restTemplate por tu controlador directo
    @Autowired
    private AuthController authController;

    @Autowired
    private UsuarioRepositoryPort usuarioRepositoryPort;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private ResponseEntity<AuthDto.AuthResponse> response;

    @Dado("que el usuario {string} ya esta registrado con la clave {string}")
    public void registrarUsuario(String email, String password) {
        usuarioRepositoryPort.findByEmail(email).orElseGet(() -> {
            Usuario usuario = new Usuario();
            usuario.setNombre("Leydi Arevalo");
            usuario.setEmail(email);
            usuario.setPassword(passwordEncoder.encode(password));
            usuario.setRol(Rol.CLIENTE);
            return usuarioRepositoryPort.save(usuario);
        });
    }

    @Cuando("intenta iniciar sesion con el correo {string} y clave {string}")
    public void iniciarSesion(String email, String password) {
        AuthDto.LoginRequest loginRequest = new AuthDto.LoginRequest(email, password);
        // 💡 Invocación local directa sin puentes de red externos
        response = authController.login(loginRequest);
    }

    @Entonces("el sistema debe permitir el ingreso y generar un token JWT")
    public void validarToken() {
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().token());
    }
}