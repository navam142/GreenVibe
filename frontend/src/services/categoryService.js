import api from '../config/api';

export const categoryService = {
  getAllCategories: async () => {
    const response = await api.get('/category/get');
    return response.data;
  },

  getCategoryById: async (id) => {
    const response = await api.get(`/category/get/${id}`);
    return response.data;
  },

  createCategory: async (categoryData) => {
    const response = await api.post('/category/create', categoryData);
    return response.data;
  },

  deleteCategory: async (id) => {
    const response = await api.delete(`/category/delete/${id}`);
    return response.data;
  },
};
