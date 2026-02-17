Título del Proyecto: CitaIdeal.cl 🌹
 Plataforma e-commerce para la gestión de citas, reservas y planes románticos.

📋 Descripción
Este proyecto es una aplicación web full-stack desarrollada durante mi formación como Java Full Stack Trainee. El objetivo es facilitar la reserva de experiencias y productos, contando con un panel administrativo robusto y una interfaz de usuario dinámica.

✨ Características Principales
Gestión de Usuarios: Roles diferenciados para Clientes y Administradores utilizando Spring Security.

Panel Administrativo: Control total sobre productos, reservas y visualización de datos.

Catálogo Dinámico: Implementación de temas visuales adaptables a festividades.

Persistencia de Datos: Gestión eficiente de información con PostgreSQL y Spring Data JPA.

🛠️ Tecnologías Utilizadas
Backend: Java 17+, Spring Boot 4.x, Maven.

Frontend: HTML5, CSS3, JavaScript, Bootstrap 5, Thymeleaf.

Base de Datos: PostgreSQL.

Seguridad: Spring Security (Autenticación y Autorización).

Servidor: Apache Tomcat (embebido).

🚀 Instalación y Configuración
Para ejecutar este proyecto localmente, sigue estos pasos:

Clonar el repositorio:

Bash
git clone https://github.com/tu-usuario/cita-ideal.git
Configurar la base de datos:

Crea una base de datos en PostgreSQL llamada cita_ideal_db.

Actualiza las credenciales en el archivo src/main/resources/application.properties.

Ejecutar la aplicación:

Bash
mvn spring-boot:run
📂 Estructura del Proyecto
Siguiendo los principios de Arquitectura en Capas:

controller/: Manejo de rutas y peticiones HTTP.

service/: Lógica de negocio.

repository/: Interfaces para la comunicación con la base de datos (JPA).

model/: Definición de entidades (Usuario, Cliente, Administrador).

👤 Autor
Sergio Carocca - Full Stack Java Trainee - 