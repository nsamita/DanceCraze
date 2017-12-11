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
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import static dancecraze.Main.currentPath;

/**
 *
 * @author Mac
 */
public class Ball {

    int lifeTime = 150;
    Path ball;
    InputStream b;
    Image Img;
    ImageView iv;
    double updatedY;
    

    public Ball(String color) throws IOException {
        if (color.equals("blue")) {
            ball = Paths.get(currentPath.toString(), "Image", "blueball.png");
            b = Files.newInputStream(Paths.get(ball.toString()));
            Img = new Image(b);
            iv = new ImageView(Img);
            iv.setX(195);
            iv.setY(-100);

        } else if (color.equals("green")) {
            ball = Paths.get(currentPath.toString(), "Image", "greenball.png");
            b = Files.newInputStream(Paths.get(ball.toString()));
            Img = new Image(b);
            iv = new ImageView(Img);
            iv.setX(304);
            iv.setY(-100);
            
        } else if (color.equals("pink")) {
            ball = Paths.get(currentPath.toString(), "Image", "pinkball.png");
            b = Files.newInputStream(Paths.get(ball.toString()));
            Img = new Image(b);
            iv = new ImageView(Img);
            iv.setX(411.5);
            iv.setY(-100);
            
        } else if (color.equals("purple")) {
            ball = Paths.get(currentPath.toString(), "Image", "purpleball.png");
            b = Files.newInputStream(Paths.get(ball.toString()));
            Img = new Image(b);
            iv = new ImageView(Img);
            iv.setX(516.5);
            iv.setY(-100);
        }
        updatedY = -100;

    }

    public ImageView getBall() {
        return iv;
    }

    public void setUpdatedY(double updatedY) {
        this.updatedY = updatedY;
    }
    
    
    public double getY() {
        return this.updatedY;
    }
}

