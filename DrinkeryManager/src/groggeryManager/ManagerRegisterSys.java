package groggeryManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;

import javax.swing.JFrame;

import dao.ManagerSystem_gslDAO;
import entity.ManagerSystem_gsl;
import executorsPool.Pool;
import frame.RegisterFrame;

/** 管理员账户注册 */
public class ManagerRegisterSys {
	
	/** 调用页面 */
	private RegisterFrame register;
	/** 调用数据库  */
	private ManagerSystem_gslDAO dataDAO;
	/** 创建新帐号 */
	private String cAccount;
	/** 创建新密码 */
	private String cPWD;
	Pool pool=new Pool();
	public ManagerRegisterSys() {
		dataDAO=new ManagerSystem_gslDAO();
		register=new RegisterFrame();
	}
	
	public void action(){
		checkAccountNo();
		register.action();
	}
	/**
	 * 1.提取帐号框的数据
	 * 2.提取密码框的数据
	 * 3.判断帐号是否存在并且密码是否在8-11位，如果在添加数据到数据库，否则返回帐号已存在或则密码输入位数不够
	 * 4.当点击退出的时候关闭注册页面
	 * */
	public void checkAccountNo(){
		//1.提取帐号框的数据
		register.registerAccount.addTextListener(new TextListener() {
			public void textValueChanged(TextEvent e) {
				cAccount=register.registerAccount.getText();
			}
		});
		
		//2.提取密码框的数据
		register.registerPWD.addTextListener(new TextListener() {
			public void textValueChanged(TextEvent e) {
				cPWD=register.registerPWD.getText();
			}
		});	
		
		//3.判断帐号是否存在并且密码是否在8-11位，如果在添加数据到数据库，否则返回帐号已存在或则密码输入位数不够
		register.regist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//判断帐号是否存在
					if(pool.getMap().containsKey(register.registerAccount.getText())){
						register.registerNotic("帐号已存在", "注册页面提示");
						return;
					}
					//判断密码是否符合3-8位
					if(register.registerPWD.getText().length()<3||register.registerPWD.getText().length()>11){
						register.registerNotic("密码位数不正确", "注册页面提示");
						return;
					}
					//判断帐号密码是否为空，不为空才加入数据
					if(cAccount!=null && cPWD!=null){
						pool.getMap().put(cAccount, cPWD);
						dataDAO.add(new ManagerSystem_gsl(cAccount,cPWD));
						register.registerNotic("注册成功", "注册页面提示");
						//注册成功关闭注册页面
						register.dispose();
						//加入数据后必须马上退出，否则会有唯一约束异常
						return;
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		
		//4.当点击退出的时候，退出注册
		register.exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				register.dispose();
			}
		});
	}
}
