var express = require('express')
var util = require('util')
var api_handler = require('./foodtruck_apihandlers.js');

var app = express()
api_handler.init()

app.listen(2040)
util.debug('Server running');
app.get('/list', function(req, res) {
			var latitude = req.query.latitude;
			var longitude = req.query.longitude;
			var radius = req.query.radius;
			var flatitude = parseFloat(latitude)
			var flongitude = parseFloat(longitude)
			var fradius = parseFloat(radius)
			
		    api_handler.getNearestFoodTrucks(flatitude,
											 flongitude,
											 fradius,
											 function(itemList) {
												  if (itemList != null) {
													 sendjson(res, itemList)
												  } else {
													sendjson(res, [])
												  }
											 })
		}
)

app.get('/getDetail', function(req, res) {
	var id = req.query.id;
	api_handler.getDetail(id, function(result) {
			if (result != null) {
				sendjson(res, result)
			}else {
				sendjson(res, "")
			}
		})
}
)

sendjson = function(res, obj) {
		res.writeHead(200, {'Content-Type' : 'text/json', });
		var objStr = JSON.stringify(obj);
		util.debug("SENDJSON");
		res.end(objStr);
	}