package MoviePlanet.view;

import MoviePlanet.DAO.Genre;
import MoviePlanet.DBAbstractionLayer.DLException;
import MoviePlanet.DBAbstractionLayer.MySQLDatabase;
import MoviePlanet.MainApp;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MovieOverviewController
{
   @FXML
   private BorderPane basePane;
   @FXML
   private ScrollPane movieScrollPane;
   @FXML
   private GridPane moviePane;
   @FXML
   private VBox filterVBox;

   private MainApp mainApp;
   private MySQLDatabase db;
   private HashMap<Integer, Genre> genres = new HashMap<>();

   public MovieOverviewController()
   {
   }

   @FXML
   private void initialize()
   {

   }

   public void setMainApp(MainApp mainApp)
   {
      this.mainApp = mainApp;


      //grid
   }

   public void setDb(MySQLDatabase db)
   {
      this.db = db;
   }

   public void getInitialMovies()
   {
      basePane.setStyle("-fx-background-color: #181818");
      movieScrollPane.setStyle("-fx-background-color: #181818");
      moviePane.setStyle("-fx-background-color: #181818");
      movieScrollPane.setStyle("-fx-box-border: transparent;");
      //movieScrollPane.setStyle("-fx-focus-color: #181818 ; -fx-faint-focus-color: #181818 ;");

      VBox pane = null;
      ImageView iv = null;
      Label titleLabel = null;
      for (int i = 0; i < 5; ++i)
      {
         for (int j = 0; j < 5; ++j)
         {
            if (i % 2 == 0)
            {
               Image img = new Image("media/lotr2.jpg");
               iv = new ImageView(img);
               titleLabel = new Label("dinger");
            }
            else
            {
               Image img = new Image("media/shokugeki2.jpg");
               iv = new ImageView(img);
               titleLabel = new Label("donger");
            }

            iv.setFitWidth(190);
            titleLabel.setAlignment(Pos.TOP_CENTER);
            titleLabel.setTextFill(Paint.valueOf("#bfbfbf"));
            titleLabel.setPadding(new Insets(5, 0, 0, 10));

            pane = new VBox();
            pane.getChildren().add(iv);
            pane.getChildren().add(titleLabel);
            moviePane.add(pane, i, j);
            moviePane.setAlignment(Pos.TOP_CENTER);
            moviePane.setVgap(50);
            moviePane.setHgap(15);
         }
      }
   }

   public void getFilters()
   {
      String sql = "SELECT genreID FROM genre;";
      try
      {
         ArrayList<ArrayList<String>> result = db.getData(sql, null);

         for (int i = 0; i < result.size(); ++i)
         {
            ArrayList<String> row = result.get(i);

            int genreID = Integer.parseInt(row.get(0));
            Genre genre = new Genre(genreID, db);
            genre.fetch();
            genres.put(genre.getGenreID(), genre);
         }
      }
      catch (DLException e)
      {
         System.out.println("Failed to fetch genres");
      }

      for (Map.Entry<Integer, Genre> entry : genres.entrySet())
      {
         Genre genre = entry.getValue();
         Label label = new Label(genre.getName());
         label.setId(Integer.toString(genre.getGenreID()));
         label.setTextFill(Paint.valueOf("#bfbfbf"));
         label.setPadding(new Insets(10, 0, 0, 0));
         label.setOnMouseClicked(event ->
         {
            //label.setText("donger");
            // TODO handle genre change
         });
         filterVBox.getChildren().add(label);
      }

      filterVBox.setAlignment(Pos.TOP_CENTER);
   }
}
