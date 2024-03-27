# JAVA-venta_de_entradas
**Trabajo para catedra JAVA UTN Rosario**  
**Tecnologias**: Java Servlet, MySQL, JSP Boostrap

**Gino Bartolucci**  
**Joaquin Betes**

## Alcance:

Venta de entradas para shows creados por una productora y que pueden ser comprado por usuarios.  
El usuario debe estar registrado para poder comprar la entrada.  
La productora necesita estar registrado y logueado para publicar o administrar el show.  
Los shows son de un artista y tienen estan en un lugar. El lugar en una ciudad y la ciudad en una provincia.  
La productora puede escanear la entrada para que no se pueda usar mas.  

# Casos de uso:

- Crear Show. (regularidad).
- Comprar entrada. (Ap. directa).
- Escaneo de entrada por parte de la productora. (Ap. directa).

# Listados:

- Filtro de show por ciudad.
- Listado de todos los shows.

# Tablas:
- Artista: id, nombre
- Shows: id, precio, horario
- Lugar: id, nombre, direccion, capacidad
- Ciudad: id, nombre
- Provincia: id, nombre
- Usuario: id, usuario, mail, contraseña, cuil, teléfono, nombre
- Entrada: cod, nombre, apellido, tipo_doc, documento, valida

DER: [link](https://drive.google.com/file/d/1_-u7W4zI35HPc6c3CfvD-wUZJjBTGvev/view?usp=sharing)
