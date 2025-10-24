# 🔐 Quick Admin Login Guide

## Super Admin Login

### Credentials
```
Email: admin@fastservices.com
Password: SuperAdmin@123
```

### Login via API
```javascript
fetch('http://localhost:8080/api/auth/login', {
  method: 'POST',
  headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify({
    email: 'admin@fastservices.com',
    password: 'SuperAdmin@123'
  })
})
.then(res => res.json())
.then(data => {
  console.log('Token:', data.accessToken);
  localStorage.setItem('token', data.accessToken);
});
```

### PowerShell Test
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/auth/login" `
  -Method Post `
  -ContentType "application/json" `
  -Body '{"email":"admin@fastservices.com","password":"SuperAdmin@123"}'
```

---

## What Super Admin Can Do

### ✅ Create Organization Admins
```http
POST /api/admin/org-admins
Authorization: Bearer {your_token}

{
  "firstName": "Jane",
  "lastName": "Smith",
  "email": "jane@hospital.com",
  "password": "AdminPass123",
  "phone": "+1234567890",
  "organizationId": "org-123"
}
```

### ✅ View All Users
```http
GET /api/users
Authorization: Bearer {your_token}
```

### ✅ Delete Users
```http
DELETE /api/users/{id}
Authorization: Bearer {your_token}
```

### ✅ Delete Organizations
```http
DELETE /api/organizations/{id}
Authorization: Bearer {your_token}
```

---

## Role Hierarchy

```
YOU ARE HERE → SUPER_ADMIN (Platform Owner)
                    ↓ can create
                ORG_ADMIN (Organization Admin)
                    ↓ can create
                STAFF (Organization Staff)
                    
                CUSTOMER (Public)
```

---

## Frontend Integration Essentials

### 1. Store Token After Login
```javascript
const { accessToken } = await loginResponse.json();
localStorage.setItem('token', accessToken);
```

### 2. Use Token in Requests
```javascript
headers: {
  'Authorization': `Bearer ${localStorage.getItem('token')}`,
  'Content-Type': 'application/json'
}
```

### 3. Check User Role
```javascript
// Decode JWT
const token = localStorage.getItem('token');
const payload = JSON.parse(atob(token.split('.')[1]));
const userRole = payload.roles; // e.g., "SUPER_ADMIN"
```

### 4. Show Admin UI
```javascript
{userRole === 'SUPER_ADMIN' && (
  <button onClick={createOrgAdmin}>Create Org Admin</button>
)}
```

---

## Quick Test Flow

1. **Login as Super Admin**
   - Email: `admin@fastservices.com`
   - Password: `SuperAdmin@123`

2. **Get Token from Response**
   - Save to localStorage

3. **Create Org Admin**
   - Use token in Authorization header
   - POST to `/api/admin/org-admins`

4. **Login as New Org Admin**
   - Use their email/password

5. **Create Staff**
   - Use org admin token
   - POST to `/api/admin/staff`

---

## Important URLs

- **Backend:** `http://localhost:8080`
- **Login:** `http://localhost:8080/api/auth/login`
- **Create Org Admin:** `http://localhost:8080/api/admin/org-admins`
- **Create Staff:** `http://localhost:8080/api/admin/staff`
- **Complete Guide:** [FRONTEND_INTEGRATION_COMPLETE.md](file://c:\Users\DELL\Desktop\FastServices\FRONTEND_INTEGRATION_COMPLETE.md)

---

## Need More Info?

📖 **Full Documentation:**
- [FRONTEND_INTEGRATION_COMPLETE.md](file://c:\Users\DELL\Desktop\FastServices\FRONTEND_INTEGRATION_COMPLETE.md) - Complete API reference
- [ROLE_HIERARCHY_IMPLEMENTATION.md](file://c:\Users\DELL\Desktop\FastServices\ROLE_HIERARCHY_IMPLEMENTATION.md) - Security details

🚀 **Backend Status:** ✅ Running on port 8080  
🔐 **Admin Login:** ✅ Ready to use  
📡 **API Endpoints:** ✅ All functional
