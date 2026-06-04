package pe.edu.upeu.ProyectLP2; // 💡 Paquete raíz unificado de tus pruebas

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration // 🎯 Le dice a Cucumber que esta clase maneja el contexto de Spring
@SpringBootTest(classes = ProyectLp2Application.class) // 💡 Enciende el entorno usando tu clase principal real
@AutoConfigureMockMvc // 💡 Crea e inyecta el MockMvc de forma global para tus controladores
public class CucumberSpringConfig {
    // Esta clase se deja completamente vacía.
    // Solo sirve como el puente central de datos para que hereden tus clases de pasos.
}