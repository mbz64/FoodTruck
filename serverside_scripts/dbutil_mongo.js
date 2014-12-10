var util = require('util');
var mongodb = require('mongodb');
var MongoClient = mongodb.MongoClient;

var mongo = {
    mongo: mongodb,
	db: null,
	dbname:null,
	collection:null,
}


mongo.init = function(name, server, port) {
	port= port || mongodb.Connection.DEFAULT_PORT;
	mongo.db = new mongodb.Db(name, new mongodb.Server(server, port, {}),
	{native_parser : true, auto_reconnect:true});
}

mongo.res = function (win, fail) {
	return function (err, res) {
		if ( err ) {
			util.log('mongo:err:' + JSON.stringify(err));
			fail && 'function' == typeof(fail) && fail(err);
		} else {
		    win && 'function' == typeof(win) && win(res);
		}
	}
}

 mongo.open = function (win, fail) {
 mongo.db.open(mongo.res(function() {
			win && win();
		}, fail))
}

mongo.coll = function(name, win, fail) {
  mongo.db.collection(name, mongo.res(win, fail));
}

exports.initDB = function(server, port, name, dbname) {
	// Connect to the db
	var connectionStr = "mongodb://"+server+":"+port+"/"+name+""
	util.debug(connectionStr)
	MongoClient.connect(connectionStr, function(err, db) {
			mongo.db = db;
			mongo.collection = mongo.db.collection(dbname);
			if(!err) {
				console.log("We are connected");
			}
	})

}

	

exports.getNearest = function(latitude, longitude, radius, onData, onEnd, onFail) {
	var query =  {
				  status : 'APPROVED',
				  geolocation : {	
					  $near: {
						 $geometry: {
							type: "Point" ,
							coordinates: [ longitude , latitude ]
						 },
						 $maxDistance: radius
					  }
				   }
				}

	var fields = {
				name:1,
				address :1,
				facilitytype:1
		}
		
	var stream = mongo.collection.find(query, fields).stream();
	stream.on("data", onData)
	stream.on("end", onEnd)
}

exports.getDetail = function(id, onData, onEnd) {
	var BSON = mongodb.BSONPure;
	var o_id = new BSON.ObjectID(id);
	var query = {
				  _id : o_id
				}
	var stream = mongo.collection.find(query).stream();
	stream.on("data", onData)
	stream.on("end", onEnd)
}


