// Author Jillian Hennessy
// Author Yahel Nachum
// Website http://jh-yn-final-cs4241.herokuapp.com/

var express = require('express');
var path = require('path');
var app = express();
var port = process.env.PORT || 3000;
var bodyParser = require("body-parser");
var pg = require('pg');
var fs = require('fs');
var bcrypt = require('bcryptjs');

app.use(bodyParser.urlencoded({extended: false}));

app.use(express.static(path.join(__dirname, '/public')));

app.get('/', function(req, res) {
});

app.get('/myAudio', function(req, res) {
  console.log('getting audio file');
  var audioData = fs.readFileSync(path.join(__dirname, '/public/short_audio.flac'));
  res.send(new Buffer(audioData, 'binary'));
  console.log('sent audio file');
});

app.listen(port, function() {
  console.log('App is listening on port ' + port);
});