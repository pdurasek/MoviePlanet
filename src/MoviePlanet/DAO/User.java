package MoviePlanet.DAO;

import MoviePlanet.DBAbstractionLayer.DLException;
import MoviePlanet.DBAbstractionLayer.MySQLDatabase;

import java.util.ArrayList;

public class User
{
   private int userID;
   private String username;
   private String password;
   private String accessLevel;
   private MySQLDatabase db;

   public User(int userID, MySQLDatabase db)
   {
      this.userID = userID;
      this.db = db;
   }

   public User(String username, String password, String accessLevel, MySQLDatabase db)
   {
      this.username = username;
      this.password = password;
      this.accessLevel = accessLevel;
      this.db = db;
   }

   public User(int userID, String username, String password, String accessLevel, MySQLDatabase db)
   {
      this.userID = userID;
      this.username = username;
      this.password = password;
      this.accessLevel = accessLevel;
      this.db = db;
   }

   public void fetch()
   {
      String query = "SELECT userID, username, password FROM user WHERE userID = ?;";
      ArrayList<ArrayList<String>> resultRow = null;
      ArrayList<String> values = new ArrayList<>();
      values.add(Integer.toString(this.userID));
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
      String query = "UPDATE user SET username = ?, password = ? WHERE userID = ?;";
      boolean dataFound = false;
      ArrayList<String> values = new ArrayList<>();
      values.add(this.username);
      values.add(this.password);
      values.add(Integer.toString(this.userID));

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
      String query = "INSERT INTO user (userID, username, password) VALUES (?, ?, ?);";
      boolean success = false;
      ArrayList<String> values = new ArrayList<>();
      values.add(Integer.toString(this.userID));
      values.add(this.username);
      values.add(this.password);

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
      String query = "DELETE from user WHERE userID = ?";
      boolean success = false;
      ArrayList<String> values = new ArrayList<>();
      values.add(Integer.toString(this.userID));

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
      setUserID(Integer.parseInt(resultRow.get(0).get(0)));
      setUsername(resultRow.get(0).get(1));
      setPassword(resultRow.get(0).get(2));
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

   public String getAccessLevel()
   {
      return accessLevel;
   }

   public void setAccessLevel(String accessLevel)
   {
      this.accessLevel = accessLevel;
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
