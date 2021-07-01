package springPrj.di;

import java.util.ArrayList;
import java.util.List;

/*
 * 이클립스 인텔리센스가 사용하지 않는 import는 자동으로 접는다. 주의하자
 * preferences > java > editor > folding : 체크 해제
 */
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import springPrj.di.entity.Exam;
import springPrj.di.entity.NewlecExam;
import springPrj.di.ui.ExamConsole;
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
////		ExamConsole console = new GridExamConsole();
//		console.setExam(exam);
//		console.print();

		// 그냥 줄나누기
		System.out.print("\n");
		
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

		// 이클립스 문제인지 스프링 문제인지...선선언 후사용 규칙을 강요한다.
		// 주의 해서 사용하고, 주석을 꼭 남겨서 잊지 않도록 하자
//		ApplicationContext context = new ClassPathXmlApplicationContext("springPrj/di/setting.xml");
		context = new ClassPathXmlApplicationContext("springPrj/di/setting.xml");
		
		/*
		 * 이건 데이터를 보려고 만든 함수이다
		 */
//		Exam lpexam = context.getBean(Exam.class);
//		System.out.println(lpexam.toString());
		
		// 4-1. 객체의 클래스명으로 읽어 올 수있다. 단, 객체를 형변환 해야 한다
		ExamConsole lpconsole = (ExamConsole) context.getBean("console");

		// 4-2. 객체의 클래스를 직접 가져오는 방식
//		ExamConsole lpconsole = context.getBean(ExamConsole.class);
		lpconsole.print();
		
		System.out.println();
		
		// 컬렉션을 사용한다
		// 일반적인 컬렉션 사용방법
//		List<Exam> exams = new ArrayList<>();
//		exams.add(new NewlecExam(1,1,1,1));

		// context를 이용해서 xml에서 가져오자
//		List<Exam> exams = (List<Exam>) context.getBean("exams");
//		
//		for (Exam e : exams)
//		{
//			System.out.println(e);
//		}
		
	/*
	 * 5. 어노테이션을 이용한 DI
	 * <context:annotation-config /> 태그와 연동
	 * @Autowired 가 붙어 있는 함수를 자동으로 DI 해준다. 단점은 묻지마 참조이다. IoC 컨테이너에서 전부 찾아서 바인딩 한다
	 * 같은 객체(클래스)가 여러개가 있으면 오류. 반드시 id를 지정한다
	 * 여러개의 객체를 이용할 때에는 각각의 id를 구분 하고, @Qualifier("exam1")으로 확실하게 지정해서 바인딩 한다
	 * @Autowired
	 * @Qualifier("exam1") 는 짝으로 같이 다닌다. 위치는 어디나 가능한데, private 앞에 두면 기본생성자를 포함한 injection이 된다
	 * 오버로드 생성자에서는 주의한다. 어노테이션을 파라미터에 넣어야 한다.
	 * 
	 * ** @Autowired(required = false) 옵션을 사용하면 객체가 없어도 injection이 가능하다. 단, null 처리는 반드시 할것
	 * 
	 * @Component
	 * 클래스를 객체로 만든다. <context:component-scan base-package:"" /> 태그와 연동
	 * 지금의 console 객체처럼 지정된 클래스 이름으로 찾을 경우 xml에 설정이 안되 있으면 에러가 난다
	 * 그래서 어노테이션에서 @Component("console")이라고 지정을 한다
	 * component-scan 태그가 정의 되어 있으면 annotation-config는 생략 가능하다
	 */
		
	}

}
