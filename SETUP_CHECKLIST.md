# ✅ GreenVibe Setup Checklist

Use this checklist to ensure everything is set up correctly.

## 📋 Pre-Setup Checklist

### System Requirements
- [ ] Java 17 or higher installed
  ```bash
  java -version
  ```
- [ ] Node.js 16+ installed
  ```bash
  node --version
  ```
- [ ] npm installed
  ```bash
  npm --version
  ```
- [ ] MySQL Server installed and running
  ```bash
  # Check if MySQL is running
  # Windows: services.msc
  # Mac: brew services list
  # Linux: systemctl status mysql
  ```

## 🗄️ Database Setup

- [ ] MySQL server is running
- [ ] Database created (or auto-create enabled)
- [ ] Database credentials configured
  - [ ] Username set in environment or application.properties
  - [ ] Password set in environment or application.properties
  - [ ] Database name configured
  - [ ] Port configured (default: 3306)

### Environment Variables (Optional but Recommended)
```bash
# Add these to your system environment variables
MYSQL_DB_USERNAME=root
MYSQL_DB_PASSWORD=your_password
MYSQL_DB_HOST=localhost
MYSQL_DB_PORT=3306
MYSQL_DB_NAME=greenvibe
```

## 🔧 Backend Setup

- [ ] Navigate to project root directory
- [ ] Review `application.properties` configuration
- [ ] Set up Cloudinary credentials (for image upload)
  ```bash
  CLOUDINARY_USERNAME=your_cloud_name
  CLOUDINARY_API_KEY=your_api_key
  CLOUDINARY_API_SECRET=your_api_secret
  ```
- [ ] Build the project
  ```bash
  ./mvnw clean install
  ```
- [ ] Start the backend server
  ```bash
  ./mvnw spring-boot:run
  ```
- [ ] Verify backend is running
  - [ ] Check console for "Started GreenVibeApplication"
  - [ ] No error messages in console
  - [ ] Server running on port 8080

### Backend Health Check
- [ ] Open browser to `http://localhost:8080`
- [ ] Should see Whitelabel Error Page (this is normal - means server is running)

## 💻 Frontend Setup

- [ ] Open new terminal window
- [ ] Navigate to frontend directory
  ```bash
  cd frontend
  ```
- [ ] Install dependencies
  ```bash
  npm install
  ```
- [ ] Install additional required packages
  ```bash
  npm install react-router-dom axios
  ```
- [ ] Verify package.json has all dependencies
  - [ ] react
  - [ ] react-dom
  - [ ] react-router-dom
  - [ ] axios
- [ ] Start development server
  ```bash
  npm run dev
  ```
- [ ] Note the URL (usually `http://localhost:5173`)

### Frontend Health Check
- [ ] Open browser to `http://localhost:5173`
- [ ] Home page loads successfully
- [ ] No console errors (F12 → Console)
- [ ] Navigation bar visible
- [ ] Footer visible

## 🧪 Functionality Testing

### 1. User Registration
- [ ] Click "Register" in navigation
- [ ] Fill in registration form
  - [ ] Name: Test User
  - [ ] Email: test@example.com
  - [ ] Phone: 1234567890
  - [ ] Address: 123 Test St
  - [ ] Password: test123
  - [ ] Confirm Password: test123
- [ ] Click "Register" button
- [ ] Should redirect to login page
- [ ] Success message displayed

### 2. User Login
- [ ] Click "Login" in navigation
- [ ] Enter credentials
  - [ ] Email: test@example.com
  - [ ] Password: test123
- [ ] Click "Login" button
- [ ] Should redirect to home page
- [ ] User greeting visible in navbar
- [ ] Cart icon visible in navbar

### 3. Navigation
- [ ] Click "Home" - loads home page
- [ ] Click "Products" - loads products page
- [ ] Click "Cart" - loads cart page (empty initially)
- [ ] All pages load without errors

### 4. Add Sample Data (Using API)

#### Create Categories
```bash
# Get your JWT token from login response
TOKEN="your_jwt_token_here"

# Create Indoor Plants category
curl -X POST http://localhost:8080/api/category/create \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{"name":"Indoor Plants","description":"Plants for indoor use"}'

# Create Outdoor Plants category
curl -X POST http://localhost:8080/api/category/create \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{"name":"Outdoor Plants","description":"Plants for outdoor use"}'

# Create Seeds category
curl -X POST http://localhost:8080/api/category/create \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{"name":"Seeds","description":"Various plant seeds"}'

# Create Planters category
curl -X POST http://localhost:8080/api/category/create \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{"name":"Planters","description":"Pots and planters"}'
```

- [ ] Categories created successfully

#### Add Sample Products (Requires Postman or similar tool)
Use Postman to add products with images:
- [ ] Create at least 3-5 sample products
- [ ] Each product has:
  - [ ] Name
  - [ ] Description
  - [ ] Price
  - [ ] Stock quantity
  - [ ] Category ID
  - [ ] Image file

### 5. Product Browsing
- [ ] Navigate to Products page
- [ ] Products are displayed
- [ ] Can filter by category
- [ ] Search functionality works
- [ ] Click on a product card
- [ ] Product detail page loads
- [ ] Product information displayed correctly

### 6. Shopping Cart
- [ ] From product detail page, click "Add to Cart"
- [ ] Success message appears
- [ ] Cart badge shows item count
- [ ] Navigate to Cart page
- [ ] Product appears in cart
- [ ] Can update quantity
- [ ] Can remove item
- [ ] Cart total updates correctly

### 7. Checkout Process
- [ ] Add items to cart
- [ ] Click "Proceed to Checkout"
- [ ] Order summary displayed
- [ ] Click "Place Order"
- [ ] Order confirmation page appears
- [ ] Order ID displayed
- [ ] Order details shown

### 8. Logout
- [ ] Click "Logout" button
- [ ] Redirected to home page
- [ ] Login/Register buttons visible again
- [ ] Cart icon hidden

## 🎨 Visual Verification

### Desktop View (1200px+)
- [ ] Layout looks good
- [ ] Images display properly
- [ ] Navigation is clear
- [ ] Footer is visible
- [ ] No horizontal scrolling

### Tablet View (768px - 1199px)
- [ ] Open browser DevTools (F12)
- [ ] Toggle device toolbar
- [ ] Select iPad or similar
- [ ] Layout adjusts properly
- [ ] All features accessible

### Mobile View (< 768px)
- [ ] Select iPhone or similar
- [ ] Layout is mobile-friendly
- [ ] Navigation works
- [ ] Buttons are touch-friendly
- [ ] Text is readable

## 🔍 Browser Console Check

### No Errors
- [ ] Open DevTools (F12)
- [ ] Check Console tab
- [ ] No red error messages
- [ ] No 404 errors
- [ ] No CORS errors

### Network Tab
- [ ] API calls succeed (200 status)
- [ ] JWT token sent in requests
- [ ] Responses are valid JSON

## 📱 Cross-Browser Testing

- [ ] Chrome - works correctly
- [ ] Firefox - works correctly
- [ ] Safari - works correctly (Mac only)
- [ ] Edge - works correctly

## 🚀 Performance Check

- [ ] Pages load quickly (< 2 seconds)
- [ ] Images load properly
- [ ] No lag when typing
- [ ] Smooth scrolling
- [ ] Responsive interactions

## 📚 Documentation Review

- [ ] Read QUICK_START.md
- [ ] Read FRONTEND_SETUP.md
- [ ] Bookmark API_REFERENCE.md
- [ ] Review ARCHITECTURE.md
- [ ] Check FRONTEND_SUMMARY.md

## 🔐 Security Verification

- [ ] JWT token stored in localStorage
- [ ] Token sent with API requests
- [ ] Protected routes require authentication
- [ ] Logout clears token
- [ ] 401 errors redirect to login

## 🐛 Common Issues Resolved

### Backend Issues
- [ ] Port 8080 not in use
- [ ] Database connection successful
- [ ] No compilation errors
- [ ] Cloudinary configured (or disabled for testing)

### Frontend Issues
- [ ] Port 5173 available
- [ ] All dependencies installed
- [ ] No npm errors
- [ ] API base URL correct

### Integration Issues
- [ ] CORS configured properly
- [ ] Backend and frontend can communicate
- [ ] JWT authentication works
- [ ] API responses are correct

## ✨ Optional Enhancements

- [ ] Add custom logo
- [ ] Change color scheme
- [ ] Add more product categories
- [ ] Customize footer links
- [ ] Add social media links
- [ ] Create admin dashboard
- [ ] Add order history page
- [ ] Implement payment gateway

## 📊 Final Verification

### Backend
- [ ] ✅ Server running on port 8080
- [ ] ✅ Database connected
- [ ] ✅ API endpoints responding
- [ ] ✅ JWT authentication working

### Frontend
- [ ] ✅ Server running on port 5173
- [ ] ✅ All pages load
- [ ] ✅ API integration working
- [ ] ✅ Responsive design working

### Features
- [ ] ✅ User registration works
- [ ] ✅ User login works
- [ ] ✅ Product browsing works
- [ ] ✅ Cart functionality works
- [ ] ✅ Checkout works
- [ ] ✅ Logout works

## 🎉 Success Criteria

Your setup is complete when:
- ✅ Backend runs without errors
- ✅ Frontend runs without errors
- ✅ Can register and login
- ✅ Can browse products
- ✅ Can add to cart
- ✅ Can checkout
- ✅ Responsive on all devices
- ✅ No console errors

## 📝 Notes Section

Use this space to track any issues or customizations:

```
Date: ___________
Issues encountered:
1. 
2. 
3. 

Solutions applied:
1. 
2. 
3. 

Customizations made:
1. 
2. 
3. 
```

## 🆘 If Something Doesn't Work

1. **Check both terminals** - Backend and Frontend should both be running
2. **Check browser console** - Look for error messages
3. **Check network tab** - Verify API calls are succeeding
4. **Review documentation** - Refer to QUICK_START.md
5. **Restart servers** - Sometimes a fresh start helps
6. **Clear browser cache** - Ctrl+Shift+Delete
7. **Check database** - Ensure MySQL is running

## 📞 Quick Reference

### Start Backend
```bash
cd /path/to/GreenVibe
./mvnw spring-boot:run
```

### Start Frontend
```bash
cd /path/to/GreenVibe/frontend
npm run dev
```

### URLs
- Backend: http://localhost:8080
- Frontend: http://localhost:5173
- API Base: http://localhost:8080/api

---

**Once all items are checked, your GreenVibe platform is ready to use! 🌿**

*Print this checklist or keep it open while setting up your application.*
