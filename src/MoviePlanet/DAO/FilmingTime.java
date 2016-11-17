package MoviePlanet.DAO;

import MoviePlanet.DBAbstractionLayer.DLException;
import MoviePlanet.DBAbstractionLayer.MySQLDatabase;

import java.util.ArrayList;

public class FilmingTime
{
   private int filmingTimeID;
   private int year;
   private int month;
   private int day;
   private MySQLDatabase db;

   public FilmingTime(int filmingTimeID, MySQLDatabase db)
   {
      this.filmingTimeID = filmingTimeID;
      this.db = db;
   }

   public FilmingTime(int filmingTimeID, int year, int month, int day, MySQLDatabase db)
   {
      this.filmingTimeID = filmingTimeID;
      this.year = year;
      this.month = month;
      this.day = day;
      this.db = db;
   }

   public void fetch()
   {
      String query = "SELECT filmingTimeID, year, month, day FROM filmingTime WHERE filmingTimeID = ?;";
      ArrayList<ArrayList<String>> resultRow = null;
      ArrayList<String> values = new ArrayList<>();
      values.add(Integer.toString(this.filmingTimeID));
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
      String query = "UPDATE filmingTime SET year = ?, month = ?, day = ? WHERE filmingTimeID = ?;";
      boolean dataFound = false;
      ArrayList<String> values = new ArrayList<>();
      values.add(Integer.toString(this.year));
      values.add(Integer.toString(this.month));
      values.add(Integer.toString(this.day));
      values.add(Integer.toString(this.filmingTimeID));

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
      String query = "INSERT INTO filmingTime (filmingTimeID, year, month, day) VALUES (?, ?, ?, ?);";
      boolean success = false;
      ArrayList<String> values = new ArrayList<>();
      values.add(Integer.toString(this.filmingTimeID));
      values.add(Integer.toString(this.year));
      values.add(Integer.toString(this.month));
      values.add(Integer.toString(this.day));

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
      String query = "DELETE from filmingTime WHERE filmingTimeID = ?";
      boolean success = false;
      ArrayList<String> values = new ArrayList<>();
      values.add(Integer.toString(this.filmingTimeID));

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
      setFilmingTimeID(Integer.parseInt(resultRow.get(0).get(0)));
      setYear(Integer.parseInt(resultRow.get(0).get(1)));
      setMonth(Integer.parseInt(resultRow.get(0).get(2)));
      setDay(Integer.parseInt(resultRow.get(0).get(3)));
   }

   public int getFilmingTimeID()
   {
      return filmingTimeID;
   }

   public void setFilmingTimeID(int filmingTimeID)
   {
      this.filmingTimeID = filmingTimeID;
   }

   public int getYear()
   {
      return year;
   }

   public void setYear(int year)
   {
      this.year = year;
   }

   public int getMonth()
   {
      return month;
   }

   public void setMonth(int month)
   {
      this.month = month;
   }

   public int getDay()
   {
      return day;
   }

   public void setDay(int day)
   {
      this.day = day;
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
