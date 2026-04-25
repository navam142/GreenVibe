import '../styles/Footer.css';

const Footer = () => {
  return (
    <footer className="footer">
      <div className="footer-container">
        <div className="footer-section">
          <h3>🌿 GreenVibe</h3>
          <p>Bringing nature closer to you</p>
        </div>

        <div className="footer-section">
          <h4>Quick Links</h4>
          <ul>
            <li><a href="/">Home</a></li>
            <li><a href="/products">Products</a></li>
            <li><a href="/cart">Cart</a></li>
          </ul>
        </div>

        <div className="footer-section">
          <h4>Categories</h4>
          <ul>
            <li>Indoor Plants</li>
            <li>Outdoor Plants</li>
            <li>Seeds</li>
            <li>Planters & Pots</li>
          </ul>
        </div>

        <div className="footer-section">
          <h4>Contact</h4>
          <p>Email: info@greenvibe.com</p>
          <p>Phone: (555) 123-4567</p>
        </div>
      </div>

      <div className="footer-bottom">
        <p>&copy; 2024 GreenVibe. All rights reserved.</p>
      </div>
    </footer>
  );
};

export default Footer;
