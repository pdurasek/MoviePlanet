package MoviePlanet.DAO;

import MoviePlanet.DBAbstractionLayer.DLException;
import MoviePlanet.DBAbstractionLayer.MySQLDatabase;

import java.util.ArrayList;

public class Movie
{
   private int movieID;
   private int ratingID;
   private String name;
   private String description;
   private double score;
   private int year;
   private int screenTime;
   private String image;
   private String trailer;
   private MySQLDatabase db;

   public Movie(int movieID, MySQLDatabase db)
   {
      this.movieID = movieID;
      this.db = db;
   }

   public Movie(int ratingID, String name, String description, double score, int year, int screenTime, String image, String trailer, MySQLDatabase db)
   {
      this.ratingID = ratingID;
      this.name = name;
      this.description = description;
      this.score = score;
      this.year = year;
      this.screenTime = screenTime;
      this.image = image;
      this.trailer = trailer;
      this.db = db;
   }

   public Movie(int movieID, int ratingID, String name, String description, double score, int year, int screenTime, String image, String trailer, MySQLDatabase db)
   {
      this.movieID = movieID;
      this.ratingID = ratingID;
      this.name = name;
      this.description = description;
      this.score = score;
      this.year = year;
      this.screenTime = screenTime;
      this.image = image;
      this.trailer = trailer;
      this.db = db;
   }

   public void fetch()
   {
      String query = "SELECT movieID, ratingID, name, description, score, year, screenTime, image, trailer FROM movie WHERE movieID = ?;";
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
      String query = "UPDATE movie SET name = ?, description = ?, score = ?, year = ?, screenTime = ?, image = ?, trailer = ? WHERE movieID = ?;";
      boolean dataFound = false;
      ArrayList<String> values = new ArrayList<>();
      values.add(this.name);
      values.add(this.description);
      values.add(Double.toString(this.score));
      values.add(Integer.toString(this.movieID));
      values.add(Integer.toString(this.year));
      values.add(Integer.toString(this.screenTime));
      values.add(this.image);
      values.add(this.trailer);

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
      String query = "INSERT INTO movie (movieID, name, description, score, year, screenTime, image, trailer) VALUES (?, ?, ?, ?);";
      boolean success = false;
      ArrayList<String> values = new ArrayList<>();
      values.add(Integer.toString(this.movieID));
      values.add(this.name);
      values.add(this.description);
      values.add(Double.toString(this.score));
      values.add(Integer.toString(this.year));
      values.add(Integer.toString(this.screenTime));
      values.add(this.image);
      values.add(this.trailer);

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
      setRatingID(Integer.parseInt(resultRow.get(0).get(1)));
      setName(resultRow.get(0).get(2));
      setDescription(resultRow.get(0).get(3));
      setScore(Double.parseDouble(resultRow.get(0).get(4)));
      setYear(Integer.parseInt(resultRow.get(0).get(5)));
      setScreenTime(Integer.parseInt(resultRow.get(0).get(6)));
      setImage(resultRow.get(0).get(7));
      setTrailer(resultRow.get(0).get(8));

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

   public int getYear()
   {
      return year;
   }

   public void setYear(int year)
   {
      this.year = year;
   }

   public int getScreenTime()
   {
      return screenTime;
   }

   public void setScreenTime(int screenTime)
   {
      this.screenTime = screenTime;
   }

   public String getImage()
   {
      return image;
   }

   public void setImage(String image)
   {
      this.image = image;
   }

   public String getTrailer()
   {
      return trailer;
   }

   public void setTrailer(String trailer)
   {
      this.trailer = trailer;
   }

   public void setDb(MySQLDatabase db)
   {
      this.db = db;
   }
}
