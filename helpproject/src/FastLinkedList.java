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


public class FastLinkedList {
    /**
    public  class Node{
        public Text textnode;
        public double xpos;
        public double ypos;
        public int linenum;
        public Node prev;
        public Node next;


        public Node(Text textnode, Node prev, Node next,int linenum,double xpos, double ypos){
            this.textnode = textnode;
            this.xpos = xpos;
            this.ypos = ypos;
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
            this.linenum = linenum;
        }

        public Node returnprev(){
            return this.prev;
        }
        public Node returnnext(){
            return this.next;
        }
    }
     **/

    private Node sentinel;
    private int currentposition;
    private ArrayList<Node> arrayList;

    private Node currentNode;
    private double genwidth;
    private double marginx;
    private double marginy;
    private int size;
    public FastLinkedList(){
        Text sentineltext = new Text(0,10,"z");
        sentineltext.setFont(Font.font("Verdana",12));
        sentinel = new Node(sentineltext,sentinel,sentinel,0,0,marginy);

        currentposition = 0;
        arrayList =new ArrayList<>();
        currentNode = sentinel;
        arrayList.add(sentinel);
        genwidth = 500;
        marginx = 1;
        marginy = 15;
        size = 0;
        System.out.println(currentposition);
    }

    public void addChar(char x,Group root){

         double xpos = calculatexpos(currentNode,genwidth,x,marginx);
         double ypos = calculateypos(currentNode,genwidth,x,marginx,marginy);


         Text newtext = new Text(xpos,ypos,Character.toString(x));
         newtext.setTextOrigin(VPos.TOP);
         newtext.setText(Character.toString(x));
         newtext.setFont(Font.font("Verdana",12));

         int linenum;

         if(xpos==marginx){
         linenum = currentNode.linenum + 1;

         }
         else{
         linenum = currentNode.linenum;

         }

         Node newNode = new Node(newtext,currentNode,currentNode.next,linenum,xpos,ypos);
         currentNode.next.prev = newNode;
         currentNode.next = newNode;
         currentNode = newNode;

         if(xpos==marginx){
         if(arrayList.size() <= linenum){
         Node newNodearray = currentNode;
         arrayList.add(newNodearray);
         }
         else{
             Node newNodearray = currentNode;
             arrayList.set(linenum,newNodearray);
             updateaddcharchangeline(genwidth, marginx, marginy);
         }
         }
         else {

             updateaddcharchangeline(genwidth, marginx, marginy);
         }




         root.getChildren().add(currentNode.textnode);
         size++;







    }


    public void deleteChar(Group root){
        root.getChildren().remove(currentNode.textnode);

         Node p = currentNode;
         currentNode.next.prev = currentNode.prev;
         currentNode= currentNode.prev;
         currentNode.next = p.next;

         p.prev = null;
         p.next = null;
         size --;






    }

    public ArrayList returnlist(){
        return this.arrayList;
    }


    public boolean addchangeline(double genwidth,char x,double marginx){
        return calculatexpos(currentNode,genwidth,x,marginx)==marginx;


    }


    public void updateaddcharchangeline(double genewidth,double marginx,double marginy) {

        double xpos;
        double ypos;

        int linestartnum = currentNode.linenum;
        Node startNode = currentNode;
        if(startNode.next != sentinel) {
            startNode = startNode.next;

            while (startNode.linenum == linestartnum ) {
                xpos = startNode.xpos + currentNode.textnode.getLayoutBounds().getWidth() + marginx;
                if (xpos + startNode.textnode.getLayoutBounds().getWidth() < genewidth) {
                    startNode.xpos = xpos;
                } else {
                    startNode.linenum = linestartnum + 1;
                    startNode.xpos = marginx;
                    startNode.ypos = startNode.ypos+marginy;




                     if(arrayList.size()<=linestartnum+1){
                     arrayList.add(startNode);
                     }



                }
                startNode = startNode.next;
                if(startNode == sentinel){
                    break;
                }

            }
            if (arrayList.size() > linestartnum + 1) {
                for (int i = linestartnum + 1; i < arrayList.size(); i++) {
                    if (arrayList.get(i).prev.linenum == i) {

                        while(arrayList.get(i).prev.linenum==i){
                            arrayList.set(i,arrayList.get(i).prev);
                        }


                        System.out.println(arrayList.get(i).textnode);

                        Node startnode = arrayList.get(i);


                        startnode = startnode.next;
                        double length = arrayList.get(i).textnode.getLayoutBounds().getWidth()+marginx;;


                        while (startnode.linenum == i) {


                            if(startnode.next.xpos==marginx){
                                xpos = startnode.xpos + length ;
                                length = length + startnode.textnode.getLayoutBounds().getWidth()+marginx;
                            }
                            else {


                                xpos = startnode.xpos + length;

                            }


                            System.out.println(startnode.textnode);
                            System.out.println(startnode.xpos);
                            System.out.println(arrayList.get(i).textnode.getLayoutBounds().getWidth());
                            System.out.println(xpos);
                            if (xpos + startnode.textnode.getLayoutBounds().getWidth() < genewidth) {
                                startnode.xpos = xpos;
                                startnode.ypos = arrayList.get(i).ypos;

                            } else {
                                startnode.linenum = i + 1;
                                startnode.xpos = marginx;
                                startnode.ypos = startnode.ypos + marginy;

                                if(arrayList.size() <= i+1){
                                    arrayList.add(startnode);
                                }


                            }
                            startnode = startnode.next;



                        }

                    }
                }


            }
        }
    }


    public double calculatexpos(Node n,double genwidth,char x,double marginx){




        if(n.xpos+n.textnode.getLayoutBounds().getWidth()+marginx+getwidth(x) > genwidth||Character.toString(x)=="\n"){
         return marginx;}
         else{
         return n.xpos+n.textnode.getLayoutBounds().getWidth()+marginx;
         }



    }
    public double getwidth(char x){
        Text checktext = new Text(100,100,Character.toString(x));
        checktext.setFont(Font.font("Verdana",12));
        return checktext.getLayoutBounds().getWidth();
    }
    public double getHeight(char x){
        Text checktext = new Text(100,100,Character.toString(x));
        checktext.setFont(Font.font("Verdana",12));
        return checktext.getLayoutBounds().getHeight();
    }

    public double calculateypos(Node n, double genwidth, char x,double marginx,double marginy){

        if (calculatexpos(n,genwidth,x,marginx)==marginx){
         return n.ypos+marginy;}
         else{
         return n.ypos;
         }

    }

    private double getcursorx(Rectangle cursor){
        return cursor.getX();
    }

    private double getcursory(Rectangle cursor){
        return cursor.getY();
    }

    private int linecursor(Rectangle cursor){
        return (int) (getcursory(cursor)/marginy);

    }

    public void resetcurrentNode(Rectangle cursor){
        double xpos = getcursorx(cursor);
        double ypos = getcursory(cursor);
        int linenum =linecursor(cursor);
        if(linenum <= arrayList.size()-1){
            Node startnode = arrayList.get(linenum);
            while(startnode.xpos <=xpos ){
                startnode = startnode.next;
            }
            currentNode = startnode;
        }
    }

    public void display(){
        Node startnode = sentinel.next;
       if(size >=1)
        for(int i=0;i<=size-1;i++){

            startnode.textnode.setX(startnode.xpos);
            startnode.textnode.setY(startnode.ypos);
            startnode.textnode.toFront();
            startnode = startnode.next;
        }
    }













}
