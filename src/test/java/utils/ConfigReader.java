package utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    static {
    //Used static block because whenever this method called from this class firstly initialized this properties object according to path and later method works.
    //Ne zaman class'dan method kullanılsa İlk olarak static blok çalışır verilen path'e göre sonra file okuma işlemi devreye girer.

        String path = "configuration.properties";
        try {

            FileInputStream file = new FileInputStream(path);

            properties= new Properties();
            properties.load(file);

            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //    This method accepts the key and returns the value
    //    Bu method  yukarıda belirttiğim string path'deki adrese giderek  buradaki keyleri okuyarak değerlerini getirir.
    public static String getProperty(String key){
        return properties.getProperty(key);
    }

}


