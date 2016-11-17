package MoviePlanet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application
{
   private Stage primaryStage;
   private BorderPane rootLayout;

   @Override
   public void start(Stage primaryStage)
   {
      this.primaryStage = primaryStage;
      this.primaryStage.setTitle("MoviePlanet");

      initRootLayout();
      showMovieOverview();
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
}
