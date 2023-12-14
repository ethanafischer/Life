package com.example.javafx.life;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        Scene scene = new Scene(loader.load());
        Controller controller = loader.getController();
        controller.setStage(stage);
        controller.setScene(scene);

        stage.setTitle("Life");
        stage.setScene(scene);
        stage.show();
        System.out.println(stage.getWidth());
        System.out.println(stage.getHeight());
    }
    public static void main(String[] args) {
        launch(args);
    }
}