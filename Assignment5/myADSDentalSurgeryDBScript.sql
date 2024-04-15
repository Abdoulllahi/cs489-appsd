CREATE DATABASE IF NOT EXISTS myADSDentalSurgeryDB;
USE myADSDentalSurgeryDB;

CREATE TABLE Dentist (
    dentist_id INT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    contact_phone VARCHAR(20),
    email VARCHAR(100),
    specialization VARCHAR(100)
);

CREATE TABLE Patient (
    patient_id INT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    contact_phone VARCHAR(20),
    email VARCHAR(100),
    mailing_address VARCHAR(255),
    date_of_birth DATE
);

CREATE TABLE Surgery (
    surgery_id INT PRIMARY KEY,
    name VARCHAR(100),
    location VARCHAR(255),
    address VARCHAR(255),
    telephone VARCHAR(20)
);

CREATE TABLE Bill (
    bill_id INT PRIMARY KEY,
    patient_id INT,
    amount DECIMAL(10, 2),
    paid BOOLEAN,
    FOREIGN KEY (patient_id) REFERENCES Patient(patient_id)
);

CREATE TABLE Appointment (
    appointment_id INT PRIMARY KEY,
    dentist_id INT,
    patient_id INT,
    date_time DATETIME,
    surgery_id INT,
    FOREIGN KEY (dentist_id) REFERENCES Dentist(dentist_id),
    FOREIGN KEY (patient_id) REFERENCES Patient(patient_id),
    FOREIGN KEY (surgery_id) REFERENCES Surgery(surgery_id)
);

-- Insert data into Dentist table
INSERT INTO Dentist (dentist_id, first_name, last_name, contact_phone, email, specialization)
VALUES
    (1, 'John', 'Doe', '123-456-7890', 'john.doe@example.com', 'General Dentistry'),
    (2, 'Jane', 'Smith', '987-654-3210', 'jane.smith@example.com', 'Orthodontics'),
    (3, 'Michael', 'Brown', '555-111-2222', 'michael.brown@example.com', 'Pediatric Dentistry'),
    (4, 'Emily', 'Davis', '555-333-4444', 'emily.davis@example.com', 'Endodontics');

-- Insert data into Patient table
INSERT INTO Patient (patient_id, first_name, last_name, contact_phone, email, mailing_address, date_of_birth)
VALUES
    (1, 'Alice', 'Johnson', '555-123-4567', 'alice.johnson@example.com', '123 Main St, City, State', '1990-05-15'),
    (2, 'Bob', 'Williams', '555-987-6543', 'bob.williams@example.com', '456 Elm St, City, State', '1985-10-20'),
    (3, 'Charlie', 'Miller', '555-555-5555', 'charlie.miller@example.com', '789 Pine St, City, State', '1978-12-25'),
    (4, 'Emma', 'Wilson', '555-777-8888', 'emma.wilson@example.com', '101 Cedar St, City, State', '1995-08-08');

-- Insert data into Surgery table
INSERT INTO Surgery (surgery_id, name, location, address, telephone)
VALUES
    (1, 'Main Street Dental', 'City', '789 Oak St, City, State', '555-789-1234'),
    (2, 'Elite Dental Clinic', 'Town', '321 Maple Ave, Town, State', '555-321-9876'),
    (3, 'City Center Dental', 'City', '456 Cedar Ave, City, State', '555-222-3333'),
    (4, 'Parkside Dental Clinic', 'Suburb', '222 Elm St, Suburb, State', '555-444-5555');

-- Insert data into Bill table
INSERT INTO Bill (bill_id, patient_id, amount, paid)
VALUES
    (1, 1, 100.00, TRUE),
    (2, 2, 200.00, FALSE),
    (3, 3, 150.00, TRUE),
    (4, 4, 300.00, FALSE);

-- Insert dummy data into Appointment table
INSERT INTO Appointment (appointment_id, dentist_id, patient_id, date_time, surgery_id)
VALUES
    (1, 1, 1, '2024-04-10 09:00:00', 1),
    (2, 1, 2, '2024-04-11 10:00:00', 1),
    (3, 2, 1, '2024-04-12 11:00:00', 2),
    (4, 3, 3, '2024-04-13 13:00:00', 3),
    (5, 4, 4, '2024-04-14 14:00:00', 4);

-- Display the list of ALL Dentists registered in the system, sorted in ascending order of their lastNames: --
SELECT * FROM Dentist ORDER BY last_name ASC;

-- Display the list of ALL Appointments for a given Dentist by their dentist_Id number. Include in the result, the Patient information: --
SELECT Appointment.*, Patient.*
FROM Appointment
JOIN Patient ON Appointment.patient_id = Patient.patient_id
WHERE Appointment.dentist_id = 1;

-- Display the list of ALL Appointments that have been scheduled at a Surgery Location: --
SELECT Appointment.*, Surgery.*
FROM Appointment
JOIN Surgery ON Appointment.surgery_id = Surgery.surgery_id
WHERE Surgery.location = 'City';

-- Display the list of the Appointments booked for a given Patient on a given Date: --
SELECT Appointment.*
FROM Appointment
JOIN Patient ON Appointment.patient_id = Patient.patient_id
WHERE Patient.patient_id = 1
AND DATE(Appointment.date_time) = '2024-04-10';
