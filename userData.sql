use courseRegistration;

-- Admin
INSERT INTO user (first_name, address, city, dob, email, last_name, password, phone_num, postal_code, profession, role, username)
VALUES
('Siyi','6666 St','Montréal','1966-06-06','siyi@email.com','Chen','Admin123','123-456-7890','H8N 1T8','Admin','ADMIN','admin1'),
('ChihYin','8888 Av','Montréal','1988-08-08','cyc@email.com','Chen','Admin123','123-456-7890','H2Y 1T3','Admin','ADMIN','admin2')
;

-- Professors
INSERT INTO user (first_name, address, city, dob, email, last_name, password, phone_num, postal_code, profession, role, username)
VALUES
('Leia','0101 St','Montréal','1963-01-01','leia@email.com','Andrews','Prof123','123-456-7890','H8N 1T8','Computer Science','INSTRUCTOR','prof1'),
('Conner','2222 Av','Montréal','1973-02-02','conner@email.com','Chambers','Prof123','123-456-7890','H2Y 1T3','Algorithm','INSTRUCTOR','prof2'),
('Sadie','3333 St','Montréal','1983-03-03','sadie@email.com','Stewart','Prof123','123-456-7890','H2K 2A1 ','Computer Architecture','INSTRUCTOR','prof3'),
('Erik','4444 Av','Montréal','1965-04-04','erik@email.com','Belanger','Prof123','123-456-7890','H8N 1T8','Computer Programming','INSTRUCTOR','prof4'),
('Jose','5555 St','Montréal','1975-05-05','jose@email.com','Marquez','Prof123','123-456-7890','H2Y 1T3','Cryptography','INSTRUCTOR','prof5'),
('Charles','6666 Av','Montréal','1985-06-06','charles@email.com','James','Prof123','123-456-7890','H2K 2A1','Programming Language','INSTRUCTOR','prof6'),
('Sara','7777 St','Montréal','1967-07-07','sara@email.com','Lowe','Prof123','123-456-7890','H8N 1T8','Computer Engineering','INSTRUCTOR','prof7'),
('Alan','8888 Av','Montréal','1977-08-08','alan@email.com','Doyle','Prof123','123-456-7890','H2Y 1T3','Data Structure','INSTRUCTOR','prof8'),
('Ray','9999 St','Montréal','1987-09-09','ray@email.com','Hopkins','Prof123','123-456-7890','H2K 2A1','Human-computer Interaction','INSTRUCTOR','prof9'),
('Henry','1010 Av','Montréal','1960-10-10','henry@email.com','Kent','Prof123','123-456-7890','H2J 2M4','Artificial Intelligence','INSTRUCTOR','prof10')
;

select *
from user
;

-- semester
INSERT INTO semesters (quarter, year)
VALUES
('Fall', '2023'),
('Winter', '2023'),
('Spring', '2024'),
('Summer','2024')
;

select *
from semesters
;

-- courses
INSERT INTO courses (id, course_description, day_of_week, end_date, end_time, start_date, start_time, student_limit, title, instructor, semester)
VALUES
(1, 'This course is an introduction to computer concepts, tools for managing software development and documentation, and the foundation of web development. The course is organized into three sections. The first section introduces students to computer concepts and explains the major parts of a computer and how computers work. The second section provides basic information on file management and the applications for creating, organizing, and sharing design documents and business proposals. The third section covers fundamental HTML and CSS commands to produce a basic website.', 'M', '2023-12-22', '10:30', '2023-09-04', '09:00', '100', 'Foundations of Web Development', '3', '1'),
(2, 'This course begins with an introduction to flow charts used to model simple algorithms and decision-making structures. These are then implemented using the Java programming language, where the student learns about program structure, variable types, passing parameters, control structures (if/else, switch, do-while, while, for) as well as the creation and use of subroutines (methods) and arrays. The student also gains a basic understanding of computer program structure, algorithm design, debugging strategies, unit testing, and source control.', 'M', '2023-12-22', '12:30', '2023-09-04', '11:00', '100', 'Programming I', '4', '1'),
(3, 'This course focuses on introducing the student to basic principles of Object-Oriented Programming, such as classes, their design and implementation, constructors, try-catch clauses, and exceptions. It also ventures deeper into the standard Java library by exposing the student to Java Collection classes, for-each loop, text file input-output and network connectivity. Students will learn about the principles of encapsulation, inheritance, the concept of “static”, interfaces, abstract classes, and polymorphism. Emphasis is put on practical application of the skill to create a solid foundation for following programming courses.', 'M', '2023-12-22', '14:30', '2023-09-04', '13:00', '100', 'Programming II', '5', '1'),
(4, 'This course focuses on how to design, create, update, and query a relational database. This course covers the key concepts of database management systems, including normalization, table creation, relationships and query construction. Students will practice writing SQL queries, including JOINs and sub-queries. The student will gain an understanding of views and how they are used, as well as the advantages in the use of stored procedures along with guidelines on creating them. The student will learn what triggers are and how they enforce data integrity. Finally, the student will learn how to save and restore a database.', 'T', '2023-12-22', '10:30', '2023-09-04', '09:00', '100', 'Database', '6', '1'),
(5, 'This Web Design course leads the student through the entire Web site creation process, while developing and enhancing their HTML, CSS, and creative design skills along the way. This course introduces the concept of responsive web design which allows you to control a websites’ appearances on multiple screen sizes. Students will explore design strategies for laying out content, graphics, and navigation for multi-page websites. A wire-framing tool, such as Adobe XD, will be used to create mock-ups and user interface designs.', 'T', '2023-12-22', '12:30', '2023-09-04', '11:00', '100', 'Website Design', '7', '1'),
(6, 'This course continues developing website design skills by utilizing JavaScript scripting language that enables Web Designers to develop highly interactive Web-based user interfaces. Students will learn how to use JavaScript for modifying the Document Object Model, event handling, controlling animation, forms verification, timers and counters. The course will then focus on additional libraries. The jQuery library will be used to add GUI widgets, apply effects, make server-side requests, and handle incoming data. A JavaScript framework with be introduced to assist in building user interfaces.', 'T', '2023-12-22', '14:30', '2023-09-04', '13:00', '100', 'User Interfaces', '8', '1'),
(7, 'In this course the student will learn how to create and consume a variety of platform-independent web services, such as SOAP, and REST. The student will also learn to consume existing web services with public APIs (Application Programming Interface), such as Facebook, Twitter, GoogleAPI, and Yahoo Finances. Clients and servers for such services may be written using C#, Java, JavaScript, NodeJS as well as mobile platforms.', 'W', '2023-12-22', '10:30', '2023-09-04', '09:00', '100', 'Web Services', '9', '1'),
(8, 'In the first part of this course, the student will learn about the syntax and control structures of the PHP language, as well as its web-context-specific features that make it a powerful tool for dynamic web pages and web app development. The student will use PHP language and external libraries to implement web pages using the MVC (Model-View-Controller) principles, with a micro-framework, database access library, and templating library. The student will also learn about best industry practices, such as PHP IDEs, visual debugging, and the use of Composer. The second part of this course focus on a full-stack project, to be done in the PHP language, individually or in teams of 2 to 3 students.', 'W', '2023-12-22', '12:30', '2023-09-04', '11:00', '100', 'Web Development I', '10', '1'),
(9, 'This course introduces students to the requirements of application and data security. Students will configure a development and production environment, apply authentication and authorization measures, and deploy a secure Web application. A scripting language such as Python will be used for system administration tasks. Deployment options on AWS and Azure will be examined.', 'W', '2023-12-22', '14:30', '2023-09-04', '13:00', '100', 'Cloud Administration and Security', '3', '1'),
(10, 'In this course, students will develop their capacity to interpret, select and adapt algorithms to solve problems in a given situation. They will begin by learning about the importance of data structures in order to manage large amounts of data efficiently. The student will then apply data structures to produce efficient algorithms. The course will include topics such as stacks, queues, lists, and trees. There will be an emphasis on cultivating an attitude and approach to problem solving.', 'TH', '2023-12-22', '10:30', '2023-09-04', '09:00', '100', 'Data Structures and Algorithms', '4', '1'),
(11, 'In this course the student will learn how and when to use the advanced Object-Oriented Programming features of the Java language such as encapsulation, inheritance, the concept of “static”, interfaces, abstract classes, and polymorphism. This course will also expose the student to event-driven programming and writing unit and integration tests for an application written in Java’s Spring framework. Emphasis is put on integration of the
36
content learned in this course and the previous two programming courses. Students will focus on a project using Java Spring.', 'TH', '2023-12-22', '12:30', '2023-09-04', '11:00', '100', 'Programming III', '5', '1'),
(12, 'This is a project-based course to provide students the opportunity to use their skills in web development to develop and deploy an application using a JavaScript framework, such as Angular or Vue. Working in teams, students will examine user requirements of a SME and design and develop an application. Emphasis will be on deployment and creating effective documentation of the project with the goal of producing maintainable code.', 'TH', '2023-12-22', '14:30', '2023-09-04', '13:00', '100', 'Web Development II', '6', '1'),
(13, 'This course introduces students to the various ways to prepare for their career as a Full-Stack Developer and integrate into the labour market. Interview skills, CV writing, preparing an online profile using Linkedin, and researching job boards are essential skills for the job search. Additionally, students will prepare for an interview by researching a company and researching a technology, to develop their research skills and develop an aptitude for continuous learning. Students will research companies, contact companies, submit their cv, and arrange interviews for their internship during this course.', 'F', '2023-12-22', '10:30', '2023-09-04', '09:00', '100', 'Integration into the Labour Market', '7', '1'),
(14, 'This course introduces software engineering using C# and covers all introductory aspects of .Net programming, including control structures, functions, arrays, and GUIs. Using C#, the students will define classes and apply the principles of encapsulation, inheritance and polymorphism. The emphasis throughout the course is on using sound software engineering principles and concepts. Students will be introduced to a set of fundamental skills in constructing system components and to a system design that will be useful in the construction of large systems as well as small components. Different GUIs will be explored such as Windows Presentation Foundation (WPF) in order to create rich, interactive client applications. Using the .NET Framework students will create and deploy fully functional, accessible, and secure Windows applications.', 'F', '2023-12-22', '12:30', '2023-09-04', '11:00', '100', 'Application Development I', '8', '1'),
(15, 'This course will teach the fundamentals of Web application site implementation by using Microsoft ASP.NET and Microsoft C# .NET. Using the Microsoft Visual Studio® .NET environment and the Microsoft .NET platform, the goal is to create an ASP.NET Web application that delivers dynamic content to a Web site. This course introduces students to the ASP.NET Model-View-Controller (MVC) web development framework. MVC is a powerful, patterns-based way to build dynamic websites that enables a clean separation of concerns and that provides full control over markup for an effective Agile development. ASP.NET MVC includes many features that enable rapid, test-driven development. This course will cover many web development related concepts such as databases, servers, configuration files, LINQ, ADO.NET Entity Framework, page validation and hosting. These concepts will be applied to the development of ASP.NET MVC Web applications.', 'F', '2023-12-22', '14:30', '2023-09-04', '13:00', '100', 'Application Development II', '9', '1')
;

select *
from courses
;
