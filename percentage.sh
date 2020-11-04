#!/bin/zsh
i=0
for var in "$@"
do
    if [[ $((i%2)) -eq 0 ]]
    then
        bc -l <<< $var/2040
    else
        bc -l <<< $var/1078
    fi
    i=$i+1
done
