# Keycloak Setup Guide

## 1. Start Keycloak

```bash
docker-compose up -d
```

## 2. Access Keycloak Admin Console

- URL: http://localhost:8180
- Username: admin
- Password: admin

## 3. Create Realm

1. Click "Create Realm"
2. Name: `beautybar`
3. Click "Create"

## 4. Create Client

1. Go to "Clients" → "Create client"
2. Client ID: `beautybar-api`
3. Client type: `OpenID Connect`
4. Click "Next"
5. Enable "Client authentication"
6. Enable "Authorization"
7. Valid redirect URIs: `http://localhost:8080/*`
8. Click "Save"

## 5. Create Roles

1. Go to "Realm roles" → "Create role"
2. Create roles: `ADMIN`, `MANAGER`, `USER`

## 6. Create Users

1. Go to "Users" → "Create new user"
2. Create test users and assign roles in "Role mapping" tab

## 7. Test Token

```bash
curl -X POST http://localhost:8180/realms/beautybar/protocol/openid-connect/token \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -d "grant_type=password" \
  -d "client_id=beautybar-api" \
  -d "client_secret=YOUR_CLIENT_SECRET" \
  -d "username=YOUR_USERNAME" \
  -d "password=YOUR_PASSWORD"
```

## 8. Use Token in API Calls

```bash
curl -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  http://localhost:8080/beautybar/api/customers
```