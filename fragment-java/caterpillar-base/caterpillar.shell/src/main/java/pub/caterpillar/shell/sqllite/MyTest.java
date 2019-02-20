package pub.caterpillar.shell.sqllite;

import java.sql.Connection;
import java.sql.DriverManager;

public class MyTest {

	public static void main(String[] args){
		try{
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:sqlite:data/test.sqlite");
			System.out.println("成功");
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
}
