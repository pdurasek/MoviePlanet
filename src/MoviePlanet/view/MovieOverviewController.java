package MoviePlanet.view;

import MoviePlanet.DAO.Genre;
import MoviePlanet.DAO.Movie;
import MoviePlanet.DBAbstractionLayer.DLException;
import MoviePlanet.DBAbstractionLayer.MySQLDatabase;
import MoviePlanet.MainApp;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MovieOverviewController
{
   @FXML
   private AnchorPane anchorPane;
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
   private HashMap<Integer, Movie> initialMovies = new HashMap<>();
   private HashMap<Integer, Movie> filteredMovies = new HashMap<>();

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
      anchorPane.setStyle("-fx-background-color: #181818");
      //movieScrollPane.setStyle("-fx-box-border: transparent;");
      //movieScrollPane.setStyle("-fx-focus-color: #181818 ; -fx-faint-focus-color: #181818 ;");

      generateMovieGrid(initialMovies);
   }

   private void getSelectedMovies()
   {

   }

   private void generateMovieGrid(HashMap map)
   {
      //moviePane = new GridPane();
      moviePane.getChildren().clear();
      int rowCount = (int) Math.ceil(map.size() / 5.0);
      int counter = 0;

      for (int i = 0; i < rowCount; ++i)
      {
         if (map.size() % 5 == 0)
         {
            for (int j = 0; j < 5; ++j)
            {
               setContent(i, j, counter, map);
               counter++;
            }
         }
         else
         {
            if (i == rowCount - 1)
            {
               for (int j = 0; j < map.size() % 5; ++j)
               {
                  setContent(i, j, counter, map);
                  counter++;
               }
            }
            else
            {
               for (int j = 0; j < 5; ++j)
               {
                  setContent(i, j, counter, map);
                  counter++;
               }
            }
         }
      }

   }

   private void setContent(int i, int j, int counter, HashMap<Integer, Movie> map)
   {
      Image img = new Image("media/" + map.get(map.keySet().toArray()[counter]).getImage());
      ImageView iv = new ImageView(img);
      Label titleLabel = new Label(map.get(map.keySet().toArray()[counter]).getName());
      generateView(iv, titleLabel, i, j);
   }

   private void generateView(ImageView iv, Label titleLabel, int i, int j)
   {
      iv.setFitWidth(190);
      titleLabel.setAlignment(Pos.TOP_CENTER);
      titleLabel.setWrapText(true);
      titleLabel.setTextFill(Paint.valueOf("#bfbfbf"));
      titleLabel.setPadding(new Insets(5, 0, 0, 10));

      VBox moviePoster = new VBox();
      moviePoster.getChildren().add(iv);
      moviePoster.getChildren().add(titleLabel);
      moviePane.add(moviePoster, j, i);
      moviePane.setAlignment(Pos.TOP_CENTER);
      moviePane.setVgap(50);
      moviePane.setHgap(15);

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

      Label clear = new Label("Top Movies");
      clear.setTextFill(Paint.valueOf("#bfbfbf"));
      clear.setPadding(new Insets(10, 0, 0, 0));
      clear.setOnMouseClicked(event ->
              getInitialMovies());
      filterVBox.getChildren().add(clear);

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
            filteredMovies = new HashMap<Integer, Movie>();
            label.setStyle("-fx-background-color: lightgray;");
            getSelectedGenre(label.getId());
            generateMovieGrid(filteredMovies);
         });
         filterVBox.getChildren().add(label);
      }

      filterVBox.setAlignment(Pos.TOP_CENTER);
   }

   public void getMovieTitles()
   {
      String sql = "SELECT movieID FROM movie ORDER BY score DESC LIMIT 10;";
      try
      {
         ArrayList<ArrayList<String>> result = db.getData(sql, null);

         getMovies(result, initialMovies);
      }
      catch (DLException e)
      {
         System.out.println("Failed to fetch genres");
      }
   }

   public void getSelectedGenre(String genre)
   {
      String sql = "SELECT DISTINCT Movie_movieID FROM movie_has_genre WHERE Genre_genreID = ?";
      try
      {
         ArrayList<String> values = new ArrayList<>();
         values.add(genre);
         ArrayList<ArrayList<String>> result = db.getData(sql, values);
         ArrayList<String> movieList = new ArrayList<>();
         StringBuilder builder = new StringBuilder();
         for (int i = 0; i < result.size(); ++i)
         {
            ArrayList<String> row = result.get(i);
            builder.append("?,");

            int movieID = Integer.parseInt(row.get(0));
            movieList.add(Integer.toString(movieID));
         }

         sql = "SELECT movieID FROM movie WHERE movieID in (" + builder.deleteCharAt(builder.length() - 1).toString() + " );"; //ORDER BY and LIMIT?
         result = db.getData(sql, movieList);
         getMovies(result, filteredMovies);

      }
      catch (DLException e)
      {
         e.printStackTrace();
         System.out.println("Failed to fetch genres");
      }
   }

   private void getMovies(ArrayList<ArrayList<String>> result, HashMap<Integer, Movie> map)
   {
      for (int i = 0; i < result.size(); ++i)
      {
         ArrayList<String> row = result.get(i);

         int movieID = Integer.parseInt(row.get(0));
         Movie movie = new Movie(movieID, db);
         movie.fetch();
         map.put(movie.getMovieID(), movie);
      }
   }
}
