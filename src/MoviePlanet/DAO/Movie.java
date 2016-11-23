package MoviePlanet.DAO;

import MoviePlanet.DBAbstractionLayer.DLException;
import MoviePlanet.DBAbstractionLayer.MySQLDatabase;

import java.util.ArrayList;

public class Movie
{
   private int movieID;
   private int filmingTimeID;
   private int screeningTimeID;
   private int userID;
   private int ratingID;
   private String name;
   private String description;
   private double score;
   private MySQLDatabase db;

   public Movie(int movieID, MySQLDatabase db)
   {
      this.movieID = movieID;
      this.db = db;
   }

   public Movie(int movieID, int filmingTimeID, int screeningTimeID, int userID, int ratingID, String name, String description, double score, MySQLDatabase db)
   {
      this.movieID = movieID;
      this.filmingTimeID = filmingTimeID;
      this.screeningTimeID = screeningTimeID;
      this.userID = userID;
      this.ratingID = ratingID;
      this.name = name;
      this.description = description;
      this.score = score;
      this.db = db;
   }

   public void fetch()
   {
      String query = "SELECT movieID, filmingTimeID, screeningTimeID, userID, ratingID, name, description, score FROM movie WHERE movieID = ?;";
      ArrayList<ArrayList<String>> resultRow = null;
      ArrayList<String> values = new ArrayList<>();
      values.add(Integer.toString(this.movieID));
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
      String query = "UPDATE movie SET name = ?, description = ?, score = ? WHERE movieID = ?;";
      boolean dataFound = false;
      ArrayList<String> values = new ArrayList<>();
      values.add(this.name);
      values.add(this.description);
      values.add(Double.toString(this.score));
      values.add(Integer.toString(this.movieID));

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
      String query = "INSERT INTO movie (movieID, name, description, score) VALUES (?, ?, ?, ?);";
      boolean success = false;
      ArrayList<String> values = new ArrayList<>();
      values.add(Integer.toString(this.movieID));
      values.add(this.name);
      values.add(this.description);
      values.add(Double.toString(this.score));

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
      String query = "DELETE from movie WHERE movieID = ?";
      boolean success = false;
      ArrayList<String> values = new ArrayList<>();
      values.add(Integer.toString(this.movieID));

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
      setMovieID(Integer.parseInt(resultRow.get(0).get(0)));
      setName(resultRow.get(0).get(1));
      setDescription(resultRow.get(0).get(2));
      setScore(Double.parseDouble(resultRow.get(0).get(3)));
   }

   public int getFilmingTimeID()
   {
      return filmingTimeID;
   }

   public void setFilmingTimeID(int filmingTimeID)
   {
      this.filmingTimeID = filmingTimeID;
   }

   public int getScreeningTimeID()
   {
      return screeningTimeID;
   }

   public void setScreeningTimeID(int screeningTimeID)
   {
      this.screeningTimeID = screeningTimeID;
   }

   public int getUserID()
   {
      return userID;
   }

   public void setUserID(int userID)
   {
      this.userID = userID;
   }

   public int getRatingID()
   {
      return ratingID;
   }

   public void setRatingID(int ratingID)
   {
      this.ratingID = ratingID;
   }

   public int getMovieID()
   {
      return movieID;
   }

   public void setMovieID(int movieID)
   {
      this.movieID = movieID;
   }

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public String getDescription()
   {
      return description;
   }

   public void setDescription(String description)
   {
      this.description = description;
   }

   public double getScore()
   {
      return score;
   }

   public void setScore(double score)
   {
      this.score = score;
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
