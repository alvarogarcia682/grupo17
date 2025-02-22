import {useEffect, useState} from "react";

export const useBooks = () => {

    const [books, setBooks] = useState([]);
    useEffect(() => {
        const fetchBooks = async () => {
            try {
                const response = await fetch(`${process.env.REACT_APP_GATEWAY_URL}/ms-books-catalogue/api/libros`, {
                    method: "POST", headers: {
                        "Content-Type": "application/json",
                    }, body: JSON.stringify({
                        targetMethod: "GET", queryParams: {}, body: {},
                    }),
                });

                if (!response.ok) throw new Error("Failed to fetch books");

                const data = await response.json();
                const mappedBooks = data.map((book) => ({
                    id: book.id,
                    name: book.title,
                    author: book.author,
                    genre: book.category,
                    publicationDate: book.publicationDate,
                    isbn: book.isbn,
                    rating: book.rating,
                    visibility: book.visibility,
                    stock: book.stock,
                }));

                setBooks(mappedBooks);
            } catch (error) {
                console.error("Fetch error:", error);
            }
        };

        fetchBooks();
    }, []);

    return books;
}