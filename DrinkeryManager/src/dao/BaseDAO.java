package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dbutils.DBUtils;

/**
 * 问题1：类中使用的实体类型不确定 解决：泛型类：泛型集合<T>占位，谁继承谁指定 查询方法
 * */
public abstract class BaseDAO<T> {
    /** 参数个数和参数类型不确定，用Object数组传参数 */
    public List<T> find(String sql, Object[] params) throws Exception {
        List<T> l = new ArrayList<T>();
        Connection conn = DBUtils.newManagerSystem_gsl();
        /** SQL语句不确定 */
        PreparedStatement pstmt = conn.prepareStatement(sql);
        /** 获得数组内容发送到服务器上 */
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
        }

        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            l.add(toEntity(rs));
        }
        DBUtils.closeConnection(conn);
        return l;
    }

    /** 返回子类实体对象，查找数据 */
    public abstract T toEntity(ResultSet rs) throws Exception;

    /** 添加方法 */
    public T superAdd(T t, String sql) throws Exception {
        Connection conn = DBUtils.newManagerSystem_gsl();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        try {
            conn.setAutoCommit(false);
            addAccount(pstmt, t);
            conn.commit();
            System.out.println("成功");
            return t;
        } catch (Exception e) {
            e.printStackTrace();
            conn.rollback();
        } finally {
            conn.setAutoCommit(true);
            DBUtils.closeConnection(conn);
        }
        return null;
    }

    /** 返回父类实体对象，插入数据 */
    public abstract T addAccount(PreparedStatement pstmt, T t) throws Exception;

    /** 更新方法 */
    public T superUpdate(T t, String sql) throws Exception {
        Connection conn = DBUtils.newManagerSystem_gsl();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        try {
            conn.setAutoCommit(false);
            updateAccount(pstmt, t);
            conn.commit();
            System.out.println("修改成功");
            return t;
        } catch (Exception e) {
            e.printStackTrace();
            conn.rollback();
        } finally {
            conn.setAutoCommit(true);
            DBUtils.closeConnection(conn);
        }
        return null;
    }

    /** 返回父类实体对象，修改数据 */
    public abstract T updateAccount(PreparedStatement pstmt, T t)
            throws Exception;

    /** 删除方法 */
    public void superDelete(String str, String sql) throws Exception {
        Connection conn = DBUtils.newManagerSystem_gsl();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        try {
            conn.setAutoCommit(false);
            deleteAccount(pstmt, str);
            conn.commit();
            System.out.println("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            conn.rollback();
        } finally {
            conn.setAutoCommit(true);
            DBUtils.closeConnection(conn);
        }
    }

    /** 返回父类实体对象，删除数据 */
    public abstract void deleteAccount(PreparedStatement pstmt, String str)
            throws Exception;

}
