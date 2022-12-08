// p. 249
// 1. 컴포넌트 첫글자 대문자 꼭
// 2. 클래스 컴포넌트 / 함수컴포넌트
// 3. this.필드명 : 현재클래스의 멤버[필드.함수.생성자]

import React from 'react'

class Ex1_Event extends React.Component {
    // 1. 생성자
    constructor(props) {
        super(props)
        this.state = { isToggleOn : true}; // 메모리관리
    }

    // 2. 이벤트함수
    handleClick(){  // prevState : state 값
        this.setState( prevState=>({
            isToggleOn : !prevState.isToggleOn
        }));
    }
    // -------------------바인딩을 하지 않고 2번째방법------------------------
    handleClick = () => {
        this.setState( prevState=>({
            isToggleOn : !prevState.isToggleOn
            }));
        }

    // 3.번째 방법
    handleClick(){
        this.setState( prevState=>)
    }

    // 2. 렌더링함수
    render(){

    return(
        <button onClick={this.handleClick}>
            { this.state.isToggleOn ? '켜짐' : '꺼짐'}
        </button>
        );
    }
}
export default Ex1_Event

/*
function Ex1_Event(props) {
    return();
}

export default Ex1_Event*/
