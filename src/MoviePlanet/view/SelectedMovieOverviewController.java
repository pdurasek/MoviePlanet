package MoviePlanet.view;

import MoviePlanet.DAO.Movie;
import MoviePlanet.DAO.User;
import MoviePlanet.DBAbstractionLayer.DLException;
import MoviePlanet.DBAbstractionLayer.MySQLDatabase;
import MoviePlanet.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextFlow;
import javafx.scene.web.WebView;

import java.util.ArrayList;

public class SelectedMovieOverviewController
{
   @FXML
   private AnchorPane anchorPane;
   @FXML
   private Button button;
   @FXML
   private Pane videoPane;
   @FXML
   private Label nameLabel;
   @FXML
   private Label castLabel;
   @FXML
   private Label screenTimeLabel;
   @FXML
   private Label yearLabel;
   @FXML
   private Label genreLabel;
   @FXML
   private Label scoreLabel;
   @FXML
   private Label descriptionArea;
   @FXML
   private Label producerLabel;
   @FXML
   private Label directorLabel;
   @FXML
   private Label writerLabel;
   @FXML
   private Button listButton;

   private MainApp mainApp;
   private Movie movie;
   private User user;
   private MySQLDatabase db;

   /**
    * Initializes the controller class. This method is automatically called
    * after the fxml file has been loaded.
    */
   @FXML
   private void initialize()
   {
      button.setOnMouseClicked(event ->
      {
         mainApp.setStage(true, null, null, null, null, null, null);
      });
      videoPane.setStyle("-fx-background-color: #181818");
      anchorPane.setStyle("-fx-background-color: #181818");
      listButton.setOnMouseClicked(event ->
      {
         addToList();
      });
   }

   public void setMainApp(MainApp mainApp)
   {
      this.mainApp = mainApp;
   }

   public User getUser()
   {
      return user;
   }

   public void setUser(User user)
   {
      this.user = user;
   }

   public MySQLDatabase getDb()
   {
      return db;
   }

   public void setDb(MySQLDatabase db)
   {
      this.db = db;
   }

   public void setDetails(Movie movie, ArrayList<String> genres, ArrayList<String> cast, ArrayList<String> writer, ArrayList<String> director,
                          ArrayList<String> producer)
   {
      this.movie = movie;

      WebView webView = new WebView();
      webView.getEngine().load(movie.getTrailer());
      webView.setPrefSize(640, 390);
      videoPane.getChildren().add(webView);

      nameLabel.setText(movie.getName());
      screenTimeLabel.setText(Integer.toString(movie.getScreenTime()) + " min");
      yearLabel.setText(Integer.toString(movie.getYear()));
      descriptionArea.setText(movie.getDescription());
      castLabel.setText(extractDetails(cast));
      producerLabel.setText(extractDetails(producer));
      directorLabel.setText(extractDetails(director));
      writerLabel.setText(extractDetails(writer));
      genreLabel.setText(extractDetails(genres));
      scoreLabel.setText(Double.toString(movie.getScore()));
   }

   private String extractDetails(ArrayList<String> details)
   {
      String detailText = details.get(0);

      for (int i = 1; i < details.size(); ++i)
      {
         detailText += ", " + details.get(i);
      }

      return detailText;
   }

   private void addToList()
   {
      String sql = "INSERT INTO user_has_movie (User_userID, Movie_movieID) VALUES (?, ?)";
      ArrayList<String> values = new ArrayList<String>();
      values.add(Integer.toString(user.getUserID()));
      values.add(Integer.toString(movie.getMovieID()));

      try
      {
         db.setData(sql, values);
      }
      catch (DLException e)
      {
         duplicateAdd();
      }
   }

   private void duplicateAdd()
   {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.initOwner(mainApp.getPrimaryStage());
      alert.setTitle("List Error");
      alert.setHeaderText("Movie already in list");
      alert.setContentText("You cannot add the same movie to the list twice");

      alert.showAndWait();
   }
}
