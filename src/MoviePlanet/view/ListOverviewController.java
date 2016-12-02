package MoviePlanet.view;

import MoviePlanet.DAO.Movie;
import MoviePlanet.DAO.User;
import MoviePlanet.DBAbstractionLayer.DLException;
import MoviePlanet.DBAbstractionLayer.MySQLDatabase;
import MoviePlanet.MainApp;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class ListOverviewController
{
   @FXML
   private GridPane moviePane;
   @FXML
   private Button backButton;
   @FXML
   private ScrollPane scrollPane;

   private MainApp mainApp;
   private MySQLDatabase db;
   private User user;
   private Movie movie;
   private LinkedHashMap<Integer, Movie> movieList = new LinkedHashMap<>();
   private LinkedHashMap<Integer, Double> scoreList = new LinkedHashMap<>();

   @FXML
   private void initialize()
   {
      scrollPane.setStyle("-fx-background-color: #181818");

      backButton.setOnMouseClicked(event -> mainApp.showMovieOverview());
   }

   public void setMainApp(MainApp mainApp)
   {
      this.mainApp = mainApp;
   }

   public void setDb(MySQLDatabase db)
   {
      this.db = db;
   }

   public User getUser()
   {
      return user;
   }

   public void setUser(User user)
   {
      this.user = user;
   }

   public void retrieveListData()
   {
      String sql = "SELECT Movie_movieID, score FROM user_has_movie WHERE User_userID = ?";
      ArrayList<String> values = new ArrayList<String>();
      values.add(Integer.toString(user.getUserID()));

      try
      {
         ArrayList<ArrayList<String>> result = db.getData(sql, values);
         for (int i = 0; i < result.size(); ++i)
         {
            ArrayList<String> row = result.get(i);

            int movieID = Integer.parseInt(row.get(0));
            Movie movie = new Movie(movieID, db);
            movie.fetch();

            movieList.put(movieID, movie);
            if (row.get(1) != null)
            {
               scoreList.put(movieID, Double.parseDouble(row.get(1)));
            }

         }

         setContent();
      }
      catch (DLException | IndexOutOfBoundsException e)
      {
         e.printStackTrace();
      }
   }

   private void setContent()
   {
      int counter = 0;
      for (Map.Entry<Integer, Movie> entry : movieList.entrySet())
      {
         Movie movie = entry.getValue();
         Pane backgroundPane = new Pane();
         backgroundPane.setMinSize(600, 250);
         backgroundPane.setStyle("-fx-background-image: url(" + getImagePath(movie.getImage()) + ")");
         Pane contentPane = new Pane();
         contentPane.setMinSize(600, 250);
         contentPane.setStyle("-fx-background-color:  rgba(24, 24, 24, 0.85)");
         backgroundPane.getChildren().addAll(contentPane);
         moviePane.add(backgroundPane, 0, counter);
         counter++;

         Button remove = new Button("Remove");
         remove.setOnMouseClicked(event ->
         {
            String sql = "DELETE FROM user_has_movie WHERE User_userID = ? AND Movie_movieID = ?";
            ArrayList<String> values = new ArrayList<String>();
            values.add(Integer.toString(user.getUserID()));
            values.add(Integer.toString(movie.getMovieID()));

            try
            {
               db.setData(sql, values);
               updateScore(movie);

               moviePane.getChildren().remove(backgroundPane);
            }
            catch (DLException e)
            {
               e.printStackTrace();
            }
         });
         Label movieTitle = new Label(movie.getName());
         movieTitle.setTextFill(Paint.valueOf("#bfbfbf"));
         movieTitle.setPadding(new Insets(5, 0, 0, 0));
         ComboBox<Double> score = new ComboBox<>();
         score.getItems().addAll(0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0);
         contentPane.getChildren().addAll(remove, movieTitle, score);
         remove.setTranslateX(20);
         remove.setTranslateY(125);
         movieTitle.setTranslateX(100);
         movieTitle.setTranslateY(125);
         score.setTranslateX(500);
         score.setTranslateY(125);

         if (scoreList.get(new Integer(entry.getKey())) != null)
         {
            score.setValue(scoreList.get(new Integer(entry.getKey())));
         }

         score.valueProperty().addListener(event ->
         {
            String sql = "UPDATE user_has_movie SET score = ? WHERE User_userID = ? AND Movie_movieID = ?";
            ArrayList<String> values = new ArrayList<String>();
            values.add(Double.toString(score.getValue()));
            values.add(Integer.toString(user.getUserID()));
            values.add(Integer.toString(movie.getMovieID()));

            try
            {
               db.setData(sql, values);

               updateScore(movie);
            }
            catch (DLException e)
            {
               e.printStackTrace();
            }
         });
      }
   }

   private String getImagePath(String movieImage)
   {
      String imageURIstart = movieImage.substring(0, movieImage.length() - 4);
      String imageURIend = movieImage.substring(movieImage.length() - 4);
      return "media/" + imageURIstart + "1" + imageURIend;
   }

   private void updateScore(Movie movie) throws DLException
   {
      String sql = "SELECT AVG(score) FROM user_has_movie WHERE Movie_movieID = ?";
      ArrayList<String> values = new ArrayList<>();
      values.add(Integer.toString(movie.getMovieID()));
      ArrayList<ArrayList<String>> res = db.getData(sql, values);

      sql = "UPDATE movie SET score = ? WHERE movieID = ?";
      values.clear();
      if(res.get(0).get(0) == null)
      {
         values.add(Double.toString(0));
      }
      else
      {
         values.add(res.get(0).get(0));
      }

      values.add(Integer.toString(movie.getMovieID()));
      db.setData(sql, values);
   }
}
