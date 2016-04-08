var express = require('express');
var app = express();
var port = process.env.PORT || 3000;
var path = require('path');
var fs = require('fs');

app.use(express.static(path.join(__dirname, '/public')));

app.get('/', function(req, res) {
	console.log('entering: app.get("/")');
	console.log('exiting : app.get("/")');
});

app.get('/test', function(req, res) {
	console.log('entering: app.get("/test")');
	console.log(fs.readFileSync(path.join(__dirname, "serviceVariables.json")).toString());
	res.end();
	console.log('exiting : app.get("/test")');
});

app.listen(port, function() {
	  console.log('App is listening on port ' + port);
});