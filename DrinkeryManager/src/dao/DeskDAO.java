package dao;

import idao.IDesk;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import entity.Desk;

public class DeskDAO extends BaseDAO<Desk> implements IDesk {

    private static final String FINDALL = "select deskNo,desktime from desk_gsl";
    // private static final String
    // FINDALL="select deskNo,DATE_FORMAT(desktime,'%Y %m %d %H %i %s') from desk_gsl";
    private static String FIND_BY_PAGE = "select a.* from (select row_number()over(order by deskNo) r,deskNo,desktime from desk_gsl)a where i*(p-1) and i*p";
    private static final String FIND_BY_NO = FINDALL + " where deskNo=?";
    private static final String INSERT = "insert into desk_gsl(deskNo) values(?)";
    private static final String DELETE = "delete from desk_gsl where deskNo=?";

    public List<Desk> findAll() throws Exception {
        return find(FINDALL, null);
    }

    public Desk findById(int deskNo) throws Exception {
        List<Desk> list = find(FIND_BY_NO, new Object[] { deskNo });
        if (list.size() != 0)
            return list.get(0);
        return null;
    }

    public List<Desk> findPage(int row, int page) throws Exception {
        FIND_BY_PAGE = "select a.* from (select row_number()over(order by deskNo) r,deskNo,desktime from desk_gsl)a where "
                + row * (page - 1) + " and " + row * page;
        return find(FIND_BY_PAGE, null);
    }

    public Desk add(Desk newDesk) throws Exception {
        return superAdd(newDesk, INSERT);
    }

    public Desk modify(Desk oldDesk) throws Exception {
        return null;
    }

    public void delete(String deskNo) throws Exception {
        superDelete(deskNo, DELETE);
    }

    public Desk toEntity(ResultSet rs) throws Exception {
        Desk desk = new Desk();
        desk.setDeskNo(rs.getInt(1));
        desk.setDate(rs.getDate(2));
        desk.setDeskTime(rs.getTime(2));

        return desk;
    }

    public Desk addAccount(PreparedStatement pstmt, Desk t) throws Exception {
        pstmt.setInt(1, t.getDeskNo());
        pstmt.executeUpdate();
        return t;
    }

    public Desk updateAccount(PreparedStatement pstmt, Desk t) throws Exception {
        return null;
    }

    public void deleteAccount(PreparedStatement pstmt, String deskNO)
            throws Exception {
        pstmt.setInt(1, Integer.parseInt(deskNO));
        pstmt.executeUpdate();
    }
}
