#!/bin/bash
for var in "$@"
do
    bc -l <<< $var/1080
done