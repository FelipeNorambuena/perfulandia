<div align="center">

# 🌸 Perfulandia SPA
### Sistema de Gestión de Microservicios

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![MariaDB](https://img.shields.io/badge/MariaDB-003545?style=for-the-badge&logo=mariadb&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-Auth-black?style=for-the-badge&logo=jsonwebtokens&logoColor=white)
![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white)

*Backend basado en arquitectura de microservicios para la gestión integral de una perfumería*

</div>

---

## 📋 Tabla de Contenidos

- [Descripción](#-descripción)
- [Arquitectura](#-arquitectura)
- [Microservicios](#-microservicios)
- [Tecnologías](#-tecnologías)
- [Requisitos Previos](#-requisitos-previos)
- [Instalación y Configuración](#-instalación-y-configuración)
- [Base de Datos](#-base-de-datos)
- [Colecciones Postman](#-colecciones-postman)
- [Estructura del Proyecto](#-estructura-del-proyecto)

---

## 📖 Descripción

**Perfulandia SPA** es un sistema backend desarrollado con arquitectura de **microservicios** usando **Spring Boot**. Permite gestionar de forma modular e independiente las distintas áreas de negocio de una perfumería: clientes, productos, ventas, envíos, inventario, reportes, soporte y vendedores.

Todas las peticiones pasan a través de un **API Gateway centralizado** que se encarga de la autenticación mediante **JWT** y el enrutamiento hacia cada microservicio.

---

## 🏗️ Arquitectura

```
Cliente HTTP / Postman
        │
        ▼
┌──────────────────────┐
│    API Gateway       │  ← Autenticación JWT + Enrutamiento
│  (Puerto: 8080)      │
└──────────────────────┘
        │
        ├──► Clientes        (clientes-api-spring-boot)
        ├──► Productos       (productos-api-spring-boot)
        ├──► Ventas          (API-Venta)
        ├──► Envíos          (API-Envios)
        ├──► Inventario      (API-Inventario)
        ├──► Reportes        (API-Reportes)
        ├──► Vendedores      (API-Vendedor)
        ├──► Soporte/Tickets (ApiSoporteTicket)
        └──► Gestión Usuarios(gestion-usuarios-api-spring-boot)
```

![Diagrama de Microservicios](Diagrama%20de%20microservicios%20Perfulandia%20SPA.png)

---

## 🔧 Microservicios

| Microservicio | Directorio | Descripción |
|---|---|---|
| 🔀 **API Gateway** | `api-gateway-api-spring-boot` | Punto de entrada central. Maneja autenticación JWT y redirige peticiones |
| 👤 **Clientes** | `clientes-api-spring-boot` | CRUD de clientes registrados en la plataforma |
| 📦 **Productos** | `productos-api-spring-boot` | Gestión del catálogo de productos/perfumes |
| 💰 **Ventas** | `API-Venta` | Registro y gestión de ventas y detalle de ventas |
| 🚚 **Envíos** | `API-Envios` | Control y seguimiento de envíos |
| 🗃️ **Inventario** | `API-Inventario` | Gestión del stock de productos |
| 📊 **Reportes** | `API-Reportes` | Generación de reportes del negocio |
| 🧑‍💼 **Vendedores** | `API-Vendedor` | Administración de vendedores |
| 🎫 **Soporte** | `ApiSoporteTicket` | Sistema de tickets de soporte al cliente |
| 🔐 **Usuarios** | `gestion-usuarios-api-spring-boot` | Gestión de usuarios y roles del sistema |

---

## 🛠️ Tecnologías

- **Java 17**
- **Spring Boot 3.x**
  - Spring Web
  - Spring Security
  - Spring Data JPA
  - Spring Boot DevTools
- **MariaDB / MySQL** — Base de datos relacional
- **JWT (jjwt 0.11.5)** — Autenticación stateless
- **Lombok** — Reducción de boilerplate
- **Springdoc OpenAPI / Swagger UI** — Documentación automática
- **Maven** — Gestión de dependencias
- **Postman** — Testing de endpoints

---

## ✅ Requisitos Previos

Antes de ejecutar el proyecto asegúrate de tener instalado:

- ☕ [Java 17+](https://adoptium.net/)
- 🛠️ [Maven 3.8+](https://maven.apache.org/)
- 🗄️ [MariaDB](https://mariadb.org/) o MySQL
- 📬 [Postman](https://www.postman.com/) *(opcional, para pruebas)*

---

## 🚀 Instalación y Configuración

### 1. Clonar el repositorio

```bash
git clone https://github.com/FelipeNorambuena/perfulandia.git
cd perfulandia
```

### 2. Configurar la base de datos

Importa el script SQL incluido en el proyecto:

```bash
mysql -u root -p < perfulandia.sql
```

### 3. Configurar `application.properties` en cada microservicio

En cada microservicio, edita el archivo `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mariadb://localhost:3306/perfulandia
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_CONTRASEÑA
spring.jpa.hibernate.ddl-auto=update
```

### 4. Ejecutar los microservicios

Puedes ejecutar cada microservicio de forma independiente con Maven:

```bash
# Ejemplo: ejecutar el API Gateway
cd api-gateway-api-spring-boot
./mvnw spring-boot:run
```

Repite el proceso para cada microservicio que necesites levantar.

---

## 🗄️ Base de Datos

El proyecto incluye el script completo de base de datos:

- 📄 **`perfulandia.sql`** — Script de creación de tablas e inserción de datos de prueba
- 📊 **`Base de datos (BASE - EV2).xlsx`** — Modelo y datos de referencia en Excel

---

## 📬 Colecciones Postman

El proyecto incluye **3 colecciones Postman** listas para usar:

| Archivo | Descripción |
|---|---|
| `Perfumeria con gateway.postman_collection.json` | Todas las peticiones enrutadas a través del API Gateway con JWT |
| `Perfumeria.postman_collection.json` | Colección base de endpoints de la perfumería |
| `Proyecto sin la gateway.postman_collection.json` | Peticiones directas a cada microservicio sin pasar por el gateway |

**Importar en Postman:**
1. Abre Postman
2. Haz clic en **Import**
3. Selecciona el archivo `.json` deseado
4. ¡Listo para probar! 🚀

---

## 📁 Estructura del Proyecto

```
perfulandia/
├── api-gateway-api-spring-boot/     # 🔀 API Gateway (JWT + Routing)
├── clientes-api-spring-boot/        # 👤 Microservicio Clientes
├── productos-api-spring-boot/       # 📦 Microservicio Productos
├── gestion-usuarios-api-spring-boot/# 🔐 Microservicio Usuarios
├── API-Venta/                       # 💰 Microservicio Ventas
├── API-Envios/                      # 🚚 Microservicio Envíos
├── API-Inventario/                  # 🗃️ Microservicio Inventario
├── API-Reportes/                    # 📊 Microservicio Reportes
├── API-Vendedor/                    # 🧑‍💼 Microservicio Vendedores
├── ApiSoporteTicket/                # 🎫 Microservicio Soporte
├── perfulandia.sql                  # 🗄️ Script base de datos
├── Diagrama de microservicios Perfulandia SPA.png
├── Perfumeria con gateway.postman_collection.json
├── Perfumeria.postman_collection.json
└── Proyecto sin la gateway.postman_collection.json
```

---

## 🔐 Autenticación

El sistema utiliza **JWT (JSON Web Token)** para proteger los endpoints. El flujo es:

1. Realiza un `POST /auth/login` con tus credenciales al API Gateway
2. Recibirás un token JWT válido por **24 horas**
3. Incluye el token en el header de cada petición:
   ```
   Authorization: Bearer <tu_token>
   ```

---

<div align="center">

**Desarrollado por [FelipeNorambuena](https://github.com/FelipeNorambuena)**

*DSY1103 — Desarrollo Full Stack I*

</div>

[![Ask DeepWiki](https://deepwiki.com/badge.svg)](https://deepwiki.com/FelipeNorambuena/perfulandia)
