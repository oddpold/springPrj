package springPrj.di.ui;

import springPrj.di.entity.Exam;

public class InlineExamConsole implements ExamConsole {

	private Exam exam;
	
	public InlineExamConsole() {
		// TODO Auto-generated constructor stub
	}
	
	public InlineExamConsole(Exam exam) {
		super();
		this.exam = exam;
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
		System.out.printf("total = %d, avg = %f\n", exam.total(), exam.avg());
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}
	
}
