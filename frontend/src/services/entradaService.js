import axios from 'axios';

const guardarSalida = async (salida) => {
    try {
        await axios.post('http://localhost:8080/entrada/upload', salida, {
            headers: {
              'Content-Type': 'application/json', // Especifica el tipo de contenido como JSON
            },
        });
        alert('Datos enviados exitosamente.');
    } catch (error) {
        console.error('Error al enviar los datos:', error);
        alert('Error al enviar los datos.');
    }
};

export { guardarSalida };