import React, { useState, useContext } from "react";
import { BookContext } from "../context/BookContext";
import {Link} from "react-router-dom";

export const Cart = () => {
    const { cart, removeFromCart, clearCart } = useContext(BookContext);
    const [isOpen, setIsOpen] = useState(false);


    return (
        <>
            <button
                className="toggle-cart btn"
                onClick={() => setIsOpen(!isOpen)}
            >
                {isOpen ? "Cerrar Carrito" : "Ver Carrito"}
            </button>

            <div className={`cart-container ${isOpen ? "visible" : ""}`}>
                <div className="cart">
                    <h3>Carrito de Compras</h3>

                    {cart.length > 0 ? (
                        <>
                            <ul className="cart-items">
                                {cart.map((item) => (
                                    <li key={item.id} className="cart-item">
                                        <span>{item.name} (x{item.quantity})</span>
                                        <button className="btn" onClick={() => removeFromCart(item.id)}>
                                            Eliminar
                                        </button>
                                    </li>
                                ))}
                            </ul>
                            <Link to="/checkout">
                                <button className="checkout-button btn">
                                    Ir al Checkout
                                </button>
                            </Link>
                        </>
                    ) : (
                        <p>El carrito está vacío.</p>
                    )}
                </div>
            </div>
        </>
    );
};
