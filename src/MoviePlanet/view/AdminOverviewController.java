package MoviePlanet.view;

import MoviePlanet.DAO.*;
import MoviePlanet.DBAbstractionLayer.DLException;
import MoviePlanet.DBAbstractionLayer.MySQLDatabase;
import MoviePlanet.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class AdminOverviewController
{
   @FXML
   private Button newMovie;
   @FXML
   private Button saveMovie;
   @FXML
   private CheckBox isMovieEdit;
   @FXML
   private TextField movieNameField;
   @FXML
   private TextField movieYearField;
   @FXML
   private TextField movieScreenTimeField;
   @FXML
   private TextField movieImageURLField;
   @FXML
   private TextField movieTrailerField;
   @FXML
   private TextArea movieDescriptionArea;
   @FXML
   private ComboBox<String> movieRatingCombo;
   @FXML
   private ComboBox<String> movieCombo;

   @FXML
   private Button clearPersonButton;
   @FXML
   private Button savePersonButton;
   @FXML
   private CheckBox isPersonEditCheckbox;
   @FXML
   private TextField personNameField;
   @FXML
   private TextField personDOBField;
   @FXML
   private TextField personCountryField;
   @FXML
   private ComboBox<String> personTypeCombo;
   @FXML
   private ComboBox<String> personCombo;

   @FXML
   private ComboBox<String> selectionItem;
   @FXML
   private ComboBox<String> addSelection;
   @FXML
   private ComboBox<String> removeSelection;
   @FXML
   private Button addItem;
   @FXML
   private Button removeItem;
   @FXML
   private Button saveSelection;
   @FXML
   private TextArea selectionTextArea;
   @FXML
   private Button clearSelection;
   @FXML
   private ComboBox<String> movieSelection;
   @FXML
   private Button populateButton;

   @FXML
   private Button clearUserButton;
   @FXML
   private CheckBox isUserEdit;
   @FXML
   private ComboBox<String> userCombo;
   @FXML
   private TextField usernameTextField;
   @FXML
   private TextField passwordTextField;
   @FXML
   private TextField accessLevelTextField;
   @FXML
   private Button saveUserButton;
   @FXML
   private Button backButton;

   private MainApp mainApp;
   private MySQLDatabase db;
   private LinkedHashMap<String, Movie> movieList = new LinkedHashMap<>();
   private LinkedHashMap<String, Rating> ratingList = new LinkedHashMap<>();
   private LinkedHashMap<String, Actor> actorList = new LinkedHashMap<>();
   private LinkedHashMap<String, Producer> producerList = new LinkedHashMap<>();
   private LinkedHashMap<String, Writer> writerList = new LinkedHashMap<>();
   private LinkedHashMap<String, Director> directorList = new LinkedHashMap<>();
   private LinkedHashMap<String, User> userList = new LinkedHashMap<>();
   private LinkedHashMap<String, Movie> selectedMovieList = new LinkedHashMap<>();
   private LinkedHashMap<String, Genre> genreList = new LinkedHashMap<>();
   private LinkedHashMap<String, Genre> activeGenres = new LinkedHashMap<>();
   private LinkedHashMap<String, Actor> actorToMovieList = new LinkedHashMap<>();
   private LinkedHashMap<String, Actor> activeActorToMovieList = new LinkedHashMap<>();
   private LinkedHashMap<String, Director> directorToMovieList = new LinkedHashMap<>();
   private LinkedHashMap<String, Director> activeDirectorToMovieList = new LinkedHashMap<>();
   private LinkedHashMap<String, Producer> producerToMovieList = new LinkedHashMap<>();
   private LinkedHashMap<String, Producer> activeProducerToMovieList = new LinkedHashMap<>();
   private LinkedHashMap<String, Writer> writerToMovieList = new LinkedHashMap<>();
   private LinkedHashMap<String, Writer> activeWriterToMovieList = new LinkedHashMap<>();

   @FXML
   private void initialize()
   {

      newMovie.setOnMouseClicked(event ->
      {
         resetTextFields(movieNameField, movieYearField, movieScreenTimeField, movieImageURLField, movieTrailerField);
         movieDescriptionArea.setText("");
      });

      clearPersonButton.setOnMouseClicked(event ->
              resetTextFields(personNameField, personDOBField, personCountryField));

      clearUserButton.setOnMouseClicked(event ->
              resetTextFields(usernameTextField, passwordTextField, accessLevelTextField));

      backButton.setOnMouseClicked(event -> mainApp.showMovieOverview());

      selectionTextArea.setStyle("-fx-text-fill: #181818");
   }

   public void setMainApp(MainApp mainApp)
   {
      this.mainApp = mainApp;
   }

   public void setDb(MySQLDatabase db)
   {
      this.db = db;
   }

   public void populateData()
   {
      try
      {
         generateMovieCombo(movieList, movieCombo);

         String sql = "SELECT ratingID, type FROM rating";
         ArrayList<ArrayList<String>> result = db.getData(sql, new ArrayList<>());

         for (int i = 0; i < result.size(); i++)
         {
            ArrayList<String> row = result.get(i);

            Rating rating = new Rating(Integer.parseInt(row.get(0)), row.get(1), db);
            ratingList.put(rating.getRatingType(), rating);
            movieRatingCombo.getItems().add(rating.getRatingType());
         }

         personTypeCombo.getItems().addAll("Actor", "Director", "Producer", "Writer");
         personTypeCombo.valueProperty().addListener(observable ->
         {
            personCombo.getItems().clear();
            directorList.clear();
            actorList.clear();
            producerList.clear();
            writerList.clear();
            String selectedPerson = personTypeCombo.getValue().toLowerCase();
            String sql1 = "SELECT " + selectedPerson + "ID, name, dob, country FROM " + selectedPerson;
            ArrayList<ArrayList<String>> res = null;
            try
            {
               res = db.getData(sql1, new ArrayList<>());

               for (int i = 0; i < res.size(); i++)
               {
                  ArrayList<String> row = res.get(i);

                  if (selectedPerson.equalsIgnoreCase("Director"))
                  {
                     Director director = new Director(Integer.parseInt(row.get(0)), row.get(1),
                             new Date(new SimpleDateFormat("yyyy-MM-dd").parse(row.get(2)).getTime()), row.get(3), db);

                     directorList.put(director.getName(), director);
                     personCombo.getItems().add(director.getName());
                  }
                  else if (selectedPerson.equalsIgnoreCase("Actor"))
                  {
                     Actor actor = new Actor(Integer.parseInt(row.get(0)), row.get(1),
                             new Date(new SimpleDateFormat("yyyy-MM-dd").parse(row.get(2)).getTime()), row.get(3), db);

                     actorList.put(actor.getName(), actor);
                     personCombo.getItems().add(actor.getName());
                  }
                  else if (selectedPerson.equalsIgnoreCase("Producer"))
                  {
                     Producer producer = new Producer(Integer.parseInt(row.get(0)), row.get(1),
                             new Date(new SimpleDateFormat("yyyy-MM-dd").parse(row.get(2)).getTime()), row.get(3), db);

                     producerList.put(producer.getName(), producer);
                     personCombo.getItems().add(producer.getName());
                  }
                  else
                  {
                     Writer writer = new Writer(Integer.parseInt(row.get(0)), row.get(1),
                             new Date(new SimpleDateFormat("yyyy-MM-dd").parse(row.get(2)).getTime()), row.get(3), db);

                     writerList.put(writer.getName(), writer);
                     personCombo.getItems().add(writer.getName());
                  }
               }
            }
            catch (DLException | ParseException e)
            {
               e.printStackTrace();
            }
         });

         generateUserCombo();
         generateSelectionCombo();

      }
      catch (DLException e)
      {
         e.printStackTrace();
      }

      setListeners();
   }

   private void resetTextFields(TextField... fields)
   {
      for (int i = 0; i < fields.length; i++)
      {
         fields[i].setText("");
      }
   }

   private void setListeners()
   {
      movieCombo.valueProperty().addListener(observable ->
      {
         Movie movie = movieList.get(movieCombo.getValue());
         movieNameField.setText(movie.getName());
         movieDescriptionArea.setText(movie.getDescription());
         movieYearField.setText(Integer.toString(movie.getYear()));
         movieScreenTimeField.setText(Integer.toString(movie.getScreenTime()));
         movieImageURLField.setText(movie.getImage());
         movieTrailerField.setText(movie.getTrailer());
      });

      saveMovie.setOnMouseClicked(event ->
      {
         Movie movie = new Movie(ratingList.get(movieRatingCombo.getValue()).getRatingID(), movieNameField.getText(),
                 movieDescriptionArea.getText(), 0.0, Integer.parseInt(movieYearField.getText()),
                 Integer.parseInt(movieScreenTimeField.getText()), movieImageURLField.getText(),
                 movieTrailerField.getText(), db);

         if (isMovieEdit.isSelected())
         {
            movie.setMovieID(movieList.get(movieCombo.getValue()).getMovieID());
            movie.put();
         }
         else
         {
            movie.post();
         }
      });

      personCombo.valueProperty().addListener(observable ->
      {
         String selectedPerson = personCombo.getValue();
         if (selectedPerson != null)
         {
            String selectedType = personTypeCombo.getValue();
            String name = "";
            String dob = "";
            String country = "";

            if (selectedType.equalsIgnoreCase("Director"))
            {
               Director director = directorList.get(selectedPerson);
               name = director.getName();
               dob = director.getDob().toString();
               country = director.getCountry();
            }

            else if (selectedType.equalsIgnoreCase("Actor"))
            {
               Actor actor = actorList.get(selectedPerson);
               name = actor.getName();
               dob = actor.getDob().toString();
               country = actor.getCountry();
            }
            else if (selectedType.equalsIgnoreCase("Producer"))
            {
               Producer producer = producerList.get(selectedPerson);
               name = producer.getName();
               dob = producer.getDob().toString();
               country = producer.getCountry();
            }
            else
            {
               Writer writer = writerList.get(selectedPerson);
               name = writer.getName();
               dob = writer.getDob().toString();
               country = writer.getCountry();
            }

            personNameField.setText(name);
            personDOBField.setText(dob);
            personCountryField.setText(country);
         }
      });

      savePersonButton.setOnMouseClicked(event ->
      {
         String name = personNameField.getText();
         String dob = personDOBField.getText();
         String country = personCountryField.getText();

         String personComboValue = personTypeCombo.getValue();
         try
         {
            if (personComboValue.equals("Director"))
            {
               Director director = new Director(name, new Date(new SimpleDateFormat("yyyy-MM-dd").parse(dob).getTime()), country, db);
               if (isPersonEditCheckbox.isSelected())
               {
                  director.setDirectorID(directorList.get(personCombo.getValue()).getDirectorID());
                  director.put();
               }
               else
               {
                  director.post();
               }
            }
            else if (personComboValue.equals("Producer"))
            {
               Producer producer = new Producer(name, new Date(new SimpleDateFormat("yyyy-MM-dd").parse(dob).getTime()), country, db);
               if (isPersonEditCheckbox.isSelected())
               {
                  producer.setProducerID(producerList.get(personCombo.getValue()).getProducerID());
                  producer.put();
               }
               else
               {
                  producer.post();
               }
            }
            else if (personComboValue.equals("Writer"))
            {
               Writer writer = new Writer(name, new Date(new SimpleDateFormat("yyyy-MM-dd").parse(dob).getTime()), country, db);
               if (isPersonEditCheckbox.isSelected())
               {
                  writer.setWriterID(writerList.get(personCombo.getValue()).getWriterID());
                  writer.put();
               }
               else
               {
                  writer.post();
               }
            }
            else if (personComboValue.equals("Actor"))
            {
               Actor actor = new Actor(name, new Date(new SimpleDateFormat("yyyy-MM-dd").parse(dob).getTime()), country, db);
               if (isPersonEditCheckbox.isSelected())
               {
                  actor.setActorID(actorList.get(personCombo.getValue()).getActorID());
                  actor.put();
               }
               else
               {
                  actor.post();
               }
            }
         }
         catch (ParseException e)
         {
            e.printStackTrace();
         }
      });

      userCombo.valueProperty().addListener(observable ->
      {
         User user = userList.get(userCombo.getValue());
         if (user != null)
         {
            usernameTextField.setText(user.getUsername());
            passwordTextField.setText(user.getPassword());
            accessLevelTextField.setText(user.getAccessLevel());
         }

      });

      saveSelection.setOnMouseClicked(event ->
      {
         String selectionGroup = selectionTextArea.getText();
         String selectedPerson = selectionItem.getValue();
         String sql = "";
         String targetTable = "";
         boolean firstQuery = true;
         ArrayList<String> values = new ArrayList<>();
         int selectionID = selectedMovieList.get(movieSelection.getValue()).getMovieID();
         String[] valueList = selectionGroup.split(", ");
         for (int i = 0; i < valueList.length; i++)
         {
            int id = -1;

            if (selectedPerson.equalsIgnoreCase("actor"))
            {
               id = activeActorToMovieList.get(valueList[i]).getActorID();
               sql = "INSERT INTO actor_has_movie (Actor_actorID, Movie_movieID) VALUES (?, ?);";
               targetTable = "actor_has_movie";
            }
            else if (selectedPerson.equalsIgnoreCase("producer"))
            {
               id = activeProducerToMovieList.get(valueList[i]).getProducerID();
               sql = "INSERT INTO movie_has_producer (Producer_producerID, Movie_movieID) VALUES (?, ?);";
               targetTable = "movie_has_producer";
            }
            else if (selectedPerson.equalsIgnoreCase("writer"))
            {
               id = activeWriterToMovieList.get(valueList[i]).getWriterID();
               sql = "INSERT INTO movie_has_writer (Writer_writerID, Movie_movieID) VALUES (?, ?);";
               targetTable = "movie_has_writer";
            }
            else if (selectedPerson.equalsIgnoreCase("director"))
            {
               id = activeDirectorToMovieList.get(valueList[i]).getDirectorID();
               sql = "INSERT INTO director_has_movie (Director_directorID, Movie_movieID) VALUES (?, ?);";
               targetTable = "director_has_movie";
            }
            else
            {
               id = activeGenres.get(valueList[i]).getGenreID();
               sql = "INSERT INTO movie_has_genre (Genre_genreID, Movie_movieID) VALUES (?, ?);";
               targetTable = "movie_has_genre";
            }

            values.add(Integer.toString(id));
            values.add(Integer.toString(selectionID));

            try
            {
               if (firstQuery)
               {
                  String deleteSql = "DELETE FROM " + targetTable + " WHERE Movie_movieID = ?";
                  ArrayList<String> deleteValues = new ArrayList<>();
                  deleteValues.add(Integer.toString(selectionID));

                  db.setData(deleteSql, deleteValues);
                  firstQuery = false;
               }

               db.setData(sql, values);
               values.clear();
            }
            catch (DLException e)
            {
               e.printStackTrace();
               values.clear();
            }
         }
      });

      saveUserButton.setOnMouseClicked(event ->
      {
         User user = new User(usernameTextField.getText(), passwordTextField.getText(), accessLevelTextField.getText(), db);
         if (isUserEdit.isSelected())
         {
            user.setUserID(userList.get(userCombo.getValue()).getUserID());
            user.put();
         }
         else
         {
            user.post();
         }

         try
         {
            generateUserCombo();
         }
         catch (DLException e)
         {
            e.printStackTrace();
         }
      });
   }

   private void generateUserCombo() throws DLException
   {
      userCombo.getItems().clear();
      String sql = "SELECT userID, username, password, accessLevel FROM user";
      ArrayList<ArrayList<String>> result = db.getData(sql, new ArrayList<>());

      for (int i = 0; i < result.size(); i++)
      {
         ArrayList<String> row = result.get(i);

         User user = new User(Integer.parseInt(row.get(0)), row.get(1), row.get(2), row.get(3), db);
         userList.put(user.getUsername(), user);
         userCombo.getItems().add(user.getUsername());
      }
   }

   private void generateMovieCombo(LinkedHashMap map, ComboBox<String> targetCombo) throws DLException
   {
      String sql = "SELECT movieID, ratingID, name, description, score, year, screenTime, image, trailer from movie";
      targetCombo.getItems().clear();
      ArrayList<ArrayList<String>> result = db.getData(sql, new ArrayList<>());

      for (int i = 0; i < result.size(); i++)
      {
         ArrayList<String> row = result.get(i);

         Movie movie = new Movie(Integer.parseInt(row.get(0)), Integer.parseInt(row.get(1)), row.get(2), row.get(3), Double.parseDouble(row.get(4)),
                 Integer.parseInt(row.get(5)), Integer.parseInt(row.get(6)), row.get(7), row.get(8), db);

         map.put(movie.getName(), movie);
         targetCombo.getItems().add(movie.getName());
      }
   }

   private void generateSelectionCombo()
   {
      try
      {
         generateMovieCombo(selectedMovieList, movieSelection);

         selectionItem.getItems().addAll("Actor", "Genre", "Producer", "Writer", "Director");

         populateButton.setOnMouseClicked(event ->
         {
            addSelection.getItems().clear();
            removeSelection.getItems().clear();
            activeGenres.clear();
            activeWriterToMovieList.clear();
            activeProducerToMovieList.clear();
            activeActorToMovieList.clear();
            activeDirectorToMovieList.clear();
            try
            {
               String selectedItem = selectionItem.getValue();
               StringBuilder stringBuilder = new StringBuilder();
               if (selectedItem.equalsIgnoreCase("genre"))
               {
                  String sql = "SELECT genreID, name from genre";
                  ArrayList<ArrayList<String>> result = db.getData(sql, new ArrayList<>());

                  for (int i = 0; i < result.size(); i++)
                  {
                     ArrayList<String> row = result.get(i);

                     Genre genre = new Genre(Integer.parseInt(row.get(0)), row.get(1), db);
                     genreList.put(genre.getName(), genre);
                     addSelection.getItems().add(genre.getName());
                  }

                  sql = "SELECT Genre_genreID from movie_has_genre WHERE Movie_movieID = ?";
                  ArrayList<String> values = new ArrayList<String>();
                  values.add(Integer.toString(selectedMovieList.get(movieSelection.getValue()).getMovieID()));

                  result = db.getData(sql, values);
                  for (Map.Entry<String, Genre> entry : genreList.entrySet())
                  {
                     Genre genre = entry.getValue();
                     for (int i = 0; i < result.size(); i++)
                     {
                        ArrayList<String> row = result.get(i);

                        if (Integer.parseInt(row.get(0)) == genre.getGenreID())
                        {
                           if (stringBuilder.length() > 0)
                           {
                              stringBuilder.append(", " + genre.getName());
                           }
                           else
                           {
                              stringBuilder.append(genre.getName());
                           }
                           activeGenres.put(genre.getName(), genre);
                        }
                     }
                  }
                  if (stringBuilder.length() > 0)
                  {
                     //stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
                  }

                  selectionTextArea.setText(stringBuilder.toString());
                  generateActiveSelectionCombo(selectedItem);
               }
               else
               {
                  String sql = "SELECT " + selectedItem.toLowerCase() + "ID, name, dob, country FROM " + selectedItem.toLowerCase();
                  ArrayList<ArrayList<String>> result = db.getData(sql, new ArrayList<>());

                  if (selectedItem.equalsIgnoreCase("actor"))
                  {
                     for (int i = 0; i < result.size(); i++)
                     {
                        ArrayList<String> row = result.get(i);

                        Actor actor = new Actor(Integer.parseInt(row.get(0)), row.get(1),
                                new Date(new SimpleDateFormat("yyyy-MM-dd").parse(row.get(2)).getTime()), row.get(3), db);
                        actorToMovieList.put(actor.getName(), actor);
                        addSelection.getItems().add(actor.getName());
                     }

                     sql = "SELECT Actor_actorID from actor_has_movie WHERE Movie_movieID = ?";
                     ArrayList<String> values = new ArrayList<String>();
                     values.add(Integer.toString(selectedMovieList.get(movieSelection.getValue()).getMovieID()));

                     result = db.getData(sql, values);
                     for (Map.Entry<String, Actor> entry : actorToMovieList.entrySet())
                     {
                        Actor actor = entry.getValue();
                        for (int i = 0; i < result.size(); i++)
                        {
                           ArrayList<String> row = result.get(i);

                           if (Integer.parseInt(row.get(0)) == actor.getActorID())
                           {
                              if (stringBuilder.length() > 0)
                              {
                                 stringBuilder.append(", " + actor.getName());
                              }
                              else
                              {
                                 stringBuilder.append(actor.getName());
                              }
                              activeActorToMovieList.put(actor.getName(), actor);
                           }
                        }
                     }
                     if (stringBuilder.length() > 0)
                     {
                        //stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
                     }
                     selectionTextArea.setText(stringBuilder.toString());
                     generateActiveSelectionCombo(selectedItem);
                  }
                  else if (selectedItem.equalsIgnoreCase("director"))
                  {
                     for (int i = 0; i < result.size(); i++)
                     {
                        ArrayList<String> row = result.get(i);

                        Director director = new Director(Integer.parseInt(row.get(0)), row.get(1),
                                new Date(new SimpleDateFormat("yyyy-MM-dd").parse(row.get(2)).getTime()), row.get(3), db);
                        directorToMovieList.put(director.getName(), director);
                        addSelection.getItems().add(director.getName());
                     }

                     sql = "SELECT Director_directorID from director_has_movie WHERE Movie_movieID = ?";
                     ArrayList<String> values = new ArrayList<String>();
                     values.add(Integer.toString(selectedMovieList.get(movieSelection.getValue()).getMovieID()));

                     result = db.getData(sql, values);
                     for (Map.Entry<String, Director> entry : directorToMovieList.entrySet())
                     {
                        Director director = entry.getValue();
                        for (int i = 0; i < result.size(); i++)
                        {
                           ArrayList<String> row = result.get(i);

                           if (Integer.parseInt(row.get(0)) == director.getDirectorID())
                           {
                              if (stringBuilder.length() > 0)
                              {
                                 stringBuilder.append(", " + director.getName());
                              }
                              else
                              {
                                 stringBuilder.append(director.getName());
                              }
                              activeDirectorToMovieList.put(director.getName(), director);
                           }
                        }
                     }
                     if (stringBuilder.length() > 0)
                     {
                        //stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
                     }

                     selectionTextArea.setText(stringBuilder.toString());
                     generateActiveSelectionCombo(selectedItem);
                  }
                  else if (selectedItem.equalsIgnoreCase("producer"))
                  {
                     for (int i = 0; i < result.size(); i++)
                     {
                        ArrayList<String> row = result.get(i);

                        Producer producer = new Producer(Integer.parseInt(row.get(0)), row.get(1),
                                new Date(new SimpleDateFormat("yyyy-MM-dd").parse(row.get(2)).getTime()), row.get(3), db);
                        producerToMovieList.put(producer.getName(), producer);
                        addSelection.getItems().add(producer.getName());
                     }

                     sql = "SELECT Producer_producerID from movie_has_producer WHERE Movie_movieID = ?";
                     ArrayList<String> values = new ArrayList<String>();
                     values.add(Integer.toString(selectedMovieList.get(movieSelection.getValue()).getMovieID()));

                     result = db.getData(sql, values);
                     for (Map.Entry<String, Producer> entry : producerToMovieList.entrySet())
                     {
                        Producer producer = entry.getValue();
                        for (int i = 0; i < result.size(); i++)
                        {
                           ArrayList<String> row = result.get(i);

                           if (Integer.parseInt(row.get(0)) == producer.getProducerID())
                           {
                              if (stringBuilder.length() > 0)
                              {
                                 stringBuilder.append(", " + producer.getName());
                              }
                              else
                              {
                                 stringBuilder.append(producer.getName());
                              }
                              activeProducerToMovieList.put(producer.getName(), producer);
                           }
                        }
                     }
                     if (stringBuilder.length() > 0)
                     {
                        //stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
                     }

                     selectionTextArea.setText(stringBuilder.toString());
                     generateActiveSelectionCombo(selectedItem);
                  }
                  else if (selectedItem.equalsIgnoreCase("writer"))
                  {
                     for (int i = 0; i < result.size(); i++)
                     {
                        ArrayList<String> row = result.get(i);

                        Writer writer = new Writer(Integer.parseInt(row.get(0)), row.get(1),
                                new Date(new SimpleDateFormat("yyyy-MM-dd").parse(row.get(2)).getTime()), row.get(3), db);
                        writerToMovieList.put(writer.getName(), writer);
                        addSelection.getItems().add(writer.getName());
                     }

                     sql = "SELECT Writer_writerID from movie_has_writer WHERE Movie_movieID = ?";
                     ArrayList<String> values = new ArrayList<String>();
                     values.add(Integer.toString(selectedMovieList.get(movieSelection.getValue()).getMovieID()));

                     result = db.getData(sql, values);
                     for (Map.Entry<String, Writer> entry : writerToMovieList.entrySet())
                     {
                        Writer writer = entry.getValue();
                        for (int i = 0; i < result.size(); i++)
                        {
                           ArrayList<String> row = result.get(i);

                           if (Integer.parseInt(row.get(0)) == writer.getWriterID())
                           {
                              if (stringBuilder.length() > 0)
                              {
                                 stringBuilder.append(", " + writer.getName());
                              }
                              else
                              {
                                 stringBuilder.append(writer.getName());
                              }
                              activeWriterToMovieList.put(writer.getName(), writer);
                           }
                        }
                     }
                     if (stringBuilder.length() > 0)
                     {
                        //stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
                     }

                     selectionTextArea.setText(stringBuilder.toString());
                     generateActiveSelectionCombo(selectedItem);
                  }
               }

            }
            catch (DLException | ParseException e)
            {
               e.printStackTrace();
            }
         });

         addItem.setOnMouseClicked(event ->
         {
            String selectedItem = addSelection.getValue();
            addActiveItem(selectedItem);

         });

         removeItem.setOnMouseClicked(event ->
         {
            String selectionItemStr = selectionItem.getValue();
            String selectedItem = removeSelection.getValue();

            if (selectionItemStr.equalsIgnoreCase("actor"))
            {
               removeActiveItem(activeActorToMovieList, selectedItem);
            }
            else if (selectionItemStr.equalsIgnoreCase("genre"))
            {
               removeActiveItem(activeGenres, selectedItem);
            }
            else if (selectionItemStr.equalsIgnoreCase("producer"))
            {
               removeActiveItem(activeProducerToMovieList, selectedItem);
            }
            else if (selectionItemStr.equalsIgnoreCase("writer"))
            {
               removeActiveItem(activeWriterToMovieList, selectedItem);
            }
            else
            {
               removeActiveItem(activeDirectorToMovieList, selectedItem);
            }
         });
      }
      catch (DLException e)
      {
         e.printStackTrace();
      }
   }

   private void generateActiveSelectionCombo(String type)
   {
      String selectionItemStr = selectionItem.getValue();
      removeSelection.getItems().clear();

      if (selectionItemStr.equalsIgnoreCase("actor"))
      {
         for (Map.Entry<String, Actor> entry : activeActorToMovieList.entrySet())
         {
            removeSelection.getItems().add(entry.getKey());
         }
      }
      else if (selectionItemStr.equalsIgnoreCase("genre"))
      {
         for (Map.Entry<String, Genre> entry : activeGenres.entrySet())
         {
            removeSelection.getItems().add(entry.getKey());
         }
      }
      else if (selectionItemStr.equalsIgnoreCase("producer"))
      {
         for (Map.Entry<String, Producer> entry : activeProducerToMovieList.entrySet())
         {
            removeSelection.getItems().add(entry.getKey());
         }
      }
      else if (selectionItemStr.equalsIgnoreCase("writer"))
      {
         for (Map.Entry<String, Writer> entry : activeWriterToMovieList.entrySet())
         {
            removeSelection.getItems().add(entry.getKey());
         }
      }
      else
      {
         for (Map.Entry<String, Director> entry : activeDirectorToMovieList.entrySet())
         {
            removeSelection.getItems().add(entry.getKey());
         }
      }
   }

   private void generateActivePreview(String type)
   {
      StringBuilder stringBuilder = new StringBuilder();

      if (type.equalsIgnoreCase("actor"))
      {
         for (Map.Entry<String, Actor> entry : activeActorToMovieList.entrySet())
         {
            Actor actor = entry.getValue();
            if (stringBuilder.length() > 0)
            {
               stringBuilder.append(", " + actor.getName());
            }
            else
            {
               stringBuilder.append(actor.getName());
            }
         }
      }
      else if (type.equalsIgnoreCase("genre"))
      {
         for (Map.Entry<String, Genre> entry : activeGenres.entrySet())
         {
            Genre genre = entry.getValue();
            if (stringBuilder.length() > 0)
            {
               stringBuilder.append(", " + genre.getName());
            }
            else
            {
               stringBuilder.append(genre.getName());
            }
         }
      }
      else if (type.equalsIgnoreCase("producer"))
      {
         for (Map.Entry<String, Producer> entry : activeProducerToMovieList.entrySet())
         {
            Producer producer = entry.getValue();
            if (stringBuilder.length() > 0)
            {
               stringBuilder.append(", " + producer.getName());
            }
            else
            {
               stringBuilder.append(producer.getName());
            }
         }
      }
      else if (type.equalsIgnoreCase("writer"))
      {
         for (Map.Entry<String, Writer> entry : activeWriterToMovieList.entrySet())
         {
            Writer writer = entry.getValue();
            if (stringBuilder.length() > 0)
            {
               stringBuilder.append(", " + writer.getName());
            }
            else
            {
               stringBuilder.append(writer.getName());
            }
         }
      }
      else
      {
         for (Map.Entry<String, Director> entry : activeDirectorToMovieList.entrySet())
         {
            Director director = entry.getValue();
            if (stringBuilder.length() > 0)
            {
               stringBuilder.append(", " + director.getName());
            }
            else
            {
               stringBuilder.append(director.getName());
            }
         }
      }

      int index = stringBuilder.indexOf(", ");
      if (index > 0)
      {
         //stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
      }
      selectionTextArea.setText(stringBuilder.toString());
   }

   private void removeActiveItem(LinkedHashMap map, String selectedItem)
   {
      map.remove(selectedItem);
      generateActivePreview(selectionItem.getValue());
      generateActiveSelectionCombo(selectionItem.getValue());
      //generateActivePreview(selectedItem);
      //generateActiveSelectionCombo(selectedItem);
   }

   private void alertExisting(String word)
   {

      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.initOwner(mainApp.getPrimaryStage());
      alert.setTitle("Invalid " + word);
      alert.setHeaderText(word + " already assigned");
      alert.setContentText("The selected " + word + " is already assigned to the movie.");

      alert.showAndWait();
   }

   private void addActiveItem(String selectedItem)
   {
      String selectionItemValue = selectionItem.getValue();
      if (selectionItemValue.equalsIgnoreCase("actor"))
      {
         if (activeActorToMovieList.containsKey(selectedItem))
         {
            alertExisting(selectedItem);
         }
         else
         {
            checkIfEmpty(selectedItem);
            activeActorToMovieList.put(selectedItem, actorToMovieList.get(selectedItem));
            generateActiveSelectionCombo(selectedItem);
         }
      }
      else if (selectionItemValue.equalsIgnoreCase("genre"))
      {
         if (activeGenres.containsKey(selectedItem))
         {
            alertExisting(selectedItem);
         }
         else
         {
            checkIfEmpty(selectedItem);
            activeGenres.put(selectedItem, genreList.get(selectedItem));
            generateActiveSelectionCombo(selectedItem);
         }
      }
      else if (selectionItemValue.equalsIgnoreCase("producer"))
      {
         if (activeProducerToMovieList.containsKey(selectedItem))
         {
            alertExisting(selectedItem);
         }
         else
         {
            checkIfEmpty(selectedItem);
            activeProducerToMovieList.put(selectedItem, producerToMovieList.get(selectedItem));
            generateActiveSelectionCombo(selectedItem);
         }
      }
      else if (selectionItemValue.equalsIgnoreCase("writer"))
      {
         if (activeWriterToMovieList.containsKey(selectedItem))
         {
            alertExisting(selectedItem);
         }
         else
         {
            checkIfEmpty(selectedItem);
            activeWriterToMovieList.put(selectedItem, writerToMovieList.get(selectedItem));
            generateActiveSelectionCombo(selectedItem);
         }
      }
      else
      {
         if (activeDirectorToMovieList.containsKey(selectedItem))
         {
            alertExisting(selectedItem);
         }
         else
         {
            checkIfEmpty(selectedItem);
            activeDirectorToMovieList.put(selectedItem, directorToMovieList.get(selectedItem));
            generateActiveSelectionCombo(selectedItem);
         }
      }
   }

   private void checkIfEmpty(String selectedItem)
   {
      if (selectionTextArea.getText().length() > 0)
      {
         selectionTextArea.setText(selectionTextArea.getText() + ", " + selectedItem);
      }
      else
      {
         selectionTextArea.setText(selectedItem);
      }
   }
}
