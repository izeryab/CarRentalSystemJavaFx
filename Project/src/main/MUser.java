package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MUser extends Group{
	ObservableList<MyUser> data = FXCollections.observableArrayList();

	public MUser() {
	Group root = new Group();
	Rectangle clearback = new Rectangle(1000, 1000);
	clearback.setTranslateY(80);
	clearback.setFill(Color.WHITE);
	VBox v= new VBox();
	 TableView<MyUser> table = new TableView<MyUser>();
	 table.setEditable(true);
	 
	 TableColumn unamecol = new TableColumn("username");
     TableColumn namecol = new TableColumn("name");
     TableColumn emailcol = new TableColumn("email");
     TableColumn concol = new TableColumn("contact");
     
     unamecol.setMinWidth(100);
     namecol.setMinWidth(100);
     emailcol.setMinWidth(100);
     concol.setMinWidth(100);
        
     
   
	TextField username =new TextField();
	username.setPromptText("User name");
	username.setFocusTraversable(false);
	username.setPrefSize(180, 30);
	
	TextField name =new TextField();
	name.setPromptText("Name");
	name.setFocusTraversable(false);
	name.setPrefSize(180, 30);

	TextField contact =new TextField();
	contact.setPromptText("contact");
	contact.setFocusTraversable(false);
	contact.setPrefSize(180, 30);

	TextField email =new TextField();
	email.setPromptText("email");
	email.setFocusTraversable(false);
	email.setPrefSize(180, 30);

	loaddata();	
	
	Button btn_add=new Button("Add");
	btn_add.setPrefSize(250, 30);
	btn_add.setTextFill(Color.WHITE);
	btn_add.setStyle("-fx-background-color : green");

	Button btn_delete=new Button("Delete");
	btn_delete.setPrefSize(250, 30);
	btn_delete.setTextFill(Color.WHITE);
	btn_delete.setStyle("-fx-background-color : green");

	Button btn_modify=new Button("Modify");
	btn_modify.setPrefSize(250, 30);
	btn_modify.setTextFill(Color.WHITE);
	btn_modify.setStyle("-fx-background-color : green");

	  unamecol.setCellValueFactory(new PropertyValueFactory<MyUser,String>("username"));
	     namecol.setCellValueFactory(new PropertyValueFactory<MyUser,String>("name"));
	     emailcol.setCellValueFactory(new PropertyValueFactory<MyUser,String>("email"));
	     concol.setCellValueFactory(new PropertyValueFactory<MyUser,String>("contact"));
	     table.setItems(data);

	     
	     
	     
	     btn_add.setOnAction(e->{
	    	 

	    	 addbtn(username.getText(), name.getText(), email.getText(), contact.getText());
	    	 username.setText("");
	    	 name.setText("");
	    	 email.setText("");
	    	 contact.setText("");
		
	});
	    
	     btn_modify.setOnAction(e->{
	    	 
	    	 if(!username.getText().equals("")) {
	    	 modifybtn(username.getText(), name.getText(), email.getText(), contact.getText());
	    	 username.setText("");
	    	 name.setText("");
	    	 email.setText("");
	    	 contact.setText("");
	    	 }
		
	});
	     btn_delete.setOnAction(e->{
	    	 if(!username.getText().equals("")) {
		    	 
	    	 deletebtn(username.getText());
	    	 username.setText("");
	    	 name.setText("");
	    	 email.setText("");
	    	 contact.setText("");
	    	 }
	});
	     
	     System.out.println(table.getSelectionModel().getSelectedItem());
	     table.setOnMouseClicked(ex -> {
	    	    if (ex.getClickCount() > 1) {
	    		     System.out.println(table.getSelectionModel().getSelectedItem());
	    	    	MyUser data=(MyUser) table.getSelectionModel().getSelectedItem();
	    	    username.setText(data.getUsername());
	    	    name.setText(data.getName());
	    	    email.setText(data.getEmail());
	    	    contact.setText(data.getContact());
	    	    
	    	    }
	     });
	 v.getChildren().add(username);
	v.getChildren().add(name);
	v.getChildren().add(contact);
	v.getChildren().add(email);
	
	
	v.getChildren().add(btn_add);
	v.getChildren().add(btn_delete);
	v.getChildren().add(btn_modify);
	v.setTranslateX(280);
	v.setTranslateY(80);
	v.setSpacing(10);
	table.setTranslateX(400);
	table.setTranslateY(80);
	v.setTranslateX(38);
	v.setTranslateY(80);
	v.setSpacing(10);
	
	  table.getColumns().addAll(unamecol, namecol, emailcol,concol);
	root.getChildren().add(clearback);
	root.getChildren().add(v);
	
	root.getChildren().add(table);
	
	super.getChildren().add(root);	

	}
private void loaddata() {
	 Connection conn = null;
	   Statement stmt = null;
	   try{
	      conn = DriverManager.getConnection(Login.DB_URL, Login.USER, Login.PASS);
	      stmt = conn.createStatement();
	      String sql = "SELECT * FROM user";
	      ResultSet rs = stmt.executeQuery(sql);
	      while(rs.next()){
	    	  MyUser m = new MyUser(rs.getString("username"),rs.getString("name"),rs.getString("email"),rs.getString("contact"));
	    	  data.add(m);
	    	 }
	      rs.close();
	   }catch(SQLException se){
	      se.printStackTrace();
	   }
	
}
private void addbtn(String u,String n,String e,String c) {
	
	 Connection conn = null;
	 PreparedStatement pstmt = null;
	   try{
	      conn = DriverManager.getConnection(Login.DB_URL, Login.USER, Login.PASS);
	      pstmt = conn.prepareStatement("Insert into user(username,name,email,contact) VALUES(?,?,?,?)");
	      //update users set num_points = ? where first_name = ?
	      pstmt.setString(1, u);
	      pstmt.setString(2, n);
	      pstmt.setString(3, e);
	      pstmt.setString(4, c);
          
	      pstmt.executeUpdate();
	   }
	   catch(SQLException se){
	      se.printStackTrace();
	   }
	data.clear();
	 loaddata();	
}

private void modifybtn(String u,String n,String e,String c) {
	
	 Connection conn = null;
	 PreparedStatement pstmt = null;
	   try{
	      conn = DriverManager.getConnection(Login.DB_URL, Login.USER, Login.PASS);
	      pstmt = conn.prepareStatement("UPDATE user SET name=?, email=?, contact=? WHERE username=?");
	      //update users set num_points = ? where first_name = ?
	      pstmt.setString(1, n);
	      pstmt.setString(2, e);
	      pstmt.setString(3, c);
	      pstmt.setString(4, u);
         
	      pstmt.executeUpdate();
	   }
	   catch(SQLException se){
	      se.printStackTrace();
	   }
	data.clear();
	 loaddata();	
}

private void deletebtn(String u) {
	
	 Connection conn = null;
	 PreparedStatement pstmt = null;
	   try{
	      conn = DriverManager.getConnection(Login.DB_URL, Login.USER, Login.PASS);
	      pstmt = conn.prepareStatement("DELETE FROM user WHERE username=?");
	      //update users set num_points = ? where first_name = ?
	      pstmt.setString(1, u);
        
	      pstmt.executeUpdate();
	   }
	   catch(SQLException se){
	      se.printStackTrace();
	   }
	data.clear();
	 loaddata();	
}

}
