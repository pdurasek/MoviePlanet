package MoviePlanet.DAO;

import MoviePlanet.DBAbstractionLayer.MySQLDatabase;

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
}
