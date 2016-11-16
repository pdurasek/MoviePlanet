package MoviePlanet.DAO;

public class Rating
{
   private int ratingID;
   private String ratingType;

   public Rating()
   {
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
