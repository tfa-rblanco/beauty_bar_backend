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
2. Name: `beauty-bar-client-admin`
3. Click "Create"

## 4. Create Clients

### Admin Client
1. Go to "Clients" → "Create client"
2. Client ID: `beauty-bar-client-admin`
3. Client type: `OpenID Connect`
4. Click "Next"
5. Enable "Client authentication"
6. Valid redirect URIs: `http://localhost:8080/*`
7. Click "Save"
8. Go to "Credentials" tab and note the client secret: `ZPzvufTbOqS1w4dtBGP0KEzFM0KWMKIx`

### User Client
1. Create another client with ID: `beauty-bar-client-users`
2. Client secret: `oCMwn2FXE6VcC7WKXxaeNeWm3IECPZ2d`

## 5. Create Client Scopes

1. Go to "Client scopes" → "Create client scope"
2. Create scopes: `bb-admin`, `bb-user-authenticated`, `bb-user`
3. Assign appropriate scopes to clients

## 6. Create Roles

1. Go to "Realm roles" → "Create role"
2. Create roles: `ADMIN`, `MANAGER`, `USER`

## 7. Create Users

1. Go to "Users" → "Create new user"
2. Create test users and assign roles in "Role mapping" tab

## 8. Test Token (Admin)

```bash
curl -X POST http://localhost:8180/realms/beauty-bar-realm/protocol/openid-connect/token \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -d "grant_type=password" \
  -d "client_id=beaty-bar-client-admin" \
  -d "client_secret=ZPzvufTbOqS1w4dtBGP0KEzFM0KWMKIx" \
  -d "username=YOUR_USERNAME" \
  -d "password=YOUR_PASSWORD"
```

## 9. Test Token (User)

```bash
curl -X POST http://localhost:8180/realms/beauty-bar-realm/protocol/openid-connect/token \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -d "grant_type=password" \
  -d "client_id=beaty-bar-client-users" \
  -d "client_secret=oCMwn2FXE6VcC7WKXxaeNeWm3IECPZ2d" \
  -d "username=YOUR_USERNAME" \
  -d "password=YOUR_PASSWORD"
```

## 10. Use Token in API Calls

```bash
curl -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  http://localhost:8080/beautybar/api/customers
```