package MoviePlanet.view;

import MoviePlanet.DBAbstractionLayer.DLException;
import MoviePlanet.DBAbstractionLayer.MySQLDatabase;
import MoviePlanet.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class LoginOverviewController
{
   @FXML
   private TextField username;
   @FXML
   private PasswordField password;
   @FXML
   private Button loginButton;

   private MainApp mainApp;
   private MySQLDatabase db;

   @FXML
   private void initialize()
   {
      loginButton.setOnMouseClicked(event -> login());
   }

   public void setMainApp(MainApp mainApp)
   {
      this.mainApp = mainApp;
   }

   public void setDb(MySQLDatabase db)
   {
      this.db = db;
   }

   private void login()
   {
      String sql = "SELECT password FROM user WHERE username = ?";
      ArrayList<String> values = new ArrayList<String>();
      values.add(username.getText());

      try
      {
         ArrayList<ArrayList<String>> result = db.getData(sql, values);
         String passwordStr = result.get(0).get(0);

         if (password.getText().equals(passwordStr))
         {
            mainApp.showMovieOverview();
         }
         else
         {
          incorretLogin();
         }
      }
      catch (DLException | IndexOutOfBoundsException e)
      {
         incorretLogin();
      }
   }

   private void incorretLogin()
   {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.initOwner(mainApp.getPrimaryStage());
      alert.setTitle("Incorrect Login");
      alert.setHeaderText("Username or password incorrect");
      alert.setContentText("Please re-enter the username and password");

      alert.showAndWait();
   }
}
