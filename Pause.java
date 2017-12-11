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
import static dancecraze.Main.scene2;
import static dancecraze.Main.currentPath;

/**
 *
 * @author Mac
 */
public class Pause {
    public Pane getPause(String songname,String mode) throws IOException 
    {
        Pane game = new Pane();
        
        Path pauseBGPath = Paths.get(currentPath.toString(), "Image", "pauseBG.png");
        Path restart1Path = Paths.get(currentPath.toString(), "Image", "restart1.png");
        Path restart2Path = Paths.get(currentPath.toString(), "Image", "restart2.png");
        Path library1Path = Paths.get(currentPath.toString(), "Image", "musicLibrary1.png");
        Path library2Path = Paths.get(currentPath.toString(), "Image", "musicLibrary2.png");
        
        InputStream bgInput = Files.newInputStream(Paths.get(pauseBGPath.toString()));
        InputStream restart1 = Files.newInputStream(Paths.get(restart1Path.toString()));
        InputStream restart2 = Files.newInputStream(Paths.get(restart2Path.toString()));
        InputStream library1 = Files.newInputStream(Paths.get(library1Path.toString()));
        InputStream library2 = Files.newInputStream(Paths.get(library2Path.toString()));
        
        Image BgImg = new Image(bgInput);
        Image restart1Img = new Image(restart1);
        Image restart2Img = new Image(restart2);
        Image library1Img = new Image(library1);
        Image library2Img = new Image(library2);
        
        ImageView bg = new ImageView(BgImg);
        ImageView restart = new ImageView(restart1Img);
        ImageView library = new ImageView(library1Img);
        
        restart.setX(300);
        restart.setY(220);

        library.setX(230);
        library.setY(300);
        
        
        restart.setOnMouseExited(event-> restart.setImage(restart1Img));
        restart.setOnMouseEntered(event-> restart.setImage(restart2Img));
        restart.setOnMouseClicked(event->{
            try {
                Main.gameScene = new Scene(new Play().gameApp(songname,mode),800,600);
                Main.primaryStage.setScene(Main.gameScene);
                Main.primaryStage.show();
            } catch (IOException ex) {
                Logger.getLogger(Pause.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Pause.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Pause.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
        
        
        library.setOnMouseExited(event-> library.setImage(library1Img));
        library.setOnMouseEntered(event-> library.setImage(library2Img));
        library.setOnMouseClicked(event->{
            try {
                Main.scene3 = new Scene(new SongLibrary().getSong());
                Main.primaryStage.setScene(Main.scene3);
                Main.primaryStage.show();
            } catch (IOException ex) {
                Logger.getLogger(Pause.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Pause.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Pause.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
        
        
        
        game.getChildren().addAll(bg,restart,library);
        
        return game;
    }
}


