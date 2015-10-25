package UDPServer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Properties;

import dao.DeskDAO;
import entity.Desk;
import executorsPool.Pool;
import frame.ClientFrame;

public class Client {

    ClientFrame client;
    /** 调用客户端服务 */
    DatagramPacket packet;
    DatagramSocket socket;

    /** 调用台号数据库 */
    DeskDAO desk;
    String[] data = null;

    /** 获取配置文件 */
    Properties porp;

    /** 客户端端口 */
    int port;

    /** 客户端地址 */
    InetAddress ip;

    /** 发送数据 */
    byte[] buf;
    String productName;
    String countNo;

    /** 接收数据 */
    byte[] rebuf;

    /** 监听发送数据线程 */
    ListenerSend send;
    /** 监听数据接收线程 */
    ListenerReceive receive;

    // 主测试方法入口
    public static void main(String[] args) {
        Client c = new Client();
        c.action();
    }

    public Client() {
        desk = new DeskDAO();
        porp = new Properties();
        client = new ClientFrame();
        client.action();
        try {
            porp.load(Client.class.getClassLoader().getResourceAsStream(
                    "udp.properties"));
            port = Integer.parseInt(porp.getProperty("port"));
            ip = InetAddress.getByName(porp.getProperty("ip"));
            socket = new DatagramSocket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** 监听发送数据线程 */
    class ListenerSend implements Runnable {
        public void run() {
            /** 当点击按钮时获取菜名文本框数据和数量文本框数据 */
            client.OKOpp.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // 监听菜名数据变化
                    if (productName != null && countNo != null) {
                        try {
                            deskNO();
                            buf = (productName + "," + countNo + "," + data[2])
                                    .getBytes("UTF-8");
                            packet = new DatagramPacket(buf, buf.length, ip,
                                    port);
                            socket.send(packet);
                            receive = new ListenerReceive();
                            Pool.pool.execute(receive);
                            System.out.println("已发送");
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        } finally {
                            // socket.close();
                        }
                    }
                }
            });
        }
    }

    /** 监听数据接收线程 */
    class ListenerReceive implements Runnable {
        public void run() {
            System.out.println("asd");
            rebuf = new byte[1000];
            packet = new DatagramPacket(rebuf, rebuf.length);
            try {
                socket.receive(packet);
                String str = new String(rebuf, 0, packet.getLength(), "utf-8");
                client.checkNotic(str, "总台回复提示");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void deskNO() throws Exception {
        Desk ds = desk.findById(8003);
        System.out.println(ds);
        data = ds.toString().split("[\\[,\\]]");
    }

    public void action() {
        client.pruductName.addTextListener(new TextListener() {
            public void textValueChanged(TextEvent e) {
                productName = client.pruductName.getText().trim();
                System.out.println(productName);
            }
        });
        // 监听数量
        client.count.addTextListener(new TextListener() {
            public void textValueChanged(TextEvent e) {
                if (client.count.getText().trim().matches("[0-9]+")) {
                    countNo = client.count.getText().trim();
                    System.out.println(countNo);
                }
            }
        });
        send = new ListenerSend();
        Pool.pool.execute(send);
    }

}
