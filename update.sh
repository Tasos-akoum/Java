#!/bin/sh
echo "Type the commit"
read change
echo 'init github commit'
git add *
git commit -am "new commit:, $change"
git push 
