package front;

public class SignFront {
//ȸ������ ��ȿ�� �˻�
	
	//���̵� ����or����+�ټ������̻�
	public boolean idCheck(String id) {
		//����ǥ����
		String regex = "^(?=.*[a-zA-Z])(?=.*[0-9]).{5,10}$";
		//������ ���ڰ� ��� ���Եǰ� �ټ����� �̻� ������ ���� == true
		return id.matches(regex);
	}
	//��й�ȣ Ư������ ����+�ټ������̻�
	public boolean pwCheck(String pw) {
		String regex = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[@$!%*?&]).{5,10}$";
		//������ ���ڰ� ��� ���Եǰ� �ټ����� �̻� ������ ���� Ư������ ���� == true
		return pw.matches(regex);
	}
}
