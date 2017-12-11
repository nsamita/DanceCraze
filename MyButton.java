/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dancecraze;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static dancecraze.Main.currentPath;

/**
 *
 * @author Mac
 */
public class MyButton {

    Path button;
    InputStream b;
    Image Img;
    ImageView iv;

    public MyButton(String color) throws IOException {
        if (color.equals("blue1")) {
            button = Paths.get(currentPath.toString(), "Image", "blue1.png");
        }
        else if (color.equals("blue2")) {
            button = Paths.get(currentPath.toString(), "Image", "blue2.png");
        }
        else if (color.equals("green1")) {
            button = Paths.get(currentPath.toString(), "Image", "green1.png");
        }
        else if (color.equals("green2")) {
            button = Paths.get(currentPath.toString(), "Image", "green2.png");
        }
        else if (color.equals("pink1")) {
            button = Paths.get(currentPath.toString(), "Image", "pink1.png");
        }
        else if (color.equals("pink2")) {
            button = Paths.get(currentPath.toString(), "Image", "pink2.png");
        }
        else if (color.equals("purple1")) {
            button = Paths.get(currentPath.toString(), "Image", "purple1.png");
        }
        else if (color.equals("purple2")) {
            button = Paths.get(currentPath.toString(), "Image", "purple2.png");
        }
        
        b = Files.newInputStream(Paths.get(button.toString()));
        Img = new Image(b);
        iv = new ImageView(Img);
    }
    
    public ImageView getiv(){
        return iv;
    }
    
    public Image getImage(){
        return Img;
    }

}
