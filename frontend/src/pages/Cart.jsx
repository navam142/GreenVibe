import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { useCart } from '../context/CartContext';
import { useAuth } from '../context/AuthContext';
import CartItem from '../components/CartItem';
import '../styles/Cart.css';

const Cart = () => {
  const navigate = useNavigate();
  const { cart, loading, fetchCart, clearCart } = useCart();
  const { isAuthenticated } = useAuth();

  useEffect(() => {
    if (!isAuthenticated) {
      navigate('/login');
      return;
    }
    fetchCart();
  }, [isAuthenticated]);

  const handleCheckout = () => {
    navigate('/checkout');
  };

  const handleClearCart = async () => {
    if (window.confirm('Are you sure you want to clear your cart?')) {
      const result = await clearCart();
      if (!result.success) {
        alert(result.error || 'Failed to clear cart');
      }
    }
  };

  if (loading) {
    return <div className="loading">Loading cart...</div>;
  }

  if (!cart || cart.cartItems?.length === 0) {
    return (
      <div className="empty-cart">
        <h2>Your cart is empty</h2>
        <p>Add some plants to your cart to get started!</p>
        <button onClick={() => navigate('/products')} className="shop-button">
          Continue Shopping
        </button>
      </div>
    );
  }

  return (
    <div className="cart-page">
      <div className="cart-header">
        <h1>Shopping Cart</h1>
        <button onClick={handleClearCart} className="clear-cart-button">
          Clear Cart
        </button>
      </div>

      <div className="cart-content">
        <div className="cart-items">
          {cart.cartItems.map((item) => (
            <CartItem key={item.productId} item={item} />
          ))}
        </div>

        <div className="cart-summary">
          <h2>Order Summary</h2>
          <div className="summary-row">
            <span>Total Items:</span>
            <span>{cart.totalItems}</span>
          </div>
          <div className="summary-row total">
            <span>Total Amount:</span>
            <span>${cart.totalAmount}</span>
          </div>
          <button onClick={handleCheckout} className="checkout-button">
            Proceed to Checkout
          </button>
          <button onClick={() => navigate('/products')} className="continue-shopping">
            Continue Shopping
          </button>
        </div>
      </div>
    </div>
  );
};

export default Cart;
