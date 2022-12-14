// p.313
import React , {useState} from 'react';

export default function NameForm(props){

    const [ value , setValue ] = useState(''); // 컴포넌트에서 사용되는 메모리 // input value
    const handleChange = (e) => { setValue(e.target.value); };

    const [ value2 , setValue2 ] = useState(''); // 컴포넌트에서 사용되는 메모리 // textarea value
    const handleChange2 = (e) => { setValue2(e.target.value); };

    const [ value3 , setValue3 ] = useState('grape'); // 컴포넌트에서 사용되는 메모리 // select value
    const handleChange3 = (e) => { setValue3(e.target.value); };


    return(
        <from>
            <label>
                이름 : <input type="text" value={value} onChange={handleChange} />
            </label>
            <label>
                요청사항 : <textarea value={value2} onChange={handleChange2} />
            </label>
            <label>
                과일을 선택하세요 :
                <select value={value3} onChange={handleChange3}>
                    <option value="apple">사과</option>
                    <option value="banana">바나나</option>
                    <option value="grape">포도</option>
                    <option value="watermelon">수박</option>
                </select>
            </label>
            <button type="submit">제출</button>
        </from>

    );
}