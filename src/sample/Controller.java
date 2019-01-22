package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.MalformedURLException;


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
    private Button labelButton;

    @FXML
    private Button importBtn;

    @FXML
    private Button clearBtn;


    @FXML
    private Pane mainPane;

    private List<PictureObject> pictureList = new ArrayList<PictureObject>();

    private List<Integer> doneNumbers = new ArrayList<Integer>();

    private Random rand = new Random();

    private PictureObject currentPic;

    private int numberOfPlays = 0;

    private int currentIndex;

    private Image backgroundImage = new Image("https://i.imgur.com/LuuEs0f.jpg");






    public void handleRestartButton(ActionEvent actionEvent) {
        doneNumbers.clear();
        restartButton.setDisable(true);
        startButton.setDisable(false);
        nextButton.setDisable(true);
        importBtn.setDisable(false);
        clearBtn.setDisable(false);
        imageViewWindow.setImage(null);
        labelButton.setText("");
        numberOfPlays = 0;
        currentPic= null;

        imageViewWindow.setImage(backgroundImage);

    }

    public void handleNextButton(ActionEvent actionEvent) {
        getImage();
    }

    public void handleStartAction(ActionEvent actionEvent) {
        if(pictureList.isEmpty()){
            showAlert("ERROR", "No images found");
        } else {
            getImage();
            startButton.setDisable(true);
            restartButton.setDisable(false);
            nextButton.setDisable(false);
            importBtn.setDisable(true);
            clearBtn.setDisable(true);
            
        }

    }
    

    public int randomNumber(){
        int n = rand.nextInt(pictureList.size());
        if (numberOfPlays==pictureList.size()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("GAME FINISHED");
            alert.setHeaderText(null);
            alert.setContentText("The Game Has Finished. Please press restart");
            alert.showAndWait();

            return currentIndex;
        }
        while (doneNumbers.contains(n)){
            n = rand.nextInt(pictureList.size());
        }
        doneNumbers.add(n);
        numberOfPlays++;
        return  n;
    }

    public void getImage(){
        currentIndex = randomNumber();
        currentPic = pictureList.get(currentIndex);

        double height = currentPic.getImage().getHeight();
        double width = currentPic.getImage().getWidth();

        labelButton.setText("");
        imageViewWindow.setImage(currentPic.getImage());
        imageViewWindow.setFitHeight(height);
        imageViewWindow.setFitWidth(width);


    }

    public void handleImportBtn(ActionEvent actionEvent) {

        getFile(importBtn);



    }

    private void getFile(Button btn) {
        List<File> pictureFileList;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Pictures");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        pictureFileList = fileChooser.showOpenMultipleDialog(btn.getScene().getWindow());

        if(pictureFileList != null) {
            for (File selectedFile : pictureFileList) {

                if ((selectedFile != null) && (selectedFile.getPath().substring(selectedFile.getAbsolutePath().lastIndexOf('.')).toLowerCase().equals(".jpg"))) {
                    String temp = selectedFile.getPath();
                    int slash = temp.lastIndexOf('\\');
                    int dot = temp.lastIndexOf('.');

                    String name = temp.substring(slash + 1, dot);


                    try {
                        Image img = new Image(String.valueOf(selectedFile.toURL()));
                        pictureList.add(new PictureObject(name, img));
                    } catch (MalformedURLException e) {

                    }


                } else {
                    showAlert("ERROR - Not a valid file", "Please choose a .txt file to import");
                }
            }
        }
    }

    private void showAlert(String title, String message) {

        Alert nonSelectedAlert = new Alert(Alert.AlertType.INFORMATION);
        nonSelectedAlert.setTitle(title);
        nonSelectedAlert.setHeaderText(null);
        nonSelectedAlert.setContentText(message);
        nonSelectedAlert.showAndWait();
    }

    public void handleClearAction(ActionEvent actionEvent) {
        pictureList.clear();
    }

    public void labelButtonAction(ActionEvent actionEvent) {
        if(currentPic != null) {
            labelButton.setText(currentPic.getName());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imageViewWindow.setImage(backgroundImage);
    }
}
