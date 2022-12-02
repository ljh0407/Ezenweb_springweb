// 1.
import React from 'react';
import StyleSheet from '../css/Header.css';
import logo from '../img/logo.png';
import { BrowserRouter , Routes , Route, Link , Router } from 'react-router-dom';

// 2.
export default function Header(  ) {
return (
            <div>
                <img className="logo" src={logo} / >
                <h3 className="header_name">헤더</h3>
                <ul>
                        <li> <Link to="/"> Home </Link></li>
                        <li> <Link to="/member/signup"> 회원가입 </Link></li>
                        <li> <a href="/member/logout" > 로그아웃 </a></li>
                </ul>
            </div>
            );
}