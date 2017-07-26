package ui;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.Label;
import java.awt.List;
import java.awt.Panel;
import java.awt.Point;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import tools.MockFile;
import tools.MockFileManager;
import tools.MockTool;
import tools.MockToolListener;


public class MainFrame extends Frame implements MockToolListener{

	private static final long serialVersionUID = -6297718732599673960L;
	private MockTool mockTool = new MockTool();
	private Button startButton = null;
	private Panel mainPanel = null;
	private TextArea textArea = null;
	
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
		mockTool.setToolListener(this);
		this.setSize(new Dimension(400, 600));
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
		mainPanel = new Panel(null);
		this.add(mainPanel);
		startButton = new Button();
		startButton.setLabel("启动服务");
		startButton.setBounds(300, 530, 80, 40);
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (mockTool.getIsStarting()) {
					mockTool.closeServer();
				}else{
					mockTool.startServer();
				}
				startButton.setEnabled(false);
			}
		});
		Button saveBtn = new Button("SAVE");
		saveBtn.setBounds(230, 510, 60, 20);
		saveBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MockFile file = new MockFile(textArea.getText(), "get", "mock");
				mockTool.addMockHnadler(file);
			}
		});
		mainPanel.add(saveBtn);
		mainPanel.add(startButton);
		this.creatList();
		Label title = new Label("请编写您的Mock.js对象代码",Label.CENTER);
		title.setBounds(120, 10, 280, 40);
		title.setFont(new Font("", 1, 20));
		mainPanel.add(title);
		textArea = new TextArea();
		textArea.setBounds(140, 90, 230, 410);
		mainPanel.add(textArea);
		this.setVisible(true);
	}

	
	private void creatList() {
		List list = new List();
		list.setBounds(0, 0, 120, 540);
		mainPanel.add(list);
		Button newButton = new Button("新建API服务");
		newButton.setBounds(0, 540, 120, 40);
		mainPanel.add(newButton);
	}
	
	@Override
	public void taskFinish(Boolean success, String info ,int type) {
		switch (type) {
		case 1:
		{
			if (success) {
				startButton.setLabel("关闭服务");
			}
		}
			break;
		case 2:
		{
			if (success) {
				startButton.setLabel("开启服务");
			}
		}
			break;
		default:
			break;
		}
		startButton.setEnabled(true);
	}
	
	

}
