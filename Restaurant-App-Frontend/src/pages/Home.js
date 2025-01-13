import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';

const Home = () => {
    const [message, setMessage] = useState('');

    useEffect(() => {
        // Fetch the welcome message from the backend
        axios.get('http://localhost:8080/')
            .then(response => {
                setMessage(response.data); // Store the data from the backend
            })
            .catch(error => {
                console.error('There was an error fetching the data!', error);
            });
    }, []);

    return (
        <div style={{
            textAlign: 'center',
            marginTop: '50px',
            padding: '20px',
            backgroundColor: '#f5f5f5',
            borderRadius: '10px',
            boxShadow: '0px 4px 6px rgba(0, 0, 0, 0.1)'
        }}>
            <h1 style={{ color: '#4CAF50', fontFamily: 'Arial, sans-serif', fontSize: '2.5rem' }}>
                Home
            </h1>
            <p style={{ fontSize: '1.2rem', marginBottom: '40px' }}>
                Please choose an option:
            </p>
            <div>
                <Link to="/login">
                    <button style={{
                        backgroundColor: '#4CAF50',
                        color: '#fff',
                        border: 'none',
                        borderRadius: '5px',
                        padding: '15px 30px',
                        fontSize: '1.1rem',
                        cursor: 'pointer',
                        margin: '10px',
                        transition: 'all 0.3s ease'
                    }}>
                        Login
                    </button>
                </Link>
                <Link to="/register">
                    <button style={{
                        backgroundColor: '#2196F3',
                        color: '#fff',
                        border: 'none',
                        borderRadius: '5px',
                        padding: '15px 30px',
                        fontSize: '1.1rem',
                        cursor: 'pointer',
                        margin: '10px',
                        transition: 'all 0.3s ease'
                    }}>
                        Register
                    </button>
                </Link>
            </div>
        </div>
    );
};

export default Home;
