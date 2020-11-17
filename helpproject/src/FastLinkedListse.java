import java.nio.Buffer;
import java.util.ArrayList;
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
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;


public class FastLinkedListse {
    public  class Nodes{
        public Text textnode;
        public int linenum;
        public int position;


        public Nodes prev;
        public Nodes next;



        public Nodes(Text textnode, Nodes prev, Nodes next,int linenum,int position){
            this.textnode = textnode;
            this.linenum = linenum;
            if(prev != null) {
                this.prev = prev;
            }else{
                this.prev = this;
            }
            if(next != null) {
                this.next = next;
            }else{
                this.next = this;
            }
            this.position = position;

        }

        public Nodes returnprev(){
            return this.prev;
        }
        public Nodes returnnext(){
            return this.next;
        }
    }

    private Nodes sentinel;
    private Nodes currentNode;
    private ArrayList<Nodes> arrayList;
    private double genwidth;
    private double leftmarginx;
    private double marginx;
    private double marginy;
    private String dynamicstring;
    private StringBuilder dynamicstringbuilder;
    private ArrayList<Rectangle> rectangleArrayList;
    private Group root;
    private int startposition;
    private int endposition;
    private LinkedList<String> stringArrayList;
    private LinkedList<Integer> startintegerArrayList;
    private LinkedList<Integer> endintegerArrayList;
    private LinkedList<Integer> status;
    private LinkedList<String> redostringArrayList;
    private LinkedList<Integer> redostartintegerArrayList;
    private LinkedList<Integer> redoendintegerArrayList;
    private LinkedList<Integer> redostatus;
    public char dynamicdeletechar;

    /*
    private int currentPosition;

     */
    public FastLinkedListse(double genwidth,Group root){
        Text sentineltext = new Text(0,0,"\r");
        sentineltext.setFont(Font.font("Verdana",12));


        sentinel = new Nodes(sentineltext,sentinel,sentinel,0,0);
        arrayList = new ArrayList<>();
        currentNode = sentinel;
        arrayList.add(sentinel);
        /*
        currentPosition = 0;

         */

        this.genwidth = genwidth;
        marginx = 1;
        marginy = 15;
        leftmarginx = 5*marginx;
        sentineltext.setX(leftmarginx-marginx);

         dynamicstring = "";
         Rectangle startrectangle = new Rectangle(0,0);
         startrectangle.setWidth(genwidth);
         startrectangle.setHeight(marginy);
         startrectangle.setFill(Color.WHITE);
         rectangleArrayList = new ArrayList<>();
         rectangleArrayList.add(startrectangle);

         this.root = root;
         this.root.getChildren().add(startrectangle);
         startposition = 0;
         endposition = 0;
         stringArrayList = new LinkedList<>();
         startintegerArrayList = new LinkedList<>();
         endintegerArrayList = new LinkedList<>();
         status = new LinkedList<>();
         redostringArrayList = new LinkedList<>();
         redoendintegerArrayList = new LinkedList<>();
         redostatus = new LinkedList<>();
         redostartintegerArrayList = new LinkedList<>();
         dynamicstringbuilder = new StringBuilder();
         dynamicdeletechar = ' ';

    }


    public void addChar(char x, Rectangle cursor){


        int linenum;
        double xpos;
        int position;

        double ypos;
        /*
        if(!currentNode.textnode.getText().equals("\n")){

         */
        xpos = currentNode.textnode.getX()+currentNode.textnode.getLayoutBounds().getWidth()+marginx;
        ypos= currentNode.textnode.getY();
        linenum = currentNode.linenum;
        position = currentNode.position+1;


        System.out.println(linenum);


        /*
        else{
            System.out.println("we test enter in sub");
            linenum = currentNode.linenum+1;
            xpos = marginx;
            ypos = marginy*linenum;
        }

         */


        Text newtext = new Text(0,0,Character.toString(x));
        newtext.setX(xpos);
        newtext.setY(ypos);
        newtext.setTextOrigin(VPos.TOP);
        newtext.setText(Character.toString(x));
        newtext.setFont(Font.font("Verdana",12));
        Nodes newNode = new Nodes(newtext,currentNode,currentNode.next,linenum,position);

        currentNode.next.prev = newNode;
        currentNode.next = newNode;
        currentNode = newNode;
        if(currentNode.textnode.getText().equals("\n")||currentNode.textnode.getText().equals("\r\n")){
            currentNode.linenum = currentNode.linenum+1;
/*
            System.out.println("we add new line in sub part");

 */
            currentNode.textnode.setX(leftmarginx-marginx);
            currentNode.textnode.setY(marginy*currentNode.linenum);
            if (currentNode.linenum >= arrayList.size()) {
                /*
                System.out.println("we add new line in first part");

                 */
                arrayList.add(currentNode);
                addRectangle(currentNode.linenum);
            } else {
                arrayList.set(currentNode.linenum, currentNode);
            }

        }
        if(currentNode.textnode.getText().equals(" ")&&checkwhetherchange(currentNode.prev)){
            updatesecond(arrayList.get(currentNode.linenum).prev);

        }
        else {
            updatesecond(currentNode);
        }
        root.getChildren().add(currentNode.textnode);
        if(!(currentNode.textnode.getText().equals("\n")||currentNode.textnode.getText().equals("\r\n"))) {
            cursor.setX(currentNode.textnode.getX() + currentNode.textnode.getLayoutBounds().getWidth());

        }
        else{
            cursor.setX(leftmarginx-marginx);

        }
        cursor.setY(currentNode.textnode.getY());
        cursor.setHeight(marginy);
        cursor.setWidth(marginx);
        /*
        currentPosition = currentPosition+1;
*/


    }


    public void addRectangle(int linenum){
        Rectangle newRectangle = new Rectangle(0,0);
        newRectangle.setX(0);
        newRectangle.setY(marginy*linenum);
        newRectangle.setFill(Color.WHITE);
        newRectangle.setWidth(genwidth);
        newRectangle.setHeight(marginy);
        rectangleArrayList.add(newRectangle);
        root.getChildren().add(newRectangle);

    }

    public void deleteChar(Rectangle cursor){
        if(currentNode == sentinel){
            return;
        }


        root.getChildren().remove(currentNode.textnode);
        dynamicdeletechar = currentNode.textnode.getText().charAt(0);
        System.out.println(dynamicdeletechar);
        Nodes p = currentNode;
        currentNode.next.prev = currentNode.prev;
        currentNode= currentNode.prev;
        currentNode.next = p.next;
        if(arrayList.contains(p)){
            if(p.next==sentinel){
                arrayList.remove(p.linenum);
                root.getChildren().remove(rectangleArrayList.get(p.linenum));
                rectangleArrayList.remove(p.linenum);

            }
            else{
                arrayList.set(p.linenum,p.next);
            }
        }
        if(checkwhetherchange(currentNode)){
            Nodes startNode = arrayList.get(currentNode.linenum);
            updatesecond(startNode.prev);
        }
        else{
            updatesecond(currentNode);
        }


        p.next = null;
        p.prev = null;
        if(!(currentNode.textnode.getText().equals("\n")||currentNode.textnode.getText().equals("\r\n"))) {
            cursor.setX(currentNode.textnode.getX() + currentNode.textnode.getLayoutBounds().getWidth());

        }
        else{
            cursor.setX(leftmarginx-marginx);

        }
        cursor.setY(currentNode.textnode.getY());
        /*
        currentPosition = currentPosition-1;
*/



    }



/**
    public void updatecurrentNode(Nodes startNode){
        while(!((startNode.textnode.getX()+startNode.textnode.getLayoutBounds().getWidth()+marginx<=genwidth&&(startNode.next==arrayList.get(startNode.linenum+1)||startNode.next==sentinel)))||startNode==sentinel){
            /*

            if(startNode.next==sentinel){
                if(startNode.textnode.getText()!=" ") {
                    Nodes searchnonspace = startNode;
                    while(searchnonspace.textnode.getText()!= " "){
                        if(searchnonspace==arrayList.get(startNode.linenum)){
                            searchnonspace = startNode;
                            break;
                        }
                        else{
                            searchnonspace = searchnonspace.prev;
                        }

                    }
                    startNode = searchnonspace;

                    startNode.linenum = startNode.linenum + 1;
                    startNode.textnode.setX(marginx);
                    startNode.textnode.setY(marginy * startNode.linenum);
                    arrayList.add(startNode);
                }
                break;


            if(startNode.textnode.getX()+startNode.textnode.getLayoutBounds().getWidth()+marginx<=genwidth){
                startNode = startNode.next;
                startNode.textnode.setX(startNode.prev.textnode.getX()+startNode.prev.textnode.getLayoutBounds().getWidth()+marginx);
                startNode.textnode.setY(startNode.prev.textnode.getY());
                startNode.linenum = startNode.prev.linenum;
            }
            else{
                if(startNode.textnode.getText()==" "){
                   startNode = startNode.next;
                   startNode.textnode.setX(startNode.prev.textnode.getX()+startNode.prev.textnode.getLayoutBounds().getWidth()+marginx);
                   startNode.textnode.setY(startNode.prev.textnode.getY());
                   startNode.linenum = startNode.prev.linenum;
                }
                if(startNode.textnode.getText()!= " "){
                    Nodes searchnonspace = startNode;
                    while(searchnonspace.textnode.getText() != " "){

                        if(searchnonspace == arrayList.get(startNode.linenum)){
                            /*
                            startNode.textnode.setX(marginx);
                            startNode.linenum = startNode.linenum+ 1;
                            startNode.textnode.setY(marginy*startNode.linenum);



                            searchnonspace = startNode.prev;
                            break;


                        }
                        else{
                            searchnonspace = searchnonspace.prev;
                            /*
                            startNode.linenum = startNode.linenum+1;
                            startNode.textnode.setX(marginx);
                            startNode.textnode.setY(marginy*startNode.linenum);


                        }


                    }
                    startNode = searchnonspace.next;
                    startNode.linenum = startNode.linenum+1;
                    startNode.textnode.setX(marginx);
                    startNode.textnode.setY(startNode.linenum*marginy);
                    if(startNode.linenum>=arrayList.size()){
                        arrayList.add(startNode);
                    }
                    else{
                        arrayList.set(startNode.linenum,startNode);
                    }
                    startNode = startNode.next;
                    startNode.textnode.setX(startNode.prev.textnode.getX()+startNode.prev.textnode.getLayoutBounds().getWidth()+marginx);
                    startNode.textnode.setY(startNode.prev.textnode.getY());
                    startNode.linenum = startNode.prev.linenum;

                }
            }

        }





    }
 */
public void moveleft(Rectangle cursor){
    if(currentNode == sentinel.next){
        return;
    }
     if(!(currentNode.textnode.getText().equals("\n")||currentNode.textnode.getText().equals("\r\n"))){
         cursor.setX(currentNode.textnode.getX()-marginx);
         cursor.setY(currentNode.textnode.getY());
     }
     else{
         cursor.setX(currentNode.prev.textnode.getX()+currentNode.prev.textnode.getLayoutBounds().getWidth());
         cursor.setY(currentNode.prev.textnode.getY());
     }
     currentNode = currentNode.prev;

     }

     public void moveright(Rectangle cursor){
      if(currentNode.next == sentinel){
          return;
      }
      currentNode = currentNode.next;
      if(arrayList.contains(currentNode.next)&&(!(currentNode.next.textnode.getText().equals("\n")||currentNode.next.textnode.equals("\r\n")))){
          cursor.setX(leftmarginx-marginx);
          cursor.setY((currentNode.linenum+1)*marginy);
      }
      else{
          cursor.setX(currentNode.textnode.getX()+currentNode.textnode.getLayoutBounds().getWidth());
          cursor.setY(currentNode.linenum*marginy);
      }
     }

     public void moveup(Rectangle cursor){
       if(currentNode.linenum==0){
           return;
       }
       double xpos = currentNode.textnode.getX();
       Nodes startNode = arrayList.get(currentNode.linenum-1);

       while(startNode.textnode.getX()+startNode.textnode.getLayoutBounds().getWidth()<=xpos){
           startNode = startNode.next;
           if(startNode == arrayList.get(currentNode.linenum)){
               break;
           }
       }
       if(startNode== arrayList.get(currentNode.linenum)){
           cursor.setX(startNode.prev.textnode.getX()+startNode.prev.textnode.getLayoutBounds().getWidth());
           cursor.setY(startNode.prev.textnode.getY());
           currentNode = startNode.prev;
       }
       else
       {
           if(xpos-startNode.textnode.getX()<=startNode.textnode.getX()+startNode.textnode.getLayoutBounds().getWidth()-xpos){
           cursor.setX(startNode.textnode.getX()-marginx);
           cursor.setY(startNode.textnode.getY());
           currentNode = startNode.prev;

         }
       else{
           cursor.setX(startNode.textnode.getX()+startNode.textnode.getLayoutBounds().getWidth());
           cursor.setY(startNode.textnode.getY());
           currentNode = startNode;

       }
       }


     }

     public void moveupsecond(Rectangle cursor,double Layouty){
      if(0<=cursor.getY()&&cursor.getY()<marginy){
          return;
      }
      double xpos = cursor.getX();
      int currentlinenum = linecursor(cursor,Layouty);
      Nodes startNode = arrayList.get(currentlinenum-1);
      while(startNode.textnode.getX()+startNode.textnode.getLayoutBounds().getWidth()<=xpos){
             startNode = startNode.next;
             if(startNode == arrayList.get(currentlinenum)){
                 break;
             }
         }
         if(startNode== arrayList.get(currentlinenum)){
             cursor.setX(startNode.prev.textnode.getX()+startNode.prev.textnode.getLayoutBounds().getWidth());
             cursor.setY(startNode.prev.textnode.getY());
             currentNode = startNode.prev;
         }
         else{
             if(xpos-startNode.textnode.getX()<=startNode.textnode.getX()+startNode.textnode.getLayoutBounds().getWidth()-xpos){
                 cursor.setX(startNode.textnode.getX()-marginx);
                 cursor.setY(startNode.textnode.getY());
                 currentNode = startNode.prev;

             }
             else{
                 cursor.setX(startNode.textnode.getX()+startNode.textnode.getLayoutBounds().getWidth());
                 cursor.setY(startNode.textnode.getY());
                 currentNode = startNode;

             }

         }

     }

     public void movedown(Rectangle cursor){
     if(currentNode.linenum== arrayList.size()-1){
         return;
     }
     double xpos = currentNode.textnode.getX();
     Nodes startNode = arrayList.get(currentNode.linenum+1);
         while(startNode.textnode.getX()+startNode.textnode.getLayoutBounds().getWidth()<=xpos){
             startNode = startNode.next;
             if(startNode == sentinel){
                 break;
             }
            if(arrayList.size()>currentNode.linenum+2){
                if(startNode == arrayList.get(currentNode.linenum+2)){
                    break;
                }
            }
         }
         if(startNode == sentinel){
             cursor.setX(sentinel.prev.textnode.getX()+sentinel.prev.textnode.getLayoutBounds().getWidth());
             cursor.setY(sentinel.prev.textnode.getY());
             currentNode = sentinel.prev;
             return;
         }
         if(arrayList.size()>currentNode.linenum+2){
             if(startNode == arrayList.get(currentNode.linenum+2)){
                 cursor.setX(startNode.prev.textnode.getX()+startNode.prev.textnode.getLayoutBounds().getWidth());
                 cursor.setY(startNode.prev.textnode.getY());
                 currentNode = startNode.prev;
                 return;
             }
         }
         if(xpos-startNode.textnode.getX()<=startNode.textnode.getX()+startNode.textnode.getLayoutBounds().getWidth()-xpos){
             cursor.setX(startNode.textnode.getX()-marginx);
             cursor.setY(startNode.textnode.getY());
             currentNode = startNode.prev;

         }
         else{
             cursor.setX(startNode.textnode.getX()+startNode.textnode.getLayoutBounds().getWidth());
             cursor.setY(startNode.textnode.getY());
             currentNode = startNode;
         }



     }

     public void movedownsecond(Rectangle cursor, double Layouty){
       if(cursor.getY()>marginy*(arrayList.size()-1)&&cursor.getY()<=marginy*arrayList.size()){
           return;
       }
       
       double xpos = cursor.getX();

     }


    public void updatesecond(Nodes startNode) {
        while (startNode != sentinel) {
            if (startNode.textnode.getText().equals("\n")||currentNode.textnode.getText().equals("\r\n")) {
                startNode.textnode.setX(leftmarginx-marginx);
                startNode.linenum = startNode.prev.linenum+1;
                startNode.textnode.setY(marginy*startNode.linenum);
                startNode.position = startNode.prev.position+1;
                if (startNode.linenum >= arrayList.size()) {
                    System.out.println("we add new line in first part");
                    arrayList.add(startNode);
                    addRectangle(startNode.linenum);

                } else {
                    arrayList.set(startNode.linenum, startNode);
                }


                if (startNode.next == sentinel) {
                    break;

                }


                startNode = startNode.next;


                startNode.textnode.setX(leftmarginx);
                startNode.textnode.setY(startNode.prev.textnode.getY());
                startNode.linenum = startNode.prev.linenum;
                startNode.position = startNode.prev.position+1;


            } else {
                if (startNode.textnode.getX() + startNode.textnode.getLayoutBounds().getWidth() + marginx <= genwidth) {
                    if (startNode.next == sentinel) {
                        break;
                    }
                    startNode = startNode.next;
                    startNode.position = startNode.prev.position+1;
                    startNode.textnode.setX(startNode.prev.textnode.getX() + startNode.prev.textnode.getLayoutBounds().getWidth() + marginx);
                    startNode.textnode.setY(startNode.prev.textnode.getY());
                    startNode.linenum = startNode.prev.linenum;
                } else {
                    if (startNode.textnode.getText().equals(" ")) {
                        if (startNode.next == sentinel) {
                            break;
                        }
                        startNode = startNode.next;
                        startNode.position = startNode.prev.position+1;
                        startNode.textnode.setX(startNode.prev.textnode.getX() + startNode.prev.textnode.getLayoutBounds().getWidth() + marginx);
                        startNode.textnode.setY(startNode.prev.textnode.getY());
                        startNode.linenum = startNode.prev.linenum;
                    } else {
/*
                        if (startNode.textnode.getText().equals('\n')) {
                            if (startNode.next == sentinel) {
                                break;

                            }
                            startNode = startNode.next;
                            startNode.linenum = startNode.linenum + 1;

                            startNode.textnode.setX(marginx);
                            startNode.textnode.setY(marginy * startNode.linenum);
                        }

 */
                            Nodes searchnonspace = startNode;

                            while (!searchnonspace.textnode.getText().equals(" ")) {

                                if (searchnonspace == arrayList.get(startNode.linenum)) {

                                    searchnonspace = startNode.prev;
                                    break;
                                }
                                searchnonspace = searchnonspace.prev;


                            }
                            startNode = searchnonspace.next;
                            startNode.position = startNode.prev.position+1;
                            startNode.linenum = startNode.linenum + 1;
                            startNode.textnode.setX(leftmarginx);
                            startNode.textnode.setY(startNode.linenum * marginy);
                            if (startNode.linenum >= arrayList.size()) {
                                System.out.println("we add new line in second part");
                                arrayList.add(startNode);
                                addRectangle(startNode.linenum);
                            } else {
                                arrayList.set(startNode.linenum, startNode);
                            }
                            if (startNode.next == sentinel) {
                                break;
                            }
                            startNode = startNode.next;
                            startNode.position = startNode.prev.position+1;
                            startNode.textnode.setX(startNode.prev.textnode.getX() + startNode.prev.textnode.getLayoutBounds().getWidth() + marginx);
                            startNode.textnode.setY(startNode.prev.textnode.getY());
                            startNode.linenum = startNode.prev.linenum;

                    }
                }
            }
        }
        int i = arrayList.size();

        while(i-1>arrayList.get(i-1).linenum){

            arrayList.remove(arrayList.get(i-1));
            root.getChildren().remove(rectangleArrayList.get(i-1));
            rectangleArrayList.remove(i-1);

            i = i-1;
            if(i-1== arrayList.get(i-1).linenum){
                break;
            }
        }


/*
        for(int j=0 ;j<=i-1;j++){
            System.out.println(arrayList.get(j).textnode);
        }

 */



    }


    private double getcursorx(Rectangle cursor){
        return cursor.getX();
    }

    private double getcursory(Rectangle cursor){
        return cursor.getY();
    }

    private int linecursor(Rectangle cursor,double Layouty){
        return (int) ((getcursory(cursor)+Layouty)/marginy);

    }
    public int calculateline(double ypressed,double Layouty){
        return (int) ((ypressed+Layouty)/marginy);
    }

    public void resetcurrentNodese(Rectangle cursor,double Layouty){
        double subLayout = Layouty;
        cursor.setHeight(marginy);
        double xpos = getcursorx(cursor);
        double ypos = getcursory(cursor);
        int linenum = linecursor(cursor,subLayout);
        if(linenum<= arrayList.size()-1){
            Nodes startnode = arrayList.get(linenum);
            while(startnode.textnode.getX()+startnode.textnode.getLayoutBounds().getWidth()<=xpos){
                startnode = startnode.next;
                if(arrayList.size()>linenum+1){
                    if(startnode==arrayList.get(linenum+1)){
                        break;
                    }
                }
                if(startnode==sentinel){
                    break;
                }

            }
            if(arrayList.size()>linenum+1){
                if(startnode==arrayList.get(linenum+1)){
                    cursor.setX(arrayList.get(linenum+1).prev.textnode.getX()+arrayList.get(linenum+1).prev.textnode.getLayoutBounds().getWidth());
                    cursor.setY(arrayList.get(linenum+1).prev.textnode.getY());
                    currentNode = arrayList.get(linenum+1).prev;
                    return;
                }
            }
            if(startnode==sentinel){
                cursor.setX(sentinel.prev.textnode.getX()+sentinel.prev.textnode.getLayoutBounds().getWidth());
                cursor.setY(sentinel.prev.textnode.getY());
                currentNode = sentinel.prev;
                return;
            }
            currentNode = startnode;
            if(xpos-startnode.textnode.getX()<=startnode.textnode.getX()+startnode.textnode.getLayoutBounds().getWidth()-xpos){
                cursor.setX(startnode.textnode.getX()-marginx);
                cursor.setY(startnode.textnode.getY());
                currentNode = startnode.prev;
            }
            else{
                cursor.setX(startnode.textnode.getX()+startnode.textnode.getLayoutBounds().getWidth());
                cursor.setY(startnode.textnode.getY());
            }


        }
        else{
            return;
        }
    }

    public void resetcurrentNode(Rectangle cursor,double Layouty){
        double subLayout = Layouty;
        cursor.setHeight(marginy);
        double xpos = getcursorx(cursor);
        double ypos = getcursory(cursor);
        int linenum =linecursor(cursor,subLayout);
        if(linenum <= arrayList.size()-1) {
            Nodes startnode = arrayList.get(linenum);
            /*
            check the one before sentinel
             */
            while (startnode.textnode.getX() <= xpos) {
                startnode = startnode.next;
                if(arrayList.size()>linenum+1){
                    if(startnode==arrayList.get(linenum+1)){
                        break;
                    }
                }
                if(startnode==sentinel){
                    break;
                }
            }
            if(arrayList.size()>linenum+1){
                if(startnode==arrayList.get(linenum+1)){
                    cursor.setX(arrayList.get(linenum+1).prev.textnode.getX()+arrayList.get(linenum+1).prev.textnode.getLayoutBounds().getWidth());
                    cursor.setY(arrayList.get(linenum+1).prev.textnode.getY());
                    currentNode = arrayList.get(linenum+1).prev;
                    return;
                }
            }
            if(startnode==sentinel){
                cursor.setX(sentinel.prev.textnode.getX()+sentinel.prev.textnode.getLayoutBounds().getWidth());
                cursor.setY(sentinel.prev.textnode.getY());
                currentNode = sentinel.prev;
                return;
            }



    /*
     if start at the beginning need to be able to click
    */
                currentNode = startnode.prev;
                if (xpos - currentNode.textnode.getX() <= currentNode.next.textnode.getX() - xpos) {
                    currentNode = currentNode.prev;
                    if(!arrayList.contains(currentNode.next)) {



                        cursor.setX(currentNode.textnode.getX() + currentNode.textnode.getLayoutBounds().getWidth());
                        cursor.setY(currentNode.textnode.getY());
                    }
                    else{
                        cursor.setX(0);
                        cursor.setY(currentNode.next.textnode.getY());

                    }
                }
                else {
                    cursor.setX(currentNode.textnode.getX() + currentNode.textnode.getLayoutBounds().getWidth());
                    cursor.setY(currentNode.textnode.getY());
                }



        }
        else{
            return;
        }
    }

    public int returnsize(){
        return arrayList.size();
    }



    public double returnlength(){
        return marginy*arrayList.size();
    }

    public boolean checkwhetherchange(Nodes startNode){
        boolean returnresult = false;
        /*
        Nodes startNode = currentNode;
*/
        while(startNode!=arrayList.get(currentNode.linenum)){

            if(startNode.textnode.getText().equals(" ")){
                break;
            }
            startNode = startNode.prev;
        }
        if(startNode == arrayList.get(currentNode.linenum)&&startNode!=sentinel){
            returnresult = true;
        }
        return returnresult;

    }
    public void display(){
        Nodes startnode = sentinel.next;
        while(startnode!=sentinel){


                startnode.textnode.toFront();
                startnode = startnode.next;
            }
    }

    public void generateFastlinkedlistse(String filename,Rectangle cursor){
        try {
            File inputfile = new File(filename);
            if (!inputfile.exists()) {
                System.out.println("Unable to copy because file with name " + filename
                        + " does not exist");
                return;
            }
            FileReader reader = new FileReader(inputfile);
            BufferedReader bufferedReader = new BufferedReader(reader);
            int intRead = -1;
            while((intRead = bufferedReader.read())!= -1){
                char charRead = (char) intRead;
                this.addChar(charRead,cursor);
            }
            System.out.println("Have successfully read file");
            this.display();
            bufferedReader.close();

        }catch (FileNotFoundException fileNotFoundException) {
            System.out.println("File not found! Exception was: " + fileNotFoundException);
        } catch (IOException ioException) {
            System.out.println("Error when copying; exception was: " + ioException);
        }



    }

    public void savefile(String filename){
        try {

            Nodes startnode = sentinel.next;
            FileWriter writer = new FileWriter(filename);
            while (startnode != sentinel) {
                char charwrite = startnode.textnode.getText().charAt(0);


                writer.write(charwrite);
                startnode = startnode.next;
            }
            System.out.println("Successfully write file");
            writer.close();
        }
        catch (IOException ioException) {
            System.out.println("Error when copying; exception was: " + ioException);
        }
        /*
        File oldfile = new File(filename);
        oldfile.delete();
        File newfile = new File("replaced.txt");
        newfile.renameTo(new File(filename));


         */


    }

    public void resetcurrentNodeno(int position){
        int goal = position;
        int startline = 0;
        if(position>sentinel.prev.position){
            System.out.println("The position is out of range");
            return;
        }
        else{
            for(int i=0;i<=arrayList.size()-1;i++){
                if(i==arrayList.size()-1){
                    Nodes startNode = arrayList.get(i);
                    while(startNode.position!=goal){
                        startNode = startNode.next;
                    }
                    currentNode = startNode;

                }
                else{
                    if(arrayList.get(i).position<=goal&&goal<arrayList.get(i+1).position){
                        Nodes startNode = arrayList.get(i);
                        while(startNode.position!=goal){
                            startNode = startNode.next;
                        }
                        currentNode = startNode;
                    }
                }
            }
        }
    }


    public void addString(int position,String inputString,Rectangle cursor){
        int subposition = position;
        resetcurrentNodeno(subposition);
        char[] arrayChar = inputString.toCharArray();
        for(char i:arrayChar){
            addChar(i,cursor);
        }



    }

    public void deleteString(int position,String deleteString,Rectangle cursor){
        int subposition = position;
        resetcurrentNodeno(subposition);
        int length = deleteString.length();
        for(int i=0;i<=length-1;i++){
            deleteChar(cursor);
        }


    }

    public void deleteStringse(int startpositioni,int endpositioni,Rectangle cursor){
        int subposition = endpositioni;
        resetcurrentNodeno(subposition);
        int length = endpositioni-startpositioni;
        for(int i=0;i<=length-1;i++){
            deleteChar(cursor);
        }
    }
/*
    public Rectangle[] selection(double startingx,double startingy,double lastx, double lasty,double Layouty){
        Rectangle[] rectangles;
        int size;
        double Layout = Layouty;

        int startline = (int)((Layout+startingy)/marginy);
        int endline = (int)((Layout+lasty)/marginy);
        size = endline-startline+1;
        rectangles = new Rectangle[endline-startline+1];
        if(size==1){
            Rectangle newRectangle = new Rectangle(0,0);
            double startvalue = computeexactxstart(startingx,startingy,Layout);
            double endvalue = computeexactxend(lastx,lasty,Layout);
            newRectangle.setX(startvalue);
            newRectangle.setY(arrayList.get(startline).textnode.getY());

           newRectangle.setWidth(endvalue-startvalue);
           newRectangle.setHeight(marginy);
           rectangles = new Rectangle[1];
           rectangles[0] = newRectangle;

        }
        if(size>1){


            for(int i=0;i<=endline-startline;i++){
            if(i==0){
            Rectangle Rectanglea ;
            double exactstartingx =computeexactxstart(startingx,startingy,Layout);
            Rectanglea = new Rectangle(0,0);
            Rectanglea.setX(exactstartingx);
            Rectanglea.setY(arrayList.get(startline).textnode.getY());
            Rectanglea.setWidth(genwidth-exactstartingx);
            Rectanglea.setHeight(marginy);
            Rectanglea.setFill(Color.LIGHTBLUE);
            rectangles[i] = Rectanglea;
            }
            if(0<i&&i<endline-startline){

             rectangles[i] = new Rectangle(0,0);
            rectangles[i].setX(0);
            rectangles[i].setY(arrayList.get(startline+i).textnode.getY());
            rectangles[i].setWidth(genwidth);
            rectangles[i].setHeight(marginy);
            rectangles[i].setFill(Color.LIGHTBLUE);

            }
            if(i== endline-startline){
            double exactendingx = computeexactxend(lastx,lasty,Layout);
            rectangles[i] = new Rectangle(0,0);
            rectangles[i].setX(0);
            rectangles[i].setY(arrayList.get(endline).textnode.getY());
            rectangles[i].setWidth(exactendingx);
            rectangles[i].setHeight(marginy);
            rectangles[i].setFill(Color.LIGHTBLUE);

            }
            }



        }
        return rectangles;

    }

 */




public void resetrectangle(double startingx,double startingy,double lastx, double lasty,double Layouty){

    double Layout = Layouty;
    int startline = (int)((Layout+startingy)/marginy);
    int endline = (int)((Layout+lasty)/marginy);
    if(endline<startline){
        resetrectangle(lastx,lasty,startingx,startingy,Layouty);
    }
    int size;
    size = endline-startline+1;
    if(size == 1){
        double startvalue = computeexactxstart(startingx,startingy,Layout);
        double endvalue = computeexactxend(lastx,lasty,Layout);

        if(startposition==endposition){
            startposition = startposition-1;
        }
        if(startposition>endposition){
            double temp = endvalue;
            endvalue = startvalue;
            startvalue = temp;
            int tempposition = endposition;
            endposition = startposition;
            startposition = tempposition;
        }


        Rectangle changerectangle = rectangleArrayList.get(startline);
        changerectangle.setX(startvalue);
        changerectangle.setWidth(endvalue-startvalue);
        changerectangle.setFill(Color.LIGHTBLUE);
        if(arrayList.size()>endline+1){
            rectangleArrayList.get(endline+1).setFill(Color.WHITE);
        }

    }
    else{
        for(int i=0;i<=endline-startline;i++){
            if(i==0){

                double startvalue = computeexactxstart(startingx,startingy,Layout);


                Rectangle changerectangle = rectangleArrayList.get(startline);

                changerectangle.setX(startvalue);
                /**
                 * need to change if we change the negative way of selection
                 */
                changerectangle.setWidth(genwidth-startvalue);

                changerectangle.setFill(Color.LIGHTBLUE);
            }
            else{
                if(0<i&&i<endline-startline){
                    Rectangle changerectangle = rectangleArrayList.get(startline+i);
                    changerectangle.setX(0);
                    changerectangle.setWidth(genwidth);
                    changerectangle.setFill(Color.LIGHTBLUE);
                }
                if(i== endline-startline){
                    Rectangle changerectangle = rectangleArrayList.get(startline+i);
                    double endvalue = computeexactxend(lastx,lasty,Layouty);
                    changerectangle.setX(0);

                    changerectangle.setWidth(endvalue);
                    changerectangle.setFill(Color.LIGHTBLUE);
                    if(arrayList.size()>endline+1){
                        rectangleArrayList.get(endline+1).setFill(Color.WHITE);
                    }

                }
            }
        }
    }


}



public double computeexactxend(double xpos,double ypos,double Layouty) {
    double returnresult = 0;
    int startline = (int) ((ypos + Layouty) / marginy);
    Nodes startNode = arrayList.get(startline);
    if (startline > arrayList.size()) {
        returnresult = sentinel.prev.textnode.getX() + sentinel.prev.textnode.getLayoutBounds().getWidth();
        currentNode = sentinel.prev;
        endposition = sentinel.prev.position;
    } else {
        while (startNode.textnode.getX() + startNode.textnode.getLayoutBounds().getWidth() < xpos) {
            startNode = startNode.next;
            if (startNode == sentinel) {

                break;
            }
            if (arrayList.size() > startline + 1) {
                if (startNode == arrayList.get(startline + 1)) {

                    break;
                }
            }
        }
        if (startNode == sentinel) {
            currentNode = sentinel.prev;
            returnresult = genwidth;
            endposition = currentNode.position;
            return returnresult;
        }
        if (arrayList.size() > startline + 1) {
            if (startNode == arrayList.get(startline + 1)) {
                currentNode = startNode.prev;
                returnresult = genwidth;
                endposition = currentNode.position;
                return returnresult;

            }
        }
                if (xpos - startNode.textnode.getX() <= startNode.textnode.getX() + startNode.textnode.getLayoutBounds().getWidth() - xpos) {
                    currentNode = startNode.prev;

                    returnresult = startNode.textnode.getX() - marginx;
                } else {
                    currentNode = startNode;
                    returnresult = startNode.textnode.getX() + startNode.textnode.getLayoutBounds().getWidth();
                }
                endposition = currentNode.position;






    }
    return returnresult;
}

public double computeexactxstart(double xpos,double ypos,double Layouty) {
        dynamicstring = "" ;
    double returnresult = 0;
    int startline = (int) ((ypos + Layouty) / marginy);
    if (startline > arrayList.size()) {
        returnresult = sentinel.prev.textnode.getX() + sentinel.prev.textnode.getLayoutBounds().getWidth();
    } else {

        Nodes startNode = arrayList.get(startline);
        while (startNode.textnode.getX() + startNode.textnode.getLayoutBounds().getWidth() < xpos) {

            startNode = startNode.next;
            if (startNode == sentinel) {

                break;
            }
            if (arrayList.size() > startline + 1) {
                if (startNode == arrayList.get(startline + 1)) {

                    break;
                }
            }
        }
        if (startNode == sentinel) {

            returnresult = startNode.prev.textnode.getX() + startNode.prev.textnode.getLayoutBounds().getWidth();
            return returnresult;
        }
        if (arrayList.size() > startline + 1) {

            if (startNode == arrayList.get(startline + 1)) {

                returnresult = startNode.prev.textnode.getX() + startNode.prev.textnode.getLayoutBounds().getWidth();
                startposition = startNode.prev.position;
                return returnresult;

            }
        }

            if (xpos - startNode.textnode.getX() <= startNode.textnode.getX() + startNode.textnode.getLayoutBounds().getWidth() - xpos) {

                returnresult = startNode.textnode.getX() - marginx;
                startposition = startNode.prev.position;
            } else {

                returnresult = startNode.textnode.getX() + startNode.textnode.getLayoutBounds().getWidth();
                if(startNode.next!= sentinel){
                    startposition = startNode.position;
                }
            }






    }
    return returnresult;
}


public ArrayList<Rectangle> returnrectangle(){
        return this.rectangleArrayList;
}

public String returndynamicstring(){
        resetcurrentNodeno(startposition);

        currentNode = currentNode.next;

        for(int i= startposition;i<=endposition-1;i++){
            dynamicstring = dynamicstring + currentNode.textnode.getText();
            currentNode = currentNode.next;
        }
        return dynamicstring;
}

public void returndynamicstringbuilder(){
        resetcurrentNodeno(startposition);
        if(startposition==endposition){
            if(startposition==0) {
                return;
            }
            else{
                startposition = startposition-1;
                currentNode = currentNode.prev;

            }
        }

            currentNode = currentNode.next;
            for (int i = startposition; i <= endposition - 1; i++) {
                dynamicstringbuilder.append(currentNode.textnode.getText().charAt(0));
                currentNode = currentNode.next;
            }
            dynamicstring = dynamicstringbuilder.toString();

            dynamicstringbuilder.delete(0, dynamicstring.length());


}

public void convertdynamicstringbuilder(){
    dynamicstring = dynamicstringbuilder.toString();

    dynamicstringbuilder.delete(0,dynamicstring.length());
}

public void convertdynamicstringbuilderreverse(){
    dynamicstring = dynamicstringbuilder.reverse().toString();


    dynamicstringbuilder.delete(0,dynamicstring.length());
}

public String returndynamicstringtest(){
        return dynamicstring;
}

public void resetdynamicstring(){
        dynamicstring = "";
}

public void deleteselection(Rectangle cursor){
        deleteString(endposition,dynamicstring,cursor);
        resetdynamicstring();
}

public void testdeleteselection(Rectangle cursor){
    /**
     * we use startposition-1 here because we need the position before that
     */
    deleteStringse(startposition,endposition,cursor);
        resetdynamicstring();
}

public void addStringoutsied(char x){
    dynamicstringbuilder.append(x);


}

public void addintegerstart(int startpositiona){

        startintegerArrayList.addLast(startpositiona);
}
public void removeintegerstart(){
        startintegerArrayList.removeLast();
}

public void removeintegerstartbegin(){
        startintegerArrayList.removeFirst();
}

public void addintegerend(int endpositiona){
        endintegerArrayList.addLast(endpositiona);
}
public void removeintegerend(){
        endintegerArrayList.removeLast();
}
public void removeintegerendbegin(){
        endintegerArrayList.removeFirst();
}

public void addStringlist(String addstring){
        stringArrayList.addLast(addstring);
}

public void removeStringlist(){
        stringArrayList.removeLast();
}

public void removeStringlistbegin(){
        stringArrayList.removeFirst();
}

public void addstatus(int preservenumber){
        status.addLast(preservenumber);
}
public void removestatus(){
        status.removeLast();
}
public void removestatusbegin(){
        status.removeFirst();
}

public int returnpostion(){
        return currentNode.position;
}

public int returnpreservesize(){
        return stringArrayList.size();
}

public void addselfstart(){

        startintegerArrayList.addLast(startposition);
}

public void addselfend(){

        endintegerArrayList.addLast(endposition);
}

public void addselfstring(){
        stringArrayList.addLast(dynamicstring);
}

public void resetrectanglewhite(){

        int startline = 0;
        int endline = 0;

        while(arrayList.get(startline).position<=startposition){

            startline = startline+1;
            if(startline == arrayList.size()-1){
                break;
            }

        }
        while(arrayList.get(endline).position<=endposition){

            endline=endline+1;
            if(endline==arrayList.size()-1){
                break;
            }

        }
        if(startline!=arrayList.size()-1){
        startline = startline-1;}
        if(endline!=arrayList.size()-1){
        endline = endline-1;}

        for(int i= startline;i<=endline;i++){
            rectangleArrayList.get(i).setFill(Color.WHITE);
        }

}

public Text returntextnode(){
        return currentNode.textnode;
}



public int returnstartposition(){
        return startposition;
}

public int returnendposition(){
        return endposition;
}

public void setstartpositionout(){
        startposition = 0;
}

public void setEndpositionout(){
        endposition = 0;
}

public void resetEndpositionout(int endpositionout){
    this.endposition = endpositionout;
}

public void resetStartpositionout(int startpositionout){
    this.startposition = startpositionout;
}

public boolean undo(Rectangle cursor){


    boolean returnresult = false;
    if(status.size()==0){
        return returnresult;
    }
    else{
        int statusresult = status.removeLast();

        String removeString = stringArrayList.removeLast();
        int endpositionfirst = endintegerArrayList.removeLast();
        int startpositionfirst = startintegerArrayList.removeLast();


        if(statusresult==3){
            if(status.size()>0){

                int statusresultsecond = status.getLast();

                if(statusresultsecond==1){
                    status.removeLast();
                    int endpositionsecond = endintegerArrayList.removeLast();
                    int startpositionsecond = startintegerArrayList.removeLast();
                    String removeStringsecond = stringArrayList.removeLast();
                    redostringArrayList.addFirst(removeStringsecond);
                    redostringArrayList.addFirst(removeString);
                    redostartintegerArrayList.addFirst(startpositionsecond);
                    redostartintegerArrayList.addFirst(startpositionfirst);
                    redoendintegerArrayList.addFirst(endpositionsecond);
                    redoendintegerArrayList.addFirst(endpositionfirst);
                    redostatus.addFirst(statusresultsecond);
                    redostatus.addFirst(statusresult);

                    deleteStringse(startpositionfirst,endpositionfirst,cursor);
                    addString(startpositionsecond,removeStringsecond,cursor);
                    resetcurrentNodeno(startpositionsecond);
                    double xstartpos = currentNode.textnode.getX()+currentNode.textnode.getLayoutBounds().getWidth();
                    double ystartpos = currentNode.textnode.getY();
                    resetcurrentNodeno(endpositionsecond);
                    double xendpos = currentNode.textnode.getX()+currentNode.textnode.getLayoutBounds().getWidth();
                    double yendpos = currentNode.textnode.getY();
                    resetrectangle(xstartpos,ystartpos,xendpos,yendpos,0);
                    System.out.println(redostatus);

                    returnresult = true;
                    return returnresult;
                    /*
                    need to change general state;
                     */





                }



            }
            redostatus.addFirst(statusresult);
            redoendintegerArrayList.addFirst(endpositionfirst);
            redostartintegerArrayList.addFirst(startpositionfirst);
            redostringArrayList.addFirst(removeString);
            deleteStringse(startpositionfirst,endpositionfirst,cursor);


        }
        if(statusresult==1){


            redostatus.addFirst(1);
            redostringArrayList.addFirst(removeString);
            redostartintegerArrayList.addFirst(startpositionfirst);
            redoendintegerArrayList.addFirst(endpositionfirst);
            addString(startpositionfirst,removeString,cursor);
            resetcurrentNodeno(startpositionfirst);
            double xposstartone = currentNode.textnode.getX()+currentNode.textnode.getLayoutBounds().getWidth();
            double yposstartone = currentNode.textnode.getY();
            resetcurrentNodeno(endpositionfirst);
            double xposendone = currentNode.textnode.getX()+currentNode.textnode.getLayoutBounds().getWidth();
            double yposendone = currentNode.textnode.getY();
            resetrectangle(xposstartone,yposstartone,xposendone,yposendone,0);
            /*
            change general state;
             */
            returnresult = true;
            return returnresult;
        }
        if(statusresult==2){
            redostatus.addFirst(2);
            redostringArrayList.addFirst(removeString);
            redostartintegerArrayList.addFirst(startpositionfirst);
            redoendintegerArrayList.addFirst(endpositionfirst);
            addString(startpositionfirst,removeString,cursor);
            resetcurrentNodeno(startpositionfirst);
            double xposstarttwo = currentNode.textnode.getX()+currentNode.textnode.getLayoutBounds().getWidth();
            double yposstarttwo = currentNode.textnode.getY();
            resetcurrentNodeno(endpositionfirst);
            double xposendtwo = currentNode.textnode.getX()+currentNode.textnode.getLayoutBounds().getWidth();
            double yposendtwo = currentNode.textnode.getY();
            resetrectangle(xposstarttwo,yposstarttwo,xposendtwo,yposendtwo,0);
            returnresult = true;
            return returnresult;






        }
    }
    return returnresult;
}

public boolean redo(Rectangle cursor){

    boolean returnresult = false;
    if(redostatus.size()==0){
        System.out.println("go to the subloop");
        return returnresult;
    }
    else {
        System.out.println("go to here,the redo beginning");
        int statusresult = redostatus.removeFirst();
        String removeString = redostringArrayList.removeFirst();

        int endpositionfirst = redoendintegerArrayList.removeFirst();
        int startpositionfirst = redostartintegerArrayList.removeFirst();
        if (statusresult == 3) {
            System.out.println("go to statusresult=3 step");
            if(redostatus.size()>0) {
                if(redostatus.getFirst()!=1) {


                    status.addLast(statusresult);
                    endintegerArrayList.addLast(endpositionfirst);
                    startintegerArrayList.addLast(startpositionfirst);
                    stringArrayList.addLast(removeString);
                }
            }
            if(redostatus.size()==0){
                status.addLast(statusresult);
                endintegerArrayList.addLast(endpositionfirst);
                startintegerArrayList.addLast(startpositionfirst);
                stringArrayList.addLast(removeString);
            }
            if (redostatus.size() > 0) {
                int statusresultsecond = redostatus.getFirst();
                if (statusresultsecond == 1) {
                    redostatus.removeFirst();
                    int endpositionsecond = redoendintegerArrayList.removeFirst();
                    int startpositionsecond = redostartintegerArrayList.removeFirst();
                    String removeStringsecond = redostringArrayList.removeFirst();

                    stringArrayList.addLast(removeStringsecond);
                    stringArrayList.addLast(removeString);
                   startintegerArrayList.addLast(startpositionsecond);
                    startintegerArrayList.addLast(startpositionfirst);
                    endintegerArrayList.addLast(endpositionsecond);
                    endintegerArrayList.addLast(endpositionfirst);
                    status.addLast(statusresultsecond);
                   status.addLast(statusresult);



                   deleteStringse(startpositionsecond,endpositionsecond,cursor);






                }

            }


            addString(startpositionfirst,removeString,cursor);
            resetcurrentNodeno(startpositionfirst);
            double xstartpos = currentNode.textnode.getX()+currentNode.textnode.getLayoutBounds().getWidth();
            double ystartpos = currentNode.textnode.getY();
            resetcurrentNodeno(endpositionfirst);
            double xendpos = currentNode.textnode.getX()+currentNode.textnode.getLayoutBounds().getWidth();
            double yendpos = currentNode.textnode.getY();
            resetrectangle(xstartpos,ystartpos,xendpos,yendpos,0);
            System.out.println(status);

            returnresult = true;
            return returnresult;


        }
        if(statusresult == 1||statusresult==2){

            status.addLast(statusresult);
            endintegerArrayList.addLast(endpositionfirst);
            startintegerArrayList.addLast(startpositionfirst);
            stringArrayList.addLast(removeString);
            deleteStringse(startpositionfirst,endpositionfirst,cursor);



        }

        }
     return returnresult;
    }

    public void clearredo(){
    int size = redoendintegerArrayList.size();
    for(int i=0;i<=size-1;i++){
        redoendintegerArrayList.removeLast();
        redostartintegerArrayList.removeLast();
        redostringArrayList.removeLast();
        redostatus.removeLast();
    }
    }


}







