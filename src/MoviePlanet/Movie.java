package MoviePlanet;

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

   public Movie()
   {
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
