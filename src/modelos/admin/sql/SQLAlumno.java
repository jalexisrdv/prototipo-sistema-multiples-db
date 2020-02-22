package modelos.admin.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelos.Conexion;
import modelos.admin.Alumno;

/**
 *
 * @author JALEXISRDV
 */
public class SQLAlumno {

    public boolean registrarAlumno(Alumno alumno) {
        Connection conexion = Conexion.getConexion();
        if (conexion != null) {
            try {
                String sql = "INSERT INTO dbo.alumno(id_alumno, nombre_alumno, apellidos_alumno, email, telefono, password, id_status) VALUES(?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = conexion.prepareStatement(sql);
                ps.setString(1, alumno.getId());
                ps.setString(2, alumno.getNombre());
                ps.setString(3, alumno.getApellidos());
                ps.setString(4, alumno.getEmail());
                ps.setString(5, alumno.getTelefono());
                ps.setString(6, alumno.getPassword());
                ps.setInt(7, alumno.getIdStatus());
                ps.executeUpdate();
                return true;
            } catch (SQLException ex) {
                ex.printStackTrace();
            }finally {
                //CERRAR CONEXION
            }
        }
        return false;
    }
    
    public boolean actualizarAlumno(Alumno alumno) {
        Connection conexion = Conexion.getConexion();
        if (conexion != null) {
            try {
                String sql = "UPDATE dbo.alumno SET id_alumno = ?, nombre_alumno = ?, apellidos_alumno = ?, "
                        + "email = ?, telefono = ?, id_status = ?";
                PreparedStatement ps = conexion.prepareStatement(sql);
                ps.setString(1, alumno.getId());
                ps.setString(2, alumno.getNombre());
                ps.setString(3, alumno.getApellidos());
                ps.setString(4, alumno.getEmail());
                ps.setString(5, alumno.getTelefono());
                ps.setInt(6, alumno.getIdStatus());
                ps.executeUpdate();
                return true;
            } catch (SQLException ex) {
                ex.printStackTrace();
            }finally {
                //CERRAR CONEXION
            }
        }
        return false;
    }
    
    public boolean eliminarAlumno(String idAlumno) {
        Connection conexion = Conexion.getConexion();
        if (conexion != null) {
            try {
                String sql = "DELETE FROM dbo.alumno WHERE id_alumno = ?";
                PreparedStatement ps = conexion.prepareStatement(sql);
                ps.setString(1, idAlumno);
                ps.executeUpdate();
                return true;
            } catch (SQLException ex) {
                ex.printStackTrace();
            }finally {
                //CERRAR CONEXION
            }
        }
        return false;
    }

    public ResultSet buscarAlumno(String idAlumno) {
        Connection conexion = Conexion.getConexion();
        if (conexion != null) {
            try {
                String sql = "SELECT * FROM dbo.alumno WHERE id_alumno LIKE ?";
                PreparedStatement ps = conexion.prepareStatement(sql);
                ps.setString(1, "%" + idAlumno + "%");
                ResultSet rs = ps.executeQuery();
                return rs;
            } catch (SQLException ex) {
                ex.printStackTrace();
            }finally {
                //CERRAR CONEXION
            }
        }
        return null;
    }

}
