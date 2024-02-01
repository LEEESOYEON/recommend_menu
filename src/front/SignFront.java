package front;

public class SignFront {
//회원가입 유효성 검사
	
	//아이디 영문or숫자+다섯글자이상
	public boolean idCheck(String id) {
		//정규표현식
		String regex = "^(?=.*[a-zA-Z])(?=.*[0-9]).{5,10}$";
		//영문과 숫자가 모두 포함되고 다섯글자 이상 열글자 이하 == true
		return id.matches(regex);
	}
	//비밀번호 특수문자 포함+다섯글자이상
	public boolean pwCheck(String pw) {
		String regex = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[@$!%*?&]).{5,10}$";
		//영문과 숫자가 모두 포함되고 다섯글자 이상 열글자 이하 특수문자 포함 == true
		return pw.matches(regex);
	}
}
