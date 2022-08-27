# Roles and Security Demo (Spring MVC)

This project is for demonstrating user-role based security in a Spring MVC application.

There are 3 user roles here, with different access provided to each role. Below is the database screenshot.

<img width="533" alt="image" src="https://user-images.githubusercontent.com/96373227/163824839-3321f850-512d-4ffa-9ca3-e9be178f10ea.png">

<img width="335" alt="image" src="https://user-images.githubusercontent.com/96373227/163823096-27b531cd-7396-44a6-84c0-214b03f8eb3f.png">

Below is the website's "root" page, which does not require any login, and is visible to the general public.

<img width="539" alt="image" src="https://user-images.githubusercontent.com/96373227/163823239-23254acd-63ec-4cb0-a580-d4dc0d6e5162.png">

Users can login here

<img width="566" alt="image" src="https://user-images.githubusercontent.com/96373227/163823341-ea7bea3f-0710-4976-aa58-f6d2da64300e.png">

User "John" has "Employee" role and can view only this "Home" page after login.

<img width="402" alt="image" src="https://user-images.githubusercontent.com/96373227/163823497-aad90f56-0958-44b3-868a-088088b488cc.png">

User "Mary" has "Employee" and "Manager" role and can view the below "Home" page and the special "Leaders" page ("Leaders" page is accessible to Managers only).

<img width="398" alt="image" src="https://user-images.githubusercontent.com/96373227/163823774-1058fa66-d655-4462-92ef-f4c2860e3152.png">

<img width="533" alt="image" src="https://user-images.githubusercontent.com/96373227/163823829-cbe7f591-3b14-475c-8d89-ed81ce31a322.png">

User "Susan" has "Employee" and "Admin" role and can view the below "Home" page and the special "IT System Admins" page ("IT System Admins" page is accessible to Admins only).

<img width="428" alt="image" src="https://user-images.githubusercontent.com/96373227/163824242-1da724eb-643d-4b31-b9c5-741369558c03.png">

<img width="613" alt="image" src="https://user-images.githubusercontent.com/96373227/163824289-8a805a21-1658-459c-9e2c-5848d1496f88.png">
