"use strict";
// Removes the noise from the JUnit stack trace

let blacklist = [
  /at org.junit/,
  /at junit.framework/,
  /at sun.reflect/,
  /at java.lang.reflect/
]

let doctor = (chunk) => {
  var trace = chunk.split('\n')
  let newTrace = [];
  var match = null;
  trace.forEach(line => {
    if (line.match(/JUnit version/)) return;
    else if (line.match('Time')) return;
    else if (line.match('There was')) return;
    else if (line.match('FAILURES!!!')) process.exit(1);
    else {
      let rejectReason = null;
      blacklist.forEach(pattern => {
        let match = line.match(pattern)
        if (match) {
          rejectReason = match[0]
          return false
        }
      })
      if (rejectReason) {
        // console.log('reject', line, rejectReason);
      } else {
        newTrace.push(line);
      }
    }
  })
  trace = newTrace;
  trace = trace.join('\n')
  trace = trace.replace(RegExp(/ir.assignments\.[A-z]+\.[A-z]+\./g), '');
  return trace;
}

process.stdin.resume();
process.stdin.setEncoding('utf8');
process.stdin.on('data', chunk => process.stdout.write(doctor(chunk)))
