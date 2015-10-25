package UDPServer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import dao.FootOppDAO;
import entity.FootOpp;
import frame.ManagerSysFrame;

public class Server {

    /** 调用面板 */
    private ManagerSysFrame server;
    /** 连接服务器 */
    private DatagramPacket packet;
    private DatagramSocket socket;
    private Properties prop;
    /** 接收数据 */
    private byte[] buf;
    /** 服务器端口 */
    private int port;
    /** 监听回发线程 */
    private ListenerSend send;
    /** 接收返回数据拆分为数组 */
    private String[] data = null;
    private String[] s = null;
    private Double b = 0.0;

    List<FootOpp> list = null;
    FootOppDAO foot = new FootOppDAO();

    /** 判断文本位置 */
    private int y = 0;
    private Integer a = 1;

    Timer timer;
    TimerTask task;

    // 主测试方法入口
    public static void main(String[] args) {
        Server s = new Server();
        s.action();
    }

    public Server() {
        list = new ArrayList<FootOpp>();
        prop = new Properties();
        server = new ManagerSysFrame();
        try {
            prop.load(Server.class.getClassLoader().getResourceAsStream(
                    "udp.properties"));
            port = Integer.parseInt(prop.getProperty("port"));
            socket = new DatagramSocket(port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** 接收数据线程 */
    public void accept() {
        buf = new byte[1000];
        packet = new DatagramPacket(buf, buf.length);
        try {
            socket.receive(packet);
            String str = new String(buf, 0, packet.getLength(), "utf-8").trim();
            System.out.println(str);
            data = str.split(",");
            System.out.println(Arrays.toString(data));
            takeMenu();
            check();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** 监听回发线程 */
    class ListenerSend implements Runnable {
        public void run() {

        }
    }

    public void check() {
        /** 选择框监控件事 */
        server.choice.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                // 获得选择的值
                System.out.println(server.choice.getSelectedItem());
                /** 单选框选择事件 */
                server.jrb1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        server.showJradio.setText(server.jrb1.getText());
                        server.showPruductName.setText(s[2]);
                        server.showMonad.setText(s[3]);
                        server.amount.setText(data[1]);
                    }
                });

                server.jrb2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        server.showJradio.setText(server.jrb2.getText());
                        server.showPruductName.setText(s[2]);
                        server.showMonad.setText(s[3]);
                        server.amount.setText(data[1]);
                    }
                });
                server.payMoney.setText(b.toString());
                /** 付账方法 */
                pay();
            }
        });
    }

    /** 付账方法 */
    public void pay() {
        server.acceptMoney.addTextListener(new TextListener() {
            public void textValueChanged(TextEvent e) {
                // 空,只为监控值的变化
                System.out.println(server.acceptMoney.getText());
            }
        });

        // 结帐
        server.checkOut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 实收金额
                if (server.acceptMoney.getText().matches("[0-9]+.?[0-9]?")
                        && server.payMoney.getText().matches("[0-9]+.?[0-9]?")) {
                    server.pay = Double.parseDouble(server.acceptMoney
                            .getText())
                            - Double.parseDouble(server.payMoney.getText());
                    // 找零
                    server.pocketMoney.setText(server.pay.toString());
                }
            }
        });
    }

    /** 签单列表 */
    public void takeMenu() throws Exception {
        list = foot.findAll();
        for (FootOpp footOpp : list) {
            s = footOpp.toString().split("[\\[,\\]]");
            System.out.println(Arrays.toString(s));
            System.out.println(s[2] + "," + data[0]);
            if (s[2].equals(data[0])) {
                if (y > 273) {
                    y = 148;
                    server.repaint(0, 0, 997, 543);
                }
                b = Double.parseDouble(s[4].trim())
                        * Double.parseDouble(data[1]);
                server.textArea.append(a + "\t" + s[1] + "         " + s[2]
                        + "\t          " + s[3] + "\t           " + data[1]
                        + "\t    " + s[4] + "\t     " + b.toString() + "\n");
                server.textArea.paintImmediately(server.textArea.getBounds());
                a++;
            }
        }
    }

    public void action() {
        server.action();
        timer = new Timer();
        task = new TimerTask() {
            public void run() {
                accept();
            }
        };
        timer.schedule(task, 100, 100);
    }

}
