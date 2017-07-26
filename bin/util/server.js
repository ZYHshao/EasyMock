var http = require('http'); 
var Mock = require('mockjs'); 
var express = require('express');
var app = express();

var server = app.listen(8081, function(){
	console.log("应用实例，访问地址为 http://127.0.0.1:8081");
})


