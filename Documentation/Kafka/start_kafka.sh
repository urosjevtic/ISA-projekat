#!/bin/bash

rm -rf zookeeper-data/*
rm -rf kafka-logs/*

# Start Zookeeper
gnome-terminal --tab --title="Zookeeper" --command="bin/zookeeper-server-start.sh config/zookeeper.properties"

# Wait for a moment to ensure Zookeeper has started before starting Kafka
sleep 5

# Start Kafka Broker
gnome-terminal --tab --title="Kafka Broker" --command="bin/kafka-server-start.sh config/server.properties"


