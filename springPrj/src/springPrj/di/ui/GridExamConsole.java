package springPrj.di.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import springPrj.di.entity.Exam;

@Component("console")
public class GridExamConsole implements ExamConsole {

	@Autowired
	@Qualifier("exam")
	private Exam exam;
	
	public GridExamConsole(Exam exam) {
		super();
		this.exam = exam;
	}

	public GridExamConsole() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void print() {
		// TODO Auto-generated method stub
		System.out.println("╔════════════╦═════════╗");
		System.out.println("║  total  ║  avg  ║");
		System.out.println("╠════════════╬══════════╣");
		System.out.printf("║    %d   ║   %f   ║\n", exam.total(), exam.avg());
		System.out.println("╚════════════╩═══════════╝");
	}

	@Override
	public void setExam(Exam exam) {
		this.exam = exam;
	}
}
