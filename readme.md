# Inditex technical test



** Construir una aplicación/servicio en Spring el siguiente end point rest de consulta **

- Acepte como parámetros de entrada: fecha de aplicación, identificador de
producto, identificador de cadena.
- Devuelva como datos de salida: identificador de producto, identificador de
cadena, tarifa a aplicar, fechas de aplicación y precio final a aplicar.

Se debe utilizar una base de datos en memoria (tipo h2) e inicializar con los datos del
ejemplo, (se pueden cambiar el nombre de los campos y añadir otros nuevos si se quiere,
elegir el tipo de dato que se considere adecuado para los mismos).

● Desarrollar unos test al endpoint rest que validen las siguientes peticiones al servicio
con los datos del ejemplo:
- Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1
- Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1
- Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1
- Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1
- Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1

El endpoint se puede revisar con http://localhost:8080/springboot-app/swagger-ui/index.html desplegado en tomcat

# Evaluación
- Diseño y construcción del servicio.
- Calidad de Código.
- Resultados correctos en los test.

