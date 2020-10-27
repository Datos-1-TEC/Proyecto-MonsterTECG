package cr.ac.itcr.Cartas;

public class HechizosCartas extends Carta {

    public HechizosCartas() {
        EjecutarAccion();
        System.out.println(getAction());
    }

    public void EjecutarAccion(){
        super.setAction("Accion");
    }
}
