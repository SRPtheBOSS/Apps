# Gym Management Mobile Application (Production-ready Blueprint)

## Monorepo Structure

```text
Apps/
├── backend/
│   ├── Dockerfile
│   ├── pom.xml
│   └── src/main/java/com/gym/app
│       ├── ai/
│       ├── config/
│       ├── controller/
│       ├── dto/
│       ├── entity/
│       ├── exception/
│       ├── repository/
│       ├── security/
│       └── service/
├── mobile/
│   ├── App.js
│   ├── package.json
│   └── src/{api,context,navigation,screens,storage}
├── docker-compose.yml
└── docs/
    └── architecture.md
```

## API Endpoints (sample)
- `POST /api/auth/register` (ADMIN only in production policy)
- `POST /api/auth/login`
- `POST /api/auth/refresh`
- `POST /api/auth/forgot-password`
- `POST /api/admin/assign-trainer/{memberId}/{trainerId}`
- `POST /api/admin/workout-plans`
- `POST /api/ai/workout`
- `POST /api/ai/diet`
- `POST /api/ai/chat`

## Sample Request / Response

`POST /api/auth/login`
```json
{ "email": "admin@gym.com", "password": "StrongPass#1" }
```

```json
{ "accessToken": "...", "refreshToken": "...", "tokenType": "Bearer" }
```

## Swagger
- URL: `http://localhost:8080/swagger-ui.html`

## Environment Variables
- `DB_URL`, `DB_USERNAME`, `DB_PASSWORD`
- `JWT_SECRET`
- `OPENAI_API_KEY`

## Frontend Highlights
- Role-based dashboard navigation
- JWT token storage with AsyncStorage
- Axios interceptor for Bearer auth
- AI chatbot, workout tracker, analytics/progress placeholders ready for API binding

## Optional enterprise extensions to add
- Redis cache, Spring Batch monthly reports, push/email workers, QR check-in microservice, integration tests.
