package MoviePlanet.DAO;

import MoviePlanet.DBAbstractionLayer.DLException;
import MoviePlanet.DBAbstractionLayer.MySQLDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Director
{

   private int directorID;
   private String firstName;
   private String lastName;
   private Date dob;
   private String country;
   private MySQLDatabase db = null;

   public Director(int directorID, MySQLDatabase db)
   {
      this.directorID = directorID;
      this.db = db;
   }

   public Director(int directorID, String firstName, String lastName, Date dob, String country, MySQLDatabase db)
   {
      this.directorID = directorID;
      this.firstName = firstName;
      this.lastName = lastName;
      this.dob = dob;
      this.country = country;
      this.db = db;
   }

   public void fetch()
   {
      ArrayList<ArrayList<String>> resultRow = null;
      ArrayList<String> values = new ArrayList<>();
      values.add(Integer.toString(this.directorID));
      String query = "SELECT directorID, firstName, lastName, dob, country" +
              " from director where directorID = ?;";

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
      values.add(this.firstName);
      values.add(this.lastName);
      values.add(this.dob.toString());
      values.add(this.country);
      values.add(Integer.toString(this.directorID));
      String query = "UPDATE director set firstName= ?, lastName = ? ,dob= ?, country= ? " +
              "where equipID=?;";

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
      values.add(this.firstName);
      values.add(this.lastName);
      values.add(this.dob.toString());
      values.add(this.country);
      String query = "INSERT INTO director (directorID, firstName, lastName, dob, country) " +
              "VALUES (?, ?, ?, ?, ?)";

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
      setFirstName(resultRow.get(1).get(1));
      setLastName(resultRow.get(2).get(2));
      try
      {
         setDob(new SimpleDateFormat("yyyy/MM/dd").parse(resultRow.get(3).get(3)));
      }
      catch (ParseException e)
      {
         //TODO handle exception
         e.printStackTrace();
      }
      setCountry(resultRow.get(4).get(4));
   }

   public int getDirectorID()
   {
      return directorID;
   }

   public void setDirectorID(int directorID)
   {
      this.directorID = directorID;
   }

   public String getFirstName()
   {
      return firstName;
   }

   public void setFirstName(String firbstName)
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
