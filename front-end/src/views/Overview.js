import React, {useContext} from 'react';
import '../styles/styles.css';
import {Book} from "../components/Book";
import {BookContext} from "../context/BookContext";
import {LinearProgress} from "@mui/material";
import {useSearch} from "../hooks/useSearch";

export const Overview = () => {
    const {books} = useContext(BookContext);
    const {query, setQuery, filteredItems} = useSearch(books, 'name');

    return (
        <div>

            {}
            <div className="search-bar">
                <input
                    type="text"
                    placeholder="Buscar por título"
                    value={query}
                    onChange={(e) => setQuery(e.target.value)}
                    className="search-input"
                />
            </div>

            <div className="book-container">
                {
                    books.length > 0 ? (
                        filteredItems.length > 0 ? (
                            filteredItems.map((book, index) => (
                                <Book
                                    key={index}
                                    id={book.id}
                                    name={book.name}
                                    author={book.author}
                                    genre={book.genre}
                                />
                            ))
                        ) : (
                            <p>No se encontraron libros que coincidan con la búsqueda.</p>
                        )
                    ) : (
                        <LinearProgress color="secondary"/>
                    )
                }
            </div>
        </div>
    );
};
