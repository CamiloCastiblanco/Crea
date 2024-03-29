package co.edu.escuelaing.interactiveblackboard.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Board class, esta clase está encargada de manejar la lógica de cada tablero 
 */
public class Board {
    private List<User> users;
    private List<Color> colors;
    private Words words;
    private static  Board _instance = new Board();
    public static Board getInstance(){
        return _instance;
    }
    public Board(){
        users = new ArrayList<User>();
        colors = new ArrayList<Color>();
        words = new Words();
    }

    public List<User> getUsuarios() {
        return users;
    }
    public void setUsuario( User user) {
        if(!(users.contains(user))){
            users.add(user);
            System.out.println("Usuario agregado: " + user.getName());
        }
        
    }
    /**
    * Método para cambiar al pintor luego de haber terminado su turno
    * @param: String name
    * @return boolean
    */
    public boolean changingPintor(String name){
        boolean flag = false;
        for(int i = 0 ; i<users.size();i++){
            if(users.get(i).getName().equals(name)){
                if(users.get(i).getPintor()==true){
                    users.get(i).setPintor(false);
                    if(i<users.size()-1){
                        users.get(i+1).setPintor(true);
                    }else{
                        users.get(0).setPintor(true);
                    }
                }
            }            
        }
        return flag;
    }

    public int getPositionUser(String user){
        int pos = 0;
        for(int i = 0 ; i<users.size();i++){
            if(users.get(i).getName().equals(user)){
                pos = i;
            }
        }
        return pos;
    }

    public List<Color> getColores() {
        return colors;
    }

    public void setColores(List<Color> colores) {
        this.colors = colores;
    }

    public Words getWords() {
        return words;
    }

    public void setWords(Words words) {
        this.words = words;
    }
}
