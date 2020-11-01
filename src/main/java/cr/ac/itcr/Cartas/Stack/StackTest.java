package cr.ac.itcr.Cartas.Stack;

public class StackTest {
    public static void main(String[] args) {
/*        Deck<Integer> miDeck = new Deck<>();
        miDeck.push(3);
        miDeck.push(4);
        miDeck.push(5);
        miDeck.pop();
        miDeck.pop();
        System.out.println(miDeck.peek());*/
        ListaCircular<Integer> circular = new ListaCircular<>();
        circular.pushFront(777);
        circular.pushFront(555);
        circular.pushFront(89);
        circular.pushFront(547);
        circular.pushFront(34);
        circular.pushFront(65);

        circular.print();
        circular.deleteNode(777);
        circular.print();



    }
}
