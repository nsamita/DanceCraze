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
import static dancecraze.Main.gameScene;

public class HowToPlay{

    MediaPlayer summer;
    public Pane getPane2() throws IOException{
        Pane p2 = new Pane();
        
        summer = new Song("Summer").getPlayer();
        summer.play();
        Path PagePath = Paths.get(Main.currentPath.toString(), "Image", "howToPlay.jpg");
        Path backPath1 = Paths.get(currentPath.toString(), "Image", "greenBack1.png");
        Path backPath2 = Paths.get(currentPath.toString(), "Image", "greenBack2.png");
        
        InputStream input = Files.newInputStream(Paths.get(PagePath.toString()));
        InputStream back1 = Files.newInputStream(Paths.get(backPath1.toString()));
        InputStream back2 = Files.newInputStream(Paths.get(backPath2.toString()));
        
        
        Image BgImg = new Image(input);
        Image back1Img = new Image(back1);
        Image back2Img = new Image(back2);
        
        ImageView bg = new ImageView(BgImg);
        ImageView back = new ImageView(back1Img);
        
        bg.setY(0);
        
        back.setX(40);
        back.setY(520);
        
        back.setOnMouseExited(event-> back.setImage(back1Img));
        back.setOnMouseEntered(event-> back.setImage(back2Img));
        back.setOnMouseClicked(event->{
            try {
                summer.pause();
                Main.scene1 = new Scene(new Main().createContent());
                Main.primaryStage.setScene(Main.scene1);
                Main.primaryStage.show();
            } catch (IOException ex) {
                Logger.getLogger(SongLibrary.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });

        p2.getChildren().addAll(bg,back);
        
        return p2;
    }
}

