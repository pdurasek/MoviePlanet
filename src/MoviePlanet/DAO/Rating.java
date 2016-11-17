package MoviePlanet.DAO;

import MoviePlanet.DBAbstractionLayer.DLException;
import MoviePlanet.DBAbstractionLayer.MySQLDatabase;

import java.util.ArrayList;

public class Rating
{
   private int ratingID;
   private String ratingType;
   private MySQLDatabase db;

   public Rating(int ratingID, MySQLDatabase db)
   {
      this.ratingID = ratingID;
      this.db = db;
   }

   public Rating(int ratingID, String ratingType, MySQLDatabase db)
   {
      this.ratingID = ratingID;
      this.ratingType = ratingType;
      this.db = db;
   }

   public void fetch()
   {
      String query = "SELECT ratingID, ratingType FROM rating WHERE ratingID = ?;";
      ArrayList<ArrayList<String>> resultRow = null;
      ArrayList<String> values = new ArrayList<>();
      values.add(Integer.toString(this.ratingID));
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
      String query = "UPDATE rating SET ratingType = ? WHERE ratingID = ?;";
      boolean dataFound = false;
      ArrayList<String> values = new ArrayList<>();
      values.add(this.ratingType);
      values.add(Integer.toString(this.ratingID));

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
      String query = "INSERT INTO rating (ratingID, ratingType) VALUES (?, ?);";
      boolean success = false;
      ArrayList<String> values = new ArrayList<>();
      values.add(Integer.toString(this.ratingID));
      values.add(this.ratingType);

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
      String query = "DELETE from rating WHERE ratingID = ?";
      boolean success = false;
      ArrayList<String> values = new ArrayList<>();
      values.add(Integer.toString(this.ratingID));

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
      setRatingID(Integer.parseInt(resultRow.get(0).get(0)));
      setRatingType(resultRow.get(0).get(1));
   }

   public int getRatingID()
   {
      return ratingID;
   }

   public void setRatingID(int ratingID)
   {
      this.ratingID = ratingID;
   }

   public String getRatingType()
   {
      return ratingType;
   }

   public void setRatingType(String ratingType)
   {
      this.ratingType = ratingType;
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
