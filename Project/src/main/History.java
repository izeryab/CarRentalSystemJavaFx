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

public class History extends Group{
	ObservableList<MyHistory> data = FXCollections.observableArrayList();
	String id;
//String id, String uid, String vid, String bookingfrom, String bookingtill
	public History(String id) {
	this.id=id;
	Group root = new Group();
	Rectangle clearback = new Rectangle(1000, 1000);
	clearback.setTranslateY(80);
	clearback.setFill(Color.WHITE);

	 TableView table = new TableView();
	 table.setEditable(true);

	 TableColumn idcol = new TableColumn("Rent id");
	 TableColumn vidcol = new TableColumn("Vehicle Name");
     TableColumn bfcol = new TableColumn("BookingFrom");
     TableColumn btcol = new TableColumn("BookingTill");
     TableColumn amountcol = new TableColumn("Amount");
     
     
     idcol.setMinWidth(100);
     vidcol.setMinWidth(100);
     bfcol.setMinWidth(100);
     btcol.setMinWidth(100);
     amountcol.setMinWidth(100);
        
     
   
 	
	

	loaddata();	
	
		idcol.setCellValueFactory(new PropertyValueFactory<MyHistory,String>("id"));
	     vidcol.setCellValueFactory(new PropertyValueFactory<MyHistory,String>("name"));
	     bfcol.setCellValueFactory(new PropertyValueFactory<MyHistory,String>("bookingfrom"));
	     btcol.setCellValueFactory(new PropertyValueFactory<MyHistory,String>("bookingtill"));
	     amountcol.setCellValueFactory(new PropertyValueFactory<MyHistory,String>("Amount"));
	     
	     table.setItems(data);

	     
	     
	     
		     
	table.setTranslateX(300);
	table.setTranslateY(80);
	
	  table.getColumns().addAll(idcol, vidcol,bfcol,btcol,amountcol);
		root.getChildren().add(clearback);
		
	root.getChildren().add(table);
	
	super.getChildren().add(root);

	}
private void loaddata() {
	
	 Connection conn = null;
	   Statement stmt = null;
	   try{
	      conn = DriverManager.getConnection(Login.DB_URL, Login.USER, Login.PASS);
	      stmt = conn.createStatement();
	      String sql = "SELECT r_id, vehicle.name, bookingfrom, bookingtill, amount FROM rent,vehicle Where u_id="+this.id+" AND vehicle.v_id=rent.v_id";
	      ResultSet rs = stmt.executeQuery(sql);
	      while(rs.next()){
	    	  MyHistory m = new MyHistory(rs.getString("r_id"),rs.getString("name"),rs.getString("bookingfrom"),rs.getString("bookingtill"),rs.getString("amount"));
	    	  data.add(m);
	    	 }
	      rs.close();
	   }catch(SQLException se){
	      se.printStackTrace();
	   }
	
}

}
