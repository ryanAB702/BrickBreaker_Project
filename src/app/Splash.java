package app;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.security.Key;

public class Splash{

    public final static String HOW_TO = "Move the paddle by pressing the left and right arrows on your keyboard. \n" +
            "Break all the opponent blocks before losing all your lives!";
    public static final int FRAMES_PER_SECOND = 60;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;


    private Text title;
    private Text instruction;
    private Text gameStart;
    private Paint BACKGROUND = Color.WHITE;
    private Button startButton;
    private Scene myScene;
    private boolean splash = true;


//    public Scene Splash(){
//
//        myView = new Scene(Breakout.root, Breakout.SIZE_WIDTH, Breakout.BLOCK_HEIGHT, background);
//        title = new Text(Breakout.SIZE_WIDTH/2, 100, "Break-Out\n");
//        title.setFont(Font.font("Helvetica", FontWeight.BOLD, 30));
//
//        instruction = new Text(Breakout.SIZE_WIDTH/2, 200, "Move the paddle side-ways to get\n most point with out dropping the ball\n");
//        instruction.setFont(Font.font("Helvetica", FontWeight.BOLD, 30));
//
//        gameStart = new Text(Breakout.SIZE_WIDTH/2,300, "Press <Space> or Click Start button to start the game!\n");
//        gameStart.setFont(Font.font("Helvetica", FontWeight.BOLD,30));
//
//        tips = new Text(Breakout.SIZE_WIDTH/2, 400,"(Breaking non-target blocks will deduct your points)");
//        tips.setFont(Font.font("Helvetica", FontWeight.BOLD,15));
//
//        Breakout.root.getChildren().addAll(title,tips,instruction,gameStart);
//        myView.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
//        return myView;
//    }

//    @Override
//    public void start (Stage stage){
//        myScene = setupSplash(SIZE,SIZE,BACKGROUND);
//        stage.setScene(myScene);
//        stage.setTitle(TITLE);
//        stage.show();
//
//        var frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
//        var animation = new Timeline();
//        animation.setCycleCount(Timeline.INDEFINITE);
//        animation.getKeyFrames().add(frame);
//        animation.play();
//    }


    public Scene setupSplash(int width, int height, Paint background){
        var root = new Group();
        Image bkg = new Image("Half.png");
        ImageView mv = new ImageView(bkg);
        Image nfl = new Image("nfl.png");
        ImageView mvNfl = new ImageView(nfl);

        var scene = new Scene(root, bkg.getWidth(), bkg.getHeight() - 100, background);

        //title
        title = new Text(scene.getWidth()* 0.27, scene.getHeight() * 0.49, "NFL Break-Out!\n");
        title.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 45));
        title.setFill(Color.BLUE);

        //nfl logo
        mvNfl.setFitWidth(100);
        mvNfl.setFitHeight(100);
        mvNfl.setX(300);
        mvNfl.setY(150);



        //instruction
        instruction = new Text(scene.getWidth() * 0.2, scene.getHeight() *0.57, HOW_TO);
        //instruction = new Text(HOW_TO);
        //instruction.setTextAlignment(TextAlignment.CENTER);
        instruction.setFont(Font.font("Helvetica", FontWeight.BOLD, 15));
        instruction.setFill(Color.BLUE);


        //start text
        gameStart = new Text(scene.getWidth()*0.21, scene.getHeight()*0.69, "Press space or click the Button to start playing!");
        gameStart.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 20));
        gameStart.setFill(Color.BLUE);

        //start button
        startButton = new Button("Start!");
        startButton.setLayoutX(300);
        startButton.setLayoutY(scene.getHeight()-100);
        startButton.setOnAction(e -> splash = false);

        root.getChildren().add(mv);
        root.getChildren().add(title);
        root.getChildren().add(mvNfl);
        root.getChildren().add(startButton);
        root.getChildren().add(gameStart);
        root.getChildren().add(instruction);

        scene.setOnMouseClicked(e -> handleMouseInput(e.getX(),e.getY()));

        return scene;
    }

    public void handleMouseInput(double x, double y){
        this.setSplash(false);
    }

    public boolean getSplash(){
        return splash;
    }

    public void setSplash(boolean bool){
        this.splash = bool;
    }


//    public static void main (String[] args){
//        launch(args);
//
//    }






}
