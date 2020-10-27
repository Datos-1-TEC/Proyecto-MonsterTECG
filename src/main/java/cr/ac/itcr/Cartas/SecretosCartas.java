package cr.ac.itcr.Cartas;

public class SecretosCartas extends Carta{

    public SecretosCartas() {
        EjecutarAccion();
        System.out.println(getAction());
    }

    public void EjecutarAccion(){
        super.setAction("Secreto");
    }
}
