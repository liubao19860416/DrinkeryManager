package idao;

import java.util.List;

import entity.Desk;

public interface IDesk {

	List<Desk> findAll() throws Exception;
	Desk findById(int deskNo) throws Exception;
	Desk add(Desk newDesk) throws Exception;
	Desk modify(Desk oldDesk)throws Exception;
	void delete(String deskNo)throws Exception;
}
