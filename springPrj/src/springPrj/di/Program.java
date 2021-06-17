package springPrj.di;

/*
 * 이클립스 인텔리센스가 사용하지 않는 import는 자동으로 접는다. 주의하자
 */
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import springPrj.di.ui.ExamConsole;
import springPrj.di.ui.GridExamConsole;
import springPrj.di.ui.InlineExamConsole;

public class Program {

	// xml에서 객체를 읽어오기위한 인터페이스. 이클립스가 선선언 후사용 원칙으로 바꿔준다
	private static ApplicationContext context;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * 1. 프로젝트를 생성할 때, 프로젝트 이름을 정하고, 모듈은 생성하지 않는다
		 * 2. 엔트리포인트 클래스를 만들 때, 프로젝트명.패키지명(springPrj.di) 식으로 구분한다
		 * 3. 엔트리포인트 클래스(Program) 이름을 정하고 메인함수를 생성한다
		 * 4. java 형식이 이렇게 되는 것 같다. spring을 쓰면 maven 프로젝트를 사용한다
		 */
		System.out.print("테스트 test\n");
		
		/*
		 * 1. 일반적인 방식
		 */
		// top-down 방식
		// 구문을 작성하고 마우스를 가져대면 인터페이스 생성 / 클래스 생성 등의 메뉴가 나온다
		// 이렇게 작성을 하는게 자바 방식인 것 같다
//		Exam exam = new NewlecExam();
//		ExamConsole console = new InlineExamConsole(exam);
//		ExamConsole console = new GridExamConsole(exam);
//		console.print();
		
		/*
		 * 2. 생성자를 사용하는 또다른 방법. interface class에 setter 함수를 만들고 java class에서 오버라이딩 한다
		 * 이클립스 2021.06 버전 현재 : 각 클래스에 맞게 setter 함수를 자동 생성이 가능하다
		 */
//		Exam exam = new NewlecExam();
//		ExamConsole console = new InlineExamConsole();
//		ExamConsole console = new GridExamConsole();
//		console.setExam(exam);
//		console.print();

		/*
		 * 3. 스프링을 사용해서 settings.xml에 지시를 한다
		 */
		// Ctrl + N(select a wizard)를 실행 해서 spring > spring bean configuration file을 생성한다
		// 여기에 사용하고자 하는 클래스, 인터페이스의 인스턴스를 생성하고, 정의 한다
		// *** 중요 : 프로젝트를 생성할 때 메인패키지가 정의가 안되 있으면 에러 발생
		// bean tag는 dll의 클래스, property tag는 dll의 함수와 비슷한 개념인것 같다
//		Exam exam = new NewlecExam();
//		ExamConsole console = new InlineExamConsole();
//		console.setExam(exam);
//		console.print();

		context = new ClassPathXmlApplicationContext("springPrj/di/setting.xml");
		
		// 4-1. 객체의 클래스명으로 읽어 올 수있다. 단, 객체를 형변환 해야 한다
//		ExamConsole console = (ExamConsole) context.getBean("console");

		// 4-2. 객체의 클래스를 직접 가져오는 방식
		ExamConsole console = context.getBean(ExamConsole.class);
		
		console.print();
		
	}

}
