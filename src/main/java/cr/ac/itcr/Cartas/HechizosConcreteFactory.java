package cr.ac.itcr.Cartas;

public class HechizosConcreteFactory implements CartasAbstractFactory {

    @Override
    public Carta crearCarta() {
        return new Carta();
    }
}
