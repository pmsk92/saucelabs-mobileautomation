package dataprovider;

import java.io.*;
import java.util.Properties;


public final class ConfigFileReader {

    private Properties properties;
    private final String propertyFilePath= "src/resources/config.properties";

    public ConfigFileReader(){
        BufferedReader reader;
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(fileInputStream);
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
        }
    }

    public String getPropertyValue(final String propertyName){
        return properties.getProperty(propertyName);
    }
}
