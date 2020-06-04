package main;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Login extends Application{
	public static String loginID="";
public static void main(String[] args) {
	launch(args);
}
String test="testuser";
static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
static final String DB_URL = "jdbc:mysql://localhost/carrent";
Stage stage=new Stage();
//  Database credentials
static final String USER = "root";
static final String PASS = "";
Scene adminpanel = new Scene(new AdminPanel(),1000,600);
Scene Clientpanel = new Scene(new ClientPanel(),1000,600);

@Override
public void start(Stage stage) throws Exception {
	this.stage=stage;
	// TODO Auto-generated method stub
	Group root = new Group();
	VBox v= new VBox();
	
	Label title= new Label(" WELCOME \n     \tTO \nCAR RENTAL");
	title.setFont(Font.font("calibiri", FontWeight.BOLD, FontPosture.REGULAR, 40));
	title.setTextFill(Color.BLACK);
	
	TextField username =new TextField();
	username.setPromptText("Enter your User name");
	username.setFocusTraversable(false);
	username.setPrefSize(180, 30);
	
	PasswordField pass=new PasswordField();
	pass.setPromptText("Enter your Passowrd");
	pass.setFocusTraversable(false);
	pass.setPrefSize(180, 30);
	
	Button btn_signin=new Button("Sign In");
	btn_signin.setPrefSize(250, 30);
	btn_signin.setTextFill(Color.WHITE);
	btn_signin.setStyle("-fx-background-color : green");
	
	username.setText(test);
	pass.setText(test);
	btn_signin.setOnAction(e->{
		 Connection conn = null;
		   Statement stmt = null;
		   try{

		      //STEP 3: Open a connection
		      System.out.println("Connecting to a selected database...");
		      conn = DriverManager.getConnection(DB_URL, USER, PASS);
		      System.out.println("Connected database successfully...");
		      
		      //STEP 4: Execute a query
		      stmt = conn.createStatement();

		      String sql = "SELECT u_id, username, password FROM user";
		      ResultSet rs = stmt.executeQuery(sql);
		      //STEP 5: Extract data from result set
		      while(rs.next()){
		         //Retrieve by column name
		    	  String usernam = rs.getString("username");
		         String password = rs.getString("password");
		          System.out.println(Login.loginID);
		         if(usernam.equals(username.getText())&&password.equals(pass.getText())) {
		        	if(usernam.equals("admin")) {
		        			stage.setScene(adminpanel);
		        	//Clientpanel
		        		//stage.setScene(Clientpanel);
		        		loginID = rs.getString("u_id");
						
		        		break;
			        	    
		        	}
		        	else {
		        		stage.setScene(Clientpanel);
		        		loginID = rs.getString("u_id");
						
		        		break;
		        		
		        	}
		        	
		         }
		         else {
		        	 System.out.println("Invalid username or password");
		         
		         }
		         //Display values
		         System.out.print(", username: " + usernam);
		         System.out.println(", password: " + password);
		      }
		      
		      rs.close();
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }
		
		
	});
	
	v.getChildren().add(title);
	v.getChildren().add(username);
	v.getChildren().add(pass);
	v.getChildren().add(btn_signin);
	v.setTranslateX(80);
	v.setTranslateY(130);
	v.setSpacing(10);
	root.getChildren().add(v);
	
	Scene sc = new Scene(root,400,600);
	//Scene sc = new Scene(new AdminPanel(),1000,600);
	
	stage.setScene(sc);
	stage.setResizable(false);
	stage.show();
	
}
}
