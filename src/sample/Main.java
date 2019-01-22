package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("BingoWindow.fxml"));
        primaryStage.setTitle("Let's Play Bingo!");
        primaryStage.getIcons().add(new Image("https://avatars3.githubusercontent.com/u/40849970?s=400&u=66111338b468e5a46fe1476b406e7640adcf066a&v=4"));
        primaryStage.setScene(new Scene(root, 1024, 800));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
