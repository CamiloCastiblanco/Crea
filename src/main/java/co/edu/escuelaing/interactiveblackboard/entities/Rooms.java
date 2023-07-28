package co.edu.escuelaing.interactiveblackboard.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Rooms class, esta clase está encargada de manejar la lógica de las salas y como es asignado el tablero de cada sala, los usuarios de la misma y el pintor de esta.  
 */
public class Rooms {

    private HashMap<String, List<User>> salasDef;
    private Board board;
    private Puntaje puntaje;
    public Rooms() {
        board = new Board();
        puntaje = new Puntaje();
        defsalas();
    }

    public Board getBoard() {
        return this.board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    private void defsalas() {
        List<User> list = new ArrayList<>();
        salasDef = new HashMap<>();
        salasDef.put("Sala A", list);
        salasDef.put("Sala B", list);
        salasDef.put("Sala C", list);
        salasDef.put("Sala D", list);

    }

    public void settingUser(User user, String key) {
        List<User> list = (salasDef.get(key));
        list.add(user);
        salasDef.put(key, list); 
        for (User u : list) {
            try {
                System.out.println("llave " + key);
                
                System.out.println(u.getName());
            } catch (Exception e) {
                System.out.println("Error");
            }
        }
        settingPintor(user, key);
    }

    public int gettingSize(String name) {
        return salasDef.get(name).size();
    }

    public HashMap<String, List<User>> getSalasDef() {
        return this.salasDef;
    }

    public void setSalasDef(HashMap<String, List<User>> salasDef) {
        this.salasDef = salasDef;
    }
    
    /**
    * Método para asignar un pintor a la sala, comienza siendo el primero que ingreso a la sala y luego va rotando el turno según el orden de ingreso a la misma
    * @param: User user, String key
    * @return void
    */
    public void settingPintor(User user, String key ) {
        List<User> users = new ArrayList<>(salasDef.get(key));
            if (users.size() == 1 && users.contains(user)) {
                System.out.println("Es pintor");
                users.forEach(x -> x.setPintor(true));
            } else {
                System.out.println(" NOOO Es pintor");
                int pos = users.indexOf(user);
                users.get(pos).setPintor(false);
            }
            System.out.println("Usuario agregado: " + user.getName());
    }
    /**
    * Método para garantizar que una sala tendrá más de 1 jugador y no hacer el juego aburrido
    * @param: String key
    * @return boolean
    */
    public boolean numeroMinimoDePersonas(String key){
        boolean flag = false;
        if(numeroDePersonas(key)<=2){
            flag = false;
        }else{
            flag = true;
        }
        return flag;
    }
    public int numeroDePersonas(String key){
        return salasDef.get(key).size();
    }


    public Puntaje getPuntaje() {
        return this.puntaje;
    }

    public void setPuntaje(Puntaje puntaje) {
        this.puntaje = puntaje;

    }


}
