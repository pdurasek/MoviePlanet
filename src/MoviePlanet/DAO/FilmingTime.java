package MoviePlanet.DAO;

import MoviePlanet.DBAbstractionLayer.MySQLDatabase;

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
}
