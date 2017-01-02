package mainmenu;

/**
 * This class displays the score on the pong game board.
 * 
 * @author Austin Mclemore
 * 
 */

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * Constructor that controls the score positioning and display of the score elements. 
 */

public class DisplayScore {
    Image score = new Image("/pics/score.png");
    ImageView p1 = new ImageView(score);
    ImageView p2 = new ImageView(score);
    Pane pane = new Pane();
    Rectangle2D scoreVP0 = new Rectangle2D(24,84,100,200);
    Rectangle2D scoreVP1 = new Rectangle2D(134,84,100,200);
    Rectangle2D scoreVP2 = new Rectangle2D(244,84,100,200);
    Rectangle2D scoreVP3 = new Rectangle2D(354,84,100,200);
    Rectangle2D scoreVP4 = new Rectangle2D(464,84,100,200);
    Rectangle2D scoreVP5 = new Rectangle2D(574,84,100,200);
    Rectangle2D scoreVP6 = new Rectangle2D(684,84,100,200);
    Rectangle2D scoreVP7 = new Rectangle2D(794,84,100,200);
    private boolean scoreChanged = false;
    public DisplayScore() {
        p1.setViewport(scoreVP0);
        p2.setViewport(scoreVP0);
        p1.setLayoutX(200);
        p2.setLayoutX(350);
    }
    
    /**
    * This method changes the score picture based on the scored points.
    */
    
    private void changeScore(ScoreKeeper sk) {
        scoreChanged = true;
        switch (sk.getP1Score()) {
            case 1: p1.setViewport(scoreVP1);
            break;
            case 2: p1.setViewport(scoreVP2);
            break;
            case 3: p1.setViewport(scoreVP3);
            break;
            case 4: p1.setViewport(scoreVP4);
            break;
            case 5: p1.setViewport(scoreVP5);
            break;
            case 6: p1.setViewport(scoreVP6);
            break;
            case 7: p1.setViewport(scoreVP7);
            break;
            default: p1.setViewport(scoreVP0);
            break;
        }
        switch (sk.getP2Score()) {
            case 1: p2.setViewport(scoreVP1);
            break;
            case 2: p2.setViewport(scoreVP2);
            break;
            case 3: p2.setViewport(scoreVP3);
            break;
            case 4: p2.setViewport(scoreVP4);
            break;
            case 5: p2.setViewport(scoreVP5);
            break;
            case 6: p2.setViewport(scoreVP6);
            break;
            case 7: p2.setViewport(scoreVP7);
            break;
            default: p2.setViewport(scoreVP0);
            break;
        }
        
    }
    /**
    * This method changes to score picture and adds it to the pane.
    * 
    * @param sk ScoreKeeper to store players' scores.
    * 
    * @return The pane to refresh the score.
    */
    public Pane getDisplay(ScoreKeeper sk) {
        pane.getChildren().removeAll(p1,p2);
        changeScore(sk);
        pane.getChildren().addAll(p1,p2);
        return pane;
    }
    
    /**
    * This method returns the flag if the score changed.
    * 
    * @return True or false when score changes.
    */
    public boolean getChange() {
        return scoreChanged;
    }
    
    /**
    * This method controls the setting of the flag when score changes.
    * 
    * @param change flag to notify the score change.
    */
    public void setChange(boolean change) {
        scoreChanged = change;
    }
}

