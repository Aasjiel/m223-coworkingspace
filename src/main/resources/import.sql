-- Create Roles

INSERT INTO public."Role" (id, name) VALUES (1, 'Admin');
INSERT INTO public."Role" (id, name) VALUES (2, 'Mitglied');

-- Create Places

INSERT INTO public."Place" (id, "placeNumber", location) VALUES (1, 3, 'Halle A');
INSERT INTO public."Place" (id, "placeNumber", location) VALUES (2, 77, 'Halle A');

-- Create Users

INSERT INTO public."User" (id, email, firstname, lastname, password, username, role) VALUES (1, 'seb@zuckerberg.ch', 'sebastian', 'zuckerberg', 'zuckermans', 'admin1234', 1);
INSERT INTO public."User" (id, email, firstname, lastname, password, username, role) VALUES (2, 'john@spatz.ch', 'johannes', 'spatz', 'spatzj', '1234', 2);

-- Create Bookings

INSERT INTO public."Booking" (id, approved, date, "endTime", "startTime", place, "user") VALUES (1, false, '2022-03-10', '2022-03-10 12:15:50', '2022-03-10 12:15:50', 1, 2);
INSERT INTO public."Booking" (id, approved, date, "endTime", "startTime", place, "user") VALUES (2, true, '2022-03-10', '2022-03-10 12:50:50', '2022-03-10 13:20:50', 2, 1);