package modelos.login;

import factorymethod.MySQLAdapter;
import factorymethod.OracleDatabaseAdapter;
import factorymethod.PostgreSQLAdapter;
import factorymethod.SQLServerAdapter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelos.Conexion;

/**
 *
 * @author JALEXISRDV
 */
public class SQLValidarLogin {

    /*Retorno el role del usuario que esta registrado en la tabla correspondiente de la base de datos
    y en caso de admin el registrado dentro del gestor, con esto puedo direccionar el usuario conectado a su panel 
    correspondiente*/
    public String existeUsuario(String user, String pass) {
        Conexion.establecerConexion(user, pass);
        Connection conexion = Conexion.getConexion();
        if (conexion != null) {//Se inicio la sesión
            try {
                String role = getRoleUsuario();
                System.out.println("Role de usuario conectado: " + role);
                String sql = "";
                switch (role) {
                    case "role_estudiantes":
                        sql = "SELECT id_alumno, password FROM dbo.alumno WHERE id_alumno=? AND password=?";
                        break;
                    case "role_profesores":
                        sql = "SELECT id_profesor, password FROM dbo.profesor WHERE id_profesor=? AND password=?";
                        break;
                    case "role_admin":
                        return role;
                }
                PreparedStatement ps = conexion.prepareStatement(sql);
                ps.setString(1, user);
                ps.setString(2, pass);
                ResultSet rs = ps.executeQuery();
                /*EL USUARIO TIENE PERMISOS DE CONEXION, PERO INDEPENDIENTE A ESO DEBO COMPROBAR
                SI ESTA REGISTRADO EN LA TABLA PARA QUE PUEDE INICIAR SESION*/
                if (rs.next()) {
                    if (rs.getString(1).equalsIgnoreCase(user)) {
                        return role;
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                //Cerrar conexion
            }
        }
        return "";
    }

    //Se obtienen los roles de acuerdo al tipo de gestor
    public String getRoleUsuario() {
        String role = "";
        switch (getTipoDB()) {
            case "SQLServer":
                role = getRoleUsuarioSQLServer();
                break;
            case "MySQL":
                role = getRoleUsuarioMySQL();
                break;
            case "OracleDatabase":
                role = getRoleUsuarioOracleDatabase();
                break;
            case "PostgreSQL":
                role = getRoleUsuarioPostgreSQL();
        }
        return role;
    }

    //Devuelve la clase con el tipo de gestor donde se realiza la conexion
    public String getTipoDB() {
        String tipoDB = Conexion.getTipoBD().getClass().getName();
        if (tipoDB.equals(SQLServerAdapter.class.getName())) {
            System.out.println("Conectado con SQLServer");
            return "SQLServer";
        }
        if (tipoDB.equals(MySQLAdapter.class.getName())) {
            return "MySQL";
        }
        if (tipoDB.equals(OracleDatabaseAdapter.class.getName())) {
            return "OracleDatabase";
        }
        if (tipoDB.equals(PostgreSQLAdapter.class.getName())) {
            return "PostgreSQL";
        }
        return "";
    }

    /*Los metodos siguientes me ayudan a obtener el rol que tiene el usuario conectado actualmente*/
    public String getRoleUsuarioSQLServer() {
        try {
            Connection conexion = Conexion.getConexion();
            String sql = "SELECT dp.name , us.name FROM sys.sysusers us right \n"
                    + "JOIN  sys.database_role_members rm ON us.uid = rm.member_principal_id\n"
                    + "JOIN sys.database_principals dp ON rm.role_principal_id =  dp.principal_id\n"
                    + "WHERE us.name = CURRENT_USER;";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String role = rs.getString(1).toLowerCase();
                if (role.contains("role_admin")) {
                    return "role_admin";
                }
                if (role.contains("role_estudiantes")) {
                    return "role_estudiantes";
                }
                if (role.contains("role_profesores")) {
                    return "role_profesores";
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            //Cerrar conexion
        }
        return "";
    }

    public String getRoleUsuarioMySQL() {
        try {
            Connection conexion = Conexion.getConexion();
            String sql = "SELECT current_role();";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String role = rs.getString(1).toLowerCase();
                if (role.contains("role_admin")) {
                    return "role_admin";
                }
                if (role.contains("role_estudiantes")) {
                    return "role_estudiantes";
                }
                if (role.contains("role_profesores")) {
                    return "role_profesores";
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            //Cerrar conexion
        }
        return "";
    }

    public String getRoleUsuarioOracleDatabase() {
        try {
            Connection conexion = Conexion.getConexion();
            String sql = "SELECT * FROM session_roles";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String role = rs.getString(1).toLowerCase();
                if (role.contains("role_admin")) {
                    return "role_admin";
                }
                if (role.contains("role_estudiantes")) {
                    return "role_estudiantes";
                }
                if (role.contains("role_profesores")) {
                    return "role_profesores";
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            //Cerrar conexion
        }
        return "";
    }

    public String getRoleUsuarioPostgreSQL() {
        try {
            Connection conexion = Conexion.getConexion();
            String sql = "SELECT rolname FROM pg_roles WHERE pg_has_role(current_user, oid, 'member');";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String role = rs.getString(1).toLowerCase();
                if (role.contains("role_admin")) {
                    return "role_admin";
                }
                if (role.contains("role_estudiantes")) {
                    return "role_estudiantes";
                }
                if (role.contains("role_profesores")) {
                    return "role_profesores";
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            //Cerrar conexion
        }
        return "";
    }

}
