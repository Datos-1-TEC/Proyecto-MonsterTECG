package cr.ac.itcr.Cartas;

public class EsbirrosConcreteFactory implements CartasAbstractFactory {

    public Carta crearCarta() {

        return new EsbirrosConcreteProduct();
    }
}
