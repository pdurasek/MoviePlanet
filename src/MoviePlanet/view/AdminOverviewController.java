package MoviePlanet.view;

import MoviePlanet.DBAbstractionLayer.MySQLDatabase;
import MoviePlanet.MainApp;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;

public class AdminOverviewController
{
   private MainApp mainApp;
   private MySQLDatabase db;

   @FXML
   private void initialize()
   {

   }

   public void setMainApp(MainApp mainApp)
   {
      this.mainApp = mainApp;
   }

   public void setDb(MySQLDatabase db)
   {
      this.db = db;
   }
}
