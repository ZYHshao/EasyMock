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
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import tools.MockFile;
import tools.MockFileManager;
import tools.MockTool;
import tools.MockToolListener;
import ui.NewDialog.NewDialogListener;


public class MainFrame extends Frame implements MockToolListener,NewDialogListener{

	private static final long serialVersionUID = -6297718732599673960L;
	private MockTool mockTool = new MockTool();
	private Button startButton = null;
	private Panel mainPanel = null;
	private TextArea textArea = null;
	private List interfaceList = null;
	private ArrayList<MockFile> mockFiles = new ArrayList<>();
	private int currentSelectedMockFileIndex = -1;//默认设置为-1 表示没有选中
	
	
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
		MainFrame self= this;
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
				MockFile file = new MockFile(textArea.getText(), "get", mockFiles.get(self.currentSelectedMockFileIndex).getPath());
				mockTool.updateMockHandler(file);
				self.updateList();
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
		mockFiles.addAll(mockTool.getAllMockFile());
		interfaceList = new List();
		MainFrame self = this;
		interfaceList.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				self.currentSelectedMockFileIndex = interfaceList.getSelectedIndex();
				MockFile file = mockFiles.get(self.currentSelectedMockFileIndex);
				textArea.setText(file.getContent());
			}
		});
		interfaceList.setBounds(0, 0, 120, 540);
		mainPanel.add(interfaceList);
		Button newButton = new Button("新建API服务");
		newButton.setBounds(0, 540, 120, 40);
		Frame frame = this;
		newButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				NewDialog dialog = new NewDialog(frame);
				dialog.setListener(self);
				dialog.setVisible(true);
			}
		});
		mainPanel.add(newButton);
		for (int i = 0; i < mockFiles.size(); i++) {
			interfaceList.add(mockFiles.get(i).getPath());
		}
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

	@Override
	public void createMockAPi(String apiName) {
		// TODO Auto-generated method stub
		for (int i = 0; i < mockFiles.size(); i++) {
			if (mockFiles.get(i).getPath().compareTo(apiName)==0) {
				return;
			}
		}
		MockFile file = new MockFile("", "get", apiName);
		mockTool.addMockHnadler(file);
		if (currentSelectedMockFileIndex==-1) {
			currentSelectedMockFileIndex=0;
		}
		this.updateList();
	}
	
	
	private void updateList(){
		mockFiles.removeAll(mockFiles);
		mockFiles.addAll(mockTool.getAllMockFile());
		interfaceList.removeAll();
		for (int i = 0; i < mockFiles.size(); i++) {
			interfaceList.add(mockFiles.get(i).getPath());
		}
		if (currentSelectedMockFileIndex!=-1) {
			interfaceList.select(currentSelectedMockFileIndex);
		}
	}
	
	

}
