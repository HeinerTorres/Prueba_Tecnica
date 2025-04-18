CREATE DATABASE prueba_bancolombia;
USE prueba_bancolombia;

--     												Estructura de base de datos
-- Tabla Clientes
CREATE TABLE Clientes (
  id INT PRIMARY KEY,
  nombre VARCHAR(100),
  email VARCHAR(100),
  fecha_registro DATE
);

-- Tabla Pedidos
CREATE TABLE Pedidos (
  id INT PRIMARY KEY,
  cliente_id INT,
  monto DECIMAL(10,2),
  fecha_pedido DATE,
  FOREIGN KEY (cliente_id) REFERENCES Clientes(id)
);

-- Insertar datos en tabla Clientes
INSERT INTO Clientes (id, nombre, email, fecha_registro) VALUES
(1, 'Santiago', 'santiago@email.com', '2024-01-01'),
(2, 'David', 'david@email.com', '2024-01-10'),
(3, 'Giovanni', 'giovanni@email.com', '2024-02-15'),
(4, 'Alejandra', 'alejandra@email.com', '2024-02-10');

-- Insertar datos en tabla Pedidos
INSERT INTO Pedidos (id, cliente_id, monto, fecha_pedido) VALUES
(1, 1, 500.00, '2024-03-01'),
(2, 1, 400.00, '2024-03-02'),
(3, 2, 700.00, '2024-02-10'),
(4, 2, 1000.00, '2024-03-11'),
(5, 2, 1200.00, '2024-03-12'),
(6, 3, 1500.00, '2024-03-20'),
(7, 3, 500.00, '2024-03-25');




--                                          Consultas SQL (Ejercicio 1)

-- Seleccionamos el nombre del cliente y la suma total de los montos de sus pedidos
SELECT 
  c.nombre,                        -- Nombre del cliente
  SUM(p.monto) AS Total_gastado   -- Suma del monto de los pedidos del cliente
FROM 
  Clientes c                       -- Tabla de clientes con alias 'c'
JOIN 
  Pedidos p ON c.id = p.cliente_id -- Unimos con la tabla Pedidos usando la clave foránea
GROUP BY 
  c.nombre;                        -- Agrupamos por nombre para obtener un total por cliente
  
  
  
-- Filtramos para mostrar solo los que gastaron más de 1000
SELECT 
  c.nombre,                     
  SUM(p.monto) AS Total_gastado  
FROM 
  Clientes c
JOIN 
  Pedidos p ON c.id = p.cliente_id
GROUP BY 
  c.nombre
HAVING 
  SUM(p.monto) > 1000;            -- Condición para mostrar solo clientes que gastaron más de 1000



--                                          Corrección de consulta SQL (Ejercicio 2)
SELECT nombre, SUM(monto)
FROM Clientes, Pedidos
WHERE Clientes.id = Pedidos.cliente_id;

SELECT 
  c.nombre,               -- Selecciona el nombre del cliente
  SUM(p.monto) AS Total_gastado  -- Suma el monto total de sus pedidos
FROM 
  Clientes c              -- Alias 'c' para la tabla Clientes
JOIN 
  Pedidos p ON c.id = p.cliente_id  -- JOIN explícito con condición
GROUP BY 
  c.nombre;               -- Agrupa los resultados por cliente

