import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useCart } from '../context/CartContext';
import { orderService } from '../services/orderService';
import '../styles/Checkout.css';

const Checkout = () => {
  const navigate = useNavigate();
  const { cart, clearCart } = useCart();
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');

  const handlePlaceOrder = async () => {
    if (!cart || cart.cartItems?.length === 0) {
      setError('Your cart is empty');
      return;
    }

    setLoading(true);
    setError('');

    try {
      const order = await orderService.placeOrder();
      await clearCart();
      alert(`Order placed successfully! Order ID: ${order.orderId}`);
      navigate('/order-confirmation', { state: { order } });
    } catch (err) {
      setError(err.response?.data?.message || 'Failed to place order. Please try again.');
    } finally {
      setLoading(false);
    }
  };

  if (!cart || cart.cartItems?.length === 0) {
    return (
      <div className="checkout-page">
        <div className="empty-checkout">
          <h2>Your cart is empty</h2>
          <button onClick={() => navigate('/products')} className="shop-button">
            Continue Shopping
          </button>
        </div>
      </div>
    );
  }

  return (
    <div className="checkout-page">
      <h1>Checkout</h1>

      {error && <div className="error-message">{error}</div>}

      <div className="checkout-content">
        <div className="order-summary">
          <h2>Order Summary</h2>
          <div className="order-items">
            {cart.cartItems.map((item) => (
              <div key={item.productId} className="order-item">
                <div className="order-item-info">
                  <img 
                    src={item.imageUrl || '/placeholder-plant.jpg'} 
                    alt={item.productName}
                    onError={(e) => {
                      e.target.src = '/placeholder-plant.jpg';
                    }}
                  />
                  <div>
                    <h4>{item.productName}</h4>
                    <p>Quantity: {item.quantity}</p>
                  </div>
                </div>
                <div className="order-item-price">
                  ${item.subtotal}
                </div>
              </div>
            ))}
          </div>

          <div className="order-totals">
            <div className="total-row">
              <span>Total Items:</span>
              <span>{cart.totalItems}</span>
            </div>
            <div className="total-row grand-total">
              <span>Total Amount:</span>
              <span>${cart.totalAmount}</span>
            </div>
          </div>
        </div>

        <div className="checkout-actions">
          <button
            className="place-order-button"
            onClick={handlePlaceOrder}
            disabled={loading}
          >
            {loading ? 'Placing Order...' : 'Place Order'}
          </button>
          <button
            className="back-to-cart-button"
            onClick={() => navigate('/cart')}
            disabled={loading}
          >
            Back to Cart
          </button>
        </div>
      </div>
    </div>
  );
};

export default Checkout;
