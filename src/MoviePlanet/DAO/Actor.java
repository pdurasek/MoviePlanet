package MoviePlanet.DAO;

import java.util.Date;

public class Actor
{

   private int actorID;
   private String firstName;
   private String lastName;
   private Date dob;
   private String country;

   public Actor()
   {
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
}
