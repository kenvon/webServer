package webborwser;

import java.util.*;
import java.net.*;
import java.io.*;
import javax.swing.*;

public class BrowserPanel extends JEditorPane {
	public List<URL> history = new ArrayList<URL>();
	public int historyIndex;
	public URL home;//主页

	public BrowserPanel() {
		try {
			home=new URL("http://localhost:8080/index.html");
			history.add(home);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		try {
			this.setPage(home);
			System.out.println("11212");
		} catch (IOException e) {
			e.printStackTrace();
		}
		setEditable(false);
	}
	
	public void goURL(URL url) {
		if(displayPage(url)){
			history.add(url);
			historyIndex = history.size() - 1;
		}
	}
	public URL goForward() {
		historyIndex++;
		if (historyIndex >= history.size()) {
			historyIndex = history.size() - 1;
		}
		URL url = (URL) history.get(historyIndex);
		displayPage(url);
		return url;
	}
	public URL goBack() {
		if (historyIndex <=0)
			historyIndex = 0;
		else{historyIndex--;}
		URL url = (URL) history.get(historyIndex);
		displayPage(url);
		return url;
	}

	public boolean displayPage(URL url) {
		try {
			setPage(url);
			return true;
		} catch (IOException ioException) {
			JOptionPane.showMessageDialog(null, "Not find the url:"+url,"提示信息",JOptionPane.WARNING_MESSAGE);
			return false;
		}
	}

}