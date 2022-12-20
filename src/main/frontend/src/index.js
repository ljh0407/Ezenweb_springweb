
// jsx : 리액트 확장 표현식 파일
// 컴포넌트 단위 애플리케이션 제작
// SPA : 싱글 페이지 애플리케이션 [ 페이지는 하나다 ]
    // 라우터 라이브러리 : 가상 URL
// 컴포넌트 만들기 준비물
    // 1. 첫글자는 대문자 [ 컴포넌트명 == 파일명 ]
    // 2. 리액트[프레임워크가 아니다] 라이브러리 집합소 [ import 많다 ]
        // 1. import React from 'react';
        // 2. function 컴포넌트명(){  return ( 렌더링할 코드 ); }
        // 3. export default 컴포넌트명 ;
               // 2,3 : export default function 컴포넌트명(){  return ( 렌더링할 코드 ); }

import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';


import Index from '../../../../../Ezenweb_springweb/src/main/frontend/src/component';
import Signup from '../../../../../Ezenweb_springweb/src/main/frontend/src/component/member/Signup';
// 1. 사용할 컴포넌트 호출 [import 컴포넌트명 from 파일명]
import Library from '../../../../../Ezenweb_springweb/src/main/frontend/src/Book/chapter3/Library'
import Clock from '../../../../../Ezenweb_springweb/src/main/frontend/src/Book/chapter4/Clock'
import CommentList from '../../../../../Ezenweb_springweb/src/main/frontend/src/Book/chapter5/CommentList'
import NotificationList from '../../../../../Ezenweb_springweb/src/main/frontend/src/Book/chapter6/NotificationList'
import Counter from '../../../../../Ezenweb_springweb/src/main/frontend/src/Book/chapter7/Ex1_Hook';
import Accommodate from '../../../../../Ezenweb_springweb/src/main/frontend/src/Book/chapter7/Accommodate';
import ConfirmButton from '../../../../../Ezenweb_springweb/src/main/frontend/src/Book/chapter8/ConfirmButton';
import ConfirmButton2 from '../../../../../Ezenweb_springweb/src/main/frontend/src/Book/chapter8/ConfirmButton2';
import TestState from '../../../../../Ezenweb_springweb/src/main/frontend/src/Book/chapter8/TestState';
import LandingPage from '../../../../../Ezenweb_springweb/src/main/frontend/src/Book/chapter9/LandingPage';
import AttendanceBook from '../../../../../Ezenweb_springweb/src/main/frontend/src/Book/chapter10/AttendanceBook';
//import NameForm from './Book/chapter11/Ex1_From';
import SignUp from '../../../../../Ezenweb_springweb/src/main/frontend/src/Book/chapter11/SignUp';


const root = ReactDOM.createRoot(document.getElementById('root'));
// * 프로젝트
root.render(
  <React.StrictMode>
    <Index />
  </React.StrictMode>
);
// 2. 기본값 [Kibrary 컴포넌트를 root에 렌더링]
/*root.render(
  <React.StrictMode>
    <Library />
  </React.StrictMode>
);*/

// 3. [Clock 컴포넌트를 root에 렌더링]
    // 1. setInterval 1초마다 렌더링
    // setInterval( (인수) => {실행문} , 밀리초)
/*
setInterval( ()=>{
    root.render(
      <React.StrictMode>
        <Clock />
      </React.StrictMode>
    );
},1000);
*/

// 4. CommentList
/*root.render(
  <React.StrictMode>
    <CommentList />
  </React.StrictMode>
);*/

/*
// 회원가입
root.render(
  <React.StrictMode>
    <Signup />
  </React.StrictMode>
);
*/

// 6.
/*
root.render(
     <React.StrictMode>
       <NotificationList />
     </React.StrictMode>
  );
*/
// 7.
/*
root.render(
     <React.StrictMode>
       <Counter />
     </React.StrictMode>
);
*/
// 8.
/*
root.render(
     <React.StrictMode>
       <Accommodate />
     </React.StrictMode>
);
*/

// 7. 실습
//root.render(
//  <React.StrictMode>
//    <Accommodate />
//  </React.StrictMode>
//);

// 8. 실습
//root.render(
//  <React.StrictMode>
//    <ConfirmButton />
//  </React.StrictMode>
//);

// 8. 실습
/*root.render(
  <React.StrictMode>
    <ConfirmButton2 />
  </React.StrictMode>
);*/

//root.render(
//  <React.StrictMode>
//    <TestState />
//  </React.StrictMode>
//);

// 실습
/*root.render(
  <React.StrictMode>
    <LandingPage />
  </React.StrictMode>
);*/

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
