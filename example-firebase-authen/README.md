### Run Project . </br >
- `docker-compose -f docker-compose-kafka.yml up -d`. </br >
- `docker-compose -f docker-compose-mysql.yml up -d`. </br >
- `mvn clean install -DskipTests`. </br >
- `./run.sh`. </br >

**Don't forget to change path trustStore.p12 in file  `run.sh`**