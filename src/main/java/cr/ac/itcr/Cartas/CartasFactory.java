package cr.ac.itcr.Cartas;

public class CartasFactory implements CartasAbstractFactory {

    public Carta crearCarta(String cartasType){
        if ("HechizosCartas".equalsIgnoreCase(cartasType)) {
            return new HechizosConcreteProduct();
        }
        return null;
    }

    public CartasFactory() {
    }

    public void getCarta(){
        crearCarta("HechizosCartas").getAction();
    }


}
