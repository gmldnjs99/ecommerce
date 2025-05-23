import React, { useState } from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import Login from './pages/Login';
import Register from './pages/Register';

function App() {
    const [showLogin, setShowLogin] = useState(true);

    return (
        <Router>
            <Routes>
                <Route path="/" element={<Navigate to="/login" replace />} />
                <Route path="/login" element={<Login />} />
                <Route path="/register" element={<Register onSwitchToLogin={() => setShowLogin(true)} />} />
            </Routes>
        </Router>
    );
}

export default App;
