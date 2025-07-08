import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {

    private static final String CONFIG_PATH = "src/config.properties";

    public static String getApiKey() throws IOException {
        Properties props = new Properties();

        try (FileInputStream input = new FileInputStream(CONFIG_PATH)) {
            props.load(input);
        } catch (IOException e) {
            throw new IOException("Error al leer el archivo de configuración en: " + CONFIG_PATH, e);
        }

        String apiKey = props.getProperty("apikey");

        if (apiKey == null || apiKey.trim().isEmpty()) {
            throw new IllegalArgumentException("La clave 'apikey' no está definida en config.properties.");
        }

        return apiKey.trim();
    }
}
