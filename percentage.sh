#!/bin/sh
i=0
for var in "$@"
do
    if [ $((i%2)) -eq 0 ]
    then
        bc -l <<< $var/1776
    else
        bc -l <<< $var/1080
    fi
    i=$i+1
done
