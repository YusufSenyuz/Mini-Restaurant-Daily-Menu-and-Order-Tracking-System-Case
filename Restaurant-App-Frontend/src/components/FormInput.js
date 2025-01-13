import React from 'react';

const FormInput = ({ label, type, value, onChange, selectOptions }) => {
    return (
        <div style={styles.inputContainer}>
            <label style={styles.label}>{label}: </label>
            {selectOptions ? (
                <select value={value} onChange={onChange} style={styles.select}>
                    {selectOptions.map((option, index) => (
                        <option key={index} value={option.value}>
                            {option.label}
                        </option>
                    ))}
                </select>
            ) : (
                <input
                    type={type}
                    value={value}
                    onChange={onChange}
                    style={styles.input}
                />
            )}
        </div>
    );
};

const styles = {
    inputContainer: {
        marginBottom: '15px',
    },
    label: {
        fontSize: '16px',
        color: '#333',
        display: 'block',
        marginBottom: '5px',
    },
    input: {
        width: '100%',
        padding: '10px',
        fontSize: '16px',
        border: '1px solid #ccc',
        borderRadius: '5px',
        outline: 'none',
    },
    select: {
        width: '100%',
        padding: '10px',
        fontSize: '16px',
        border: '1px solid #ccc',
        borderRadius: '5px',
        outline: 'none',
    },
};

export default FormInput;
