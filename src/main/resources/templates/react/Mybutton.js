alert('ㅎㅎ')

// 1. 해당 root 라는 id를 갖는 html 태그 호출
const domContainer = document.querySelector("#root")
console.log(domContainer)
// 2. 리액트 렌더링 [ render( 이벤트 , 위치 ) ]
ReactDOM.render( React.createElement(Mybutton) , domContainer)

// 3.
function Mybutton(props){ // props : properties 의 약자
    const [ isClicked , setIsClicked ] = React.useState( false );

    return React.createElement( // React.createElement( <button> )
        'button',
        { onClick : () => setIsClicked( true ) } ,  // 옵션 : 이벤트
        isClicked ? "clicked!" : "Click here!"  // html 작성
    )
}