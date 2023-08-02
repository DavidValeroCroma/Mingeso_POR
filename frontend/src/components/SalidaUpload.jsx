import React, { useState } from 'react';
import { guardarSalida } from '../services/salidaService';

const SalidaUpload = () => {
  const [fecha, setFecha] = useState('');
  const [numero, setNumero] = useState('');
  const [monto, setMonto] = useState('');
  const [tipo, setTipo] = useState('Boleta');
  const [motivo, setMotivo] = useState('Alimentos');

  const handleSubmit = async (e) => {
    e.preventDefault();

    let fechaObjeto = new Date(fecha);
    let fechaUnix = fechaObjeto.getTime() + (fechaObjeto.getTimezoneOffset() * 60 * 1000);

    const nuevaSalida = {
      fecha: fechaUnix,
      numero,
      monto: parseFloat(monto),
      tipo,
      motivo,
    };

    // Llamamos a la función guardarSalida del servicio para enviar los datos al backend
    await guardarSalida(nuevaSalida);

    
  };

  return (
    <div>
      <h2>Subir Datos de Salida</h2>
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
        <div>
          <label>Tipo:</label>
          <select value={tipo} onChange={(e) => setTipo(e.target.value)}>
            <option value="Boleta">Boleta</option>
            <option value="Factura">Factura</option>
          </select>
        </div>
        <div>
          <label>Motivo:</label>
          <select value={motivo} onChange={(e) => setMotivo(e.target.value)}>
            <option value="Alimentos">Alimentos</option>
            <option value="Combustible">Combustible</option>
            <option value="Artículos de Oficina">Artículos de Oficina</option>
            <option value="Productos de limpieza">Productos de limpieza</option>
            <option value="Reparaciones">Reparaciones</option>
            <option value="Taxis">Taxis</option>
          </select>
        </div>
        <button type="submit">Guardar</button>
      </form>
    </div>
  );
};

export default SalidaUpload;
