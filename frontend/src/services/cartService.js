import api from '../config/api';

export const cartService = {
  getCart: async (customerId) => {
    const response = await api.get(`/cart/${customerId}`);
    return response.data;
  },

  addItem: async (customerId, productId, quantity) => {
    const response = await api.post(`/cart/${customerId}/add`, null, {
      params: { productId, quantity },
    });
    return response.data;
  },

  updateItem: async (customerId, productId, quantity) => {
    const response = await api.put(`/cart/${customerId}/update`, null, {
      params: { productId, quantity },
    });
    return response.data;
  },

  removeItem: async (customerId, productId) => {
    const response = await api.delete(`/cart/${customerId}/remove/${productId}`);
    return response.data;
  },

  clearCart: async (customerId) => {
    const response = await api.delete(`/cart/${customerId}/clear`);
    return response.data;
  },
};
