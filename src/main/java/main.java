import cr.ac.itcr.Jugador.Anfitrion;
import cr.ac.itcr.Jugador.ConnectionReceiver;
import cr.ac.itcr.Jugador.ConnectionRequest;
import cr.ac.itcr.UI.Jugador.Window;

import javax.swing.*;

public class main {
    static Anfitrion anfitrion = new Anfitrion();
    private static JOptionPane messageDialog;


    public static void main(String[] args) {
        String myRol = selectingRole();

        if (myRol.equals("Invitado")){
            ConnectionRequest request = new ConnectionRequest(anfitrion.getIP(), anfitrion.getPort());
        }else if (myRol.equals("Anfitrion")){
            settingPort();
            ConnectionReceiver receiver = new ConnectionReceiver(anfitrion);
            Window window = new Window();
        }
    }


    public static String selectingRole(){
        Object[] possibleValues = { "Anfitrion", "Invitado" };
        Object selectedValue = JOptionPane.showInputDialog(null,
                "Choose one", "Choosing role..",
                JOptionPane.INFORMATION_MESSAGE, null,
                possibleValues, possibleValues[0]);
        return  (String) selectedValue;
    }
    public static void settingPort(){
        String inputValue = JOptionPane.showInputDialog("Ingrese el puerto");
        int port =Integer.parseInt(inputValue);
        anfitrion.setPort(port);

    }

}