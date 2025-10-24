insert into users (id, first_name, last_name, email, password, phone, roles) values
    ('12345', 'Chinedu', 'Okonkwo', 'chinedu.okonkwo@gmail.com', '$2a$10$wHjp1ZW9O6YYyuCcS2uFmOwvDLj5f4.3.G3D3TJrPLbFi8y5VG5aG', '08012345678', 'CUSTOMER'),
    ('12346', 'Adunni', 'Alao', 'adunni.alao@gmail.com', '$2a$10$wHjp1ZW9O6YYyuCcS2uFmOwvDLj5f4.3.G3D3TJrPLbFi8y5VG5aG', '08098765432', 'CUSTOMER'),
    ('12347', 'Emeka', 'Eze', 'emeka.eze@company.com', '$2a$10$wHjp1ZW9O6YYyuCcS2uFmOwvDLj5f4.3.G3D3TJrPLbFi8y5VG5aG', '08056565656', 'ORGANIZATION');

insert into organizations (id, name, code, address, contact_email, contact_phone, verified, created_at) values
    ('org123', 'Oluwaloni Services Ltd', 'OSL001', '156 Awolowo Way, Ikoyi, Lagos', 'info@oluwaloniservices.com', '0123456789', true, CURRENT_TIMESTAMP),
    ('org124', 'Chibuzor & Sons Nig. Ltd', 'CSN001', '42 Okpara Avenue, Surulere, Lagos', 'info@chibuzorsons.com', '0134567890', true, CURRENT_TIMESTAMP);

insert into offerings (id, name, description, estimated_wait_time, duration, organization_id, created_at) values
    ('off123', 'Bank Deposit Service', 'Cash deposit service for all banks', 30, 15, 'org123', CURRENT_TIMESTAMP),
    ('off124', 'Bill Payment Service', 'Utility bill payment service', 45, 20, 'org123', CURRENT_TIMESTAMP),
    ('off125', 'Document Printing', 'Document printing and binding service', 60, 30, 'org124', CURRENT_TIMESTAMP);

insert into queues (id, name, description, organization_id, created_at) values
    ('q123', 'Main Service Queue', 'General service queue for walk-in customers', 'org123', CURRENT_TIMESTAMP),
    ('q124', 'Priority Queue', 'Priority service queue for premium customers', 'org123', CURRENT_TIMESTAMP),
    ('q125', 'Printing Queue', 'Queue for printing services', 'org124', CURRENT_TIMESTAMP);

insert into appointments (id, user_id, offering_id, queue_id, appointment_date, status, queue_position, created_at) values
    ('apt100', '12345', 'off123', 'q123', CURRENT_TIMESTAMP, 'CONFIRMED', 1, CURRENT_TIMESTAMP),
    ('apt101', '12346', 'off124', 'q124', CURRENT_TIMESTAMP, 'CONFIRMED', 2, CURRENT_TIMESTAMP),
    ('apt102', '12345', 'off125', 'q125', CURRENT_TIMESTAMP, 'PENDING', 3, CURRENT_TIMESTAMP);