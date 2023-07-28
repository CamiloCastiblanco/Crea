package co.edu.escuelaing.interactiveblackboard.controllers;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.escuelaing.interactiveblackboard.entities.Crea;
import co.edu.escuelaing.interactiveblackboard.entities.User;
/**
 * Controlador Rest para manejar todo lo que tenga que ver con la sesión de los jugadores pero para todo lo que tenga que ver con servicios de usuarios y salas
 */
@RestController
public class DrawingServiceController {
    @Resource
    private HttpServletRequest request;
    int posicion = 0;
    @GetMapping("/status")
    public String status() {
        sessionManagement();
        String name = (String) request.getSession().getAttribute("name");
        return "{\"status\":\"Greetings from Spring Boot "
                + name + ", "
                + java.time.LocalDate.now() + ", "
                + java.time.LocalTime.now()
                + ". " + "The server is Runnig!\"}";
    }

    public void sessionManagement() {
       // System.out.println(request.getSession(true).getId());
    }
    /**
     * Servicio para tomar el nombre de cada uno de los usuarios que va entrando a las salas
     * @param: String name
     * @return: void
     */
    @PostMapping("/index")
    public void getName(@RequestParam(value = "name") String name) {
        System.out.println("name + " + name);
        Crea.getInstance().getRooms().getBoard().setUsuario(new User(name));
    }
    /**
     * Servicio para añadir a cada uno de los usuarios a la sala que solicitó
     * @param: String name, String rooms
     * @return: void
     */
    @PostMapping("/aRooms")
    public void addingUserToRoom(@RequestParam(value = "name") String name,@RequestParam(value = "rooms") String rooms) {
        System.out.println("rooms + " + rooms);
        Crea.getInstance().getRooms().settingUser(new User(name), rooms);
    }

    /**
     * Servicio para obtener una palabra generada de forma aleatoria y asignarla a la sesión de cada uno de los jugadores
     * 
     * @return: String
     */
    @GetMapping("/getWord")
    public String getWord() {
        sessionManagement();
        String word = Crea.getInstance().getRooms().getBoard().getWords().getRandomWord();
        Crea.getInstance().getRooms().getPuntaje().setPalabraRandom(word);
        return "{\"getWord\":\""
                + word +"\"}";

    }

    /**
     * Servicio para ver si una sala ya es apta para comenzar el juego
     * @param: String key
     * @return: ResponseEntity<?>
     */
    @GetMapping("/numeroPersonasBool")
    public ResponseEntity<?> numeroPersonasBool(@RequestParam(value = "key") String key) {
        return new ResponseEntity<>(Crea.getInstance().getRooms().numeroMinimoDePersonas(key), HttpStatus.ACCEPTED);
    }

    /**
     * Servicio para ir pasando el turno de pintar a cada jugador 
     * @param: String key
     * @return: ResponseEntity<?>
     */
    @GetMapping("/cambioPintor")
    public ResponseEntity<?> cambioPintor(@RequestParam(value = "pintor") String pintor) {
        return new ResponseEntity<>(Crea.getInstance().getRooms().getBoard().changingPintor(pintor), HttpStatus.ACCEPTED);
    }

    @GetMapping("/numeroPersonas")
    public ResponseEntity<?> numeroPersonas(@RequestParam(value = "key") String key) {
        return new ResponseEntity<>(Crea.getInstance().getRooms().numeroDePersonas(key), HttpStatus.ACCEPTED);
    }    
    
    @GetMapping("/game")
    public ResponseEntity<?> getStatus(@RequestParam (value = "pintor") String pintor ) {
        sessionManagement();
        posicion = Crea.getInstance().getRooms().getBoard().getPositionUser(pintor);
        User user = Crea.getInstance().getRooms().getBoard().getUsuarios().get(posicion);
        return new ResponseEntity<>(user.getPintor(),HttpStatus.ACCEPTED);
    }

    @GetMapping("/gettingPoints")
    public ResponseEntity<?> getPoints(@RequestParam (value = "name") String name) {
        return new ResponseEntity<>(Crea.getInstance().getRooms().getPuntaje().getPointsByUsername(name), HttpStatus.ACCEPTED);
    }

    @GetMapping("/getRoom")
    public String getRoom(@RequestParam(value = "pintor") String pintor) {
        String hola = "";
        return hola;
    }

    

}