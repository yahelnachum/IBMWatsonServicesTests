var express = require('express');
var app = express();
var port = process.env.PORT || 3000;
var path = require('path');
var fs = require('fs');
var extend = require('util')._extend;
var watson = require('watson-developer-cloud');

var config = {
		   version: '<service_version>',
		   url: '<service_url>',
		   username: '<username>',
		   password: '<password>'
};

app.use(express.static(path.join(__dirname, '/public')));

app.get('/', function(req, res) {
});

app.get('/test', function(req, res) {
	console.log('entering: app.get("/test")');
	var credentials = extend(config, getSpeechToTextCredentials());
	var authorization = watson.authorization(getSpeechToTextCredentials());
	authorization.getToken({url: credentials.url}, function(err, token) {
	      if (err) {
	         console.log('error:', err);
	         res.status(err.code);
	      }
	      console.log(token);
	      res.send(token);
	      res.end();
	});
	
	console.log('exiting : app.get("/test")');
});

function getSpeechToTextCredentials(){
	var serviceVariables = fs.readFileSync(path.join(__dirname, "serviceVariables.json")).toString();
	serviceVariables = JSON.parse(serviceVariables);
	return serviceVariables.speech_to_text.credentials;
}

app.listen(port, function() {
	  console.log('App is listening on port ' + port);
});