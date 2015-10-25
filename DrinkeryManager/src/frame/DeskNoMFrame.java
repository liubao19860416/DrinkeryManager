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
import java.sql.Time;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import dao.DeskDAO;
import entity.Desk;

public class DeskNoMFrame extends JFrame{

	public JTextArea area;
	private TextField deskNOMenu;
	private TextField deskMenu;
	public TextField deskNO;
	public int deNo;
	private TextField times;
	public String de;
	private TextField menu;
	private JScrollPane jspane;
	public JButton deleteDesk;
	public JButton addDesk;
	public DeskDAO desk;
	private Time time;
	Timer timer;
	TimerTask task;
	public DeskNoMFrame() {
		desk=new DeskDAO();
		deleteDesk=new JButton("删除");
		addDesk=new JButton("添加");
		area=new JTextArea();
		jspane=new JScrollPane(area);
		deskNOMenu=new TextField();
		deskMenu =new TextField();
		deskNO=new TextField();
		times =new TextField();
		menu=new TextField("选择的台号\t开台时间");
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
		this.setTitle("台号管理");
		this.getContentPane().setLayout(null) ;
		
		addArea();
		
		area.setEditable(false);
		area.setForeground(Color.blue);
		area.setWrapStyleWord(true);
		jspane.setBounds(new Rectangle(0, 30, 170, 150));
		
		deskNOMenu.setBounds(new Rectangle(-1, -1, 85, 30));
		Font font=new Font("宋体",Font.BOLD,14);
		deskNOMenu.setForeground(Color.red);
		deskNOMenu.setFont(font);
		deskNOMenu.setText("台号");
		deskNOMenu.setEnabled(false);
		
		deskMenu.setBounds(new Rectangle(85, -1, 85, 30));
		deskMenu.setForeground(Color.red);
		deskMenu.setFont(font);
		deskMenu.setText("开台时间");
		deskMenu.setEnabled(false);
		
		deskNO.setBounds(new Rectangle(-1, 203, 85, 25));
		deskNO.addTextListener(new TextListener() {
			public void textValueChanged(TextEvent e) {
				if(deskNO.getText().matches("[0-9]{1,4}")){
					deNo=Integer.parseInt(deskNO.getText());
					System.out.println(deNo);
				}
			}
		});
		
		
		times.setBounds(new Rectangle(85, 203, 85, 25));
		timer=new Timer();
		task=new TimerTask() {
			public void run() {
				time=null;
				time=new Time(Calendar.getInstance().getTimeInMillis());
				times.setText(time.toString());
			}
		};
		timer.schedule(task, 1000,10);
		menu.setBounds(new Rectangle(-2, 178, 170, 25));
		menu.setEnabled(false);
		
		addDesk.setBounds(new Rectangle(0, 230, 85, 28));
		deleteDesk.setBounds(new Rectangle(85, 230, 85, 28));
	
		this.getContentPane().add(jspane);
		this.getContentPane().add(deskNOMenu);
		this.getContentPane().add(deskMenu);
		this.getContentPane().add(deskNO);
		this.getContentPane().add(times);
		this.getContentPane().add(menu);
		this.getContentPane().add(deleteDesk);
		this.getContentPane().add(addDesk);
	}
	
	public void addArea() throws Exception{
		List<Desk> l=desk.findAll();
		String[] s=null;
		for (Desk deskOpp : l) {
			s=deskOpp.toString().split("[\\[,\\]]");
			System.out.println(l);
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
		DeskNoMFrame d=new DeskNoMFrame();
		d.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		d.action();
	}

}
