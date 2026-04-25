import { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { productService } from '../services/productService';
import { useCart } from '../context/CartContext';
import { useAuth } from '../context/AuthContext';
import '../styles/ProductDetail.css';

const ProductDetail = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const { addToCart } = useCart();
  const { isAuthenticated } = useAuth();
  
  const [product, setProduct] = useState(null);
  const [quantity, setQuantity] = useState(1);
  const [loading, setLoading] = useState(true);
  const [adding, setAdding] = useState(false);

  useEffect(() => {
    fetchProduct();
  }, [id]);

  const fetchProduct = async () => {
    try {
      setLoading(true);
      const data = await productService.getProductById(id);
      setProduct(data);
    } catch (error) {
      console.error('Error fetching product:', error);
    } finally {
      setLoading(false);
    }
  };

  const handleAddToCart = async () => {
    if (!isAuthenticated) {
      navigate('/login');
      return;
    }

    setAdding(true);
    const result = await addToCart(product.id, quantity);
    
    if (result.success) {
      alert('Product added to cart!');
      setQuantity(1);
    } else {
      alert(result.error || 'Failed to add product to cart');
    }
    setAdding(false);
  };

  const handleQuantityChange = (change) => {
    const newQuantity = quantity + change;
    if (newQuantity >= 1 && newQuantity <= product.stockQuantity) {
      setQuantity(newQuantity);
    }
  };

  if (loading) {
    return <div className="loading">Loading product details...</div>;
  }

  if (!product) {
    return <div className="error">Product not found</div>;
  }

  return (
    <div className="product-detail">
      <button className="back-button" onClick={() => navigate('/products')}>
        ← Back to Products
      </button>

      <div className="product-detail-content">
        <div className="product-detail-image">
          <img 
            src={product.imageUrl || '/placeholder-plant.jpg'} 
            alt={product.name}
            onError={(e) => {
              e.target.src = '/placeholder-plant.jpg';
            }}
          />
        </div>

        <div className="product-detail-info">
          <span className="product-category-badge">{product.categoryName}</span>
          <h1>{product.name}</h1>
          <p className="product-price">${product.price}</p>
          
          <div className="product-description">
            <h3>Description</h3>
            <p>{product.description}</p>
          </div>

          <div className="stock-info">
            {product.stockQuantity > 0 ? (
              <span className="in-stock">✓ In Stock ({product.stockQuantity} available)</span>
            ) : (
              <span className="out-of-stock">✗ Out of Stock</span>
            )}
          </div>

          {product.stockQuantity > 0 && (
            <div className="quantity-selector">
              <label>Quantity:</label>
              <div className="quantity-controls">
                <button onClick={() => handleQuantityChange(-1)} disabled={quantity <= 1}>
                  -
                </button>
                <span>{quantity}</span>
                <button onClick={() => handleQuantityChange(1)} disabled={quantity >= product.stockQuantity}>
                  +
                </button>
              </div>
            </div>
          )}

          <button
            className="add-to-cart-button"
            onClick={handleAddToCart}
            disabled={adding || product.stockQuantity === 0}
          >
            {adding ? 'Adding to Cart...' : product.stockQuantity === 0 ? 'Out of Stock' : 'Add to Cart'}
          </button>
        </div>
      </div>
    </div>
  );
};

export default ProductDetail;
