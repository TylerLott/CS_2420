
public class LeftistQueue<E extends Comparable<E>>{

    private LeftistNode root;

    public LeftistQueue(){
        root = null;
    }


    public boolean isEmpty(){
        return root == null;
    }

    public void enqueue(E ele){
        root = merge(new LeftistNode(ele), root);
    }

    public LeftistNode dequeue(){
        if (root == null){
            return null;
        }
        LeftistNode min = root;
        root = merge(root.left, root.right);
        return min;
    }

    private void merge(LeftistQueue right){
        if (this == right){
            return;
        }
        root = merge(root, right.root);
        right.root = null;
    }

    private LeftistNode merge(LeftistNode a, LeftistNode b){
        if (a == null){
            return b;
        } if (b == null){
            return a;
        } if (a.element.compareTo(b.element) >= 0){
            LeftistNode temp = a;
            a = b;
            b = temp;
        }
        a.right = merge(a.right, b);

        if (a.left == null){
            a.left = a.right;
            a.right = null;
        } else {
            if (a.left.dist < a.right.dist){
                LeftistNode temp = a.left;
                a.left = a.right;
                a.right = temp;
            }
            a.dist = a.right.dist + 1;
        }
        return a;
    }
}

class LeftistNode<E extends Comparable<E>>{
    E element;
    LeftistNode left;
    LeftistNode right;
    int dist;

    public LeftistNode(E element){
        this.element = element;
        this.left = null;
        this.right = null;
    }

    public LeftistNode(E element, LeftistNode left, LeftistNode right){
        this.element = element;
        this.left = left;
        this.right = right;
        this.dist = 0;
    }

}
