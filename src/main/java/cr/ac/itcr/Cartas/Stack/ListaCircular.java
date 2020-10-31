package cr.ac.itcr.Cartas.Stack;
import java.util.*;

public class ListaCircular<T> {
    private static Node start;
    private Node head = null;
    private Node tail = null;

    //Insert elements
    public void addNode(T value) {
        Node newNode = new Node(value);

        if (head == null) {
            head = newNode;
        } else {
            tail.next = newNode;
        }

        tail = newNode;
        tail.next = head;
    }
    //Insertar elementos en lista doblemente circular
    public void insertEnd(T value){
        if (start == null){
            Node newNode = new Node();
            newNode.data = value;
            newNode.next = newNode.prev = newNode;
            start = newNode;
            return;
        }
        Node last = (start).prev;

        Node newNode = new Node();
        newNode.data = value;

        newNode.next = start;
        (start).prev = newNode;
        newNode.prev = last;
        last.next = newNode;
    }

    public void insertBegin(T value){
        Node last = (start).prev;
        Node newNode = new Node();
        newNode.data = value;

        newNode.next = start;
        newNode.prev = last;

        last.next = (start).prev = newNode;

        start = newNode;
    }

    public void insertAfter(T value1, T value2){
        Node newNode = new Node();
        newNode.data = value1;

        Node temp = start;
        while (temp.data != value2) {
            temp = temp.next;
            Node next = temp.next;

            temp.next = newNode;
            newNode.prev = temp;
            newNode.next = next;
            next.prev = newNode;
        }
    }


    public boolean containsNode(T searchValue) {
        Node currentNode = head;

        if (head == null) {
            return false;
        } else {
            do {
                if (((Node<T>) currentNode).value == searchValue) {
                    return true;
                }
                currentNode = currentNode.next;
            } while (currentNode != head);
            return false;
        }
    }







}
