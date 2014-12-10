var dbutil = require('./dbutil_mongo.js');
var util = require('util');


exports.init = function() {
	dbutil.initDB('localhost', 27017, 'testdb', 'test')
}

exports.getNearestFoodTrucks = function(latitude, longitude, radius, resultfn) {
	var itemList = [];
	dbutil.getNearest(latitude, 
			  longitude,
			  radius,
			  function(item) {
				  itemList.push(item)	
			  },
			  function() {
				resultfn(itemList)
			  }
			  
			 )
}


exports.getDetail = function(id, resultfn) {
	var itemJSON;
	dbutil.getDetail(id, 
		  function(item) {
			  itemJSON = item;
		  },
		  function() {
			 resultfn(itemJSON)
		  }
		  
		 )
}	