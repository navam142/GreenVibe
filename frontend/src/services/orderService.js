import api from '../config/api';

export const orderService = {
  getOrderById: async (orderId) => {
    const response = await api.get(`/orders/${orderId}`);
    return response.data;
  },

  placeOrder: async () => {
    const response = await api.post('/orders/place');
    return response.data;
  },

  cancelOrder: async (orderId) => {
    const response = await api.put(`/orders/${orderId}/cancel`);
    return response.data;
  },

  updateOrderStatus: async (orderId, orderStatus) => {
    const response = await api.put(`/orders/admin/${orderId}/status`, null, {
      params: { orderStatus },
    });
    return response.data;
  },
};
