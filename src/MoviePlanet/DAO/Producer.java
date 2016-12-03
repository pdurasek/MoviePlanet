package MoviePlanet.DAO;

import MoviePlanet.DBAbstractionLayer.DLException;
import MoviePlanet.DBAbstractionLayer.MySQLDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Producer
{
   private int producerID;
   private String name;
   private Date dob;
   private String country;
   private MySQLDatabase db;

   public Producer(int producerID, MySQLDatabase db)
   {
      this.producerID = producerID;
      this.db = db;
   }

   public Producer(String name, Date dob, String country, MySQLDatabase db)
   {
      this.name = name;
      this.dob = dob;
      this.country = country;
      this.db = db;
   }

   public Producer(int producerID, String name, Date dob, String country, MySQLDatabase db)
   {
      this.producerID = producerID;
      this.name = name;
      this.dob = dob;
      this.country = country;
      this.db = db;
   }

   public void fetch()
   {
      String query = "SELECT producerID, name, dob, country FROM producer WHERE producerID = ?;";
      ArrayList<ArrayList<String>> resultRow = null;
      ArrayList<String> values = new ArrayList<>();
      values.add(Integer.toString(this.producerID));
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
      String query = "UPDATE producer SET name = ?, dob = ?, country = ? WHERE producerID = ?;";
      boolean dataFound = false;
      ArrayList<String> values = new ArrayList<>();
      values.add(this.name);
      values.add(this.dob.toString());
      values.add(this.country);
      values.add(Integer.toString(this.producerID));

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
      String query = "INSERT INTO producer (producerID, name, dob, country) VALUES (?, ?, ?, ?, ?);";
      boolean success = false;
      ArrayList<String> values = new ArrayList<>();
      values.add(Integer.toString(this.producerID));
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
      String query = "DELETE from producer WHERE producerID = ?";
      boolean success = false;
      ArrayList<String> values = new ArrayList<>();
      values.add(Integer.toString(this.producerID));

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
      setProducerID(Integer.parseInt(resultRow.get(0).get(0)));
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

   public int getProducerID()
   {
      return producerID;
   }

   public void setProducerID(int producerID)
   {
      this.producerID = producerID;
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
