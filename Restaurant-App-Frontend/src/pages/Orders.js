import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import BASE_URL from "../config";

const Orders = () => {
    const { username } = useParams();
    const [orders, setOrders] = useState([]);
    const [errorMessage, setErrorMessage] = useState('');
    const navigate = useNavigate(); // Hook for navigation

    const formatTime = (date) => {
        if (!date) return 'N/A'; // Handle null/undefined dates
        const time = new Date(date);
        // Ensure the time is in the local timezone
        const formattedTime = time.toLocaleTimeString("en-US", {
            hour: '2-digit',
            minute: '2-digit',
            hour12: false, // Use 24-hour format
        });

        return formattedTime; // Return formatted time in local timezone
    };

    const handleMenuNavigation = () => {
        navigate('/menus/today'); // Navigate to the menu page
    };

    const handleLogout = () => {
        // Remove the username from localStorage
        localStorage.removeItem('username');
        // Navigate to the login page or home page
        navigate('/login');
    };

    useEffect(() => {
        const fetchOrders = async () => {
            const username = localStorage.getItem('username') || 'guest';
            try {
                const response = await fetch(`${BASE_URL}/orders/${username}`);
                // Check if response is ok (status code 2xx)
                if (response.ok) {
                    const data = await response.json(); // Parse JSON if the response is valid JSON
                    if (Array.isArray(data)) {
                        setOrders(data); // Set orders state if it's an array
                    } else {
                        throw new Error('Invalid response format');
                    }
                } else {
                    // Log the error response
                    const errorText = await response.text();
                    setErrorMessage('Failed to fetch orders.');
                }
            } catch (error) {
                setErrorMessage('Error fetching orders.');
            }
        };

        fetchOrders();
    }, [username]);

    return (
        <div style={{ padding: '20px', backgroundColor: '#e0f7e0', textAlign: 'center' }}>
            <h1 style={{ marginBottom: '20px', color: 'green' }}>Orders for {username}</h1>

            <table style={{ width: '100%', borderCollapse: 'collapse' }}>
                <thead>
                <tr style={{ backgroundColor: '#d4edd4' }}>
                    <th>Id</th>
                    <th>Item</th>
                    <th>Price (USD)</th>
                    <th>Status</th>
                    <th>Order Time</th>
                    <th>Approve Time</th>
                    <th>Arrival Time</th>
                </tr>
                </thead>
                <tbody>
                {orders.length > 0 ? (
                    orders.map((order, index) => {
                        // Function to format the time by adding 3 hours
                        const formatWithTimeZone = (date) => {
                            if (!date) return 'N/A';
                            const time = new Date(date);
                            const adjustedTime = new Date(time.getTime() + 3 * 60 * 60 * 1000); // Add 3 hours
                            return adjustedTime.toLocaleTimeString('en-US', {
                                hour: '2-digit',
                                minute: '2-digit',
                                hour12: false, // 24-hour format
                            });
                        };

                        return (
                            <tr key={index} style={{ backgroundColor: index % 2 === 0 ? '#f9f9f9' : '#ffffff' }}>
                                <td>{order.id}</td>
                                <td>{order.itemName}</td>
                                <td>{order.price.toFixed(2)}</td>
                                <td>{order.status}</td>
                                <td>{formatWithTimeZone(order.orderDate)}</td>
                                <td>{formatWithTimeZone(order.approveDate)}</td>
                                <td>{formatWithTimeZone(order.arrivalDate)}</td>
                            </tr>
                        );
                    })
                ) : (
                    <tr>
                        <td colSpan="7" style={{ textAlign: 'center' }}>No orders found.</td>
                    </tr>
                )}
                </tbody>
            </table>

            {/* Buttons for navigating to Menu and Logging out placed below the table */}
            <div style={{ marginTop: '20px' }}>
                <button
                    onClick={handleMenuNavigation}
                    style={{ padding: '10px', marginRight: '10px', backgroundColor: '#4CAF50', color: 'white', border: 'none', cursor: 'pointer' }}>
                    Go to Menu
                </button>
                <button
                    onClick={handleLogout}
                    style={{ padding: '10px', backgroundColor: '#f44336', color: 'white', border: 'none', cursor: 'pointer' }}>
                    Log Out
                </button>
            </div>

            {errorMessage && <p style={{ color: 'red' }}>{errorMessage}</p>}
        </div>
    );


};

export default Orders;
