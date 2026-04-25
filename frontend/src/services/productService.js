import api from '../config/api';

export const productService = {
  getAllProducts: async () => {
    const response = await api.get('/products/get');
    return response.data;
  },

  getProductById: async (id) => {
    const response = await api.get(`/products/get/${id}`);
    return response.data;
  },

  createProduct: async (productData, imageFile) => {
    const formData = new FormData();
    formData.append('product', new Blob([JSON.stringify(productData)], { type: 'application/json' }));
    formData.append('image', imageFile);

    const response = await api.post('/products/add', formData, {
      headers: {
        'Content-Type': 'multipart/form-data',
      },
    });
    return response.data;
  },

  deleteProduct: async (id) => {
    const response = await api.delete(`/products/delete/${id}`);
    return response.data;
  },
};
