// 1.
import React , {useState} from 'react';
import StyleSheet from '../css/Header.css';
import logo from '../img/EzenWeb.png';
import { BrowserRouter , Routes , Route, Link , Router } from 'react-router-dom';
import axios from 'axios'; // react 비동기 통신 라이브러리 [ npm i axios ]

// 2.
export default function Header() {

    const [ login , setLogin ] = useState(null);  // 로그인된 회원정보 state 생명주기 // 변경시 재 렌더링
    // 1. 비동기통신 [ ajax , Fetch(리액트내장라이브러리) , Axios(설치형라이브러리) ]
            // axios : Data type default json
    // 1. 서버와 통신
    axios
            .get("/member/getloginMno")
            .then( (response) => { setLogin( response.data ); } )
    /*
         $.ajax({
            url : "/member/getloginMno",
            type : "get",
            success : function(re) {}
    */
return (
            <div>
                <div className="header">
                    <div className="header_logo">
                        <Link to="/"><img className="logo" src={logo} / ></Link>
                    </div>
                    <ul className = "top_menu">

                        {login == ""?
                            (
                                <>
                                    <li><Link to="/member/signup"> 회원가입 </Link></li>
                                    <li><Link to="/member/login"> 로그인 </Link></li>
                                </>
                            )
                            :
                            (
                                <>
                                    <li>{login}</li>
                                    <li><a href="/member/logout">로그아웃</a></li>
                                    <li><Link to="/book/list">리액트공부방</Link></li>
                               </>
                            )

                        }

                        <li> <Link to="/board/list" > 자유게시판 </Link></li>
                        <li> <Link to="/chatting" > 익명채팅방 </Link></li>
                    </ul>
                </div>
            </div>
    )
}

   // get("url")
               // .post("url" , data)
               // .delete("url")
               // .put("url" , data)
               // .then( 옵션메소드 )
                // .then( (응답변수명) => { 응답 실행문 } )
                // 응답 객체명 : http 응답 객체 반환
                // 응답 데이터 호출 : 객체명.data

/*
    가상 DOM 작성시 주의점
       1. <태그명></태그명> , <태그명/>
       2. ( <태그명></태그명>)
       3-1 (<div> <태그명></태그명> <태그명></태그명> </div>)
       3-2
*/