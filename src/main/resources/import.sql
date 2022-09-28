-- Create Roles

INSERT INTO public."Role" ( name) VALUES ( 'Admin');
INSERT INTO public."Role" ( name) VALUES ( 'Mitglied');

-- Create Places

INSERT INTO public."Place" ( "placeNumber", location) VALUES ( 3, 'Halle A');
INSERT INTO public."Place" ( "placeNumber", location) VALUES ( 77, 'Halle A');

-- Create Users

INSERT INTO public."User" ( email, firstname, lastname, password, username, role) VALUES ( 'seb@zuckerberg.ch', 'sebastian', 'zuckerberg', 'admin1234', 'zuckermans', 1);
INSERT INTO public."User" ( email, firstname, lastname, password, username, role) VALUES ( 'john@spatz.ch', 'johannes', 'spatz', '1234', 'spatzj', 2);

-- Create Bookings

INSERT INTO public."Booking" ( approved, date, "endTime", "startTime", place, "user") VALUES ( false, '2022-03-10', '2022-03-10 12:15:50', '2022-03-10 12:15:50', 1, 2);
INSERT INTO public."Booking" ( approved, date, "endTime", "startTime", place, "user") VALUES ( true, '2022-03-10', '2022-03-10 12:50:50', '2022-03-10 13:20:50', 2, 1);