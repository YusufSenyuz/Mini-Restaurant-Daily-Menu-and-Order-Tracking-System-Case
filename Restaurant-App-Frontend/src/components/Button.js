import React from 'react';

const Button = ({ onClick, children, style }) => {
    return (
        <button onClick={onClick} style={{ ...defaultStyles.button, ...style }}>
            {children}
        </button>
    );
};

const defaultStyles = {
    button: {
        padding: '10px 20px',
        fontSize: '16px',
        border: 'none',
        borderRadius: '5px',
        cursor: 'pointer',
        width: '100%',
    },
};

export default Button;
