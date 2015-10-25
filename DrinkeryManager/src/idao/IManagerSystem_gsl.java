package idao;

import java.util.List;
import entity.ManagerSystem_gsl;

/** 第三部创建接口 */
public interface IManagerSystem_gsl {
	/** 查询所有对象，返回有所对象集合 */
	List<ManagerSystem_gsl> findAll() throws Exception;
	/** 查询一个对象，返回一个对象 */
	ManagerSystem_gsl findById(String accountno) throws Exception;
	/** 添加一个对象，返回一个对象 */
	ManagerSystem_gsl add(ManagerSystem_gsl newManager) throws Exception;
	/** 修改一个对象，返回一个对象 */
	ManagerSystem_gsl modify(ManagerSystem_gsl oldManager) throws Exception;
	/** 删除一个对象*/
	void delete(String accountno) throws Exception;
}
