import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.sql.*;

public class DataScreen extends Application {
  // PreparedStatement for executing queries
  private PreparedStatement preparedStatement;
  private TextField tfPlayer = new TextField();
  private Label  lScore = new Label ();
  private Label  lLevel = new Label ();
  private Label lblStatus = new Label();
  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    // Initialize database connection and create a Statement object
    initializeDB();

    Button btShowData = new Button("Show Data");
    HBox hBox = new HBox(5);
    hBox.getChildren().addAll(new Label("Score"), lScore, 
      new Label("Level"), lLevel, (btShowData));

    VBox vBox = new VBox(10);
    vBox.getChildren().addAll(hBox, lblStatus);
    
   // tfPlayer.setPrefColumnCount(6);
   // tfCourseId.setPrefColumnCount(6);
    btShowData.setOnAction(e -> showData());
    
    // Create a scene and place it in the stage
    Scene scene = new Scene(vBox, 420, 80);
    primaryStage.setTitle("Database"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage   
  }

  private void initializeDB() {
    try {
      // Load the JDBC driver
      Class.forName("com.mysql.jdbc.Driver");
      System.out.println("Driver loaded");

      // Establish a connection
      Connection connection = DriverManager.getConnection
      ("jdbc:mysql://localhost/java_squad", "final", "me");


      System.out.println("Database connected");
      String queryString = "";
        // Create a statement  
        preparedStatement = connection.prepareStatement(queryString);   }

    catch (Exception ex) {
      ex.printStackTrace();
    }
  }
   private void showData(){
      String scoreLabel = lScore.getText();    String levelLabel = lLevel.getText();
      try{
         preparedStatement.setString(1, scoreLabel);
         preparedStatement.setString(2, levelLabel);
         ResultSet rset = preparedStatement.executeQuery();
              }
         catch(SQLException ex) {   ex.printStackTrace();}}
         
  
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}