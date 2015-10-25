package bll;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;

import dao.FootOppDAO;
import dbutils.DBUtils;
import entity.FootOpp;

public class FootOppBLL {

    public static void main(String[] args) throws Exception {
        FootOppDAO f = new FootOppDAO();
        List<FootOpp> l = f.findANDSER();
        System.out.println(l);
        String[] s = null;
        for (FootOpp footOpp : l) {
            s = footOpp.toString().split("[\\[,\\]]");
        }
        System.out.println(Arrays.toString(s));

        final String FIND_AND_SER = "select productNo,productName,monad,unitPrice,v.series from footopp_gsl f join vigatableseries v using(seriesNo)";
        Connection conn = DBUtils.newManagerSystem_gsl();
        /** SQL语句不确定 */
        PreparedStatement pstmt = conn.prepareStatement(FIND_AND_SER);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getObject(1));
            System.out.println(rs.getObject(2));
            System.out.println(rs.getObject(3));
            System.out.println(rs.getObject(4));
            System.out.println(rs.getObject(5));
        }

    }

}
