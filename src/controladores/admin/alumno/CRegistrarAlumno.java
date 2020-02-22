package controladores.admin.alumno;

import utilidades.UtilidadesVentana;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import modelos.admin.Alumno;
import modelos.admin.sql.SQLAlumno;
import vistas.VentanaPrincipal;
import vistas.admin.alumno.PanelNuevoAlumno;

/**
 *
 * @author JALEXISRDV
 */
public class CRegistrarAlumno implements ActionListener {

    private final SQLAlumno sqlAlumno;
    private final PanelNuevoAlumno panel;

    public CRegistrarAlumno(SQLAlumno sqlAlumno, PanelNuevoAlumno panel) {
        this.sqlAlumno = sqlAlumno;
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComponent button = (JComponent) e.getSource();
        switch (button.getName()) {
            case "buttonRegister":
                registrarAlumno();
                break;
            case "buttonCancel":
                limpiarCampos();
        }
    }

    private void registrarAlumno() {
        Alumno alumno = new Alumno();
        alumno.setId(panel.getFieldId().getText());
        alumno.setNombre(panel.getFieldName().getText());
        alumno.setApellidos(panel.getFieldLastNames().getText());
        alumno.setEmail(panel.getFieldEmail().getText());
        alumno.setTelefono(panel.getFieldCellphone().getText());
        char[] password = panel.getFieldPassword().getPassword();
        /*SI EL CAMPO PASSWORD ESTA VACIO AL CONVERTIRSE A STRING
                ESTABLECE UN ESPACIO EN BLACO, POR ESTE MOTIVO EL ARREGLO
                DEBE SER >= 1
         */
        if (password.length >= 1) {
            alumno.setPassword(new String(password));
        }
        boolean registrado = sqlAlumno.registrarAlumno(alumno);
        VentanaPrincipal v = UtilidadesVentana.getVentanaPrincipal();
        if (registrado) {
            limpiarCampos();
            JOptionPane.showMessageDialog(v, "Alumno registrado",
                    "Registro correcto", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(v, "No se puede registrar alumno",
                    "Error al registrar", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void limpiarCampos() {
        panel.getFieldId().setText("");
        panel.getFieldName().setText("");
        panel.getFieldLastNames().setText("");
        panel.getFieldEmail().setText("");
        panel.getFieldCellphone().setText("");
        panel.getFieldPassword().setText("");
    }

}
