USE Heaven_Gebregiorgis;
SELECT COUNT(id), country
FROM Addresses
GROUP BY Country
HAVING COUNT(id) > 3;


SELECT Users.last_name, COUNT(Orders.id) AS NumberOfOrders
FROM (Orders
INNER JOIN Users ON Orders.User_id = Users.id)
GROUP BY last_name
HAVING COUNT(Orders.id) > 10;


Select Users.last_name, Users.first_name, SUM(Orders.total_price) AS TotalOrder
FROM (Orders INNER JOIN Users ON Orders.User_id = Users.id)
GROUP BY last_name
HAVING SUM(Orders.total_price)>1000; 


SELECT Cart.id, Users.id,  MAX(Carts.total_price)
FROM (Carts JOIN Users on Carts.user_id = Users.id)
HAVING MAX(Carts.total_price)>10000;