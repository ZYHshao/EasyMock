package tools;

import java.io.Serializable;

public class MockFile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String content;
	
	private String mothod;
	
	private String path;
	
	public MockFile(String content,String mothod ,String path) {
		// TODO Auto-generated constructor stub
		this.content = content;
		this.mothod = mothod;
		this.path = path;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMothod() {
		return mothod;
	}

	public void setMothod(String mothod) {
		this.mothod = mothod;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
