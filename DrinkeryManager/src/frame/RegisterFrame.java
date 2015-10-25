package frame;

import java.awt.AWTEvent;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.TextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class RegisterFrame extends JFrame{
	
	/** 用户注册帐号*/
	public TextField registerAccount;
	/** 用户注册密码 */
	public TextField registerPWD;
	/** 确定注册 */
	public JButton regist;
	/** 退出 */
	public JButton exit;
	public RegisterFrame() {
		registerAccount=new TextField();
		registerPWD=new TextField();
		regist=new JButton(new ImageIcon(LoginFrame.class.getResource("OK.png")));
		exit=new JButton(new ImageIcon(LoginFrame.class.getResource("exit.png")));
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
	    try {
	      jbInit();
	    }
	    catch(Exception e) {
	      e.printStackTrace();
	    }
	  }
	
	private void jbInit() throws Exception  {
		this.setSize(new Dimension(660, 416));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setTitle("管理员注册页面");

	    this.getContentPane().setLayout(null) ;//设置布局管理器为空布局
	    //指定坐标空间中的一个区域
	    registerAccount.setBounds(new Rectangle(352, 163, 100, 20));
	    registerPWD.setBounds(new Rectangle(352,191,100,20));
	    regist.setBounds(new Rectangle(352, 225, 51, 22));
	    exit.setBounds(new Rectangle(405, 225, 51, 22));
	    this.getContentPane().add(regist);
	    this.getContentPane().add(exit);
	    
	    ImageIcon img=new ImageIcon(LoginFrame.class.getResource("registerBg.png"));
	    JLabel hy=new JLabel(img);
	    this.getLayeredPane().add(hy,new Integer(Integer.MIN_VALUE));
	    this.getContentPane().add(registerAccount);
	    this.getContentPane().add(registerPWD);
	    hy.setBounds(0,0,img.getIconWidth(),img.getIconHeight());
	    //设置控件透明
	    ((JPanel)getContentPane()).setOpaque(false);
	}
	
	/** 注册显示提示 */
	public void registerNotic(String str1,String str2){
		Object[] message = {str1}; 
		JOptionPane.showConfirmDialog(null, message,str2,JOptionPane.DEFAULT_OPTION);
	}
	
	public void action(){
		this.setVisible(true);
	} 
	
	public static void main(String[] args){
		RegisterFrame r=new RegisterFrame();
		r.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		r.action();
	}
}
