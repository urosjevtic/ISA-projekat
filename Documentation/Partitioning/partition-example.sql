CREATE TABLE public.appointments_partitioned (
    id bigint NOT NULL,
    admin_id bigint,
    admin_last_name character varying(255),
    admin_name character varying(255),
    company_id bigint,
    date timestamp(6) without time zone,
    duration integer NOT NULL,
    taken boolean NOT NULL
) PARTITION BY RANGE (date);



CREATE TABLE appointments_partitioned_before_2023 PARTITION OF appointments_partitioned
    FOR VALUES FROM (MINVALUE) TO ('2023-01-01');


CREATE TABLE appointments_partitioned_after_2023 PARTITION OF appointments_partitioned
    FOR VALUES FROM ('2023-01-01') TO (MAXVALUE);


INSERT INTO public.appointments_partitioned(
	id, admin_id, admin_last_name, admin_name, company_id, date, duration, taken)
	VALUES (-3, -3, 'Djokic', 'Filip', -1, '2023-12-22 12:00:00', 3, FALSE),
	(-2, -3, 'Djokic', 'Filip', -1, '2022-12-22 12:00:00', 3, FALSE);
	
SELECT * FROM appointments_partitioned_before_2023
SELECT * FROM appointments_partitioned_after_2023
