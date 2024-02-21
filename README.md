# '요리 추천 프로그램' 프로젝트
<div align="center">
    <img src="https://github.com/LEEESOYEON/recommend_menu/assets/150236010/a8bbe26d-d890-4317-a393-2a9faeb4fcf2" width="100px" height="100px">
</div>

### <주제>
**요리 추천 프로그램 "맛있는 길잡이"**
### <프로젝트 목적>
**객체지향언어를 활용한 개발 경험**

<span>
- SQL문법을 활용한 데이터베이스 관리 및 조작 능력 향상<br>
- 객체지향 JAVA 언어의 class, 상속, 인터페이스 활용으로 이해도 증진<br>
- 디자인 패턴을 적용한 개발 경험 강화<br>
- JAVA와 oracle  DB 연동을 통한 CRUD 작업 경험<br>
- JAVA swing 을 활용한 GUI 개발 경험<br>
</span>

### <개발 일정>
**2023.11.20 ~ 2023.11.30 (11일)**
### <개발 인원>
**1인**

- ### __*소개*__
  식자재에 따른 요리 고민을 하는 사용자에게 요리를 추천해주고 정보를 제공하고자 합니다.<br>
  게시판을 사용하여 커뮤니티가 가능하게 하고 장바구니를 이용한 구매 유도가 이루어지게 하는 프로그램을 개발하게 되었습니다.

<div align="center">
    <img src="https://github.com/LEEESOYEON/recommend_menu/assets/150236010/13269bf2-4c1a-49b8-be15-742a29377a36" width="80%" height="100%">
</div>

- ### __*개발 환경*__
<div align="center">
    <img src="https://github.com/LEEESOYEON/recommend_menu/assets/150236010/e65be310-5ec3-4d3a-b3d0-8e1def340a54" width="80%" height="100%">
</div>

- ### __*개발 흐름도*__
<div align="center">
    <img src="https://github.com/LEEESOYEON/recommend_menu/assets/150236010/8814da24-c42b-4604-a186-d8ea11f1f2f9" width="80%" height="100%">
</div>

- ### __*DB 설계*__
<div align="center">
    <img src="https://github.com/LEEESOYEON/recommend_menu/assets/150236010/dd703b7b-4f23-4142-8ff5-34dffb22a03d" width="80%" height="100%">
</div>

- ### __*Class Diagram*__
<div align="center">
    <img src="https://github.com/LEEESOYEON/recommend_menu/assets/150236010/008fa08b-3cd6-40e7-be2d-1160a044f21b" width="80%" height="100%">
</div>

- ### __*활용 기술*__

**1. 상속 및 인터페이스**
  
▷ GUI 기반으로 구현하기 위해 Java Swing을 사용하여 JFrame을 상속받고 다양한 이벤트 리스너 인터페이스를 구현하여 사용자와의 상호작용을 지원합니다.<br>
▷ 데이터베이스의 정보를 가지고 커넥션을 담당하는 클래스를 따로 객체로 구현하여 DAO 클래스에 상속하여 재사용성을 높이고 유지보수가 용이해지게 했습니다.<br>
또한, 메모리 누수를 방지하기 위해 자원 반납 기능을 가진 메서드를 오버로딩하여 여러 형태로 사용했습니다.<br>

 **2. 싱글톤 객체 활용하기**

데이터베이스 연결을 유지하는데 필요한 Oracle JDBC 드라이버를 로드하는 클래스를 싱글톤으로 구현했습니다.<br>
매번 커넥션을 해야하는 것과 다르게 드라이버는 한 번만 로드하면 되므로 싱글톤으로 구현하여 동일한 드라이버가 여러번 로드되는 것을 방지하고 일관된 연결을 유지합니다.<br>
프로그램이 시작되는 main 클래스에서 싱글톤을 호출하여 사용했습니다.<br>

 **3. 데이터베이스 연결**

회원가입,로그인, 그리고 마이페이지와 같이 데이터베이스에 저장하고 결과를 받아야 하는 다양한 기능을 구현해야 했습니다.<br>
이를 위해 Oracle에서 제공하는 ojdbc 라이브러리를 활용하여 Java 어플리케이션과 Oracle 데이터베이스 간의 통신을 가능하게 했습니다.<br>
데이터베이스와의 상호작용을 담당하기 위해 DAO 클래스를 사용하였습니다.<br>
DAO 클래스는 커넥션을 수립하고 쿼리문 작성,실행,결과의 매핑을 수행하여 CRUD 기능을 구현합니다.<br>
CRUD 기능을 수행하는 메서드 정의 시 매개변수를 최대한 활용하여 코드의 중복을 줄이고, 단일 책임 원칙을 준수하면서도 보다 우연하고 재사용성 높은 코드를 구현할 수 있었습니다.<br>














