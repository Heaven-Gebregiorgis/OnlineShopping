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
