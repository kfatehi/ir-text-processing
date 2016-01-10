"use strict";
const spawn = require('child_process').spawn;
const fs = require('fs');
const chance = require('chance').Chance();
const args = require('minimist')(process.argv);

function init() {
  for (let i=0; i<process.argv.length; i++) {
    let arg = process.argv[i];
    if (arg === "--") {
      let cmd = process.argv[i+1];
      let cmdArgs = process.argv.slice(i+2, process.argv.length);
      let size = parseInt(args.size);

      analyze(size, cmd, cmdArgs, function(diff) {
        console.log('%d\t%d', size, diff[0] * 1e9 + diff[1]);
      });
      break
    }
  }
}

function analyze(size, cmd, args, done) {
  let path = '/tmp/'+Math.random().toString(24).substring(2);
  let stream = fs.createWriteStream(path)
  stream.write(generateInputData(size))
  stream.end(function() {
    var time = process.hrtime()
    let proc = spawn(cmd, args.concat([path]));
    proc.on('close', function() {
      done(process.hrtime(time))
      fs.unlink(path)
    })
  });
}

function generateInputData(i) {
  return chance.sentence({ words: i })
}

init();
