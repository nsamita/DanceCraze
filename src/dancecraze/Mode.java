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
import static dancecraze.Main.currentPath;

/**
 *
 * @author Mac
 */
public class Mode {
    public Pane selectMode(String songname) throws IOException{
        Pane p2 = new Pane();
        
        Path modePagePath = Paths.get(currentPath.toString(), "Image", "Mode.jpg");
        Path advanced1Path = Paths.get(currentPath.toString(), "Image", "advanced1.png");
        Path advanced2Path = Paths.get(currentPath.toString(), "Image", "advanced2.png");
        Path intermediate1Path = Paths.get(currentPath.toString(), "Image", "intermediate1.png");
        Path intermediate2Path = Paths.get(currentPath.toString(), "Image", "intermediate2.png");
        Path beginner1Path = Paths.get(currentPath.toString(), "Image", "beginner1.png");
        Path beginner2Path = Paths.get(currentPath.toString(), "Image", "beginner2.png");
        Path back1Path = Paths.get(currentPath.toString(), "Image", "back1.png");
        Path back2Path = Paths.get(currentPath.toString(), "Image", "back2.png");
        
        InputStream input = Files.newInputStream(Paths.get(modePagePath.toString()));
        InputStream advanced1 = Files.newInputStream(Paths.get(advanced1Path.toString()));
        InputStream advanced2 = Files.newInputStream(Paths.get(advanced2Path.toString()));
        InputStream intermediate1 = Files.newInputStream(Paths.get(intermediate1Path.toString()));
        InputStream intermediate2 = Files.newInputStream(Paths.get(intermediate2Path.toString()));
        InputStream beginner1 = Files.newInputStream(Paths.get(beginner1Path.toString()));
        InputStream beginner2 = Files.newInputStream(Paths.get(beginner2Path.toString()));
        InputStream back1 = Files.newInputStream(Paths.get(back1Path.toString()));
        InputStream back2 = Files.newInputStream(Paths.get(back2Path.toString()));
        
        
        Image BgImg = new Image(input);
        Image Beginner1Img = new Image(beginner1);
        Image Beginner2Img = new Image(beginner2);
        Image intermediate1Img = new Image(intermediate1);
        Image intermediate2Img = new Image(intermediate2);
        Image Advanced1Img = new Image(advanced1);
        Image Advanced2Img = new Image(advanced2);
        Image back1Img = new Image(back1);
        Image back2Img = new Image(back2);
        
        ImageView bg = new ImageView(BgImg);
        ImageView beginner = new ImageView(Beginner1Img);
        ImageView intermediate = new ImageView(intermediate1Img);
        ImageView advance = new ImageView(Advanced1Img);
        ImageView back = new ImageView(back1Img);
        
        beginner.setX(200);
        beginner.setY(200);
        intermediate.setX(140);
        intermediate.setY(280);
        advance.setX(200);
        advance.setY(360);
        back.setX(30);
        back.setY(480);
        
        beginner.setOnMouseExited(event-> beginner.setImage(Beginner1Img));
        beginner.setOnMouseEntered(event-> beginner.setImage(Beginner2Img));
        beginner.setOnMouseClicked(event -> {
            try {
                Main.gameScene = new Scene(new Play().gameApp(songname,"easy"),800,600);
                Main.primaryStage.setScene(Main.gameScene);
                Main.primaryStage.show();
            } catch (IOException ex) {
                Logger.getLogger(HowToPlay.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Mode.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Mode.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
        intermediate.setOnMouseExited(event-> intermediate.setImage(intermediate1Img));
        intermediate.setOnMouseEntered(event-> intermediate.setImage(intermediate2Img));
        intermediate.setOnMouseClicked(event -> {
            try {
                Main.gameScene = new Scene(new Play().gameApp(songname,"medium"),800,600);
                Main.primaryStage.setScene(Main.gameScene);
                Main.primaryStage.show();
            } catch (IOException ex) {
                Logger.getLogger(HowToPlay.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Mode.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Mode.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
        advance.setOnMouseExited(event-> advance.setImage(Advanced1Img));
        advance.setOnMouseEntered(event-> advance.setImage(Advanced2Img));
        advance.setOnMouseClicked(event -> {
            try {
                Main.gameScene = new Scene(new Play().gameApp(songname,"hard"),800,600);
                Main.primaryStage.setScene(Main.gameScene);
                Main.primaryStage.show();
            } catch (IOException ex) {
                Logger.getLogger(HowToPlay.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Mode.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Mode.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
        back.setOnMouseExited(event-> back.setImage(back1Img));
        back.setOnMouseEntered(event-> back.setImage(back2Img));
        back.setOnMouseClicked(event -> {
            try {
                Main.scene3 = new Scene(new SongLibrary().getSong());
                Main.primaryStage.setScene(Main.scene3);
                Main.primaryStage.show();
            } catch (IOException ex) {
                Logger.getLogger(HowToPlay.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Mode.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Mode.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
        
        p2.getChildren().addAll(bg,beginner,intermediate,advance,back);
        
        return p2;
    }
}

