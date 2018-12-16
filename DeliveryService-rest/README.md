# REST how to test

#### Build and run
```
mvn clean install
cd DeliveryService-rest
mvn tomcat7:run
```
# Articles

#### Get all articles
```
curl -i -X GET http://localhost:8080/pa165/rest/articles
```
#### Get article by id
```
curl -i -X GET http://localhost:8080/pa165/rest/articles/1
```
#### Create new article
```
curl -X POST -i -H "Content-Type: application/json" --data '{"name":"Article","weight":"1"}' http://localhost:8080/pa165/rest/articles/create
```
#### Delete article
```
curl -i -X DELETE http://localhost:8080/pa165/rest/articles/1
```