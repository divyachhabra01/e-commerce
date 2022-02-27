# E-Commerce Application
## Description
This is a small e-commerce application developed using Spring Boot that provides functionality of adding a product , customer and placing the order

#### NOTE: You don't need to create DB tables ,it will create itself once you build the Project.
## Apis
It contains the following apis:

* GET "/api/v1/order/orders": This API returns list of all orders.
====================================================

  curl --location --request GET 'http://localhost:8080/api/v1/order/orders'
  
=============================================================

* POST "/api/v1/customer": This API is used to add customer.

===================================================

  curl --location --request POST 'http://localhost:8080/api/v1/customer' \
  --header 'Accept: application/json' \
  --header 'Content-Type: application/json' \
  --data-raw '{
  "name":"Ferne Roman",
  "country":"China",
  "address":"1370 Ridge Oak Pass"
  }'
  
========================================================
* POST "/api/v1/product": This API is used to add product

===========================================================

  curl --location --request POST 'http://localhost:8080/api/v1/product' \
  --header 'Accept: application/json' \
  --header 'Content-Type: application/json' \
  --data-raw '
  {
  "title":"Two-toed ",
  "description":"Aspagarus-White"
  }'
  
========================================================

* POST "/api/v1/order": This API is used to place order

=========================================================

curl --location --request POST 'http://localhost:8080/api/v1/order?customerId=1&status=COMPLETED' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json' \
--data-raw '[1,2]'

=================================================================

* PATCH "/api/v1/order/{id}": This API is used to update status of order

==================================================================

curl --location --request PATCH 'http://localhost:8080/api/v1/order/1' \
--header 'Content-Type: application/json' \
--data-raw '{
"status":"DELIVERED"
}'


### Technology and Library  Used
1. Spring Boot 
2. MySQL
3. Hibernate 
4. Project Lombok
5. Model Mapper





