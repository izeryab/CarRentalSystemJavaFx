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

public class MRent extends Group{
	ObservableList<MyRent> data = FXCollections.observableArrayList();
//String id, String uid, String vid, String bookingfrom, String bookingtill
	public MRent() {
	Group root = new Group();
	Rectangle clearback = new Rectangle(1000, 1000);
	clearback.setTranslateY(80);
	clearback.setFill(Color.WHITE);

	VBox v= new VBox();
	 TableView table = new TableView();
	 table.setEditable(true);

	 TableColumn idcol = new TableColumn("id");
	 TableColumn uidcol = new TableColumn("User id");
     TableColumn vidcol = new TableColumn("Vehicle id");
     TableColumn bfcol = new TableColumn("BookingFrom");
     TableColumn btcol = new TableColumn("BookingTill");
     TableColumn amountcol = new TableColumn("Amount");
     
     
     idcol.setMinWidth(100);
     uidcol.setMinWidth(100);
     vidcol.setMinWidth(100);
     bfcol.setMinWidth(100);
     btcol.setMinWidth(100);
     amountcol.setMinWidth(100);
        
     
   
 	TextField id =new TextField();
 	id.setPromptText("Id");
 	id.setFocusTraversable(false);
 	id.setPrefSize(180, 30);

 	TextField uid =new TextField();
 	uid.setPromptText("User Id");
 	uid.setFocusTraversable(false);
 	uid.setPrefSize(180, 30);

 	TextField vid =new TextField();
 	vid.setPromptText("Vehicle Id");
 	vid.setFocusTraversable(false);
 	vid.setPrefSize(180, 30);

 	TextField bf =new TextField();
 	bf.setPromptText("Booking from");
 	bf.setFocusTraversable(false);
 	bf.setPrefSize(180, 30);

 	
     TextField bt =new TextField();
     bt.setPromptText("Booking till");
     bt.setFocusTraversable(false);
     bt.setPrefSize(180, 30);
	

	TextField am =new TextField();
	am.setPromptText("Amount");
	am.setFocusTraversable(false);
	am.setPrefSize(180, 30);

	

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

		idcol.setCellValueFactory(new PropertyValueFactory<MyRent,String>("id"));
	  	uidcol.setCellValueFactory(new PropertyValueFactory<MyRent,String>("uid"));
	     vidcol.setCellValueFactory(new PropertyValueFactory<MyRent,String>("vid"));
	     bfcol.setCellValueFactory(new PropertyValueFactory<MyRent,String>("bookingfrom"));
	     btcol.setCellValueFactory(new PropertyValueFactory<MyRent,String>("bookingtill"));
	     amountcol.setCellValueFactory(new PropertyValueFactory<MyRent,String>("Amount"));
	     
	     table.setItems(data);

	     
	     
	     
	     btn_add.setOnAction(e->{
	    	 

	    	 addbtn(uid.getText(), vid.getText(), bf.getText(), bt.getText(), am.getText());
	      	 id.setText("");
	      	 uid.setText("");
	    	 vid.setText("");
	    	 bf.setText("");
	    	 bt.setText("");
	    	 am.setText("");
	    	 
		
	});
	    
	     btn_modify.setOnAction(e->{
	    	 
	    	 if(!id.getText().equals("")) {
	    	 modifybtn(id.getText(),uid.getText(), vid.getText(), bf.getText(), bt.getText(), am.getText());
	    	 id.setText("");
	      	 uid.setText("");
	    	 vid.setText("");
	    	 bf.setText("");
	    	 bt.setText("");
	    	 am.setText("");
	    	 }
		
	});
	     btn_delete.setOnAction(e->{
	    	 if(!id.getText().equals("")) {
		    	 
	    	 deletebtn(id.getText());
	    	 id.setText("");
	      	 uid.setText("");
	    	 vid.setText("");
	    	 bf.setText("");
	    	 bt.setText("");
	    	 am.setText("");
	    	 }
	});
	     
	     System.out.println(table.getSelectionModel().getSelectedItem());
	     table.setOnMouseClicked(ex -> {
	    	    if (ex.getClickCount() > 1) {
	    		     System.out.println(table.getSelectionModel().getSelectedItem());
	    	    	MyRent data=(MyRent) table.getSelectionModel().getSelectedItem();
	    	    id.setText(data.getId());
	    	    uid.setText(data.getUid());
	    	    vid.setText(data.getVid());
	    	    bf.setText(data.getBookingfrom());
	    	    bt.setText(data.getBookingtill());
	    	    am.setText(data.getAmount());
	    	    
	    	    }
	     });
	 	v.getChildren().add(id);	     
	v.getChildren().add(uid);
	v.getChildren().add(vid);
	v.getChildren().add(bf);
	v.getChildren().add(bt);
	v.getChildren().add(am);
	
	
	v.getChildren().add(btn_add);
	v.getChildren().add(btn_delete);
	v.getChildren().add(btn_modify);
	v.setTranslateX(280);
	v.setTranslateY(80);
	v.setSpacing(10);
	table.setTranslateX(300);
	table.setTranslateY(80);
	v.setTranslateX(38);
	v.setTranslateY(80);
	v.setSpacing(10);
	
	  table.getColumns().addAll(idcol,uidcol, vidcol,bfcol,btcol,amountcol);
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
	      String sql = "SELECT * FROM rent";
	      ResultSet rs = stmt.executeQuery(sql);
	      while(rs.next()){
	    	  MyRent m = new MyRent(rs.getString("r_id"),rs.getString("u_id"),rs.getString("v_id"),rs.getString("bookingfrom"),rs.getString("bookingtill"),rs.getString("amount"));
	    	  data.add(m);
	    	 }
	      rs.close();
	   }catch(SQLException se){
	      se.printStackTrace();
	   }
	
}
private void addbtn(String u,String v,String bf,String bt,String am) {
	
	 Connection conn = null;
	 PreparedStatement pstmt = null;
	   try{
	      conn = DriverManager.getConnection(Login.DB_URL, Login.USER, Login.PASS);
	      pstmt = conn.prepareStatement("Insert into rent(u_id,v_id,bookingfrom,bookingtill,amount) VALUES(?,?,?,?,?)");
	      //update users set num_points = ? where first_name = ?
	      pstmt.setString(1, u);
	      pstmt.setString(2, v);
	      pstmt.setString(3, bf);
	      pstmt.setString(4, bt);
	      pstmt.setString(5, am);
          
	      pstmt.executeUpdate();
	   }
	   catch(SQLException se){
	      se.printStackTrace();
	   }
	data.clear();
	 loaddata();	
}

private void modifybtn(String i,String u,String v,String bf,String bt,String am) {
	
	 Connection conn = null;
	 PreparedStatement pstmt = null;
	   try{
	      conn = DriverManager.getConnection(Login.DB_URL, Login.USER, Login.PASS);
	      pstmt = conn.prepareStatement("UPDATE rent SET u_id=?, v_id=?, bookingfrom=?, bookingtill=?, amount=?  WHERE r_id=?");
	      //update users set num_points = ? where first_name = ?
	      pstmt.setString(1, u);
	      pstmt.setString(2, v);
	      pstmt.setString(3, bf);
	      pstmt.setString(4, bt);
	      pstmt.setString(5, am);
	         
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
	      pstmt = conn.prepareStatement("DELETE FROM rent WHERE r_id=?");
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
