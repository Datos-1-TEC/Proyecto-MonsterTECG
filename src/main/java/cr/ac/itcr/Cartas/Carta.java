package cr.ac.itcr.Cartas;
import java.io.Serializable;

/**
 * Objeto carta que es usado por los jugadores en cada turno
 */
public class Carta implements Serializable {

    private String type;
    private  String name;
    private  String Action;
    private  int costeMana;
    private String imagePath;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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
    public String getImagePath() {
        return imagePath;
    }

    public Carta() {
    }







}
