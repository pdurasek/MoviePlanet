package MoviePlanet.DAO;

import MoviePlanet.DBAbstractionLayer.DLException;
import MoviePlanet.DBAbstractionLayer.MySQLDatabase;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Director
{

   private int directorID;
   private String name;
   private Date dob;
   private String country;
   private MySQLDatabase db = null;

   public Director(int directorID, MySQLDatabase db)
   {
      this.directorID = directorID;
      this.db = db;
   }

   public Director(String name, Date dob, String country, MySQLDatabase db)
   {
      this.name = name;
      this.dob = dob;
      this.country = country;
      this.db = db;
   }

   public Director(int directorID, String name, Date dob, String country, MySQLDatabase db)
   {
      this.directorID = directorID;
      this.name = name;
      this.dob = dob;
      this.country = country;
      this.db = db;
   }

   public void fetch()
   {
      String query = "SELECT directorID, name, dob, country" +
              " from director where directorID = ?;";
      ArrayList<ArrayList<String>> resultRow = null;
      ArrayList<String> values = new ArrayList<>();
      values.add(Integer.toString(this.directorID));

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
      boolean dataFound = false;
      ArrayList<String> values = new ArrayList<>();
      values.add(this.name);
      values.add(this.dob.toString());
      values.add(this.country);
      values.add(Integer.toString(this.directorID));
      String query = "UPDATE director set name= ?,dob= ?, country= ? " +
              "where directorID=?;";

      try
      {
         dataFound = db.setData(query, values);
      }
      catch (DLException e)
      {
         //TODO Handle exception
         System.out.println("Error while trying to create or execute the statement!");

      }
      if (dataFound)
      {
         //TODO Implement logic
      }
      else
      {
         System.out.println("No rows were found!");
      }
   }

   public void post()
   {
      boolean success = false;
      ArrayList<String> values = new ArrayList<>();
      values.add(Integer.toString(this.directorID));
      values.add(this.name);
      values.add(this.dob.toString());
      values.add(this.country);
      String query = "INSERT INTO director (name, dob, country) " + "VALUES (?, ?, ?)";

      try
      {
         success = db.setData(query, values);
      }
      catch (DLException e)
      {
         //TODO Catch proper exception
         System.out.println("Error while trying to create or execute the statement!");
      }
      if (success)
      {
         //TODO Implement logic
      }
      else
      {
         System.out.println("No rows were inserted!");
      }
   }

   public void delete()
   {
      boolean success = false;
      ArrayList<String> values = new ArrayList<>();
      values.add(Integer.toString(this.directorID));
      String query = "DELETE from equipment where equipID=?";

      try
      {
         success = db.setData(query, values);
      }
      catch (DLException e)
      {
         //TODO Catch proper exception
         System.out.println("Error while trying to create or execute the statement!");
      }

      if (success)
      {
         //TODO Implement logic
         System.out.println("Rows deleted successfully");
      }
      else
      {
         System.out.println("No rows were deleted!");
      }
   }

   public void setValues(ArrayList<ArrayList<String>> resultRow)
   {
      setDirectorID(Integer.parseInt(resultRow.get(0).get(0)));
      setName(resultRow.get(1).get(1));
      try
      {
         setDob(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(resultRow.get(0).get(2)).getTime()));
      }
      catch (ParseException e)
      {
         //TODO handle exception
         e.printStackTrace();
      }
      setCountry(resultRow.get(3).get(3));
   }

   public int getDirectorID()
   {
      return directorID;
   }

   public void setDirectorID(int directorID)
   {
      this.directorID = directorID;
   }

   public String getName()
   {
      return name;
   }

   public void setName(String firbstName)
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
