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
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringJoiner;

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
   @FXML
   private TextField searchBar;

   private MainApp mainApp;
   private MySQLDatabase db;
   private LinkedHashMap<Integer, Genre> genres = new LinkedHashMap<>();
   private LinkedHashMap<Integer, Movie> initialMovies = new LinkedHashMap<>();
   private LinkedHashMap<Integer, Movie> filteredMovies = new LinkedHashMap<>();
   private LinkedHashMap<Integer, Movie> searchedMovies = new LinkedHashMap<>();
   private LinkedHashMap<String, String> secondaryFilters = new LinkedHashMap<>();
   private ArrayList<String> movieList = new ArrayList<>();
   private StringBuilder builder = new StringBuilder();
   private String selectedSecondary = "score";
   private String order = "asc";

   public MovieOverviewController()
   {
   }

   @FXML
   private void initialize()
   {
      secondaryFilters.put("Rating", "score");
      secondaryFilters.put("A-Z", "name");
      secondaryFilters.put("Release Date", "year");
      secondaryFilters.put("Duration", "screenTime");

      searchBar.setOnKeyReleased(event ->
      {
         search();
      });
      searchBar.setDisable(true);
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
      /*WebView webView = new WebView();
      webView.getEngine().load("https://www.youtube.com/embed/u3jVet3ZWPw");
      webView.setPrefSize(640, 390);
      moviePane.getChildren().add(webView);*/
   }

   private void getSelectedMovies()
   {

   }

   private void generateMovieGrid(LinkedHashMap map)
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

   private void setContent(int i, int j, int counter, LinkedHashMap<Integer, Movie> map)
   {
      Movie currentMovie = map.get(map.keySet().toArray()[counter]);
      Image img = new Image("media/" + currentMovie.getImage());
      ImageView iv = new ImageView(img);

      iv.setOnMouseClicked(event ->
      {
         /*try
         {
            StringBuilder genreBuilder = new StringBuilder();
            String sql = "SELECT Genre_genreID from movie_has_genre where Movie_movieID = ?";
            ArrayList<String> values = new ArrayList<>();
            values.add(Integer.toString(currentMovie.getMovieID()));
            ArrayList<ArrayList<String>> selectedGenres = db.getData(sql, values);

            values.clear();

            for (int x = 0; x < selectedGenres.size(); ++x)
            {
               ArrayList<String> row = selectedGenres.get(x);
               genreBuilder.append("?,");

               values.add(row.get(0));
            }
            genreBuilder .deleteCharAt(genreBuilder.length() - 1);
            sql = "SELECT name from genre where genreID IN (" + genreBuilder.toString() +")";
            selectedGenres.clear();
            selectedGenres = db.getData(sql, values);

            mainApp.setStage(false, currentMovie);
         }
         catch (DLException e)
         {
            e.printStackTrace();
         }*/
         String movieID = Integer.toString(currentMovie.getMovieID());

         ArrayList<String> genres = fetchDetails(movieID, "Genre_genreID", "movie_has_genre",
                 "genre", "genreID");
         ArrayList<String> actor = fetchDetails(movieID, "Actor_actorID", "actor_has_movie",
                 "actor", "actorID");
         ArrayList<String> writers = fetchDetails(movieID, "Writer_writerID", "movie_has_writer",
                 "writer", "writerID");
         ArrayList<String> directors = fetchDetails(movieID, "Director_directorID", "director_has_movie",
                 "director",  "directorID");
         ArrayList<String> producers = fetchDetails(movieID, "Producer_producerID", "movie_has_producer",
                 "producer", "producerID");

         mainApp.setStage(false, currentMovie, genres, actor, writers, directors, producers);
      });
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
      moviePane.setVgap(55);
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
      {
         getInitialMovies();
         disableFilters(true);
      });
      filterVBox.getChildren().add(clear);
      Separator separatorGenres = new Separator();
      separatorGenres.setPadding(new Insets(5, 5, 0, 10));
      filterVBox.getChildren().add(separatorGenres);

      for (Map.Entry<Integer, Genre> entry : genres.entrySet())
      {
         Genre genre = entry.getValue();
         Label label = new Label(genre.getName());
         label.setId(Integer.toString(genre.getGenreID()));
         label.setTextFill(Paint.valueOf("#bfbfbf"));
         label.setPadding(new Insets(10, 0, 0, 0));
         label.setOnMouseClicked(event ->
         {
            filteredMovies = new LinkedHashMap<Integer, Movie>();
            //label.setStyle("-fx-background-color: lightgray;");
            getSelectedGenre(label.getId());
            generateMovieGrid(filteredMovies);
            disableFilters(false);
         });
         filterVBox.getChildren().add(label);
      }

      Separator separatorSecondary = new Separator();
      separatorSecondary.setPadding(new Insets(5, 5, 0, 10));
      filterVBox.getChildren().add(separatorSecondary);

      for (Map.Entry<String, String> entry : secondaryFilters.entrySet())
      {
         Label label = new Label(entry.getKey());
         label.setTextFill(Paint.valueOf("#bfbfbf"));
         label.setPadding(new Insets(10, 0, 0, 0));
         label.setDisable(true);
         label.setOnMouseClicked(event ->
         {
            if (selectedSecondary.equals(entry.getValue()))
            {
               if (order.equals("asc"))
               {
                  order = "desc";
               }
               else
               {
                  order = "asc";
               }
            }
            else
            {
               order = "asc";
            }
            selectedSecondary = entry.getValue();
            fetchFilteredMovies();
            generateMovieGrid(filteredMovies);
         });

         filterVBox.getChildren().add(label);
      }

      filterVBox.setAlignment(Pos.TOP_CENTER);
   }

   public void getMovieTitles()
   {
      String sql = "SELECT movieID FROM movie ORDER BY score DESC LIMIT 20;";
      try
      {
         ArrayList<ArrayList<String>> result = db.getData(sql, null);

         getMovies(result, initialMovies);
      }
      catch (DLException e)
      {
         System.out.println("Failed to fetch movies");
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
         movieList = new ArrayList<>();
         builder = new StringBuilder();
         for (int i = 0; i < result.size(); ++i)
         {
            ArrayList<String> row = result.get(i);
            builder.append("?,");

            int movieID = Integer.parseInt(row.get(0));
            movieList.add(Integer.toString(movieID));
         }
         builder.deleteCharAt(builder.length() - 1);
         fetchFilteredMovies();
      }
      catch (DLException e)
      {
         e.printStackTrace();
         System.out.println("Failed to fetch genres");
      }
   }

   private void fetchFilteredMovies()
   {
      String sql = "SELECT movieID FROM movie WHERE movieID in (" + builder.toString() + " ) " +
              "ORDER BY " + selectedSecondary + " " + order + ";"; //ORDER BY and LIMIT?
      ArrayList<ArrayList<String>> result = null;
      try
      {
         result = db.getData(sql, movieList);
      }
      catch (DLException e)
      {
         e.printStackTrace();
      }
      getMovies(result, filteredMovies);
   }

   private void getMovies(ArrayList<ArrayList<String>> result, LinkedHashMap<Integer, Movie> map)
   {
      //map = new LinkedHashMap<>();
      map.clear();
      for (int i = 0; i < result.size(); ++i)
      {
         ArrayList<String> row = result.get(i);

         int movieID = Integer.parseInt(row.get(0));
         Movie movie = new Movie(movieID, db);
         movie.fetch();
         map.put(movie.getMovieID(), movie);
      }
   }

   private void disableFilters(boolean isFiltered)
   {
      int size = filterVBox.getChildren().size();
      searchBar.setDisable(isFiltered);

      for (int i = size - 4; i < size; i++)
      {
         filterVBox.getChildren().get(i).setDisable(isFiltered);
      }
   }

   private void search()
   {
      searchedMovies.clear();
      String searchTerm = searchBar.getText();

      for (Map.Entry<Integer, Movie> entry : filteredMovies.entrySet())
      {
         String name = entry.getValue().getName();

         if (name.toLowerCase().contains(searchTerm.toLowerCase()))
         {
            searchedMovies.put(entry.getKey(), entry.getValue());
         }
      }

      generateMovieGrid(searchedMovies);
   }

   private ArrayList<String> fetchDetails(String id, String selectTarget, String selectDestination, String targetDestination,
                                          String targetCondition)
   {
      ArrayList<String> values = new ArrayList<>();

      try
      {
         StringBuilder genreBuilder = new StringBuilder();
         String sql = "SELECT " + selectTarget + " from " + selectDestination + " where Movie_movieID = ?";
         values.add(id);
         ArrayList<ArrayList<String>> selectedGenres = db.getData(sql, values);

         values.clear();

         for (int x = 0; x < selectedGenres.size(); ++x)
         {
            ArrayList<String> row = selectedGenres.get(x);
            genreBuilder.append("?,");

            values.add(row.get(0));
         }
         genreBuilder.deleteCharAt(genreBuilder.length() - 1);
         sql = "SELECT name from " + targetDestination + " where " + targetCondition + " IN (" + genreBuilder.toString() + ")";
         selectedGenres.clear();
         selectedGenres = db.getData(sql, values);
         values.clear();

         for (int x = 0; x < selectedGenres.size(); x++)
         {
            values.add(selectedGenres.get(x).get(0));
         }

      }
      catch (DLException e)
      {
         e.printStackTrace();
      }

      return values;
   }
}
