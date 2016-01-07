"use strict";
// Removes the noise from the JUnit stack trace

let doctor = (chunk) => {
  var trace = chunk.split('\n')
  let newTrace = [];
  trace.forEach(line => {
    if (line.match(/JUnit version/)) return;

    else if (line.match('Time')) return;

    else if (line.match('There was')) return;

    else if (line.match('FAILURES!!!')) process.exit(1);

    else if (! line.match(/at [org.junit|junit.framework|sun.reflect]/) ) newTrace.push(line);
  })
  trace = newTrace;
  trace = trace.join('\n')
  trace = trace.replace(RegExp(/ir.assignments\.[A-z]+\.[A-z]+\./g), '');
  return trace;
}

process.stdin.resume();
process.stdin.setEncoding('utf8');
process.stdin.on('data', chunk => process.stdout.write(doctor(chunk)))
