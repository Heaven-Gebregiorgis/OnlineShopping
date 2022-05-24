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

SELECT Users.last_name, MAX(Carts.total_items)
FROM (Carts INNER JOIN Users ON Carts.User_id = Users.id)
GROUP BY last_name
HAVING MAX(Carts.total_items) > 2;

-----------------------------------------------------------------------------

Use Heaven_Gebregiorgis;
Delete from Users where id = 3 AND 9;
Delete from Products where status = 'Sold out';
Delete from Vendors where vendor_name = 'Moyennis All store';

---------------------------------------------------------------------------------------

USE Heaven_Gebregiorgis;

Insert into Settings (location, advertising_preference, language, camera_permission)
values
('On', 'EveryDay', 'English', 'Do not allow'),
('On', 'EveryDay', 'English', 'Allow'),
('On', 'Once a week', 'English', 'Do not Allow'),
('Off', 'EveryDay', 'English', 'Do not Allow'),
('On', 'Do not send', 'English', 'Do not Allow'),
('Off', 'EveryDay', 'English', 'Allow'),
('Off', 'Once a week', 'English', 'Do not Allow'),
('Off', 'Do not send', 'English', 'Do not Allow');

Insert into Communication_Preferences (app_notification, text, email)
values
('Allow', 'Do not send', 'Yes'),
('Allow', 'Send', 'Yes'),
('Allow', 'Do not send', 'No'),
('Allow', 'Send', 'No'),
('Do not allow', 'Do not send', 'Yes'),
('Do not allow', 'Send', 'Yes'),
('Do not allow', 'Do not send', 'No'),
('Do not allow', 'Send', 'No');

Insert into Users (last_name, first_name, birthday, email, registered_on, communication_preference_id, setting_id)
values 
('Frank', 'Rob', '1993-09-26', '123@gmail.com', '2022-05-01', 3, 2),
('Smith', 'Bob', '1986-06-26', "1233@gmail.com", '2022-05-01', 3, 1),
('Johnson', 'Claire', '2001-03-14', '1234@gmail.com', '2022-05-01', 2, 2),
('Douglas', 'Mark', '1988-09-30', '1235@gmail.com', '2022-05-01', 1, 2),
('Killip', 'Suzan', '1972-02-08', '1236@gmail.com', '2022-05-01', 4, 4);


Insert into Addresses (house_number, street, apartment_number, city, state, postal_code, country)
values
(1234, '1st street', null, 'Seattle', 'WA', '98001', 'USA'),
(1256, '2nd street', null, 'Sacramento', 'CA', '97001', 'USA'),
(0001, 'MLK street', null, 'Dallas', 'TX', '21001', 'USA'),
(786, 'some street', null, 'Istanbul', null, '98001', 'Turkey'),
(56432, 'swiss Ave', null, 'Asmara', null, '27Z87', 'Eritrea');


Insert into Categories (department)
Values
('Home'),
('Electronics'),
('Shoes'),
('Shoes'),
('clothes');

Insert into Vendors (vendor_name, contact_name, email, phone, address_id)
values
('Lucky store', 'Samuel King', 'email1@email.com', '800-000-0002', null),
('Nike.com', null, 'email@email.com', '800-000-0001', null),
('Hamilton store', null, 'email2@email.com', '800-011-0001', null),
('Moyennis All store', 'Mark Steven', 'email110@email.com', '800-000-0011', null),
('Apple store', 'Brandon', 'email1123@email.com', '888-000-0002', null);



Insert into Products (product_name, brand, size, status, category_id, vendor_id)
values
('Slippers', 'Nike', 7, 'In stock', 3, 7),
('High heel shoe', 'Lucky', 10, 'In stock', 4, 6),
('Mixer', 'Hamilton Beach', '4 qt', 'In stock', 1, 8),
('Mac laptop', 'MacBook Air', '11 inch', 'Sold out', 2, 10),
('Bedsheets', 'Moyennis', 'Queen size', 'In stock', 1, 8),
('Jeans', 'Lucky', 'S', 'Sold out', 5, 6);


---------------------------------------------------------------------------------------

Use Heaven_Gebregiorgis;
SELECT u.last_name, p.product_name, ct.department, v.vendor_name, m.id, d.delivery_status, o.confirmation_number,
 w.id, t.transactionStatus, f.star_rate FROM Feedbacks f 
 JOIN Users u on u.id = f.user_id
 JOIN Products p on p.id = f.product_id
 JOIN Categories ct on ct.id = p.category_id
 JOIN Vendors v on v.id = p.vendor_id
 JOIN Messages m on m.user_id = u.id
 JOIN Deliveries d on d.user_id = u.id
 JOIN Orders o on o.user_id = u.id
 JOIN Wallets w on w.user_id = u.id
 JOIN Transactions t on t.order_id = o.id 
 WHERE f.star_rate = 5;
 
 ---------------------------------------------------------------------------------------
 
 USE Heaven_Gebregiorgis;

Update Products set status = 'Sold out' where id = 17;
Update Users set email = '123@hotmail.com' where id = 13;
Update Addresses set house_number = 5678 where id = 1;
Update Vendors set vendor_name = 'Nike store' where id =4;
Update Users set setting_id = 3 where id = 14;

