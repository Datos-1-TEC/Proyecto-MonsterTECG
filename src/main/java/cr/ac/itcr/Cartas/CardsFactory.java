package cr.ac.itcr.Cartas;

public class CardsFactory {
    public CardsFactory(CartasAbstractFactory factory) {
        Carta carta = factory.crearCarta();
    }
}
