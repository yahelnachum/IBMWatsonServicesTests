var express = require('express');
var app = express();
var port = process.env.PORT || 3000;
var path = require('path');

app.use(express.static(path.join(__dirname, '/public')));

app.get('/', function(req, res) {
	console.log('still good');
	res.send('hello world');
	res.end();
});

app.listen(port, function() {
	  console.log('App is listening on port ' + port);
});