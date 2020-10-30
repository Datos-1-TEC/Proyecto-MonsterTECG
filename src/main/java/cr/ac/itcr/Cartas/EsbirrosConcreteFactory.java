package cr.ac.itcr.Cartas;

public class EsbirrosConcreteFactory implements CartasAbstractFactory {

    public Carta crearCarta(String cartasType) {

        return new EsbirrosConcreteProduct();
    }
}
