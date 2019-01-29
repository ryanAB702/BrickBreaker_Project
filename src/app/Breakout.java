package app;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.shape.Circle;


/**
 * A basic example JavaFX program for the first lab.
 *
 * Cool! No WAY!! YETALLY!
 *
 * Branched dude!
 *
 * Right on
 *
 * * @author Robert C. Duvall
 */
public class Breakout extends Application {

    public static final String TITLE = "Example JavaFX";
    public static final int SIZE = 400;
    public static final int FRAMES_PER_SECOND = 60;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final Paint BACKGROUND = Color.LIGHTGREEN;
    //public static final Paint HIGHLIGHT = Color.OLIVEDRAB;
    //public static final Paint BOUNCER_COLOR = Color.BROWN;
    //public static final int BOUNCER_SPEED = 60;
    //public static final Paint PADDLE_COLOR = Color.GRAY;
    //public static final int PADDLE_SPEED = 30;





    // some things we need to remember during our game
    private Scene myScene;
    private Ball myBall; //= new app.Ball(myScene.getWidth()/2, myScene.getHeight()/2);
    private Paddle myPaddle;
    //private Circle myBouncer;
    //private int velocityX = 1;
    //private int velocityY = 1;

    /**
     * Initialize what will be displayed and how it will be updated.
     *
     * Wahtever
     */
    @Override
    public void start (Stage stage) {
        // attach scene to the stage and display it
        myScene = setupGame(SIZE, SIZE, BACKGROUND);
        //myPaddle = new Paddle();
        stage.setScene(myScene);
        stage.setTitle(TITLE);
        stage.show();
        // attach "game loop" to timeline to play it
        var frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
        var animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }

    // Create the game's "scene": what shapes will be in the game and their starting properties
    private Scene setupGame (int width, int height, Paint background) {
        // create one top level collection to organize the things in the scene
        var root = new Group();
        // create a place to see the shapes
        var scene = new Scene(root, width, height, background);
        myBall = new Ball(scene.getWidth()/2, scene.getHeight()/2);
        myPaddle = new Paddle(scene);
        //myPaddle = new Paddle();


        // order added to the group is the order in which they are drawn
        root.getChildren().add(myBall.getBall());
        root.getChildren().add(myPaddle.getPaddle());
        // respond to input
        scene.setOnKeyPressed(e -> myPaddle.handleKeyInput(e.getCode()));
        return scene;
    }

    // Change properties of shapes to animate them
    // Note, there are more sophisticated ways to animate shapes, but these simple ways work fine to start.
    private void step (double elapsedTime) {
        // update attributes
        myBall.move(elapsedTime);

        if(getBottom(myBall.getBall()) >= myScene.getHeight()){
            myPaddle.updateLives(-1);
            if(myPaddle.getLives() == 0){
                //END GAME HERE
            }
        }

        // check for collisions
        //check if paddle and mover intersects
        if(detCollision(myBall.getBall(), myPaddle.getPaddle())){
            myBall.updateVelo(1, -1);
        }

        //change direction in x-axis when hits a wall
        myBall.wallBounce();
    }

    public Scene getScene(){
        return myScene;
    }

    public boolean detCollision(ImageView arg1, ImageView arg2){
        double left1 = arg1.getX();
        double right1 = getRight(arg1);
        double top1 = arg1.getY();
        double bottom1 = getBottom(arg1);
        double left2 = arg2.getX();
        double right2 = getRight(arg2);
        double top2 = arg2.getY();
        double bottom2 = getBottom(arg2);
        if((left1 <= right2 && left1 >= left2) || (right1 >= left2 && right1 <= right2)){
            return verticalOverlap(top1, bottom1, top2, bottom2);
        }
        return false;
    }

    public boolean verticalOverlap(double top1, double bottom1, double top2, double bottom2){
        return((top1 <= bottom2 && top1>=top2) || (bottom1 >= top2 && bottom1<=bottom2));
    }
    public double getRight(ImageView arg){
        return arg.getX() + arg.getBoundsInLocal().getWidth();
    }
    public double getBottom(ImageView arg){
        return arg.getY() + arg.getBoundsInLocal().getHeight();
    }

    /*
    // What to do each time a key is pressed
    private void handleMouseInput (double x, double y) {
//        if (myGrower.contains(x, y)) {
//            myGrower.setScaleX(myGrower.getScaleX() * GROWER_RATE);
//            myGrower.setScaleY(myGrower.getScaleY() * GROWER_RATE);
//        }
    }
    */

    /**
     * Start the program.
     */
    public static void main (String[] args) {
        launch(args);
    }
}
