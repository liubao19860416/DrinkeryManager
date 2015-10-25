package dao;

import idao.IVigatableseries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import entity.Vigatableseries;

public class VigatableseriesDAO extends BaseDAO<Vigatableseries> implements
        IVigatableseries {

    private static final String SELETE = "select seriesNo,series from vigatableseries";
    private static final String FIND_BY_ID = SELETE + " where series=?";
    private static final String UPDATE = "update vigatableseries set series=?";
    private static final String INSERT = "insert into vigatableseries(seriesNo,series) values(?,?)";
    private static final String DELETE = "delete from vigatableseries where series=?";

    @Override
    public List<Vigatableseries> findAll() throws Exception {
        return find(SELETE, null);
    }

    @Override
    public Vigatableseries findById(String str) throws Exception {
        List<Vigatableseries> list = find(FIND_BY_ID, new Object[] { str });
        if (list.size() > 0)
            return list.get(0);
        return null;
    }

    @Override
    public Vigatableseries add(Vigatableseries newVigatableseries)
            throws Exception {
        return superAdd(newVigatableseries, INSERT);
    }

    @Override
    public Vigatableseries modify(Vigatableseries oldVigatableseries)
            throws Exception {
        return superUpdate(oldVigatableseries, UPDATE);
    }

    @Override
    public void delete(String series) throws Exception {
        superDelete(series, DELETE);
    }

    @Override
    public Vigatableseries toEntity(ResultSet rs) throws Exception {
        Vigatableseries v = new Vigatableseries();
        v.setSeriesNo(rs.getInt(1));
        v.setSeries(rs.getString(2));
        return v;
    }

    @Override
    public Vigatableseries addAccount(PreparedStatement pstmt, Vigatableseries t)
            throws Exception {
        pstmt.setObject(1, t.getSeriesNo());
        pstmt.setObject(2, t.getSeries());
        pstmt.executeUpdate();
        return t;
    }

    @Override
    public Vigatableseries updateAccount(PreparedStatement pstmt,
            Vigatableseries t) throws Exception {
        pstmt.setObject(1, t.getSeries());
        pstmt.executeUpdate();
        return t;
    }

    @Override
    public void deleteAccount(PreparedStatement pstmt, String str)
            throws Exception {
        pstmt.setObject(1, str);
        pstmt.executeUpdate();
    }

}
