/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dancecraze;

public class Score {
    int score;
    int highScore;

    public Score(int highscore) {
        this.highScore = highscore;
        this.score = 0;
    }

    
    
    public void addScore(int score){
        this.score+=score;
    }
    
    public void setScore(int score){
        this.score= score;
    }
    
}

