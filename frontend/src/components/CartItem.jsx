import { useState } from 'react';
import { useCart } from '../context/CartContext';
import '../styles/CartItem.css';

const CartItem = ({ item }) => {
  const { updateCartItem, removeFromCart } = useCart();
  const [updating, setUpdating] = useState(false);

  const handleQuantityChange = async (newQuantity) => {
    if (newQuantity < 1) return;
    
    setUpdating(true);
    const result = await updateCartItem(item.productId, newQuantity);
    
    if (!result.success) {
      alert(result.error || 'Failed to update quantity');
    }
    setUpdating(false);
  };

  const handleRemove = async () => {
    if (window.confirm('Remove this item from cart?')) {
      const result = await removeFromCart(item.productId);
      if (!result.success) {
        alert(result.error || 'Failed to remove item');
      }
    }
  };

  return (
    <div className="cart-item">
      <div className="cart-item-image">
        <img 
          src={item.imageUrl || '/placeholder-plant.jpg'} 
          alt={item.productName}
          onError={(e) => {
            e.target.src = '/placeholder-plant.jpg';
          }}
        />
      </div>

      <div className="cart-item-details">
        <h3>{item.productName}</h3>
        <p className="item-price">${item.price} each</p>
      </div>

      <div className="cart-item-quantity">
        <button
          onClick={() => handleQuantityChange(item.quantity - 1)}
          disabled={updating || item.quantity <= 1}
        >
          -
        </button>
        <span>{item.quantity}</span>
        <button
          onClick={() => handleQuantityChange(item.quantity + 1)}
          disabled={updating}
        >
          +
        </button>
      </div>

      <div className="cart-item-total">
        <p className="item-subtotal">${item.subtotal}</p>
      </div>

      <button className="remove-button" onClick={handleRemove} disabled={updating}>
        ✕
      </button>
    </div>
  );
};

export default CartItem;
