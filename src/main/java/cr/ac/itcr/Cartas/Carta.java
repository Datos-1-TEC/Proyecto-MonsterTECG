package cr.ac.itcr.Cartas;
import java.io.Serializable;

public class Carta implements Serializable {
    private  String name;
    private  String Action;
    private  int costeMana;

    public  String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAction() {
        return this.Action;
    }

    public void setAction(String action) {
        this.Action = action;
    }

    public int getCosteMana() {
        return this.costeMana;
    }

    public void setCosteMana(int costeMana) {
        this.costeMana = costeMana;
    }

    public Carta() {
        getName();
        getAction();
        getCosteMana();
    }





    //private String type;






}
