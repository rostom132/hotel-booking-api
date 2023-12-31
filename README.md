# Hotel Booking API


## API Document
Access this link to get API document (Include success testcases)
- [API Document](https://documenter.getpostman.com/view/11864108/2s9Ykhh4VD)

## Technical stack
- Java 11
- SpringBoot
- Postgre
- Elasticsearch
- Docker

## User Manual
### Deplendencies
- docker

### Steps
To start the project, run
```
./start.sh
```
To stop the project, run
```
./stop.sh
```

## Design
DB Design
![ER Diagram](./images/ER_diagram.png)

## Test
### Stress Test
Configuration
![Configuration](./images/stress_test_config.png)

Result
![Configuration](./images/stress_test_result.png)

## TODO LIST
- Write unit test (Target min 85%)
- Improve authen and author (currently using basic auth)
- Using cache (Redis, ...) to improve performance (reduce traffic for DB)
- Develop Log service to handle logs, for further analysing
- Improve hotel search (search with long, lat by using PostGIS)
- Improve booking service (use message queue with microservices, blocking queue, LMAX, ...) to ensure data consistency when booking (Currently using optimistic lock)
- Apply rate limiter for booking API
- Build CI/CD
