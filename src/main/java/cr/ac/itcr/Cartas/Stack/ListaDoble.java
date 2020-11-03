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
    public void eliminarNodo(T Element){
        Node current = new Node();
        Node predecessor = new Node();
        current = head;
        predecessor = tail;
        while (current != null){
            if (current.value == Element){
                if (current == head){
                    head = head.next;
                    head.prev = null;
                }else {
                    predecessor.next = current.next;
                    current.next.prev = current.prev;
                }
            }
            predecessor = current;
            current = current.next;
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
