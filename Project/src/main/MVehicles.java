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

public class MVehicles extends Group{
	ObservableList<MyVehicle> data = FXCollections.observableArrayList();

	public MVehicles() {
	Group root = new Group();
	Rectangle clearback = new Rectangle(1000, 1000);
	clearback.setTranslateY(80);
	clearback.setFill(Color.WHITE);

	VBox v= new VBox();
	 TableView table = new TableView();
	 table.setEditable(true);

	 TableColumn idcol = new TableColumn("id");
	 TableColumn namecol = new TableColumn("name");
     TableColumn catcol = new TableColumn("category");
     TableColumn rescol = new TableColumn("reserved");
     
     idcol.setMinWidth(100);

     namecol.setMinWidth(100);
     catcol.setMinWidth(100);
     rescol.setMinWidth(100);
        
     
   
 	TextField id =new TextField();
 	id.setPromptText("Id");
 	id.setFocusTraversable(false);
 	id.setPrefSize(180, 30);

     TextField name =new TextField();
	name.setPromptText("Name");
	name.setFocusTraversable(false);
	name.setPrefSize(180, 30);
	
	TextField cat =new TextField();
	cat.setPromptText("Category");
	cat.setFocusTraversable(false);
	cat.setPrefSize(180, 30);

	TextField res =new TextField();
	res.setPromptText("Reserved(Yes/No)");
	res.setFocusTraversable(false);
	res.setPrefSize(180, 30);

	

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

		idcol.setCellValueFactory(new PropertyValueFactory<MyVehicle,String>("id"));
	  	namecol.setCellValueFactory(new PropertyValueFactory<MyVehicle,String>("name"));
	     catcol.setCellValueFactory(new PropertyValueFactory<MyVehicle,String>("category"));
	     rescol.setCellValueFactory(new PropertyValueFactory<MyVehicle,String>("reserved"));
	     table.setItems(data);

	     
	     
	     
	     btn_add.setOnAction(e->{
	    	 

	    	 addbtn(name.getText(), cat.getText(), res.getText());
	      	 id.setText("");
	      	 name.setText("");
	    	 cat.setText("");
	    	 res.setText("");
		
	});
	    
	     btn_modify.setOnAction(e->{
	    	 
	    	 if(!id.getText().equals("")) {
	    	 modifybtn(id.getText(),name.getText(), cat.getText(), res.getText());
	    	 id.setText("");
	    	 name.setText("");
	    	 cat.setText("");
	    	 res.setText("");
	    	 }
		
	});
	     btn_delete.setOnAction(e->{
	    	 if(!id.getText().equals("")) {
		    	 
	    	 deletebtn(id.getText());
	    	 id.setText("");
	    	 
	    	 name.setText("");
	    	 cat.setText("");
	    	 res.setText("");
	    	 }
	});
	     
	     System.out.println(table.getSelectionModel().getSelectedItem());
	     table.setOnMouseClicked(ex -> {
	    	    if (ex.getClickCount() > 1) {
	    		     System.out.println(table.getSelectionModel().getSelectedItem());
	    	    	MyVehicle data=(MyVehicle) table.getSelectionModel().getSelectedItem();
	    	    id.setText(data.getId());
	    	    name.setText(data.getName());
	    	    cat.setText(data.getCategory());
	    	    res.setText(data.getReserved());
	    	    
	    	    }
	     });
	 	v.getChildren().add(id);	     
	v.getChildren().add(name);
	v.getChildren().add(cat);
	v.getChildren().add(res);
	
	
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
	
	  table.getColumns().addAll(idcol,namecol, catcol,rescol);
		root.getChildren().add(clearback);

	root.getChildren().add(table);
	root.getChildren().add(v);
	
	super.getChildren().add(root);	

	}
private void loaddata() {
	 Connection conn = null;
	   Statement stmt = null;
	   try{
	      conn = DriverManager.getConnection(Login.DB_URL, Login.USER, Login.PASS);
	      stmt = conn.createStatement();
	      String sql = "SELECT * FROM vehicle";
	      ResultSet rs = stmt.executeQuery(sql);
	      while(rs.next()){
	    	  MyVehicle m = new MyVehicle(rs.getString("v_id"),rs.getString("name"),rs.getString("category"),rs.getString("reserved"));
	    	  data.add(m);
	    	 }
	      rs.close();
	   }catch(SQLException se){
	      se.printStackTrace();
	   }
	
}
private void addbtn(String n,String c,String r) {
	
	 Connection conn = null;
	 PreparedStatement pstmt = null;
	   try{
	      conn = DriverManager.getConnection(Login.DB_URL, Login.USER, Login.PASS);
	      pstmt = conn.prepareStatement("Insert into vehicle(name,category,reserved) VALUES(?,?,?)");
	      //update users set num_points = ? where first_name = ?
	      pstmt.setString(1, n);
	      pstmt.setString(2, c);
	      pstmt.setString(3, r);
          
	      pstmt.executeUpdate();
	   }
	   catch(SQLException se){
	      se.printStackTrace();
	   }
	data.clear();
	 loaddata();	
}

private void modifybtn(String i,String n,String c,String r) {
	
	 Connection conn = null;
	 PreparedStatement pstmt = null;
	   try{
	      conn = DriverManager.getConnection(Login.DB_URL, Login.USER, Login.PASS);
	      pstmt = conn.prepareStatement("UPDATE vehicle SET name=?, category=?, reserved=? WHERE v_id=?");
	      //update users set num_points = ? where first_name = ?
	      pstmt.setString(1, n);
	      pstmt.setString(2, c);
	      pstmt.setString(3, r);
	      pstmt.setString(4, i);
         
	      pstmt.executeUpdate();
	   }
	   catch(SQLException se){
	      se.printStackTrace();
	   }
	data.clear();
	 loaddata();	
}

private void deletebtn(String i) {
	
	 Connection conn = null;
	 PreparedStatement pstmt = null;
	   try{
	      conn = DriverManager.getConnection(Login.DB_URL, Login.USER, Login.PASS);
	      pstmt = conn.prepareStatement("DELETE FROM vehicle WHERE v_id=?");
	      //update users set num_points = ? where first_name = ?
	      pstmt.setString(1, i);
        
	      pstmt.executeUpdate();
	   }
	   catch(SQLException se){
	      se.printStackTrace();
	   }
	data.clear();
	 loaddata();	
}

}
