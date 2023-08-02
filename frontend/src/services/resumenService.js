import axios from 'axios';

const BASE_URL = 'http://localhost:8080/resumen';

const resumenService = {
  obtenerResumenes: async (fechaInicio, fechaFinal) => {
    try {
      const response = await axios.get(BASE_URL, {
        params: {
          fechaInicio,
          fechaFinal,
        },
      });
      return response.data;
    } catch (error) {
      console.error('Error al obtener resúmenes:', error);
      return [];
    }
  },
};

export default resumenService;