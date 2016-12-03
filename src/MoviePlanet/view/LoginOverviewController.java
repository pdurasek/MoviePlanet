package MoviePlanet.view;

import MoviePlanet.DAO.User;
import MoviePlanet.DBAbstractionLayer.DLException;
import MoviePlanet.DBAbstractionLayer.MySQLDatabase;
import MoviePlanet.MainApp;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

import java.awt.event.KeyEvent;
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
      loginButton.setOnKeyPressed(event ->
      {
         if(event.getCode().equals(KeyCode.ENTER))
         {
            login();
         }
      });
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
      String sql = "SELECT password, userID FROM user WHERE username = ?";
      ArrayList<String> values = new ArrayList<String>();
      values.add(username.getText());

      try
      {
         ArrayList<ArrayList<String>> result = db.getData(sql, values);
         String passwordStr = result.get(0).get(0);

         if (password.getText().equals(passwordStr))
         {
            User user = new User(Integer.parseInt(result.get(0).get(1)), db);
            user.fetch();
            mainApp.setUser(user);
            mainApp.showMovieOverview();
         }
         else
         {
            incorrectLogin();
         }
      }
      catch (DLException | IndexOutOfBoundsException e)
      {
         incorrectLogin();
      }
   }

   private void incorrectLogin()
   {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.initOwner(mainApp.getPrimaryStage());
      alert.setTitle("Incorrect Login");
      alert.setHeaderText("Username or password incorrect");
      alert.setContentText("Please re-enter the username and password");

      alert.showAndWait();
   }
}
