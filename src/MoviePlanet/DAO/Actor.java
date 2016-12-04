package MoviePlanet.DAO;

import MoviePlanet.DBAbstractionLayer.DLException;
import MoviePlanet.DBAbstractionLayer.MySQLDatabase;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Actor
{

   private int actorID;
   private String name;
   private Date dob;
   private String country;
   private MySQLDatabase db;

   public Actor(int actorID, MySQLDatabase db)
   {
      this.actorID = actorID;
      this.db = db;
   }

   public Actor(String name, Date dob, String country, MySQLDatabase db)
   {
      this.name = name;
      this.dob = dob;
      this.country = country;
      this.db = db;
   }

   public Actor(int actorID, String name, Date dob, String country, MySQLDatabase db)
   {
      this.actorID = actorID;
      this.name = name;
      this.dob = dob;
      this.country = country;
      this.db = db;
   }

   public void fetch()
   {
      String query = "SELECT actorID, name, dob, country FROM actor WHERE actorID = ?;";
      ArrayList<ArrayList<String>> resultRow = null;
      ArrayList<String> values = new ArrayList<>();
      values.add(Integer.toString(this.actorID));
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
      String query = "UPDATE actor SET name = ?,dob = ?, country = ? WHERE actorID = ?;";
      boolean dataFound = false;
      ArrayList<String> values = new ArrayList<>();
      values.add(this.name);
      values.add(this.dob.toString());
      values.add(this.country);
      values.add(Integer.toString(this.actorID));

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
      String query = "INSERT INTO actor (name, dob, country) VALUES (?, ?, ?);";
      boolean success = false;
      ArrayList<String> values = new ArrayList<>();
      values.add(this.name);
      values.add(this.dob.toString());
      values.add(this.country);

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
      String query = "DELETE from actor WHERE actorID = ?";
      boolean success = false;
      ArrayList<String> values = new ArrayList<>();
      values.add(Integer.toString(this.actorID));

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
      setActorID(Integer.parseInt(resultRow.get(0).get(0)));
      setName(resultRow.get(0).get(1));
      try
      {
         setDob(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(resultRow.get(0).get(2)).getTime()));
      }
      catch (ParseException e)
      {
         //TODO handle exception
         e.printStackTrace();
      }
      setCountry(resultRow.get(0).get(3));
   }

   public int getActorID()
   {
      return actorID;
   }

   public void setActorID(int actorID)
   {
      this.actorID = actorID;
   }

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public Date getDob()
   {
      return dob;
   }

   public void setDob(Date dob)
   {
      this.dob = dob;
   }

   public String getCountry()
   {
      return country;
   }

   public void setCountry(String country)
   {
      this.country = country;
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
