package nl.han.ica.oose.dea.spotitube.data.connection;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

class DBConnectionCredentials {
    private Logger logger = Logger.getLogger(DBConnectionCredentials.class.getName());
    private Properties properties = new Properties();

    DBConnectionCredentials(String propertiesFileName) {
        String fileName = String.format("%s.properties",propertiesFileName);
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream(fileName));
        } catch (IOException e) {
            logger.log(Level.SEVERE,MessageFormat.format("Could not load properties file: {0}!", fileName));
        }
    }

    String driver(){
        return getProperty("driver");
    }

    String host()
    {
        return getProperty("url");
    }

    String database()
    {
        return getProperty("database");
    }

    String user()
    {
        return getProperty("dbuser");
    }

    String password()
    {
        return getProperty("dbpassword");
    }

    private String getProperty(String propertyName){
        final String propValue = properties.getProperty(propertyName);
        if(propValue == null) {
            logger.log(Level.SEVERE,MessageFormat.format("Missing value for property: {0}!", propertyName));
        }
        return propValue;
    }
}
