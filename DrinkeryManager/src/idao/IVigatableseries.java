package idao;

import java.util.List;

import entity.Vigatableseries;

public interface IVigatableseries {

	List<Vigatableseries> findAll() throws Exception;
	Vigatableseries findById(String str) throws Exception;
	Vigatableseries add(Vigatableseries newVigatableseries)throws Exception;
	Vigatableseries modify(Vigatableseries oldVigatableseries)throws Exception;
	void delete(String str)throws Exception;
}
