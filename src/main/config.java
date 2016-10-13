package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by nmikutskiy on 01.10.16.
 */
public class config {
    private static String host;
    private static String chatPort;
    private static String port;

    public static String getHost(){
        return host;
    }

    public static String getPort(){
        return port;
    }

    public static String getChatPort(){
        return chatPort;
    }

    public static void loadProperties() {
        FileInputStream fileInputStream;
        Properties property = new Properties();

        try {
            fileInputStream = new FileInputStream("resources" + File.separator + "config.properties");
            property.load(fileInputStream);
        } catch (IOException e) {
            System.err.println("Error: properties file is not exists");
        }
        host = property.getProperty("host");
        port = property.getProperty("port");
        chatPort = property.getProperty("chat.port");

        System.out.println("HOST: " + host
                + ", port: " + chatPort);
    }
}