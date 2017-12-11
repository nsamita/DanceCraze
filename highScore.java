/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dancecraze;

import java.util.ArrayList;

import java.io.*;
import java.util.Scanner;

public class highScore {

    ArrayList<Integer> highScoreList = new ArrayList<Integer>();

    public highScore() throws IOException {

        File file = new File("highScore.txt");

        try {
            Scanner sc = new Scanner(file);

            while (sc.hasNextInt()) {
                int i = sc.nextInt();
                highScoreList.add(i);
            }

            sc.close();

//            for (int i = 0; i < highScoreList.size(); i++) {
//                System.out.println(highScoreList.get(i));
//            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void rewrite(int score, String songname, String mode) throws IOException {
        File file = new File("highScore.txt");

        try {

            Writer wr = new FileWriter("highScore.txt");
            if (songname == "Closer") {
                if (mode == "easy") {
                    if (score > highScoreList.get(1)) {
                        highScoreList.set(1, score);
                    }
                } else if (mode == "medium") {
                    if (score > highScoreList.get(2)) {
                        highScoreList.set(2, score);
                    }
                } else if (mode == "hard") {
                    if (score > highScoreList.get(3)) {
                        highScoreList.set(3, score);
                    }
                }
            } else if (songname == "Roses") {
                if (mode == "easy") {
                    if (score > highScoreList.get(4)) {
                        highScoreList.set(4, score);
                    }
                } else if (mode == "medium") {
                    if (score > highScoreList.get(5)) {
                        highScoreList.set(5, score);
                    }
                } else if (mode == "hard") {
                    if (score > highScoreList.get(6)) {
                        highScoreList.set(6, score);
                    }
                }
            } else if (songname == "Summer") {
                if (mode == "easy") {
                    if (score > highScoreList.get(7)) {
                        highScoreList.set(7, score);
                    }
                } else if (mode == "medium") {
                    if (score > highScoreList.get(8)) {
                        highScoreList.set(8, score);
                    }
                } else if (mode == "hard") {
                    if (score > highScoreList.get(9)) {
                        highScoreList.set(9, score);
                    }
                }
            } else if (songname == "This is") {
                if (mode == "easy") {
                    if (score > highScoreList.get(10)) {
                        highScoreList.set(10, score);
                    }
                } else if (mode == "medium") {
                    if (score > highScoreList.get(11)) {
                        highScoreList.set(11, score);
                    }
                } else if (mode == "hard") {
                    if (score > highScoreList.get(12)) {
                        highScoreList.set(12, score);
                    }
                }
            }

            for (int i = 0; i < highScoreList.size(); i++) {
                wr.write(highScoreList.get(i) + " ");
            }

            wr.flush();
            wr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int getCloser1() {
        return highScoreList.get(1);
    }

    public int getCloser2() {
        return highScoreList.get(2);
    }

    public int getCloser3() {
        return highScoreList.get(3);
    }

    public int getRoses1() {
        return highScoreList.get(4);
    }

    public int getRoses2() {
        return highScoreList.get(5);
    }

    public int getRoses3() {
        return highScoreList.get(6);
    }

    public int getSummer1() {
        return highScoreList.get(7);
    }

    public int getSummer2() {
        return highScoreList.get(8);
    }

    public int getSummer3() {
        return highScoreList.get(9);
    }

    public int getThisIs1() {
        return highScoreList.get(10);
    }

    public int getThisIs2() {
        return highScoreList.get(11);
    }

    public int getThisIs3() {
        return highScoreList.get(12);
    }
}

