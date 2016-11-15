package act;

import act.View.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login extends JFrame implements ActionListener
{
	private JTextField username; // 用户名框
	private JTextField password; // 密码框
	private JButton login_bt; // 登录按钮
	private JButton exit_bt; // 退出按钮
	private Cursor handcur,costomcur;
	private JPanel mainpanel;
	private JPanel toppanel;
	private JPanel bottompanel;
	private String userName;
	private String passwd;
	
	public Login(){
		init();
		System.out.println(getMACAddress());
	}
	private String getOsName() {  
        String os = "";  
        os = System.getProperty("os.name");  
        return os;  
    }  
	private String getMACAddress() {  
        String address = "";  
        String os = getOsName();  
        Pattern pat = Pattern.compile("[^0-9]");
        
        if (os.startsWith("Windows")) {  
            try {  
                String command = "cmd.exe /c ipconfig /all";  
                Process p = Runtime.getRuntime().exec(command);  
                BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));  
                String line;  
                while ((line = br.readLine()) != null) {  
                    if (line.indexOf("Physical Address") > 0) {  
                        int index = line.indexOf(":");  
                        index += 2;  
                        address = line.substring(index);  
                        break;  
                    }else  if (line.indexOf("物理地址") > 0) {  
                        int index = line.indexOf(":");  
                        index += 2;  
                        address = line.substring(index);  
                        break;  
                    }  
                }  
                br.close();  
                address = address.trim().replace("-", "");
                Matcher m = pat.matcher(address);
                String result = m.replaceAll("");
                return result;
            } catch (IOException e) {  
            }  
        } else if (os.startsWith("Linux")) {  
            String command = "/bin/sh -c ifconfig -a";  
            Process p;  
            try {  
                p = Runtime.getRuntime().exec(command);  
                BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));  
                String line;  
                while ((line = br.readLine()) != null) {  
                    if (line.indexOf("HWaddr") > 0) {  
                        int index = line.indexOf("HWaddr") + "HWaddr".length();  
                        address = line.substring(index);  
                        break;  
                    }  
                }  
                br.close();  
            } catch (IOException e) {  
            }  
        }  
        address = address.trim().replace("-", "");  
        Matcher m = pat.matcher(address);
        String result = m.replaceAll("");
        return result;  
    }
	public void init(){
		System.out.println("now in login");
		this.setTitle("Login");
		this.setSize(300, 200);
		this.setLayout(new GridBagLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainpanel = new JPanel();
		mainpanel.setLayout(new BorderLayout());
		
		toppanel = new JPanel();
		mainpanel.add(toppanel,BorderLayout.CENTER);
		toppanel.setLayout(new GridLayout(5,2));
		
		bottompanel = new JPanel();
		mainpanel.add(bottompanel,BorderLayout.SOUTH);
		
		JLabel uname = new JLabel("User ID:");
		username = new JTextField();
		username.setText(getMACAddress());
		userName = getMACAddress();
		username.setEditable(false);
		JLabel pwd = new JLabel("Password: ");
		password = new JTextField();
		toppanel.add(uname);
		toppanel.add(username);
		toppanel.add(pwd);
		toppanel.add(password);
		
		login_bt = new JButton("Go");
		login_bt.addActionListener(this);
		exit_bt = new JButton("Exit");
		exit_bt.addActionListener(this);
		bottompanel.add(login_bt);
		bottompanel.add(exit_bt);
		this.add(mainpanel);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == exit_bt){
			System.out.println("Exit!!");
			System.exit(0);
		}
		if (e.getSource() == login_bt){
			passwd = password.getText();
			System.out.println(check(userName));
			System.out.println(passwd);
			if (!passwd.equals(check(userName))){
				return;
			}
			this.dispose();
			MainActivity mainActivity = new MainActivity();
			mainActivity.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			mainActivity.addWindowListener(new WindowAdapter(){
					@Override
					public void windowClosing(WindowEvent we){
						System.exit(0);
					}
			});
		}
	}
	private String check(String before){
		String after = "";
		int sum = 0;
		for (int i = 0; i < before.length(); i++){
			int x = Integer.parseInt(before.substring(i,i+1));
			sum += x;
		}
		return String.valueOf(sum);
	}
	public static void main(String[] args){
		Login lg = new Login();
		new centerShow(lg);
		lg.setVisible(true);
	}
}