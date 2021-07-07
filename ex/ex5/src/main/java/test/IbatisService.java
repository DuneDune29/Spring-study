package test;

public class IbatisService {
	private IbatisDao ibatisDao;
	
	public void setIbatisDao (IbatisDao i) {
		this.ibatisDao = i;
	}
	
	public void daoTest() {
		System.out.println("START");
		ibatisDao.insertDB();
		ibatisDao.updateDB();
		System.out.println("END");
	}
}
