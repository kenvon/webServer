package webborwser;

import java.awt.*;
import java.awt.event.*;
import java.net.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.html.HTMLEditorKit;

public class Browser extends JFrame {
	public ToolBar toolBar;
	public BrowserPanel browserPane;
    public Browser() {
    	HTMLEditorKit kit = new HTMLEditorKit();
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
    	setSize(1366,675);
    	browserPane=new BrowserPanel();
    	browserPane.setEditorKit(kit);
    	toolBar=new ToolBar(this);
    	Container contentPane=getContentPane();
    	contentPane.add(toolBar,BorderLayout.NORTH);
    	contentPane.add(new JScrollPane(browserPane),BorderLayout.CENTER);
    	setVisible(true);
    }
    
    public static void main(String args[]){
    	Browser browser=new Browser();
    }  
}
