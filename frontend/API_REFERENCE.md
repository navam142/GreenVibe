# GreenVibe API Reference

Complete API documentation for the GreenVibe backend endpoints.

## Base URL

```
http://localhost:8080/api
```

## Authentication

Most endpoints require JWT authentication. Include the token in the Authorization header:

```
Authorization: Bearer <your-jwt-token>
```

---

## 🔐 Authentication Endpoints

### Register User

**POST** `/auth/register`

Register a new customer account.

**Request Body:**
```json
{
  "name": "John Doe",
  "email": "john@example.com",
  "password": "password123",
  "phoneNumber": "1234567890",
  "address": "123 Main St, City, State"
}
```

**Response:** `201 Created`
```json
{
  "id": 1,
  "name": "John Doe",
  "email": "john@example.com",
  "phoneNumber": "1234567890",
  "address": "123 Main St, City, State"
}
```

---

### Login

**POST** `/auth/login`

Authenticate user and receive JWT token.

**Request Body:**
```json
{
  "email": "john@example.com",
  "password": "password123"
}
```

**Response:** `200 OK`
```json
{
  "message": "Login successful",
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

---

## 🛍️ Product Endpoints

### Get All Products

**GET** `/products/get`

Retrieve all products.

**Response:** `200 OK`
```json
[
  {
    "id": 1,
    "name": "Monstera Deliciosa",
    "description": "Beautiful indoor plant",
    "price": 29.99,
    "stockQuantity": 15,
    "imageUrl": "https://example.com/image.jpg",
    "categoryName": "Indoor Plants"
  }
]
```

---

### Get Product by ID

**GET** `/products/get/{id}`

Retrieve a specific product.

**Response:** `200 OK`
```json
{
  "id": 1,
  "name": "Monstera Deliciosa",
  "description": "Beautiful indoor plant with large leaves",
  "price": 29.99,
  "stockQuantity": 15,
  "imageUrl": "https://example.com/image.jpg",
  "categoryName": "Indoor Plants"
}
```

---

### Add Product (Admin)

**POST** `/products/add`

Add a new product with image upload.

**Content-Type:** `multipart/form-data`

**Form Data:**
- `product` (JSON):
  ```json
  {
    "name": "Snake Plant",
    "description": "Low maintenance plant",
    "price": 19.99,
    "stockQuantity": 20,
    "categoryId": 1
  }
  ```
- `image` (File): Product image file

**Response:** `201 Created`
```json
{
  "id": 2,
  "name": "Snake Plant",
  "description": "Low maintenance plant",
  "price": 19.99,
  "stockQuantity": 20,
  "imageUrl": "https://cloudinary.com/...",
  "categoryName": "Indoor Plants"
}
```

---

### Delete Product (Admin)

**DELETE** `/products/delete/{id}`

Delete a product by ID.

**Response:** `200 OK`
```json
"Product deleted successfully"
```

---

## 📂 Category Endpoints

### Get All Categories

**GET** `/category/get`

Retrieve all product categories.

**Response:** `200 OK`
```json
[
  {
    "name": "Indoor Plants",
    "description": "Plants suitable for indoor environments"
  },
  {
    "name": "Outdoor Plants",
    "description": "Plants for gardens and outdoor spaces"
  }
]
```

---

### Get Category by ID

**GET** `/category/get/{id}`

Retrieve a specific category.

**Response:** `200 OK`
```json
{
  "name": "Indoor Plants",
  "description": "Plants suitable for indoor environments"
}
```

---

### Create Category (Admin)

**POST** `/category/create`

Create a new category.

**Request Body:**
```json
{
  "name": "Seeds",
  "description": "Various plant seeds"
}
```

**Response:** `201 Created`
```json
{
  "name": "Seeds",
  "description": "Various plant seeds"
}
```

---

### Delete Category (Admin)

**DELETE** `/category/delete/{id}`

Delete a category by ID.

**Response:** `200 OK`
```json
"Category deleted successfully"
```

---

## 🛒 Cart Endpoints

### Get Cart

**GET** `/cart/{customerId}`

Retrieve customer's shopping cart.

**Response:** `200 OK`
```json
{
  "cartId": 1,
  "customerId": 1,
  "totalAmount": 59.98,
  "totalItems": 2,
  "cartItems": [
    {
      "productId": 1,
      "productName": "Monstera Deliciosa",
      "price": 29.99,
      "quantity": 2,
      "subtotal": 59.98,
      "imageUrl": "https://example.com/image.jpg"
    }
  ]
}
```

---

### Add Item to Cart

**POST** `/cart/{customerId}/add`

Add a product to the cart.

**Query Parameters:**
- `productId` (Long): Product ID
- `quantity` (int): Quantity to add

**Example:**
```
POST /cart/1/add?productId=1&quantity=2
```

**Response:** `200 OK`
```json
{
  "cartId": 1,
  "customerId": 1,
  "totalAmount": 59.98,
  "totalItems": 2,
  "cartItems": [...]
}
```

---

### Update Cart Item

**PUT** `/cart/{customerId}/update`

Update quantity of a cart item.

**Query Parameters:**
- `productId` (Long): Product ID
- `quantity` (int): New quantity

**Example:**
```
PUT /cart/1/update?productId=1&quantity=3
```

**Response:** `200 OK`
```json
{
  "cartId": 1,
  "customerId": 1,
  "totalAmount": 89.97,
  "totalItems": 3,
  "cartItems": [...]
}
```

---

### Remove Item from Cart

**DELETE** `/cart/{customerId}/remove/{productId}`

Remove a specific item from cart.

**Response:** `200 OK`
```json
"Item removed from cart successfully"
```

---

### Clear Cart

**DELETE** `/cart/{customerId}/clear`

Remove all items from cart.

**Response:** `200 OK`
```json
"Cart cleared successfully"
```

---

## 📦 Order Endpoints

### Place Order

**POST** `/orders/place`

Convert current cart to an order.

**Response:** `200 OK`
```json
{
  "orderId": 1,
  "customerId": 1,
  "customerName": "John Doe",
  "orderDate": "2024-04-24T10:30:00",
  "totalAmount": 59.98,
  "orderStatus": "PENDING",
  "orderItems": [
    {
      "productId": 1,
      "productName": "Monstera Deliciosa",
      "price": 29.99,
      "quantity": 2
    }
  ]
}
```

---

### Get Order by ID

**GET** `/orders/{orderId}`

Retrieve order details.

**Response:** `200 OK`
```json
{
  "orderId": 1,
  "customerId": 1,
  "customerName": "John Doe",
  "orderDate": "2024-04-24T10:30:00",
  "totalAmount": 59.98,
  "orderStatus": "PENDING",
  "orderItems": [...]
}
```

---

### Cancel Order

**PUT** `/orders/{orderId}/cancel`

Cancel an existing order.

**Response:** `200 OK`
```json
{
  "orderId": 1,
  "customerId": 1,
  "customerName": "John Doe",
  "orderDate": "2024-04-24T10:30:00",
  "totalAmount": 59.98,
  "orderStatus": "CANCELLED",
  "orderItems": [...]
}
```

---

### Update Order Status (Admin)

**PUT** `/orders/admin/{orderId}/status`

Update order status.

**Query Parameters:**
- `orderStatus` (OrderStatus): New status

**Valid Status Values:**
- `PENDING`
- `PROCESSING`
- `SHIPPED`
- `DELIVERED`
- `CANCELLED`

**Example:**
```
PUT /orders/admin/1/status?orderStatus=SHIPPED
```

**Response:** `200 OK`
```json
"Order status updated successfully"
```

---

## 🚨 Error Responses

All endpoints may return error responses in the following format:

### 400 Bad Request
```json
{
  "message": "Invalid request data",
  "timestamp": "2024-04-24T10:30:00"
}
```

### 401 Unauthorized
```json
{
  "message": "Invalid credentials",
  "timestamp": "2024-04-24T10:30:00"
}
```

### 404 Not Found
```json
{
  "message": "Resource not found",
  "timestamp": "2024-04-24T10:30:00"
}
```

### 409 Conflict
```json
{
  "message": "Resource already exists",
  "timestamp": "2024-04-24T10:30:00"
}
```

### 500 Internal Server Error
```json
{
  "message": "An unexpected error occurred",
  "timestamp": "2024-04-24T10:30:00"
}
```

---

## 📝 Notes

1. **Authentication**: Most endpoints require JWT token in Authorization header
2. **Customer ID**: Currently hardcoded to 1 in frontend for demo purposes
3. **Image Upload**: Product images are uploaded to Cloudinary
4. **Date Format**: ISO 8601 format (e.g., `2024-04-24T10:30:00`)
5. **Decimal Precision**: Prices use BigDecimal with 2 decimal places

---

## 🔧 Testing with cURL

### Login Example
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"john@example.com","password":"password123"}'
```

### Get Products Example
```bash
curl -X GET http://localhost:8080/api/products/get \
  -H "Authorization: Bearer <your-token>"
```

### Add to Cart Example
```bash
curl -X POST "http://localhost:8080/api/cart/1/add?productId=1&quantity=2" \
  -H "Authorization: Bearer <your-token>"
```

---

**For more information, refer to the backend source code or contact the development team.**
