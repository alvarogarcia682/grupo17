import React, { useContext } from 'react';
import { useNavigate } from 'react-router-dom';
import { BookContext } from '../context/BookContext';

function Checkout() {
    const { cart, clearCart } = useContext(BookContext);
    const navigate = useNavigate();

    const handlePayment = () => {
        alert('¡El pedido se ha realizado con éxito!');
        clearCart();
        navigate('/books');
    };

    if (cart.length === 0) {
        return <h2>No hay libros en el carrito.</h2>;
    }

    return (
        <div className="checkout-container">
            <h2>Resumen de tu compra</h2>
            <ul>
                {cart.map((item) => (
                    <li key={item.id}>
                        {item.name} (x{item.quantity || 1})
                    </li>
                ))}
            </ul>
            <button className={"btn"} onClick={handlePayment}>
                Proceder al Pago
            </button>
        </div>
    );
}

export default Checkout;
