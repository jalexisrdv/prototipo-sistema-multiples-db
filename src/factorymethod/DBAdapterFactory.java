package factorymethod;

import java.util.Properties;

/**
 *
 * @author JALEXISRDV
 */
public class DBAdapterFactory {

    public static IDBAdapter getDBAdapter() {
        IDBAdapter adapter = null;
        try {
            Properties p = ConfiguracionLoader.getPropiedadades();
            String tipoDB = p.getProperty("TipoDB");
            System.out.println("TipoDB => " + tipoDB);
            adapter = (IDBAdapter) Class.forName(tipoDB).newInstance();
            System.out.println("IDBAdapter => " + adapter);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            ex.printStackTrace();
        }
        return adapter;
    }

}
