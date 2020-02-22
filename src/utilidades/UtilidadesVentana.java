package utilidades;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JDialog;
import vistas.VentanaPrincipal;
import vistas.login.VentanaLogin;

/**
 *
 * @author JALEXISRDV
 */
public class UtilidadesVentana {

    private static VentanaLogin ventanaLogin;
    private static VentanaPrincipal ventanaPrincipal;

    public static void setVentanaLogin(VentanaLogin ventanaLogin) {
        UtilidadesVentana.ventanaLogin = ventanaLogin;
    }

    public static VentanaLogin getVentanaLogin() {
        return UtilidadesVentana.ventanaLogin;
    }

    /**
     *
     * @description devuelve los puntos para que la ventana este centrada en la pantalla
     */
    public static int[] getPuntosCentrarVentana(int ancho, int alto) {
        Toolkit t = Toolkit.getDefaultToolkit();
        Dimension pantalla = t.getScreenSize();
        int xc = pantalla.width / 2;
        int yc = pantalla.height / 2;
        int xv = xc - ancho / 2;
        int yv = yc - alto / 2;
        int[] pc = {xv, yv};
        return pc;
    }
    
    public static void setVentanaPrincipal(VentanaPrincipal ventanaPrincipal) {
        UtilidadesVentana.ventanaPrincipal = ventanaPrincipal;
    }
    
    public static VentanaPrincipal getVentanaPrincipal() {
        return ventanaPrincipal;
    }
    
    public static int[] getPuntosCentrarDialogo(VentanaPrincipal v, JDialog d) {
        int cx = v.getWidth() / 2;
        int cy = v.getHeight() / 2;
        int x1 = cx - d.getWidth() / 2;
        int y1 = cy - d.getHeight() / 2;
        int[] pc = {v.getX() + x1, v.getY() + y1};
        return pc;
    }

}
