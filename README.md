# SpringBootCRUD


Push to github ->
    Execute webhook ->
        Trigger Jenkins pipeline on http://3.65.220.152/
        - build jar with project
        - build docker image
        - build docker 
        - push to dockerhub 
        - stop java process on server http://34.252.51.97/
        - transfer jar file to server http://34.252.51.97/
        - start jar on server http://34.252.51.97/

PostgresDB on 52.206.148.95 | DB = test | Table = Employees

Swagger on APP_SERVER:8082/swagger-ui.html
