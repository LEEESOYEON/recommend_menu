package driver;

public class Driver {

	public static Driver single=null;
	private Driver() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("����̹� �ε� ����");
		}catch(Exception e) {
			System.out.println("����̹� �ε� ����");
		}
	}
	public static Driver getInstance() {
		if(single == null) {
			single= new Driver();
		} 
		return single;
	}
}
