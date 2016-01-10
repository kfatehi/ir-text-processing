#!/bin/bash
N=4

echo "compiling project..."
make compile
echo "done, starting trials..."
echo "running in batches of $N"
echo -e "words\ttime"

(
for size in $(seq 1 10); do 
   ((i=i%N)); ((i++==0)) && wait
   node test/support/analyzer/index.js --size $size -- java -classpath _build ir.assignments.one.d.PalindromeFrequencyCounter &
done
)
sleep 2 # wait for final echos before prompt reappears
