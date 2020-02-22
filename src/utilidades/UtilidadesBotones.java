package utilidades;

import java.awt.Component;
import javax.swing.JComponent;

/**
 *
 * @author JALEXISRDV
 */
public class UtilidadesBotones {
    
    public static void seleccionBotones(JComponent boton, Component[] botones) {
        boton.setEnabled(false);
        for (int i = 0; i < botones.length; i++) {
            if (!botones[i].getName().equals(boton.getName())) {
                botones[i].setEnabled(true);
            }
        }
    }

}
