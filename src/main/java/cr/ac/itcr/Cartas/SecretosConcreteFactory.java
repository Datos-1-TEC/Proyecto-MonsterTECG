package cr.ac.itcr.Cartas;

public class SecretosConcreteFactory implements CartasAbstractFactory {

    public Carta crearCarta(){

        return new SecretosConcreteProduct();
    }
}
