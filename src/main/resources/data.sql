insert into Example (id, value_example, description) values (1, 'example', 'this is an example');

DROP TABLE IF EXISTS usuario;

CREATE TABLE usuario (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  nombre VARCHAR(250) NOT NULL,
  apellido VARCHAR(250) NOT NULL,
  login VARCHAR(250) NOT NULL,
  password VARCHAR(250) NOT NULL,
  cedula INT ,
  fecha_creacion timestamp,
  estado BOOLEAN DEFAULT true
);
