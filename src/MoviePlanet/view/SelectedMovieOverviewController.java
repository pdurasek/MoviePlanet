package MoviePlanet.view;

import MoviePlanet.DAO.Movie;
import MoviePlanet.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebView;

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

   private MainApp mainApp;
   private Movie movie;

   /**
    * Initializes the controller class. This method is automatically called
    * after the fxml file has been loaded.
    */
   @FXML
   private void initialize()
   {
      button.setOnMouseClicked(event ->
      {
         mainApp.setStage(true, null);
      });
      videoPane.setStyle("-fx-background-color: #181818");
      anchorPane.setStyle("-fx-background-color: #181818");
   }

   public void setMainApp(MainApp mainApp)
   {
      this.mainApp = mainApp;
   }

   public void setMovie(Movie movie)
   {
      this.movie = movie;
   }

   public void setVideo()
   {
      WebView webView = new WebView();
      webView.getEngine().load(movie.getTrailer());
      webView.setPrefSize(640, 390);
      videoPane.getChildren().add(webView);

      nameLabel.setText(movie.getName());
      castLabel.setText("Neki Glumac");
      screenTimeLabel.setText(Integer.toString(movie.getScreenTime()) + " min");
      yearLabel.setText(Integer.toString(movie.getYear()));
   }
}
