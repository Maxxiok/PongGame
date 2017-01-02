
package mainmenu;

/**
 * This class keeps the players' scores of the pong game.
 * 
 * @author Austin Mclemore
 * 
 */

public class ScoreKeeper {
    /**
     *Score of the player 1
     */
    
    protected int player1Score;
    
    /**
     *Score of the player 2
     */
    protected int player2Score;
    
    /**
     *Constructor to initialize the scores.
     */
    public ScoreKeeper() {
        player1Score = 0;
        player2Score = 0;
    }
    /**
     *This method updates the score when the ball leaves the field.
     * 
     * @param x Position of the ball
     */
    public void keepScore(double x) {
        if (5>=x) {
            player2Score++;
        }
        if (x>=595) {
            player1Score++;
        }
    }
    
    /**
     * This method gets the player 1 score.
     */
    public int getP1Score() {
        return player1Score;
    }
    
    /**
     * This method gets the player 2 score.
     */
    public int getP2Score() {
        return player2Score;
    }
    
    /**
     * This method resets the score for both players to start a new game.
     */
    public void refreshScore() {
        player1Score = 0;
        player2Score = 0;
    }
}

