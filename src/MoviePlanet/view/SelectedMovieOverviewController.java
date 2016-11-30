package MoviePlanet.view;

import MoviePlanet.DAO.Movie;
import MoviePlanet.MainApp;
import javafx.fxml.FXML;
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
   private Label descriptionArea;
   @FXML
   private Label producerLabel;
   @FXML
   private Label directorLabel;
   @FXML
   private Label writerLabel;

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
         mainApp.setStage(true, null, null, null, null, null, null);
      });
      videoPane.setStyle("-fx-background-color: #181818");
      anchorPane.setStyle("-fx-background-color: #181818");
   }

   public void setMainApp(MainApp mainApp)
   {
      this.mainApp = mainApp;
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
}
