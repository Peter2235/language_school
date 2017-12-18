# language_school
Manager for language school lectures. Subject PA165.

Curl commands for Rest layer.

Get specific person by ID
curl -i -X GET  http://localhost:8080/pa165/rest/person/1

Get person by username
curl -i -X GET  http://localhost:8080/pa165/rest/person/username/Neo

Get person by lastname
curl -i -X GET  http://localhost:8080/pa165/rest/person/lastname/Anderson

Get all person
curl -i -X GET  http://localhost:8080/pa165/rest/person

Create new person
curl -X POST -i -H "Content-Type: application/json" --data '{"userName":"John123","firstName":"John","lastName":"Smith","isAdmin":"false"}' http://localhost:8080/pa165/rest/person/create

Update person
curl -X PUT -i -H "Content-Type: application/json" --data '{"id":"11","userName":"John","firstName":"John","lastName":"Smith"}' http://localhost:8080/pa165/rest/person/update

Delete a person by id
curl -i -X DELETE http://localhost:8080/pa165/rest/person/12