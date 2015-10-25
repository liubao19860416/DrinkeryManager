package frame;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import dao.ManagerSystem_gslDAO;
import entity.Desk;
import entity.ManagerSystem_gsl;

public class UpdatePWDFrame extends JFrame{
	private JTextArea area;
	private TextField accountMenu;
	private TextField pwdMenu;
	private TextField account;
	public String accountNo;
	private TextField pwd;
	public String pwdNo;
	private TextField menu;
	private JScrollPane jspane;
	private JButton modifyPWD;
	ManagerSystem_gslDAO manager;
	
	public UpdatePWDFrame() {
		manager =new ManagerSystem_gslDAO();
		modifyPWD=new JButton("确认修改");
		area=new JTextArea();
		jspane=new JScrollPane(area);
		accountMenu=new TextField();
		pwdMenu =new TextField();
		account=new TextField();
		pwd =new TextField();
		menu=new TextField("修改的帐号\t新密码");
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
	    try {
	      jbInit();
	    }
	    catch(Exception e) {
	      e.printStackTrace();
	    }
	}
	private void jbInit() throws Exception {
		this.setSize(170, 285);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setTitle("修改密码");
		this.getContentPane().setLayout(null) ;
		
		addArea();
		
		area.setEditable(false);
		area.setForeground(Color.blue);
		area.setWrapStyleWord(true);
		jspane.setBounds(new Rectangle(0, 30, 170, 150));
		
		accountMenu.setBounds(new Rectangle(-1, -1, 85, 30));
		Font font=new Font("宋体",Font.BOLD,14);
		accountMenu.setForeground(Color.red);
		accountMenu.setFont(font);
		accountMenu.setText("帐号");
		accountMenu.setEnabled(false);
		
		pwdMenu.setBounds(new Rectangle(85, -1, 85, 30));
		pwdMenu.setForeground(Color.red);
		pwdMenu.setFont(font);
		pwdMenu.setText("密码");
		pwdMenu.setEnabled(false);
		
		account.setBounds(new Rectangle(-1, 203, 85, 25));
		account.addTextListener(new TextListener() {
			public void textValueChanged(TextEvent e) {
				if(account.getText().length()>0&&account.getText().length()<40){
					accountNo=account.getText();
					System.out.println(accountNo);
				}
			}
		});
		
		
		pwd.setBounds(new Rectangle(85, 203, 85, 25));
		pwd.addTextListener(new TextListener() {
			public void textValueChanged(TextEvent e) {
				if(pwd.getText().length()>0&&pwd.getText().length()<10){
					pwdNo=pwd.getText();
					System.out.println(pwdNo);
				}
			}
		});
		
		menu.setBounds(new Rectangle(-2, 178, 170, 25));
		menu.setEnabled(false);
		
		modifyPWD.setBounds(new Rectangle(0, 230, 170, 28));
		modifyPWD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(manager.findById(accountNo)!=null&&!accountNo.equals("Tsoft")){
						ManagerSystem_gsl managers=new ManagerSystem_gsl(accountNo,pwdNo);
						manager.modify(managers);
						pwd.setText(managers.getPwd());
						area.setText("");
						addArea();
						registerNotic("密码修改成功！","修改密码提示");
					}else{
						registerNotic("密码修改失败", "修改密码提示");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		this.getContentPane().add(jspane);
		this.getContentPane().add(accountMenu);
		this.getContentPane().add(pwdMenu);
		this.getContentPane().add(account);
		this.getContentPane().add(pwd);
		this.getContentPane().add(menu);
		this.getContentPane().add(modifyPWD);
	}
	public void addArea() throws Exception{
		List<ManagerSystem_gsl> l=manager.findAll();
		String[] s=null;
		for (ManagerSystem_gsl manaOpp : l) {
			s=manaOpp.toString().split("[\\[,\\]]");
			area.append(s[1]+"\t"+s[2]+"\n");
		}
	}
	
	public void registerNotic(String str1,String str2){
		Object[] message = {str1}; 
		JOptionPane.showConfirmDialog(null, message,str2,JOptionPane.DEFAULT_OPTION);
	}
	
	
	public void action(){
		this.setVisible(true);
	}

	public static void main(String[] args) {
		UpdatePWDFrame u=new UpdatePWDFrame();
		u.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		u.setVisible(true);
	}

}
