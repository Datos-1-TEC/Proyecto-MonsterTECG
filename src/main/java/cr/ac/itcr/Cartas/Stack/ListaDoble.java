package cr.ac.itcr.Cartas.Stack;

public class ListaDoble<T> {
    private Node head = null;
    private Node tail = null;

    public void ingresarNodo(T newElement){
        Node newNode = new Node();
        newNode.value = newElement;

        if (head == null){
            head = newNode;
            head.next = null;
            head.prev = null;
            tail = head;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            newNode.next = null;
            tail = newNode;
        }
    }
    public boolean buscarNodo(T searchValue){
        Node current = new Node();
        current = head;
        while (current != null){
            if (current.value == searchValue) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
}
