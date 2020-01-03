CREATE DATABASE proyecto_final;
use proyecto_final;
CREATE TABLE Usuario (ID int AUTO_INCREMENT PRIMARY KEY, Usuario VARCHAR(35) NULL DEFAULT NULL, Nombre VARCHAR(35) NULL DEFAULT NULL, Salario DOUBLE NULL DEFAULT NULL,
 Imagen VARCHAR(200) NOT NULL DEFAULT '', Contraseña VARCHAR(100), UNIQUE INDEX Usuario (Usuario));

CREATE TABLE Tarea (ID int AUTO_INCREMENT PRIMARY KEY, Nombre VARCHAR(120) NULL DEFAULT NULL, Categoria VARCHAR(20) NULL DEFAULT NULL, Fecha_Inscrita DATE NULL DEFAULT NULL,
 Fecha_Realizar DATE NULL DEFAULT NULL, Descripcion VARCHAR(120) NULL DEFAULT NULL, Estado bool NULL DEFAULT NULL, Prioritario bool NULL DEFAULT NULL, Id_Usuario int NULL DEFAULT NULL,
 FOREIGN KEY (Id_Usuario) REFERENCES Usuario (ID));

CREATE TABLE Subtarea (ID int AUTO_INCREMENT PRIMARY KEY, Nombre VARCHAR(35), Estado bool, ID_Tarea INT, FOREIGN KEY (ID_Tarea) REFERENCES Tarea (ID) ON DELETE CASCADE);

CREATE TABLE Gasto (ID int AUTO_INCREMENT PRIMARY KEY, Nombre_Gasto VARCHAR(50) NULL DEFAULT NULL, Tipo_Gasto VARCHAR(30) NULL DEFAULT NULL, Precio_Gasto DOUBLE NULL DEFAULT NULL, ID_Usuario int NULL DEFAULT NULL,
FOREIGN KEY (Id_Usuario) REFERENCES Usuario (ID));

CREATE TABLE Producto (ID int NOT NULL AUTO_INCREMENT PRIMARY KEY, Nombre_Prod VARCHAR(50) NULL DEFAULT NULL, Precio_Prod DOUBLE NULL DEFAULT NULL, ID_Gasto int NULL DEFAULT NULL,
FOREIGN KEY (ID_Gasto) REFERENCES Gasto (ID) ON DELETE CASCADE);



INSERT INTO Usuario (Usuario, Nombre, Salario, Contraseña) VALUES ('Manu333', 'Manuel Serrano', 1200, 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3');
INSERT INTO Usuario (Usuario, Nombre, Salario, Contraseña) VALUES ('Alejandro123', 'Alejandro Rámirez', 1200, 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3');
INSERT INTO Usuario (Usuario, Nombre, Salario, Contraseña) VALUES ('mc15', 'Mari Carmen', 1200, 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3');
INSERT INTO Usuario (Usuario, Nombre, Salario, Contraseña) VALUES ('Ruiz50', 'Ramón Ruiz', 1200, 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3');
INSERT INTO Usuario (Usuario, Nombre, Salario, Contraseña) VALUES ('Prozis', 'Prozis Spain', 1200, 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3');




INSERT INTO Tarea (Nombre, Categoria, Fecha_Inscrita, Fecha_Realizar, Descripcion, Estado, Prioritario, ID_Usuario) VALUES ('Estudiar para Selectividad', 'Estudios', '2019-06-18', '2019-06-25', 'Preparar examenes de selectividad', 0, 1, 1);
INSERT INTO Tarea (Nombre, Categoria, Fecha_Inscrita, Fecha_Realizar, Descripcion, Estado, Prioritario, ID_Usuario) VALUES ('Celebrar cumpleaños de María', 'Ocio', '2019-06-15', '2019-06-27', 'Organizar fiesta de cumpleaños de Maria', 0, 0, 1);
INSERT INTO Tarea (Nombre, Categoria, Fecha_Inscrita, Fecha_Realizar, Descripcion, Estado, Prioritario, ID_Usuario) VALUES ('Entregar documentación del software', 'Trabajo', '2019-06-27', '2019-06-15', 'Preparar documentación de software', 1, 1, 1);
INSERT INTO Tarea (Nombre, Categoria, Fecha_Inscrita, Fecha_Realizar, Descripcion, Estado, Prioritario, ID_Usuario) VALUES ('Pagar mensualidad gym', 'Ocio', '2019-06-18', '2019-06-10', 'Gym', 1, 0, 1);
INSERT INTO Tarea (Nombre, Categoria, Fecha_Inscrita, Fecha_Realizar, Descripcion, Estado, Prioritario, ID_Usuario) VALUES ('Hacer la compra semanal', 'Otros', '2019-06-18', '2019-06-10', 'compra', 0, 0, 1);
INSERT INTO Tarea (Nombre, Categoria, Fecha_Inscrita, Fecha_Realizar, Descripcion, Estado, Prioritario, ID_Usuario) VALUES ('Estudiar para Selectividad', 'Estudios', '2019-06-18', '2019-06-25', 'Preparar examenes de selectividad', 1, 1, 2);
INSERT INTO Tarea (Nombre, Categoria, Fecha_Inscrita, Fecha_Realizar, Descripcion, Estado, Prioritario, ID_Usuario) VALUES ('Celebrar cumpleaños de María', 'Ocio', '2019-06-15', '2019-06-27', 'Organizar fiesta de cumpleaños de Maria', 1, 0, 2);
INSERT INTO Tarea (Nombre, Categoria, Fecha_Inscrita, Fecha_Realizar, Descripcion, Estado, Prioritario, ID_Usuario) VALUES ('Entregar documentación del software', 'Trabajo', '2019-06-27', '2019-06-15', 'Preparar documentación de software', 1, 1, 2);
INSERT INTO Tarea (Nombre, Categoria, Fecha_Inscrita, Fecha_Realizar, Descripcion, Estado, Prioritario, ID_Usuario) VALUES ('Pagar mensualidad gym', 'Ocio', '2019-06-18', '2019-06-10', 'Gym', 1, 0, 3);
INSERT INTO Tarea (Nombre, Categoria, Fecha_Inscrita, Fecha_Realizar, Descripcion, Estado, Prioritario, ID_Usuario) VALUES ('Hacer la compra semanal', 'Otros', '2019-06-18', '2019-06-10', 'compra', 1, 0, 3);
INSERT INTO Tarea (Nombre, Categoria, Fecha_Inscrita, Fecha_Realizar, Descripcion, Estado, Prioritario, ID_Usuario) VALUES ('Pagar mensualidad gym', 'Ocio', '2019-06-18', '2019-06-10', 'Gym', 1, 0, 4);
INSERT INTO Tarea (Nombre, Categoria, Fecha_Inscrita, Fecha_Realizar, Descripcion, Estado, Prioritario, ID_Usuario) VALUES ('Hacer la compra semanal', 'Otros', '2019-06-18', '2019-06-10', 'compra', 1, 0, 5);


INSERT INTO Subtarea (Nombre, Estado, ID_Tarea) VALUES ('Preparar apuntes de lengua', 0, 1);
INSERT INTO Subtarea (Nombre, Estado, ID_Tarea) VALUES ('Preparar apuntes de física', 1, 1);
INSERT INTO Subtarea (Nombre, Estado, ID_Tarea) VALUES ('Preparar apuntes de química', 0, 1);
INSERT INTO Subtarea (Nombre, Estado, ID_Tarea) VALUES ('Documentar apartado 1.3', 1, 3);
INSERT INTO Subtarea (Nombre, Estado, ID_Tarea) VALUES ('Corregir faltas de ortografía', 0, 3);
INSERT INTO Subtarea (Nombre, Estado, ID_Tarea) VALUES ('Tomate', 0, 5);
INSERT INTO Subtarea (Nombre, Estado, ID_Tarea) VALUES ('Napolitanas', 0, 5);
INSERT INTO Subtarea (Nombre, Estado, ID_Tarea) VALUES ('Pechuga de pollo', 0, 5);
INSERT INTO Subtarea (Nombre, Estado, ID_Tarea) VALUES ('Pasta', 0, 5);
INSERT INTO Subtarea (Nombre, Estado, ID_Tarea) VALUES ('Salmón', 0, 5);
INSERT INTO Subtarea (Nombre, Estado, ID_Tarea) VALUES ('Pan', 0, 5);

INSERT INTO Gasto (Nombre_Gasto, Tipo_Gasto, Precio_Gasto, ID_Usuario) VALUES ('Ingredientes de tarta de cumpleaños', 'Producto', 0, 1);
INSERT INTO Gasto (Nombre_Gasto, Tipo_Gasto, Precio_Gasto, ID_Usuario) VALUES ('Gym Junio', 'Servicio', 0, 1);
INSERT INTO Gasto (Nombre_Gasto, Tipo_Gasto, Precio_Gasto, ID_Usuario) VALUES ('Reparación Coche', 'Producto', 0, 1);
INSERT INTO Gasto (Nombre_Gasto, Tipo_Gasto, Precio_Gasto, ID_Usuario) VALUES ('Ingredientes de tarta de cumpleaños', 'Producto', 0, 2);
INSERT INTO Gasto (Nombre_Gasto, Tipo_Gasto, Precio_Gasto, ID_Usuario) VALUES ('Gym Junio', 'Servicio', 0, 2);
INSERT INTO Gasto (Nombre_Gasto, Tipo_Gasto, Precio_Gasto, ID_Usuario) VALUES ('Reparación Coche', 'Producto', 0, 3);
INSERT INTO Gasto (Nombre_Gasto, Tipo_Gasto, Precio_Gasto, ID_Usuario) VALUES ('Ingredientes de tarta de cumpleaños', 'Producto', 0, 3);
INSERT INTO Gasto (Nombre_Gasto, Tipo_Gasto, Precio_Gasto, ID_Usuario) VALUES ('Gym Junio', 'Servicio', 0, 4);
INSERT INTO Gasto (Nombre_Gasto, Tipo_Gasto, Precio_Gasto, ID_Usuario) VALUES ('Reparación Coche', 'Producto', 0, 5);
