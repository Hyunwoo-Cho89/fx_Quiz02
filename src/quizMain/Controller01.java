package quizMain;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

public class Controller01 implements Initializable{
	Parent root;
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userId = "mepuma";
	String userPwd = "1234";
	@FXML RadioButton rdoOversea;
	@FXML RadioButton rdoLocal;
	@FXML RadioButton rdoNo;
	@FXML RadioButton rdo10;
	@FXML RadioButton rdo20;
	@FXML RadioButton rdo30;
	@FXML RadioButton rdo40;
	@FXML RadioButton rdoMan;
	@FXML RadioButton rdoWoman;
	@FXML PieChart pieChart;
	@FXML BarChart barChart;
	@FXML AreaChart areaChart;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}	
		System.out.println("드라이브 로드 성공");
	}
	public void setRoot(Parent root) {
		this.root = root;
	}

	public void surveyComp() {
		String sql = "insert into surveyTrip(location, age, gender)" +  
						"values(?,?,?)";
		try {
			conn = DriverManager.getConnection(url, userId, userPwd);
			int location = 0;
			
			if(rdoOversea.isSelected()) location=1;
			if(rdoLocal.isSelected()) location=2;
			if(rdoNo.isSelected()) location =3;
			//System.out.println(location);
			int age = 0;
			if(rdo10.isSelected()) age=1;
			if(rdo20.isSelected()) age=2;
			if(rdo30.isSelected()) age=3;
			if(rdo40.isSelected()) age=4;
			//System.out.println(age);
			int gender = 0;
			if(rdoMan.isSelected()) gender=1;
			if(rdoWoman.isSelected()) gender=2;
			//System.out.println(gender);
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, location);
			ps.setInt(2, age);
			ps.setInt(3, gender);
			ps.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void surveyList() throws Exception{		
			Stage chartStage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("surveyChart.fxml"));
			Parent root01 = loader.load();
			Scene scene = new Scene(root01);
			
			Controller02 controller02 = loader.getController();
			controller02.setRoot(root01);
			
			chartStage.setScene(scene);
			chartStage.show();
		/*
		Stage chartStage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("surveyChart"));
		Parent root01 = loader.load();
		Scene scene = new Scene(root01);
		
		chartStage.setScene(scene);
		chartStage.show();
		*/
		//System.out.println("모든회원 보기 클릭");
	}
	
	public void surveyCancel() {
		Stage stage = (Stage)root.getScene().getWindow();
		stage.close();
		System.out.println("서베이 취소");
	}
}
