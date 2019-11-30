#!/bin/bash
for var in "$@"
do
    bc -l <<< $var/1776
done