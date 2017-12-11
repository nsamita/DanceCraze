/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dancecraze;

import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Box;
import javafx.util.Duration;
import static dancecraze.Main.scene2;
import static dancecraze.Main.currentPath;
import java.sql.*;
import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.util.Duration;
import java.text.DecimalFormat;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class Play {

    Duration time = Duration.millis(0);


    int score = 0;

    ImageView bg;
    ImageView pause;
    ImageView ivblue;
    ImageView ivgreen;
    ImageView ivpink;
    ImageView ivpurple;

    ArrayList<Integer> arrayPos1 = new ArrayList<Integer>();
    ArrayList<Integer> arrayPos2 = new ArrayList<Integer>();
    ArrayList<Integer> arrayPos3 = new ArrayList<Integer>();
    ArrayList<Integer> arrayPos4 = new ArrayList<Integer>();
    ArrayList<Integer> tryTime = new ArrayList<Integer>();
    private Connection connect1;
    private Statement stat1;
    private ResultSet rs;

    ArrayList<Ball> blueList;
    ArrayList<Ball> greenList;
    ArrayList<Ball> pinkList;
    ArrayList<Ball> purpleList;

    ArrayList<TranslateTransition> transList;

    int comp;
    Label scoreLabel = new Label("0");
    Label highLabel = new Label("");
    int highScore;
    String songname;
    String mode;

    MyButton blue1;
    MyButton blue2;
    MyButton green1;
    MyButton green2;
    MyButton pink1;
    MyButton pink2;
    MyButton purple1;
    MyButton purple2;

    public Pane gameApp(String songname, String mode) throws IOException, SQLException, ClassNotFoundException {
        //boolean state = false;
        highScore hScore = new highScore();
        this.songname = songname;
        this.mode = mode;

        blue1 = new MyButton("blue1");
        blue2 = new MyButton("blue2");
        green1 = new MyButton("green1");
        green2 = new MyButton("green2");
        pink1 = new MyButton("pink1");
        pink2 = new MyButton("pink2");
        purple1 = new MyButton("purple1");
        purple2 = new MyButton("purple2");

        Pane game = new Pane();
        Path gameScreenPath = Paths.get(currentPath.toString(), "Image", "basicBg.png");
        Path pause1Path = Paths.get(currentPath.toString(), "Image", "pause1.png");
        Path pause2Path = Paths.get(currentPath.toString(), "Image", "pause2.png");


        InputStream input = Files.newInputStream(Paths.get(gameScreenPath.toString()));
        InputStream pause1 = Files.newInputStream(Paths.get(pause1Path.toString()));
        InputStream pause2 = Files.newInputStream(Paths.get(pause2Path.toString()));


        Image BgImg = new Image(input);
        Image pause1Img = new Image(pause1);
        Image pause2Img = new Image(pause2);

        scoreLabel.setTranslateX(85);
        scoreLabel.setTranslateY(100);
        scoreLabel.setScaleX(3);
        scoreLabel.setScaleY(3);
        scoreLabel.setTextFill(Color.web("#FFD4F4"));

        highLabel.setTranslateX(85);
        highLabel.setTranslateY(450);
        highLabel.setScaleX(3);
        highLabel.setScaleY(3);
        highLabel.setTextFill(Color.web("#FFD4F4"));

        //ballList = new ArrayList<Ball>();
        blueList = new ArrayList<Ball>();
        greenList = new ArrayList<Ball>();
        pinkList = new ArrayList<Ball>();
        purpleList = new ArrayList<Ball>();
        transList = new ArrayList<TranslateTransition>();


        bg = new ImageView(BgImg);
        pause = new ImageView(pause1Img);

        ivblue = blue1.getiv();
        ivgreen = green1.getiv();
        ivpink = pink1.getiv();
        ivpurple = purple1.getiv();

        pause.setX(680);
        pause.setY(30);

        //button pos 
        ivblue.setX(184);
        ivgreen.setX(295.5);
        ivpink.setX(404.5);
        ivpurple.setX(511);
        ivblue.setY(490);
        ivgreen.setY(490);
        ivpink.setY(490);
        ivpurple.setY(490);

        // get location positions
        //nat's path
        connect1 = DriverManager.getConnection("jdbc:ucanaccess:///Users/macintoshhd/Desktop/DanceCraze/tapNodes.accdb");
        //fon's path
        //connect1 = DriverManager.getConnection("jdbc:ucanaccess://C://Users//Mac//Desktop//crazyTap//tapNodes.accdb");
        //kk's path
        //connect1 = DriverManager.getConnection("jdbc:ucanaccess://C://Users//Macbook Pro//Documents//GitHub//crazyTap//tapNodes.accdb");
        stat1 = connect1.createStatement();

        if (songname.equals("Closer")) {
            if (mode.equals("easy")) {
                highScore = hScore.getCloser1();
            } else if (mode.equals("medium")) {
                highScore = hScore.getCloser2();
            } else {
                highScore = hScore.getCloser3();
            }
        } else if (songname.equals("Roses")) {
            if (mode.equals("easy")) {
                highScore = hScore.getRoses1();
            } else if (mode.equals("medium")) {
                highScore = hScore.getRoses2();
            } else {
                highScore = hScore.getRoses3();
            }
        } 
        highLabel.setText("" + highScore);
        rs = stat1.executeQuery(selectAccess(mode, songname));

        while (rs.next()) {
            int timey = Integer.parseInt(rs.getString(1));
            System.out.println("timey = "+timey);
            int pos1 = rs.getInt(2);
            int pos2 = rs.getInt(3);
            int pos3 = rs.getInt(4);
            int pos4 = rs.getInt(5);
            tryTime.add(timey);
            arrayPos1.add(pos1);
            arrayPos2.add(pos2);
            arrayPos3.add(pos3);
            arrayPos4.add(pos4);

            //System.out.println(timey + " " + pos1+ " " + pos2+ " " +pos3+ " " +pos4);
        }

        MediaPlayer summer = new Song(songname).getPlayer();

        final TextField textBox = new TextField();
        textBox.setPromptText("Write here");

        int dutime=0;
        if(songname.equals("Closer")) dutime = 91000;
        else if(songname.equals("Roses")) dutime = 105010;
        Duration summerTime = Duration.millis(dutime);

        int comp = tryTime.get(0);
        System.out.println("comp = " + comp);
        int index = 0;

        summer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                for (Ball ball : blueList) {
                    ball.setUpdatedY(ball.iv.getTranslateY());
                    
                }
                for (Ball ball : greenList) {
                    ball.setUpdatedY(ball.iv.getTranslateY());
                }
                for (Ball ball : pinkList) {
                    ball.setUpdatedY(ball.iv.getTranslateY());
                }
                for (Ball ball : purpleList) {
                    ball.setUpdatedY(ball.iv.getTranslateY());
                }
                if ((int) newValue.toMillis() == (int) summer.getMedia().getDuration().toMillis()) {
                    Pane pane3;
                    try {
                        hScore.rewrite(highScore, songname, mode);
                        pane3 = new ShowScore().showScore(score, highScore);
                        Main.scene6 = new Scene(pane3, 800, 600);
                        Main.primaryStage.setScene(Main.scene6);
                        Main.primaryStage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });


        while (summerTime.greaterThan(time)) {
            if ((int) time.toMillis() == comp) {
                if (arrayPos1.get(index) == 1) {

                    Ball temp = new Ball("blue");
                    ImageView iv = temp.getBall();
                    //iv.setY(0);
                    TranslateTransition tt = new TranslateTransition(Duration.millis(1500), iv);
                    //tt.setFromY(700);
                    tt.setToY(700);
                    tt.setDelay(Duration.millis((int) time.toMillis()));
                    tt.play();

                    transList.add(tt);
                    blueList.add(temp);

                    System.out.println("1");
                }
                if (arrayPos2.get((int) time.toSeconds()) == 1) {
                    Ball temp = new Ball("green");
                    ImageView iv = temp.getBall();
                    //iv.setY(600);
                    TranslateTransition tt = new TranslateTransition(Duration.millis(1500), iv);
                    //tt.setFromY(700);
                    tt.setToY(700);
                    tt.setDelay(Duration.millis((int) time.toMillis()));
                    tt.play();

                    transList.add(tt);
                    greenList.add(temp);
                    System.out.println("2");
                }
                if (arrayPos3.get((int) time.toSeconds()) == 1) {
                    Ball temp = new Ball("pink");
                    ImageView iv = temp.getBall();
                    //iv.setY(600);
                    TranslateTransition tt = new TranslateTransition(Duration.millis(1500), iv);
                    //tt.setFromY(700);
                    tt.setToY(700);
                    tt.setDelay(Duration.millis((int) time.toMillis()));
                    tt.play();

                    transList.add(tt);
                    pinkList.add(temp);
                    System.out.println("3");
                }
                if (arrayPos4.get((int) time.toSeconds()) == 1) {
                    Ball temp = new Ball("purple");
                    ImageView iv = temp.getBall();
                    //iv.setY(600);
                    TranslateTransition tt = new TranslateTransition(Duration.millis(1500), iv);
                    //tt.setFromY(700);
                    tt.setToY(700);
                    tt.setDelay(Duration.millis((int) time.toMillis()));
                    tt.play();

                    transList.add(tt);
                    purpleList.add(temp);
                    System.out.println("4");
                }
                index++;
                comp = tryTime.get(index);
            }

            textBox.setOnKeyPressed(new TextBoxHandler());

            textBox.setOnKeyReleased(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent ke) {

                    if (ke.getText().equals("s")) {
                        ivblue.setImage(blue1.getImage());
                    } else if (ke.getText().equals("d")) {
                        ivgreen.setImage(green1.getImage());
                    } else if (ke.getText().equals("j")) {
                        ivpink.setImage(pink1.getImage());
                    } else if (ke.getText().equals("k")) {
                        ivpurple.setImage(purple1.getImage());
                    }

                }

            });

            time = time.add(Duration.millis(1));
            //System.out.println("time : " + (int) time.toMillis());
        }

        pause.setOnMouseExited(event
                -> pause.setImage(pause1Img));
        pause.setOnMouseEntered(event
                -> pause.setImage(pause2Img));
        pause.setOnMouseClicked(event
                -> {
            try {
                summer.pause();
                Main.scene5 = new Scene(new Pause().getPause(songname, mode));
                Main.primaryStage.setScene(Main.scene5);
                Main.primaryStage.show();
            } catch (IOException ex) {
                Logger.getLogger(Play.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        new Thread(new Runnable() {

            public void run() {
                try {
                    Thread.sleep(820);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Play.class.getName()).log(Level.SEVERE, null, ex);
                }

                //do something.........
                System.out.println("all things done");
            }
        }).run();
        summer.play();
        System.out.println("Score = " + score);
        game.getChildren().addAll(textBox, bg, pause);
        for (int i = 0; i < blueList.size(); i++) {
            game.getChildren().add(blueList.get(i).getBall());
            System.out.println("getbb");

        }
        for (int i = 0; i < greenList.size(); i++) {
            game.getChildren().add(greenList.get(i).getBall());
            System.out.println("getgb");
        }
        for (int i = 0; i < pinkList.size(); i++) {
            game.getChildren().add(pinkList.get(i).getBall());
            System.out.println("getpb");
        }
        for (int i = 0; i < purpleList.size(); i++) {
            game.getChildren().add(purpleList.get(i).getBall());
            System.out.println("getppb");
        }

        game.getChildren().addAll(ivblue, ivgreen, ivpink, ivpurple, scoreLabel, highLabel);
        return game;
    }

    private String selectAccess(String mode, String songname) throws SQLException {

        String select = "select time, col1, col2, col3,col4 ";
        //String selectcloserM = "select time, col1, col2, col3,col4 from closer2";
        //String selecteasycloser = "select "
        if (songname.equals("Closer")) {
            if (mode.equals("easy")) {

                select += "from closer1";
                highLabel.setText("" + highScore);
            } else if (mode.equals("medium")) {
                select += "from closer2";
                highLabel.setText("" + highScore);
            } else {
                select += "from closer3";
                highLabel.setText("" + highScore);
            }
        } else if (songname.equals("Roses")) {
            if (mode.equals("easy")) {
                select += "from Roses1";
                highLabel.setText("" + highScore);
            } else if (mode.equals("medium")) {
                select += "from Roses2";
                highLabel.setText("" + highScore);
            } else {
                select += "from Roses3";
                highLabel.setText("" + highScore);
            }
        } 

        return select;
    }

    private class TextBoxHandler implements EventHandler<KeyEvent> {

        public void handle(KeyEvent ke) {
            if (ke.getText().equals("s")) {
                ivblue.setImage(blue2.getImage());
                for (Ball ball : blueList) {
                    System.out.println(ball.getY());
                    if (ball.getY() >= 490 && ball.getY() < 599) {
                        score += 5;
                        System.out.println("blue hit " + score);
                    }
                }

            } else if (ke.getText().equals("d")) {
                ivgreen.setImage(green2.getImage());
                for (Ball ball : greenList) {
                    System.out.println(ball.getY());
                    if (ball.getY() >= 490 && ball.getY() < 599) {
                        score += 5;
                        System.out.println("green hit " + score);
                    }
                }
            } else if (ke.getText().equals("j")) {
                ivpink.setImage(pink2.getImage());
                for (Ball ball : pinkList) {
                    System.out.println(ball.getY());
                    if (ball.getY() >= 490 && ball.getY() < 599) {
                        score += 5;
                        System.out.println("pink hit " + score);
                    }
                }
            } else if (ke.getText().equals("k")) {
                ivpurple.setImage(purple2.getImage());
                for (Ball ball : purpleList) {
                    System.out.println(ball.getY());
                    if (ball.getY() >= 490 && ball.getY() < 599) {
                        score += 5;
                        System.out.println("purple hit " + score);
                    }
                }
            }
            scoreLabel.setText(score + "");
            if (highScore < score) {
                highScore = score;
                highLabel.setText("" + highScore);
            }
            //System.out.println("Key Pressed: " + ke.getText());
        }
    }

}


