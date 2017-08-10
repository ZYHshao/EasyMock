package tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class MockTool {
	
	private String systemPath = System.getProperty("user.dir");
	private String userPaht = System.getProperty("user.home");

	private Boolean isStarting = false;
	
	private MockToolListener toolListener = null;
	
	private MockFileManager fileManager = new MockFileManager();
	
	public int STATT_SERVER = 1;
	public int CLOSE_SERVER = 2;

	
	public MockTool() {
		super();
		
	}
	
	
	
    /*
     * 	开启node服务
     * */
	public void startServer() {
		String formatString =null;
		this.closeServer();
		try {
			formatString = readBaseCommandFile();
			System.out.println(formatString);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (formatString!=null) {
			String mockString = "";
			ArrayList<MockFile> files = fileManager.getMockFiles();
			for(int i=0;i<files.size();i++){
				MockTask task = new MockTask(files.get(i).getContent(), files.get(i).getMothod(), files.get(i).getPath());
				mockString = mockString+task.getTaskString();
			}
			String finishStirg =  formatString + mockString;
			new Thread(){
				public void run() {
					String path = writeJSFile(finishStirg, "mocjdata.js");
					String command = "/usr/local/bin/node "+path;
					isStarting = runTask(command);
					if (toolListener!=null) {
						toolListener.taskFinish(isStarting, "服务启动成功：Server running at http://127.0.0.1:8888",1);
					}
				};
			}.start();
		}

	}
	
	/*
	 * 关闭node服务
	 * */
	public void closeServer() {
		String command = "pkill node";
		if (!isStarting) {
			return;
		}
		new Thread(){
			public void run() {
				if (runTask(command)) {
					isStarting = false;
				}
				if (toolListener!=null) {
					toolListener.taskFinish(!isStarting, "服务已关闭!",2);
				}
			};
		}.start();
	}
	
	private Boolean runTask(String command) {
		try{
			Process process = null;
			process = Runtime.getRuntime().exec(command);
			BufferedReader stdout = new BufferedReader(new InputStreamReader(process.getInputStream()));  
			String line = null;  
			line = stdout.readLine();
			if  (line!=null) {  
				System.out.println(line);
			}  
			stdout.close();
			return true;
        } catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
	}
	
	private String writeJSFile(String content,String name) {
		File dir = new File(userPaht+"/EasyMock");
        File file = new File(userPaht+"/EasyMock","mocjdata.js");   
        //如果文件夹不存在则创建   
        if  (!dir.exists())     
        {     
          file.mkdirs();   
        }  
        if (!file.exists()) {
            try {  
                file.createNewFile(); // 创建文件  
            } catch (IOException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
		}
        // 向文件写入内容(输出流)   
        byte bt[] = new byte[1024];  
        bt = content.getBytes();  
        try {  
            FileOutputStream in = new FileOutputStream(file);  
            try {  
                in.write(bt, 0, bt.length);  
                in.close();  
                // boolean success=true;  
                // System.out.println("写入文件成功");  
            } catch (IOException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
        } catch (FileNotFoundException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        return userPaht+"/EasyMock/mocjdata.js";
	}
	
	private String readBaseCommandFile() throws URISyntaxException {
		String str="";

	    File file=new File(systemPath+"/src/util/server.js");
	    try {
	        FileInputStream in=new FileInputStream(file);
	        // size  为字串的长度 ，这里一次性读完

	        int size=in.available();

	        byte[] buffer=new byte[size];

	        in.read(buffer);

	        in.close();

	        str=new String(buffer,"UTF-8");

	    } catch (IOException e) {

	        // TODO Auto-generated catch block
	    	e.printStackTrace();
	        return null;

	    }

	    return str;
	}
	
	
	
	public Boolean getIsStarting() {
		return isStarting;
	}

	
	


	public MockToolListener getToolListener() {
		return toolListener;
	}



	public void setToolListener(MockToolListener toolListener) {
		this.toolListener = toolListener;
	}

	public void addMockHnadler(MockFile file) {
		fileManager.addMockFile(file);
	}
	
	public ArrayList<MockFile> getAllMockFile() {
		return fileManager.getMockFiles();
	}

	
}
