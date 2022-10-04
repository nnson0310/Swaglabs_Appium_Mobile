package helpers;

import commons.GlobalConstants;

import java.io.*;
import java.util.Properties;

public class ConfigHelper {
    private static ConfigHelper configHelper = null;
    private Properties properties = new Properties();

    public static ConfigHelper getInstance() {
        if (configHelper == null) {
            configHelper = new ConfigHelper();
            configHelper.readConfigFile();
        }
        return configHelper;
    }

    public String getProperty(String propertyName) {
        return properties.getProperty(propertyName);
    }

    private void readConfigFile() {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(GlobalConstants.pathToMainResource + File.separator + "config.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
