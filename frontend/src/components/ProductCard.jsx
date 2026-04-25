import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useCart } from '../context/CartContext';
import { useAuth } from '../context/AuthContext';
import '../styles/ProductCard.css';

const ProductCard = ({ product }) => {
  const navigate = useNavigate();
  const { addToCart } = useCart();
  const { isAuthenticated } = useAuth();
  const [adding, setAdding] = useState(false);

  const handleAddToCart = async (e) => {
    e.stopPropagation();
    
    if (!isAuthenticated) {
      navigate('/login');
      return;
    }

    setAdding(true);
    const result = await addToCart(product.id, 1);
    
    if (result.success) {
      alert('Product added to cart!');
    } else {
      alert(result.error || 'Failed to add product to cart');
    }
    setAdding(false);
  };

  const handleCardClick = () => {
    navigate(`/products/${product.id}`);
  };

  return (
    <div className="product-card" onClick={handleCardClick}>
      <div className="product-image">
        <img 
          src={product.imageUrl || '/placeholder-plant.jpg'} 
          alt={product.name}
          onError={(e) => {
            e.target.src = '/placeholder-plant.jpg';
          }}
        />
        {product.stockQuantity === 0 && (
          <div className="out-of-stock-badge">Out of Stock</div>
        )}
      </div>
      
      <div className="product-info">
        <h3 className="product-name">{product.name}</h3>
        <p className="product-category">{product.categoryName}</p>
        <p className="product-description">{product.description}</p>
        
        <div className="product-footer">
          <span className="product-price">${product.price}</span>
          <button
            className="add-to-cart-btn"
            onClick={handleAddToCart}
            disabled={adding || product.stockQuantity === 0}
          >
            {adding ? 'Adding...' : product.stockQuantity === 0 ? 'Out of Stock' : 'Add to Cart'}
          </button>
        </div>
        
        <div className="product-stock">
          {product.stockQuantity > 0 && product.stockQuantity < 10 && (
            <span className="low-stock">Only {product.stockQuantity} left!</span>
          )}
        </div>
      </div>
    </div>
  );
};

export default ProductCard;
