# GreenVibe Frontend 🌿

React frontend application for the GreenVibe e-commerce platform.

## 🏗️ Architecture

This frontend follows the **MVC (Model-View-Controller)** architecture pattern with **Context API** for state management:

### Structure

```
frontend/
├── src/
│   ├── config/          # API configuration
│   │   └── api.js       # Axios instance with interceptors
│   ├── context/         # Context API (State Management)
│   │   ├── AuthContext.jsx
│   │   └── CartContext.jsx
│   ├── services/        # API service layer (Model)
│   │   ├── authService.js
│   │   ├── cartService.js
│   │   ├── categoryService.js
│   │   ├── orderService.js
│   │   └── productService.js
│   ├── pages/           # Page components (View)
│   │   ├── Home.jsx
│   │   ├── Products.jsx
│   │   ├── ProductDetail.jsx
│   │   ├── Cart.jsx
│   │   ├── Checkout.jsx
│   │   ├── Login.jsx
│   │   ├── Register.jsx
│   │   └── OrderConfirmation.jsx
│   ├── components/      # Reusable components (View)
│   │   ├── Navbar.jsx
│   │   ├── Footer.jsx
│   │   ├── ProductCard.jsx
│   │   └── CartItem.jsx
│   ├── styles/          # CSS files
│   ├── App.jsx          # Main app component with routing
│   └── main.jsx         # Entry point
```

## ✨ Features

- **Authentication**: Login and registration with JWT token management
- **Product Catalog**: Browse products with category filtering and search
- **Shopping Cart**: Add, update, and remove items
- **Checkout**: Place orders with order confirmation
- **Responsive Design**: Mobile-friendly UI
- **Context API**: Global state management for auth and cart

## 🚀 Getting Started

### Prerequisites

- Node.js 16+ and npm
- Backend server running on `http://localhost:8080`

### Installation

1. **Install dependencies:**
   ```bash
   npm install
   ```

2. **Start the development server:**
   ```bash
   npm run dev
   ```

3. **Access the application:**
   Open your browser and navigate to `http://localhost:5173`

### Build for Production

```bash
npm run build
```

The production-ready files will be in the `dist/` directory.

## 🔧 Configuration

### API Base URL

The API base URL is configured in `src/config/api.js`:

```javascript
const API_BASE_URL = 'http://localhost:8080/api';
```

Update this if your backend runs on a different URL.

## 📡 API Integration

### Authentication

- **POST** `/api/auth/register` - Register new user
- **POST** `/api/auth/login` - Login user (returns JWT token)

### Products

- **GET** `/api/products/get` - Get all products
- **GET** `/api/products/get/:id` - Get product by ID

### Categories

- **GET** `/api/category/get` - Get all categories

### Cart

- **GET** `/api/cart/:customerId` - Get user's cart
- **POST** `/api/cart/:customerId/add` - Add item to cart
- **PUT** `/api/cart/:customerId/update` - Update cart item quantity
- **DELETE** `/api/cart/:customerId/remove/:productId` - Remove item from cart
- **DELETE** `/api/cart/:customerId/clear` - Clear cart

### Orders

- **POST** `/api/orders/place` - Place order
- **GET** `/api/orders/:orderId` - Get order details
- **PUT** `/api/orders/:orderId/cancel` - Cancel order

## 🎨 Styling

The application uses custom CSS with CSS variables for theming. Main color scheme:

- Primary: `#4caf50` (Green)
- Primary Dark: `#388e3c`
- Secondary: `#8bc34a`
- Error: `#f44336`

## 🔐 Authentication Flow

1. User registers or logs in
2. JWT token is stored in localStorage
3. Token is automatically added to all API requests via Axios interceptor
4. On 401 response, user is redirected to login page

## 📱 Responsive Design

The application is fully responsive with breakpoints at:
- Desktop: 1200px+
- Tablet: 768px - 1199px
- Mobile: < 768px

## 🛠️ Technologies Used

- **React 19** - UI library
- **React Router DOM** - Client-side routing
- **Axios** - HTTP client
- **Context API** - State management
- **Vite** - Build tool and dev server
- **CSS3** - Styling

## 📝 Notes

- Make sure the backend server is running before starting the frontend
- Default customer ID is set to 1 for demo purposes (can be updated after proper user management)
- JWT tokens expire after 10 days (configured in backend)

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Open a pull request

## 📄 License

This project is part of the GreenVibe e-commerce platform.
