#!/bin/bash
N=${PARALLEL:-4}
SIZE=${START_SIZE:-1}
INCR_BY=${INCR_BY:-1}

echo "compiling project..." 1>&2
make compile 1>&2
echo "running in batches of $N and printing CSV data to stdout" 1>&2

echo "lineData = ["

(
while true; do
   ((i=i%N)); ((i++==0)) && wait
   node test/support/analyzer/index.js --size "$SIZE" -- $PREFIX &
   SIZE=$((SIZE+INCR_BY))
done
)

function finish {
wait
echo "]"
}
trap finish EXIT
