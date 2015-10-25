package frame;

import java.awt.AWTEvent;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.TextField;
import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import dao.FootOppDAO;
import entity.FootOpp;
import executorsPool.Pool;

public class ClientFrame extends JFrame{

	public TextField pruductName;
	
	public TextField count;
	
	/** 确认按钮 */
	public JButton OKOpp;
	
	/** 调用菜单数据 */
	private FootOppDAO foot;
	
	private List<FootOpp> list;
	
	private JTextArea textArea;
	
	private JScrollPane spane;
	
	public ClientFrame() {
		textArea=new JTextArea();
		spane=new JScrollPane(textArea);
		foot=new FootOppDAO();
		pruductName=new TextField();
		count=new TextField();
		OKOpp=new JButton("确认");
	    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
	    try {
	      jbInit();
	    }
	    catch(Exception e) {
	      e.printStackTrace();
	    }
	  }

	private void jbInit() throws Exception  {
		this.setSize(new Dimension(529, 307));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("客户酒店点菜菜单");
		
	    this.getContentPane().setLayout(null) ;//设置布局管理器为空布局
	    pruductName.setBounds(new Rectangle(125, 242, 100, 24));
	    count.setBounds(new Rectangle(310,242,76,24));
	    OKOpp.setBounds(new Rectangle(400,240,76,30));
	    textArea.setBackground(Pool.color());
	    textArea.setEditable(false);
	    spane.setBounds(new Rectangle(2,53,525,170));
	    this.add(spane);
	    this.getContentPane().add(OKOpp);
	    takeMenu();
	    
	    ImageIcon img=new ImageIcon(LoginFrame.class.getResource("client.png"));
	    JLabel hy=new JLabel(img);
	    this.getLayeredPane().add(hy,new Integer(Integer.MIN_VALUE));
	    this.getContentPane().add(pruductName);
	    this.getContentPane().add(count);
	    
	    hy.setBounds(0,0,img.getIconWidth(),img.getIconHeight());
	    //设置控件透明
	    ((JPanel)getContentPane()).setOpaque(false);
	}
	
	public void takeMenu() throws Exception{
	    list =foot.findAll();
	    String[] s=null;
	    Integer a=1;
		for (FootOpp footOpp : list) {
			s=footOpp.toString().split("[\\[,\\]]");
			System.out.println(Arrays.toString(s));
		    s[0]=a.toString();
		    textArea.append(s[0]+"\t"+"       "+s[1]+"             "+s[2]+"\t"+s[3]+"\t"+s[4]+"\n");
		    a++;
		}
	}
	
	public void checkNotic(String str1,String str2){
		Object[] message = {str1}; 
		JOptionPane.showConfirmDialog(null, message,str2,JOptionPane.DEFAULT_OPTION);
	}
	
	public void action(){
		this.setVisible(true);
	} 
	
	
	public static void main(String[] args){
		ClientFrame l=new ClientFrame();
		l.action();
	}

}
