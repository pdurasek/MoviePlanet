package MoviePlanet;

import java.util.ArrayList;
import java.util.List;

public class Equipment
{
    private int equipID;
    private String equipmentName;
    private String equipmentDescription;
    private int equipmentCapacity;
    private MySQLDatabase db = null;


    public Equipment(int equipID)
    {
        this.equipID = equipID;
    }

    public Equipment(int equipID, String equipmentName, String equipmentDescription, int equipmentCapacity)
    {
        this.equipID = equipID;
        this.equipmentName = equipmentName;
        this.equipmentDescription = equipmentDescription;
        this.equipmentCapacity = equipmentCapacity;
    }

    public int getEquipID()
    {
        return equipID;
    }

    public void setEquipID(int equipID)
    {
        this.equipID = equipID;
    }

    public String getEquipmentName()
    {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName)
    {
        this.equipmentName = equipmentName;
    }

    public String getEquipmentDescription()
    {
        return equipmentDescription;
    }

    public void setEquipmentDescription(String equipmentDescription)
    {
        this.equipmentDescription = equipmentDescription;
    }

    public int getEquipmentCapacity()
    {
        return equipmentCapacity;
    }

    public void setEquipmentCapacity(int equipmentCapacity)
    {
        this.equipmentCapacity = equipmentCapacity;
    }

    public void setDb(MySQLDatabase db)
    {
        this.db = db;
    }

    /**
     * Creates and calls a select method with the current equipID and prints out the result
     *
     * @param colHeaders - true to include the headers, false to print only the data
     */
    public void fetch(boolean colHeaders)
    {
        List<String[]> resultRow = null;
        int startPoint = 0;

        try
        {
            resultRow = db.getData("SELECT equipID, equipmentName, equipmentDescription, equipmentCapacity" +
                    " from equipment where equipID = " + this.equipID + ";", colHeaders);

            if (colHeaders)
            {
                startPoint = 1;
                printColumnHeaders(resultRow.get(0)[0], resultRow.get(0)[1], resultRow.get(0)[2], resultRow.get(0)[3]);
            }

            setValues(resultRow, startPoint);
        }
        catch (DLException e)
        {
            System.out.println("Error while creating or executing the statement!");
        }
    }

    public void put()
    {
        boolean dataFound = false;
        try
        {
            dataFound = db.setData("UPDATE equipment set equipmentName=" + "'" + this.equipmentName + "'" + ",equipmentDescription="
                    + "'" + this.equipmentDescription + "'" + ",equipmentCapacity=" + this.equipmentCapacity + " where equipID="
                    + this.equipID + ";");
        }
        catch (DLException e)
        {
            System.out.println("Error while trying to create or execute the statement!");
        }
        if (dataFound)
        {
            printEquipment();
        }
        else
        {
            System.out.println("No rows were found!");
        }
    }

    public void post()
    {
        boolean dataFound = false;
        try
        {
            dataFound = db.setData("INSERT INTO equipment (equipID, equipmentName, equipmentDescription, equipmentCapacity) VALUES ("
                    + this.equipID + "," + "'" + this.equipmentName + "'" + "," + "'" + this.equipmentDescription + "'"
                    + "," + this.equipmentCapacity + ")");
        }
        catch (DLException e)
        {
            System.out.println("Error while trying to create or execute the statement!");
        }
        if (dataFound)
        {
            printEquipment();
        }
        else
        {
            System.out.println("No rows were found!");
        }
    }

    public void delete()
    {
        boolean dataFound = false;
        try
        {
            dataFound = db.setData("DELETE from equipment where equipID = " + this.equipID);
        }
        catch (DLException e)
        {
            System.out.println("Error while trying to create or execute the statement!");
        }

        if (dataFound)
        {
            System.out.println("Rows deleted successfully");
        }
        else
        {
            System.out.println("No rows were found!");
        }
    }

    public void fetchP(boolean colHeaders)
    {
        List<String[]> resultRow = null;
        ArrayList<String> values = new ArrayList<>();
        values.add(Integer.toString(this.equipID));
        int startPoint = 0;

        try
        {
            resultRow = db.getData("SELECT equipID, equipmentName, equipmentDescription, equipmentCapacity" +
                    " from equipment where equipID = ?;", values, colHeaders);

            if (colHeaders)
            {
                startPoint = 1;
                printColumnHeaders(resultRow.get(0)[0], resultRow.get(0)[1], resultRow.get(0)[2], resultRow.get(0)[3]);
            }

            setValues(resultRow, startPoint);


        }
        catch (DLException e)
        {
            System.out.println("Error while creating or executing the statement!");
        }
    }

    public void putP()
    {
        boolean dataFound = false;
        ArrayList<String> values = new ArrayList<>();
        values.add(this.equipmentName);
        values.add(this.equipmentDescription);
        values.add(Integer.toString(this.equipmentCapacity));
        values.add(Integer.toString(this.equipID));

        try
        {
            dataFound = db.setData("UPDATE equipment set equipmentName= ?, equipmentDescription = ? ,equipmentCapacity= ? where equipID=?;", values);
        }
        catch (DLException e)
        {
            System.out.println("Error while trying to create or execute the statement!");

        }
        if (dataFound)
        {
            printEquipment();
        }
        else
        {
            System.out.println("No rows were found!");
        }
    }

    public void postP()
    {
        boolean dataFound = false;
        ArrayList<String> values = new ArrayList<>();
        values.add(Integer.toString(this.equipID));
        values.add(this.equipmentName);
        values.add(this.equipmentDescription);
        values.add(Integer.toString(this.equipmentCapacity));

        try
        {
            dataFound = db.setData("INSERT INTO equipment (equipID, equipmentName, equipmentDescription, equipmentCapacity) VALUES (?, ?, ?, ?)", values);
        }
        catch (DLException e)
        {
            System.out.println("Error while trying to create or execute the statement!");
        }
        if (dataFound)
        {
            printEquipment();
        }
        else
        {
            System.out.println("No rows were found!");
        }
    }

    public void deleteP()
    {
        boolean dataFound = false;
        ArrayList<String> values = new ArrayList<>();
        values.add(Integer.toString(this.equipID));
        try
        {
            dataFound = db.setData("DELETE from equipment where equipID=?", values);
        }
        catch (DLException e)
        {
            System.out.println("Error while trying to create or execute the statement!");
        }

        if (dataFound)
        {
            System.out.println("Rows deleted successfully");
        }
        else
        {
            System.out.println("No rows were found!");
        }
    }

    public void printEquipment()
    {
        System.out.println(this.equipID + " | " + this.equipmentName + " | " + this.equipmentDescription + " | " + this.equipmentCapacity);
    }

    public void printColumnHeaders(String... headers)
    {
        System.out.println("\n" + headers[0] + " | " + headers[1] + " | " + headers[2] + " | " + headers[3]);
    }

    public void setValues(List<String[]> resultRow, int startPoint)
    {
        if (resultRow.size() > startPoint)
        {
            setEquipmentName(resultRow.get(startPoint)[1]);
            setEquipmentDescription(resultRow.get(startPoint)[2]);
            setEquipmentCapacity(Integer.parseInt(resultRow.get(startPoint)[3]));
            printEquipment();
        }
        else
        {
            System.out.println("No rows were found!");
        }
    }
}
