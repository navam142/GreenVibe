# 🚀 GreenVibe Quick Start Guide

Get your GreenVibe e-commerce platform up and running in minutes!

## Prerequisites Checklist

- ✅ Java 17 or higher installed
- ✅ MySQL Server running
- ✅ Node.js 16+ and npm installed
- ✅ Git (optional, for version control)

## Step-by-Step Setup

### 1️⃣ Configure Database

Create a MySQL database and update your environment variables or `application.properties`:

```properties
# Option 1: Set environment variables
MYSQL_DB_USERNAME=root
MYSQL_DB_PASSWORD=your_password
MYSQL_DB_HOST=localhost
MYSQL_DB_PORT=3306
MYSQL_DB_NAME=greenvibe

# Option 2: Or directly in application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/greenvibe?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=your_password
```

### 2️⃣ Start the Backend

Open a terminal in the project root directory:

```bash
# On Windows (PowerShell)
.\mvnw.cmd spring-boot:run

# On Mac/Linux
./mvnw spring-boot:run
```

Wait for the message: `Started GreenVibeApplication in X seconds`

The backend will be running at: **http://localhost:8080**

### 3️⃣ Install Frontend Dependencies

Open a **new terminal** and navigate to the frontend directory:

```bash
cd frontend
npm install
```

If you encounter PowerShell execution policy issues on Windows, run:
```powershell
Set-ExecutionPolicy -ExecutionPolicy RemoteSigned -Scope CurrentUser
```

Or use Command Prompt instead of PowerShell.

### 4️⃣ Start the Frontend

In the same frontend directory:

```bash
npm run dev
```

The frontend will be running at: **http://localhost:5173**

### 5️⃣ Access the Application

Open your browser and navigate to:

```
http://localhost:5173
```

## 🎯 First Steps

### Create Your First User

1. Click **"Register"** in the navigation bar
2. Fill in the registration form:
   - Name: Your Name
   - Email: your@email.com
   - Phone: 1234567890
   - Address: Your address
   - Password: (minimum 6 characters)
3. Click **"Register"**
4. You'll be redirected to the login page

### Login

1. Click **"Login"** in the navigation bar
2. Enter your email and password
3. Click **"Login"**
4. You're now logged in!

### Add Products (Admin Task)

Since you're starting fresh, you'll need to add products via API or create an admin interface. For now, you can use tools like Postman or cURL:

```bash
# Example: Add a product using cURL
curl -X POST http://localhost:8080/api/products/add \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -F 'product={"name":"Monstera","description":"Beautiful plant","price":29.99,"stockQuantity":10,"categoryId":1}' \
  -F 'image=@/path/to/image.jpg'
```

### Create Categories First

Before adding products, create categories:

```bash
curl -X POST http://localhost:8080/api/category/create \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{"name":"Indoor Plants","description":"Plants for indoor use"}'
```

## 🛠️ Troubleshooting

### Backend Issues

**Problem:** Port 8080 already in use
```bash
# Find and kill the process using port 8080
# Windows
netstat -ano | findstr :8080
taskkill /PID <PID> /F

# Mac/Linux
lsof -ti:8080 | xargs kill -9
```

**Problem:** Database connection failed
- Verify MySQL is running
- Check username and password in application.properties
- Ensure database exists or `createDatabaseIfNotExist=true` is set

**Problem:** Cloudinary errors
- Set up Cloudinary account at https://cloudinary.com
- Add credentials to environment variables:
  ```
  CLOUDINARY_USERNAME=your_cloud_name
  CLOUDINARY_API_KEY=your_api_key
  CLOUDINARY_API_SECRET=your_api_secret
  ```

### Frontend Issues

**Problem:** npm install fails
- Clear npm cache: `npm cache clean --force`
- Delete `node_modules` and `package-lock.json`
- Run `npm install` again

**Problem:** CORS errors
- Ensure backend is running
- Check that backend has CORS configured for `http://localhost:5173`

**Problem:** API calls fail with 401
- Check if you're logged in
- Verify JWT token is being sent in requests
- Token might be expired (default: 10 days)

### Common Issues

**Problem:** "Cannot find module 'react-router-dom'"
```bash
cd frontend
npm install react-router-dom axios
```

**Problem:** Blank page after login
- Open browser console (F12) to check for errors
- Verify backend is responding to API calls
- Check network tab for failed requests

## 📊 Verify Everything Works

### Test Checklist

- [ ] Backend starts without errors
- [ ] Frontend loads at http://localhost:5173
- [ ] Can register a new user
- [ ] Can login with credentials
- [ ] Can view products page (even if empty)
- [ ] Navigation bar shows cart icon when logged in
- [ ] Can logout successfully

## 🎨 Default Configuration

### Backend
- **Port:** 8080
- **API Base:** /api
- **JWT Expiration:** 10 days
- **Database:** MySQL

### Frontend
- **Port:** 5173 (Vite default)
- **API URL:** http://localhost:8080/api
- **Build Tool:** Vite
- **Router:** React Router v7

## 📚 Next Steps

1. **Add Sample Data**
   - Create categories
   - Add products with images
   - Test the shopping flow

2. **Explore Features**
   - Browse products
   - Add items to cart
   - Complete checkout
   - View order confirmation

3. **Customize**
   - Update colors in CSS variables
   - Add your logo
   - Modify product categories
   - Add more features

## 🆘 Need Help?

### Documentation Files
- `FRONTEND_SETUP.md` - Detailed frontend architecture
- `frontend/README.md` - Frontend-specific documentation
- `frontend/API_REFERENCE.md` - Complete API documentation
- `readme.md` - Project overview

### Useful Commands

```bash
# Backend
./mvnw clean install          # Build project
./mvnw spring-boot:run        # Run backend
./mvnw test                   # Run tests

# Frontend
npm run dev                   # Start dev server
npm run build                 # Build for production
npm run preview               # Preview production build
npm run lint                  # Run linter
```

## 🎉 You're All Set!

Your GreenVibe e-commerce platform is now running. Start by:
1. Creating some product categories
2. Adding products
3. Testing the shopping experience

Happy coding! 🌿

---

**Pro Tip:** Keep both terminal windows open - one for backend, one for frontend. This makes it easy to see logs from both services.
