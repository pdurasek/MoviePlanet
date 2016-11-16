package MoviePlanet.DAO;

public class ScreeningTime
{
   private int screeningTimeID;
   private int hour;
   private int minute;
   private int second;

   public ScreeningTime()
   {
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
