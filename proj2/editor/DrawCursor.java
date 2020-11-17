package editor;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


public class DrawCursor extends  Application{
    private final Rectangle cursor;
    private static final int WINDOW_WIDTH = 500;
    private static final int WINDOW_HEIGHT = 500;

    private double cursorpositionx ;
    private double cursorpositiony ;
    private double Height ;
    private double width ;


    public DrawCursor(){

        Height = 10;
        width = 1;


        cursor = new Rectangle(0,0);

        cursor.setWidth(width);
        cursor.setHeight(Height);
        cursor.setX(0);
        cursor.setY(0);
        cursorpositionx = 0;
        cursorpositiony = 0;


    }


    private class DrawCursorHandler implements EventHandler<ActionEvent>{
        private int currentColorindex = 0;
        private Color[] boxcolor = {Color.BLACK,Color.WHITE};
        DrawCursorHandler(){
            ChangeColor();
        }
        private void ChangeColor(){
            cursor.setFill(boxcolor[currentColorindex]);
            currentColorindex = (currentColorindex+1)%2;

        }

        public void handle(ActionEvent event){
            ChangeColor();
        }
    }

    public void makecolorchange(){
        final Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        DrawCursorHandler cursorchange = new DrawCursorHandler();
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.5),cursorchange);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();

    }


    private class KeyeventHandeler implements EventHandler<KeyEvent>{



        @Override
        public void handle(KeyEvent keyEvent) {
            if(keyEvent.getEventType()==KeyEvent.KEY_PRESSED){
                KeyCode code = keyEvent.getCode();
                if(code == KeyCode.EQUALS){
                    width = width+5;
                    Height = Height + 5;
                    cursor.setHeight(Height);
                    cursor.setWidth(width);
                }
                else{if (code == KeyCode.MINUS){
                    width = Math.max(width -5,1);
                    Height = Math.max(Height - 5,5);
                    cursor.setWidth(width);
                    cursor.setHeight(Height);
                }
                    if(code == KeyCode.ENTER){
                        cursorpositiony = cursorpositiony + cursor.getLayoutBounds().getHeight();
                        cursor.setX(cursorpositionx);
                        cursor.setY(cursorpositiony);
                    }
                    if(code == KeyCode.SPACE){
                        cursorpositionx = cursorpositionx + 3;
                        cursor.setX(cursorpositionx);
                        cursor.setY(cursorpositiony);
                    }
                }
            }
        }



    }

    private class MouseEventHandler implements EventHandler<MouseEvent>{

        public void handle(MouseEvent mouseEvent){
            double mousePressedx = mouseEvent.getX();
            double mousePressedy = mouseEvent.getY();
            cursor.setX(mousePressedx);
            cursor.setY(mousePressedy);
            cursorpositionx = mousePressedx;
            cursorpositiony = mousePressedy;

        }
    }

    public Rectangle returncursor(){
        return cursor;
    }

    public void start(Stage primaryStage){
        Group root = new Group();
        Scene scene = new Scene(root,WINDOW_WIDTH,WINDOW_HEIGHT,Color.WHITE);

        root.getChildren().add(cursor);

        makecolorchange();

        EventHandler<KeyEvent> keyEventEventHandler = new KeyeventHandeler();
        scene.setOnKeyPressed(keyEventEventHandler);
        EventHandler<MouseEvent> Mouseeventhandler = new MouseEventHandler();
        scene.setOnMouseClicked(Mouseeventhandler);


        primaryStage.setTitle("SIngle cursor");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }



}
