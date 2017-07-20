package tools;

public class MockTask {

	private String formatString = "app.%s(\'/%s\',function(req,res){var data=Mock.mock(%s);response={\"data\":data};res.end(JSON.stringify(response,null,4));});";
	private String taskString = null;
	public String getTaskString() {
		return taskString;
	}
	public void setTaskString(String taskString) {
		this.taskString = taskString;
	}
	public MockTask() {
		// TODO Auto-generated constructor stub
		taskString =  String.format(formatString,"get","mock","{\"test\":\'@integer(60, 100)\', \"test1\":\'@string(0, 5)\', \"test2\":\'@string(5)\'}");
	}

}
