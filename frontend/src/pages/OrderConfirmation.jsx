import { useLocation, useNavigate } from 'react-router-dom';
import '../styles/OrderConfirmation.css';

const OrderConfirmation = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const order = location.state?.order;

  if (!order) {
    return (
      <div className="order-confirmation">
        <div className="no-order">
          <h2>No order information found</h2>
          <button onClick={() => navigate('/')} className="home-button">
            Go to Home
          </button>
        </div>
      </div>
    );
  }

  return (
    <div className="order-confirmation">
      <div className="confirmation-card">
        <div className="success-icon">✓</div>
        <h1>Order Placed Successfully!</h1>
        <p className="order-id">Order ID: #{order.orderId}</p>

        <div className="order-details">
          <h2>Order Details</h2>
          <div className="detail-row">
            <span>Order Date:</span>
            <span>{new Date(order.orderDate).toLocaleDateString()}</span>
          </div>
          <div className="detail-row">
            <span>Status:</span>
            <span className="status-badge">{order.orderStatus}</span>
          </div>
          <div className="detail-row">
            <span>Total Amount:</span>
            <span className="total-amount">${order.totalAmount}</span>
          </div>
        </div>

        <div className="order-items-section">
          <h3>Items Ordered</h3>
          {order.orderItems?.map((item, index) => (
            <div key={index} className="confirmation-item">
              <span>{item.productName} x {item.quantity}</span>
              <span>${item.price * item.quantity}</span>
            </div>
          ))}
        </div>

        <div className="confirmation-actions">
          <button onClick={() => navigate('/')} className="home-button">
            Continue Shopping
          </button>
        </div>
      </div>
    </div>
  );
};

export default OrderConfirmation;
