Server part of a package GUI that can contain Book, Perishable, and Electronic objects. Menu loads from, saves into a JSON file each time a request is made from client for new package to be created/removed or be delivered.

You may test this in isolation and without the use of client code using curl commands. My client code which also uses curl commands to get/update the list is also available on Github.


Curl Commands:

Notes:
a)-i is used to display header information.
b)-X not sure what it does.
c)this is required for all POST messages, so we put it in every command.
    According to Dr. Fraser, this gives a custom header. "Content-Type: application/json"
d)use -d to provide the data in post messages.



1)To run the ping command.
curl -i -X GET localhost:8080/ping


2)Get a list of all the packages.
curl -i -H "Content-Type: application/json" -X GET localhost:8080/listAll

3)Add new package to packageList.
Book:
curl -i -H "Content-Type:application/json" -X POST -d "{\"name\":\"Test1\",\"notes\":\"Test1\",\"priceInDollar\":23.75,\"weight\":0.372,\"isDelivered\":false,\"expectedDeliveryDate\":\"2022-06-18 10:40\",\"authorName\":\"James\"}" localhost:8080/addBook

Perishable:
curl -i -H "Content-Type:application/json" -X POST -d"{\"expiryDate\":\"2022-08-03 02:30\",\"name\":\"PerishableTest\",\"type\":\"Perishable\",\"notes\":\"\",\"priceInDollar\":3.0,\"weight\":3.0,\"isDelivered\":false,\"expectedDeliveryDate\":\"2022-08-04 01:30\"}"  localhost:8080/addPerishable

Electronic:
curl -i -H "Content-Type:application/json" -X POST -d"{\"environmentalHandlingFee\":7.0,\"name\":\"ElectronicTest\",\"type\":\"Electronic\",\"notes\":\"\",\"priceInDollar\":3.0,\"weight\":3.0,\"isDelivered\":false,\"expectedDeliveryDate\":\"2022-08-18 03:30\"}"  localhost:8080/addElectronic


4)Remove a package from packageList(go from 0 to n-1).
curl -i -H "Content-Type:application/json" -X POST localhost:8080/removePackage/2

5)List Overdue packages.
curl -i -H "Content-Type: application/json" -X GET localhost:8080/listOverduePackage

6)List Upcoming packages.
curl -i -H "Content-Type: application/json" -X GET localhost:8080/listUpcomingPackage


7) Mark a package as delivered.
curl -i -H "Content-Type:application/json" -X POST localhost:8080/markPackageAsDelivered/1


8)Save the result of packageList in JSON file.
curl -i -H "Content-Type: application/json" -X GET localhost:8080/exit
