package cr.ac.itcr.Cartas.Stack;

public class ListaCircular<T> {
    private Node head = null;
    private Node tail = null;
    private int length = 0;

    //Insert elements

    public void pushFront(T newElement){
        Node newNode = new Node();
        newNode.value = newElement;
        if (head == null) {
            head = newNode;
            head.next = head;
            newNode.prev = tail;
            tail = newNode;
            length ++;
        }
        tail.next = newNode;
        newNode.next = head;
        newNode.prev = tail;
        tail = newNode;
        head.prev = tail;
        length ++;
    }
    public void deleteNode(T element){
        Node<T> current = new Node<T>();
        Node<T> predecessor = new Node<T>();
        current = head;
        predecessor = tail;
        do{
            if (current.value == element){
                if(current == head){
                    head = head.next;
                    tail.next = head;
                    head.prev = tail;
                    length --;
                }
                else if (current == tail){
                    tail = predecessor;
                    head.prev = tail;
                    tail.next = head;
                    length --;

                }
                else {
                    predecessor.next = current.next;
                    current.next.prev = predecessor;
                    length --;
                }
            }
            predecessor = current;
            current = current.next;

        }while (current != head);

    }
    public void pushBack(T newElement){
        Node newNode = new Node();
        newNode.data = newElement;
        newNode.prev = null;
        newNode.next = null;

        if (head == null) {
            head = newNode;
            newNode.next = head;
            newNode.prev = head;
        } else {
            Node temp = new Node();
            temp = head;
            while (temp.next != head){
                temp = temp.next;
                temp.next = newNode;
                newNode.next = head;
                newNode.prev = temp;
                head.prev = newNode;
            }

        }
    }

    public void popBack() {
        if (this.head != null) {
            if (this.head.next == this.head) {
                this.head = null;
            } else {
                Node temp = new Node();
                temp = this.head;
                while (temp.next.next != this.head) {
                    temp = temp.next;
                }
                Node lastNode = temp.next;
                temp.next = this.head;
                this.head.prev = temp;
                lastNode = null;
            }
        }
    }

    //Insertar elementos en lista doblemente circular
    public void insertEnd(T value){
        if (head == null){
            Node newNode = new Node();
            newNode.value = value;
            newNode.next = newNode.prev = newNode;
            head = newNode;
            return;
        }
        Node last = (head).prev;

        Node newNode = new Node();
        newNode.value = value;

        newNode.next = head;

        (head).prev = newNode;
        newNode.prev = last;
        last.next = newNode;
        length ++;
    }

    public void insertBegin(T value){
        Node last = head.prev;
        Node newNode = new Node();
        newNode.value = value;

        newNode.next = head;
        newNode.prev = last;

        last.next = newNode;
        head.prev = last.next;

        head = newNode;
        length ++;
    }

    public void insertAfter(T value1, T value2){
        Node newNode = new Node();
        newNode.value = value1;

        Node temp = head;
        while (temp.value != value2) {
            temp = temp.next;
            Node next = temp.next;

            temp.next = newNode;
            newNode.prev = temp;
            newNode.next = next;
            next.prev = newNode;

        }
        length ++;
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
    public void print(){
        Node<T> current = this.head;
        int cont = 0;
        while (cont < length){
            System.out.printf("<-|%s|-> ", current.getValue());
            current = current.getNext();
            cont++;
        }
        System.out.println();
    }

}
