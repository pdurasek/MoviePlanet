package MoviePlanet.view;

import MoviePlanet.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebView;

public class SelectedMovieOverviewController
{
   @FXML
   private Button button;
   @FXML
   private Pane videoPane;

   private MainApp mainApp;

   /**
    * Initializes the controller class. This method is automatically called
    * after the fxml file has been loaded.
    */
   @FXML
   private void initialize()
   {
      button.setOnMouseClicked(event ->
      {
         mainApp.setStage(true);
      });
      videoPane.setStyle("-fx-background-color: #181818");

      WebView webView = new WebView();
      webView.getEngine().load("https://www.youtube.com/embed/u3jVet3ZWPw");
      webView.setPrefSize(640, 390);
      videoPane.getChildren().add(webView);
   }

   public void setMainApp(MainApp mainApp)
   {
      this.mainApp = mainApp;
   }
}
