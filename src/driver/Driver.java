package driver;

public class Driver {

	public static Driver single=null;
	private Driver() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 로드 성공");
		}catch(Exception e) {
			System.out.println("드라이버 로드 실패");
		}
	}
	public static Driver getInstance() {
		if(single == null) {
			single= new Driver();
		} 
		return single;
	}
}
