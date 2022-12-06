import React from "react";
import Notification from './Notification'

//data
const reservedNotificationfications = [
    { id : 1 ,message: '안녕하세요 오늘 일정 알려드립니다' },
    { id : 2 ,message: '점심식사 시간입니다' },
    { id : 3 ,message: '이제 곧 미팅이 시작됩니다' },
]

//타이머 변수 [ Inrerval ]
var timer

///클래스를 이용한 컴포넌트 만들기
class NotificationList extends React.Component{
    // 1.생성자
    constructor(props) {
        super(props);
        this.state = { notifcations : [ ] } ;  //비어있음
    }
    // 2.함수1 (컴포넌트 생성)
    componentDidMount() {
        const  { notifcations } = this.state; //생명주기
        //  timer = setInterval(() => { 실행코드 } , 1000) ;
        timer = setInterval(() => {
                //0개                3개(data)
            if(notifcations.length < reservedNotificationfications.length){
                const index = notifcations.length;   //0개의 인덱스
                notifcations.push(reservedNotificationfications[index]); //0인덱스에 push로 배열에 추가
                this.setState({notifcations : notifcations});
            }else {

                this.setState({ notifcations : []}) // 상태 초기화

                clearInterval(timer) ;   //타미어 초기화
            }
        },1000)  ;   //1초 마다 함수 실행
    }
    // 추가코드  // NotificationList 컴포넌트 사망시[ 끝났을때 ] timer클리어
    componentWillUnmount(){
    if( timer){
        clearInterval(timer) ; // timer 변수에 setInterval 함수가 있으면
        } // setInterval 종료
    }

    // 함수 2
    render() {
        return(
            <div>
                { this.state.notifcations.map( (n) => {
                    return<Notification id={ n.id } message = {n.message}/>;
                } ) }
            </div>
        )
    }
}

export default NotificationList;
