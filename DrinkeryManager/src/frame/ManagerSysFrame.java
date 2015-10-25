package frame;

import java.awt.AWTEvent;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import dao.DeskDAO;
import entity.Desk;
import executorsPool.Pool;

public class ManagerSysFrame extends JFrame{

	public JButton sort;
	public JButton menu;
	public JButton desk;
	
	public JButton day;
	public JButton month;
	public JButton year;
	
	public JButton user;
	public JButton passWord;
	public JButton exit;
	
	/** 下拉框选择 */
	public Choice choice;
	
	/** 单选框 */
	public ButtonGroup bg;//创建按钮组
	public JRadioButton jrb1;
	public JRadioButton jrb2; 
	
	/** 用于显示单选框选择的文本框 */
	public TextField showJradio;
	
	/** 用于显示商品名称 */
	public TextField showPruductName;
	
	/** 用于显示单位 */
	public TextField showMonad;
	
	/** 用于显示数量 */
	public TextField amount;
	
	/** 开单按钮 */
	public JButton openOpp;
	
	/** 签单按钮 */
	public JButton endorseOpp;
	
	/** 取消按钮 */
	public JButton cancelOpp;
	
	/** 消费金额 */
	public TextField payMoney;
	public Double pay;
	
	/** 实收金额 */
	public TextField acceptMoney;
	
	/** 找零金额 */
	public TextField pocketMoney;
	
	/** 结帐 */
	public JButton checkOut;
	
	/** 用于显示开台序号 */
	private JTextArea deskArea;
	/** 调用台号数据库 */
	private DeskDAO deskDAO;
	public String[] data=null;
	
	/** 时间显示区域 */
	private JTextArea timeArea;
	private Calendar cal;
	private SimpleDateFormat fmt;
	
	public JTextArea textArea;
	
	private VigatableSeriesFrame vigSer;
	private VigatableProductFrame vigPro;
	private DeskNoMFrame deskNo;
	private UpdatePWDFrame updatePWD;
	Timer timer;
	TimerTask task;
	private Time time;
	public ManagerSysFrame() {
		updatePWD=new UpdatePWDFrame();
		deskNo=new DeskNoMFrame();
		vigPro=new VigatableProductFrame();
		vigSer=new VigatableSeriesFrame();
		deskArea=new JTextArea();
		textArea=new JTextArea();
		cal=Calendar.getInstance();
		fmt=new SimpleDateFormat("HH:mm:ss");
		timeArea=new JTextArea();
		deskDAO=new DeskDAO();
		checkOut=new JButton("结帐");
		pocketMoney=new TextField();
		pocketMoney.setBackground(Pool.color());
		acceptMoney=new TextField();
		payMoney=new TextField();
		payMoney.setBackground(Pool.color());
		cancelOpp=new JButton("取消");
		endorseOpp=new JButton("签单");
		openOpp=new JButton("开单");
		amount=new TextField();
		showMonad=new TextField();
		showMonad.setBackground(Pool.color());
		showPruductName=new TextField();
		showPruductName.setBackground(Pool.color());
		showJradio=new TextField();
		bg = new ButtonGroup();
		jrb1 = new JRadioButton("编号",true);
		jrb2 = new JRadioButton("助记码"); 
		//单选框添加选择项
		bg.add(jrb1);
		bg.add(jrb2);
		
		choice=new Choice();
		
		sort=new JButton(new ImageIcon(LoginFrame.class.getResource("/img/sort.jpg")));
		sort.setBorder(null);
		menu=new JButton(new ImageIcon(LoginFrame.class.getResource("/img/menu.jpg")));
		menu.setBorder(null);
		desk=new JButton(new ImageIcon(LoginFrame.class.getResource("/img/desk.jpg")));
		desk.setBorder(null);
		
		day=new JButton(new ImageIcon(LoginFrame.class.getResource("/img/day.png")));
		day.setBorder(null);
		month=new JButton(new ImageIcon(LoginFrame.class.getResource("/img/month.png")));
		month.setBorder(null);
		year=new JButton(new ImageIcon(LoginFrame.class.getResource("/img/year.png")));
		year.setBorder(null);
		
		user=new JButton(new ImageIcon(LoginFrame.class.getResource("/img/user.jpg")));
		user.setBorder(null);
		passWord=new JButton(new ImageIcon(LoginFrame.class.getResource("/img/password.jpg")));
		passWord.setBorder(null);
		exit=new JButton(new ImageIcon(LoginFrame.class.getResource("/img/exit.jpg")));
		exit.setBorder(null);
		
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
	    try {
	      jbInit();
	    }
	    catch(Exception e) {
	      e.printStackTrace();
	    }
	  }

	private void jbInit() throws Exception  {
		this.setSize(new Dimension(997, 543));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("酒店管理系统——登录");
		
	    this.getContentPane().setLayout(null) ;//设置布局管理器为空布局
	    
	    sort.setBounds(new Rectangle(622, 356, 113, 43));
	    menu.setBounds(new Rectangle(622, 414, 113, 43));
	    desk.setBounds(new Rectangle(622, 472, 113, 43));
	    
	    day.setBounds(new Rectangle(748, 356, 113, 43));
	    month.setBounds(new Rectangle(748, 414, 113, 43));
	    year.setBounds(new Rectangle(748, 472, 113, 43));
	    
	    user.setBounds(new Rectangle(873, 356, 113, 43));
	    passWord.setBounds(new Rectangle(873, 414, 113, 43));
	    exit.setBounds(new Rectangle(873, 472, 113, 43));
	    
	    choice.setBounds(new Rectangle(85,312,35,23));
	    
	    jrb1.setBounds(new Rectangle(164,312,52,20));
	    jrb1.setOpaque(false);
	    jrb2.setBounds(new Rectangle(218,312,80,20));
	    jrb2.setOpaque(false);
	    
		timeArea.setCaretColor(Color.green);
		timeArea.setSelectedTextColor(Color.red);
		timeArea.setEditable(false);
	    timeArea.setBackground(Pool.color());
	    timeArea.setBounds(new Rectangle(0,345,213,171));
	    timeArea.setWrapStyleWord(true);
	    timeArea.setForeground(Color.red);
	    
	    textArea.scrollRectToVisible(new Rectangle(98, 148, 635, 150));
	    textArea.setBackground(Pool.color());
	    textArea.setEditable(false);
	    textArea.setLineWrap(true);
	    textArea.setWrapStyleWord(true);
	    final JScrollPane jScrollPane =new JScrollPane(textArea);
	    this.getContentPane().add(jScrollPane);
	    jScrollPane.setBorder(null);
	    jScrollPane.setBounds(new Rectangle(98,148,635,148));
	    textArea.setForeground(Color.magenta);
	    deskArea.setBackground(Pool.color());
	    deskArea.setEditable(false);
	    deskArea.setLineWrap(true);
	    deskArea.setWrapStyleWord(true);
	    deskArea.setForeground(Color.blue);
	    final JScrollPane jScrolldesk =new JScrollPane(deskArea);
	    this.getContentPane().add(jScrolldesk);
	    jScrolldesk.setBorder(null);
	    jScrolldesk.setBounds(new Rectangle(748,148,235,148));
	    
		
	    timer=new Timer();
		task=new TimerTask() {
			public void run() {
				time=null;
				time=new Time(Calendar.getInstance().getTimeInMillis());
				try {
					switch(cal.get(Calendar.DAY_OF_WEEK)){
					case 1:
						getDay("星期天",time);
						break;
					case 2:
						getDay("星期一",time);
						break;
					case 3:
						getDay("星期二",time);
						break;
					case 4:
						getDay("星期三",time);
						break;
					case 5:
						getDay("星期四",time);
						break;
					case 6:
						getDay("星期五",time);
						break;
					default:
						getDay("星期六",time);
						break;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
	    timer.schedule(task, 1000,10);
	    
	    showJradio.setBounds(new Rectangle(305,313,63,22));
	    showJradio.setEnabled(false);//设置不可编辑
	    
	    showPruductName.setBounds(new Rectangle(448,313,122,22));
	    showPruductName.setEnabled(false);//设置不可编辑
	    
	    showMonad.setBounds(new Rectangle(620,313,45,22));
	    showMonad.setEnabled(false);
	    
	    amount.setBounds(new Rectangle(700,313,45,22));
	    amount.setEnabled(false);
	    
	    openOpp.setBounds(new Rectangle(755,310,63,28));
	    endorseOpp.setBounds(new Rectangle(822,310,63,28));
	    
	    cancelOpp.setBounds(new Rectangle(888,310,63,28));
	    
	    payMoney.setBounds(new Rectangle(450,365,89,30));
	    payMoney.setEnabled(false);
	    //payMoney消费金额=数量*单价
	    
	    acceptMoney.setBounds(new Rectangle(450,402,89,30));

	    pocketMoney.setBounds(new Rectangle(450,475,89,30));
	    pocketMoney.setEnabled(false);
	    
	    checkOut.setBounds(new Rectangle(496,441,65,27));
	    
	    addArea();
	    
	    openOpp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkNotic("单据正在打印","开单提示");
				JFrame f=new JFrame();
				f.setSize(700,300);
				f.setResizable(false);
				f.setLocationRelativeTo(null);
				f.getContentPane().add(jScrollPane);
				f.setVisible(true);
			}
		});
	    
	    endorseOpp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkNotic("已签单","签单提示");
			}
		});
	    
	    cancelOpp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkNotic("正在取消单据", "取消提示");
			}
		});
	    
	    this.getContentPane().add(sort);
	    	sort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vigSer.action();
			}
		});
	    
	    
	    this.getContentPane().add(menu);
	    	menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vigPro.action();
			}
		});
	    
	    this.getContentPane().add(desk);
	       desk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deskNo.action();
				deskNo.addDesk.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							if(deskNo.deNo!=0){
								deskDAO.add(new Desk(Integer.parseInt(deskNo.deskNO.getText().trim())));
								deskNo.registerNotic("台号已开","添加提示");
								deskNo.area.setText("");
								deskNo.addArea();
								deskArea.setText("");
								addArea();
							}
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				});
				
				deskNo.deleteDesk.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							if(deskDAO.findById(Integer.parseInt(deskNo.deskNO.getText().trim()))!=null&&deskNo.deskNO.getText().matches("[0-9]{1,4}")){
								deskDAO.delete(deskNo.deskNO.getText().trim());
								deskNo.registerNotic("成功清除台号","添加提示");
								deskNo.area.setText("");
								deskNo.addArea();
								deskArea.setText("");
								addArea();
							}else{
								deskNo.registerNotic("此台未开","添加提示");
							}
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				});
				
			}
		});
	    
	    this.getContentPane().add(day);
	    this.getContentPane().add(month);
	    this.getContentPane().add(year);
	    
	    this.getContentPane().add(user);
	    this.getContentPane().add(passWord);
	    passWord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatePWD.action();
			}
		});
	    
	    this.getContentPane().add(exit);
	    exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	    
	    this.getContentPane().add(choice);
	    
	    this.getContentPane().add(jrb1);
	    this.getContentPane().add(jrb2);
	    
	    this.getContentPane().add(showJradio);
	    
	    this.getContentPane().add(showPruductName);
	    
	    this.getContentPane().add(showMonad);
	    
	    this.getContentPane().add(amount);
	    
	    this.getContentPane().add(openOpp);
	    
	    this.getContentPane().add(endorseOpp);
	    
	    this.getContentPane().add(cancelOpp);
	    
	    this.getContentPane().add(payMoney);
	    
	    this.getContentPane().add(acceptMoney);
	    
	    this.getContentPane().add(pocketMoney);
	    
	    this.getContentPane().add(checkOut);
	    
	    this.getContentPane().add(timeArea);
	    
	    
	    ImageIcon img=new ImageIcon(LoginFrame.class.getResource("/img/bg.png"));
	    JLabel hy=new JLabel(img);
	    this.getLayeredPane().add(hy,new Integer(Integer.MIN_VALUE));
	    hy.setBounds(0,0,img.getIconWidth(),img.getIconHeight());
	    //设置控件透明
	    ((JPanel)getContentPane()).setOpaque(false);
	}
	
	private void addArea() throws Exception {
		List<Desk> list=deskDAO.findAll();
	    int i=0;
	    for (Desk desk : list) {
			data=desk.toString().split("[\\[,\\]]");
			choice.add(data[1]);
			data[0]=String.valueOf(i+1);
			deskArea.append(data[0]+"\t"+data[1]+"         "+data[2]+"\n");
			i++;
		}
	}

	public void getDay(String date,Time time) throws PrinterException{
		/*timeArea.setText("今天是："+"\n"+"\n"
	    		+"                "+cal.get(Calendar.YEAR)+"年"+(cal.get(Calendar.MONTH)+1)+"月"+cal.get(Calendar.DATE)+"日"+"\n"+"\n"
	    		+"                      "+date+"\n"+"\n"
	    		+"                   "+time.toString()+"\n"+"\n"
	    		+"当前操作员："+"\n"+"\n"
	    		+"                        "+"00"
	    );*/
		timeArea.setText("今天是："+"\n"+"\n"
	    		+"                "+cal.get(Calendar.YEAR)+"年"+(cal.get(Calendar.MONTH)+1)+"月"+cal.get(Calendar.DATE)+"日"+"\n"+"\n"
	    		+"                      "+date+"\n"+"\n"
	    		+"                   "+time.toString()+"\n"+"\n"
	    		+"当前操作员："+"\n"
	    		+"                        "+"00"
	    );
	}
	
	public void action(){
		this.setVisible(true);
	} 
	
	public void checkNotic(String str1,String str2){
		Object[] message = {str1}; 
		JOptionPane.showConfirmDialog(null, message,str2,JOptionPane.DEFAULT_OPTION);
	}
	
	public static void main(String[] args) {
		ManagerSysFrame m=new ManagerSysFrame();
		m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m.action();
	}

}
