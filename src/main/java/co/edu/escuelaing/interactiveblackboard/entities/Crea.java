package co.edu.escuelaing.interactiveblackboard.entities;

import org.springframework.stereotype.Component;
/**
 * Componente principal 
 */
@Component
public class Crea {
    private Rooms rooms;
    private static Crea _instance = new Crea();
    public static Crea getInstance() {
        return _instance;
    }
    public Crea(){
        rooms = new Rooms();
    }

    public Rooms getRooms() {
        return this.rooms;
    }

    public void setRooms(Rooms rooms) {
        this.rooms = rooms;
    }
    

    @Override
    public String toString() {
        return "Crea [rooms=" + rooms + "]";
    }


}
