import React, { useState } from 'react';
import { guardarSalida } from '../services/entradaService';

const EntradaUpload = () => {
  const [fecha, setFecha] = useState('');
  const [numero, setNumero] = useState('');
  const [monto, setMonto] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();

    let fechaObjeto = new Date(fecha);
    let fechaUnix = fechaObjeto.getTime() + (fechaObjeto.getTimezoneOffset() * 60 * 1000);

    const nuevaSalida = {
      fecha: fechaUnix,
      numero,
      monto: parseFloat(monto),
    };

    // Llamamos a la función guardarSalida del servicio para enviar los datos al backend
    await guardarSalida(nuevaSalida);

    
  };

  return (
    <div>
      <h2>Subir Datos de Entrada</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Fecha:</label>
          <input
            type="date"
            value={fecha}
            onChange={(e) => setFecha(e.target.value)}
          />
        </div>
        <div>
          <label>Número:</label>
          <input
            type="text" // Ahora numero es de tipo text
            value={numero}
            onChange={(e) => setNumero(e.target.value)}
          />
        </div>
        <div>
          <label>Monto:</label>
          <input
            type="number"
            min="0"
            step="0.01"
            value={monto}
            onChange={(e) => setMonto(e.target.value)}
          />
        </div>
        <button type="submit">Guardar</button>
      </form>
    </div>
  );
};

export default EntradaUpload;