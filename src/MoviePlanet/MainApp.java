package MoviePlanet;

import MoviePlanet.DBAbstractionLayer.DLException;
import MoviePlanet.DBAbstractionLayer.MySQLDatabase;
import MoviePlanet.view.MovieOverviewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
   private BorderPane rootLayout;
   private MySQLDatabase db;

   @Override
   public void start(Stage primaryStage)
   {
      this.primaryStage = primaryStage;
      this.primaryStage.setTitle("MoviePlanet");

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

         Scene scene = new Scene(rootLayout);
         primaryStage.setScene(scene);
         primaryStage.show();
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
