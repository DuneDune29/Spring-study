package test;

public class ExamDao { // POJO 클래스
	private String msg;
	
	public ExamDao() {}; 
	
	public ExamDao(String msg) {
		this.msg = msg;
	}
	
	public void printMessage() {
		System.out.println("ExamDao.msg : " + msg);
	}
}
