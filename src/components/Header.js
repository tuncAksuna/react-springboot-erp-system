import React from 'react'
import { NavLink } from 'react-router-dom';

const Header = () => {
    return (
        <>
            <header className='header'>
                <nav className='navbar navbar-expand-md navbar-dark bg-dark'>
                    <div>
                        <NavLink to="/employees" activeclassname="active" className="navbar-brand">
                            Main Page
                        </NavLink>
                    </div>
                </nav>
            </header>
        </>
    )
}

export default Header;
