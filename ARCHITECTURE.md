# 🏗️ GreenVibe Architecture Documentation

## System Overview

GreenVibe is a full-stack e-commerce platform built with Spring Boot (backend) and React (frontend), following modern architectural patterns and best practices.

## High-Level Architecture

```
┌─────────────────────────────────────────────────────────────┐
│                         FRONTEND                             │
│                    (React + Vite)                            │
│                   Port: 5173                                 │
├─────────────────────────────────────────────────────────────┤
│                                                              │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐     │
│  │   Context    │  │     Pages    │  │  Components  │     │
│  │   (State)    │  │    (Views)   │  │   (Reusable) │     │
│  └──────────────┘  └──────────────┘  └──────────────┘     │
│         │                  │                  │             │
│         └──────────────────┴──────────────────┘             │
│                            │                                │
│                   ┌────────▼────────┐                       │
│                   │    Services     │                       │
│                   │    (API Layer)  │                       │
│                   └────────┬────────┘                       │
│                            │                                │
└────────────────────────────┼────────────────────────────────┘
                             │
                    HTTP/REST (JSON)
                    JWT Authentication
                             │
┌────────────────────────────▼────────────────────────────────┐
│                         BACKEND                              │
│                   (Spring Boot)                              │
│                     Port: 8080                               │
├─────────────────────────────────────────────────────────────┤
│                                                              │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐     │
│  │ Controllers  │  │   Services   │  │ Repositories │     │
│  │  (REST API)  │  │  (Business)  │  │   (Data)     │     │
│  └──────┬───────┘  └──────┬───────┘  └──────┬───────┘     │
│         │                  │                  │             │
│         └──────────────────┴──────────────────┘             │
│                            │                                │
│         ┌──────────────────┴──────────────────┐            │
│         │                                      │            │
│    ┌────▼─────┐                         ┌─────▼─────┐     │
│    │ Security │                         │  Entities │     │
│    │   (JWT)  │                         │   (JPA)   │     │
│    └──────────┘                         └─────┬─────┘     │
│                                               │            │
└───────────────────────────────────────────────┼────────────┘
                                                │
                                         JPA/Hibernate
                                                │
                                         ┌──────▼──────┐
                                         │    MySQL    │
                                         │  Database   │
                                         └─────────────┘
```

## Frontend Architecture (MVC + Context API)

### Directory Structure

```
frontend/src/
│
├── config/                    # Configuration
│   └── api.js                # Axios instance, interceptors
│
├── context/                   # Context API (Controller)
│   ├── AuthContext.jsx       # Authentication state
│   └── CartContext.jsx       # Shopping cart state
│
├── services/                  # API Services (Model)
│   ├── authService.js        # Auth API calls
│   ├── cartService.js        # Cart API calls
│   ├── categoryService.js    # Category API calls
│   ├── orderService.js       # Order API calls
│   └── productService.js     # Product API calls
│
├── pages/                     # Page Components (View)
│   ├── Home.jsx              # Landing page
│   ├── Products.jsx          # Product listing
│   ├── ProductDetail.jsx     # Product details
│   ├── Cart.jsx              # Shopping cart
│   ├── Checkout.jsx          # Checkout
│   ├── Login.jsx             # Login
│   ├── Register.jsx          # Registration
│   └── OrderConfirmation.jsx # Order success
│
├── components/                # Reusable Components (View)
│   ├── Navbar.jsx            # Navigation
│   ├── Footer.jsx            # Footer
│   ├── ProductCard.jsx       # Product card
│   └── CartItem.jsx          # Cart item
│
├── styles/                    # CSS Stylesheets
│   └── *.css                 # Component styles
│
├── App.jsx                    # Main app + routing
├── App.css                    # App styles
├── main.jsx                   # Entry point
└── index.css                  # Global styles
```

### Data Flow

```
┌─────────────────────────────────────────────────────────┐
│                    USER INTERACTION                      │
└────────────────────┬────────────────────────────────────┘
                     │
                     ▼
┌─────────────────────────────────────────────────────────┐
│                  VIEW (Components)                       │
│  • Renders UI based on state                            │
│  • Handles user events (clicks, inputs)                 │
│  • Displays data from Context                           │
└────────────────────┬────────────────────────────────────┘
                     │
                     ▼
┌─────────────────────────────────────────────────────────┐
│              CONTROLLER (Context API)                    │
│  • Manages application state                            │
│  • Coordinates between View and Model                   │
│  • Provides state and actions to components             │
└────────────────────┬────────────────────────────────────┘
                     │
                     ▼
┌─────────────────────────────────────────────────────────┐
│                MODEL (Services)                          │
│  • Makes API calls to backend                           │
│  • Handles data transformation                          │
│  • Returns data to Controller                           │
└────────────────────┬────────────────────────────────────┘
                     │
                     ▼
              Backend REST API
```

## Backend Architecture (Spring Boot)

### Layer Structure

```
src/main/java/com/app/greenvibe/
│
├── controller/                # REST Controllers
│   ├── CartController.java
│   ├── CategoryController.java
│   ├── CustomerController.java
│   ├── OrderController.java
│   └── ProductController.java
│
├── service/                   # Business Logic
│   ├── CartService.java
│   ├── CategoryService.java
│   ├── CustomerService.java
│   ├── OrderService.java
│   └── ProductService.java
│
├── repository/                # Data Access (JPA)
│   ├── CartRepository.java
│   ├── CategoryRepository.java
│   ├── CustomerRepository.java
│   ├── OrderRepository.java
│   └── ProductRepository.java
│
├── entity/                    # JPA Entities
│   ├── Cart.java
│   ├── CartItem.java
│   ├── Category.java
│   ├── Customer.java
│   ├── Order.java
│   ├── OrderItem.java
│   └── Product.java
│
├── dto/                       # Data Transfer Objects
│   ├── request/              # Request DTOs
│   └── response/             # Response DTOs
│
├── security/                  # Security Configuration
│   ├── JwtService.java
│   └── JwtAuthenticationFilter.java
│
├── config/                    # Configuration
│   ├── SecurityConfig.java
│   └── CloudinaryConfig.java
│
├── exception/                 # Exception Handling
│   └── GlobalExceptionHandler.java
│
└── mapper/                    # Entity-DTO Mappers
    ├── CartMapper.java
    ├── CategoryMapper.java
    └── ProductMapper.java
```

### Request Flow

```
┌─────────────────────────────────────────────────────────┐
│                    HTTP REQUEST                          │
│              (from Frontend/Client)                      │
└────────────────────┬────────────────────────────────────┘
                     │
                     ▼
┌─────────────────────────────────────────────────────────┐
│              Security Filter Chain                       │
│  • JWT Authentication Filter                            │
│  • Validates token                                      │
│  • Sets SecurityContext                                 │
└────────────────────┬────────────────────────────────────┘
                     │
                     ▼
┌─────────────────────────────────────────────────────────┐
│                  CONTROLLER                              │
│  • Receives HTTP request                                │
│  • Validates input                                      │
│  • Calls service layer                                  │
│  • Returns HTTP response                                │
└────────────────────┬────────────────────────────────────┘
                     │
                     ▼
┌─────────────────────────────────────────────────────────┐
│                   SERVICE                                │
│  • Business logic                                       │
│  • Transaction management                               │
│  • Calls repository layer                               │
│  • Maps entities to DTOs                                │
└────────────────────┬────────────────────────────────────┘
                     │
                     ▼
┌─────────────────────────────────────────────────────────┐
│                 REPOSITORY                               │
│  • Data access via JPA                                  │
│  • CRUD operations                                      │
│  • Custom queries                                       │
└────────────────────┬────────────────────────────────────┘
                     │
                     ▼
┌─────────────────────────────────────────────────────────┐
│                   DATABASE                               │
│                    (MySQL)                               │
└─────────────────────────────────────────────────────────┘
```

## Database Schema

```
┌─────────────────┐
│    Customer     │
├─────────────────┤
│ id (PK)         │
│ name            │
│ email (unique)  │
│ password        │
│ phoneNumber     │
│ address         │
└────────┬────────┘
         │
         │ 1:1
         │
┌────────▼────────┐
│      Cart       │
├─────────────────┤
│ id (PK)         │
│ customer_id(FK) │
│ totalAmount     │
│ totalItems      │
└────────┬────────┘
         │
         │ 1:N
         │
┌────────▼────────┐       ┌─────────────────┐
│   CartItem      │   N:1 │    Product      │
├─────────────────┤◄──────┤─────────────────┤
│ id (PK)         │       │ id (PK)         │
│ cart_id (FK)    │       │ name            │
│ product_id (FK) │       │ description     │
│ quantity        │       │ price           │
│ subtotal        │       │ stockQuantity   │
└─────────────────┘       │ imageUrl        │
                          │ category_id(FK) │
                          └────────┬────────┘
                                   │
                                   │ N:1
                                   │
                          ┌────────▼────────┐
                          │    Category     │
                          ├─────────────────┤
                          │ id (PK)         │
                          │ name            │
                          │ description     │
                          └─────────────────┘

┌─────────────────┐
│     Order       │
├─────────────────┤
│ id (PK)         │
│ customer_id(FK) │
│ orderDate       │
│ totalAmount     │
│ orderStatus     │
└────────┬────────┘
         │
         │ 1:N
         │
┌────────▼────────┐
│   OrderItem     │
├─────────────────┤
│ id (PK)         │
│ order_id (FK)   │
│ product_id      │
│ productName     │
│ quantity        │
│ price           │
│ subtotal        │
└─────────────────┘
```

## Authentication Flow

```
┌──────────┐                                    ┌──────────┐
│  Client  │                                    │  Server  │
└────┬─────┘                                    └────┬─────┘
     │                                               │
     │  1. POST /api/auth/login                     │
     │  { email, password }                         │
     ├──────────────────────────────────────────────►
     │                                               │
     │                    2. Validate credentials   │
     │                       Generate JWT token     │
     │                                               │
     │  3. Return JWT token                         │
     ◄──────────────────────────────────────────────┤
     │  { token: "eyJhbG..." }                      │
     │                                               │
     │  4. Store token in localStorage              │
     │                                               │
     │  5. Subsequent requests                      │
     │  Authorization: Bearer <token>               │
     ├──────────────────────────────────────────────►
     │                                               │
     │                    6. Validate JWT           │
     │                       Extract user info      │
     │                                               │
     │  7. Return protected resource                │
     ◄──────────────────────────────────────────────┤
     │                                               │
```

## State Management (Context API)

### AuthContext

```javascript
AuthContext
├── State
│   ├── user (object)
│   ├── token (string)
│   └── loading (boolean)
│
└── Actions
    ├── login(email, password)
    ├── register(userData)
    ├── logout()
    └── isAuthenticated (computed)
```

### CartContext

```javascript
CartContext
├── State
│   ├── cart (object)
│   ├── loading (boolean)
│   ├── cartItemCount (computed)
│   └── cartTotal (computed)
│
└── Actions
    ├── fetchCart()
    ├── addToCart(productId, quantity)
    ├── updateCartItem(productId, quantity)
    ├── removeFromCart(productId)
    └── clearCart()
```

## API Communication

### Request/Response Cycle

```
Frontend Component
       │
       ▼
Context Hook (useCart, useAuth)
       │
       ▼
Service Function (cartService.addItem)
       │
       ▼
Axios Instance (with interceptors)
       │
       ├─► Add JWT token to headers
       ├─► Set Content-Type
       └─► Handle errors
       │
       ▼
HTTP Request to Backend
       │
       ▼
Spring Boot Controller
       │
       ▼
Service Layer
       │
       ▼
Repository Layer
       │
       ▼
Database
       │
       ▼
Response flows back up the chain
```

## Security Architecture

### JWT Token Structure

```
Header
{
  "alg": "HS256",
  "typ": "JWT"
}

Payload
{
  "sub": "user@example.com",
  "iat": 1619000000,
  "exp": 1619864000
}

Signature
HMACSHA256(
  base64UrlEncode(header) + "." +
  base64UrlEncode(payload),
  secret
)
```

### Security Filter Chain

```
HTTP Request
     │
     ▼
┌─────────────────────────────┐
│  CORS Filter                │
└────────────┬────────────────┘
             │
             ▼
┌─────────────────────────────┐
│  JWT Authentication Filter  │
│  • Extract token            │
│  • Validate token           │
│  • Set SecurityContext      │
└────────────┬────────────────┘
             │
             ▼
┌─────────────────────────────┐
│  Authorization Check        │
│  • Check user roles         │
│  • Verify permissions       │
└────────────┬────────────────┘
             │
             ▼
        Controller
```

## Deployment Architecture

### Development

```
┌─────────────────┐         ┌─────────────────┐
│   Frontend      │         │    Backend      │
│   localhost     │◄───────►│   localhost     │
│   :5173         │         │   :8080         │
└─────────────────┘         └────────┬────────┘
                                     │
                            ┌────────▼────────┐
                            │  MySQL Local    │
                            │  :3306          │
                            └─────────────────┘
```

### Production (Suggested)

```
┌─────────────────────────────────────────┐
│           Load Balancer / CDN           │
└────────────────┬────────────────────────┘
                 │
        ┌────────┴────────┐
        │                 │
┌───────▼──────┐  ┌───────▼──────┐
│   Frontend   │  │   Backend    │
│   (Static)   │  │   (Spring)   │
│   Nginx/S3   │  │   EC2/Docker │
└──────────────┘  └───────┬──────┘
                          │
                  ┌───────▼──────┐
                  │   Database   │
                  │   RDS/MySQL  │
                  └──────────────┘
```

## Technology Stack Summary

### Frontend
- **Framework:** React 19
- **Build Tool:** Vite
- **Routing:** React Router DOM v7
- **HTTP Client:** Axios
- **State Management:** Context API
- **Styling:** CSS3 with CSS Variables

### Backend
- **Framework:** Spring Boot 3.x
- **Language:** Java 17
- **Security:** Spring Security + JWT
- **ORM:** Spring Data JPA (Hibernate)
- **Database:** MySQL
- **Image Storage:** Cloudinary
- **Build Tool:** Maven

## Performance Considerations

### Frontend Optimizations
- Code splitting with React.lazy()
- Image lazy loading
- Memoization with useMemo/useCallback
- Debounced search inputs
- Optimistic UI updates

### Backend Optimizations
- Database indexing on frequently queried fields
- Connection pooling
- Caching with Spring Cache
- Lazy loading for JPA relationships
- Pagination for large datasets

---

**This architecture provides a solid foundation for a scalable, maintainable e-commerce platform.** 🌿
