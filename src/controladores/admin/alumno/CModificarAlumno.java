package controladores.admin.alumno;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import modelos.admin.Alumno;
import modelos.admin.sql.SQLAlumno;
import utilidades.UtilidadesVentana;
import vistas.VentanaPrincipal;
import vistas.admin.alumno.DialogModificarAlumno;
import vistas.admin.alumno.PanelModificarAlumno;

/**
 *
 * @author JALEXISRDV
 */
public class CModificarAlumno implements ActionListener, DocumentListener {

    private final SQLAlumno sqlAlumno;
    private final PanelModificarAlumno panel;
    private final DialogModificarAlumno dialog;
    private final VentanaPrincipal vP;

    public CModificarAlumno(SQLAlumno sqlAlumno, PanelModificarAlumno panel) {
        this.sqlAlumno = sqlAlumno;
        this.panel = panel;
        vP = UtilidadesVentana.getVentanaPrincipal();
        dialog = new DialogModificarAlumno(vP, true);
        dialog.addEventos(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComponent button = (JComponent) e.getSource();
        switch (button.getName()) {
            case "buttonSaveChanges"://DialogModificarAlumno
                guardarCambiosDialog();
                break;
            case "buttonCancel"://DialogModificarAlumno
                dialog.setVisible(false);
                break;
            case "buttonModifyStudent"://PanelModificarAlumno
                establecerValoresCampos(dialog, vP);
        }
    }

    private void establecerValoresCampos(DialogModificarAlumno d, VentanaPrincipal vP) {
        JTable tabla = panel.getTablaStudent();
        int rowSelected = tabla.getSelectedRow();
        if (rowSelected >= 0) {
            d.setSize(450, 350);
            int[] pc = UtilidadesVentana.getPuntosCentrarDialogo(vP, d);
            d.setLocation(pc[0], pc[1]);
            try {
                String idAlumno = tabla.getValueAt(rowSelected, 0).toString();
                ResultSet rs = sqlAlumno.buscarAlumno(idAlumno);
                if (rs.next()) {
                    d.getFieldId().setText(rs.getString("id_alumno"));
                    d.getFieldName().setText(rs.getString("nombre_alumno"));
                    d.getFieldLastNames().setText(rs.getString("apellidos_alumno"));
                    d.getFieldEmail().setText(rs.getString("email"));
                    d.getFieldCellphone().setText(rs.getString("telefono"));
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            d.setVisible(true);
        }
    }

    private void guardarCambiosDialog() {
        Alumno alumno = new Alumno();
        alumno.setId(dialog.getFieldId().getText());
        alumno.setNombre(dialog.getFieldName().getText());
        alumno.setApellidos(dialog.getFieldLastNames().getText());
        alumno.setEmail(dialog.getFieldEmail().getText());
        alumno.setTelefono(dialog.getFieldCellphone().getText());
        boolean actualizado = sqlAlumno.actualizarAlumno(alumno);
        VentanaPrincipal v = UtilidadesVentana.getVentanaPrincipal();
        if (actualizado) {
            JTextField field = panel.getFieldSearchStudent();
            llenarTabla(field.getText());
            dialog.setVisible(false);
            JOptionPane.showMessageDialog(v, "Alumno actualizado",
                    "Actualizacion correcta", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(v, "No se puede actualizar alumno",
                    "Error al actualizar", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        JTextField field = panel.getFieldSearchStudent();
        llenarTabla(field.getText());
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        JTextField field = panel.getFieldSearchStudent();
        llenarTabla(field.getText());
    }

    @Override
    public void changedUpdate(DocumentEvent e) {

    }

    private void llenarTabla(String idAlumno) {
        /*LIKE '%%' POR DEFECTO DEVUELVE TODOS LOS RESULTADOS,
        ENTONCES SI EL STRING DE BUSQUEDA ESTA VACIO
        LE PONGO UN ESPACIO EN BLACO PARA QUE NO MUESTRE RESULTADOS*/
        if (idAlumno.isEmpty()) {
            idAlumno = " ";
        }
        ResultSet rs = sqlAlumno.buscarAlumno(idAlumno);
        DefaultTableModel modeloTabla = (DefaultTableModel) panel.getTablaStudent().getModel();
        while (modeloTabla.getRowCount() != 0) {
            modeloTabla.removeRow(0);
        }
        if (rs != null) {
            Alumno alumno = new Alumno();
            try {
                while (rs.next()) {
                    alumno.setId(rs.getString("id_alumno"));
                    alumno.setNombre(rs.getString("nombre_alumno"));
                    alumno.setApellidos(rs.getString("apellidos_alumno"));
                    alumno.setEmail(rs.getString("email"));
                    alumno.setTelefono(rs.getString("telefono"));
                    alumno.setIdStatus(rs.getInt("id_status"));

                    Object[] rowAlumno = {
                        alumno.getId(),
                        alumno.getNombre(),
                        alumno.getApellidos(),
                        alumno.getEmail(),
                        alumno.getTelefono(),
                        alumno.getIdStatus()
                    };

                    modeloTabla.addRow(rowAlumno);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

}
