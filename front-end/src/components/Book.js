import React from "react";
import { Link } from "react-router-dom";

export const Book = ({ id, name, author, genre, publicationDate, isbn, rating, visibility, stock }) => {
    return (
        <Link to={`/books/${id}`} className="link">
            <div className="card">
                <h3>Título: {name}</h3>
                <p>Autor: {author}</p>
                <p>Categoría: {genre}</p>
                <p>Fecha de publicación: {publicationDate}</p>
                <p>ISBN: {isbn}</p>
                <p>Rating: {rating}</p>
                <p>Visibilidad: {visibility ? "Visible" : "No visible"}</p>
                <p>Stock: {stock}</p>
            </div>
        </Link>
    );
};