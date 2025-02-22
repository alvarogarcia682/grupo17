import React from 'react';
import GlobalRouter from "./routes/GlobalRouter";
import {BookProvider} from "./context/BookContext";
import {Footer} from "./components/Footer";
import {useBooks} from "./hooks/useBooks";

function App() {

    const books = useBooks();

    return (
        <BookProvider initialBooks={books}>
            <GlobalRouter></GlobalRouter>
            <Footer/>
        </BookProvider>
    );
}

export default App;
