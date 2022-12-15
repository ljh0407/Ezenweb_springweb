// jsx : 리액트 확장 표현식파일
// 컴포넌트 단위 애플리케이션 제작
// SPA :

// 컴포넌트 만들기 준비물
    // 1. 첫글자는 대문자 [ 컴포넌트명 == 파일명 ]
    // 2. 리액트[프레임워크가 아니다] 라이브러리 집합소 [ import가 많다]
        // 1.     import React from 'react';
        // 2.   function 컴포넌트명(){ return( 렌더링할 코드 ); }


// { 주석쓰는방법  중괄호 안에 쓰자}
// 1.
import React from 'react';
import Header from './Header';
import Home from './Home';
import Footer from './Footer';
import Signup from './member/Signup';
import Login from './member/Login';
import BoardList from './board/BoardList';
import BoardWrite from './board/BoardWrite';
import BoardView from './board/BoardView';
import BoardUpdate from './board/BoardUpdate';
import Chatting from './chatting/Chatting';

import Library from '../Book/chapter3/Library';
import Clock from '../Book/chapter4/Clock';
import CommentList from '../Book/chapter5/CommentList';
import NotificationList from '../Book/chapter6/NotificationList';
import Counter from '../Book/chapter7/Ex1_Hook';
import Accommodate from '../Book/chapter7/Accommodate';
import ConfirmButton from '../Book/chapter8/ConfirmButton';
import ConfirmButton2 from '../Book/chapter8/ConfirmButton2';
import TestState from '../Book/chapter8/TestState';
import LandingPage from '../Book/chapter9/LandingPage';
import AttendanceBook from '../Book/chapter10/AttendanceBook';
import SignUp from '../Book/chapter11/SignUp';
import BookList from '../Book/BookList';



// 라우터 설치[ 터미널 ] : npm i react-router-dom  i = install 약자
// import { 모듈명 } from 'react-router-dom';
import { BrowserRouter , Routes , Route, Link , Router } from 'react-router-dom';
    // BrowserRouter : 가상 URL
    // vs HashRouter :
    // Routes :
    // Route : 가상 URL 만들기 --> 해당 URL 에 따른 컴포넌트 렌더링 [ SPA ]
    // Link : <----> a 태그 : 하이퍼링크
            // Link to = "Route URL"
    // Router :
// 3. 컴포넌트 내보내기
export default function Index( props ){
    return  (
        <div className="webbox">
            <BrowserRouter>
                <Header/>
                    <Routes>
                        <Route path="/" element = { <Home />} />
                        <Route path="/member/signup" element={ <Signup/>} />
                        <Route path="/member/login" element={ <Login/>} />
                        <Route path="/board/list" element={ <BoardList/>} />
                        <Route path="/board/write" element={ <BoardWrite/>} />
                        <Route path="/board/view/:bno" element={ <BoardView/>} />
                        <Route path="/board/update/:bno" element={ <BoardUpdate/>} />
                        <Route path="/chatting" element={ <Chatting/>} />

                        <Route path="/book/list" element={ <BookList/>} />

                        <Route path="/chapter3/Library" element={ <Library/>} />
                        <Route path="/chapter4/Clock" element={ <Clock/>} />
                        <Route path="/chapter5/CommentList" element={ <CommentList/>} />
                        <Route path="/chapter6/NotificationList" element={ <NotificationList/>} />
                        <Route path="/chapter7/Accommodate" element={ <Accommodate/>} />
                        <Route path="/chapter8/ConfirmButton2" element={ <ConfirmButton2/>} />
                        <Route path="/chapter9/LandingPage" element={ <LandingPage/>} />
                        <Route path="/chapter10/AttendanceBook" element={ <AttendanceBook/>} />
                        <Route path="/chapter11/SignUp" element={ <SignUp/>} />
                    </Routes>
                <Footer/>
             </BrowserRouter>
        </div>
    );
}

