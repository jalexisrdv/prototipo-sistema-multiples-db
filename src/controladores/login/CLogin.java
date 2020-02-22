package controladores.login;

import utilidades.UtilidadesVentana;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import modelos.login.SQLValidarLogin;
import vistas.admin.PanelAdmin;
import vistas.alumno.PanelAlumno;
import vistas.profesor.PanelProfesor;
import vistas.login.PanelLogin;
import vistas.login.VentanaLogin;
import vistas.VentanaPrincipal;

/**
 *
 * @author JALEXISRDV
 */
public class CLogin implements ActionListener {

    private final PanelLogin panelLogin;
    private final SQLValidarLogin sqlValidarLogin;

    public CLogin(PanelLogin panelLogin, SQLValidarLogin sqlValidarLogin) {
        this.panelLogin = panelLogin;
        this.sqlValidarLogin = sqlValidarLogin;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComponent c = (JComponent) e.getSource();
        switch (c.getName()) {
            case "btnLogin":
                iniciarSesion();
        }
    }

    private void iniciarSesion() {
        String user = panelLogin.getFieldUser().getText();
        char[] pass = panelLogin.getFieldPassword().getPassword();
        String password = new String(pass);
        String existeUsuario = sqlValidarLogin.existeUsuario(user, password);
        switch (existeUsuario) {
            case "role_estudiantes":
                VentanaPrincipal ventanaPrincipal = abrirVentanaPrincipal();
                ventanaPrincipal.getContentPane().add(new PanelAlumno(), "Center");
                ventanaPrincipal.setVisible(true);
                break;
            case "role_profesores":
                ventanaPrincipal = abrirVentanaPrincipal();
                ventanaPrincipal.getContentPane().add(new PanelProfesor(), "Center");
                ventanaPrincipal.setVisible(true);
                break;
            case "role_admin":
                ventanaPrincipal = abrirVentanaPrincipal();
                ventanaPrincipal.getContentPane().add(new PanelAdmin(), "Center");
                ventanaPrincipal.setVisible(true);
                break;
            default:
                JOptionPane.showMessageDialog(UtilidadesVentana.getVentanaLogin(), "Error al ingresar tus datos", "Error al iniciar sesion",
                        JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private VentanaPrincipal abrirVentanaPrincipal() {
        VentanaLogin ventanaLogin = UtilidadesVentana.getVentanaLogin();
        ventanaLogin.dispose();
        VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
        UtilidadesVentana.setVentanaPrincipal(ventanaPrincipal);
        ventanaPrincipal.setSize(800, 600);
        int[] ps = UtilidadesVentana.getPuntosCentrarVentana(ventanaPrincipal.getWidth(), ventanaPrincipal.getHeight());
        ventanaPrincipal.setLocation(ps[0], ps[1]);
        return ventanaPrincipal;
    }

}
