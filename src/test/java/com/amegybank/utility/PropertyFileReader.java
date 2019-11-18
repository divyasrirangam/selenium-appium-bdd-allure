package com.amegybank.utility;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class PropertyFileReader
{

   private static Properties properties = null;
   private static final Logger LOG = LogManager.getLogger(PropertyFileReader.class);

   /**
    * Loads the properties file and Log4js properties
    */
   public PropertyFileReader()
   {
      properties = new Properties();
      try
      {

         properties.load(new FileInputStream(Constants.LOCAL_CONFIG_PROPERTY_FILENAME));

      }
      catch (Exception e)
      {
         LOG.error("Error while initializing properties file: " + e.getMessage());
         e.printStackTrace();
         throw new RuntimeException(e);
      }
      assert !properties.isEmpty();
   }

   /**
    * returns the value of the property denoted by key
    *
    * @param key
    * @return
    */
   public static String getProperty(String key)
   {
      try
      {
         if (properties == null)
         {
            new PropertyFileReader();
         }

      }
      catch (Exception e)
      {
         new PropertyFileReader();
      }
      String property = properties.getProperty(key);
      return property != null?property.trim():property;
   }

   /**
    * Load the property and returns properties as object
    *
    * @param key
    * @return
    */
   public static Properties loadProperties()
   {
      try
      {
         if (properties == null)
         {
            new PropertyFileReader();
         }

      }
      catch (Exception e)
      {
         new PropertyFileReader();
      }
      return properties;
   }
}