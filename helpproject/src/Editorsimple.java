
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Orientation;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.control.ScrollBar;




public class Editorsimple extends Application{
    private static final int WINDOW_WIDTH = 500;
    private static final int WINDOW_HEIGHT = 500;
    private FastLinkedListse string;

    private DrawCursor drawCursor;
    private Rectangle cursor;

    private String filenames;
    private double movelength;
    private int status = 0;
    private int startposition = 0;
    private int endposition = 0;
    private boolean presscommand = false;


    public void setdoublelength(double movelength){
        this.movelength = movelength;

    }

    public void addChargeneral(char x){
        string.addChar(x,cursor);


        string.addStringoutsied(x);
        endposition = endposition+1;

    /*
        int returnsize = string.returnpreservesize();
        if(returnsize>100){
            string.removeintegerstartbegin();
            string.removeintegerendbegin();
            string.removeStringlistbegin();
            string.removestatusbegin();
        }

         */
        /*
        string.addintegerstart(startposition);
        string.addintegerend(endposition);
        string.addStringlist(preservestring);
        string.addstatus(0);

         */
        string.display();


    }

    public void deleteGeneral(char x){
        System.out.println("go to deleteGeneral here");

        string.deleteChar(cursor);

        string.addStringoutsied(x);

        startposition = startposition -1;
        string.display();




    }

    public void upsubdeal(){

    }

    public void resetstateselection(){
        string.returndynamicstringbuilder();

        string.addselfstart();
        string.addselfend();
        string.addselfstring();
        string.resetdynamicstring();
        string.addstatus(1);
        string.resetrectanglewhite();
        string.testdeleteselection(cursor);
    }


    public void resetstateadd(){
        string.convertdynamicstringbuilder();

        string.addintegerstart(startposition);
        string.addintegerend(endposition);

        string.addselfstring();
        string.addstatus(3);
        string.resetdynamicstring();
    }

    public void resetstatedelete(){
        string.convertdynamicstringbuilderreverse();
        string.addselfstring();
        string.addintegerend(endposition);
        string.addintegerstart(startposition);
        System.out.println(startposition);
        System.out.println(endposition);
        string.addstatus(2);
        string.resetdynamicstring();
    }
/*
    public void changetozero(){
        if(status==3){

        }
    }

 */






    private class KeyEventHandler implements EventHandler<KeyEvent> {
        int textCenterX;
        int textCenterY;

        private static final int STARTING_FONT_SIZE = 20;
        private static final double STARTING_TEXT_POSITION_X = 10;
        private static final double STARTING_TEXT_POSITION_Y = 10;
        private double curentpositionx= STARTING_TEXT_POSITION_X;
        private double currentpositiony = STARTING_TEXT_POSITION_Y;
        private double margin = 1;



        private Group root;

/**

         The Text to display on the screen.
 **/

        private int fontSize = STARTING_FONT_SIZE;



        private String fontName = "Verdana";








        KeyEventHandler(final Group root, int windowWidth, int windowHeight,String filename) {
            textCenterX = windowWidth / 2;
            textCenterY = windowHeight / 2;


            // Initialize some empty text and add it to root so that it will be displayed.

            // Always set the text origin to be VPos.TOP! Setting the origin to be VPos.TOP means
            // that when the text is assigned a y-position, that position corresponds to the
            // highest position across all letters (for example, the top of a letter like "I", as
            // opposed to the top of a letter like "e"), which makes calculating positions much
            // simpler!




            string = new FastLinkedListse(windowWidth-10,root);
            this.root = root;

            drawCursor = new DrawCursor();
            cursor = drawCursor.returncursor();
            filenames=filename;
            movelength = 0;



            // All new Nodes need to be added to the root in order to be displayed.



        }

        @Override
        public void handle(KeyEvent keyEvent) {



            if (keyEvent.getEventType() == KeyEvent.KEY_TYPED) {
                if(!presscommand){
                    string.clearredo();
                }
                else{
                    presscommand = false;
                    return;
                }


                // Use the KEY_TYPED event rather than KEY_PRESSED for letter keys, because with
                // the KEY_TYPED event, javafx handles the "Shift" key and associated
                // capitalization.
                String characterTyped = keyEvent.getCharacter();





                if (characterTyped.length() > 0 && characterTyped.charAt(0) != 8 &&characterTyped.charAt(0)!=13) {
                    /**if(status = 0){
                     *  string.addChar(characterTyped.charAt(0),cursor);
                     *
                     *                     System.out.println(characterTyped.charAt(0));
                     *                     string.display();
                     *                     String preservestring= Character.toString(x);
                     *                     int startposition = string.returnposition()-1;
                     *                     int endposition = string.returnposition();
                     *                     int returnsize = string.returnstringsize();
                     *                     if(returnsize>=100){
                     *                     string.remove..();
                     *                     string.remove..();
                     *                     string.remove..();
                     *                     }
                     *                     string.add..(startposition);
                     *                     string.add..(endposition);
                     *                     string.add..(preservestring);
                     *
                     *
                     * }
                     * if(status = 1){
                     * String stringreturn = string.returndynamicString();
                     * string.add..();
                     * string.add..();
                     * string.add..();
                     * string.deleteString();
                     * string.addChar(characterTyped.charAt(0),cursor);
                     *                      *
                     *                      *                     System.out.println(characterTyped.charAt(0));
                     *                      *                     string.display();
                     * String preservestring= Character.toString(x);
                     *                      *                     int startposition = string.returnposition()-1;
                     *                      *                     int endposition = string.returnposition();
                     *                      *                     int returnsize = string.returnstringsize();
                     *                      *                     if(returnsize>=100){
                     *                      *                     string.remove..();
                     *                      *                     string.remove..();
                     *                      *                     string.remove..();
                     *                      *                     }
                     *                      *                     string.add..(startposition);
                     *                      *                     string.add..(endposition);
                     *                      *                     string.add..(preservestring);
                     *                      *string.display();
                     *
                     *
                     *
                     * }
                     *
                     *
                     */
                    // Ignore control keys, which have non-zero length, as well as the backspace
                    // key, which is represented as a character of value = 8 on Windows.
                    if(status==0){
                        startposition = string.returnpostion();
                        endposition = startposition;
                        addChargeneral(characterTyped.charAt(0));
                        status = 3;
                        return;
                    }
                    if(status==3){
                        addChargeneral(characterTyped.charAt(0));
                        return;
                    }
                    if(status==1){
                        /**
                         * preserve,delete,add
                         *
                         string.returndynamicstringbuilder();
                         System.out.println(string.returndynamicstringtest());


                        string.addselfstart();
                        string.addselfend();
                        string.addselfstring();
                        string.resetdynamicstring();
                        string.resetrectanglewhite();
                        string.testdeleteselection(cursor);

                        string.addstatus(status);

                         **/
                        root.getChildren().add(cursor);
                        resetstateselection();
                        startposition = string.returnpostion();

                        endposition = startposition;

                        addChargeneral(characterTyped.charAt(0));
                        status = 3;
                        return;


                    }
                    if(status==2){
                        /**
                        string.convertdynamicstringbuilderreverse();
                        string.addselfstring();
                        string.addintegerstart(startposition);
                        string.addintegerend(endposition);
                        string.addstatus(status);
                        string.resetdynamicstring();
                         **/
                        resetstatedelete();
                        startposition = string.returnpostion();

                        endposition = startposition;
                        addChargeneral(characterTyped.charAt(0));
                        status = 3;
                        return;

                    }



                }



            } else if (keyEvent.getEventType() == KeyEvent.KEY_PRESSED) {
                // Arrow keys should be processed using the KEY_PRESSED event, because KEY_PRESSED
                // events have a code that we can check (KEY_TYPED events don't have an associated
                // KeyCode).
                KeyCode code = keyEvent.getCode();
                        if(code == KeyCode.UP){
                            if(status == 1){
                                root.getChildren().add(cursor);
                                int privatestart = string.returnstartposition();
                                string.resetcurrentNodeno(privatestart);
                                string.resetrectanglewhite();
                                status=0;
                                string.setEndpositionout();
                                string.setstartpositionout();
                            }
                            if(status ==2){
                                resetstatedelete();
                                status = 0;
                                string.setEndpositionout();
                                string.setstartpositionout();
                            }
                            if(status == 3){
                                resetstateadd();
                                status = 0;
                                string.setEndpositionout();
                                string.setstartpositionout();

                            }
                            string.moveupsecond(cursor,0);
                        }
                        if(code == KeyCode.DOWN){
                            if(status == 1){
                                /*
                                int privateend = string.returnendposition();
                                string.resetcurrentNodeno(privateend);

                                 */
                                root.getChildren().add(cursor);
                                string.resetrectanglewhite();
                                status=0;
                                string.setEndpositionout();
                                string.setstartpositionout();
                            }
                            if(status ==2){

                                resetstatedelete();
                                status = 0;
                                string.setEndpositionout();
                                string.setstartpositionout();
                            }
                            if(status == 3){
                                resetstateadd();
                                status = 0;
                                string.setEndpositionout();
                                string.setstartpositionout();
                            }

                            string.movedown(cursor);
                        }
                        if(code == KeyCode.LEFT){
                            if(status == 1){
                                root.getChildren().add(cursor);
                                int privatestart = string.returnstartposition();
                                string.resetcurrentNodeno(privatestart);
                                string.resetrectanglewhite();
                                status=0;
                                string.setEndpositionout();
                                string.setstartpositionout();
                            }
                            if(status ==2){
                                resetstatedelete();
                                status = 0;
                                string.setEndpositionout();
                                string.setstartpositionout();
                            }
                            if(status == 3){
                                resetstateadd();
                                status = 0;
                                string.setEndpositionout();
                                string.setstartpositionout();
                            }


                            string.moveleft(cursor);
                        }
                        if(code == KeyCode.RIGHT){
                            if(status == 1){
                                root.getChildren().add(cursor);
                                string.resetrectanglewhite();
                                status=0;
                                string.setEndpositionout();
                                string.setstartpositionout();
                            }
                            if(status ==2){
                                resetstatedelete();
                                status = 0;
                                string.setEndpositionout();
                                string.setstartpositionout();
                            }
                            if(status == 3){
                                resetstateadd();
                                status = 0;
                                string.setEndpositionout();
                                string.setstartpositionout();
                            }
                            string.moveright(cursor);
                        }


                        if (code == KeyCode.BACK_SPACE) {
                            if(status == 3) {
                                System.out.println("delete at 3 state");
                                /*
                                int returnsize = string.returnpreservesize();
                                if(returnsize>100){
                                    string.removeintegerstartbegin();
                                    string.removeintegerendbegin();
                                    string.removeStringlistbegin();
                                    string.removestatusbegin();
                                }
                                /*


                                int startposition = string.returnpostion()-1;
                                int endposition = string.returnpostion();

                                 */
                                /**
                                string.addintegerstart(startposition);
                                string.addintegerend(endposition);
                                string.convertdynamicstringbuilder();

                                string.addselfstring();

                                string.addstatus(status);
                                string.resetdynamicstring();
                                 **/
                                resetstateadd();
                                endposition = string.returnpostion();
                                startposition = endposition;

                                char x = string.returntextnode().getText().charAt(0);
                                deleteGeneral(x);


                                string.display();
                                status =2 ;
                                return;
                            }
                            if(status==1){
                                root.getChildren().add(cursor);
                                System.out.println("delete at 1 state");
                                /*
                                int returnsize = string.returnpreservesize();
                                if(returnsize>100){
                                    string.removeintegerstartbegin();
                                    string.removeintegerendbegin();
                                    string.removeStringlistbegin();
                                    string.removestatusbegin();
                                }

                                 */
                                /**
                                string.addselfstart();
                                string.addselfend();
                                string.returndynamicstringbuilder();

                                System.out.println(string.returndynamicstringtest());

                                string.addselfstring();
                                string.addstatus(status);



                                string.resetrectanglewhite();
                                string.testdeleteselection(cursor);
                                 **/
                                resetstateselection();
                                startposition = string.returnpostion();


                                endposition = startposition;


                                string.display();
                                status = 0;
                                return;




                            }
                            if(status==2){

                                char x= string.returntextnode().getText().charAt(0);
                                deleteGeneral(x);
                                string.display();
                                return;
                            }
                            if(status == 0){

                                startposition = string.returnpostion();
                                endposition = startposition;
                                char x = string.returntextnode().getText().charAt(0);
                                deleteGeneral(x);
                                string.display();
                                status = 2;
                                return;
                            }



                        }

                        if(code == KeyCode.ENTER){
                            if(status==0){
                                startposition = string.returnpostion();
                                endposition = startposition;
                                addChargeneral('\n');
                                status = 3;
                                return;
                            }
                            if(status==1){
                                /*
                                int returnsize = string.returnpreservesize();
                                if(returnsize>100){
                                    string.removeintegerstartbegin();
                                    string.removeintegerendbegin();
                                    string.removeStringlistbegin();
                                    string.removestatusbegin();
                                }

                                 */
                                /*
                                string.returndynamicstringbuilder();
                                System.out.println(string.returndynamicstringtest());
                                string.addselfstart();
                                string.addselfend();
                                string.addselfstring();
                                string.resetrectanglewhite();
                                string.testdeleteselection(cursor);
                                string.resetdynamicstring();


                                string.addstatus(status);

                                 */
                                root.getChildren().add(cursor);
                                resetstateselection();
                                startposition = string.returnpostion();

                                endposition = startposition;
                                addChargeneral('\n');
                                status = 3;
                                return;


                            }
                            if(status == 2){
                                /*
                                string.convertdynamicstringbuilderreverse();
                                string.addselfstring();
                                string.addintegerstart(startposition);
                                string.addintegerend(endposition);
                                string.addstatus(status);
                                string.resetdynamicstring();

                                 */
                                resetstatedelete();
                                startposition = string.returnpostion();
                                endposition = startposition;
                                addChargeneral('\n');
                                status = 3;
                                return;

                            }
                            if(status == 3){
                                addChargeneral('\n');
                                return;
                            }




                        }
                if (keyEvent.isShortcutDown()) {
                    presscommand = true;
                    if (code == KeyCode.S) {
                        /**
                         * need to save everything here
                         */
                        string.savefile(filenames);
                        System.out.println("Save file ");
                    }

                    if(code == KeyCode.Z) {
                        if(status==1){
                            root.getChildren().add(cursor);
                            string.resetrectanglewhite();
                            status=0;
                            string.setEndpositionout();
                            string.setstartpositionout();

                        }

                        if(status==2){
                        /*
                        string.convertdynamicstringbuilderreverse();

                        string.addselfstring();
                        string.addintegerstart(startposition);
                        string.addintegerend(endposition);
                        string.addstatus(status);
                        string.resetdynamicstring();
*/

                            resetstatedelete();
                            status = 0;
                            string.setEndpositionout();
                            string.setstartpositionout();
                        }

                        if(status==3){
                        /*
                        string.convertdynamicstringbuilder();
                        string.addintegerstart(startposition);
                        string.addintegerend(endposition);
                        string.addstatus(status);
                        string.addselfstring();
                        string.resetdynamicstring();

*/


                            resetstateadd();
                            status = 0;
                            string.setEndpositionout();
                            string.setstartpositionout();


                        }


                        boolean setresult = string.undo(cursor);

                        System.out.println(setresult);
                        if(setresult==true){
                            root.getChildren().remove(cursor);
                            status=1;

                        }
                        else{
                            status = 0;

                        }
                        System.out.println(status);
                    }
                    if(code == KeyCode.Y){
                        if(status == 1){
                            root.getChildren().add(cursor);
                            string.resetrectanglewhite();
                            status=0;
                            string.setEndpositionout();
                            string.setstartpositionout();
                        }


                        boolean setresult = string.redo(cursor);
                        System.out.println(setresult);
                        if(setresult == true){
                            root.getChildren().remove(cursor);

                            status = 1;

                        }

                        System.out.println(status);
                    }


                }

                    }





        }



    }

    private class MouseEventHandler implements EventHandler<MouseEvent>{
        Group root;
        double lastPositionX;
        double lastPositionY;

        MouseEventHandler(Group root){
            this.root = root;

        }

        public void handle(MouseEvent mouseEvent){

            double mousePressedx = mouseEvent.getX();
            double mousePressedy = mouseEvent.getY();
            double checkmousex=0;
            double checkmousey=0;


            cursor.setX(mousePressedx);
            cursor.setY(mousePressedy+movelength);
            string.resetcurrentNodese(cursor,movelength);

            EventType eventType = mouseEvent.getEventType();
            /*
            if(eventType == MouseEvent.MOUSE_CLICKED){
                System.out.println("go to here");

                if(status!=0){

                    if(status==1){
                        string.resetrectanglewhite();
                        status=0;
                        string.setEndpositionout();
                        string.setstartpositionout();
                    }



                    if(status==2){

                        string.convertdynamicstringbuilderreverse();

                        string.addselfstring();
                        string.addintegerstart(startposition);
                        string.addintegerend(endposition);
                        string.addstatus(status);
                        string.resetdynamicstring();


                        resetstatedelete();


                         status = 0;
                        string.setEndpositionout();
                        string.setstartpositionout();
                    }

                    if(status==3){

                        string.convertdynamicstringbuilder();
                        string.addintegerstart(startposition);
                        string.addintegerend(endposition);
                        string.addstatus(status);
                        string.addselfstring();
                        string.resetdynamicstring();


                        System.out.println("go to here");

                        resetstateadd();
                        status = 0;
                        string.setEndpositionout();
                        string.setstartpositionout();


                    }


                }

            }

             */

            if(eventType == MouseEvent.MOUSE_PRESSED){
                System.out.println("go to mousepressed");



                if(status!=0){
                    if(status==1){
                        root.getChildren().add(cursor);
                        string.resetrectanglewhite();
                        status=0;
                        string.setEndpositionout();
                        string.setstartpositionout();
                    }

                    if(status==2){
                        /*
                        string.convertdynamicstringbuilderreverse();

                        string.addselfstring();
                        string.addintegerstart(startposition);
                        string.addintegerend(endposition);
                        string.addstatus(status);
                        string.resetdynamicstring();
*/

                        resetstatedelete();
                        status = 0;
                        string.setEndpositionout();
                        string.setstartpositionout();
                    }

                    if(status==3){
                        /*
                        string.convertdynamicstringbuilder();
                        string.addintegerstart(startposition);
                        string.addintegerend(endposition);
                        string.addstatus(status);
                        string.addselfstring();
                        string.resetdynamicstring();

*/
                        System.out.println("go to here");

                        resetstateadd();
                        status = 0;
                        string.setEndpositionout();
                        string.setstartpositionout();


                    }


                }








                lastPositionX = mousePressedx;
                lastPositionY = mousePressedy;
                /*
                if(lastPositionX!=checkmousex||lastPositionY!=checkmousey){

                    string.resetrectanglewhite();
                    status=0;
                    string.setEndpositionout();
                    string.setstartpositionout();

                }

                 */




            }
            else if(eventType == MouseEvent.MOUSE_DRAGGED) {


                if ((mousePressedx - lastPositionX > 5 || mousePressedx - lastPositionX < -5)) {
                    root.getChildren().remove(cursor);


                    string.resetrectangle(lastPositionX, lastPositionY, mousePressedx, mousePressedy, movelength * 2);

                    status = 1;


                    string.display();


                }
            }
            /*
            we can do some adjustment here when we add the function of
             */
            else if(eventType == MouseEvent.MOUSE_RELEASED){
                checkmousex = lastPositionX;
                checkmousey = lastPositionY;



            }


            /**EventType eventType = mouseEvent.getEventType();
             * if (eventType == MouseEvent.MOUSE_PRESSED) {
             *                 lastPositionX = mousePressedX;
             *                 lastPositionY = mousePressedY;
             *                 int startline = string.calculate(lastPositionY,movelength);
             *
             *
             *             } else if (eventType == MouseEvent.MOUSE_DRAGGED) {
             *
             *             int endline = string.calculatePostion(mosePressedy,movelength);
             *             Rectangle[] arrayRectangle = new Rectangle[endline-startline+1];
             *             if(endline== startline){
             *             Rectangle newRectangle = new Rectangle(0,0);
             *             newRectangle.setX(lastpositionx);
             *             newRectangle.setY(lastpostiony);
             *             newRectangle.setWidth(mousePressedX-lastpositionx);
             *             newRectangle.setHeight(15);
             *             newRectangle.fill(lightblue);
             *             arrayRectangle[0]=newRectangle;
             *
             *
             *             }
             *             else{
             *             for(int i=0;i<=endlength-startlength;i++}{
             *             if(i = 0){
             *              Rectangle newRectangle = new Rectangle(0,0);
             *              *             newRectangle.setX(lastpositionx);
             *              *             newRectangle.setY(lastpostiony);
             *              *             newRectangle.setWidth(windowwidth-lastpositionx);
             *              *             newRectangle.setHeight(15);
             *               newRectangle.fill(lightblue);
             *              *             arrayRectangle[0]=newRectangle;
             *              }
             *              if(i == endlength-startlength
             *
             *
             *
             *
             *             }
             *
             */



        }
    }





   public void start(Stage primaryStage){
        int windowheight = 500;
        int windowwidth = 500;
        String inputfile = "examplefilehelp.txt";




       Group root = new Group();


       Group textroot = new Group();
       Scene scene = new Scene(root, windowwidth,windowheight,Color.WHITE);





       EventHandler<KeyEvent> keyEventEventHandler = new KeyEventHandler(textroot,windowwidth,windowheight,inputfile);
       scene.setOnKeyPressed(keyEventEventHandler);
       scene.setOnKeyTyped(keyEventEventHandler);

       string.generateFastlinkedlistse(inputfile,cursor);

       ScrollBar scrollBar = new ScrollBar();
       scrollBar.setOrientation(Orientation.VERTICAL);
       scrollBar.setPrefHeight(windowheight);
       scrollBar.setMin(0);
       scrollBar.setMax(windowheight);
       scrollBar.setLayoutX(windowwidth-10);
       root.getChildren().add(scrollBar);
       textroot.getChildren().add(cursor);
       root.getChildren().add(textroot);
       scrollBar.valueProperty().addListener(new ChangeListener<Number>() {
           @Override
           public void changed(ObservableValue<? extends Number> observableValue, Number oldvalue, Number newvalue) {
               double scrollbarmovevalue = newvalue.doubleValue();
               double setmovevalue = (scrollbarmovevalue*(string.returnlength()-windowheight))/windowheight;
               System.out.println(setmovevalue);
               setdoublelength(setmovevalue/2);




               textroot.setLayoutY(-setmovevalue);

           }
       });









       drawCursor.makecolorchange();

       EventHandler<MouseEvent> mouseEventEventHandler = new MouseEventHandler(textroot);

       scene.setOnMousePressed(mouseEventEventHandler);
       scene.setOnMouseDragged(mouseEventEventHandler);
       scene.setOnMouseReleased(mouseEventEventHandler);




       primaryStage.setTitle("Single");
       primaryStage.setScene(scene);
       primaryStage.show();

   }

   public static void main(String[] args){
       launch(args);
   }


}