package groggeryManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;

import UDPServer.Server;
import executorsPool.Pool;
import frame.LoginFrame;
import frame.ManagerSysFrame;

public class ManagerSystem{

	/**
	 * （1）用户登录后，进入酒店管理主界面，如图7所示。
	 * 类 ManagerSystem
	 * 帐号accountNo
	 * 密码passWord
	 * 管理员登录页面 enterPage
	 * 管理员管理员系统 managerSys
	 * 管理界面标志 State
	 * 存放账户数据Map<String,Object>
	 * KeyStroke键盘操作
	 * JButton按钮的实现
	 * JTextField单行文本输入
	 * paramString()  返回此 JTextField 的字符串表示形式。
	 * setHorizontalAlignment(int alignment) 设置文本的水平对齐方式。
	 * setScrollOffset(int scrollOffset) 获取滚动偏移量（以像素为单位）。
	 * JMenu弹出菜单
	 * JMenuBar菜单栏的实现
	 * JMenuItem菜单中的项的实现
	 * JPanel画板
	 * JFrame 窗体
	 * 1.绘制管理员界面
	 * 2.从服务器中提取管理员账户
	 * 3.从服务器中提取管理员密码
	 * 4.从界面中获取数据的帐号密码返回给程序
	 * 5.当输入帐号密码点击登录时，判断管理员帐号密码是否正确，如果正确进入管理员系统
	 * 定义一个变量用来作为登录状态，分别是管理员登录界面状态和管理状态
	 * 6.当点击页面的退出时，退出软件
	 * 7.如果错误，返回管理员帐号密码错误,并要求重新输入
	 * 8.当进入管理界面的时候，点击创建账户新建一个账户保存到散列表中
	 */
//	/** 帐号accountNo */
//	public static  String ACCOUNTNO ="Tsoft";
//	/**  密码passWord */
//	public static  String PASSWORD="111";
	/** 获取页面帐号的值 */
	private String checkAccount=null;
	
	/** 获取页面密码的值 */
	private String checkPWD=null;
	
	/** 管理员登录页面 */
	private LoginFrame login;
	
	/** 管理界面*/
	private Server managerSys;
	
	/** 监听系统退出线程 */
	private ListenerExit lExit;
	
	/** 调用线程池和map数据*/
	Pool pool=new Pool();
	
	/** 监听系统登录线程 */
	private ListenerEnter lEnter;
	
	/** 监听管理员账户注册线程*/
	private ListenerRegister lRegister;
	
	/** 管理员账户注册 */
	ManagerRegisterSys regSys;
	
	
	public ManagerSystem() {
		login=new LoginFrame();
		managerSys=new Server();
		regSys=new ManagerRegisterSys();
	}
	
	/** 监听管理员账户注册线程*/
	class ListenerRegister implements Runnable{
		public void run() {
			login.register.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					regSys.action();
				}
			});
		}
	}
	
	/** 监听系统退出线程 */
	class ListenerExit implements Runnable{
		/** 6.当点击页面的退出时，退出软件*/
		public void run() {
			login.exit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
		}
	}
	
	/** 监听系统登录线程 */
	class ListenerEnter implements Runnable{
		/** 5.当输入帐号密码点击登录时，判断管理员帐号密码是否正确，如果正确进入管理员系统*/
		public void run() {
			login.enter.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					try {
						//6.如果错误，返回管理员帐号密码错误,并要求重新输入
						if(!pool.getMap().containsKey(checkAccount)){
							/** 7.如果错误，返回管理员帐号密码错误,并要求重新输入,JOptionPane，用户提示框*/
							login.checkNotic("您输入的帐号或密码有误,请重新输入","登录提示"); 
							return;
						}
						
						if(!pool.getMap().get(login.account.getText().trim()).equals(checkPWD)){
							login.checkNotic("您输入的帐号或密码有误,请重新输入","登录提示");
							return;
						}
						login.dispose();
						managerSys.action();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			});
		}
	}

	/** 获得页面输入的管理员帐号和密码 */
	private void regexManager() {
		//4.从界面中获取数据的帐号密码返回给程序
		/** 调用帐号框文本监听 */
		TextListener l=new TextListener(){
			public void textValueChanged(TextEvent e) {
				//获取帐号信息
				checkAccount=login.account.getText();
			}
		};
		login.account.addTextListener(l);
		/** 通过焦点集中和丢失获取密码值 */
		login.pwd.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e) {
				/** 保证线程安全 */
				synchronized (login.pwd) {
					checkPWD=new String(login.pwd.getPassword());
				}
			}
			public void focusGained(FocusEvent e) {
			}
		});
	}
	
	/** 启动管理系统 */
	public void action(){
		lExit=new ListenerExit();
		lEnter=new ListenerEnter();
		lRegister=new ListenerRegister();
		//启动系统登录线程
		Pool.pool.execute(lEnter);
		//启动系统退出线程
		Pool.pool.execute(lExit);
		//启动管理员注册系统
		Pool.pool.execute(lRegister);
		//启动登录面板
		login.action();
		//获得页面帐号和密码
		regexManager();
	}
	
	/** 启动系统  */
	public static void main(String[] args) {
		ManagerSystem manager = new ManagerSystem();
		manager.action();
	}

}
