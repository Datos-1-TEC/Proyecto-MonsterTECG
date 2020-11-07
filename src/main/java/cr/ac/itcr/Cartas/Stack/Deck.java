package cr.ac.itcr.Cartas.Stack;

/**
 * Esta es una clase Deck implementada como estructura de datos Pila
 * que almacena las cartas iniciales y que no son visibles para el jugador
 * @param <T> tipo de elemento que habrá en la pila
 */
public class Deck<T> {
    private Node<T> top;

    public int getSize() {
        return size;
    }

    private int size = 0;

    public Deck(){
        this.top = null;
    }

    public boolean isEmpty(){
        return top == null;
    }

    public void push(T element){
        if (this.isEmpty()) {
            this.top = new Node<>(element);
            this.size ++;
        } else {
            Node<T> ref = new Node<>(element);
            ref.setNext(this.top);
            top = ref;
            this.size ++;
        }
    }

    public T peek(){
        if(!isEmpty()){
            return this.top.getValue();
        }
        return null;
    }


    public T pop(){
        Node<T> temp ;

        if(top == null){
            return null;
        }
        temp = top;
        top = top.getNext();
        this.size --;
        return temp.getValue();
    }


}

