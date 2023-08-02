import React from 'react';
import { Link } from 'react-router-dom';

const HomeComponent = () => {
  return (
    <div>
      <h2>Bienvenido</h2>
      <p>Selecciona una opción:</p>
      <ul>
        <li>
          <Link to="/entrada">Formulario de Entrada</Link>
        </li>
        <li>
          <Link to="/salida">Formulario de Salida</Link>
        </li>
        <li>
          <Link to="/resumen">Lista de Resúmenes</Link>
        </li>
      </ul>
    </div>
  );
};

export default HomeComponent;
