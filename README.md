CONEXION A LA BASE DE DATOS

Paso a paso para conectar MySQL Workbench a Aiven
1. Abre MySQL Workbench en tu equipo.

2. Crear una nueva conexión:

- En la ventana principal de MySQL Workbench, haz clic en el icono + al lado de MySQL Connections para crear una nueva conexión.

3. Configurar la conexión: En la ventana de "Set up a New Connection", completa los siguientes campos usando las credenciales que Aiven te proporcionó:

- Connection Name: Aiven MySQL.
- Connection Method: Asegúrate de que esté seleccionada la opción "Standard (TCP/IP)".
- Hostname: mysql-18708054-estramipyme-d7b5.h.aivencloud.com
- Port: 26866
- Username: 
- Password: 

3. Probar la conexión:

Haz clic en el botón Test Connection para verificar que todo esté configurado correctamente. Si todo está bien, recibirás un mensaje de confirmación.


Desde visual Studio Code

Instrucciones para conectar la base de datos en un proyecto Spring Boot

Sigue estos pasos para configurar y conectar tu proyecto Spring Boot a la base de datos MySQL proporcionada:

1. Crear la carpeta resources en tu proyecto:
 *Asegúrate de estar utilizando un IDE como IntelliJ IDEA, Eclipse, o Visual Studio Code.
 *Navega al directorio src/main.
 *Crea una carpeta llamada resources si aún no existe.

2. Crear el archivo application.properties

 *Dentro de la carpeta resources, crea un archivo llamado application.properties.
CONEXION A LA BASE DE DATOS

Paso a paso para conectar MySQL Workbench a Aiven
1. Abre MySQL Workbench en tu equipo.

2. Crear una nueva conexión:
- En la ventana principal de MySQL Workbench, haz clic en el icono + al lado de MySQL Connections para crear una nueva conexión.

3. Configurar la conexión: En la ventana de "Set up a New Connection", completa los siguientes campos usando las credenciales que Aiven te proporcionó:
- Connection Name: Aiven MySQL.
- Connection Method: Asegúrate de que esté seleccionada la opción "Standard (TCP/IP)".
- Hostname: mysql-18708054-estramipyme-d7b5.h.aivencloud.com
- Port: 26866
- Username: 
- Password: 

3. Probar la conexión:

Haz clic en el botón Test Connection para verificar que todo esté configurado correctamente. Si todo está bien, recibirás un mensaje de confirmación.

Desde visual Studio Code

Instrucciones para conectar la base de datos en un proyecto Spring Boot
Sigue estos pasos para configurar y conectar tu proyecto Spring Boot a la base de datos MySQL proporcionada:

1. Crear la carpeta resources en tu proyecto:
 *Asegúrate de estar utilizando un IDE como IntelliJ IDEA, Eclipse, o Visual Studio Code.
 *Navega al directorio src/main.
 *Crea una carpeta llamada resources si aún no existe.

2. Crear el archivo application.properties
 *Dentro de la carpeta resources, crea un archivo llamado application.properties.
 *Abre el archivo y copia la siguiente configuración:
# Nombre de la aplicación
spring.application.name=estramipymeAPI
# Configuración de la base de datos
spring.datasource.url=jdbc:mysql://mysql-18708054-estramipyme-d7b5.h.aivencloud.com:26866/estramipyme
spring.datasource.username=
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
# Configuración de JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect



3. Verificar las dependencias de MySQL:
En tu archivo pom.xml, asegúrate de incluir la dependencia para MySQL:
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <scope>runtime</scope>
</dependency>
También verifica que tengas las siguientes dependencias para JPA y Spring Boot:
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>


4. Probar la conexión a la base de datos

•	*Arranca tu aplicación Spring Boot ejecutando el comando mvn spring-boot:run o ejecutando la clase principal desde tu IDE.
•	*Revisa los logs de la aplicación; deberías ver un mensaje indicando que la conexión a la base de datos se realizó correctamente.


5. Solución de problemas comunes:
•	*Error de conexión: Revisa que la URL, el usuario y la contraseña sean correctos.
•	*Driver no encontrado: Asegúrate de que el mysql-connector-java esté incluido en tus dependencias.
•	*Puerto bloqueado: Confirma que el puerto 26866 esté accesible en tu entorno.


PD: Se adjunta dentro del git script de postman para realizar pruebas
