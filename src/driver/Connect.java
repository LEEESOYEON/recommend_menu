package driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Connect {

	private String url="jdbc:oracle:thin:@192.168.0.18:1521:orcl";
	private String username="system";
	private String pass="1111";
	protected Connection connection=null;

	
	public boolean getConnection() {
		try {
			connection=DriverManager.getConnection(url , username , pass);
			System.out.println("Ŀ�ؼ� ����");
			return true;
		}catch(Exception e) {
			System.out.println("Ŀ�ؼ� ����");
		}
		return false;
	}
	
	//�ڿ��ݳ�
	public void close(PreparedStatement ps) {
		try {
			if(connection!=null) {
				connection.close();
			}
			if(ps!=null) {
				ps.close();
			}
		}catch(Exception e) {
			
		}
	}
	
	//�ڿ��ݳ�
	public void close(PreparedStatement ps,ResultSet r) {
		try {
			if(connection!=null) {
				connection.close();
			}
			if(ps!=null) {
				ps.close();
			}
			if(r!=null) {
				r.close();
			}
		}catch(Exception e) {
			
		}
	}
}
