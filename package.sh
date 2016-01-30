#!/bin/bash

cd bin
echo "Manifest-Version: 1.0
Main-Class: $2

" > MANIFEST.mf
jar cfm $1 MANIFEST.mf *
mv $1 ..
cd ..
