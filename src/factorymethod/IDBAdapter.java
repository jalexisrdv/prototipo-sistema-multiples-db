package factorymethod;

import java.sql.Connection;

public interface IDBAdapter {
    
    public Connection getConexion(String user, String pass);
    
}
