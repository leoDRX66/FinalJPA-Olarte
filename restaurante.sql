CREATE DATABASE restaurante;
USE restaurante;

CREATE TABLE personas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cedula VARCHAR(20) UNIQUE NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    telefono VARCHAR(20),
    tipo_persona ENUM('GERENTE', 'CHEF', 'MESERO', 'CLIENTE') NOT NULL,
    fecha_vinculacion DATE,
    hora_ingreso TIME,
    hora_salida TIME,
    salario DOUBLE,
    usuario VARCHAR(50),
    contrasena VARCHAR(50)
);

CREATE TABLE ingredientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(100) NOT NULL,
    cantidad_stock INT DEFAULT 0
);

CREATE TABLE recetas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    proceso TEXT,
    chef_id INT,
    FOREIGN KEY (chef_id) REFERENCES personas(id)
);

CREATE TABLE receta_detalles (
    receta_id INT,
    ingrediente_id INT,
    cantidad INT,
    PRIMARY KEY (receta_id, ingrediente_id),
    FOREIGN KEY (receta_id) REFERENCES recetas(id),
    FOREIGN KEY (ingrediente_id) REFERENCES ingredientes(id)
);

CREATE TABLE alimentos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    precio DOUBLE NOT NULL,
    tipo_alimento VARCHAR(50),
    receta_id INT,
    FOREIGN KEY (receta_id) REFERENCES recetas(id)
);

CREATE TABLE pedidos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
    total DOUBLE,
    estado BOOLEAN DEFAULT 1, 
    cliente_id INT,
    mesero_id INT,
    FOREIGN KEY (cliente_id) REFERENCES personas(id),
    FOREIGN KEY (mesero_id) REFERENCES personas(id)
);

DROP TABLE IF EXISTS pedidos_cabecera;
CREATE TABLE pedidos_cabecera (
    id INT AUTO_INCREMENT PRIMARY KEY,
    numero_mesa INT NOT NULL,
    id_mesero INT,
    id_chef INT, -- Asumimos un chef por mesa, o podrías moverlo al detalle
    fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
    total_final DOUBLE,
    estado VARCHAR(20) DEFAULT 'PENDIENTE',
    FOREIGN KEY (id_mesero) REFERENCES personas(id),
    FOREIGN KEY (id_chef) REFERENCES personas(id)
);

DROP TABLE IF EXISTS pedidos_items;
CREATE TABLE pedidos_items (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_cabecera INT,
    id_alimento INT,
    cantidad INT DEFAULT 1,
    precio_unitario DOUBLE,
    subtotal DOUBLE,
    FOREIGN KEY (id_cabecera) REFERENCES pedidos_cabecera(id),
    FOREIGN KEY (id_alimento) REFERENCES alimentos(id)
);

USE restaurante;

DROP TABLE IF EXISTS pedidos_detalle;
CREATE TABLE pedidos_detalle (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_plato INT,
    id_bebida INT,
    id_chef INT,
    id_mesero INT,
    id_cliente INT, 
    total DOUBLE,
    fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_plato) REFERENCES alimentos(id),
    FOREIGN KEY (id_bebida) REFERENCES alimentos(id),
    FOREIGN KEY (id_chef) REFERENCES personas(id),
    FOREIGN KEY (id_mesero) REFERENCES personas(id)
);

INSERT INTO personas (nombre, cedula, telefono, tipo_persona, salario, fecha_vinculacion, hora_ingreso, hora_salida) 
VALUES 
('Juan Pérez', 'CHEF001', '+54 9 11 3456-7890', 'CHEF', 5000.00, CURDATE(), '08:00:00', '16:00:00'),
('María González', 'CHEF002', '+54 9 261 567-8901', 'CHEF', 4500.00, CURDATE(), '09:00:00', '17:00:00'),
('Carlos López', 'CHEF003', '+54 9 351 234-5678', 'CHEF', 4800.00, CURDATE(), '10:00:00', '18:00:00'),
('Laura Díaz', 'CHEF004', '+54 9 223 456-7890', 'CHEF', 4600.00, CURDATE(), '08:00:00', '16:00:00'),
('Pedro Sánchez', 'CHEF005', '+54 9 11 9876-5432', 'CHEF', 5200.00, CURDATE(), '11:00:00', '19:00:00'),
('Sofía Romero', 'CHEF006', '+54 9 381 654-3210', 'CHEF', 4700.00, CURDATE(), '09:00:00', '17:00:00'),
('Diego Torres', 'CHEF007', '+54 9 299 111-2222', 'CHEF', 4900.00, CURDATE(), '10:00:00', '18:00:00');


INSERT INTO personas (nombre, cedula, telefono, tipo_persona, salario, fecha_vinculacion, hora_ingreso, hora_salida) 
VALUES 
('Ana Martínez', 'MES001', '+54 9 11 6789-0123', 'MESERO', 1200.00, CURDATE(), '12:00:00', '22:00:00'),
('Luis Rodríguez', 'MES002', '+54 9 341 890-1234', 'MESERO', 1200.00, CURDATE(), '12:00:00', '22:00:00'),
('Carmen Gómez', 'MES003', '+54 9 261 333-4444', 'MESERO', 1250.00, CURDATE(), '13:00:00', '23:00:00'),
('Jorge Ruiz', 'MES004', '+54 9 11 555-6666', 'MESERO', 1200.00, CURDATE(), '12:00:00', '22:00:00'),
('Elena Vázquez', 'MES005', '+54 9 351 777-8888', 'MESERO', 1300.00, CURDATE(), '18:00:00', '02:00:00'),
('Lucas Fernández', 'MES006', '+54 9 221 999-0000', 'MESERO', 1200.00, CURDATE(), '12:00:00', '22:00:00');


USE restaurante;
CREATE TABLE IF NOT EXISTS pedidos_detalle (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_plato INT, id_bebida INT, id_chef INT, id_mesero INT, id_cliente INT, total DOUBLE,
    FOREIGN KEY (id_plato) REFERENCES alimentos(id),
    FOREIGN KEY (id_chef) REFERENCES personas(id)
);





