package idao;

import java.util.List;

import entity.FootOpp;

public interface IFootOpp {

	List<FootOpp> findAll() throws Exception;
	
	FootOpp findById(String productNo) throws Exception;
	
	FootOpp add(FootOpp newFootOpp) throws Exception;
	
	FootOpp modify(FootOpp oldFootOpp) throws Exception;
	
	void delete(String productNo)throws Exception;
}

