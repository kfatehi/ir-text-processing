"use strict";
const spawn = require('child_process').spawn;
const args = require('minimist')(process.argv);

var proc = spawn('bash', [__dirname+"/analyze.sh"], {
  env: {
    PARALLEL: args.parallel,
    MAX_SIZE: args.size,
    PREFIX: args.prefix
  }
})

var list = [];

proc.stdout.on('data', function(buf) {
  var trial = buf.toString().trim().split(/\s/)
  var datum = {
    size: parseInt(trial[0]),
    nanoseconds: parseInt(trial[1])
  };
  console.log(datum);
  list.push(datum);
})

proc.stdout.on('end', function() {
  console.log('TODO compute results using list');
});
