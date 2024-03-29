// ******************ERRORS********************************
// Throws UnderflowException as appropriate

import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;

class UnderflowException extends RuntimeException {
    /**
     * Construct this exception object.
     * @param message the error message.
     */
    public UnderflowException(String message) {
        super(message);
    }
}

public class Tree<E extends Comparable<? super E>> {
    final String ENDLINE = "\n";
    private BinaryNode<E> root;  // Root of tree
    private BinaryNode<E> curr;  // Last node accessed in tree
    private String treeName;  // Name of tree
    /**
     * Create an empty tree
     *
     * @param label Name of tree
     */
    public Tree(String label) {
        treeName = label;
        root = null;
    }

    /**
     * Create non ordered tree from list in preorder
     * @param arr    List of elements
     * @param label  Name of tree
     * @param height Maximum height of tree
     */
    public Tree(ArrayList<E> arr, String label, int height) {
        this.treeName = label;
        root = buildTree(arr, height, null);
    }

    /**
     * Create BST
     * @param arr   List of elements to be added
     * @param label Name of tree
     */
    public Tree(ArrayList<E> arr, String label) {
        root = null;
        treeName = label;
        for (int i = 0; i < arr.size(); i++) {
            bstInsert(arr.get(i));
        }
    }


    /**
     * Create BST from Array
     * @param arr   List of elements to be added
     * @param label Name of  tree
     */
    public Tree(E[] arr, String label) {
        root = null;
        treeName = label;
        for (int i = 0; i < arr.length; i++) {
            bstInsert(arr[i]);
        }
    }

    /**
     * Change name of tree
     * @param name new name of tree
     */
    public void changeName(String name) {
        this.treeName = name;
    }

    /**
     * Return a string displaying the tree contents as a tree with one node per line
     */
    public String toString() {
        if (root == null)
            return (treeName + " Empty tree\n");
        else {
            stringTraversal(root);
        }
        return " ";
    }

    public void stringTraversal(BinaryNode<E> node){
            if (node.left == null && node.right == null) {
                System.out.println(node.element.toString() + "[" + node.parent.element.toString() + "]");
            }
            if (node.right != null) {
                stringTraversal(node.right);
                if (node.parent != null) {
                    System.out.println(node.element.toString() + "[" + node.parent.element.toString() + "]");
                } else {
                    System.out.println(node.element.toString() + "[no parent]");
                }
            }
            if (node.left != null) {
                if (node.parent != null && node.right == null) {
                    System.out.println(node.element.toString() + "[" + node.parent.element.toString() + "]");
                }
                stringTraversal(node.left);
            }


    }

    /**
     * Return a string displaying the tree contents as a single line
     */
    public String toString2() {
        if (root == null)
            return treeName + " Empty tree";
        else
            return treeName + " " + toString2(root);
    }

    /**
     * reverse left and right children recursively
     */
    public void flip() {
         flipTraversal(root);
    }

    public void flipTraversal(BinaryNode<E> node){
        if (node.right != null){
            flipTraversal(node.right);
            if (node.left != null){
                flipTraversal(node.left);
            }
            BinaryNode<E> temp = node.right;
            node.right = node.left;
            node.left = temp;
        }

    }

    /**
     * Find successor of "curr" node in tree
     * @return String representation of the successor
     */
    public String successor() {
        if (curr == null) curr = root;
        curr = successor(curr);
        if (curr == null) return "null";
        else return curr.toString();
    }

    private BinaryNode<E> successor(BinaryNode<E> node){
        if (node.right != null) {
            BinaryNode<E> current = node.right;
            while (current.left != null) {
                current = current.left;
            }
            return current;
        }
        BinaryNode<E> parent = node.parent;
        while (parent != null && node == parent.right){
            node = parent;
            parent = parent.parent;
        }
        return parent;
    }

    /**
     * Counts number of nodes in specifed level
     * @param level Level in tree, root is zero
     * @return count of number of nodes at specified level
     */
    public int nodesInLevel(int level) {
        return nodesInLevel(root, level, 0);
    }

    private int nodesInLevel(BinaryNode<E> node, int stopLevel, int currLevel){
        if (node == null) {
            return 0;
        }
        if (stopLevel == currLevel){
            return 1;
        }
        return nodesInLevel(node.left, stopLevel, currLevel + 1) +
                nodesInLevel(node.right, stopLevel, currLevel + 1);
    }

    /**
     * Print all paths from root to leaves
     */
    public void printAllPaths() {
        ArrayList<Integer> pathList = new ArrayList<>();
        printAllPaths(root, pathList, 0);
    }

    private void printAllPaths(BinaryNode<E> node, ArrayList<Integer> path, int height){
        if (node == null){
            return;
        }
        while (path.size() > height){
            path.remove(path.size() - 1);
        }
        path.add((Integer) node.element);
        height++;
        if (node.left == null && node.right == null){
            System.out.println(path);
        } else {
            printAllPaths(node.left, path, height);
            printAllPaths(node.right, path, height);
        }
    }


    /**
     * Print contents of levels in zig zag order starting at maxLevel
     * @param maxLevel
     */
    public void byLevelZigZag(int maxLevel) {
        System.out.println(byLevelZigZag(root, maxLevel, 0));
    }

    private String byLevelZigZag(BinaryNode<E> node, int max, int cur){
        if (node == null) {
            return "";
        }
        if (max == cur){
            return node.element.toString() + " ";
        }
        return byLevelZigZag(node.right, max, cur + 1) + byLevelZigZag(node.left, max, cur + 1);
    }

    /**
     * Counts all non-null binary search trees embedded in tree
     * @return Count of embedded binary search trees
     */
    public Integer countBST() {
        if (root == null) return 0;
        return countBST(root);
    }

    private Integer countBST(BinaryNode<E> node){
        return 0;
    }

    /**
     * Insert into a bst tree; duplicates are allowed
     * @param x the item to insert.
     */
    public void bstInsert(E x) {
        root = bstInsert(x, root, null);
    }

    /**
     * Determines if item is in tree
     * @param item the item to search for.
     * @return true if found.
     */
    public boolean contains(E item) {

        return bstContains(item, root);
    }

    /**
     * Remove all paths from tree that sum to less than given value
     * @param sum: minimum path sum allowed in final tree
     */
    // complexity O(n)
    public void pruneK(Integer sum) {
        pruneRecursive((BinaryNode<Integer>) root, sum);
    }
    private BinaryNode<Integer> pruneRecursive(BinaryNode<Integer> node, int sum){
        if (node == null){
            return null;
        }

        node.left = pruneRecursive(node.left, sum - node.element);
        node.right = pruneRecursive(node.right, sum - node.element);

        if (node.left == null && node.right == null){
            if (sum > node.element){
                node = null;
            }
        }
        return node;
    }

    /**
     * Build tree given inOrder and preOrder traversals.  Each value is unique
     * @param inOrder  List of tree nodes in inorder
     * @param preOrder List of tree nodes in preorder
     */
    public void buildTreeTraversals(E[] inOrder, E[] preOrder) {
        int i = 0;
        buildTreeTraversals(inOrder, preOrder, 0, inOrder.length - 1, 0);
    }
    private BinaryNode<E> buildTreeTraversals(E[] in, E[] pre, int a, int b, int index){
        if (a > b){
            return null;
        }
        BinaryNode<E> node = new BinaryNode<>(pre[index++]);

        if (a == b){
            return node;
        }

        int inOrder = find(in, node.element);

        node.left = buildTreeTraversals(in, pre, a,  inOrder - 1, index);
        node.right = buildTreeTraversals(in, pre, inOrder + 1, b, index);

        return node;
    }

    private int find(E[] a, E thing){
        for (int i=0;i<a.length;i++){
            if (a[i].equals(thing)){
                return i;
            }
        }
        return -1;
    }


    /**
     * Find the least common ancestor of two nodes
     * @param a first node
     * @param b second node
     * @return String representation of ancestor
     */
    public String lca(E a, E b) {
        BinaryNode<E> ancestor = null;
//        if (a.compareTo(b) < 0) {
//            ancestor = lca(root, a, b);
//        } else {
//            ancestor = lca(root, b, a);
//        }
        if (ancestor == null) return "none";
        else return ancestor.toString();
    }

    /**
     * Balance the tree
     */
    // complexity O(n)
    public void balanceTree() {
        ArrayList<BinaryNode<E>> inOrderTrav = new ArrayList<>();
        inOrderTraversal(root, inOrderTrav);
        root = null;
        System.out.println(inOrderTrav);
        balanceTree(inOrderTrav, 0, inOrderTrav.size() - 1, null);
    }
    private void inOrderTraversal(BinaryNode<E> node, ArrayList<BinaryNode<E>> arr){
        if (node == null){
            return;
        }
        inOrderTraversal(node.left, arr);
        arr.add(node);
        inOrderTraversal(node.right, arr);
    }

    private BinaryNode<E> balanceTree(ArrayList<BinaryNode<E>> trav, int a, int b, BinaryNode<E> parent){
        if (a > b){
            return null;
        }
        int middle = (a+b)/2;
        BinaryNode<E> node = new BinaryNode<E>(trav.get(middle).element, null, null, parent);

        if (root == null){
            root = node;
            root.parent = null;
        }

        node.left = balanceTree(trav, a, middle - 1, node);
        node.right = balanceTree(trav, middle + 1, b, node);

        return node;
    }


    /**
     * In a BST, keep only nodes between range
     * @param a lowest value
     * @param b highest value
     */
    // complexity O(n)
    public void keepRange(int a, int b) {
        keepRange((BinaryNode<Integer>) root, a, b);
     }

     private BinaryNode<Integer> keepRange(BinaryNode<Integer> node, int min, int max){
        if (node == null){
            return null;
        }

        node.right = keepRange(node.right, min, max);
        node.left = keepRange(node.left, min, max);

        if (node.element < min){
            BinaryNode<Integer> childRight = node.right;
            node = null;
            return childRight;
        }
         if (node.element > max){
             BinaryNode<Integer> childLeft = node.left;
             node = null;
             return childLeft;
         }
         return node;
    }

    //PRIVATE

     /**
     * Build a NON BST tree by preorder
     *
     * @param arr    nodes to be added
     * @param height maximum height of tree
     * @param parent parent of subtree to be created
     * @return new tree
     */
    private BinaryNode<E> buildTree(ArrayList<E> arr, int height, BinaryNode<E> parent) {
        if (arr.isEmpty()) return null;
        BinaryNode<E> curr = new BinaryNode<>(arr.remove(0), null, null, parent);
        if (height > 0) {
            curr.left = buildTree(arr, height - 1, curr);
            curr.right = buildTree(arr, height - 1, curr);
        }
        return curr;
    }

    /**
     * Internal method to insert into a subtree.
     * In tree is balanced, this routine runs in O(log n)
     *
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<E> bstInsert(E x, BinaryNode<E> t, BinaryNode<E> parent) {
        if (t == null)
            return new BinaryNode<>(x, null, null, parent);

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0) {
            t.left = bstInsert(x, t.left, t);
        } else {
            t.right = bstInsert(x, t.right, t);
        }

        return t;
    }


    /**
     * Internal method to find an item in a subtree.
     * This routine runs in O(log n) as there is only one recursive call that is executed and the work
     * associated with a single call is independent of the size of the tree: a=1, b=2, k=0
     *
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     *          SIDE EFFECT: Sets local variable curr to be the node that is found
     * @return node containing the matched item.
     */
    private boolean bstContains(E x, BinaryNode<E> t) {
        curr = null;
        if (t == null)
            return false;

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0)
            return bstContains(x, t.left);
        else if (compareResult > 0)
            return bstContains(x, t.right);
        else {
            curr = t;
            return true;    // Match
        }
    }



    /**
     * Internal method to return a string of items in the tree in order
     * This routine runs in O(??)
     * @param t the node that roots the subtree.
     */
    private String toString2(BinaryNode<E> t) {
        if (t == null) return "";
        StringBuilder sb = new StringBuilder();
        sb.append(toString2(t.left));
        sb.append(t.element.toString() + " ");
        sb.append(toString2(t.right));
        return sb.toString();
    }

    // Basic node stored in unbalanced binary  trees
    private static class BinaryNode<AnyType> {
        AnyType element;            // The data in the node
        BinaryNode<AnyType> left;   // Left child
        BinaryNode<AnyType> right;  // Right child
        BinaryNode<AnyType> parent; //  Parent node

        // Constructors
        BinaryNode(AnyType theElement) {
            this(theElement, null, null, null);
        }

        BinaryNode(AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt, BinaryNode<AnyType> pt) {
            element = theElement;
            left = lt;
            right = rt;
            parent = pt;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Node:");
            sb.append(element);
            if (parent == null) {
                sb.append("<>");
            } else {
                sb.append("<");
                sb.append(parent.element);
                sb.append(">");
            }

            return sb.toString();
        }

    }


    // Test program
    public static void main(String[] args) {
        long seed = 436543;
        Random generator = new Random(seed);  // Don't use a seed if you want the numbers to be different each time
        final String ENDLINE = "\n";

        int val = 60;
        final int SIZE = 8;

        Integer[] v1 = {25, 10, 60, 55, 58, 56, 14, 63, 8, 50, 6, 9};

        ArrayList<Integer> v2 = new ArrayList<Integer>();
        for (int i = 0; i < SIZE * 2; i++) {
            int t = generator.nextInt(200);
            v2.add(t);
        }
        v2.add(val);
        Integer[] v3 = {200, 15, 3, 65, 83, 70, 90};
        ArrayList<Integer> v4 = new ArrayList<Integer>(Arrays.asList(v3));
        Integer[] v = {21, 8, 5, 6, 7, 19, 10, 40, 43, 52, 12, 60};
        ArrayList<Integer> v5 = new ArrayList<Integer>(Arrays.asList(v));
        Integer[] inorder = {4, 2, 1, 7, 5, 8, 3, 6};
        Integer[] preorder = {1, 2, 4, 3, 5, 7, 8, 6};


        Tree<Integer> tree1 = new Tree<Integer>(v1, "Tree1:");
        Tree<Integer> tree2 = new Tree<Integer>(v2, "Tree2:");
        Tree<Integer> tree3 = new Tree<Integer>(v3, "Tree3:");
        Tree<Integer> treeA = new Tree<Integer>(v4, "TreeA:", 2);
        Tree<Integer> treeB = new Tree<Integer>(v5, "TreeB", 3);
        Tree<Integer> treeC = new Tree<Integer>("TreeC");
//        System.out.println(tree1.toString());
//        System.out.println(tree1.toString2());
//
//        System.out.println(treeA.toString());
//
//        treeA.flip();
//        System.out.println("Now flipped" + treeA.toString());
//
//        System.out.println(tree2.toString());
//        tree2.contains(val);  //Sets the current node inside the tree6 class.
//        int succCount = 5;  // how many successors do you want to see?
//        System.out.println("In Tree2, starting at " + val + ENDLINE);
//        for (int i = 0; i < succCount; i++) {
//            System.out.println("The next successor is " + tree2.successor());
//        }
//
//        System.out.println(tree1.toString());
//        for (int mylevel = 0; mylevel < SIZE; mylevel += 2) {
//            System.out.println("Number nodes at level " + mylevel + " is " + tree1.nodesInLevel(mylevel));
//        }
//        System.out.println("All paths from tree1");
//        tree1.printAllPaths();
//
//        System.out.print("Tree1 byLevelZigZag (5): ");
//        tree1.byLevelZigZag(5);
//        System.out.print("Tree2 byLevelZigZag (3): ");
//        tree2.byLevelZigZag(3);
//        treeA.flip();
//        System.out.println(treeA.toString());
//        System.out.println("treeA Contains BST: " + treeA.countBST());
//
//        System.out.println(treeB.toString());
//        System.out.println("treeB Contains BST: " + treeB.countBST());
//
//        treeB.pruneK(60);
//        treeB.changeName("treeB after pruning 60");
//        System.out.println(treeB.toString());
//        treeA.pruneK(220);
//        treeA.changeName("treeA after pruning 220");
//        System.out.println(treeA.toString());
//
        treeC.buildTreeTraversals(inorder, preorder);
        treeC.changeName("Tree C built from inorder and preorder traversals");
        System.out.println(treeC.toString());
//
//        System.out.println(tree1.toString());
//        System.out.println("tree1 Least Common Ancestor of (56,61) " + tree1.lca(56, 61) + ENDLINE);
//
//        System.out.println("tree1 Least Common Ancestor of (6,25) " + tree1.lca(6, 25) + ENDLINE);
//        System.out.println(tree3.toString());
//        tree3.balanceTree();
//        tree3.changeName("tree3 after balancing");
//        System.out.println(tree3.toString());
//
//        System.out.println(tree1.toString());
//        tree1.keepRange(10, 50);
//        tree1.changeName("tree1 after keeping only nodes between 10 and 50");
//        System.out.println("tree1 after keeping only nodes between 10 and 50");
//        System.out.println(tree1.toString());
//        tree3.keepRange(3, 85);
//        tree3.changeName("tree3 after keeping only nodes between 3  and 85");
//        System.out.println("tree3 after keeping only nodes between 3  and 85");
//        System.out.println(tree3.toString());


    }

}
