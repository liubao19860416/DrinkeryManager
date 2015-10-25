package frame;

import java.awt.AWTEvent;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class LoginFrame extends JFrame{

	public TextField account;
	public JPasswordField pwd;
//	JComboBox qx=new JComboBox();
	public JButton enter;
	public JButton exit;
	public JButton register;
	
	public LoginFrame() {
		account=new TextField();
		pwd=new JPasswordField();
		enter=new JButton(new ImageIcon(LoginFrame.class.getResource("enter.png")));
		exit=new JButton(new ImageIcon(LoginFrame.class.getResource("exit.png")));
		register=new JButton(new ImageIcon(LoginFrame.class.getResource("register.png")));
	    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
	    try {
	      jbInit();
	    }
	    catch(Exception e) {
	      e.printStackTrace();
	    }
	  }

	private void jbInit() throws Exception  {
		this.setSize(new Dimension(425, 286));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("酒店管理系统——登录");
		
	    this.getContentPane().setLayout(null) ;//设置布局管理器为空布局
	    account.setBounds(new Rectangle(252, 155, 100, 20));
	    pwd.setBounds(new Rectangle(252,181,100,20));
	    enter.setBounds(new Rectangle(148, 205, 60, 20));
	    register.setBounds(new Rectangle(218, 205, 60, 20));
	    exit.setBounds(new Rectangle(288, 205, 60, 20));
	    this.getContentPane().add(enter);
	    this.getContentPane().add(register);
	    this.getContentPane().add(exit);
	    ImageIcon img=new ImageIcon(LoginFrame.class.getResource("land_background.jpg"));
	    JLabel hy=new JLabel(img);
	    this.getLayeredPane().add(hy,new Integer(Integer.MIN_VALUE));
	    this.getContentPane().add(account);
	    this.getContentPane().add(pwd);
	    hy.setBounds(0,0,img.getIconWidth(),img.getIconHeight());
	    //设置控件透明
	    ((JPanel)getContentPane()).setOpaque(false);
	}
	
	public void action(){
		this.setVisible(true);
	} 
	
	
	public void checkNotic(String str1,String str2){
		Object[] message = {str1}; 
		JOptionPane.showConfirmDialog(null, message,str2,JOptionPane.DEFAULT_OPTION);
	}
	
	public static void main(String[] args){
		LoginFrame l=new LoginFrame();
		l.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		l.action();
	}
}
