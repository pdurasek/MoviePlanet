package MoviePlanet;

import MoviePlanet.DBAbstractionLayer.DLException;
import MoviePlanet.DBAbstractionLayer.MySQLDatabase;
import MoviePlanet.view.MovieOverviewController;
import MoviePlanet.view.SelectedMovieOverviewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MainApp extends Application
{
   private Stage primaryStage;
   private Scene mainScreen;
   private BorderPane rootLayout;
   private MySQLDatabase db;

   @Override
   public void start(Stage primaryStage)
   {
      this.primaryStage = primaryStage;
      this.primaryStage.setTitle("MoviePlanet");
      this.primaryStage.getIcons().add(new Image("media/icon.png"));

      db = setConnectionData("dbinfo");

      try
      {
         if (db.connect())
         {
            initRootLayout();
            showMovieOverview();
         }
      }
      catch (DLException e)
      {
         System.out.println("Cannot start application, database unreachable");
         System.exit(0);
      }
   }

   public void initRootLayout()
   {
      try
      {
         FXMLLoader loader = new FXMLLoader();
         loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
         rootLayout = (BorderPane) loader.load();

         mainScreen = new Scene(rootLayout);
         primaryStage.setScene(mainScreen);
         primaryStage.show();

         /*WebView webView = new WebView();
         webView.getEngine().load("https://www.youtube.com/embed/u3jVet3ZWPw");
         webView.setPrefSize(640, 390);
         primaryStage.setScene(new Scene(webView));
         primaryStage.show();*/
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
   }

   public void showMovieOverview()
   {
      try
      {
         FXMLLoader loader = new FXMLLoader();
         loader.setLocation(MainApp.class.getResource("view/MovieOverview.fxml"));
         AnchorPane movieOverview = (AnchorPane) loader.load();

         rootLayout.setCenter(movieOverview);

         MovieOverviewController controller = loader.getController();
         controller.setDb(db);
         controller.getMovieTitles();
         controller.getFilters();
         controller.getInitialMovies();
         controller.setMainApp(this);
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
   }

   public Stage getPrimaryStage()
   {
      return primaryStage;
   }

   public static void main(String[] args)
   {
      launch(args);
   }

   public void setStage(boolean version)
   {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(MainApp.class.getResource("view/SelectedMovieOverview.fxml"));
      try
      {
         AnchorPane page = (AnchorPane) loader.load();
         Stage testStage = new Stage();
         testStage.setTitle("Test");
         Scene scene = new Scene(page);
         if (version)
         {
            primaryStage.setScene(mainScreen);
         }
         else
         {
            primaryStage.setScene(scene);
         }

         SelectedMovieOverviewController controller = loader.getController();
         controller.setMainApp(this);
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
   }

   private MySQLDatabase setConnectionData(String filename)
   {
      Properties dbInfo = new Properties();
      try
      {
         InputStream infoStream = new FileInputStream(filename + ".properties");
         dbInfo.load(infoStream);
      }
      catch (FileNotFoundException e)
      {
         System.out.println("Property file not found!");
         System.exit(0);
      }
      catch (IOException e)
      {
         System.out.println("Error while reading the property file");
         System.exit(0);
      }

      return new MySQLDatabase(dbInfo.getProperty("username"), dbInfo.getProperty("password"), dbInfo.getProperty("dbms"));
   }
}
