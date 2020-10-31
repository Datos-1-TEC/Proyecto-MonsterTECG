package cr.ac.itcr.Cartas.Stack;


public class Deck<T> {
    private Node<T> top;

    public Deck(){
        this.top = null;
    }
    public boolean isEmpty(){
        return top == null;
    }
    public void push(T element){
        if (this.isEmpty()) {
            this.top = new Node<>(element);
        } else {
            Node<T> ref = new Node<>(element);
            ref.setNext(this.top);
            top = ref;
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
        return temp.getValue();
    }


}

