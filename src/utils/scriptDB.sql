CREATE SCHEMA libreriareto;
USE libreriaReto;

CREATE TABLE producto(
    prod_id INT PRIMARY KEY AUTO_INCREMENT,
    prod_titulo CHAR(80)
);

CREATE TABLE autor(
    aut_id INT PRIMARY KEY AUTO_INCREMENT,
    aut_nombre CHAR(20),
    aut_apellido CHAR(20),
    aut_nacionalidad CHAR(40)
);

CREATE TABLE cliente(
    cli_username CHAR(20) PRIMARY KEY,
    cli_nombre CHAR(20),
    cli_apellido CHAR(20),
    cli_email CHAR(30),
    cli_celular BIGINT,
    cli_clave CHAR(20),
    cli_fecha_nto DATE
);

CREATE TABLE revista(
    rev_id_fk INT,
    rev_ediciones INT,
    rev_articulos INT,
    FOREIGN KEY(rev_id_fk) REFERENCES producto(prod_id)
);

CREATE TABLE libro(
    lib_id_fk INT,
    lib_resenia CHAR(255),
    lib_anio INT,
    lib_autor_fk INT,
    FOREIGN KEY(lib_id_fk) REFERENCES producto(prod_id),
    FOREIGN KEY(lib_autor_fk) REFERENCES autor(aut_id)
);

CREATE TABLE venta(
    ven_id INT,
    ven_user_fk CHAR(40),
    ven_prod_fk INT,
    ven_fecha DATETIME,
    FOREIGN KEY(ven_user_fk) REFERENCES cliente(cli_username),
    FOREIGN KEY(ven_prod_fk) REFERENCES producto(prod_id)
);