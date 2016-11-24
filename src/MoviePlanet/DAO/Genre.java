package MoviePlanet.DAO;

import MoviePlanet.DBAbstractionLayer.DLException;
import MoviePlanet.DBAbstractionLayer.MySQLDatabase;

import java.util.ArrayList;

public class Genre
{
   private int genreID;
   private String name;
   private MySQLDatabase db;

   public Genre(int genreID, MySQLDatabase db)
   {
      this.genreID = genreID;
      this.db = db;
   }

   public Genre(String name, MySQLDatabase db)
   {
      this.name = name;
      this.db = db;
   }

   public Genre(int genreID, String name, MySQLDatabase db)
   {
      this.genreID = genreID;
      this.name = name;
      this.db = db;
   }

   public void fetch()
   {
      String query = "SELECT genreID, name FROM genre WHERE genreID = ?;";
      ArrayList<ArrayList<String>> resultRow = null;
      ArrayList<String> values = new ArrayList<>();
      values.add(Integer.toString(this.genreID));
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
      String query = "UPDATE genre SET name = ? WHERE genreID = ?;";
      boolean dataFound = false;
      ArrayList<String> values = new ArrayList<>();
      values.add(this.name);
      values.add(Integer.toString(this.genreID));

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
      String query = "INSERT INTO genre (genreID, name) VALUES (?, ?);";
      boolean success = false;
      ArrayList<String> values = new ArrayList<>();
      values.add(Integer.toString(this.genreID));
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
      String query = "DELETE from genre WHERE genreID = ?";
      boolean success = false;
      ArrayList<String> values = new ArrayList<>();
      values.add(Integer.toString(this.genreID));

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
      setGenreID(Integer.parseInt(resultRow.get(0).get(0)));
      setName(resultRow.get(1).get(1));
   }

   public int getGenreID()
   {
      return genreID;
   }

   public void setGenreID(int genreID)
   {
      this.genreID = genreID;
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
