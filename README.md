# Hr system backend

To use the system we require some dependencies and the environment variables.

## Postgres environment
Create a .env and put the environment variable in or use Intellij configuration.

```
POSTGRES_DB={db name}
POSTGRES_PASSWORD={db password}
POSTGRES_USER={db username}
```

## App environment
```
DB_PASSWORD={db password}
DB_URL=jdbc:postgresql://localhost:5431/{db name}
DB_USERNAME={db username}
ADMIN_ID={admin_employee_id}
ADMIN_PASSWORD={admin_employee_password}
CLOUDINARY_API_KEY={cloudinary_api_key}
CLOUDINARY_API_SECRET={cloudinary_api_secret}
CLOUDINARY_CLOUD={cloudinary_cloud}
JWT_SECRET_KEY= 512 key for jwt
```

## Run:
Use this to run only the db because it won't change as much.
```
docker compose up -d db
```

Use this to build and run new code if need both app and db to run.

```
docker compose up -d --build
```
