package sistemamultiplesbd;

import utilidades.UtilidadesVentana;
import vistas.login.VentanaLogin;

/**
 *
 * @author JALEXISRDV
 */
public class AdministracionBD {

    public static void main(String[] args) {
        VentanaLogin v = new VentanaLogin();
        UtilidadesVentana.setVentanaLogin(v);
        v.setSize(500, 500);
        int[] pc = UtilidadesVentana.getPuntosCentrarVentana(v.getWidth(), v.getHeight());
        v.setLocation(pc[0], pc[1]);
        v.setVisible(true);
    }

}
