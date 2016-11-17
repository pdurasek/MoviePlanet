package MoviePlanet.view;

import MoviePlanet.MainApp;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class MovieOverviewController
{
   @FXML
   private BorderPane basePane;
   @FXML
   private ScrollPane movieScrollPane;
   @FXML
   private GridPane moviePane;

   private MainApp mainApp;

   public MovieOverviewController()
   {
   }

   @FXML
   private void initialize()
   {
      basePane.setStyle("-fx-background-color: #181818");
      movieScrollPane.setStyle("-fx-background-color: #181818");
      moviePane.setStyle("-fx-background-color: #181818");
      movieScrollPane.setStyle("-fx-box-border: transparent;");
      //movieScrollPane.setStyle("-fx-focus-color: #181818 ; -fx-faint-focus-color: #181818 ;");
      ImageView iv = null;
      for (int i = 0; i < 5; ++i)
      {
         for (int j = 0; j < 5; ++j)
         {
            if (i%2 == 0)
            {
               Image img = new Image("media/lotr2.jpg");
               iv = new ImageView(img);
               iv.setFitWidth(190);
            }
            else
            {
               Image img = new Image("media/shokugeki2.jpg");
               iv = new ImageView(img);
            }
            /*TextArea b = new TextArea(Double.toString(movieScrollPane.getPrefWidth()));
            b.setMinHeight(275);*/
            moviePane.add(iv, i, j);
            moviePane.setAlignment(Pos.TOP_CENTER);
            moviePane.setVgap(50);
            moviePane.setHgap(15);
         }
      }
   }

   public void setMainApp(MainApp mainApp)
   {
      this.mainApp = mainApp;


      //grid
   }
}
