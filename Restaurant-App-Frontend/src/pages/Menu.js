import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import BASE_URL from '../config';

const Menu = () => {
    const [menu, setMenu] = useState({ day: '', categories: [] });
    const [message, setMessage] = useState('');
    const navigate = useNavigate();

    const goToOrdersPage = () => {
        const username = localStorage.getItem('username');
        navigate(`/orders/${username || 'guest'}`);
    };

    const handleLogout = () => {
        localStorage.removeItem('username');
        navigate('/login');
    };

    const showPopup = (message) => {
        const popup = document.createElement('div');
        popup.innerText = message;
        popup.style.position = 'fixed';
        popup.style.top = '50%';
        popup.style.left = '50%';
        popup.style.transform = 'translate(-50%, -50%)';
        popup.style.padding = '20px';
        popup.style.backgroundColor = '#333';
        popup.style.color = '#fff';
        popup.style.borderRadius = '5px';
        popup.style.boxShadow = '0 2px 10px rgba(0, 0, 0, 0.2)';
        popup.style.zIndex = '1000';

        document.body.appendChild(popup);

        setTimeout(() => {
            document.body.removeChild(popup);
        }, 3000);
    };

    useEffect(() => {
        const fetchMenu = async () => {
            try {
                const response = await fetch(`${BASE_URL}/menus/today`);
                if (response.ok) {
                    const data = await response.json();
                    setMenu(data);
                } else {
                    showPopup('Failed to load menu');
                }
            } catch (err) {
                showPopup(`Error: ${err.message}`);
            }
        };
        fetchMenu();
    }, []);

    const handleOrder = async (item) => {
        const username = localStorage.getItem('username') || 'guest';
        const order = {
            username,
            itemName: item.name,
            price: item.price,
            status: 'Pending',
            orderDate: new Date().toISOString(),
            approveDate: null,
            arrivalDate: null
        };

        try {
            const response = await fetch(`${BASE_URL}/orders/${username}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(order),
            });

            const responseText = await response.text();
            if (response.ok) {
                showPopup(`${item.name} added to your orders!`);
            } else {
                let errorData;
                try {
                    errorData = JSON.parse(responseText);
                } catch (error) {
                    errorData = { message: responseText };
                }
                showPopup(`Failed to add order: ${errorData.message || response.statusText}`);
            }
        } catch (error) {
            showPopup(`Error placing order: ${error.message}`);
        }
    };

    const renderCategory = (category) => (
        <div key={category.name} style={{ marginBottom: '30px' }}>
            <h3 style={{ color: 'green', marginBottom: '5px' }}>{category.name.replace(/_/g, ' ')}</h3>
            <p style={{ color: 'green', textAlign: 'center', fontWeight: 'bold' }}>----------------------------------------------------</p>
            {category.items.map((item, index) => {
                return (
                    <div key={index} style={{ marginBottom: '10px', display: 'flex', alignItems: 'center' }}>
                        {/* Colored circles for multiple colors */}
                        {item.colors && item.colors.length > 0 && (
                            <div style={{ display: 'flex', marginRight: '10px' }}>
                                {item.colors.map((color, colorIndex) => (
                                    <div
                                        key={colorIndex}
                                        style={{
                                            width: '15px',
                                            height: '15px',
                                            borderRadius: '50%',
                                            backgroundColor: color,
                                            marginRight: '5px',
                                        }}
                                    ></div>
                                ))}
                            </div>
                        )}
                        <div style={{ flex: 1 }}>
                            {/* Handle isNew and parenthesesDescription */}
                            <p style={{ marginBottom: '5px' }}>
                                {item.isNew && <span style={{ color: 'green', fontStyle: 'italic' }}>New! </span>}
                                <strong>
                                    {item.name}
                                    {item.parenthesesDescription ? ` (${item.parenthesesDescription})` : ''}
                                </strong>{' '}
                                ({item.price.toFixed(2)} USD)
                            </p>
                            {/* Description with spacing */}
                            {item.description && (
                                <p style={{ marginTop: '10px', fontSize: '14px', color: '#555' }}>
                                    {item.description}
                                </p>
                            )}
                        </div>
                        {/* Order Button */}
                        <button
                            style={{
                                padding: '5px 10px',
                                backgroundColor: 'green',
                                color: 'white',
                                border: 'none',
                                borderRadius: '5px',
                                fontSize: '12px',
                            }}
                            onClick={() => handleOrder(item)}
                        >
                            Order
                        </button>
                    </div>
                );
            })}
        </div>
    );



    return (
        <div style={{ textAlign: 'center', backgroundColor: '#e0f7e0', padding: '20px' }}>
            <div
                style={{
                    width: '80%',
                    margin: '0 auto',
                    backgroundColor: 'white',
                    padding: '20px',
                    borderRadius: '10px',
                }}
            >
                <h2>{menu.day || 'Loading...'}</h2>
                {message && <p>{message}</p>}
                <h1 style={{ marginBottom: '20px' }}>Today's Menu</h1>
                {menu.categories?.length > 0 ? menu.categories.map(renderCategory) : <p>No menu available for today.</p>}
            </div>

            {/* Buttons for navigating to Menu and Logging out */}
            <div style={{ marginTop: '20px' }}>
                <button
                    onClick={goToOrdersPage}
                    style={{ padding: '10px', marginRight: '10px', backgroundColor: '#4CAF50', color: 'white', border: 'none', cursor: 'pointer' }}>
                    My Orders
                </button>
                <button
                    onClick={handleLogout}
                    style={{ padding: '10px', backgroundColor: '#f44336', color: 'white', border: 'none', cursor: 'pointer' }}>
                    Log Out
                </button>
            </div>
        </div>
    );
};

export default Menu;
