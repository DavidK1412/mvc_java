CREATE SCHEMA libreriareto;
USE libreriaReto;


CREATE TABLE producto(
                         prod_id INT PRIMARY KEY AUTO_INCREMENT,
                         prod_titulo CHAR(80)
);

INSERT INTO producto(prod_titulo) VALUES("Eloquent JavaScript");
INSERT INTO producto(prod_titulo) VALUES("Data Science: A Gentle Introduction");
INSERT INTO producto(prod_titulo) VALUES("Reinforcement: Learning, second edition: An Introduction");
INSERT INTO producto(prod_titulo) VALUES("Core Java Volume I - Fundamentals");
INSERT INTO producto(prod_titulo) VALUES("Introduction to Machine Learning with Python");


CREATE TABLE autor(
                      aut_id INT PRIMARY KEY AUTO_INCREMENT,
                      aut_nombre CHAR(20),
                      aut_apellido CHAR(20),
                      aut_nacionalidad CHAR(40)
);

INSERT INTO autor(aut_nombre, aut_apellido, aut_nacionalidad) VALUES("Richard", "Sutton", "canadiense");
INSERT INTO autor(aut_nombre, aut_apellido, aut_nacionalidad) VALUES("Marijn","Haverbeke", "aleman");
INSERT INTO autor(aut_nombre, aut_apellido, aut_nacionalidad) VALUES("James", "Scott", "estadounidense");
INSERT INTO autor(aut_nombre, aut_apellido, aut_nacionalidad) VALUES("Cay", "Horstmann", "aleman");
INSERT INTO autor(aut_nombre, aut_apellido, aut_nacionalidad) VALUES("Andreas", "Muller", "estadounidense");
#TERCER ARCHIVO

CREATE TABLE cliente(
                        cli_username CHAR(20) PRIMARY KEY,
                        cli_nombre CHAR(20),
                        cli_apellido CHAR(20),
                        cli_email CHAR(30),
                        cli_celular BIGINT,
                        cli_clave CHAR(20),
                        cli_fecha_nto DATE
);

#CUARTO ARCHIVO
CREATE TABLE revista(
                        rev_id_fk INT,
                        rev_ediciones INT,
                        rev_articulos INT,
                        FOREIGN KEY(rev_id_fk) REFERENCES producto(prod_id)
);
#QUINTO ARCHIVO
CREATE TABLE libro(
                      lib_id_fk INT,
                      lib_resenia CHAR(255),
                      lib_anio INT,
                      lib_autor_fk INT,
                      FOREIGN KEY(lib_id_fk) REFERENCES producto(prod_id),
                      FOREIGN KEY(lib_autor_fk) REFERENCES autor(aut_id)
);
INSERT INTO libro(lib_id_fk, lib_resenia, lib_anio, lib_autor_fk)VALUES(1, "Probably the biggest standout of Eloquent JavaScript: A Modern Introduction to Programming is its heavy usage of practice exercises. Unlike other books on programming and JS, this book is a work of pure art.", 2016, 2);
INSERT INTO libro(lib_id_fk, lib_resenia, lib_anio, lib_autor_fk)VALUES(2, "This book is structured as a series of walk-through lessons in R that will have you doing real data science in no time. It covers both the core ideas of data science as well as the concrete software skills.", 2020, 3);
INSERT INTO libro(lib_id_fk, lib_resenia, lib_anio, lib_autor_fk)VALUES(3, "The appetite for reinforcement learning among machine learning researchers has never been stronger, as now. If you want to fully understand the fundamentals of learning agents, this is the textbook to go to and get started with.", 2018, 1);
INSERT INTO libro(lib_id_fk, lib_resenia, lib_anio, lib_autor_fk)VALUES(4, "For serious programmers, Core Java, Volume I—Fundamentals, Eleventh Edition, is the definitive guide to writing robust, maintainable code. Whether you're using Java SE 9, 10, or 11.", 2018, 4);
INSERT INTO libro(lib_id_fk, lib_resenia, lib_anio, lib_autor_fk)VALUES(5, "Machine learning has become an integral part of many commercial applications and research projects, but this field is not exclusive to large companies with extensive research teams.", 2016, 5);

#SEXTO ARCHIVO
CREATE TABLE venta(
                      ven_id INT,
                      ven_user_fk CHAR(40),
                      ven_prod_fk INT,
                      ven_fecha DATETIME,
                      FOREIGN KEY(ven_user_fk) REFERENCES cliente(cli_username),
                      FOREIGN KEY(ven_prod_fk) REFERENCES producto(prod_id)
);
