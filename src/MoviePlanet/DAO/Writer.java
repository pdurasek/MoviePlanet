package MoviePlanet.DAO;

import MoviePlanet.DBAbstractionLayer.DLException;
import MoviePlanet.DBAbstractionLayer.MySQLDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Writer
{
   private int writerID;
   private String name;
   private Date dob;
   private String country;
   private MySQLDatabase db = null;

   public Writer(int writerID, MySQLDatabase db)
   {
      this.writerID = writerID;
      this.db = db;
   }

   public Writer(String name, Date dob, String country, MySQLDatabase db)
   {
      this.name = name;
      this.dob = dob;
      this.country = country;
      this.db = db;
   }

   public Writer(int writerID, String name, Date dob, String country, MySQLDatabase db)
   {
      this.writerID = writerID;
      this.name = name;
      this.dob = dob;
      this.country = country;
      this.db = db;
   }

   public void fetch()
   {
      String query = "SELECT writerID, name, dob, country FROM writer WHERE writerID = ?;";
      ArrayList<ArrayList<String>> resultRow = null;
      ArrayList<String> values = new ArrayList<>();
      values.add(Integer.toString(this.writerID));
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
      String query = "UPDATE writer SET name = ?, dob = ?, country = ? WHERE writerID = ?;";
      boolean dataFound = false;
      ArrayList<String> values = new ArrayList<>();
      values.add(this.name);
      values.add(this.dob.toString());
      values.add(this.country);
      values.add(Integer.toString(this.writerID));

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
      String query = "INSERT INTO writer (writerID, name, dob, country) VALUES (?, ?, ?, ?, ?);";
      boolean success = false;
      ArrayList<String> values = new ArrayList<>();
      values.add(Integer.toString(this.writerID));
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
      String query = "DELETE from writer WHERE writerID = ?";
      boolean success = false;
      ArrayList<String> values = new ArrayList<>();
      values.add(Integer.toString(this.writerID));

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
      setWriterID(Integer.parseInt(resultRow.get(0).get(0)));
      setName(resultRow.get(0).get(1));
      try
      {
         setDob(new SimpleDateFormat("yyyy-MM-dd").parse(resultRow.get(0).get(2)));
      }
      catch (ParseException e)
      {
         //TODO handle exception
         e.printStackTrace();
      }
      setCountry(resultRow.get(0).get(3));
   }

   public int getWriterID()
   {
      return writerID;
   }

   public void setWriterID(int writerID)
   {
      this.writerID = writerID;
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
