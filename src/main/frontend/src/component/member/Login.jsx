import React from 'react';

export default function Login( props ) {
    return(
        <div>
               <h3>로그인</h3>
                     <form action="/member/getmember" method="post">
                     이메일 : <input type="text" name="memail" class="memail" /><br/>
                     비밀번호 : <input type="password" name="mpassword" class="mpassword" /><br/>
                     <input type="submit" value="로그인" />
                     <a href="/oauth2/authorization/kakao">카카오로그인</a>
                     <a href="/oauth2/authorization/naver">네이버로그인</a>
                     <a href="/oauth2/authorization/google">구글로그인</a>
                 </form>
        </div>
    );
}

