package cr.ac.itcr.Cartas.Stack;

/**
 * Esta clase es una estructura de datos naturaleza ListaCircular Doblemente enlazada
 * que almacena elementos de un tipo determinado
 * @param <T> elemento que almacena la lista
 */
public class ListaCircular<T> {
    private Node head = null;
    private Node tail = null;
    private int length = -1;

    public int getLength() {
        return length;
    }

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
            if (current.value.equals(element)){
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
    public T getElementAt(int position){
        Node<T> current = this.head;
        Node<T> prev = null;
        while (position > 0){
            prev = current;
            current = current.getNext();
            if (current == null){
                break;
            }
            position --;
        }
        return current.getValue();
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
