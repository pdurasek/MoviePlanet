package MoviePlanet.DAO;

import MoviePlanet.DBAbstractionLayer.DLException;
import MoviePlanet.DBAbstractionLayer.MySQLDatabase;

import java.util.ArrayList;

public class ScreeningTime
{
   private int screeningTimeID;
   private int hour;
   private int minute;
   private int second;
   private MySQLDatabase db;

   public ScreeningTime(int screeningTimeID, MySQLDatabase db)
   {
      this.screeningTimeID = screeningTimeID;
      this.db = db;
   }

   public ScreeningTime(int screeningTimeID, int hour, int minute, int second, MySQLDatabase db)
   {
      this.screeningTimeID = screeningTimeID;
      this.hour = hour;
      this.minute = minute;
      this.second = second;
      this.db = db;
   }

   public void fetch()
   {
      String query = "SELECT screeningTimeID, hour, minute, second FROM screeningTime WHERE screeningTimeID = ?;";
      ArrayList<ArrayList<String>> resultRow = null;
      ArrayList<String> values = new ArrayList<>();
      values.add(Integer.toString(this.screeningTimeID));
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
      String query = "UPDATE screeningTime SET hour = ?, minute = ?, second = ? WHERE screeningTimeID = ?;";
      boolean dataFound = false;
      ArrayList<String> values = new ArrayList<>();
      values.add(Integer.toString(this.hour));
      values.add(Integer.toString(this.minute));
      values.add(Integer.toString(this.second));
      values.add(Integer.toString(this.screeningTimeID));

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
      String query = "INSERT INTO screeningTime (screeningTimeID, hour, minute, second) VALUES (?, ?, ?, ?);";
      boolean success = false;
      ArrayList<String> values = new ArrayList<>();
      values.add(Integer.toString(this.screeningTimeID));
      values.add(Integer.toString(this.hour));
      values.add(Integer.toString(this.minute));
      values.add(Integer.toString(this.second));

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
      String query = "DELETE from screeningTime WHERE screeningTimeID = ?";
      boolean success = false;
      ArrayList<String> values = new ArrayList<>();
      values.add(Integer.toString(this.screeningTimeID));

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
      setScreeningTimeID(Integer.parseInt(resultRow.get(0).get(0)));
      setHour(Integer.parseInt(resultRow.get(0).get(1)));
      setMinute(Integer.parseInt(resultRow.get(0).get(2)));
      setSecond(Integer.parseInt(resultRow.get(0).get(3)));
   }

   public int getScreeningTimeID()
   {
      return screeningTimeID;
   }

   public void setScreeningTimeID(int screeningTimeID)
   {
      this.screeningTimeID = screeningTimeID;
   }

   public int getHour()
   {
      return hour;
   }

   public void setHour(int hour)
   {
      this.hour = hour;
   }

   public int getMinute()
   {
      return minute;
   }

   public void setMinute(int minute)
   {
      this.minute = minute;
   }

   public int getSecond()
   {
      return second;
   }

   public void setSecond(int second)
   {
      this.second = second;
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
