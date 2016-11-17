package MoviePlanet.DAO;

import MoviePlanet.DBAbstractionLayer.DLException;
import MoviePlanet.DBAbstractionLayer.MySQLDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Actor
{

   private int actorID;
   private String firstName;
   private String lastName;
   private Date dob;
   private String country;
   private MySQLDatabase db;

   public Actor(int actorID, MySQLDatabase db)
   {
      this.actorID = actorID;
      this.db = db;
   }

   public Actor(int actorID, String firstName, String lastName, Date dob, String country, MySQLDatabase db)
   {
      this.actorID = actorID;
      this.firstName = firstName;
      this.lastName = lastName;
      this.dob = dob;
      this.country = country;
      this.db = db;
   }

   public void fetch()
   {
      String query = "SELECT actorID, firstName, lastName, dob, country FROM actor WHERE actorID = ?;";
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
      String query = "UPDATE actor SET firstName = ?, lastName = ?, dob = ?, country = ? WHERE actorID = ?;";
      boolean dataFound = false;
      ArrayList<String> values = new ArrayList<>();
      values.add(this.firstName);
      values.add(this.lastName);
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
      String query = "INSERT INTO actor (actorID, firstName, lastName, dob, country) VALUES (?, ?, ?, ?, ?);";
      boolean success = false;
      ArrayList<String> values = new ArrayList<>();
      values.add(Integer.toString(this.actorID));
      values.add(this.firstName);
      values.add(this.lastName);
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
      setFirstName(resultRow.get(0).get(1));
      setLastName(resultRow.get(0).get(2));
      try
      {
         setDob(new SimpleDateFormat("yyyy/MM/dd").parse(resultRow.get(0).get(3)));
      }
      catch (ParseException e)
      {
         //TODO handle exception
         e.printStackTrace();
      }
      setCountry(resultRow.get(0).get(4));
   }

   public int getActorID()
   {
      return actorID;
   }

   public void setActorID(int actorID)
   {
      this.actorID = actorID;
   }

   public String getFirstName()
   {
      return firstName;
   }

   public void setFirstName(String firstName)
   {
      this.firstName = firstName;
   }

   public String getLastName()
   {
      return lastName;
   }

   public void setLastName(String lastName)
   {
      this.lastName = lastName;
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
