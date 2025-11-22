# catalog-service 🍷🧀

Microservice **Catalog Service** para la aplicación **Saveurs Maison**, encargado de gestionar el **catálogo de vinos y quesos**.  
Expone una API REST para que el BFF y el frontend (Ionic) puedan consultar y administrar el catálogo.

- Lenguaje: **Java 17**
- Framework: **Spring Boot 3.5.8**
- Build: **Maven**
- Persistencia: **Firebase Firestore**
- Documentación API: **springdoc-openapi** (Swagger UI) http://localhost:8081/swagger-ui.html
- Observabilidad: **Spring Boot Actuator**

---

## 🧱 Arquitectura (alto nivel)

Este microservicio forma parte de una arquitectura más grande:

`Ionic Frontend` → `API Gateway (AWS)` → `BFF (Spring Boot)` → `catalog-service (este MS)` → `Firestore (Firebase)`

El `catalog-service` expone endpoints REST para:

- Gestionar **vinos**
- Gestionar **quesos**

Toda la configuración de acceso a Firebase se hace vía `application.properties` y un archivo de credenciales **NO versionado** en Git.

---

## 🛠️ Tecnologías y dependencias principales

- `spring-boot-starter-web`
- `spring-boot-starter-validation`
- `spring-boot-starter-actuator`
- `springdoc-openapi-starter-webmvc-ui`
- `lombok`
- **Firebase / Firestore** vía:
  - `firebase-admin`
  - `google-cloud-firestore`

---

## 📂 Estructura de proyecto (resumen)

```text
catalog-service/
├─ src/
│  ├─ main/
│  │  ├─ java/com/saveurs/maison/catalog/
│  │  │  ├─ CatalogServiceApplication.java
│  │  │  ├─ config/
│  │  │  │  └─ FirebaseConfig.java
│  │  │  ├─ controller/
│  │  │  │  ├─ WineController.java
│  │  │  │  └─ CheeseController.java
│  │  │  ├─ domain/
│  │  │  │  ├─ model/
│  │  │  │  │  ├─ Wine.java
│  │  │  │  │  └─ Cheese.java
│  │  │  │  └─ enums/
│  │  │  │     ├─ WineType.java
│  │  │  │     ├─ WineFlavor.java
│  │  │  │     ├─ CheeseType.java
│  │  │  │     └─ CheeseFlavor.java
│  │  │  ├─ dto/
│  │  │  │  ├─ ApiResponse.java
│  │  │  │  ├─ WineDto.java
│  │  │  │  └─ CheeseDto.java
│  │  │  ├─ mapper/
│  │  │  │  ├─ WineMapper.java
│  │  │  │  └─ CheeseMapper.java
│  │  │  ├─ repository/
│  │  │  │  ├─ FirestoreCatalogRepository.java
│  │  │  │  └─ InMemoryCatalogRepository.java
│  │  │  ├─ service/
│  │  │  │  └─ CatalogService.java
│  │  │  └─ exception/
│  │  │     └─ NotFoundException.java
│  │  └─ resources/
│  │     ├─ application.properties
│  │     └─ (otros recursos)
│  └─ test/
│     └─ ...
├─ firebase/
│  └─ serviceAccountKey.json   (NO se sube a GitHub)
├─ pom.xml
├─ .gitignore
└─ README.md
