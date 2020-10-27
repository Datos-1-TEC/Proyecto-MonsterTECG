package cr.ac.itcr.Cartas;

public class EsbirrosConcreteFactory implements CartasAbstractFactory {

    @Override
    public Carta crearCarta() {
        return new Carta();
    }
}
