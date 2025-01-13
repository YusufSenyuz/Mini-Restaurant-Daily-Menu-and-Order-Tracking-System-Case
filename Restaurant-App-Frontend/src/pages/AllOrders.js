import React, { useEffect, useState } from 'react';
import BASE_URL from '../config';
import { useNavigate } from 'react-router-dom';

const AllOrders = () => {
    const [orders, setOrders] = useState([]);
    const navigate = useNavigate();

    const fetchOrders = async () => {
        try {
            const response = await fetch(`${BASE_URL}/orders/all`);
            if (response.ok) {
                const data = await response.json();
                setOrders(data);
            } else {
                showPopup('Failed to load orders');
            }
        } catch (err) {
            showPopup(`Error: ${err.message}`);
        }
    };

    useEffect(() => {
        fetchOrders();
    }, []);

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

    const updateOrderStatus = async (username, orderId, currentStatus, newStatus, currentApproveDate, currentArrivalDate) => {
        if (currentStatus.toLowerCase() === 'approved' && newStatus.toLowerCase() === 'approved') {
            showPopup(`Order ${orderId} is already approved.`);
            return;
        }
        if (currentStatus.toLowerCase() === 'arrived') {
            showPopup(`Order ${orderId} has already arrived and cannot be updated.`);
            return;
        }
        if (newStatus.toLowerCase() === 'arrived' && currentStatus.toLowerCase() !== 'approved') {
            showPopup(`Order ${orderId} must be approved before marking it as arrived.`);
            return;
        }

        // Add timestamps for 'approved' and 'arrived' statuses
        const now = new Date().toISOString();  // Current time in ISO format
        let newApproveDate = currentApproveDate;
        let newArrivalDate = currentArrivalDate
        if (newStatus.toLowerCase() === 'approved') {
            newApproveDate = now;
        }
        if (newStatus.toLowerCase() === 'arrived') {
            newArrivalDate = now;
        }

        try {
            const response = await fetch(`${BASE_URL}/orders/${username}/update`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ id: orderId, status: newStatus, approveDate: newApproveDate, arrivalDate: newArrivalDate}),
            });
            if (response.ok) {
                showPopup(`Order ${orderId} updated to ${newStatus} successfully.`);
                fetchOrders();
            } else {
                showPopup(`Failed to update order ${orderId}.`);
            }
        } catch (err) {
            showPopup(`Error updating order: ${err.message}`);
        }
    };

    // Function to format date to display only the time (hours and minutes)
    const formatTime = (date) => {
        if (!date) return 'N/A';
        const time = new Date(date);
        return time.toLocaleTimeString('en-US', {
            hour: '2-digit',
            minute: '2-digit',
            hour12: false, // 24-hour format
        });
    };

    const renderOrder = (order, index) => {
        // Helper function to add 3 hours to a given date and format only hour and minute
        const addThreeHours = (date) => {
            const newDate = new Date(date);
            newDate.setHours(newDate.getHours() + 3);
            return newDate;
        };

        const formatTime = (date) => {
            const newDate = addThreeHours(date);
            const hours = newDate.getHours().toString().padStart(2, '0');
            const minutes = newDate.getMinutes().toString().padStart(2, '0');
            return `${hours}:${minutes}`;
        };

        return (
            <div key={index} style={{ marginBottom: '15px', padding: '10px', border: '1px solid #ccc' }}>
                <p><strong>Order ID:</strong> {order.id}</p>
                <p><strong>Username:</strong> {order.username}</p>
                <p><strong>Item Name:</strong> {order.itemName}</p>
                <p><strong>Price:</strong> ${order.price.toFixed(2)}</p>
                <p><strong>Status:</strong> {order.status}</p>
                <p><strong>Order Time:</strong> {formatTime(order.orderDate)}</p>
                <p><strong>Approve Time:</strong> {order.approveDate ? formatTime(order.approveDate) : 'Not yet approved'}</p>
                <p><strong>Arrival Time:</strong> {order.arrivalDate ? formatTime(order.arrivalDate) : 'Not yet arrived'}</p>

                {/* Only show buttons for orders that are not yet approved or marked as arrived */}
                {order.status.toLowerCase() !== 'arrived' && (
                    <>
                        {order.status.toLowerCase() !== 'approved' && (
                            <button
                                onClick={() => updateOrderStatus(order.username, order.id, order.status, 'Approved', order.approveDate, order.arrivalDate)}
                                style={{ marginRight: '10px', padding: '5px', backgroundColor: '#4CAF50', color: 'white', border: 'none', cursor: 'pointer' }}>
                                Approve Order
                            </button>
                        )}

                        {order.status.toLowerCase() === 'approved' && (
                            <button
                                onClick={() => updateOrderStatus(order.username, order.id, order.status, 'Arrived', order.approveDate, order.arrivalDate)}
                                style={{ padding: '5px', backgroundColor: '#2196F3', color: 'white', border: 'none', cursor: 'pointer' }}>
                                Mark as Arrived
                            </button>
                        )}
                    </>
                )}
            </div>
        );
    };



    const handleLogout = () => {
        localStorage.removeItem('username');
        navigate('/login');
    };

    // Separate orders by their status
    const notApprovedOrders = orders.filter(order => order.status.toLowerCase() !== 'approved' && order.status.toLowerCase() !== 'arrived');
    const approvedOrders = orders.filter(order => order.status.toLowerCase() === 'approved');
    const arrivedOrders = orders.filter(order => order.status.toLowerCase() === 'arrived');

    return (
        <div style={{ textAlign: 'center', backgroundColor: '#f0f8f0', padding: '20px' }}>
            <h1>All Orders</h1>

            <div style={{ display: 'flex', justifyContent: 'space-around', marginBottom: '20px' }}>
                <div style={{ flex: 1, padding: '10px' }}>
                    <h2>Not Approved</h2>
                    {notApprovedOrders.length > 0 ? notApprovedOrders.map(renderOrder) : <p>No orders available.</p>}
                </div>

                <div style={{ flex: 1, padding: '10px' }}>
                    <h2>Approved</h2>
                    {approvedOrders.length > 0 ? approvedOrders.map(renderOrder) : <p>No orders available.</p>}
                </div>

                <div style={{ flex: 1, padding: '10px' }}>
                    <h2>Arrived</h2>
                    {arrivedOrders.length > 0 ? arrivedOrders.map(renderOrder) : <p>No orders available.</p>}
                </div>
            </div>

            <div style={{ marginTop: '20px' }}>
                <button
                    onClick={handleLogout}
                    style={{ padding: '10px', backgroundColor: '#f44336', color: 'white', border: 'none', cursor: 'pointer' }}>
                    Log Out
                </button>
            </div>
        </div>
    );
};

export default AllOrders;
