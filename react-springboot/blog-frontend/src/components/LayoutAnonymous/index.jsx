import React from "react";
import { Outlet } from "react-router-dom";
import Header from "../Header";

function LayoutAnonymous() {
    return (
        <>
            <Header />
            <Outlet />
        </>
    );
}

export default LayoutAnonymous;
