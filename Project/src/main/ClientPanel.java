package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class ClientPanel extends Group{

	public ClientPanel() {
		// TODO Auto-generated method stub
	Group root= new Group();
		HBox v= new HBox();
		
		
		Button btn_book=new Button("Book a Car");
		btn_book.setPrefSize(250, 30);
		btn_book.setTextFill(Color.WHITE);
		btn_book.setStyle("-fx-background-color : green");
		
		Button btn_his=new Button("See History");
		btn_his.setPrefSize(250, 30);
		btn_his.setTextFill(Color.WHITE);
		btn_his.setStyle("-fx-background-color : green");
		
	
		
		btn_book.setOnAction(e->{
			System.out.println("ahjkask");
			Booking m=new Booking();
			m.setTranslateX(80);
			m.setTranslateY(30);
			super.getChildren().add(m);
			
		});
		
		btn_his.setOnAction(e->{
			System.out.println("ahjkask");
			History m=new History(Login.loginID);
			m.setTranslateX(80);
			m.setTranslateY(30);
			super.getChildren().add(m);
		});
	
	v.getChildren().addAll(btn_book,btn_his);
		v.setTranslateX(120);
		v.setTranslateY(30);
		v.setSpacing(10);
		super.getChildren().add(v);
		super.getChildren().add(root);
		
	}
	
}
