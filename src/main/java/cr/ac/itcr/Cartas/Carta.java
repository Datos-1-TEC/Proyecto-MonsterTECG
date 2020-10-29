package cr.ac.itcr.Cartas;


public class Carta {
/*    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }*/

    public int getCosteMana() {
        return costeMana;
    }

    public void setCosteMana(int costeMana) {
        this.costeMana = costeMana;
    }

    public String getAction() {
        return Action;
    }

    public void setAction(String action) {
        Action = action;
    }

    public static String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //private String type;
    private static String name;
    private static String Action;
    private static int costeMana;





}
