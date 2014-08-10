package webborwser;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;
import javax.swing.text.DefaultStyledDocument;

public class ToolBar extends JPanel implements HyperlinkListener,ActionListener {
	private JLabel jLabel;
//	private JPanel jPanel1;
//	private JPanel jpanel2;
//	private JPanel jPanel3;
//	private Container container;
	
	private JButton goBack;
	private JButton goForward;
	private JButton goRefresh;
	private JButton goHome;
	private Browser webBrowser;
	private ImageIcon background;
	public JTextField TextField;
	public ToolBar(Browser browser) {
		Border bevelBorder = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
		this.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(5,5,5,5), bevelBorder));
		BoxLayout box=new BoxLayout(this,BoxLayout.X_AXIS);
		this.setLayout(box);
		webBrowser= browser;
		webBrowser.browserPane.addHyperlinkListener(this);
		
		TextField = new JTextField("http://localhost:8080/index.html");
		//设置输入框的字体
		TextField.setFont(new Font("宋体",Font.BOLD,20));
		TextField.addActionListener(this);//设置时间监听
		background = new ImageIcon("button_down.png");
		goBack = new JButton(new ImageIcon("D:/DevelopSoftware/WorkSpaces/MyWebborwser/src/TEST/fanhui1.png"));
		goBack.addActionListener(this);
		
		goForward = new JButton(new ImageIcon("D:/DevelopSoftware/WorkSpaces/MyWebborwser/src/TEST/forward2.png"));
		goForward.addActionListener(this);
		
		goRefresh=new JButton(new ImageIcon("D:/DevelopSoftware/WorkSpaces/MyWebborwser/src/TEST/refresh.png"));
		goRefresh.addActionListener(this);
		
		goHome=new JButton(new ImageIcon("D:/DevelopSoftware/WorkSpaces/MyWebborwser/src/TEST/home2.png"));
		goHome.addActionListener(this);
		jLabel =new JLabel("  地址:  ");
		add(jLabel);
		add(TextField);
		add(goHome);
		add(Box.createHorizontalStrut(5));
		add(goBack);
		add(Box.createHorizontalStrut(5));
		add(goForward);
		add(Box.createHorizontalStrut(5));
		add(goRefresh);
		add(Box.createHorizontalStrut(5));
//		add(TextField);
		
//		jPanel1.add(jLabel);
//		jPanel1.add(TextField);
//		jpanel2.add(goHome);
//		jpanel2.add(goBack);
//		jpanel2.add(goForward);
//		jpanel2.add(goRefresh);
//		jPanel3.add(jPanel1,BorderLayout.NORTH);
//		jPanel3.add(jpanel2,BorderLayout.SOUTH);
//		
//		container.add(jPanel3,BorderLayout.SOUTH);
	}

	public void hyperlinkUpdate(HyperlinkEvent e) {
		if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
			URL url = e.getURL();
			webBrowser.browserPane.goURL(url);
			TextField.setText(url.toString());
		}
		
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==TextField){
			URL url;
			try {
				url = new URL(TextField.getText());
				webBrowser.browserPane.goURL(url);
			} catch (MalformedURLException ex) {
				JOptionPane.showMessageDialog(null, "Not find the url:"+TextField.getText());
			}
		}
		
		if(e.getSource()==goBack){
			URL url = webBrowser.browserPane.goBack();
			TextField.setText(url.toString());
		}
		
		if(e.getSource()==goForward){
			URL url = webBrowser.browserPane.goForward();
			TextField.setText(url.toString());
		}
		if(e.getSource()==goRefresh){
			URL url =webBrowser.browserPane.history.get(webBrowser.browserPane.historyIndex);
			try {
				webBrowser.browserPane.setPage(url);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			TextField.setText(url.toString());
		}
		if(e.getSource()==goHome){
			//webBrowser.browserPane.history.clear();
			webBrowser.browserPane.goURL(webBrowser.browserPane.home);
			TextField.setText(webBrowser.browserPane.home.toString());
		}
	}
}