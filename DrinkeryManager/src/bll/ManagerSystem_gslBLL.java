package bll;

import idao.IManagerSystem_gsl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.ManagerSystem_gslDAO;
import entity.ManagerSystem_gsl;

public class ManagerSystem_gslBLL {

    public static void main(String[] args) {
        IManagerSystem_gsl manager = new ManagerSystem_gslDAO();
        Map<String, String> map = new HashMap<String, String>();
        ManagerSystem_gsl m = new ManagerSystem_gsl("tss1a", "sss11");
        System.out.println(m.getAccountno());
        try {
            // System.out.println(manager.findById("5"));
            // manager.add(m);
            // manager.modify(m);
            // manager.delete("tss1a");
            // System.out.println("ss");
            List<ManagerSystem_gsl> l = manager.findAll();
            for (ManagerSystem_gsl managerSystem : l) {
                System.out.println(managerSystem.toString());
                String[] s = managerSystem.toString().split("[\\[,\\]]");
                map.put(s[1], s[2]);
            }
            System.out.println(map);
            // ManagerSystem_gsl m=manager.findById("s");
            // if(m==null){
            // System.out.println("null");
            // }else{
            // System.out.println("s");
            // }

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}
