// src/context/BookContext.js
import React, { createContext, useEffect, useState } from 'react';

export const BookContext = createContext();

export const BookProvider = ({ children, initialBooks = [] }) => {
    const [books, setBooks] = useState(initialBooks);

    const [cart, setCart] = useState([]);

    useEffect(() => {
        setBooks(initialBooks);
    }, [initialBooks]);

    const addToCart = (book) => {
        setCart((prevCart) => {
            const existingIndex = prevCart.findIndex((item) => item.id === book.id);

            if (existingIndex === -1) {
                return [...prevCart, { ...book, quantity: 1 }];
            } else {
                const updatedCart = [...prevCart];
                updatedCart[existingIndex] = {
                    ...updatedCart[existingIndex],
                    quantity: updatedCart[existingIndex].quantity + 1,
                };
                return updatedCart;
            }
        });
    };

    const removeFromCart = (bookId) => {
        setCart((prevCart) => {
            const existingIndex = prevCart.findIndex((item) => item.id === bookId);

            if (existingIndex === -1) {
                return prevCart;
            }

            const updatedCart = [...prevCart];
            const currentItem = updatedCart[existingIndex];

            if (currentItem.quantity === 1) {
                updatedCart.splice(existingIndex, 1);
            } else {
                updatedCart[existingIndex] = {
                    ...currentItem,
                    quantity: currentItem.quantity - 1,
                };
            }
            return updatedCart;
        });
    };


    const clearCart = () => {
        setCart([]);
    };

    return (
        <BookContext.Provider
            value={{
                books,
                cart,
                setBooks,
                addToCart,
                removeFromCart,
                clearCart,
            }}
        >
            {children}
        </BookContext.Provider>
    );
};
