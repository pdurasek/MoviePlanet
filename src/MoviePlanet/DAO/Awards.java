package MoviePlanet.DAO;

import MoviePlanet.DBAbstractionLayer.MySQLDatabase;

public class Awards
{

   private int awardsID;
   private String name;
   private MySQLDatabase db;

   public Awards(int awardsID, MySQLDatabase db)
   {
      this.awardsID = awardsID;
      this.db = db;
   }

   public Awards(int awardsID, String name, MySQLDatabase db)
   {
      this.awardsID = awardsID;
      this.name = name;
      this.db = db;
   }

   public int getAwardsID()
   {
      return awardsID;
   }

   public void setAwardsID(int awardsID)
   {
      this.awardsID = awardsID;
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
