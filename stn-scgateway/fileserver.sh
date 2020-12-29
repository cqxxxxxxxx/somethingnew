#!/usr/bin/env sh
mkfile -n 2g ./2gfile.txt
python -m http.server 9001 --bind 0.0.0.0