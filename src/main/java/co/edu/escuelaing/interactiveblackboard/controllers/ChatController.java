package co.edu.escuelaing.interactiveblackboard.controllers;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import co.edu.escuelaing.interactiveblackboard.entities.ChatMessage;
import co.edu.escuelaing.interactiveblackboard.entities.Crea;
import co.edu.escuelaing.interactiveblackboard.entities.User;


/**
 * Controlador para manejar el tema de enviar los mensajes y que se le envíen a todos los jugadores de la sala
 */
@Controller
public class ChatController { 

    /**
     * Método para que se registre el mensaje en cada una de las sesiones de los jugadores 
     * @param: @Payload ChatMessage
     * @return: ChatMessage
     */
    @MessageMapping("/chat.register")
    @SendTo("/topic/public")
    public ChatMessage register(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        System.out.println();
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        Crea.getInstance().getRooms().getPuntaje().setUser(new User(chatMessage.getSender()
        ));
        return chatMessage;
    }
    /**
     * Método para que todas las personas puedan ver el mensaje que cada uno de los jugadores envía a excepción del pintor
     * @param: @Payload ChatMessage
     * @return: ChatMessage
     */
    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        System.out.println("este es el contenido del mensaje " + chatMessage.getContent());
        System.out.println("esta es la persona que envia el mensaje " + chatMessage.getSender());
        System.out.println(Crea.getInstance().getRooms().getPuntaje().setWord(chatMessage.getContent(), chatMessage.getSender()));
        return chatMessage;
    }

}
