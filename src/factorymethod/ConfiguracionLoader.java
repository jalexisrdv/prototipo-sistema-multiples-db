package factorymethod;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author JALEXISRDV
 */
public class ConfiguracionLoader {

    public static Properties getPropiedadades() {
        Properties p = null;
        try {
            p = new Properties();
            ClassLoader classLoader = ConfiguracionLoader.class.getClassLoader();
            InputStream stream = classLoader.getResourceAsStream("META-INF/DBAdapter.properties");
            p.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return p;
    }
    
}
