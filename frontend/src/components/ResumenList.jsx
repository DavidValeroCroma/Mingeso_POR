import React, { useState } from 'react';
import ResumenService from '../services/resumenService';
import Resumen from './Resumen';
import '../App';

const ResumenList = () => {
  const [fechaInicio, setFechaInicio] = useState('');
  const [fechaFinal, setFechaFinal] = useState('');
  const [resumenes, setResumenes] = useState([]);
  const [loading, setLoading] = useState(false);

  const handleBuscarResumenes = async () => {
    if (!fechaInicio || !fechaFinal) {
      alert('Por favor, seleccione ambas fechas.');
      return;
    }

    setLoading(true);
    try {
      const resumenesData = await ResumenService.obtenerResumenes(
        fechaInicio,
        fechaFinal
      );
      setResumenes(resumenesData);
      setLoading(false);
    } catch (error) {
      console.error('Error al buscar resúmenes:', error);
      setLoading(false);
    }
  };

  return (
    <div>
      <h2>Búsqueda de Resúmenes</h2>
      <div>
        <label>Fecha de inicio:</label>
        <input
          type="date"
          value={fechaInicio}
          onChange={(e) => setFechaInicio(e.target.value)}
        />
      </div>
      <div>
        <label>Fecha final:</label>
        <input
          type="date"
          value={fechaFinal}
          onChange={(e) => setFechaFinal(e.target.value)}
        />
      </div>
      <button onClick={handleBuscarResumenes}>Buscar</button>

      {loading ? (
        <p>Cargando resúmenes...</p>
      ) : resumenes.length > 0 ? (
        <div>
        <h3>Lista de Resúmenes</h3>
        <table>
          <thead>
            <tr>
              <th>Nro</th>
              <th>Fecha</th>
              <th>Tipo</th>
              <th>Número</th>
              <th>Motivo</th>
              <th>Monto Entrada</th>
              <th>Monto Salida</th>
              <th>Balance</th>
            </tr>
          </thead>
          <tbody>
            {resumenes.map((resumen) => (
              <Resumen key={resumen.id} resumen={resumen} />
            ))}
          </tbody>
        </table>
        </div>
      ) : (
        <p>No se encontraron resúmenes para las fechas seleccionadas.</p>
      )}
    </div>
  );
};

export default ResumenList;
