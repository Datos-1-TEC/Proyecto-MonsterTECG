package cr.ac.itcr.Cartas.Stack;

/**
 * clase Node es utilizada para representar un elemento con sus respectivas referencias en una estructura de datos
 * como lista o pila
 * @param <T>
 */
public class Node<T> {
    public T value;
    public Node next;
    public Node prev;
    T data;

    public Node() {
        this.next = this.prev = null;
    }

    public Node(T value) {
        this();
        this.value = value;
    }

    public Node(T value, Node next) {
        this(value);
        this.next = next;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }
}
