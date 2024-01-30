INSERT INTO public.companies(
	id, address, average_rating, company_logo, description, name, wrk_hr_from, wrk_hr_to)
	VALUES (-1, 'UnitedHealth Group P.O. Box 1459 Minneapolis, MN 55440-1459', 4.3, 'https://1792exchange.com/wp-content/uploads/2021/08/UHG-logo-3.png', 'At UnitedHealthcare, our mission is to help people live healthier lives and make the health system work better for everyone. We dedicate ourselves to this every day for our members by being there for what matters in moments big and small.', 'UnitedHealth Group', '08:00:00', '15:00:00'),
           (-2, 'CVS Health One CVS Drive Woonsocket, Rhode Island 02895', 4.5, 'https://download.logo.wine/logo/CVS_Health/CVS_Health-Logo.wine.png', 'A purpose-driven company, we’re making healthier happen together with millions of patients, members and customers. We reach more people and do more to improve the health of their communities thanks to our local presence.', 'CVS Health', '08:00:00', '15:00:00'),
           (-3, '6555 State Hwy 161, Irving, TX, 75039', 4.8, 'https://download.logo.wine/logo/McKesson_Corporation/McKesson_Corporation-Logo.wine.png', 'We’re an impact-driven organization that touches virtually every aspect of health. Every decision we make is guided by our I 2 CARE and ILEAD principles.', 'McKesson', '08:00:00', '15:00:00'),
           (-4, 'PO Box 495918 Cincinnati, OH 45249', 4.4, 'https://purepng.com/public/uploads/large/purepng.com-cigna-logologobrand-logoiconslogos-25151994073572jqp.png', 'Cigna is a global health service company dedicated to helping people improve their health, wellbeing and peace of mind. Cigna has over 74,000 employees who serve more than 170 million customers throughout the world.', 'Cigna Global® Health Insurance', '08:00:00', '15:00:00'),
           (-5, 'Cardinal Health Media Relations 7000 Cardinal Place Dublin, OH 43017', 3.3, 'https://companieslogo.com/img/orig/CAH-8e1f7039.png?t=1681966045', 'Headquartered in Dublin, Ohio, Cardinal Health, Inc. (NYSE: CAH) is a distributor of pharmaceuticals, a global manufacturer and distributor of medical and laboratory products, and a provider of performance and data solutions for healthcare facilities.', 'Cardinal Health', '08:00:00', '15:00:00');

INSERT INTO public.medical_equipment(
	id, company_id, description, name, count)
	VALUES (-1, -1, 'Vitamin D', 'Vitamin D', 100),
           (-2, -1, 'Amoxicillin', 'Amoxicillin', 152),
           (-3, -1, 'Levothyroxine', 'Levothyroxine', 3),
           (-4, -1, 'Lisinopril', 'Lisinopril', 511),
           (-5, -1, 'Ibuprofen', 'Ibuprofen', 14),
           (-6, -2, 'Gabapentin', 'Gabapentin',512),
           (-7, -2, 'Benzonatate', 'Benzonatate',125),
           (-8, -2, 'Alprazolam', 'Alprazolam', 544),
           (-9, -2, 'Cyclobenzaprine', 'Cyclobenzaprine', 877),
           (-10, -3, 'Metformin', 'Metformin', 3),
           (-11, -3, 'Folic Acid', 'Folic Acid',2),
           (-12, -3, 'Trazodone', 'Trazodone', 12),
           (-13, -4, 'Sertraline', 'Sertraline', 15),
           (-14, -4, 'Omeprazole', 'Omeprazole', 11),
           (-15, -5, 'Meloxicam', 'Meloxicam', 111);

INSERT INTO public.country(
	id, name)
	VALUES (-1, 'USA'),
           (-2, 'Canada'),
           (-3, 'Mexico');

INSERT INTO public.city(
	id, name)
	VALUES (-1, 'Boston'),
           (-2, 'LA'),
           (-3, 'Miami'),
           (-4, 'Toronto'),
           (-5, 'Ottawa'),
           (-6, 'Winnipeg'),
           (-7, 'Mexico City'),
           (-8, 'Tijuana'),
           (-9, 'Ecatepec');

INSERT INTO public.country_cities(
	country_id, cities_id)
	VALUES (-1, -1),
           (-1, -2),
           (-1, -3),
           (-2, -4),
           (-2, -5),
           (-2, -6),
           (-3, -7),
           (-3, -8),
           (-3, -9);

INSERT INTO public.users(
	id, address, city, company_name, country, email, enabled, name, password, penal_points, phone, profession, role, surname, username)
	VALUES (-1, 'Address1', 'Boston', 'UnitedHealth Group', 'USA', 'filipdjokic2001@gmail.com', TRUE, 'user1', '$2a$10$ddmk2X3.TMlr4aWVlVzYd.gX/KKjY8TfPAb5sKb.FCWklcWTqhn1S', 1, 6113133130, 'profession1', 0, 'user1', 'user1'),
           (-2, 'Address2', 'Toronto', 'CVS Health', 'Canada', 'filipdjokic2001@gmail.com', TRUE, 'user2', '$2a$10$ddmk2X3.TMlr4aWVlVzYd.gX/KKjY8TfPAb5sKb.FCWklcWTqhn1S', 2, 6113133130, 'profession2', 1, 'user2', 'user2'),
           (-3, 'Address3', 'Ottawa', 'McKesson', 'Mexico', 'filipdjokic2001@gmail.com', TRUE, 'user3', '$2a$10$ddmk2X3.TMlr4aWVlVzYd.gX/KKjY8TfPAb5sKb.FCWklcWTqhn1S', 3, 6113133130, 'profession3', 2, 'user3', 'user3'),
           (-4, 'Address4', 'Ottawa', 'McKesson', 'Mexico', 'filipdjokic2001@gmail.com', TRUE, 'user4', '$2a$10$ddmk2X3.TMlr4aWVlVzYd.gX/KKjY8TfPAb5sKb.FCWklcWTqhn1S', 4, 6113133130, 'profession4', 2, 'user4', 'user4'),
           (-5, 'Address5', 'Ottawa', 'McKesson', 'Mexico', 'filipdjokic2001@gmail.com', TRUE, 'user5', '$2a$10$ddmk2X3.TMlr4aWVlVzYd.gX/KKjY8TfPAb5sKb.FCWklcWTqhn1S', 5, 6113133130, 'profession5', 2, 'user5', 'user5'),
           (-6, 'Address6', 'Ottawa', 'McKesson', 'Mexico', 'filipdjokic2001@gmail.com', TRUE, 'user6', '$2a$10$ddmk2X3.TMlr4aWVlVzYd.gX/KKjY8TfPAb5sKb.FCWklcWTqhn1S', 6, 6113133130, 'profession6', 2, 'user6', 'user6'),
           (-7, 'Address7', 'Ottawa', 'McKesson', 'Mexico', 'filipdjokic2001@gmail.com', TRUE, 'user7', '$2a$10$ddmk2X3.TMlr4aWVlVzYd.gX/KKjY8TfPAb5sKb.FCWklcWTqhn1S', 7, 6113133130, 'profession7', 2, 'user7', 'user7'),
           (-8, 'Address8', 'Ottawa', 'McKesson', 'Mexico', 'filipdjokic2001@gmail.com', TRUE, 'user8', '$2a$10$ddmk2X3.TMlr4aWVlVzYd.gX/KKjY8TfPAb5sKb.FCWklcWTqhn1S', 8, 6113133130, 'profession8', 2, 'user8', 'user8'),
           (-9, 'Address9', 'Ottawa', 'McKesson', 'Mexico', 'filipdjokic2001@gmail.com', TRUE, 'user9', '$2a$10$ddmk2X3.TMlr4aWVlVzYd.gX/KKjY8TfPAb5sKb.FCWklcWTqhn1S', 9, 6113133130, 'profession9', 2, 'user9', 'user9');

INSERT INTO public.appointments(
	id, admin_id, admin_last_name, admin_name, company_id, date, duration, taken)
	VALUES (-3, -3, 'Djokic', 'Filip', -1, '2023-12-22 12:00:00', 3, FALSE);

INSERT INTO public.companies_company_admins(
	company_profile_id, company_admins_id)
	VALUES (-1, -3),
           (-1, -4),
           (-2, -5),
           (-2, -6),
           (-3, -7),
           (-4, -8),
           (-5, -9);
