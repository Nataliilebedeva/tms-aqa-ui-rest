package core;

import java.io.IOException;
import java.util.Properties;

public class ReadProperties {
    protected Properties properties;

    public ReadProperties() {
        properties = new Properties();

        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getURL(){ return properties.getProperty("url");}
    public String getBrowser(){ return properties.getProperty("browser");}
    public String getUsername(){ return properties.getProperty("username");}
    public String getPassword(){ return properties.getProperty("password");}
    public int getTimeout(){ return Integer.parseInt(properties.getProperty("timeout"));}
    public boolean getHeadless(){ return Boolean.parseBoolean(properties.getProperty("headless"));}
    public String getTestRailURL() {
        return properties.getProperty("testrail_url");
    }
    public String getApiUsername() {
        return properties.getProperty("api_username");
    }
    public String getApiPassword() {
        return properties.getProperty("api_password");
    }

}
