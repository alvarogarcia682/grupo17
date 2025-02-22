import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Landing from '../views/Landing';
import BookDetails from '../views/BookDetails';
import NotFound from '../views/NotFound';
import {Overview} from "../views/Overview";
import {Header} from "../components/Header";
import {Cart} from "../components/Cart";
import Checkout from "../views/Checkout";

function GlobalRouter() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Landing />} />
                <Route path="/books" element={<Layout><Overview /></Layout>} />
                <Route path="/books/:bookId" element={<Layout><BookDetails /></Layout>} />
                <Route path="/checkout" element={<Layout><Checkout /></Layout>} />
                <Route path="*" element={<Layout><NotFound /></Layout>} />
            </Routes>
        </BrowserRouter>
    );
}

const Layout = ({children}) => (
    <>
        <Header />
        <Cart />
        {children}
    </>
);

export default GlobalRouter;
