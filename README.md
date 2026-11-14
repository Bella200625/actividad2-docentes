# Actividad 2 - Gestión de Docentes (Kodular + API REST)

Este repositorio corresponde a la actividad 2 del curso. Mi ejercicio asignado es el número 3 (Docentes).

La aplicación móvil está hecha en Kodular y permite administrar usuarios y docentes. A diferencia de la actividad 1, los datos ya no se guardan en el celular: la app se comunica con una API REST que hice en Java con Spring Boot, siguiendo la arquitectura hexagonal. La API está publicada en Render y usa una base de datos PostgreSQL alojada en Neon.

Dirección de la API: https://docentes-api.onrender.com

Nota: como Render está en el plan gratuito, la API se suspende cuando lleva un rato sin uso. La primera petición puede demorar alrededor de un minuto mientras se activa; después responde normal.

## Contenido del repositorio

- `src/`: código fuente del backend.
- `pom.xml`, `mvnw`, `mvnw.cmd`: configuración de Maven para compilar y ejecutar el proyecto.
- `Dockerfile`: lo usa Render para construir y ejecutar la API.
- `kodular/`: el proyecto de Kodular (.aia) y la aplicación instalable (.apk).

## Qué hace la aplicación

- Login: valida el usuario y la contraseña contra la API.
- Registro y CRUD de usuarios (username, password, nombre, email).
- CRUD de docentes (nombre, apellidos, email, teléfono, blog, profesional, escalafón, idioma, años de experiencia y área de trabajo).
- Recordatorio de clave: el usuario escribe su username, la app consulta su correo registrado y abre la aplicación de correo con el mensaje listo para enviar.
- Reportes con parámetros:
  - Usuarios cuyo nombre contiene un texto.
  - Usuarios cuyo email contiene un texto (por ejemplo "gmail").
  - Docentes de un área de trabajo.
  - Docentes con un mínimo de años de experiencia.
- Sensores:
  - Acelerómetro: al agitar el celular se limpian los campos del formulario (pantallas de Usuarios y Docentes).
  - Ubicación: muestra la latitud y longitud en la pantalla de Usuarios.

## Organización del backend (arquitectura hexagonal)

El código está dentro de `src/main/java/com/docentes/api` y se divide en tres partes:

- `dominio`: las clases `Docente` y `Usuario`, las excepciones y los puertos. Los puertos de entrada son las interfaces con las operaciones que se pueden pedir (crear, buscar, login, etc.) y los de salida son las interfaces que el dominio necesita para guardar la información. Esta capa no depende de Spring ni de la base de datos.
- `aplicacion`: los servicios `DocenteServicio` y `UsuarioServicio`, que implementan los casos de uso y validan los datos.
- `infraestructura`: los adaptadores. Los de entrada son los controladores REST que reciben las peticiones de la app, y los de salida usan JPA para guardar en la base de datos. En `config` se conectan los servicios con los adaptadores.

## Endpoints

Todos empiezan por `https://docentes-api.onrender.com/api`.

Usuarios:
- `POST /usuarios` crear
- `GET /usuarios` listar
- `GET /usuarios/{username}` buscar
- `PUT /usuarios/{username}` actualizar
- `DELETE /usuarios/{username}` eliminar
- `GET /usuarios/reportes/por-nombre?texto=...`
- `GET /usuarios/reportes/por-email?dominio=...`

Docentes:
- `POST /docentes` crear (el id lo asigna la base de datos)
- `GET /docentes` listar
- `GET /docentes/{id}` buscar
- `PUT /docentes/{id}` actualizar
- `DELETE /docentes/{id}` eliminar
- `GET /docentes/reportes/por-area?area=...`
- `GET /docentes/reportes/por-experiencia?min=...`

Login y recordatorio:
- `POST /auth/login` con `{"username": "...", "password": "..."}`
- `POST /auth/recordar` con `{"username": "..."}`

## Instalar la app en el celular

1. Descargar el archivo .apk que está en la carpeta `kodular`.
2. Abrirlo en un celular Android e instalarlo. Si aparece un aviso de seguridad, hay que permitir la instalación desde esa fuente.
3. En la pantalla de inicio, tocar "¿No tienes una cuenta?" para registrar un usuario.
4. Iniciar sesión con ese usuario. Desde el menú se entra a Usuarios, Docentes y Reportes.

Para ver o editar los bloques, se puede importar el archivo .aia en https://creator.kodular.io desde Proyecto > Importar proyecto.

## Ejecutar el backend localmente

Se necesita Java 21 o una versión más reciente y Git.

```
git clone https://github.com/Bella200625/actividad2-docentes.git
cd actividad2-docentes
./mvnw spring-boot:run
```

En Windows (PowerShell) el último comando es `.\mvnw spring-boot:run`.

Si no se configura ninguna base de datos, el proyecto usa H2 en memoria, que sirve para hacer pruebas (los datos se borran al apagar la aplicación). La API queda disponible en http://localhost:8080.

Para conectarlo a PostgreSQL hay que definir las variables de entorno `DB_URL`, `DB_USER` y `DB_PASSWORD`. Las tablas se crean automáticamente al iniciar.

## Despliegue

El servicio en Render está conectado a este repositorio, usa el Dockerfile y tiene configuradas las tres variables de la base de datos. Cada vez que se hace `git push` a la rama `main`, Render vuelve a desplegar la API automáticamente.
