1.Run command -> (docker-compose up -d) -> in docker-build folder.
2.Eureka will run at 8761 and api gateway will run at 8080.
3.Make sure all six services(nes-order,nes-user,nes-product,nes-delivery,nes-api-gateway,nes-payment)
 are register on eureka.
4. Import following links in postman to import collection:

nes-delivery-> https://www.getpostman.com/collections/9d7a9558699a6358614a
nes-order -> https://www.getpostman.com/collections/0ec6a9a2b5fe3104e59c
nes-payment -> https://www.getpostman.com/collections/44ea10c137d3a00b3146
nes-user ->   https://www.getpostman.com/collections/7844bb36b99e83b7dfd5
nes-product-> https://www.getpostman.com/collections/b5fd58e8abf6f301e4c3


5. Make sure to add header Authorization token in all the requests:
Authorization axcafdagaddff12345

6. Make sure to use following keywords while testing APIs:

UpdateCartType-> INC(for increase),DEC(for decrease)
PAYMENT_TYPE -> CARD,COD
DELIVERYSTATE-> PACKAGING_IN_PROGRESS, PREPARED, SHIPPED, OUT_FOR_DELIVERY, DELIVERED, UNDELIVERED
USER_TYPE - CUSTOMER,ADMIN,DELIVERY_MANAGER

7. 7. Use swagger interface for microservices definitions:
http://localhost:8081/swagger-ui.html. (nes-delivery)

In the similar way you can access other microservices' swagger.
