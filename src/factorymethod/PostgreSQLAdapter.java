package factorymethod;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author JALEXISRDV
 */
public class PostgreSQLAdapter implements IDBAdapter {
    
    @Override
    public Connection getConexion(String  user, String pass) {
        Connection conexion = null;
        try {
            String urlConexion = getURLConexion();
            conexion = DriverManager.getConnection(urlConexion, user, pass);
            System.out.println("Clase de conexion => " + conexion.getClass().getCanonicalName());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return conexion;
    }

    public String getURLConexion() {
        return "jdbc:postgresql://127.0.0.1:5432/dbo";
    }

}
