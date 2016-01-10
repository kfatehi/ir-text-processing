#!/bin/bash
N=${PARALLEL:-4}
MAX_SIZE=${MAX_SIZE:-10}

echo "compiling project..." 1>&2
make compile 1>&2
echo "done, starting trials..." 1>&2
echo "running in batches of $N up to max size of $MAX_SIZE" 1>&2
echo -e "words\ttime" 1>&2

(
for size in $(seq 1 $MAX_SIZE); do 
   ((i=i%N)); ((i++==0)) && wait
   node test/support/analyzer/index.js --size $size -- $PREFIX &
done
)
sleep 2 # wait for final echos before prompt reappears
