package modelos;

import factorymethod.DBAdapterFactory;
import factorymethod.IDBAdapter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author JALEXISRDV
 */
public class Conexion {

    private static Connection conexion = null;
    private static IDBAdapter tipoDB;
    
    public static void establecerConexion(String user, String pass) {
        tipoDB = (IDBAdapter) DBAdapterFactory.getDBAdapter();
        conexion = tipoDB.getConexion(user, pass);
    }
    
    public static Connection getConexion() {
        return conexion;
    }
    
    public static IDBAdapter getTipoBD() {
        return tipoDB;
    }

    public static void cerrarConexion() {
        try {
            conexion.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void cerrarSentencia(PreparedStatement sentencia) {
        try {
            sentencia.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void cerrarResulset(ResultSet rs) {
        try {
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
