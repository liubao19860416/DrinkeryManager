package dao;

import idao.IManagerSystem_gsl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import entity.ManagerSystem_gsl;

/***/
public class ManagerSystem_gslDAO extends BaseDAO<ManagerSystem_gsl> implements
        IManagerSystem_gsl {
    // private static final String
    // FINDALL="select accountno,pwd from managerSystem_gsl";
    private static final String FINDALL = "select accountno,pwd from managerSystem_gsl";
    private static final String FIND_BY_ACCOUNTNO = FINDALL
            + " where accountno=?";
    // MySQL
    private static final String INSERT = "insert into managerSystem_gsl(accountno,pwd) values(?,?)";
    // private static final String
    // INSERT="insert into managerSystem_gsl(system_id,accountno,pwd) values(se_sys_gsl.nextval,?,?)";
    private static final String MODIFY = "update managerSystem_gsl set pwd=? where accountno=?";
    private static final String DELETE = "delete from managerSystem_gsl where accountno=?";

    public List<ManagerSystem_gsl> findAll() throws Exception {
        return find(FINDALL, null);
    }

    public ManagerSystem_gsl findById(String accountno) throws Exception {
        List<ManagerSystem_gsl> l = find(FIND_BY_ACCOUNTNO,
                new Object[] { accountno });
        if (l.size() != 0)
            return l.get(0);
        return null;
    }

    public ManagerSystem_gsl add(ManagerSystem_gsl newManager) throws Exception {
        return superAdd(newManager, INSERT);
    }

    public ManagerSystem_gsl modify(ManagerSystem_gsl oldManager)
            throws Exception {
        return superUpdate(oldManager, MODIFY);
    }

    public void delete(String accountno) throws Exception {
        superDelete(accountno, DELETE);
    }

    /** 重写查找 */
    public ManagerSystem_gsl toEntity(ResultSet rs) throws Exception {
        ManagerSystem_gsl m = new ManagerSystem_gsl();
        m.setAccountno(rs.getString(1));
        m.setPwd(rs.getString(2));
        return m;
    }

    /** 重写插入 */
    public ManagerSystem_gsl addAccount(PreparedStatement pstmt,
            ManagerSystem_gsl newManager) throws Exception {
        pstmt.setString(1, newManager.getAccountno());
        pstmt.setString(2, newManager.getPwd());
        pstmt.executeUpdate();
        return newManager;
    }

    /** 重写修改 */
    public ManagerSystem_gsl updateAccount(PreparedStatement pstmt,
            ManagerSystem_gsl oldManager) throws Exception {
        pstmt.setString(1, oldManager.getPwd());
        pstmt.setString(2, oldManager.getAccountno());
        pstmt.executeUpdate();
        return oldManager;
    }

    /** 重写删除 */
    public void deleteAccount(PreparedStatement pstmt, String str)
            throws Exception {
        pstmt.setString(1, str);
        pstmt.executeUpdate();
    }
}
