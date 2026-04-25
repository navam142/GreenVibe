# GreenVibe Frontend Setup Guide 🌿

## Overview

A complete React frontend has been created for your GreenVibe Spring Boot backend using **MVC architecture** and **Context API** for state management.

## 📁 Project Structure

```
frontend/
├── src/
│   ├── config/
│   │   └── api.js                    # Axios configuration with JWT interceptors
│   ├── context/                      # Context API (Global State)
│   │   ├── AuthContext.jsx           # Authentication state management
│   │   └── CartContext.jsx           # Shopping cart state management
│   ├── services/                     # API Services (Model Layer)
│   │   ├── authService.js            # Authentication API calls
│   │   ├── cartService.js            # Cart API calls
│   │   ├── categoryService.js        # Category API calls
│   │   ├── orderService.js           # Order API calls
│   │   └── productService.js         # Product API calls
│   ├── pages/                        # Page Components (View Layer)
│   │   ├── Home.jsx                  # Landing page
│   │   ├── Products.jsx              # Product listing with filters
│   │   ├── ProductDetail.jsx         # Single product view
│   │   ├── Cart.jsx                  # Shopping cart
│   │   ├── Checkout.jsx              # Checkout page
│   │   ├── Login.jsx                 # Login page
│   │   ├── Register.jsx              # Registration page
│   │   └── OrderConfirmation.jsx     # Order success page
│   ├── components/                   # Reusable Components
│   │   ├── Navbar.jsx                # Navigation bar
│   │   ├── Footer.jsx                # Footer
│   │   ├── ProductCard.jsx           # Product card component
│   │   └── CartItem.jsx              # Cart item component
│   ├── styles/                       # CSS Stylesheets
│   │   ├── Home.css
│   │   ├── Products.css
│   │   ├── ProductDetail.css
│   │   ├── ProductCard.css
│   │   ├── Cart.css
│   │   ├── CartItem.css
│   │   ├── Checkout.css
│   │   ├── OrderConfirmation.css
│   │   ├── Auth.css
│   │   ├── Navbar.css
│   │   └── Footer.css
│   ├── App.jsx                       # Main app with routing
│   ├── App.css                       # App-level styles
│   ├── main.jsx                      # Entry point
│   └── index.css                     # Global styles
```

## 🚀 Installation & Setup

### Step 1: Install Dependencies

Navigate to the frontend directory and install the required packages:

```bash
cd frontend
npm install react-router-dom axios
```

### Step 2: Start the Backend

Make sure your Spring Boot backend is running on `http://localhost:8080`:

```bash
cd ..
./mvnw spring-boot:run
```

### Step 3: Start the Frontend

In a new terminal, start the Vite development server:

```bash
cd frontend
npm run dev
```

The frontend will be available at `http://localhost:5173`

## 🏗️ Architecture Explanation

### MVC Pattern

**Model (Services Layer)**
- Located in `src/services/`
- Handles all API communication with the backend
- Uses Axios for HTTP requests
- Returns data to controllers/components

**View (Components & Pages)**
- Located in `src/pages/` and `src/components/`
- Renders UI based on state
- Handles user interactions
- Displays data from Context/Services

**Controller (Context API)**
- Located in `src/context/`
- Manages application state
- Coordinates between Model and View
- Provides state and actions to components

### Context API Structure

**AuthContext**
- Manages user authentication state
- Handles login, register, logout
- Stores JWT token in localStorage
- Provides authentication status to all components

**CartContext**
- Manages shopping cart state
- Handles add, update, remove cart items
- Fetches cart data from backend
- Provides cart count and total to components

## 🔌 API Integration

All API calls are configured to connect to your Spring Boot backend at `http://localhost:8080/api`.

### Endpoints Used

| Feature | Method | Endpoint | Description |
|---------|--------|----------|-------------|
| **Auth** | POST | `/auth/register` | Register new user |
| | POST | `/auth/login` | Login user |
| **Products** | GET | `/products/get` | Get all products |
| | GET | `/products/get/:id` | Get product by ID |
| **Categories** | GET | `/category/get` | Get all categories |
| **Cart** | GET | `/cart/:customerId` | Get user cart |
| | POST | `/cart/:customerId/add` | Add item to cart |
| | PUT | `/cart/:customerId/update` | Update cart item |
| | DELETE | `/cart/:customerId/remove/:productId` | Remove item |
| | DELETE | `/cart/:customerId/clear` | Clear cart |
| **Orders** | POST | `/orders/place` | Place order |
| | GET | `/orders/:orderId` | Get order details |
| | PUT | `/orders/:orderId/cancel` | Cancel order |

## ✨ Features Implemented

### 1. **User Authentication**
- Registration with validation
- Login with JWT token
- Automatic token management
- Protected routes

### 2. **Product Catalog**
- Product listing with grid layout
- Category filtering
- Search functionality
- Product detail view
- Stock availability display

### 3. **Shopping Cart**
- Add products to cart
- Update quantities
- Remove items
- Clear cart
- Real-time cart count in navbar
- Cart total calculation

### 4. **Checkout & Orders**
- Order summary
- Place order
- Order confirmation page
- Order details display

### 5. **Responsive Design**
- Mobile-friendly layout
- Tablet optimization
- Desktop experience

## 🎨 Styling

The application uses a green nature-themed color scheme:

```css
--primary-color: #4caf50;      /* Main green */
--primary-dark: #388e3c;       /* Dark green */
--primary-light: #81c784;      /* Light green */
--secondary-color: #8bc34a;    /* Secondary green */
```

## 🔐 Security Features

1. **JWT Token Management**
   - Stored in localStorage
   - Automatically added to requests
   - Auto-logout on 401 response

2. **Request Interceptors**
   - Adds Authorization header
   - Handles token expiration
   - Redirects to login on auth failure

## 📱 Responsive Breakpoints

- **Desktop**: 1200px and above
- **Tablet**: 768px - 1199px
- **Mobile**: Below 768px

## 🛠️ Development Tips

### Adding New Pages

1. Create component in `src/pages/`
2. Create corresponding CSS in `src/styles/`
3. Add route in `App.jsx`

### Adding New API Calls

1. Add service function in appropriate service file
2. Use the configured `api` instance from `config/api.js`
3. Handle errors appropriately

### Using Context

```jsx
import { useAuth } from './context/AuthContext';
import { useCart } from './context/CartContext';

function MyComponent() {
  const { user, isAuthenticated, login, logout } = useAuth();
  const { cart, addToCart, cartItemCount } = useCart();
  
  // Use the context values and functions
}
```

## 🐛 Troubleshooting

### CORS Issues
If you encounter CORS errors, ensure your Spring Boot backend has CORS configured:

```java
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:5173")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
```

### Port Already in Use
If port 5173 is in use, Vite will automatically try the next available port.

### Backend Connection Issues
Verify the backend is running on `http://localhost:8080` and check the API base URL in `src/config/api.js`.

## 📝 Next Steps

1. **Install dependencies**: Run `npm install react-router-dom axios` in the frontend directory
2. **Start backend**: Ensure Spring Boot is running
3. **Start frontend**: Run `npm run dev`
4. **Test the application**: Navigate to `http://localhost:5173`

## 🎯 Future Enhancements

Consider adding:
- User profile page
- Order history
- Product reviews
- Wishlist functionality
- Admin dashboard
- Payment integration
- Image upload for products
- Advanced search and filters
- Product recommendations

## 📚 Additional Resources

- [React Documentation](https://react.dev/)
- [React Router](https://reactrouter.com/)
- [Axios Documentation](https://axios-http.com/)
- [Vite Documentation](https://vitejs.dev/)

---

**Happy Coding! 🌿**
