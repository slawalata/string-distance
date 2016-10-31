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
|422 | Missing mandatory parameters | No Content|

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

####  Example

`curl -X GET localhost:8080/similars -H 'Content-Type: application/json' -d '{"notebook_entry" : "Word One three","keyword":"Word"}' `

`{"keyword":"Word","frequency":1,"similar_words":["Words","Wor","word"]}`

## Build
* `$ mvn package && java -jar target/string-distance-0.0.1-SNAPSHOT.jar`

## Acceptance Criteria

### API
* accessible endpoint
* HTTP 400 error for missing request body
* HTTP 422 for missing mandatory parameters

### Uses Cases
|exact word (ld =0) | similar (ld =1)| not similar (ld >1) |testCase|
|------------|-------------| ------|------------|
|false|false| true|testSearchSimilarsWordWithNoSimilarities|
|false|true| true|testSearchSimilarsWordWith1SimilaritiesAnd1NotSimiliar|
|true|true| true|testSearchSimilarsWordWith1ExactAnd3Similarities|

## Future Discussion
* Common best practice is to use `paramRequest` instead of `body request` while search/find using HTTP get. 
But String length (notebook_entry) of `paramRequest` would be limited in case searching in a very long string. 
`paramRequest` enables users to call directly service endpoint in a web browser.
* `StringDistance`is a wrapper of Levenshtein-Distance implementation. 
It is a spring bean that can be replaced with other implementations.
* Currently `similar` means 2 words have `levenshtein distance == 1`. 
Currently it is hard coded. It can be move to configuration (file or bean) so it can be changed dinamically.
* `SearchControllerIT` tests can be reduced only to test 1 or 2 test cases. 
It loads SpringContainers to test Rest Serialization/Deserialization based on given inputs which can takes time when test cases grow. 
For Rest JSON Serialization/Deserialization test, we can use `@JSONTest`. 
For `SearchController` test, we can use basic unit test.