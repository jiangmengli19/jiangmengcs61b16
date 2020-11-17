import javafx.scene.text.Text;

public  class Node{
    public Text textnode;
    public double xpos;
    public double ypos;
    public int linenum;
    public Node prev;
    public Node next;


    public Node(Text textnode, Node prev, Node next, int linenum, double xpos, double ypos){
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