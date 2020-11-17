import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.event.EventType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;



public class testfastlinkedlist extends Application{

    private Rectangle newRectangle = new Rectangle(0,0);

    private class MouseEventHandler implements  EventHandler<MouseEvent>{
        double lastPositionx;
        double lastPositiony;
        private Group root;
        private FastLinkedListse string;
        MouseEventHandler(Group root,FastLinkedListse fastlist){
            this. root = root;
            string = fastlist;
        }




        public void handle(MouseEvent mouseEvent){
            double mousePressedx = mouseEvent.getX();
            double mousePressedy = mouseEvent.getY();

            EventType eventType = mouseEvent.getEventType();
            if(eventType == MouseEvent.MOUSE_PRESSED){
                lastPositionx = mousePressedx;
                lastPositiony = mousePressedy;
            }
            else if(eventType == MouseEvent.MOUSE_DRAGGED){
                string.resetrectangle(lastPositionx,lastPositiony,mousePressedx,mousePressedy,0);
                string.display();
                System.out.println(string.returndynamicstring());





            }
        }
    }


    public void start(Stage Primarystage){
        DrawCursor subdrawcursor = new DrawCursor();
        Rectangle cursor = subdrawcursor.returncursor();



        Group root = new Group();
        root.getChildren().add(cursor);
        root.getChildren().add(newRectangle);
        FastLinkedListse string = new FastLinkedListse(500,root);
/*
        String inputString = "abcde";
        string.addString(5,inputString,root,cursor);

 */





/*
        Rectangle newRectangle = new Rectangle(0,0);
        newRectangle.setHeight(15);
        newRectangle.setWidth(100);
        newRectangle.setFill(Color.LIGHTBLUE);
        root.getChildren().add(newRectangle);
        string.display();

*/


        Scene scene = new Scene(root,500,500,Color.WHITE);

        MouseEventHandler mouseEventHandler = new MouseEventHandler(root,string);
        scene.setOnMousePressed(mouseEventHandler);
        scene.setOnMouseDragged(mouseEventHandler);


        string.addChar('v',cursor);
        string.addChar('j',cursor);
        string.addChar('k',cursor);
        string.addChar('\n',cursor);
        for(int i=0;i<=4;i++){
            string.addChar('a',cursor);
        }






        String inputtString = "a\ncde";
        string.addString(2,inputtString,cursor);
        System.out.println(string.returnrectangle().size());




        string.display();



        Primarystage.setScene(scene);
        Primarystage.show();
    }
    public static void main(String[] args){
        launch(args);
    }

}
