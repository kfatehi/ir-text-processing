"use strict";
const spawn = require('child_process').spawn;
const args = require('minimist')(process.argv);
const fs = require('fs');
const _ = require('lodash');
const ProgressBar = require('progress');

var bar = new ProgressBar('running [:bar] :percent', { total: args.size+2, width: 60 });
bar.tick();

var proc = spawn('bash', [__dirname+"/analyze.sh"], {
  env: {
    PARALLEL: args.parallel,
    MAX_SIZE: args.size,
    PREFIX: args.prefix
  }
})

var outPath = __dirname+'/data.js';

var out = fs.createWriteStream(outPath);
out.write('window.lineData = ');

var list = [];

proc.stdout.on('data', function(buf) {
  bar.tick();
  var trial = buf.toString().trim().split(/\s/)
  var datum = {
    x: parseInt(trial[0]),
    y: parseInt(trial[1]) * 1e-9 // nanosecond to second
  };
  list.push(datum);
})
.on('end', function() {
  bar.tick();
  finish();
})

process.on('SIGINT', function() {
  finish();
})

function finish() {
  out.write(JSON.stringify(_.sortBy(list, i => i.x), null, 4)+'\n', function() {
    out.end(function() {
      console.log(`wrote data to ${outPath}`);
      process.exit(0);
    })
  });
}
