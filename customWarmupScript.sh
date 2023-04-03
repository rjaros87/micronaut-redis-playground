#!/usr/bin/env bash

echo "Custom micronaut-redis-playground warmup script"

echo -e "Put operation\n"
curl -XPUT -sS -d 'myvalue' 'http://localhost:8080/mykey'
sleep 1

echo -e "\n\nGet operation\n"
curl -XGET -sS 'http://localhost:8080/mykey'
sleep 1

echo -e "\n\nPublish operation - see micronaut logs\n"
curl -XPOST -sS -d 'mypublish' 'http://localhost:8080/'
sleep 1
echo -e "\n\nmicronaut-redis-playground warmup script done!"