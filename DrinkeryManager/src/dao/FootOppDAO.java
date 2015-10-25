package dao;

import idao.IFootOpp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import entity.FootOpp;

public class FootOppDAO extends BaseDAO<FootOpp> implements IFootOpp {

    private static final String FINDALL = "select productNo,productName,monad,unitPrice,seriesNo from footopp_gsl";

    private static final String FIND_BY_NO = FINDALL + " where productNo=?";
    // Oracle
    // private static final String
    // INSERT="insert into footopp_gsl(id,productNo,productName,monad,unitPrice) values(se_product_gsl.nextval,?,?,?,?)";
    // MySQL
    private static final String INSERT = "insert into footopp_gsl(productNo,productName,monad,unitPrice,seriesNo) values(?,?,?,?,?)";
    private static final String FIND_AND_SER = "select productNo,productName,monad,unitPrice,v.series from footopp_gsl f join vigatableseries v using(seriesNo)";
    private static final String UPDATE = "update footopp_gsl unitPrice=? where productNo=?";

    private static final String DELETE = "delete from footopp_gsl where productNo=?";

    public List<FootOpp> findAll() throws Exception {
        return find(FINDALL, null);
    }

    public FootOpp findById(String productNo) throws Exception {
        List<FootOpp> list = find(FIND_BY_NO, new Object[] { productNo });
        if (list.size() != 0)
            return list.get(0);
        return null;
    }

    public List<FootOpp> findANDSER() throws Exception {
        return find(FIND_AND_SER, null);
    }

    public FootOpp add(FootOpp newFootOpp) throws Exception {
        return superAdd(newFootOpp, INSERT);
    }

    public FootOpp modify(FootOpp oldFootOpp) throws Exception {
        return superUpdate(oldFootOpp, UPDATE);
    }

    public void delete(String productNo) throws Exception {
        superDelete(productNo, DELETE);
    }

    public FootOpp toEntity(ResultSet rs) throws Exception {
        FootOpp foot = new FootOpp();
        foot.setProductNo(rs.getString(1));
        foot.setProductName(rs.getString(2));
        foot.setManad(rs.getString(3));
        foot.setUnitPrice(rs.getDouble(4));
        foot.setSeriesNo(rs.getInt(5));
        return foot;
    }

    public FootOpp addAccount(PreparedStatement pstmt, FootOpp t)
            throws Exception {
        pstmt.setString(1, t.getProductNo());
        pstmt.setString(2, t.getProductName());
        pstmt.setString(3, t.getManad());
        pstmt.setDouble(4, t.getUnitPrice());
        pstmt.setInt(5, t.getSeriesNo());
        pstmt.executeUpdate();
        return t;
    }

    public FootOpp updateAccount(PreparedStatement pstmt, FootOpp t)
            throws Exception {
        pstmt.setDouble(1, t.getUnitPrice());
        pstmt.setString(2, t.getProductNo());
        pstmt.executeUpdate();
        return null;
    }

    public void deleteAccount(PreparedStatement pstmt, String str)
            throws Exception {
        pstmt.setString(1, str);
        pstmt.executeUpdate();
    }

}
