package co.edu.escuelaing.interactiveblackboard.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * Puntaje class, esta clase está encargada de manejar la lógica de los puntos y como son asignados luego de haber adivinado la palabra  
 */

public class Puntaje {
    HashMap<String, String> puntaje;
    private List<User> users;
    private List<List<String>> jugadorPuntos;
    private String word;
    private User user;

    public Puntaje() {
        puntaje = new HashMap<>();
        users = new ArrayList<>();
        jugadorPuntos = new ArrayList<List<String>>();
        jugadorPuntos.add(new ArrayList<String>());
        jugadorPuntos.add(new ArrayList<String>());
    }
    /**
    * Método para asignar una palabra para adivinar luego de haber empezado el juego, verificando primero si la palabra que escribió el usuario es la correcta y asigna puntos
    * en el caso contrario no suma los puntos y sigue estando la misma palabra del principio
    * @param: String wordChat, String name
    * @return boolean
    */
    public int setWord(String wordChat, String name) {
        int memoria = 0;
        int pos = 0;
        
        if (jugadorPuntos.get(0).contains(name) && jugadorPuntos.get(1).contains("No tiene puntos de momento")) {
            
            if (wordChat.equals(word)) {
                System.out.println("las palabras son iguales!");
                for (User u : users) {
                    pos = jugadorPuntos.get(0).indexOf(name);
                    System.out.println("posicion del jugador " + pos);
                    if (u.getName().equals(jugadorPuntos.get(0).get(pos))) {
                        System.out.println("Se encontro el el usuario en la lista con el de la matriz");
                        System.out.println("Puntaje actual " + u.getPuntaje());
                        memoria = u.getPuntaje() + tienePuntos();
                        u.setPuntaje(memoria);
                        jugadorPuntos.get(1).set(pos, "punto");
                    }
                }
            } else {
                
                if (!(wordChat.equals(word))) {
                    System.out.println("las palabras NO son iguales!");
                    for (User u : users) {
                        pos = jugadorPuntos.get(0).indexOf(name);                        
                        if (u.getName().equals(jugadorPuntos.get(0).get(pos))) {
                            System.out.println("Se encontro el el usuario en la lista con el de la matriz");
                            memoria = u.getPuntaje();
                            u.setPuntaje(memoria);
                            jugadorPuntos.get(1).set(pos, "No tiene puntos de momento");
                        }
                    }
                }
            }
        }
        return memoria;
    }
    
    
    private int tienePuntos() {
        int cont = jugadorPuntos.get(0).size();
        for (int i = 0; i < jugadorPuntos.get(0).size(); i++) {
            if (jugadorPuntos.get(1).get(i).equals("punto")) {
                cont--;
            }
        }
        return cont;
    }

    public int getPointsByUsername(String name) {
        int points = 0;
        for (User u : users) {
            if (u.getName().equals(name)) {
                points = u.getPuntaje();
            }
        }
        return points;
    }

    public List<User> getUsers() {
        return this.users;
    }

    public void setUsers(List<User> user) {
        this.users = user;
    }

    public HashMap<String, String> getPuntaje() {
        return this.puntaje;
    }

    public void setPuntaje(HashMap<String, String> puntaje) {
        this.puntaje = puntaje;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
        users.add(user);
        jugadorPuntos.get(0).add(user.getName());
        jugadorPuntos.get(1).add("No tiene puntos de momento");
    }

    public List<List<String>> getJugadorPuntos() {
        return this.jugadorPuntos;
    }

    public void setJugadorPuntos(List<List<String>> jugadorPuntos) {
        this.jugadorPuntos = jugadorPuntos;
    }

    public String getPalabraRandom() {
        return this.word;
    }

    public void setPalabraRandom(String palabraRandom) {
        this.word = palabraRandom;
    }

}
