package cr.ac.itcr.Cartas;

public class HechizosCartas extends Carta {

    public HechizosCartas() {
        EjecutarAccion();
    }

    public void EjecutarAccion(){
        super.setAction("Ejecutando accion");
    }
}
