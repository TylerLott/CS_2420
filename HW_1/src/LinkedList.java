public class LinkedList {
    private Node head;

    // default constructor
    public LinkedList(){
        head = new Node(null);
    }

    public LinkedList(Object data){
        head = new Node(data, null);
    }

    public void add(Object data){

        if (head == null) {
            head = new Node(data);
        }

        Node temp = new Node(data);
        Node current = head;

        if (current != null) {
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(temp);
        }
    }

    public Node dequeue(){
        Node oldHead = head;
        if (this.getLength() > 1) {
            Node newHead = head.getNext();
            head = newHead;
        }
        return oldHead;
    }

    public int getLength(){
        int count = 1;
        Node current = head;
        while (current.getNext() != null) {
            current = current.getNext();
            count++;
        }
        return count;
    }

    public Node getHead(){
        return head;
    }
}

