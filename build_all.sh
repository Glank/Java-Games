#!/bin/bash

for dir in `find . -maxdepth 1 -mindepth 1 -type d -not -path '*/\.*' -printf '%f\n'`
do
    cd $dir 
    make clean package doc || exit 1
    cd ..
done
