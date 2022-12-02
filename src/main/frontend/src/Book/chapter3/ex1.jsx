//  리액트 사용하기 전 js
const name = '소플';  // js 문자 변수
const element = '<h1>안녕, '+name+'</h1>';
document.querySelector("#root").innerHTML = element;

// p. 104 리액트사용
// ------------------------- 변수 표현식 -----------------------
const name = '소플';  // js 문자 변수
const element = <h1>안녕, {name}</h1>;  // js + html => jsx
        // 변수 호출 표현식 : { 변수명 }

// React.render ( 요소변수 , dom컨테이너 )
React.render( element , document.getElementById('#root') );
// ------------------------- 함수 표현식 ---------------------
function formatName(user){
return user.firstName +'  '+ user.lastName;}

// 객체
const user = {    firstName : 'inje',  lastName : 'lee' };

const element = ( <h1> Hello , {formatName(user)}</h1> )
// 해당 id에 html 렌더링 (뿌려주기/넣어주기/표시해주기)
React.render ( element , document.querySelector('#root') );
//------------------------- 활용 -----------------------

function getGreeting(user){
    if(user){
    return <h1>Hello , {formatName(user)} ! </h1>; // 유저가 있을경우
    }
    return <h1> Hello, Stranger. </h1> // 유저가 없을경우
}

// ------------------------ JSX 식 HTML ---------------------------
const element = (<div> <h1>안녕하세요</h1> <h2> 열심히 리액트를 공부해 봅시다!</h2> </div>)
const element = '<div> <h1>안녕하세요</h1> <h2> 열심히 리액트를 공부해 봅시다!</h2> </div>'