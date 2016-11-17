package MoviePlanet.DAO;

import MoviePlanet.DBAbstractionLayer.DLException;
import MoviePlanet.DBAbstractionLayer.MySQLDatabase;

import java.util.ArrayList;

public class Awards
{

   private int awardsID;
   private String name;
   private MySQLDatabase db;

   public Awards(int awardsID, MySQLDatabase db)
   {
      this.awardsID = awardsID;
      this.db = db;
   }

   public Awards(int awardsID, String name, MySQLDatabase db)
   {
      this.awardsID = awardsID;
      this.name = name;
      this.db = db;
   }

   public void fetch()
   {
      String query = "SELECT awardsID, name FROM awards WHERE awardsID = ?;";
      ArrayList<ArrayList<String>> resultRow = null;
      ArrayList<String> values = new ArrayList<>();
      values.add(Integer.toString(this.awardsID));
      int startPoint = 0;

      try
      {
         resultRow = db.getData(query, values);
         setValues(resultRow);
      }
      catch (DLException e)
      {
         //TODO handle exception
         e.printStackTrace();
      }
   }

   public void put()
   {
      String query = "UPDATE awards SET name = ? WHERE awardsID = ?;";
      boolean dataFound = false;
      ArrayList<String> values = new ArrayList<>();
      values.add(this.name);
      values.add(Integer.toString(this.awardsID));

      try
      {
         dataFound = db.setData(query, values);
      }
      catch (DLException e)
      {
         //TODO handle exception
         e.printStackTrace();
      }

      if (dataFound)
      {
         //TODO implement
      }
      else
      {
         System.out.println("No rows found to update");
      }
   }

   public void post()
   {
      String query = "INSERT INTO awards (awardsID, name) VALUES (?, ?);";
      boolean success = false;
      ArrayList<String> values = new ArrayList<>();
      values.add(Integer.toString(this.awardsID));
      values.add(this.name);

      try
      {
         success = db.setData(query, values);
      }
      catch (DLException e)
      {
         //TODO handle exception
         e.printStackTrace();
      }

      if (success)
      {
         //TODO implement
      }
      else
      {
         System.out.println("No rows inserted");
      }
   }

   public void delete()
   {
      String query = "DELETE from awards WHERE awardsID = ?";
      boolean success = false;
      ArrayList<String> values = new ArrayList<>();
      values.add(Integer.toString(this.awardsID));

      try
      {
         success = db.setData(query, values);
      }
      catch (DLException e)
      {
         //TODO handle exception
         e.printStackTrace();
      }

      if (success)
      {
         //TODO implement
      }
      else
      {
         System.out.println("No rows deleted");
      }
   }

   public void setValues(ArrayList<ArrayList<String>> resultRow)
   {
      setAwardsID(Integer.parseInt(resultRow.get(0).get(0)));
      setName(resultRow.get(0).get(1));
   }

   public int getAwardsID()
   {
      return awardsID;
   }

   public void setAwardsID(int awardsID)
   {
      this.awardsID = awardsID;
   }

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public MySQLDatabase getDb()
   {
      return db;
   }

   public void setDb(MySQLDatabase db)
   {
      this.db = db;
   }
}
