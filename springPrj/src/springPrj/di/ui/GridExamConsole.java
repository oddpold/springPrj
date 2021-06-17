package springPrj.di.ui;

import springPrj.di.entity.Exam;

public class GridExamConsole implements ExamConsole {

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

	public void setExam(Exam exam) {
		this.exam = exam;
	}
}
