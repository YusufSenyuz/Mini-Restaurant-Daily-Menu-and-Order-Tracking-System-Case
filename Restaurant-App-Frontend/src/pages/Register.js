import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import BASE_URL from '../config';
import FormInput from '../components/FormInput';
import Button from '../components/Button';
import RedirectButton from '../components/RedirectButton';

const Register = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [userType, setUserType] = useState('user');
    const [message, setMessage] = useState('');

    const navigate = useNavigate();

    const handleRegister = async () => {
        const endpoint =
            userType === 'user' ? `${BASE_URL}/auth/register/user` : `${BASE_URL}/auth/register/restaurant`;

        try {
            const response = await fetch(endpoint, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ username, password }),
            });

            if (response.ok) {
                const data = await response.text();
                setMessage(`Success: ${data}`);
            } else {
                const error = await response.text();
                setMessage(`Error: ${error}`);
            }
        } catch (err) {
            setMessage(`Error: ${err.message}`);
        }
    };

    const handleLoginRedirect = () => {
        navigate('/login');
    };

    return (
        <div style={styles.container}>
            <h1 style={styles.header}>Register</h1>
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
                <Button onClick={handleRegister} style={styles.registerButton}>Register</Button>
                <p>{message}</p>
                <RedirectButton onClick={handleLoginRedirect} text="If you have an account, login" />
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
    registerButton: {
        backgroundColor: '#2e8b57',
        color: 'white',
        marginTop: '20px',
    },
};

export default Register;
