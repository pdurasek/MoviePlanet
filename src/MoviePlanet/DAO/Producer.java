package MoviePlanet.DAO;

import MoviePlanet.DBAbstractionLayer.MySQLDatabase;

import java.util.Date;

public class Producer
{
   private int producerID;
   private String firstName;
   private String lastName;
   private Date dob;
   private String country;
   private MySQLDatabase db;

   public Producer(int producerID, MySQLDatabase db)
   {
      this.producerID = producerID;
      this.db = db;
   }

   public Producer(int producerID, String firstName, String lastName, Date dob, String country, MySQLDatabase db)
   {
      this.producerID = producerID;
      this.firstName = firstName;
      this.lastName = lastName;
      this.dob = dob;
      this.country = country;
      this.db = db;
   }

   public int getProducerID()
   {
      return producerID;
   }

   public void setProducerID(int producerID)
   {
      this.producerID = producerID;
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
}
