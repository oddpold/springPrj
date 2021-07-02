package springPrj.di.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import springPrj.di.entity.Exam;

@Component("console")
public class InlineExamConsole implements ExamConsole {

//	@Autowired
	@Autowired(required = false)
	@Qualifier("exam")
	private Exam exam;
	
	public InlineExamConsole() {
		// TODO Auto-generated constructor stub
		System.out.println("constructor");
	}
	
	public InlineExamConsole(Exam exam) {
		super();
		System.out.println("overloded constructor");
		this.exam = exam;
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
		if (exam == null)
			System.out.printf("total = %d, avg = %f\n", 0, 0.0);
		else
			System.out.printf("total = %d, avg = %f\n", exam.total(), exam.avg());
	}

	@Override
	public void setExam(Exam exam) {
		System.out.println("setter");
		this.exam = exam;
	}
	
}
