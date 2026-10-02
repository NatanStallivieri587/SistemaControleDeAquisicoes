#!/bin/bash
git restore --source=HEAD src/Main.java
git add src/Main.java
git rebase --continue
