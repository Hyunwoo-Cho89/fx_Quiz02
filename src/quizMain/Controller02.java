package quizMain;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

public class Controller02 implements Initializable{
	@FXML PieChart pieChart;
	@FXML BarChart barChart;
	@FXML AreaChart areaChart;
	Parent root;
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userId = "mepuma";
	String userPwd = "1234";
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}	
		
		
		
	}
	public void setRoot(Parent root) throws Exception {
		this.root = root;
		chartView();
	}
	
	public void chartView() throws Exception {
		String sql = "select * from surveyTrip";
		int local01 = 0, local02 =0, local03 =0;
		int age10= 0, age20 = 0 , age30 = 0, age40 = 0 ;
		int gender01 =0 , gender02 =0 ;
			conn = DriverManager.getConnection(url, userId, userPwd);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				if(rs.getInt("location")==1) {
					local01++;
				}else if(rs.getInt("location")==2) {
					local02++;
				}else if(rs.getInt("location")==3) {
					local03++;
				}
				if(rs.getInt("age")==1) {
					age10++;
				}else if(rs.getInt("age")==2) {
					age20++;
				}else if(rs.getInt("age")==3) {
					age30++;
				}else if(rs.getInt("age")==4) {
					age40++;
				}
				
				if(rs.getInt("gender")==1) {
					gender01++;
				}else if(rs.getInt("gender")==2) {
					gender02++;
				}
			}
			//PieChart
			pieChart.setData(FXCollections.observableArrayList(
					new PieChart.Data("10대", age10),
					new PieChart.Data("20대", age20),
					new PieChart.Data("30대", age30),
					new PieChart.Data("40대", age40)
					));
			
			//BarChart
			XYChart.Series series1 = new XYChart.Series();
			series1.setName("남자");
			series1.setData(FXCollections.observableArrayList(
					new XYChart.Data("2000",gender01)				
					));
			
			XYChart.Series series2 = new XYChart.Series();
			series2.setName("여자");
			series2.setData(FXCollections.observableArrayList(
					new XYChart.Data("2000",gender02)			
					));
			
			barChart.getData().add(series1);
			barChart.getData().add(series2);
			
			//AreaChart
			XYChart.Series series3 = new XYChart.Series();
			series3.setName("가고 싶은 여행지");
			series3.setData(FXCollections.observableArrayList(
					new XYChart.Data("해외여행",local01),
					new XYChart.Data("국내여행",local02),
					new XYChart.Data("없음",local03)		
					));			
			areaChart.getData().add(series3);

			
	}
	

}
