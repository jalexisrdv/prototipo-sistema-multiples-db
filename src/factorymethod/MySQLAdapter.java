package factorymethod;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author JALEXISRDV
 */
public class MySQLAdapter implements IDBAdapter {
    
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
        return "jdbc:mysql://localhost:3306/dbo"
                + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    }

}
