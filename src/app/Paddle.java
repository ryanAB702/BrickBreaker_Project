package app;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class Paddle {
    public static final String PADDLE_IMAGE = "paddle.png";
    public static final long PADDLE_WIDTH = 80;
    public static final long PADDLE_HEIGHT = 10;
    public static final long PADDLE_LONG_WIDTH = 150;


    private ImageView myPaddle;
    private int myLives;
    private double paddle_speed = 10;
    private double paddleWidth = 80;
    //private boolean speedUp = false;
    private boolean lengthUp = false;

    private double paddle_velocity = 0;
    private Set<KeyCode> currentlyPressed = new HashSet<>();

    private CheatKeys ch = new CheatKeys();

    double screenWidth, screenHeight;

    public Paddle(double screenWidth, double screenHeight){
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        Breakout b = new Breakout();
        Image image = new Image(this.getClass().getClassLoader().getResourceAsStream(PADDLE_IMAGE));
        myPaddle = new ImageView(image);
        myLives = 4;
        myPaddle.setX(screenWidth/2);
        myPaddle.setY(screenHeight-10);
        myPaddle.setFitWidth(paddleWidth);
        myPaddle.setFitHeight(PADDLE_HEIGHT);
        //setPaddle(paddle_speed, paddleWidth);
    }

    /**
     * Called when ball hits bottom of screen (lose life) or when paddle catches extra life powerUp
     * Returns 0 when lose game (lives = 0)
     * @param i
     * @return
     */
    public int updateLives(int i){
        myLives += i;
        if(myLives < 1){
            return 0;
        }
        if(i<0){
            //paddle_speed = 10;
            //paddleWidth = 80;
            //myPaddle.setFitWidth(80);
            setPaddle(10, 80);
        }
        return 1;
    }

    /**
     * Getter returns ImageView of paddle
     * @return
     */
    public ImageView getPaddle() { return myPaddle; }

    /**
     * Used to vary the bounce of ball upon paddle collision depending on location that the ball hits the paddle
     * @param idx
     * @return
     */
    public ImageView getPaddlePart(int idx) {
        var dummy = new ImageView();
        dummy.setX(myPaddle.getX()+idx*myPaddle.getFitWidth()/3);
        dummy.setFitWidth(myPaddle.getFitWidth()/3);
        dummy.setY(myPaddle.getY());
        dummy.setFitHeight(myPaddle.getFitHeight());
        return dummy;
    }

    /**
     * Getter returns number of lives user (paddle) has left
     * @return
     */
    public int getLives(){
        return myLives;
    }

    /**
     * Set velocity of paddle
     * @param v
     */
    private void setVX(double v) { paddle_velocity = v; }

    public void move(double screenWidth) {
        myPaddle.setX(Math.max(Math.min(myPaddle.getX() + paddle_velocity, screenWidth-myPaddle.getFitWidth()), 0));
    }

    public void handleKeyReleased(KeyCode code) {
        currentlyPressed.remove(code);
        if(currentlyPressed.size() == 0) {
            setVX(0);
        } else {
            if(currentlyPressed.iterator().next() == KeyCode.LEFT) setVX(-paddle_speed);
            else setVX(paddle_speed);
        }
    }

    /**
     * handles key inputs to move paddle and handle cheatKeys
     * @param code
     * @param ball
     */
    public void handleKeyPressed(KeyCode code, Ball ball){
        if(code == KeyCode.RIGHT){
            setVX(paddle_speed);
            currentlyPressed.add(code);
        }
        else if(code == KeyCode.LEFT){
            setVX(-paddle_speed);
            currentlyPressed.add(code);
        }
        else if(code == KeyCode.L){
            myLives++;
        }
        else if(code == KeyCode.R){
            myPaddle.setX(screenWidth/2);
            myPaddle.setY(screenHeight-10);
            ball.resetBall(screenWidth, screenHeight);
        }
        else if(code == KeyCode.S){
            stretch();
        }
        else if(code == KeyCode.F){
            speedUp();
        }
        else if(code == KeyCode.B){
            ball.pumpPower();
        }
        else if(code == KeyCode.COMMA){

        }
        else if(code == KeyCode.PERIOD){

        }
        else if(code == KeyCode.SLASH){

        }
    }

    /**
     * Handles "gatorade" powerUp -> increases paddle speed by 1.5 for 5 seconds
     */
    public void speedUp(){
        //paddle_speed = 15;
        setPaddle(15, paddleWidth);
        timeOut("speed");
    }

    /**
     * Handles "stretcher" powerUp -> increases paddle size to 150px for 5 seconds
     */
    public void stretch(){
        //myPaddle.setFitWidth(150);
        //paddleWidth = 150;
        setPaddle(paddle_speed, 150);
        timeOut("stretch");
    }

    /**
     * Called by speedUp() and stretch() to reset paddle after 5 second delay
     * @param powType
     */
    //https://stackoverflow.com/questions/35512648/adding-a-timer-to-my-program-javafx
    public void timeOut(String powType){
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if(powType.equals("stretch")){
                    //myPaddle.setFitWidth(80);
                    //paddleWidth = 80;
                    setPaddle(paddle_speed, 80);
                }
                else{
                    //paddle_speed = 10;
                    setPaddle(10, paddleWidth);
                }
            }
        };
        timer.schedule(task, 5000l);
    }

    /**
     * Helper method sets paddle speed and width based on given parameters
     * Called in constructor, speedUp, stretch, timeOut
     * @param speed
     * @param width
     */
    public void setPaddle(double speed, double width){
        myPaddle.setFitWidth(width);
        paddle_speed = speed;
        paddleWidth = width;
        myPaddle.setFitHeight(PADDLE_HEIGHT);
    }
}
