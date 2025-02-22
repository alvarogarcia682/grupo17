import {useEffect, useState} from "react";

export const useBooks = () => {

    const [books, setBooks] = useState([]);
    useEffect(() => {

        setTimeout(() => {
            setBooks([
                {id: "1", name: "American Gods", author: "Neil Gaiman", genre: "Fantasía"},
                {id: "2", name: "On the Road", author: "Jack Kerouac", genre: "Novela Beat"},
                {id: "3", name: "Women", author: "Charles Bukowski", genre: "Novela"},
                {id: "4", name: "Kafka on the Shore", author: "Hakuri Murakami", genre: "Realismo Mágico"},
                {id: "5", name: "The Prince", author: "Nicolas Maquiavelo", genre: "Tratado Político"},
                {id: "6", name: "The Road Back", author: "Erich Maria remarque", genre: "Novela de Guerra"},
            ]);
        }, 2500);
    }, []);

    return books;
}