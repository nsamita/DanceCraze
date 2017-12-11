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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;
import static dancecraze.Main.currentPath;

/**
 *
 * @author Mac
 */
public class SongLibrary {

    MediaPlayer closer;
    MediaPlayer roses;
    public Pane getSong() throws IOException, SQLException, ClassNotFoundException {
        closer = new Song("Closer").getPlayer();
        roses = new Song("Roses").getPlayer();
        closer.play();
        
        Pane game = new Pane();
        Path closerPath = Paths.get(currentPath.toString(), "Image", "Closer.jpg");
        Path rosesPath = Paths.get(currentPath.toString(), "Image", "Roses.jpg");
        Path left1Path = Paths.get(currentPath.toString(), "Image", "left2.png");
        Path left2Path = Paths.get(currentPath.toString(), "Image", "left1.png");
        Path right1Path = Paths.get(currentPath.toString(), "Image", "right2.png");
        Path right2Path = Paths.get(currentPath.toString(), "Image", "right1.png");
        Path select1Path = Paths.get(currentPath.toString(), "Image", "select1.png");
        Path select2Path = Paths.get(currentPath.toString(), "Image", "select2.png");
        Path mainmenu1Path = Paths.get(currentPath.toString(), "Image", "mainmenu1.png");
        Path mainmenu2Path = Paths.get(currentPath.toString(), "Image", "mainmenu2.png");

        //this is for test 
        InputStream Closer = Files.newInputStream(Paths.get(closerPath.toString()));
        InputStream Roses = Files.newInputStream(Paths.get(rosesPath.toString()));
        InputStream left1 = Files.newInputStream(Paths.get(left1Path.toString()));
        InputStream left2 = Files.newInputStream(Paths.get(left2Path.toString()));
        InputStream right1 = Files.newInputStream(Paths.get(right1Path.toString()));
        InputStream right2 = Files.newInputStream(Paths.get(right2Path.toString()));
        InputStream select1 = Files.newInputStream(Paths.get(select1Path.toString()));
        InputStream select2 = Files.newInputStream(Paths.get(select2Path.toString()));
        InputStream mainmenu1 = Files.newInputStream(Paths.get(mainmenu1Path.toString()));
        InputStream mainmenu2 = Files.newInputStream(Paths.get(mainmenu2Path.toString()));

        Image ImgCloser = new Image(Closer);
        Image ImgRoses = new Image(Roses);
//        Image ImgSummer = new Image(Summer);
//        Image ImgThisIs = new Image(ThisIs);
        Image ImgLeft1 = new Image(left1);
        Image ImgLeft2 = new Image(left2);
        Image ImgRight1 = new Image(right1);
        Image ImgRight2 = new Image(right2);
        Image ImgSelect1 = new Image(select1);
        Image ImgSelect2 = new Image(select2);
        Image Imgmain1 = new Image(mainmenu1);
        Image Imgmain2 = new Image(mainmenu2);

        ImageView bg = new ImageView(ImgCloser);
        ImageView left = new ImageView(ImgLeft1);
        ImageView right = new ImageView(ImgRight1);
        ImageView select = new ImageView(ImgSelect1);
        ImageView mainmenu = new ImageView(Imgmain1);

        left.setX(50);
        left.setY(345);
        right.setX(650);
        right.setY(345);
        select.setX(300);
        select.setY(340);
        mainmenu.setX(250);
        mainmenu.setY(480);

        left.setOnMouseExited(event -> left.setImage(ImgLeft1));
        left.setOnMouseEntered(event -> left.setImage(ImgLeft2));
        left.setOnMouseClicked(event -> {
            if (bg.getImage().equals(ImgCloser)) {
                closer.pause();
                roses.play();
                bg.setImage(ImgRoses);
            } else if (bg.getImage().equals(ImgRoses)) {
                roses.pause();
                closer.play();
                bg.setImage(ImgCloser);
            }

        });
        right.setOnMouseExited(event -> right.setImage(ImgRight1));
        right.setOnMouseEntered(event -> right.setImage(ImgRight2));
        right.setOnMouseClicked(event -> {
            if (bg.getImage().equals(ImgCloser)) {
                closer.pause();
                roses.play();

                bg.setImage(ImgRoses);
            } else if (bg.getImage().equals(ImgRoses)) {
                roses.pause();
                closer.play();
                bg.setImage(ImgCloser);
            }

        });
        select.setOnMouseExited(event -> select.setImage(ImgSelect1));
        select.setOnMouseEntered(event -> select.setImage(ImgSelect2));
        select.setOnMouseClicked(event -> {
            Pane pane2;
            try {
                String temp = "Closer";
                if (bg.getImage().equals(ImgCloser)) {
                    temp = "Closer";
                } else if (bg.getImage().equals(ImgRoses)) {
                    temp = "Roses";
                }
                closer.pause();
                roses.pause();
                Main.scene4 = new Scene(new Mode().selectMode(temp));
                Main.primaryStage.setScene(Main.scene4);
                Main.primaryStage.show();
            } catch (IOException ex) {
                Logger.getLogger(SongLibrary.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        mainmenu.setOnMouseExited(event -> mainmenu.setImage(Imgmain1));
        mainmenu.setOnMouseEntered(event -> mainmenu.setImage(Imgmain2));
        mainmenu.setOnMouseClicked(event -> {
            try {
                closer.pause();
                roses.pause();
                Main.scene1 = new Scene(new Main().createContent());
                Main.primaryStage.setScene(Main.scene1);
                Main.primaryStage.show();
            } catch (IOException ex) {
                Logger.getLogger(SongLibrary.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        game.getChildren().addAll(bg, left, right, select, mainmenu);

        return game;
    }
}

