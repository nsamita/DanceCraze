/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dancecraze;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import static dancecraze.Main.currentPath;

/**
 *
 * @author macintoshhd
 */
public class Song {

    File musicFile;
    Media sound;
    MediaPlayer player;

    public Song(String music) throws IOException {
        if (music.equals("Closer")) {
            musicFile = new File("Songs" + File.separator + "Closer.mp3");

        } else if (music.equals("Roses")) {
            musicFile = new File("Songs" + File.separator + "Roses.mp3");
        } else if (music.equals("mainSong")) {
            musicFile = new File("Songs" + File.separator + "mainSong.mp3");
        
        } else if (music.equals("Summer")) {
            musicFile = new File("Songs" + File.separator + "Summer.mp3");
        }

        sound = new Media(musicFile.toURI().toString());
        player = new MediaPlayer(sound);

    }

    public Media getMedia() {
        return sound;
    }

    public MediaPlayer getPlayer() {
        return player;
    }

}
