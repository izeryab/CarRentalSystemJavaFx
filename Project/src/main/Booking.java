package main;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Booking extends Group{
	ObservableList<MyBooking> data = FXCollections.observableArrayList();
//String id, String vn, String vid, String bookingfrom, String bookingtill
	public Booking() {
	Group root = new Group();
	Rectangle clearback = new Rectangle(1000, 1000);
	clearback.setTranslateY(80);
	clearback.setFill(Color.WHITE);

	VBox v= new VBox();
	 TableView table = new TableView();
	 table.setEditable(true);

	 TableColumn vidcol = new TableColumn("Vehicle id");
	 TableColumn vncol = new TableColumn("Vehicle Name");
	 TableColumn vccol = new TableColumn("Vehicle Category");
	 TableColumn bfcol = new TableColumn("BookedFrom");
     TableColumn btcol = new TableColumn("BookedTill");
     
     
     vidcol.setMinWidth(100);
     vncol.setMinWidth(100);
     vccol.setMinWidth(100);

     bfcol.setMinWidth(100);
     btcol.setMinWidth(100);
        
     
   
 	TextField vid =new TextField();
 	vid.setPromptText("Vehicle Id");
 	vid.setFocusTraversable(false);
 	vid.setPrefSize(180, 30);
 	vid.setEditable(false);

 	TextField vn =new TextField();
 	vn.setPromptText("Vehicle name");
 	vn.setFocusTraversable(false);
 	vn.setPrefSize(180, 30);
 	vn.setEditable(false);
 	
 	TextField vc =new TextField();
 	vc.setPromptText("Vehicle Category");
 	vc.setFocusTraversable(false);
 	vc.setPrefSize(180, 30);
 	vc.setEditable(false);
 	
 	TextField bf =new TextField();
 	bf.setPromptText("Booking Date");
 	bf.setFocusTraversable(false);
 	bf.setPrefSize(180, 30);

 	String[] days = {"1","2","3","4","5","6","7","8","9","10"};
     ComboBox<String> bt =new ComboBox<String>(FXCollections.observableArrayList(days));
     bt.setPromptText("No of Days");
     bt.setFocusTraversable(false);
     bt.setPrefSize(180, 30);
	
     TextField am =new TextField();
     am.setPromptText("Amount");
     am.setEditable(false);
     am.setFocusTraversable(false);
     am.setPrefSize(180, 30);
	
	
	

	loaddata();	
	
	Button btn_book=new Button("Book");
	btn_book.setPrefSize(250, 30);
	btn_book.setTextFill(Color.WHITE);
	btn_book.setStyle("-fx-background-color : green");


		vidcol.setCellValueFactory(new PropertyValueFactory<MyBooking,String>("vid"));
	  	vncol.setCellValueFactory(new PropertyValueFactory<MyBooking,String>("vn"));
	  	vccol.setCellValueFactory(new PropertyValueFactory<MyBooking,String>("vc"));

	  	bfcol.setCellValueFactory(new PropertyValueFactory<MyBooking,String>("bf"));
	     btcol.setCellValueFactory(new PropertyValueFactory<MyBooking,String>("bt"));
	     
	     table.setItems(data);

	     
	     
	     
	     btn_book.setOnAction(e->{
	    	 ObservableList<MyBooking> tabledata= table.getItems();
	    	 boolean validdate=false;
	    	 boolean bfv=true;
	    	 boolean btv=true;
	    	 
	    	 for (MyBooking data : tabledata) {
	    		 if(bfv&&btv&&(data!=null)&&(data.getVid().equals(vid.getText())))
	    		 {
	    		bfv=datevalid(data.getBf(),data.getBt(),bf.getText());
	    		btv=datevalid(data.getBf(),data.getBt(),dategen(bf.getText(),bt.getSelectionModel().getSelectedItem()));
	    		System.out.println("bft "+bfv);
	    		System.out.println("btv "+btv);
	    		
	    		if(!bfv||!btv)break;
	    		else validdate=true;
	    		 }
	    	}
	    	 
	    	 
	    	 //System.out.println(validdate);
	    	 //System.out.println("jkhsakjdh"+datevalid("2020-06-04","2020-06-10","2020-06-04"));
	
//	    	 if(datevalid("2020-06-04","2020-06-10","2020-06-04"))
	
	    	 
//	    	 System.out.println();
//	    	 System.out.println(datevalid("2020-06-04","2020-06-10",bf.getText()));
//	    		System.out.println(datevalid("2020-06-04","2020-06-10",dategen(bf.getText(),bt.getValue())));
	    	 
	    		    	 
if(!validdate){
	    	 addbook(Login.loginID, vid.getText(), bf.getText(), dategen(bf.getText(), bt.getSelectionModel().getSelectedItem()), amgen(vc.getText(), bt.getSelectionModel().getSelectedItem()));
	      	 vid.setText("");
	      	 vn.setText("");
	    	 vc.setText("");
	    	 bf.setText("");
}
	});
	      
	//     System.out.println(table.getSelectionModel().getSelectedItem());
	     table.setOnMouseClicked(ex -> {
	    	    if (ex.getClickCount() > 1) {
	    		    MyBooking data=(MyBooking) table.getSelectionModel().getSelectedItem();
	    	    vid.setText(data.getVid());
	    	    vn.setText(data.getVn());
	    	    vc.setText(data.getVc());
	    	    bf.setText(data.getBf());
	    	
	    	    
	    	    }
	     });
	     
	     bt.setOnAction(e->{
	    	 am.setText(amgen(vc.getText(), bt.getValue()));
	     });
	     
	 	v.getChildren().add(vid);	     
	v.getChildren().add(vn);
	v.getChildren().add(vc);
	v.getChildren().add(bf);
	v.getChildren().add(bt);
	v.getChildren().add(am);
	
	
	
	v.getChildren().add(btn_book);
	v.setTranslateX(280);
	v.setTranslateY(80);
	v.setSpacing(10);
	table.setTranslateX(300);
	table.setTranslateY(80);
	v.setTranslateX(38);
	v.setTranslateY(80);
	v.setSpacing(10);
	
	  table.getColumns().addAll(vidcol,vncol, vccol,bfcol,btcol);
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
	      String sql = "SELECT DISTINCT vehicle.v_id,vehicle.name,vehicle.category,rent.bookingfrom,rent.bookingtill FROM vehicle,rent";
	      ResultSet rs = stmt.executeQuery(sql);
	      while(rs.next()){
	    	  MyBooking m = new MyBooking(rs.getString("v_id"),rs.getString("name"),rs.getString("category"),rs.getString("bookingfrom"),rs.getString("bookingtill"));
	    	  data.add(m);
	    	 }
	      rs.close();
	   }catch(SQLException se){
	      se.printStackTrace();
	   }
	
}

private static String dategen(String g,String days) {
	String c="";
	int count=0;
	int day=Integer.parseInt(days);
	for (int i = 0; i < g.toCharArray().length; i++) {
		if((count!=2)&&(g.charAt(i)=='-'))count++;
		else c=c+g.charAt(i);
	}
	day=day+Integer.parseInt(c);
	String nd=""+day;
	
	String year=nd.substring(0, 4);
	
	String month=nd.substring(4, 6);
	String d=nd.substring(6, 8);
	
	return year+"-"+month+"-"+d;
}

private static String amgen(String cat,String days) {
	int day=Integer.parseInt(days);
	int amount=100;
	if(cat.equals("luxury")) {
		amount=2*amount*day;
	}
	else {
		amount=amount*day;
	}
	
	return ""+amount;
}


public static boolean between(int i, int minValueInclusive, int maxValueInclusive) {
    return (i >= minValueInclusive && i <= maxValueInclusive);
}

private static boolean datevalid(String bookingfrom,String bookingtill,String yourbookingdate) {
	int ibf=Integer.parseInt(dateparse(bookingfrom));
	int ibt=Integer.parseInt(dateparse(bookingtill));
	int ibd=Integer.parseInt(dateparse(yourbookingdate));
	
	boolean valid=false;
		valid=between(ibd, ibf, ibt);
	return !valid;
}

private static String dateparse(String d) {
	String c="";
	int count=0;
	for (int i = 0; i < d.toCharArray().length; i++) {
		if((count!=2)&&(d.charAt(i)=='-'))count++;
		else c=c+d.charAt(i);
	}
	return c;
}
private void addbook(String u,String vid,String bf,String bt,String am) {

	 Connection conn = null;
	 PreparedStatement pstmt = null;
	   try{
	      conn = DriverManager.getConnection(Login.DB_URL, Login.USER, Login.PASS);
	      pstmt = conn.prepareStatement("Insert into rent(u_id,v_id,bookingfrom,bookingtill,amount) VALUES(?,?,?,?,?)");
	      //update users set num_points = ? where first_name = ?
	      pstmt.setString(1, u);
	      pstmt.setString(2, vid);
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


}
