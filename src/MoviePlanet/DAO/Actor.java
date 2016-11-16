package MoviePlanet.DAO;

import MoviePlanet.DBAbstractionLayer.MySQLDatabase;

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
