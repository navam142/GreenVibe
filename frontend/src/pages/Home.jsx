import { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { productService } from '../services/productService';
import ProductCard from '../components/ProductCard';
import '../styles/Home.css';

const Home = () => {
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetchProducts();
  }, []);

  const fetchProducts = async () => {
    try {
      setLoading(true);
      const data = await productService.getAllProducts();
      setProducts(data);
    } catch (err) {
      setError('Failed to load products');
      console.error(err);
    } finally {
      setLoading(false);
    }
  };

  if (loading) {
    return <div className="loading">Loading products...</div>;
  }

  if (error) {
    return <div className="error">{error}</div>;
  }

  return (
    <div className="home">
      <section className="hero">
        <div className="hero-content">
          <h1>Welcome to GreenVibe 🌿</h1>
          <p>Bring nature closer to you with our wide variety of plants, seeds, and planters</p>
          <Link to="/products" className="cta-button">Shop Now</Link>
        </div>
      </section>

      <section className="featured-products">
        <h2>Featured Products</h2>
        <div className="products-grid">
          {products.slice(0, 8).map((product) => (
            <ProductCard key={product.id} product={product} />
          ))}
        </div>
        {products.length > 8 && (
          <div className="view-all">
            <Link to="/products" className="view-all-button">View All Products</Link>
          </div>
        )}
      </section>

      <section className="categories">
        <h2>Shop by Category</h2>
        <div className="category-grid">
          <div className="category-card">
            <h3>🪴 Indoor Plants</h3>
            <p>Perfect for your home and office</p>
          </div>
          <div className="category-card">
            <h3>🌳 Outdoor Plants</h3>
            <p>Beautiful plants for your garden</p>
          </div>
          <div className="category-card">
            <h3>🌱 Seeds</h3>
            <p>Grow your own garden</p>
          </div>
          <div className="category-card">
            <h3>🏺 Planters & Pots</h3>
            <p>Stylish containers for your plants</p>
          </div>
        </div>
      </section>
    </div>
  );
};

export default Home;
