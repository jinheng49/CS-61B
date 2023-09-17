/** second part of project1A.
 * deque implemented by array
 * @author JinHeng
 * Inspired by Flying Pig
 */

public  class ArrayDeque<T>{

    /*Array to save the data*/
    private T[] array;
    /*The size of my data*/
    private int size;
    /*the length of the array*/
    private  int length;
    /*The first index*/
    private int front;
    /*The last*/
    private  int last;

    public ArrayDeque(){
        array = (T[]) new Object[8];
        size = 0;
        length = 8;
        front = 4;
        last = 4;
    }
    /*Examine the array if empty*/
    public boolean isEmpty(){
        return size == 0;
    }
    /*return the size*/
    public int getSize(){
        return size;
    }
    /*return the minus 1 when we need the pre of front*/
    public int minusone(int index){
        if(index == 0){
            return length - 1;/*The array is a circle*/
        }
        return index - 1;
    }
    /*return the plus 1 when we need the next of the last*/
    public int plusone(int index,int module){
        index %= module;
        if(index == module - 1){
            return 0;
        }
        return index + 1;
    }
    public void grow(){
        T[] newArray = (T[]) new Object[length * 2];
        int ptr1 = front;
        int ptr2 = length;
        while (ptr1 != length){
            newArray[ptr2] = array[ptr1];
            ptr1 = plusone(ptr1, length);
            ptr2 = plusone(ptr2, length * 2);
        }
        front = length;;
        last = ptr2;
        length *= 2;
        array = newArray;
    }

    private void shrink() {
        T[] newArray = (T[]) new Object[length / 2];
        int ptr1 = front;
        int ptr2 = length / 4;
        while (ptr1 != last) {
            newArray[ptr2] = array[ptr1];
            ptr1 = plusone(ptr1, length);
            ptr2 = plusone(ptr2, length / 2);
        }
        front = length / 4;
        last = ptr2;
        array = newArray;
        length /= 2;
    }
    public void  addFirst(T item){
        if(size == length){
            grow();
        }
        front = minusone(front);
        array[front] = item;
        size++;
    }
    public void addLast(T item) {
        if (size == length - 1) {
            grow();
        }
        array[last] = item;
        last = plusone(last, length);
        size++;
    }
    public T removeLast() {
        if (length >= 16 && length / size >= 4) {
            shrink();
        }
        if (size == 0) {
            return null;
        }
        last = minusone(last);
        size--;
        return array[last];
    }
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        int ptr = front;
        for (int i = 0; i < index; i++) {
            ptr = plusone(ptr, length);
        }
        return array[ptr];
    }

    public void printDeque() {
        int ptr = front;
        while (ptr != last) {
            System.out.print(array[ptr] + " ");
            ptr = plusone(ptr, length);
        }
    }



}



























