/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainmenu;

import java.net.URL;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * This class represents the sound player which plays the collision sounds as well as
 * various music in the pong game.
 *
 * @author Maksym Kolov
 */
public class SoundPlayer{
    
    /**
     * A path to the selected sound to play
     */
    
    URL soundplay;
    
    /**
     * A path media to be added to the player.
     */
    
    Media stopl;
    
    /**
     * A player to play the sound.
     */
    
    MediaPlayer player;
    
    /**
     * A constructor to create a music player based on the passed parameter.
     * 
     * @param soundpath path to the file to play.
     */
    
    
    public SoundPlayer(URL soundpath){
    
        soundplay=soundpath;
        
        stopl= new Media(soundplay.toString());
        
        player=new MediaPlayer(stopl);
    
    }
    
    /**
     * Play the selected file.
     */
    
    
    public void play(){
    
        player.play();

    
    }
    
    /**
     * Play the selected file a number of specified times.
     * 
     * @param i number of times to play the sound.
     */
    
    
    public void play(int i){
        
        player.setCycleCount(i);
    
        player.play();

    
    }
    
    /**
     * Stop the player.
     */
    
    public void stop(){
    
        player.stop();

    
    }
    
}
