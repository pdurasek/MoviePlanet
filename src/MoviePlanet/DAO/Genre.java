package MoviePlanet.DAO;

import MoviePlanet.DBAbstractionLayer.MySQLDatabase;

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

   public Genre(int genreID, String name, MySQLDatabase db)
   {
      this.genreID = genreID;
      this.name = name;
      this.db = db;
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
}
