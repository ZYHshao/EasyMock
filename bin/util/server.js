var http = require('http'); 
var Mock = require('mockjs'); 
var express = require('express');
var app = express();

app.get('/mockget', function(req, res){
	var data = Mock.mock({
		"test":'@integer(60, 100)', 
		"test1":'@string(0, 5)', 
		"test2":'@string(5)'
	});
	response = {
		"data":data
	}
	res.end(JSON.stringify(response,null,4));
});

app.post('/mockpost', function(req, res){
	var data = Mock.mock({
		"name":req.body.name,
		"cartId":'@string', 
		"price":'@integer(0, 10000)', 
		"number":'@integer(0, 10)',
		"subModel":Mock.mock({
			"subModelId" : '@string(10)'
		})
	});
	console.log(data);

	response = {
		"data":data
	}
	fileManager.writeFileInDefaultPath(JSON.stringify(response));
	res.end(JSON.stringify(response));
});

var server = app.listen(8082, function(){
	var host = server.address().address
	var port = server.address().port

	console.log("应用实例，访问地址为 http://%s:%s", host, port)
})