import React from "react";
import { Link } from "react-router-dom";

export const Book = ({ id, name, author, genre }) => {
    return (
        <Link to={`/books/${id}`} className="link">
            <div className="card">
                <h3>Título: {name}</h3>
                <p>Autor: {author}</p>
                <p>Género: {genre}</p>
            </div>
        </Link>
    );
};
