import React from 'react';
import {Link} from "react-router-dom";
import useRedirection from "../hooks/useRedirection";

function Landing() {

    useRedirection("/books", 5000);

    return (
        <div className="landing">
            <div className="loader-container">
                <Link to={"/books"} className="load-link">
                    <h1>Relatos de Papel</h1>
                </Link>
                <div className="loader"></div>
            </div>
        </div>
    );
}

export default Landing;
