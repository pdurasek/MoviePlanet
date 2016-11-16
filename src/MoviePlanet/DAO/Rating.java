package MoviePlanet.DAO;

import MoviePlanet.DBAbstractionLayer.MySQLDatabase;

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
}
