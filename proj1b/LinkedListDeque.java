/** first part of project1A
 *  Deque Implemented by Linked List
 * @author JinHeng
 */
public class LinkedListDeque<T>implements Deque<T> {
    public class Node {
        /**The item stored in the Node*/
        public T item;
        /* The pre Node*/
        public Node pre;
        /*The next Node*/
        public Node next;

        public Node(T i, Node p, Node n) {
            item = i;
            pre = p;
            next = n;
        }
        /** constructor for Node.(especially for sentinel node). */
        public Node(Node ppre, Node nnext) {
            pre = ppre;
            next = nnext;
        }
    }


    private int size;
    /* The first item (if it exists) is at sentinel.next. */
    private Node sentinel;

    public LinkedListDeque() {
        sentinel = new Node( null, null);
        sentinel.pre = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }
    /*Adds an item to the front of the Deque*/
    @Override
    public void addFirst(T item){
        Node newnode = new Node(item,sentinel,sentinel.next);
        sentinel.next = newnode;
        sentinel.next.pre = newnode;
        size++;
    }
    /*Adds an item to the front of the Deque*/
    @Override
    public void addLast(T item){
        Node newnode = new Node(item,sentinel.pre,sentinel);
        sentinel.pre.next = newnode;
        sentinel.pre = newnode;
        size++;
    }
    /*Remove the first*/
    @Override
    public T removeFirst(){
        if(size==0)
            return null;
        Node temp = sentinel.next;
        sentinel.next=sentinel.next.next;
        sentinel.next.next.pre=sentinel;
        size--;
        return temp.item;
    }
    /*Remove the last*/
    @Override
    public T removeLast(){
        if(size==0)
            return null;
        Node temp = sentinel.pre;
        sentinel.pre=sentinel.pre.pre;
        sentinel.pre.pre.next=sentinel;
        size--;
        return temp.item;
    }
    /* Check if the dueqe is empty,if yes,return ture*/
    @Override
    public boolean isEmpty(){
        return size==0;
    }
    /*return the size*/
    public int size(){
        return size;
    }
    /* Prints the items in the deque from first to last, separated by a space.*/
    @Override
    public void printDeque(){
        Node n = sentinel.next;
        while(n !=sentinel){
            System.out.print(n.item + " ");
            n=n.next;
        }
    }
    /*Gets the item at the given index*/
    @Override
    public T get(int index){
        if(index >= size){
            return null;
        }
        Node n=sentinel;
        for(int i=0;i<=index;i++){
            n=n.next;
        }
        return n.item;

    }
    /*Same as get, but uses recursion.*/
    private T getRecursiveHelp(Node start, int index) {
        if (index == 0) {
            return start.item;
        } else {
            return getRecursiveHelp(start.next, index - 1);
        }
    }
    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return getRecursiveHelp(sentinel.next, index);
    }





}