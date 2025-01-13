import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from './pages/Home';
import Login from './pages/Login';
import Register from './pages/Register';
import Menu from './pages/Menu';
import Orders from './pages/Orders';
import AllOrders from "./pages/AllOrders";

const App = () => {
    return (
        <Router>
            <Routes>
                {/* Use 'element' prop instead of 'component' */}
                <Route path="/" element={<Home />} />
                <Route path="/login" element={<Login />} />
                <Route path="/register" element={<Register />} />
                <Route path="/menus/today" element={<Menu />} />
                <Route path="/menus/yesterday" element={<Menu />} />
                <Route path="/menus/tomorrow" element={<Menu />} />
                <Route path="/orders/:username" element={<Orders />} />
                <Route path="/orders/all" element={<AllOrders />} /> {/* Add route for AllOrders */}

            </Routes>
        </Router>
    );
};

export default App;
