package MoviePlanet.DAO;

import java.util.Date;

public class Writer
{
   private int writerID;
   private String firstName;
   private String lastName;
   private Date dob;
   private String country;

   public Writer()
   {
   }

   public int getWriterID()
   {
      return writerID;
   }

   public void setWriterID(int writerID)
   {
      this.writerID = writerID;
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
