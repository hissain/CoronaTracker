# 1. Server Architecture #

## 1.1.0 Database Tests

### Database user
Please create phpmyadmin new user `(id: admin, passwd: admin)` with all previlages. Or you can change the user/password as your own from SpringBoot app/ `application.properties`

``` # MySQL
spring.datasource.url=jdbc:mysql://192.168.64.2:3306/ctracker
spring.datasource.username=admin
spring.datasource.password=admin

logging.level.org.springframework=info
logging.level.org.springframework.jdbc=DEBUG
```

### Dummy data insertion

You can download the .sql files exported [here](https://github.com/hissain/CoronaTracker/blob/dev/architecture/ctracker.sql). Running this to sql will create above schemes and insert some dummy data for testing. You can configure in you localhost database server.

## 1.1. Server Table schemes (users, events)

### users table
<img src="https://github.com/hissain/CoronaTracker/blob/dev/architecture/Screenshots/Server/db_users.png" alt="Users table" width="650"/>

### events table
<img src="https://github.com/hissain/CoronaTracker/blob/dev/architecture/Screenshots/Server/db_events.png" alt="Events table" width="600"/>


## 1.2. Postman Samples

### 1.2.1. user registration [REST]
<img src="https://github.com/hissain/CoronaTracker/blob/dev/architecture/Screenshots/Server/pm_users.png" alt="Users API" width="500"/>

### 1.2.2. event push [REST]
<img src="https://github.com/hissain/CoronaTracker/blob/dev/architecture/Screenshots/Server/pm_events.png" alt="Events API" width="500"/>

### 1.2.3. candidate fetch [POST]
<img src="https://github.com/hissain/CoronaTracker/blob/dev/architecture/Screenshots/Server/pm_fetch.png" alt="Fetch API" width="600"/>

### Postman Tests
You can download postman API collection from [here](https://github.com/hissain/CoronaTracker/blob/dev/architecture/ctracker.postman_collection.json) and import to your Postman for testing with your locally setup localhost servers.

# 2. Android Architecture #

Android Java/Kotlin app

# 3. iOS Architecture #

iOS Swift app
