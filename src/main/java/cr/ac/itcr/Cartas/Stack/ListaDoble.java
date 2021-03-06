package cr.ac.itcr.Cartas.Stack;

/**
 * Clase que se utiliza para crear una lista de elementos que estan doblemente enlazados
 * @param <T>
 */
public class ListaDoble<T> {
    private Node head = null;
    private Node tail = null;

    public int getLength() {
        return length;
    }

    private int length = 0;

    public void ingresarNodo(T newElement){
        Node newNode = new Node();
        newNode.value = newElement;

        if (head == null){
            head = newNode;
            head.next = null;
            head.prev = null;
            tail = head;
            length ++;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            newNode.next = null;
            tail = newNode;
            length ++;
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
                    length --;
                }else {
                    predecessor.next = current.next;
                    current.next.prev = current.prev;
                    length --;
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
