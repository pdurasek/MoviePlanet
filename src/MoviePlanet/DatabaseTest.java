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
            db.printDBInfo();
            db.printTableInfo("trip");
        }
        catch (DLException e)
        {
            System.out.println("Unable to print the database/table info\n");
        }
        try
        {
            db.printResultSetInfo("SELECT * from trip;");
        }
        catch (DLException e)
        {
            System.out.println("Unable to analyze the query \n");
        }

        Equipment equipment = new Equipment(9999);

        equipment.setDb(db);
        System.out.print("Fetched data: ");
        equipment.fetch(false);
        equipment.setEquipID(894);
        System.out.print("Fetched data: ");
        equipment.fetch(false);
        System.out.print("Fetched data: ");
        // testing the new getData method, if value passed to fetch is true, column headers will be added
        equipment.fetch(true);
        System.out.print("Fetched data: ");
        // testing the new getData method, if value passed to fetch is true, column headers will be added
        equipment.fetchP(true);

        equipment.setEquipID(1);
        System.out.print("Insert data: ");
        equipment.postP();
        equipment.setEquipmentName("Unicycle");
        equipment.setEquipmentDescription("Dat Boi");
        System.out.print("Updated data: ");
        equipment.put();
        equipment.setEquipmentName("Motor Bike");
        System.out.print("Updated data: ");
        equipment.putP();
        equipment.deleteP();
        try
        {
            db.close();
            System.out.println("Connection closed successfully");
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
