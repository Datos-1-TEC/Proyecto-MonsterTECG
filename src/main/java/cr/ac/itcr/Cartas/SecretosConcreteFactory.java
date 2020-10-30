package cr.ac.itcr.Cartas;

public class SecretosConcreteFactory implements CartasAbstractFactory {

    public Carta crearCarta(String cartasType){

        return new SecretosConcreteProduct();
    }
}
