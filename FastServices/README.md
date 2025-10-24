# FastService

FastService is a smart queue and appointment management system built with Spring Boot and PostgreSQL. The system aims to reduce overcrowding and improve service efficiency in high-traffic environments such as banks, hospitals, and government offices.

## Problem

Traditional queuing and appointment systems often lead to:
- Long waiting times and overcrowding
- Inefficient resource allocation
- Lack of transparency in service delivery
- Exclusion of individuals without smartphones
- Poor customer experience and satisfaction

These issues are particularly prominent in sectors like healthcare, banking, and public services where large numbers of people need to access services daily.

## Solution (FastService)

FastService provides a comprehensive solution that digitizes and optimizes queue and appointment management. The system enables organizations to create service points (e.g., "Customer Service", "Cashier") and generate QR codes for each service. Customers can join queues by scanning these QR codes, while those without smartphones can check in through kiosks or with staff assistance. The platform ensures inclusive, transparent, and efficient service delivery for all users.

## Core Features

- **Organization Management**: Organizations can register and manage their service offerings
- **Service Points**: Create and manage different service points like "Customer Service" or "Cashier"
- **QR Code Generation**: Automatically generate QR codes for each service point
- **Queue Management**: Digital queuing system with real-time updates
- **Appointment Booking**: Schedule appointments in advance
- **Multi-Channel Access**: Smartphone scanning, kiosks, and staff-assisted check-in
- **User Management**: Admin panel for managing users and monitoring system performance
- **Real-time Notifications**: Email confirmations and status updates

## Tech Stack

- **Backend**: Spring Boot 3.5.6
- **Database**: PostgreSQL
- **Security**: Spring Security with JWT authentication
- **QR Code Generation**: ZXing library
- **Java Version**: Java 21
- **Build Tool**: Maven
- **Additional Libraries**: Lombok, ModelMapper, Hibernate Validator

## Setup and Configuration

### ⚠️ Security First!

**IMPORTANT:** Never commit secrets to version control!

1. Copy the environment template:
   ```bash
   cp .env.example .env
   ```

2. Edit `.env` with your actual credentials (this file is git-ignored)

3. See [SECURITY.md](file://c:UsersDELLDesktopFastServicesSECURITY.md) for detailed security guidelines

### Environment Variables

The application requires the following environment variables. See [`.env.example`](file://c:UsersDELLDesktopFastServicesFastServices..env.example) for the template:

```properties
# Database Configuration
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/fastservice
SPRING_DATASOURCE_USERNAME=your_db_username
SPRING_DATASOURCE_PASSWORD=your_secure_password_here

# JWT Configuration (Generate secure key: https://www.grc.com/passwords.htm)
APP_JWT_SECRET=your_jwt_secret_key_minimum_256_bits
APP_JWT_EXPIRATION_MILLISECONDS=604800000

# Email Configuration (Use Gmail App Password)
SMTP_HOST=smtp.gmail.com
SMTP_PORT=587
SMTP_USERNAME=your_email@gmail.com
SMTP_PASSWORD=your_gmail_app_password_here

# Server Configuration
SERVER_PORT=8080

# CORS Configuration
CORS_ALLOWED_ORIGINS=http://localhost:3000,http://localhost:5173
```

**📚 For Gmail App Password setup:**
1. Enable 2-Step Verification: https://myaccount.google.com/security
2. Generate App Password: https://myaccount.google.com/apppasswords
3. Use the 16-digit password in `SMTP_PASSWORD`

### Running the Application

1. **Setup PostgreSQL database:**
   ```sql
   CREATE DATABASE fastservice;
   ```

2. **Configure environment variables:**
   ```bash
   cp .env.example .env
   # Edit .env with your actual credentials
   ```

3. **Build and run:**
   ```bash
   ./mvnw clean install
   ./mvnw spring-boot:run
   ```

4. **Access the application:**
   - API: http://localhost:8080
   - Swagger UI: http://localhost:8080/swagger-ui.html

## API Documentation

Comprehensive API documentation is available:
- **API Endpoints:** [API_DOCUMENTATION.md](API_DOCUMENTATION.md)
- **Security Guidelines:** [SECURITY.md](file://c:UsersDELLDesktopFastServicesSECURITY.md)
- **Configuration Guide:** [CONFIGURATION_FIXES.md](file://c:UsersDELLDesktopFastServicesCONFIGURATION_FIXES.md)
- **Quick Start:** [QUICK_START.md](file://c:UsersDELLDesktopFastServicesQUICK_START.md)
- **Frontend/Backend Gap Analysis:** [FRONTEND_BACKEND_GAP_ANALYSIS.md](file://c:UsersDELLDesktopFastServicesFRONTEND_BACKEND_GAP_ANALYSIS.md)

## Impact

FastService transforms how organizations deliver services by:
- Reducing physical waiting times by up to 70%
- Improving customer satisfaction through transparent queuing
- Enabling better resource allocation and staff planning
- Providing inclusive access for all customers, regardless of technical capability
- Offering real-time insights into service performance
- Decreasing overcrowding in physical locations
- Streamlining operations for banks, hospitals, and government offices

The system ensures that service delivery is fair, efficient, and accessible to everyone, creating a better experience for both customers and service providers.