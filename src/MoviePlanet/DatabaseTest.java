package MoviePlanet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseTest
{
   public static void main(String[] args)
   {
      DatabaseTest tester = new DatabaseTest();
      tester.testDatabase(tester.setConnectionData("dbinfo"));
   }

   private void testDatabase(MySQLDatabase db)
   {
      try
      {
         if (db.connect())
         {
            System.out.println("Connected successfully!");
         }
         else
         {
            System.out.println("Unable to connect to the database!");
         }
      }
      catch (DLException e)
      {
         System.out.println("Unable to connect to the database");
         System.exit(0);
      }

      try
      {
         db.close();
         System.out.println("Connection closed successfully!");
      }
      catch (DLException e)
      {
         System.out.println("Error while trying to close the connection!");
      }
   }

   private MySQLDatabase setConnectionData(String filename)
   {
      Properties dbInfo = new Properties();
      try
      {
         InputStream infoStream = new FileInputStream(filename + ".properties");
         dbInfo.load(infoStream);
      }
      catch (FileNotFoundException e)
      {
         System.out.println("Property file not found!");
         System.exit(0);
      }
      catch (IOException e)
      {
         System.out.println("Error while reading the property file");
         System.exit(0);
      }

      return new MySQLDatabase(dbInfo.getProperty("username"), dbInfo.getProperty("password"), dbInfo.getProperty("dbms"));
   }
}
