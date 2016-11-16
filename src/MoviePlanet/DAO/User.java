package MoviePlanet.DAO;

import MoviePlanet.DBAbstractionLayer.MySQLDatabase;

public class User
{
   private int userID;
   private String username;
   private String password;
   private MySQLDatabase db;

   public User(int userID, MySQLDatabase db)
   {
      this.userID = userID;
      this.db = db;
   }

   public User(int userID, String username, String password, MySQLDatabase db)
   {
      this.userID = userID;
      this.username = username;
      this.password = password;
      this.db = db;
   }

   public int getUserID()
   {
      return userID;
   }

   public void setUserID(int userID)
   {
      this.userID = userID;
   }

   public String getUsername()
   {
      return username;
   }

   public void setUsername(String username)
   {
      this.username = username;
   }

   public String getPassword()
   {
      return password;
   }

   public void setPassword(String password)
   {
      this.password = password;
   }
}
