package api;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by Vishal Rathod on 2016-10-06.
 */
@SpringBootApplication
public class LPTApplication {
    public static void main(String[] args) {
        //SpringApplication.run(Application.class, args);
        RandomController runRandom = new RandomController();
        System.out.println(runRandom.randomThread().toString());
    }
}
