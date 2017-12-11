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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import static dancecraze.Main.currentPath;

/**
 *
 * @author Macbook Pro
 */
public class ShowScore {
    Label scoreLabel = new Label("");
    Label highLabel = new Label("");
    public Pane showScore(int score,int  highscore) throws IOException 
    {
        Pane game = new Pane();
        scoreLabel.setText(""+score);
        highLabel.setText(""+highscore);
        Path pauseBGPath = Paths.get(currentPath.toString(), "Image", "highscore.jpg");
        Path backPath1 = Paths.get(currentPath.toString(), "Image", "greenBack1.png");
        Path backPath2 = Paths.get(currentPath.toString(), "Image", "greenBack2.png");
        
        InputStream bgInput = Files.newInputStream(Paths.get(pauseBGPath.toString()));
        InputStream back1 = Files.newInputStream(Paths.get(backPath1.toString()));
        InputStream back2 = Files.newInputStream(Paths.get(backPath2.toString()));
        
        Image BgImg = new Image(bgInput);
        Image back1Img = new Image(back1);
        Image back2Img = new Image(back2);
        
        ImageView bg = new ImageView(BgImg);
        ImageView back = new ImageView(back1Img);
        bg.setY(0);
        
        back.setX(40);
        back.setY(520);

        scoreLabel.setTranslateX(380);
        scoreLabel.setTranslateY(230);
        scoreLabel.setScaleX(4);
        scoreLabel.setScaleY(4);
        scoreLabel.setTextFill(Color.web("#FFD4F4"));
        highLabel.setTranslateX(380);
        highLabel.setTranslateY(420);
        highLabel.setScaleX(4);
        highLabel.setScaleY(4);
        highLabel.setTextFill(Color.web("#FFD4F4"));
        
        
        back.setOnMouseExited(event-> back.setImage(back1Img));
        back.setOnMouseEntered(event-> back.setImage(back2Img));
        back.setOnMouseClicked(event->{
            try {
                Main.scene3 = new Scene(new SongLibrary().getSong(),800,600);
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
        
        
        
        
        game.getChildren().addAll(bg,back,scoreLabel,highLabel);
        
        return game;
    }
}

