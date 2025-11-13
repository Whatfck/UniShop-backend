# 🏗️ UniShop Backend

API REST del proyecto UniShop, desarrollada con Spring Boot (Java 21) para proporcionar servicios backend al marketplace universitario.

## 🚀 Tecnologías

- **Framework:** Spring Boot 3.5.0
- **Lenguaje:** Java 21
- **ORM:** Hibernate + Spring Data JPA
- **Base de Datos:** PostgreSQL
- **Seguridad:** Spring Security + JWT
- **Documentación:** Swagger/OpenAPI
- **Contenedor:** Docker

## 📋 Características

- ✅ API REST completa con autenticación JWT
- ✅ Gestión de usuarios y productos
- ✅ Sistema de categorías
- ✅ Validación de datos con Bean Validation
- ✅ CORS configurado para desarrollo y producción
- ✅ Health checks con Spring Boot Actuator
- ✅ Logging estructurado con SLF4J
- ✅ Manejo de errores centralizado

## 🔧 Configuración

### Variables de Entorno

Crear archivo `.env` basado en `.env.example`:

```bash
# Base de datos
DB_HOST=db
DB_PORT=5432
DB_NAME=unishop_db
DB_USERNAME=unishop_user
DB_PASSWORD=unishop_password

# JWT (opcional, tiene valores por defecto)
JWT_SECRET=your-secret-key
JWT_EXPIRATION=86400000
```

### Desarrollo Local

#### Opción 1: Con Docker (Recomendado)
```bash
# Desde raíz del proyecto
docker-compose up --build
```

#### Opción 2: Desarrollo Nativo
```bash
# Instalar dependencias
./mvnw install

# Asegurar PostgreSQL corriendo localmente
# Luego ejecutar
./mvnw spring-boot:run
```

## 🌐 Endpoints Principales

### Autenticación
- `POST /api/v1/auth/login` - Iniciar sesión
- `POST /api/v1/auth/register` - Registro de usuario

### Productos
- `GET /api/v1/products` - Listar productos
- `POST /api/v1/products` - Crear producto
- `GET /api/v1/products/{id}` - Detalles de producto
- `PUT /api/v1/products/{id}` - Actualizar producto
- `DELETE /api/v1/products/{id}` - Eliminar producto

### Categorías
- `GET /api/v1/categories` - Listar categorías

### Health Check
- `GET /actuator/health` - Estado del servicio

## 📖 Documentación API

### Swagger UI
Cuando el backend esté corriendo:
- **Local:** http://localhost:8080/swagger-ui.html
- **Docker:** http://localhost:8080/swagger-ui.html

### OpenAPI Spec
- **JSON:** http://localhost:8080/v3/api-docs
- **YAML:** http://localhost:8080/v3/api-docs.yaml

## 🏛️ Arquitectura

```
backend/
├── src/main/java/com/unishop/
│   ├── config/           # Configuración (CORS, Security, etc.)
│   ├── controller/       # Controladores REST
│   ├── dto/             # Data Transfer Objects
│   ├── entity/          # Entidades JPA
│   ├── repository/      # Repositorios de datos
│   ├── service/         # Lógica de negocio
│   └── UnishopApplication.java
├── src/main/resources/
│   └── application.properties
├── Dockerfile
├── pom.xml
└── .env.example
```

### Capas de Responsabilidad

1. **Controller:** Maneja requests HTTP, valida entrada, retorna responses
2. **Service:** Contiene lógica de negocio, orquesta operaciones
3. **Repository:** Abstrae acceso a datos, implementa consultas
4. **Entity:** Representa tablas de base de datos
5. **DTO:** Transfiere datos entre capas y cliente

## 🧪 Testing

```bash
# Ejecutar tests
./mvnw test

# Con reporte de cobertura
./mvnw test jacoco:report

# Tests específicos
./mvnw test -Dtest=AuthServiceTest
```

## 🔒 Seguridad

- **Autenticación:** JWT tokens con expiración configurable
- **Autorización:** Basada en roles (USER, ADMIN)
- **CORS:** Configurado para desarrollo y producción
- **Validación:** Bean Validation en DTOs
- **Rate Limiting:** Preparado para implementación

## 📊 Monitoreo

### Spring Boot Actuator
Endpoints disponibles en `/actuator/*`:
- `/health` - Estado del servicio
- `/info` - Información de la aplicación
- `/metrics` - Métricas de aplicación
- `/loggers` - Configuración de logging

### Logs
- **Formato:** JSON estructurado
- **Niveles:** INFO, WARN, ERROR
- **Archivo:** `logs/unishop.log`

## 🚀 Despliegue

### Producción
```bash
# Build
./mvnw clean package -DskipTests

# Ejecutar JAR
java -jar target/unishop-backend-0.0.1-SNAPSHOT.jar
```

### Docker
```bash
# Build imagen
docker build -t unishop-backend .

# Ejecutar contenedor
docker run -p 8080:8080 --env-file .env unishop-backend
```

## 🔧 Desarrollo

### Dependencias Principales
- `spring-boot-starter-web` - API REST
- `spring-boot-starter-data-jpa` - ORM
- `spring-boot-starter-security` - Seguridad
- `spring-boot-starter-validation` - Validación
- `jjwt-api` - JWT tokens
- `postgresql` - Driver PostgreSQL

### Scripts Útiles
```bash
# Limpiar y reconstruir
./mvnw clean install

# Ejecutar con perfil específico
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev

# Generar documentación
./mvnw spring-doc:generate
```

## 📋 Próximos Pasos

- [ ] Implementar más endpoints de productos
- [ ] Agregar sistema de favoritos
- [ ] Implementar notificaciones
- [ ] Añadir rate limiting
- [ ] Configurar CI/CD
- [ ] Añadir tests de integración
- [ ] Implementar caché Redis
- [ ] Configurar métricas avanzadas

## 👥 Contribución

1. Crear rama desde `main`
2. Implementar cambios
3. Agregar tests
4. Ejecutar `./mvnw test`
5. Crear Pull Request

## 📞 Soporte

Para issues relacionados con el backend, usar el repositorio correspondiente o contactar al equipo de desarrollo.