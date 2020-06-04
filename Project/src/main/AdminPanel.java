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

public class AdminPanel extends Group{

	public AdminPanel() {
		// TODO Auto-generated method stub
	Group root= new Group();
		HBox v= new HBox();
		
		
		Button btn_vehicle=new Button("Manage Vehicles");
		btn_vehicle.setPrefSize(250, 30);
		btn_vehicle.setTextFill(Color.WHITE);
		btn_vehicle.setStyle("-fx-background-color : green");
		
		Button btn_user=new Button("Manage Users");
		btn_user.setPrefSize(250, 30);
		btn_user.setTextFill(Color.WHITE);
		btn_user.setStyle("-fx-background-color : green");
		
		Button btn_rent=new Button("Manage Rent");
		btn_rent.setPrefSize(250, 30);
		btn_rent.setTextFill(Color.WHITE);
		btn_rent.setStyle("-fx-background-color : green");
		
		btn_user.setOnAction(e->{
			System.out.println("ahjkask");
			MUser m=new MUser();
			m.setTranslateX(80);
			m.setTranslateY(30);
			super.getChildren().add(m);
			
		});
		
		btn_vehicle.setOnAction(e->{
			System.out.println("ahjkask");
			MVehicles m=new MVehicles();
			m.setTranslateX(80);
			m.setTranslateY(30);
			super.getChildren().add(m);
			
		});
		btn_rent.setOnAction(e->{
			System.out.println("ahjkask");
			MRent m=new MRent();
			m.setTranslateX(80);
			m.setTranslateY(30);
			super.getChildren().add(m);
			
		});
	v.getChildren().addAll(btn_user,btn_vehicle,btn_rent);
		v.setTranslateX(120);
		v.setTranslateY(30);
		v.setSpacing(10);
		super.getChildren().add(v);
		super.getChildren().add(root);
		
	}
	
}
