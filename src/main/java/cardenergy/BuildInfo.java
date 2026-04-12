package cardenergy;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class BuildInfo {
    private static final String DEFAULT_CHANNEL = "unknown";
    private static final String DEFAULT_DISPLAY = "Unknown";
    private static final Properties properties = loadProperties();

    private BuildInfo() {
    }

    public static String getChannel() {
        return properties.getProperty("channel", DEFAULT_CHANNEL);
    }

    public static String getDisplay() {
        return properties.getProperty("display", DEFAULT_DISPLAY);
    }

    public static String getVersion() {
        return properties.getProperty("version", "unknown");
    }

    private static Properties loadProperties() {
        Properties props = new Properties();
        try (InputStream stream = BuildInfo.class.getClassLoader().getResourceAsStream("build-info.properties")) {
            if (stream != null) {
                props.load(stream);
            }
        } catch (IOException ignored) {
        }
        return props;
    }
}
