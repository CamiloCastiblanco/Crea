package co.edu.escuelaing.interactiveblackboard;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class, esta clase lo único que hace es ejecutarse por medio de Springboot
 */
@SpringBootApplication
public class BBAppStarter {
    public static void main(String[] args){
         SpringApplication.run(BBAppStarter.class,args);

      
    }
    
    static int getPort() {
        if (System.getenv("PORT") != null) {
        return Integer.parseInt(System.getenv("PORT"));
        }
        return 8080;
    }
}