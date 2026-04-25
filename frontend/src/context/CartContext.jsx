import { createContext, useContext, useState, useEffect } from 'react';
import { cartService } from '../services/cartService';
import { useAuth } from './AuthContext';

const CartContext = createContext(null);

export const CartProvider = ({ children }) => {
  const [cart, setCart] = useState(null);
  const [loading, setLoading] = useState(false);
  const { user, isAuthenticated } = useAuth();

  const customerId = user?.id || 1; // Default to 1 for demo purposes

  useEffect(() => {
    if (isAuthenticated) {
      fetchCart();
    }
  }, [isAuthenticated]);

  const fetchCart = async () => {
    try {
      setLoading(true);
      const data = await cartService.getCart(customerId);
      setCart(data);
    } catch (error) {
      console.error('Error fetching cart:', error);
    } finally {
      setLoading(false);
    }
  };

  const addToCart = async (productId, quantity = 1) => {
    try {
      const data = await cartService.addItem(customerId, productId, quantity);
      setCart(data);
      return { success: true };
    } catch (error) {
      return { 
        success: false, 
        error: error.response?.data?.message || 'Failed to add item to cart' 
      };
    }
  };

  const updateCartItem = async (productId, quantity) => {
    try {
      const data = await cartService.updateItem(customerId, productId, quantity);
      setCart(data);
      return { success: true };
    } catch (error) {
      return { 
        success: false, 
        error: error.response?.data?.message || 'Failed to update cart item' 
      };
    }
  };

  const removeFromCart = async (productId) => {
    try {
      await cartService.removeItem(customerId, productId);
      await fetchCart();
      return { success: true };
    } catch (error) {
      return { 
        success: false, 
        error: error.response?.data?.message || 'Failed to remove item from cart' 
      };
    }
  };

  const clearCart = async () => {
    try {
      await cartService.clearCart(customerId);
      setCart(null);
      return { success: true };
    } catch (error) {
      return { 
        success: false, 
        error: error.response?.data?.message || 'Failed to clear cart' 
      };
    }
  };

  const value = {
    cart,
    loading,
    fetchCart,
    addToCart,
    updateCartItem,
    removeFromCart,
    clearCart,
    cartItemCount: cart?.totalItems || 0,
    cartTotal: cart?.totalAmount || 0,
  };

  return <CartContext.Provider value={value}>{children}</CartContext.Provider>;
};

export const useCart = () => {
  const context = useContext(CartContext);
  if (!context) {
    throw new Error('useCart must be used within a CartProvider');
  }
  return context;
};
