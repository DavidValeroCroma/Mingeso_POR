import React from 'react';

const Resumen = ({ resumen }) => {
  // Formatear las fechas antes de mostrarlas
  const formattedFecha = new Date(resumen.fecha).toLocaleDateString();

  return (
    <tr className="resumen-row">
      <td>{resumen.id}</td>
      <td>{formattedFecha}</td> {/* Usamos la fecha formateada */}
      <td>{resumen.tipo}</td>
      <td>{resumen.numero}</td>
      <td>{resumen.motivo}</td>
      <td>{resumen.monto_entrada}</td>
      <td>{resumen.monto_salida}</td>
      <td>{resumen.balance}</td>
    </tr>
  );
};

export default Resumen;
