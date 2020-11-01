package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    private void startServer() {
        try{
            // create on port 1099
            Registry registry= LocateRegistry.createRegistry(1099);
            // create a new service named CounterService
            registry.rebind("CounterService", new CounterImpl());
        }
        catch(Exception e) {

        }
        System.out.println("system is ready");
    }

    public static void main(String[] args) {

        launch(args);


    }
}

