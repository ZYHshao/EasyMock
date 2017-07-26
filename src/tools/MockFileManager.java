package tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MockFileManager {
	private String userPaht = System.getProperty("user.home");
	private int mocksFileCount = -1;
	private ArrayList<MockFile> mockFiles = new ArrayList<MockFile>();
	
	public MockFileManager() {
		// TODO Auto-generated constructor stub
	}

	public int getMocksFileCount() {
		if (mocksFileCount==-1) {
			mocksFileCount = 0;
			 try    
		        {     
		           ObjectInputStream in =new ObjectInputStream( new FileInputStream(getSettingFilePaht()));     
		           mocksFileCount = in.readInt();   //读取数据  
		           in.close();  
		        }catch(Exception e) {    
		            e.printStackTrace();  
		        }  
		}
		return mocksFileCount;
	}

	public void setMocksFileCount(int mocksFileCount) {
		this.mocksFileCount = mocksFileCount;
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(getSettingFilePaht()));
			out.writeInt(mocksFileCount);
			out.close();
		} catch (Exception e) {
			 e.printStackTrace(); 
		}
		
	}
	
	private String getSettingFilePaht(){
		File dir = new File(userPaht+"/EasyMock");
		File file = new File(userPaht+"/EasyMock","setting.easymock");   
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
        return userPaht+"/EasyMock/setting.easymock";
	}
	
	private String getMockFilePath(int index) {
		File dir = new File(userPaht+"/EasyMock");
		File file = new File(userPaht+"/EasyMock","mockFile"+index+".em");   
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
        return file.getPath();
	}

	public ArrayList<MockFile> getMockFiles() {
		if (this.getMocksFileCount()>mockFiles.size()) {
			//重新读取文件
			mockFiles.clear();
			for(int i=0;i<this.getMocksFileCount();i++){
				 try    
			        {     
			           ObjectInputStream in =new ObjectInputStream( new FileInputStream(getMockFilePath(i)));     
			           MockFile file = (MockFile) in.readObject();   //读取数据  
			           mockFiles.add(file);
			           in.close();  
			        }catch(Exception e) {    
			            e.printStackTrace();  
			        }  
			}

		}
		return mockFiles;
	}

	public void  addMockFile(MockFile mock) {
		//存数据
		try    
        {     
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(getMockFilePath(this.getMocksFileCount())));
			out.writeObject(mock);
			out.close();
   			//存数量
   			this.setMocksFileCount(this.getMocksFileCount()+1);
   			mockFiles.add(mock);
        }catch(Exception e) {    
            e.printStackTrace();  
        }  
		
	}





}
