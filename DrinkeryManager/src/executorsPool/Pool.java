package executorsPool;

import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import dao.ManagerSystem_gslDAO;
import entity.ManagerSystem_gsl;

public class Pool {
    ManagerSystem_gslDAO manager;
    /** 统一线程池 */
    public static final ExecutorService pool = Executors.newFixedThreadPool(4);

    /** 存放账户数据Map<String,Object> */
    public static final Map<String, String> map = new HashMap<String, String>();

    public static Color c = new Color(212, 208, 199);

    public static Color color() {
        return c;
    }

    public Pool() {
        manager = new ManagerSystem_gslDAO();
    }

    public Map<String, String> getMap() throws Exception {
        List<ManagerSystem_gsl> list = manager.findAll();
        for (ManagerSystem_gsl m : list) {
            String[] s = m.toString().split("[\\[,\\]]");
            map.put(s[1], s[2]);
        }
        return map;
    }
}
