
# Kafka instrukcije

1. Skinuti sa kafka sajta binaru verziju kafke ( ja sam koristio kafka 3.6.1 -> binary downloads -> scala 2.12 , starije verzije mozda imaju drugaciji nacin pokretanja )

2. Ekstraktovati kafku u folder, u kafka folderu treba napraviti dva nova foldera zookeeper-data i kafka-logs

3. U kafka/config/zookeeper.properties editovati dataDir da pokazuje na zookeeper-data folder npr:

```
dataDir = C:/kafka/zookeeper-data
```

4. U kafka/config/server.properties editovati log.dirs da pokazuje na kafka-logs folder npr:

```
log.dirs = C:/kafka/kafka-logs
```

5. Prije pokretanja skripte staviti skriptu za pokretanje u kafka folder, pokrenuti skriptu **start_kafka.bat** za windows ili **start_kafka.sh**  za linux

6. Ako treba, napraviti novi topic (na njega se subscrujbujes), otvoriti novi terminal (ime topica mozete staviti po zelji):
    - za linux: 
        ```
        $ bin/kafka-topics.sh --bootstrap-server localhost:9092 --create --topic [ime topica] 
	    ```
    - za windows: 
        ```
        ./bin/windows/kafka-topics.bat --bootstrap-server localhost:9092 --create --topic [ime topica]
        ```


napomena:

>	- VAZNO: kafku treba smjestiti negdje gdje je path mali tipa C:/kafka, ima nekih problema oko pokretanja kafke kada je path duzi nez zasto
>	- kad koristis kafku kao consumer ili producer konektovati se na localhost:9092
>	- kad korisits kafku kao consumer ili producer moras slati (subscribovati se) na topic koji postoji  
>	- da vidis topice koji su napravljeni moras pozvati: 
    za linux: $ bin/kafka-topics.sh --bootstrap-server localhost:9092 --list
        
	za windows: ./bin/windows/kafka-topics.bat --bootstrap-server localhost:9092 --list
    
>	- brisanje topica ide slicno kao i za kreiranje samo zamjeniti flag --create sa --delete
>	- ako ima error-a pri pokretanju zookeepera ili kafke izbrisati sadrzaj u folderima /zookeeper-data i /kafka-logs pa pokrenuti ponovo

