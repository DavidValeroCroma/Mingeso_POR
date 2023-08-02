import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import HomeComponent from './components/HomeComponent';
import EntradaForm from './components/EntradaComponent';
import SalidaForm from './components/SalidaUpload';
import ResumenList from './components/ResumenList';

const App = () => {
  return (
    <Router>
      <div style={{ padding: '20px', maxWidth: '600px', margin: '0 auto' }}>
        <nav style={{ marginBottom: '20px' }}>
          <Link to="/" style={linkStyle}>
            Inicio
          </Link>
        </nav>

        <Routes>
          <Route path="/" element={<HomeComponent />} />
          <Route path="/entrada" element={<EntradaForm />} />
          <Route path="/salida" element={<SalidaForm />} />
          <Route path="/resumen" element={<ResumenList />} />
        </Routes>
      </div>
    </Router>
  );
};

const linkStyle = {
  color: '#007bff',
  textDecoration: 'none',
  fontWeight: 'bold',
};

export default App;
