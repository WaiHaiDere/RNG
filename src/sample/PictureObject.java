package sample;

import javafx.scene.image.Image;

import java.io.File;

public class PictureObject {

    private String name;
    private Image picFile;

    public PictureObject(String name, Image fileName){
        this.name = name;
        this.picFile= fileName;
    }

    public String getName(){
        return name;
    }

    public Image getImage(){
        return picFile;
    }
}
