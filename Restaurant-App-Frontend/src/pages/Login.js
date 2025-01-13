import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import BASE_URL from '../config';
import FormInput from '../components/FormInput';
import Button from '../components/Button';
import RedirectButton from '../components/RedirectButton';

const Login = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [userType, setUserType] = useState('user');
    const [message, setMessage] = useState('');

    const navigate = useNavigate();

    const handleLogin = async () => {
        const endpoint = userType === 'user' ? `${BASE_URL}/auth/login/user` : `${BASE_URL}/auth/login/restaurant`;

        try {
            const response = await fetch(endpoint, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },

                body: JSON.stringify({ username, password, userType }),
            });


            const data = await response.text();
            if (response.ok) {
                setMessage(`Success: ${data}`);
                localStorage.setItem('username', username);

                if (userType === 'user') {
                    navigate('/menus/today');
                } else {
                    navigate(`/orders/all`);
                }
            } else {
                setMessage(`Error: ${data}`);
            }
        } catch (err) {
            console.error('Error during login:', err);
            setMessage(`Error: ${err.message}`);
        }
    };


    const handleRegisterRedirect = () => {
        navigate('/register');
    };

    return (
        <div style={styles.container}>
            <h1 style={styles.header}>Login</h1>
            <div style={styles.formContainer}>
                <FormInput
                    label="Username"
                    type="text"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                />
                <FormInput
                    label="Password"
                    type="password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                />
                <FormInput
                    label="User Type"
                    selectOptions={[
                        { value: 'user', label: 'User' },
                        { value: 'restaurant', label: 'Restaurant' },
                    ]}
                    value={userType}
                    onChange={(e) => setUserType(e.target.value)}
                />
                <Button onClick={handleLogin} style={styles.loginButton}>Login</Button>
                <p>{message}</p>
            </div>
            <div style={styles.registerContainer}>
                <RedirectButton onClick={handleRegisterRedirect} text="If you don't have an account, register" />
            </div>
        </div>
    );
};

const styles = {
    container: {
        textAlign: 'center',
        backgroundColor: '#f0f8f0',
        padding: '40px',
        borderRadius: '10px',
        boxShadow: '0px 8px 16px rgba(0, 0, 0, 0.1)',
        width: '400px',
        margin: 'auto',
        marginTop: '100px',
    },
    header: {
        fontSize: '32px',
        color: '#2e8b57',
        marginBottom: '20px',
    },
    formContainer: {
        marginBottom: '20px',
    },
    loginButton: {
        backgroundColor: '#2e8b57',
        color: 'white',
        marginTop: '20px',
    },
    registerContainer: {
        marginTop: '20px',
    },
    registerText: {
        fontSize: '14px',
        color: '#333',
    },
};

export default Login;
