package cr.ac.itcr.Cartas.Stack;

public class StackTest {
    public static void main(String[] args) {
        Deck<Integer> miDeck = new Deck<>();
        miDeck.push(3);
        miDeck.push(4);
        miDeck.push(5);
        miDeck.pop();
        System.out.println(miDeck.peek());
    }
}
