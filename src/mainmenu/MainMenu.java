package mainmenu;

import java.net.URL;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * This class represents the pong game done in javafx. This class calls all the
 * sub-methods and initializes the game start.
 * 
 * The game has two levels- normal and hard, adding moving paddles to make the game more challenging. Two
 * users can control the player paddles using A and D and Up and Down keys respectively. The game ends after one user scores 7 points.
 *
 * @author Maksym Kolov
 * @author Neil Patel
 * @author Austin Mclemore
 * 
 */
public class MainMenu extends Application {
    
    /**
     * The pane that contains all the game elements.
     */
    
    Pane pn;
    
    /**
     * The pane that contains the main menu elements
     */
    
    GridPane welcome;
    
    /**
     * The pane to select the difficulty level.
     */
    
    GridPane diff;
    
    /**
     * A button to start a new game.
     */
    
    Button ng;
    
    /**
     * A button to exit the game.
     */
    
    Button exit;
    
    /**
     * A button for respective difficulty levels.
     */
    
    Button easy;
    
    Button hard;
    
    /**
     * Timeline to animate the main menu.
     */
    
    Timeline tl;
    
    /**
     * Keyframe to animate the main menu.
     */
   
    KeyFrame kf;
    
    /**
     * Animated circles that bounce off the edges of the screen creating the main menu
     * animation.
     */
    
    Circle ball1;
    
    Circle ball2;
    
    Circle ball3;
    
    /**
     * SoundPlayer to play the main menu music.
     */
    
    SoundPlayer snd;
    
    /**
     * The pong game class that starts the game, ball animations, and score control.
     */
    Pong3 pong;
    
    /**
     * Url path of the theme song
     */
    
    URL hit=MainMenu.class.getResource("/sound/theme.mp3");
    
    /**
     * Button to go back to the main menu.
     */
       
    Button mainm=new Button("Main Menu");
    
    /**
     * Directions of the 3 moving circles.
     */
    
    int directionX=1;
    int directionY=1;
    
    int directionX1=1;
    int directionY1=-1;
    
    int directionX2=-1;
    int directionY2=1;
    final double displacement=5.0;
    
    /**
     * Welcome message on the main menu.
     */
    
    Text wel;
    
    /**
     * Text to hint the difficulty level choice.
     */
    
    Text diffc;
    
    /**
     * Stage for the game.
     */
    Stage thestage;
    
    /**
     * Initial main menu scene
     */
    
    Scene sc;
    
    /**
     * This start method launches the pong game. Initially, the main menu is displayed
     * showing the button to start the game or exit, then the next pane stores the game
     * difficulty choice. After the choice is made, that actual game starts.
     * 
     * @param primaryStage Defines that stage for the game to launch.
     * 
     */
    
    @Override
    public void start(Stage primaryStage) {
        thestage=primaryStage;
        

        ball1 = new Circle(10);
        ball1.setFill(Color.RED);
        
        ball1.setTranslateX(10);
        
        ball1.setTranslateY(20);  
        
        ball2 = new Circle(10);
        ball2.setFill(Color.RED);
        
        ball2.setTranslateX(200);
        
        ball2.setTranslateY(300);
        
        ball3 = new Circle(10);
        ball3.setFill(Color.RED);
        
        ball3.setTranslateX(300);
        
        ball3.setTranslateY(150);
        
        kf = new KeyFrame(Duration.millis(100), e -> move());
        
        tl = new Timeline();
        
        tl.getKeyFrames().add(kf);
        tl.setCycleCount(Timeline.INDEFINITE);
        tl.setRate(2.5);
        
        pn=new Pane();
        
        pn.getStyleClass().add("pane");
        
        welcome=new GridPane();
        
        diff=new GridPane();

        diff.setVisible(false);
        
        welcome.setAlignment(Pos.CENTER);

        welcome.setVgap(20);

        welcome.setHgap(10);
        
        diff.setAlignment(Pos.CENTER);

        diff.setVgap(20);

        diff.setHgap(10);
        
        wel=new Text();
        
        wel.setText("Welcome To Pong Game");
        
        wel.setFont(Font.font("OfficinaSerifCTT", 30));
        
        wel.setFill(Color.WHITE);
        
        diffc=new Text();
        
        diffc.setText("Choose Your Difficulty");
        
        diffc.setFont(Font.font("OfficinaSerifCTT", 30));
        
        diffc.setFill(Color.WHITE);
        
        ng=new Button("New Game");
        
        ng.setMinWidth(164);
        
        ng.setId("btn");
        
        exit=new Button("Exit");
        
        exit.setMinWidth(164);
        
        exit.setId("exb");
        
        easy=new Button("Normal");
        
        easy.setMinWidth(147);
        
        easy.setId("btn");

        hard=new Button("Hard");
        
        hard.setMinWidth(147);
        
        hard.setId("btn");
        
        ng.setOnAction(e->{diffPick();});
        
        exit.setOnAction(e->{exit();});
        
        wel.setLayoutX(165);
        
        wel.setLayoutY(100);
        
        welcome.add(ng,1,1);
        
        welcome.add(exit,1,2);
        
        diffc.setLayoutX(160);
        
        diffc.setLayoutY(100);
        
        diff.add(easy,0,1,3,1); 
        
        diff.add(hard,0,2,3,1);
        
        welcome.setLayoutX(210);
        
        welcome.setLayoutY(150);
        
        diff.setLayoutX(225);
        
        diff.setLayoutY(120);
        
        diffc.setVisible(false);
        
        pong = new Pong3();
        
        pong.instButton(mainm);
        
        mainm.setOnAction(e->{backToMenu();});
     
        pn.getChildren().addAll(ball1,ball2,ball3,welcome,diff,wel,diffc);
        
        tl.play();
        
        sc=new Scene(pn,600,400);
        
        sc.getStylesheets().add("/mainmenu/style.css");
        
        snd=new SoundPlayer(hit);
        
        snd.play(12);
        
        primaryStage.setScene(sc);
                
        primaryStage.show();
        
        easy.setOnAction(e->{begin();pong.begin();});

        hard.setOnAction(e->{begin();pong.hardMode();});

    }
    
    /**
     * This method calls the main menu again when the game is done and a user would like
     * to go back and change the difficulty level or exit the game.     *      * 
     */
    
    public void backToMenu(){
        
        snd.play();
        thestage.setScene(this.sc);
        pn.getChildren().addAll(ball1,ball2,ball3,welcome,diff,wel,diffc);
        tl.play();

    }
    
    /**
     * This method controls the logic behind the movements of the circles in the main
     * menu.
     */
 
    public void move(){
        
        
            if (ball1.getTranslateX() < 5){
                    directionX = 1;
            
            }
            else if (ball1.getTranslateX() > 595){
                    directionX = -1;
            }
            if (ball1.getTranslateY() < 5){
                    directionY = 1;
            }
            else if (ball1.getTranslateY() > 395){
                    directionY=-1;
            }
            
            if (ball2.getTranslateX() < 5){
                    directionX1 = 1;
                    snd.play();
            }
            else if (ball2.getTranslateX() > 595)
                    directionX1 = -1;
            if (ball2.getTranslateY() < 5)
                    directionY1 = 1;
            else if (ball2.getTranslateY() > 395){
                    directionY1=-1;
            }
            
            if (ball3.getTranslateX() < 5)
                    directionX2 = 1;
            else if (ball3.getTranslateX() > 595)
                    directionX2 = -1;
            if (ball3.getTranslateY() < 5)
                    directionY2 = 1;
            else if (ball3.getTranslateY() > 395){
                    directionY2=-1;
            }

            ball1.setTranslateX(ball1.getTranslateX() + directionX * displacement);
            ball1.setTranslateY(ball1.getTranslateY() + directionY * displacement);
            
            ball2.setTranslateX(ball2.getTranslateX() + directionX1 * displacement);
            ball2.setTranslateY(ball2.getTranslateY() + directionY1 * displacement);
            
            ball3.setTranslateX(ball3.getTranslateX() + directionX2 * displacement);
            ball3.setTranslateY(ball3.getTranslateY() + directionY2 * displacement);
	}
    
    /**
     * This method exits the the game and closes the window when the exit button
     * is clicked.
     */
    
    public void exit(){
    
        Platform.exit();
    
    }
    
    /**
     * This method calls the difficulty level pane if a user selects to start a 
     * new game in the main menu.
     */
    
    public void diffPick(){
    
        welcome.setVisible(false);
        
        wel.setVisible(false);
        
        diff.setVisible(true);
        
        diffc.setVisible(true);

    }
    
    /**
     * This method calls the Pong class to start the game. It also stops the main menu animation and hides it from view. 
     */
    
    public void begin(){
        snd.stop();
        thestage.setScene(pong.scene);
        pn.getChildren().removeAll(ball1,ball2,ball3,welcome,diff,wel,diffc);
        tl.stop();
    }


    /**
     * Main method to launch the project.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
