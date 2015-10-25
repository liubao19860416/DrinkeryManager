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

import dao.VigatableseriesDAO;

import entity.Vigatableseries;
import executorsPool.Pool;

public class VigatableSeriesFrame extends JFrame{

	private JTextArea area;
	private TextField seriesNOMenu;
	private TextField seriesMenu;
	private TextField seriesNO;
	public int serNo;
	private TextField series;
	public String ser;
	private TextField menu;
	private JScrollPane jspane;
	private JButton deleteSeries;
	private JButton addSeries;
	public VigatableseriesDAO d;
	
	public VigatableSeriesFrame() {
		d=new VigatableseriesDAO();
		deleteSeries=new JButton("删除");
		addSeries=new JButton("添加");
		area=new JTextArea();
		jspane=new JScrollPane(area);
		seriesNOMenu=new TextField();
		seriesMenu =new TextField();
		seriesNO=new TextField();
		series =new TextField();
		menu=new TextField("            要添加的菜品");
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
		this.setTitle("菜系管理");
		this.getContentPane().setLayout(null) ;

		addarea();
		
		area.setEditable(false);
		area.setForeground(Color.blue);
		area.setWrapStyleWord(true);
		jspane.setBounds(new Rectangle(0, 30, 170, 150));
		
		seriesNOMenu.setBounds(new Rectangle(-1, -1, 85, 30));
		Font font=new Font("宋体",Font.BOLD,14);
		seriesNOMenu.setForeground(Color.red);
		seriesNOMenu.setFont(font);
		seriesNOMenu.setText("菜系编号");
		seriesNOMenu.setEnabled(false);
		
		seriesMenu.setBounds(new Rectangle(85, -1, 85, 30));
		seriesMenu.setForeground(Color.red);
		seriesMenu.setFont(font);
		seriesMenu.setText("菜系名称");
		seriesMenu.setEnabled(false);
		
		seriesNO.setBounds(new Rectangle(-1, 203, 85, 25));
		seriesNO.addTextListener(new TextListener() {
			public void textValueChanged(TextEvent e) {
				if(seriesNO.getText().matches("[0-9]{0,4}")){
					serNo=Integer.parseInt(seriesNO.getText());
				}
			}
		});
		
		
		series.setBounds(new Rectangle(85, 203, 85, 25));
		series.addTextListener(new TextListener() {
			public void textValueChanged(TextEvent e) {
				if(series.getText().trim().length()<40){
					ser=series.getText();
				}
			}
		});
		
		menu.setBounds(new Rectangle(-2, 178, 170, 25));
		menu.setEnabled(false);
		
		addSeries.setBounds(new Rectangle(0, 230, 85, 28));
		addSeries.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(serNo!=0&&ser!=null){
						d.add(new Vigatableseries(Integer.parseInt(seriesNO.getText()),series.getText()));
						registerNotic("成功添加菜品","添加提示");
						area.setText("");
						addarea();
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		deleteSeries.setBounds(new Rectangle(85, 230, 85, 28));
		deleteSeries.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(d.findById(series.getText())!=null){
						d.delete(series.getText().trim());
						registerNotic("成功清除台号","添加提示");
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
		this.getContentPane().add(seriesNOMenu);
		this.getContentPane().add(seriesMenu);
		this.getContentPane().add(seriesNO);
		this.getContentPane().add(series);
		this.getContentPane().add(menu);
		this.getContentPane().add(deleteSeries);
		this.getContentPane().add(addSeries);
	}
	public void addarea() throws Exception{
		List<Vigatableseries> l=d.findAll();
		String[] s=null;
		for (Vigatableseries footOpp : l) {
			s=footOpp.toString().split("[\\[,\\]]");
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
		VigatableSeriesFrame v=new VigatableSeriesFrame();
		v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		v.action();
	}

}
