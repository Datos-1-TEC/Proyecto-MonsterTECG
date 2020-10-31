package cr.ac.itcr.Cartas.Stack;

public class ListaCircular<T> {
    private Node head = null;
    private Node tail = null;
    private int length = 0;

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
        length ++;
    }
    public void pushFront(T newElement){
        Node newNode = new Node();
        newNode.value = newElement;
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
                newNode.prev = temp;
                newNode.next = head;
                head.prev = newNode;
                head = newNode;
            }
        }
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
            System.out.printf("|%s|-> ", current.getValue());
            current = current.getNext();
            cont++;
        }
        System.out.println();
    }

}
