package bll;

import java.util.List;

import dao.VigatableseriesDAO;
import entity.Vigatableseries;

public class VigatableseriesBLL {

    public static void main(String[] args) throws Exception {
        VigatableseriesDAO d = new VigatableseriesDAO();
        List<Vigatableseries> l = d.findAll();
        System.out.println(l);

        Vigatableseries d2 = d.findById("玩吧");
        System.out.println(d2);
    }

}
