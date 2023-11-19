INSERT INTO public.companies(
	id, address, average_rating, company_logo, description, name)
	VALUES (1, 'UnitedHealth Group P.O. Box 1459 Minneapolis, MN 55440-1459', 4.3, 'https://1792exchange.com/wp-content/uploads/2021/08/UHG-logo-3.png', 'At UnitedHealthcare, our mission is to help people live healthier lives and make the health system work better for everyone. We dedicate ourselves to this every day for our members by being there for what matters in moments big and small.', 'UnitedHealth Group'),
           (2, 'CVS Health One CVS Drive Woonsocket, Rhode Island 02895', 4.5, 'https://download.logo.wine/logo/CVS_Health/CVS_Health-Logo.wine.png', 'A purpose-driven company, we’re making healthier happen together with millions of patients, members and customers. We reach more people and do more to improve the health of their communities thanks to our local presence.', 'CVS Health'),
           (3, '6555 State Hwy 161, Irving, TX, 75039', 4.8, 'https://download.logo.wine/logo/McKesson_Corporation/McKesson_Corporation-Logo.wine.png', 'We’re an impact-driven organization that touches virtually every aspect of health. Every decision we make is guided by our I 2 CARE and ILEAD principles.', 'McKesson'),
           (4, 'PO Box 495918 Cincinnati, OH 45249', 4.4, 'https://purepng.com/public/uploads/large/purepng.com-cigna-logologobrand-logoiconslogos-25151994073572jqp.png', 'Cigna is a global health service company dedicated to helping people improve their health, wellbeing and peace of mind. Cigna has over 74,000 employees who serve more than 170 million customers throughout the world.', 'Cigna Global® Health Insurance'),
           (5, 'Cardinal Health Media Relations 7000 Cardinal Place Dublin, OH 43017', 3.3, 'https://companieslogo.com/img/orig/CAH-8e1f7039.png?t=1681966045', 'Headquartered in Dublin, Ohio, Cardinal Health, Inc. (NYSE: CAH) is a distributor of pharmaceuticals, a global manufacturer and distributor of medical and laboratory products, and a provider of performance and data solutions for healthcare facilities.', 'Cardinal Health');

INSERT INTO public.medical_equipment(
	id, company_id, description, name)
	VALUES (1, 1, 'Vitamin D', 'Vitamin D'),
           (2, 1, 'Amoxicillin', 'Amoxicillin'),
           (3, 1, 'Levothyroxine Sodium', 'Levothyroxine Sodium'),
           (4, 1, 'Lisinopril', 'Lisinopril'),
           (5, 1, 'Ibuprofen', 'Ibuprofen'),
           (6, 2, 'Gabapentin', 'Gabapentin'),
           (7, 2, 'Benzonatate', 'Benzonatate'),
           (8, 2, 'Alprazolam', 'Alprazolam'),
           (9, 2, 'Cyclobenzaprine', 'Cyclobenzaprine'),
           (10, 3, 'Metformin', 'Metformin'),
           (11, 3, 'Folic Acid', 'Folic Acid'),
           (12, 3, 'Trazodone', 'Trazodone'),
           (13, 4, 'Sertraline', 'Sertraline'),
           (14, 4, 'Omeprazole', 'Omeprazole'),
           (15, 5, 'Meloxicam', 'Meloxicam');

INSERT INTO public.country(
	id, name)
	VALUES (1, 'USA'),
           (2, 'Canada'),
           (3, 'Mexico');

INSERT INTO public.city(
	id, name)
	VALUES (1, 'Boston'),
           (2, 'LA'),
           (3, 'Miami'),
           (4, 'Toronto'),
           (5, 'Ottawa'),
           (6, 'Winnipeg'),
           (7, 'Mexico City'),
           (8, 'Tijuana'),
           (9, 'Ecatepec');

INSERT INTO public.country_cities(
	country_id, cities_id)
	VALUES (1, 1),
           (1, 2),
           (1, 3),
           (2, 4),
           (2, 5),
           (2, 6),
           (3, 7),
           (3, 8),
           (3, 9);

INSERT INTO public.users(
	id, address, city, company_name, country, email, enabled, name, password, penal_points, phone, profession, role, surname, username)
	VALUES (1, 'Address1', 'Boston', 'UnitedHealth Group', 'USA', 'filipdjokic2001@gmail.com', TRUE, 'user1', 'user1', 1, 0113133130, 'profession1', 0, 'user1', 'user1'),
           (2, 'Address2', 'Toronto', 'CVS Health', 'Canada', 'filipdjokic2001@gmail.com', TRUE, 'user2', 'user2', 2, 0113133130, 'profession2', 1, 'user2', 'user2'),
           (3, 'Address3', 'Ottawa', 'McKesson', 'Mexico', 'filipdjokic2001@gmail.com', TRUE, 'user3', 'user3', 3, 0113133130, 'profession3', 2, 'user3', 'user3');
