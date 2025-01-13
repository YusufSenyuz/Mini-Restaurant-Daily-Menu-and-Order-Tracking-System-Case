import React from 'react';
import Button from './Button';

const RedirectButton = ({ onClick, text }) => {
    return <Button onClick={onClick} style={styles.button}>{text}</Button>;
};

const styles = {
    button: {
        backgroundColor: '#4CAF50',
        color: 'white',
        marginTop: '10px',
    },
};

export default RedirectButton;
