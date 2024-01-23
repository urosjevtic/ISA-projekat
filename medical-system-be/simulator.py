from confluent_kafka import Producer
import sys
import json
import time

def main():

    json_data = sys.stdin.readline()

    data = json.loads(json_data)

    kafka_config = {
        'bootstrap.servers': 'localhost:9092',  
    }

    producer = Producer(kafka_config)

    topic = 'simulator'

    for ind,tmp in enumerate(data):
        if(ind%10==0):          
            producer.produce(topic, key=None, value=json.dumps(tmp))
            producer.flush()
            time.sleep(5)

    sys.exit(0)


if __name__ == "__main__":
    main()