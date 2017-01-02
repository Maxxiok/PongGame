package mainmenu;

import java.net.URL;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;


/**
 * This class represents the pong game logic with all the animations. After the selections
 * are made in the main menu, this class launches the actual pong game where users can
 * compete.
 *
 * @author Maksym Kolov
 * @author Neil Patel
 * @author Austin Mclemore
 * 
 */
public class Pong3 extends Pane{
    
    /**
     * Rectangles for the game elements.
     */

    Rectangle pad1, pad2, cenLine,background;
    
    /**
     * Rectangles that appear and move on the screen in hard mode only.
     */
    Rectangle[] barrier = new Rectangle[4];
    /**
     * Game ball.
     */
    Circle ball;
    /**
     * The pane for the game field.
     */
    Pane field = new Pane();
    /**
     * Boolean flags for paddles to be controlled simultaneously by both players.
     */
    boolean p1u, p1d, p2u, p2d;
    /**
     * Boolean flag for enabling hard mode element creation and animation.
     */
    boolean barFlag;
    /**
     * Boolean flag to help control ball bouncing behaviour.
     */
    boolean bounceFlag;
    /**
     * Timer for the paddle animation.
     */
    AnimationTimer timer;
    /**
     * Ball movements integers that control the bouncing.
     */
    int vx, vy, displacement;
    /**
     * Integers that control paddle movement.
     */
    int m,n,o,p;
    /**
     * Timeline objects to animate the elements.
     */
    Timeline bounce, barrier1, barrier2;
    
    /**
     * Keyframe objects to animate the elements.
     */
    KeyFrame bb, b1, b2;
    /**
     * Custom scoreboard display object.
     */
    DisplayScore score;
    /**
     * Custom score keeping object.
     */
    ScoreKeeper rep;
    
    /**
     * A pane when shows up when one player wins the game.
     */
    Pane restart=new Pane();
    
    /**
     * A button to go back to main menu.
     */
    
    Button goBack;
    
    /**
     * A button to restart the game.
     */
    
    Button rest=new Button("Restart Game");
    
    /**
     * A text stating the end of the game.
     */
    
    Text newgame=new Text("Game Over");
    
    /**
     * Scene to play the game..
     */
    
    public Scene scene;
    
    /**
     * Image to animate the ball explosion
     */
    
    Image explosion=new Image("/pics/explosion.png");
    
    /**
     * Image path to the explosion picture.
     */
    
    URL bomb=Pong3.class.getResource("/sound/bomb.mp3");
    
    /**
     * Imageview object to store the explosion picture.
     */
        
    ImageView imview=new ImageView(explosion);
    
    /**
     * Image to animate the ball explosion
     */
    
    Image explosion1=new Image("/pics/explosion1.png");
    
    /**
     * Imageview object to store the explosion picture.
     */
    
    ImageView imview1=new ImageView(explosion1);
    
    /**
     * Url to the sound effect on the ball bounce.
     */
    
    URL hit=Pong3.class.getResource("/sound/punch1.mp3");
    
     /**
     * SoundPlayer to play the sound effects in the game.
     */
    
    SoundPlayer snd1, snd2;
    
    /**
     * This method controls the pong game by setting position of all game elements
     * as well as defining the controls for it.
     * 
     */
    
    public Pong3(){
        pad1 = new Rectangle(5,50);
        pad1.setTranslateX(5);
        pad1.setFill(Color.RED);
        
        background=new Rectangle(600,400);
        
        background.setFill(Color.BLACK);
        
        pad2 = new Rectangle(5,50);
        pad2.setTranslateX(590);
        pad2.setFill(Color.BLUE);
        
        ball = new Circle(0,0,5);
        ball.setTranslateX(300);
        ball.setTranslateY(200);
        ball.setFill(Color.WHITE);
        
        cenLine = new Rectangle(295,0,10,400);
        cenLine.setFill(Color.gray(0.35, 0.7));
        
        rep = new ScoreKeeper();
        score = new DisplayScore();
       
        field.getChildren().add(background);
        field.getChildren().add(cenLine);
        field.getChildren().add(pad1);
        field.getChildren().add(pad2);
        field.getChildren().add(ball);
        field.getChildren().add(score.getDisplay(rep));
        
        restart.setVisible(false);
        
        barFlag=false;
        bounceFlag=false;
       
        bounce = new Timeline();
        barrier1 = new Timeline();
        barrier2 = new Timeline();
        vx = vy = 1;
        m=n=o=p=1;
        displacement = 5;
        bb = new KeyFrame(Duration.millis(100), e->{ballMove();});
        b1 = new KeyFrame(Duration.millis(85), e->{bar1Move();});
        b2 = new KeyFrame(Duration.millis(115), e->{bar2Move();});
        
        bounce.getKeyFrames().add(bb);
        barrier1.getKeyFrames().add(b1);
        barrier2.getKeyFrames().add(b2);
        bounce.setCycleCount(Timeline.INDEFINITE);
        barrier1.setCycleCount(Timeline.INDEFINITE);
        barrier2.setCycleCount(Timeline.INDEFINITE);
        bounce.setRate(2.5);
        barrier1.setRate(2.5);
        barrier2.setRate(2.5);
        
        
        scene = new Scene(this,600,400);
        scene.getStylesheets().add("/mainmenu/style.css");
        scene.setOnKeyPressed((KeyEvent event) -> {
            switch (event.getCode()) {
                case UP:    p2u = true; break;
                case DOWN:  p2d = true; break;
                case W:     p1u = true; break;
                case S:     p1d = true; break;
                
            }
        });

        scene.setOnKeyReleased((KeyEvent event) -> {
            switch (event.getCode()) {
                case UP:    p2u = false; break;
                case DOWN:  p2d = false; break;
                case W:     p1u = false; break;
                case S:     p1d = false; break;
            }
        });
        
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                int y1 = 0, y2 = 0;

                if (p2u)
                    y2 -= 5;
                if (p2d)  
                    y2 += 5;
                if (p1d)  
                    y1 += 5;
                if (p1u)  
                    y1 -= 5;

                movePaddlesBy(y1, y2);
            }
        };
        timer.start();
        getChildren().add(field);

    }
    
    /**
     * This method controls the step of the paddle movement
     * 
     * @param y1 An integer of the step size of the first paddle
     * @param y2 An integer of the step size of the second paddle
     * 
     */

    private void movePaddlesBy(int y1, int y2) {
        final double p1 = pad1.getBoundsInLocal().getHeight() / 2;
        final double p2 = pad2.getBoundsInLocal().getHeight() / 2;

        double move1 = p1 + pad1.getLayoutY() + y1;
        double move2 = p2 + pad2.getLayoutY() + y2;

        movePaddlesTo(move1, move2);
    }
    
    /**
     * This method controls the boundaries of the moving paddles.
     * 
     * @param m1 Movement position of the paddle one.
     * @param m2 Movement position of the paddle two.
     * 
     */

    private void movePaddlesTo(double m1, double m2) {
        final double p1 = pad1.getBoundsInLocal().getHeight() / 2;
        final double p2 = pad2.getBoundsInLocal().getHeight() / 2;

        if (m1 - p1 >= 0 && m1 + p1 <= 400){
            pad1.relocate(0, m1 - p1);
        }
        if (m2 - p2 >= 0 && m2 + p2 <= 400) {
            pad2.relocate(0, m2 - p2);
        }
    }
    
    /**
     * This method makes the barriers for the hard mode game.
     */

    private void makeBarriers() {
        for(int i=0; i<barrier.length; i++){
            barrier[i] = new Rectangle(5,30);
            barrier[i].setTranslateX((i+1)*120);
            barrier[i].setTranslateY(((i+1)*150)%400);
            
            barrier[i].setFill(Color.DARKGREEN);
            
            field.getChildren().add(barrier[i]);
        }
    }
    
    /**
     * This method controls the ball movement within the screen.
     */
    
    private void ballMove(){
        snd1=new SoundPlayer(bomb);

        if(ball.getTranslateX() < 5){
            rep.keepScore(ball.getTranslateX());
            score.getDisplay(rep);
            field.getChildren().remove(ball);
            bounce.stop();
            explAnim1 ex=new explAnim1(explosion1,imview1);
            imview1.setTranslateX(0);
            imview1.setTranslateY(ball.getTranslateY());
            ex.setCycleCount(1);
            ex.play();
            snd1.play();
            field.getChildren().add(imview1);
            ex.setOnFinished(e->{resetBoard();});
        }
            
        else if(ball.getTranslateX() > 595){
            rep.keepScore(ball.getTranslateX());
            score.getDisplay(rep);
            field.getChildren().remove(ball);
            bounce.stop();
            explAnim ex=new explAnim(explosion,imview);
            imview.setTranslateX(510);
            imview.setTranslateY(ball.getTranslateY());
            ex.setCycleCount(1);
            ex.play();
            snd1.play();
            field.getChildren().add(imview);
            ex.setOnFinished(e->{resetBoard();});
        }
        if(ball.getTranslateY()<5)
            vy=1;
        else if(ball.getTranslateY()>395){
            vy=-1;
        }
        collisionDetection();
        ball.setTranslateX(ball.getTranslateX()+vx*displacement);
        ball.setTranslateY(ball.getTranslateY()+vy*displacement);
    }
    
    /**
     * This method controls the collision logic and ball movements when it hits either
     * one of the paddles or the screen edges.
     */
    
    public void collisionDetection(){
        snd2=new SoundPlayer(hit);
        Bounds pad1Object = pad1.getBoundsInParent();
        Bounds pad2Object = pad2.getBoundsInParent();
        Bounds ballObject = ball.getBoundsInParent();
        if(pad1Object.intersects(ballObject)){
            snd2.play();
            vx=1;
            bounceFlag=true;
        }else if(pad2Object.intersects(ballObject)){
            snd2.play();
            vx=-1;
            bounceFlag=true;
        }
        else{
            bounceFlag=false;
        }
        if(barFlag)
            barrierCollision(ballObject);
        
    }
    
    /**
     * This method initializes the bounds of the hard mode barriers.
     * 
     * @param ballObject The game ball.
     */
    
    public void barrierCollision(Bounds ballObject){
        for (int i=barrier.length-1;i>=0;i--) {
            Bounds barBlock = barrier[i].getBoundsInParent();
            if(barBlock.intersects(ballObject)&&barFlag){
                snd2.play();
                vx*=-1;
            }
        }
    }

    /**
     * This method controls the movement of two of the hard mode barriers on the screen.
     * 
     */

    public void bar1Move() {
        if(barrier[0].getTranslateY() < 1)
            m = 1;
        else if(barrier[0].getTranslateY() > 370)
            m = -1;
        if(barrier[3].getTranslateY() < 1)
            n = 1;
        else if(barrier[3].getTranslateY() > 370)
            n = -1;
        barrier[0].setTranslateY(barrier[0].getTranslateY()+m*displacement);
        barrier[3].setTranslateY(barrier[3].getTranslateY()+n*displacement);
    }
    
    /**
     * This method controls the movement of the third hard mode barrier on the screen.
     * 
     */

    public void bar2Move() {
        if(barrier[1].getTranslateY() < 1)
            o = 1;
        else if(barrier[1].getTranslateY() > 370)
            o = -1;
        if(barrier[2].getTranslateY() < 1)
            p = 1;
        else if(barrier[2].getTranslateY() > 370)
            p = -1;
        barrier[1].setTranslateY(barrier[1].getTranslateY()+o*displacement);
        barrier[2].setTranslateY(barrier[2].getTranslateY()+p*displacement);
    }
    
    /**
     * This method adds the hard mode elements on the screen as well as animating them.
     * 
     */
    
    public void hardMode(){
        barFlag=true;
        makeBarriers();
        barrier1.play();
        barrier2.play();
        begin();
    }
    
    /**
     * This method starts the game and randomizes the initial ball movement.
     * 
     */
    
    public void begin(){
        do{
            vx = (int)(Math.signum(Math.random()-0.5));
            vy = (int)(Math.signum(Math.random()-0.5));
        }while(vx==0||vy==0);
        bounce.play();
    }
    
    /**
     * This method resets the game allowing to play one more point round.
     */
    
    public void resetBoard(){
        field.getChildren().removeAll(imview,imview1);
        if(rep.getP1Score()<7&&rep.getP2Score()<7){
            ball.setTranslateX(300);
            ball.setTranslateY(200);
            field.getChildren().add(ball);
            
            begin();
        }
        else{
            
            restartPane();
            
        }
    }
    
    /**
     * This method displays the pane after the game is over allowing players
     * to either play again or go back to the main menu.
     * 
     */
    
    public void restartPane(){
        
        restart.setVisible(true);
        
        goBack.setId("btn");
        
        rest.setId("btn");
        
        newgame.setFont(Font.font("OfficinaSerifCTT", 30));
        
        newgame.setId("txt");
        
        goBack.setLayoutX(200);
        
        goBack.setLayoutY(250);
        
        rest.setOnAction(e->{
            restart.setVisible(false);
            rep.refreshScore();
            
            if(barFlag){
                
               for(int i=0;i<barrier.length;i++)
                   
                    field.getChildren().remove(barrier[i]);
               
                    ball.setTranslateX(300);
                    ball.setTranslateY(200);
            
               hardMode();
                
            }
            else{
            
                begin();
            
            }
        });
        
        rest.setLayoutX(310);
        
        rest.setLayoutY(250);
        
        newgame.setLayoutX(240);
        
        newgame.setLayoutY(200);
        
        newgame.setFill(Color.WHITE);
        
        restart.getChildren().addAll(newgame,goBack,rest);
        
        field.getChildren().addAll(restart);    
    }
    
    /**
     * This method instantiates the button to go back to the main menu.
     * 
     *@param btn The main menu button from the MainMenu class. 
     */
    
    public void instButton(Button btn){
    
        goBack=btn;
    
    }
}
