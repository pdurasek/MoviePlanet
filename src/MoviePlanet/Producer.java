package MoviePlanet;

import java.util.Date;

public class Producer
{
   private int producerID;
   private String firstName;
   private String lastName;
   private Date dob;
   private String country;

   public Producer()
   {
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
