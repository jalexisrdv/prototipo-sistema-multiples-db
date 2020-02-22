package controladores.admin.alumno;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import vistas.admin.alumno.PanelEliminarAlumno;

/**
 *
 * @author JALEXISRDV
 */
public class CEliminarAlumno implements ActionListener, DocumentListener {

    private final SQLAlumno sqlAlumno;
    private final PanelEliminarAlumno panel;

    public CEliminarAlumno(SQLAlumno sqlAlumno, PanelEliminarAlumno panel) {
        this.sqlAlumno = sqlAlumno;
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        eliminarAlumno();
    }
    
    private void eliminarAlumno() {
        JTable tabla = panel.getTablaStudent();
        int rowSelected = tabla.getSelectedRow();
        if (rowSelected >= 0) {
            String idAlumno = tabla.getValueAt(rowSelected, 0).toString();
            boolean eliminado = sqlAlumno.eliminarAlumno(idAlumno);
            VentanaPrincipal v = UtilidadesVentana.getVentanaPrincipal();
            if (eliminado) {
                JTextField field = panel.getFieldSearchStudent();
                llenarTabla(field.getText());
                JOptionPane.showMessageDialog(v, "Alumno eliminado",
                        "Eliminado correctamente", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(v, "No se puede eliminar alumno",
                        "Error al eliminar", JOptionPane.INFORMATION_MESSAGE);
            }
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
