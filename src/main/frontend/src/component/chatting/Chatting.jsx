import React , { useState , useEffect , useRef } from "react";

export default function Chatting(props) {

    // * 서버소켓과 연결 여부 저장하는 메모리
    const [ socketConn , setSockConn ] = useState(false);
    // * 서버소켓으로 부터 들어온 메시지들을 저장하는 메모리
    const [ msgList , setMsgList ] = useState( [] );

    // * 클라이언트소켓 [ 재 렌더링될때마다 상태 유지 --> useRef 사용
    let ws = useRef(null); // useState 수정될때마다 해당 컴포넌트 재렌더링 // useRef 재렌더링될때 적용X

    // * 컴포넌트 mount 될때 서버소켓 연결 , unmount 될때 서버소켓 닫기
    // useEffect( () => {} , []) , useEffect( 함수명, [] ); <<< useEffect 원형
    useEffect( () => {
        if( !ws.current ){ // 클라이언트 소켓이 없으면
            // 클라이언트 소켓 생성
            ws.current = new WebSocket('ws://localhost:8080/chat');  // 클라이언트소켓 생성 [ 서버소켓 주소 ]
            // 1. 클라이언트 소켓 open
            ws.current.onopen = () => {
            setSockConn(true);
            }
            // 2. 클라이언트 소켓 close
            ws.current.onclose = (e) => {
            console.log("닫기 :"+e);
            }
            // 3. 클라이언트 소켓 error
            ws.current.onerror = (e) => {
            console.log("에러 :"+e);
            }
            // 4. 서버소켓으로부터 message 받았을때
            ws.current.onmessage = (e) => {
                let data = JSON.parse(e.data);
                setMsgList( (prevMsgList) => [ ...prevMsgList , data ] ); // 기존값 + 새로운값 => 상태 업데이트
            }
         }
     }  , [] ) ;



    // * 전송버튼 클릭했을때 이벤트
    const onMsg = () => {

        let msg = document.querySelector('.msgInput').value;        // 1. 입력받은 데이터 가져오기
        ws.current.send( JSON.stringify( { message : msg } ));      // 2. 웹소켓 이용한 send 메소드 사용 [ JSON 형식 ]
        document.querySelector('.msgInput').value =''               // 3. 입력상자 초기화

    }

    return(
        <div>
            접속상태 : <span>{ socketConn }</span>
            채팅입력 : <input type="text" className="msgInput" />
            <button type="button" onClick={onMsg}> 전송 </button>

            <div>
                <h6> 채팅창 </h6>
                {
                    msgList.map( (msg) => {
                        return <div> { JSON.stringify(msg) } </div>
                    })
                }
            </div>
        </div>


    )
}

/*
    useRef : 재렌더링 될때 초기화되지않는 메모리
    useState
        1. setState() : 상태 업데이트 : 특정부분 업데이트/추가X --> 덮어쓰기만 가능
        2. prevState() : 이전 상태 호출

        * 상태 리스트에 새로운 값 추가
        setMsglist( (prevMsglist) => [ ...prevMsglist , 새로운값 ] );

*/
/*
    리스트.forEach( () => {}) : 반환 return없다.
    리스트.map( () =>{} )     : 반환 return있다.
*/