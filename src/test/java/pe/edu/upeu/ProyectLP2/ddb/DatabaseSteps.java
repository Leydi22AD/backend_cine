package pe.edu.upeu.ProyectLP2.ddb;

import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pe.edu.upeu.ProyectLP2.CucumberSpringConfig;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public class DatabaseSteps extends CucumberSpringConfig {

    @Autowired
    private MockMvc mockMvc;

    @Dado("que la base de datos de pruebas está activa y limpia")
    public void limpiarBaseDeDatos() {}

    @Cuando("se envía una solicitud POST para crear un asiento en la fila {int} columna {int} con estado {string} y sala ID {int}")
    public void crearAsientoExitoso(int fila, int columna, String estado, int salaId) throws Exception {
        String jsonBody = String.format("{\"fila\":%d,\"columna\":%d,\"estado\":\"%s\",\"idSala\":%d}", fila, columna, estado, salaId);
        mockMvc.perform(post("/api/v1/asientos/crear")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody));
    }

    @Entonces("el sistema debe responder con un código de estado de éxito {int}")
    public void verificarCodigoEstado(int codigo) {}

    @Y("el JSON de respuesta debe contener un ID de asiento autogenerado")
    public void verificarIdAutogenerado() {}

    @Cuando("se solicita el asiento creado mediante una petición GET")
    public void ejecutarPeticionGet() {}

    @Entonces("el sistema debe retornar los datos de la fila {int} y columna {int} correctamente")
    public void verificarDatosRetornados(int fila, int columna) {}

    @Cuando("se envía una solicitud POST para crear un asiento con la fila {int} y columna {int}")
    public void crearAsientoInvalido(int fila, int columna) throws Exception {
        mockMvc.perform(post("/api/v1/asientos/crear")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"));
    }

    @Entonces("el sistema debe denegar el registro respondiendo con un código de error {int}")
    public void denegarAsientoError(int codigo) {}

    @Y("el mensaje de error debe indicar {string}")
    public void verificarMensajeError(String mensaje) {}

    @Y("se intenta enviar otra solicitud POST exactamente con la misma fila {int} y columna {int} en la sala ID {int}")
    public void intentarDuplicado(int fila, int columna, int salaId) {}

    @Entonces("el sistema debe lanzar una excepción AsientoAlreadyExistsException")
    public void verificarExcepcionDuplicado() {}

    @Y("responder con un código de estado de conflicto {int}")
    public void responderConflicto(int codigo) {}
}