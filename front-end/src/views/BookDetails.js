// BookDetails.js
import React, {useContext} from 'react';
import {useParams} from 'react-router-dom';
import {BookContext} from '../context/BookContext';

const BookDetails = () => {
    const {bookId} = useParams();
    const { books, addToCart } = useContext(BookContext);
    const book = books.find((book) => book.id === Number(bookId));

    if (!book) {
        return <h2>Libro no encontrado</h2>;
    }

    const handleAddToCart = () => {
        addToCart(book);
    };

    return (
        <div className="book-details">
            <h2 className="book-name">{book.name}</h2>
            <p className="book-author">Autor: {book.author}</p>
            <p className="book-genre">Género: {book.genre}</p>

            <button className="add-to-cart-button btn" onClick={handleAddToCart}>
                Añadir al carrito
            </button>
        </div>
    );
}

export default BookDetails;