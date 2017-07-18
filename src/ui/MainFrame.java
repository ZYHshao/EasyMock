package ui;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.Panel;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;


public class MainFrame extends Frame {

	private static final long serialVersionUID = -6297718732599673960L;

	public MainFrame() throws HeadlessException {
		super();
	}

	public MainFrame(GraphicsConfiguration gc) {
		super(gc);
		// TODO Auto-generated constructor stub
		
	}

	public MainFrame(String title) throws HeadlessException {
		super(title);
		// TODO Auto-generated constructor stub
	}

	public MainFrame(String title, GraphicsConfiguration gc) {
		super(title, gc);
		// TODO Auto-generated constructor stub
	}
	
	public void setDisplay() {
		this.setSize(new Dimension(300, 600));
		this.setResizable(false);
		this.setLocation(new Point(400, 150));
		this.setTitle("EasyMock-您的数据模拟管家!");
		this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		//panel
		Panel panel = new Panel(null);
		this.add(panel);
		Button button = new Button();
		button.setLabel("启动服务");
		button.setBounds(90, 400, 120, 50);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try{
			    String path = System.getProperty("user.dir");
				String command = "/usr/local/bin/node "+path+"/src/util/server.js";
				Process process = null;
				
				process = Runtime.getRuntime().exec(command);
				
				BufferedReader stdout = new BufferedReader(new InputStreamReader(  
	                    process.getInputStream()));  
	            String line = null;  
	            line = stdout.readLine();
	            while (line!=null) {  
	                System.out.println(line); 
	                line = stdout.readLine();
	            }  
	            stdout.close(); 
	            } catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel.add(button);
		this.setVisible(true);
	}
	
	

}
