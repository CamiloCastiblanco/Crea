package co.edu.escuelaing.interactiveblackboard.entities;

import java.util.ArrayList;

import java.util.*;

/**
 * Color class, esta clase solo está encargada de mantener una lista de colores para el jugador 
 */
public class Color {
    private static List<String> rgb;

    public Color(){ 
        rgb = new ArrayList<>();
        colorRandom();
    }
    public void colorRandom(){
        rgb.add("darkblue");
        rgb.add("blueviolet");
        rgb.add("chartreuse");
        rgb.add("darkgreen");
        rgb.add("dodgerblue");
        rgb.add("orange");
        rgb.add("orchid");
    } 

    public String colorX(){
        String cadena = "";
        for(int i = 0 ; i<rgb.size();i++){
            Random random = new Random();
            int numeroRandom = random.nextInt(rgb.size());
            cadena = rgb.get(numeroRandom);
        }
        return cadena;
    }
   

}
