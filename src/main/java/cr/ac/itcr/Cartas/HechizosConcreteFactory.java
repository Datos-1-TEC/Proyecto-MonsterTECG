package cr.ac.itcr.Cartas;

public class HechizosConcreteFactory implements CartasAbstractFactory {

    public Carta crearCarta() {

        return new HechizosConcreteProduct();
    }
}
