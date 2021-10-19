#!/bin/sh
echo "Type the commit"
read change
ech 'init github commit'
git add *
git commit -am "new commit:, $change"
git push 
