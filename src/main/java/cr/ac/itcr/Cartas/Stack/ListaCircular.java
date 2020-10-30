package cr.ac.itcr.Cartas.Stack;

public class ListaCircular<T> {
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
