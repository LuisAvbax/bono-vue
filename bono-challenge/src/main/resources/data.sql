
INSERT INTO customers (id, name) VALUES
                                     (1, 'Company A'),
                                     (2, 'Company B');

INSERT INTO facilities (id, name, country_code, company_id) VALUES
                                                                (1, 'Facility 1', 'US', 1),
                                                                (2, 'Facility 2', 'US', 1),
                                                                (3, 'Facility 3', 'CA', 2);

INSERT INTO carbon_footprints (id, input_amount, co2, facility_id) VALUES
                                                                       (1, 100, 200, 1),
                                                                       (2, 150, 300, 1),
                                                                       (3, 200, 500, 2),
                                                                       (4, 50, 100, 3);
