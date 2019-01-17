package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    @FXML
    private Button startButton;
    
    @FXML
    private Button nextButton;
    
    @FXML
    private Button restartButton;

    @FXML
    private ImageView imageViewWindow;

    @FXML
    private Label pictureLabel;

    private List<PictureObject> pictureList = new ArrayList<PictureObject>();

    private List<Integer> doneNumbers = new ArrayList<Integer>();

    private Random rand = new Random();

    private PictureObject currentPic;

    private int numberOfPlays = 0;

    private int currentIndex;


    public void handleRestartButton(ActionEvent actionEvent) {
        doneNumbers.clear();
        restartButton.setDisable(true);
        startButton.setDisable(false);
        nextButton.setDisable(true);
        imageViewWindow.setImage(null);
        pictureLabel.setText("");
        numberOfPlays = 0;
    }

    public void handleNextButton(ActionEvent actionEvent) {
        getImage();
    }

    public void handleStartAction(ActionEvent actionEvent) {
        getImage();
        startButton.setDisable(true);
        restartButton.setDisable(false);
        nextButton.setDisable(false);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        PictureObject pic = new PictureObject("Crunchie", new Image("sample/Crunchie.JPG"));
        pictureList.add(pic);
        pic = new PictureObject("Fairy Bread", new Image("sample/FairyBread.JPG"));
        pictureList.add(pic);
        pic = new PictureObject("Fish and Chips", new Image("sample/FishAndChips.JPG"));
        pictureList.add(pic);
        pic = new PictureObject("Hangi", new Image("sample/Hangi.JPG"));
        pictureList.add(pic);
        pic = new PictureObject("Kakapo", new Image("sample/Kakapo.JPG"));
        pictureList.add(pic);
        pic = new PictureObject("Kangaroo", new Image("sample/Kangaroo.JPG"));
        pictureList.add(pic);
        pic = new PictureObject("Kiwi", new Image("sample/Kiwi.JPG"));
        pictureList.add(pic);
        pic = new PictureObject("Koala", new Image("sample/Koala.JPG"));
        pictureList.add(pic);
        pic = new PictureObject("Lamington", new Image("sample/Lamington.JPG"));
        pictureList.add(pic);
        pic = new PictureObject("L And P", new Image("sample/LandP.JPG"));
        pictureList.add(pic);
        pic = new PictureObject("Little Blue Penguin", new Image("sample/LittleBluePenguin.JPG"));
        pictureList.add(pic);
        pic = new PictureObject("Marmite", new Image("sample/Marmite.JPG"));
        pictureList.add(pic);
        pic = new PictureObject("Pavlova", new Image("sample/Pavlova.JPG"));
        pictureList.add(pic);
        pic = new PictureObject("Quokka", new Image("sample/Quokka.JPG"));
        pictureList.add(pic);
        pic = new PictureObject("Tim Tams", new Image("sample/TimTams.JPG"));
        pictureList.add(pic);
        pic = new PictureObject("Vegemite", new Image("sample/Vegemite.JPG"));
        pictureList.add(pic);

    }

    public int randomNumber(){
        int n = rand.nextInt(16);
        if (numberOfPlays==16){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("GAME FINISHED");
            alert.setHeaderText(null);
            alert.setContentText("The Game Has Finished. Please press restart");
            alert.showAndWait();

            return currentIndex;
        }
        while (doneNumbers.contains(n)){
            n = rand.nextInt(16);
        }
        doneNumbers.add(n);
        numberOfPlays++;
        return  n;
    }

    public void getImage(){
        currentIndex = randomNumber();
        currentPic = pictureList.get(currentIndex);

        pictureLabel.setText(currentPic.getName());
        imageViewWindow.setImage(currentPic.getImage());
    }
}
