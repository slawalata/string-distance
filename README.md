# string-distance
## Overview
string-distance is a REST service which finds similaties from entry and keyword.
## Endpoint
### /similars 
`get /similars ` 
#### Description
find word similiarities in a long string based on keyword
#### Parameters
| Type        | Name           | Descripton  |Required |
| ------------- |:-------------:| :-----:|:-----:|
|BodyParameter| notebook_entry | requestParam |true|
|BodyParameter| keyword      |   requestParam |true|
#### Responses
| HTTP Code        | Description | Schema  
| ------------- |:-------------:| :-----:|
|200 | OK | Similarity |
|400 | Missing Request Body | No Content|
##### Consumes
application/json
##### Produces
application/json
##### Schema 
Similarity
| Name        | Description   | Schema  |
| ------------|:-------------:| :------:|
|keyword| search keyword | string |
|frequency| number of matching words | int |
|similar_words| word which has LevenshteinDistance 1| string[] |

#####  example

`curl -X GET localhost:8080/similars -H 'Content-Type: application/json' -d '{"notebook_entry" : "Word One three","keyword":"Word"}' `

`{"keyword":"Word","frequency":1,"similar_words":["Words","Wor","word"]}`

# Build
* `$ mvn package && java -jar target/string-distance-0.0.1-SNAPSHOT.jar`
