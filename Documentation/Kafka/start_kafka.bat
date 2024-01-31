@echo off

REM Delete contents of zookeeper-data and kafka-logs folders
rmdir /s /q zookeeper-data
rmdir /s /q kafka-logs

REM Start Zookeeper
start "Zookeeper" cmd /c bin\windows\zookeeper-server-start.bat config\zookeeper.properties

REM Wait for a moment to ensure Zookeeper has started before starting Kafka
timeout /nobreak /t 5

REM Start Kafka Broker
start "Kafka Broker" cmd /c bin\windows\kafka-server-start.bat config\server.properties

