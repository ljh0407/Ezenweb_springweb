import React from 'react';
import styles from './signup.css';
import axios from 'axios';

function Signup(props) {
    // class -> class name 변경
    // 태그 닫기 /tag명
    // 함수호출 -> { } jsx 표현식

    // 1. setmember 이벤트 함수 정의
    const setmember = () => {
         let info = {
                memail : document.querySelector('.memail').value ,
                mpassword : document.querySelector('.mpassword').value,
                mphone : document.querySelector('.mphone').value
            }
            console.log(info)
            // 비동기 통신 [ ajax vs fetch[ react내장 ] vs axios[json이 기본값] ]
                // axios 설치방법
                    // npm : 라이브러리 빌드/관리 [ node.js ]
                    // 1. npm install axios
        // @CrossOrigin(origins = "http://localhost:3000")
        axios           // 3. axios 비동기통신 이용한 서버[spring] 통신
            .post("http://localhost:8080/member/setmember" , info) // 요청
            .then( res => {alert(res.data) } )
     }
    // 2. 인증코드 요청 함수
    const getauth = () => {   alert("클릭이벤트") }
    // 3. 타이머함수
    const settimer = () => {   alert("클릭이벤트") }
    // 4. 인증 버튼 확인 함수
    const authcode = () => {   alert("클릭이벤트") }
    return(
    <div>
        <h3>회원가입</h3>
                <div>
                이메일 : <input type="memail" className="memail" />
                <button type="button" onClick={getauth} className="getauthbtn">인증코드받기</button><br/>
                    <div className="authbox">
                        <input type="text" className="authinput" />
                        <button type="button" className="authbtn" onClick={authcode}>인증</button>
                        <span className="timerbox"></span>
                    </div>
                </div>
                핸드폰 : <input type="mphone" className="mphone" /><br/>
                비밀번호 : <input type="mpassword" className="mpassword" /><br/>
                <button type="button" onClick={setmember}>가입하기</button>
            </div>
    );
}

export default Signup;