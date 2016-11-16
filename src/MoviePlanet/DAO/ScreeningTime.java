package MoviePlanet.DAO;

import MoviePlanet.DBAbstractionLayer.MySQLDatabase;

public class ScreeningTime
{
   private int screeningTimeID;
   private int hour;
   private int minute;
   private int second;
   private MySQLDatabase db;

   public ScreeningTime(int screeningTimeID, MySQLDatabase db)
   {
      this.screeningTimeID = screeningTimeID;
      this.db = db;
   }

   public ScreeningTime(int screeningTimeID, int hour, int minute, int second, MySQLDatabase db)
   {
      this.screeningTimeID = screeningTimeID;
      this.hour = hour;
      this.minute = minute;
      this.second = second;
      this.db = db;
   }

   public int getScreeningTimeID()
   {
      return screeningTimeID;
   }

   public void setScreeningTimeID(int screeningTimeID)
   {
      this.screeningTimeID = screeningTimeID;
   }

   public int getHour()
   {
      return hour;
   }

   public void setHour(int hour)
   {
      this.hour = hour;
   }

   public int getMinute()
   {
      return minute;
   }

   public void setMinute(int minute)
   {
      this.minute = minute;
   }

   public int getSecond()
   {
      return second;
   }

   public void setSecond(int second)
   {
      this.second = second;
   }
}
