package bll;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import dao.DeskDAO;
import entity.Desk;

public class DeskBLL {

    public static void main(String[] args) throws Exception {
        DeskDAO d = new DeskDAO();
        List<Desk> l = d.findAll();
        System.out.println(l);
        String[] s = null;
        for (Desk footOpp : l) {
            s = footOpp.toString().split("[\\[,\\]]");
            System.out.println(Arrays.toString(s));
        }

        Desk ds = d.findById(8003);
        System.out.println(ds);
        s = ds.toString().split("[\\[,\\]]");
        System.out.println(Arrays.toString(s));
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        System.out.println(f.format(cal.getTime()));
        String str = f.format(cal.getTime());
        s = str.split(" ");
        System.out.println(Arrays.toString(s));
        // d.add(new Desk(1004,));
        Date date = new Date(cal.getTimeInMillis());
        Time time = new Time(cal.getTimeInMillis());
        System.out.println(f.format(date));
        System.out.println(time.toString());

        // short y = 6;
        // System.out.println();
        // long z = 7;
        // int a=3;
        // go(y);
        // go(z);
        // go(a);
        // 因为java基本数据类型中小的数据类型short如果找不到对应的小写short会自动专程比它大
        // 的基本数据类型，如果找不到才会找包装类
    }

    public static void go(Long n) {
        System.out.println("Long ");
    }

    public static void go(Short n) {
        System.out.println("Short ");
    }

    public static void go(byte n) {
        System.out.println("int ");
    }

    public static void go(long n) {
        System.out.println("ss");
    }

}
