public class Node {
    private Node next;
    private Object data;

    public Node(Object value){
        next = null;
        data = value;
    }

    public Node(Object value, Node nextNode){
        next = nextNode;
        data = value;
    }

    public Object getData(){
        return data;
    }
    public Node getNext(){
        return next;
    }
    public void setNext(Node nextNode){
        next = nextNode;
    }
}
