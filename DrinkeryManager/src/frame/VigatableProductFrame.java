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
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import dao.FootOppDAO;
import entity.FootOpp;

public class VigatableProductFrame extends JFrame{

	private JTextArea area;
	private JTextArea monad;
	private TextField productNO;
	public String proNo;
	private TextField productName;
	public String proNa;
	private TextField monadNO;
	public String moNO;
	private TextField unitPrice;
	public int utPrice;
	private TextField serie;
	public int se;
	private JTextArea bg;
	
	private TextField menu;
	private JScrollPane jspane;
	private JButton deleteProduct;
	private JButton addProduct;
	public FootOppDAO d;
	
	public VigatableProductFrame() {
		bg=new JTextArea();
		serie=new TextField();
		d=new FootOppDAO();
		unitPrice=new TextField();
		monadNO=new TextField();
		deleteProduct=new JButton("删除");
		addProduct=new JButton("添加");
		area=new JTextArea();
		jspane=new JScrollPane(area);
		monad=new JTextArea();
		productNO=new TextField();
		productName =new TextField();
//		menu=new TextField("菜品编号"+"\t"+"菜品名称"+"\t       "+"单位"+"\t   "+"价格"+"\t"+"菜系编号");
		menu=new TextField("菜品编号\t\t"+"菜品名称\t          "+"单位\t"+"价格\t     "+"菜系编号");
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
	    try {
	      jbInit();
	    }
	    catch(Exception e) {
	      e.printStackTrace();
	    }
	}
	private void jbInit() throws Exception {
		this.setSize(500, 285);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setTitle("菜品管理");
		this.getContentPane().setLayout(null) ;
		addarea();
		
		bg.setBounds(new Rectangle(0, 30,500, 285));
		bg.setEnabled(false);
		
		area.setEditable(false);
		area.setForeground(Color.blue);
//		area.setBackground(Pool.color());
		area.setWrapStyleWord(true);
		jspane.setBounds(new Rectangle(0, 30,500, 150));
		
		Font font=new Font("宋体",Font.BOLD,14);
		monad.setBounds(new Rectangle(0, -10, 500, 40));
		monad.setForeground(Color.red);
		monad.setFont(font);
		monad.setText("\n"+"菜品编号"+"    "+"菜品名称"+"\t       "+"单位"+"\t   "+"价格"+"        "+"菜系编号"+"\n");
		monad.setEnabled(false);
		
		productNO.setBounds(new Rectangle(-1, 203, 85, 25));
		productNO.addTextListener(new TextListener() {
			public void textValueChanged(TextEvent e) {
				if(productNO.getText().trim().length()<40){
					proNo=productNO.getText();
				}
			}
		});
		
		
		productName.setBounds(new Rectangle(85, 203, 85, 25));
		productName.addTextListener(new TextListener() {
			public void textValueChanged(TextEvent e) {
				if(productName.getText().trim().length()<40){
					proNa=productName.getText();
				}
			}
		});
		
		monadNO.setBounds(new Rectangle(170, 203, 85, 25));
		monadNO.addTextListener(new TextListener() {
			public void textValueChanged(TextEvent e) {
				if(monadNO.getText().trim().length()<3){
					moNO=monadNO.getText();
				}
			}
		});
		
		unitPrice.setBounds(new Rectangle(255, 203, 85, 25));
		unitPrice.addTextListener(new TextListener() {
			public void textValueChanged(TextEvent e) {
				if(unitPrice.getText().matches("[0-9]{0,4}")){
					utPrice=Integer.parseInt(unitPrice.getText());
				}
			}
		});
		
		
		serie.setBounds(new Rectangle(340, 203, 85, 25));
		serie.addTextListener(new TextListener() {
			public void textValueChanged(TextEvent e) {
				if(serie.getText().matches("[0-9]{0,4}")){
					se=Integer.parseInt(serie.getText());
				}
			}
		});
		
		menu.setBounds(new Rectangle(-2, 178, 500, 25));
		menu.setEnabled(false);
		
		//添加功能
		addProduct.setBounds(new Rectangle(0, 230, 85, 28));
		addProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(proNo!=null&&proNa!=null&&moNO!=null&&utPrice!=0&&se!=0){
						d.add(new FootOpp(proNo,proNa,moNO,utPrice,se));
						registerNotic("成功添加菜品","添加提示");
						area.setText("");
						addarea();
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		//删除功能
		deleteProduct.setBounds(new Rectangle(85, 230, 85, 28));
		deleteProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(d.findById(productNO.getText())!=null){
						d.delete(productNO.getText().trim());
						registerNotic("成功删除菜品","添加提示");
						area.setText("");
						addarea();
					}else{
						registerNotic("没有此菜品","添加提示");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		this.getContentPane().add(jspane);
		this.getContentPane().add(productNO);
		this.getContentPane().add(productName);
		this.getContentPane().add(menu);
		this.getContentPane().add(deleteProduct);
		this.getContentPane().add(addProduct);
		this.getContentPane().add(monad);
		this.getContentPane().add(monadNO);
		this.getContentPane().add(unitPrice);
		this.getContentPane().add(serie);
		this.getContentPane().add(bg);
	}
	
	public void addarea() throws Exception{
		List<FootOpp> l=d.findAll();
		String[] s=null;
		for (FootOpp footOpp : l) {
			s=footOpp.toString().split("[\\[,\\]]");
			area.append(s[1]+"\t"+s[2]+"\t\t"+s[3]+"\t"+s[4]+"\t"+s[5]+"\n");
		}
	}
	
	public void registerNotic(String str1,String str2){
		Object[] message = {str1}; 
		JOptionPane.showConfirmDialog(null, message,str2,JOptionPane.DEFAULT_OPTION);
	}
	
	
	public void action(){
		this.setVisible(true);
	}
	
	public void checkNotic(String str1,String str2){
		Object[] message = {str1}; 
		JOptionPane.showConfirmDialog(null, message,str2,JOptionPane.DEFAULT_OPTION);
	}
	
	
	public static void main(String[] args) {
		VigatableProductFrame v=new VigatableProductFrame();
		v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		v.action();
	}

}
