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