package cr.ac.itcr.Cartas;

public class EsbirrosCartas extends Carta{

    public EsbirrosCartas() {
        Atacar();
        System.out.println(getAction());
    }

    public void Atacar() {
        super.setAction("Damage");
    }
}
