# Super Admin Functionality

## Overview

The Super Admin is the highest level user in the FastServices platform with full access to all system features. This document outlines the Super Admin capabilities and how to use them.

## Default Super Admin Credentials

Upon application startup, a default Super Admin user is automatically created with the following credentials:

```
Email: admin@fastservices.com
Password: SuperAdmin@123
```

> ⚠️ **Important**: Change this default password immediately after first login for security reasons.

## Super Admin Capabilities

### 1. User Management

Super Admin can:
- Create Organization Admins
- View all users in the system
- Delete any user account

#### Create Organization Admin
```http
POST /api/admin/org-admins
Authorization: Bearer {super_admin_token}

{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john@company.com",
  "password": "SecurePass123",
  "phone": "+1234567890"
}
```

#### Get All Users
```http
GET /api/admin/users
Authorization: Bearer {super_admin_token}
```

#### Delete User
```http
DELETE /api/admin/users/{user_id}
Authorization: Bearer {super_admin_token}
```

### 2. Organization Management

Super Admin can:
- View all organizations
- Delete any organization

### 3. System Administration

Super Admin can:
- Access all system data
- Perform administrative tasks
- Monitor platform usage

## Role Hierarchy

```
SUPER_ADMIN (Top level)
    ↓
ORG_ADMIN (Organization level)
    ↓
STAFF (Staff level)
    ↓
CUSTOMER (Public users)
```

## API Endpoints

| Endpoint | Method | Description | Role Required |
|----------|--------|-------------|---------------|
| `/api/admin/org-admins` | POST | Create Organization Admin | SUPER_ADMIN |
| `/api/admin/users` | GET | Get all users | SUPER_ADMIN |
| `/api/admin/users/{id}` | DELETE | Delete user | SUPER_ADMIN |
| `/api/admin/org-admins` | GET | Get all Organization Admins | SUPER_ADMIN |
| `/api/admin/staff` | GET | Get all Staff | ORG_ADMIN |

## Security Implementation

The Super Admin role is protected using Spring Security annotations:

```java
@PreAuthorize("hasRole('SUPER_ADMIN')")
```

JWT tokens are used for authentication, and role-based access control ensures only authorized users can access specific endpoints.

## Changing Password

After logging in with the default credentials, immediately change your password using the user profile update endpoint.

## Best Practices

1. **Security**: Store Super Admin credentials securely and never share them
2. **Access Control**: Limit Super Admin access to essential personnel only
3. **Monitoring**: Regularly review Super Admin activities for security purposes
4. **Password Rotation**: Change passwords regularly according to your security policy