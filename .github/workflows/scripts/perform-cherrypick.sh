#!/usr/bin/env bash

set -eou

environment=$1

git config --global user.email "mu-bot@toasttab.com"
git config --global user.name "Ministry of User Bot"

sha=$(git log -n 1 --pretty=format:"%H")
title=$(git log -n 1 --pretty=format:"%s")

branch_name="${sha}-${environment}"
echo "branch_name=${branch_name}" >> $GITHUB_ENV
echo "commit_title=${title}" >> $GITHUB_ENV
echo "commit_sha=${sha}" >> $GITHUB_ENV

git fetch
git checkout ${environment}

git checkout -b ${branch_name}
git push --force-with-lease origin ${branch_name}

git cherry-pick "${sha}"
