// p. 300 리스트와 키
import React from 'react';

const students = [  {id: 1, name: "Inje"} ,
                    {id: 2, name: "Steve"} ,
                    {id: 3, name: "Bill"} ,
                    {id: 4, name: "Jeff"}]

export default function AttendanceBook(props) {

    // jsx 표현식{js코드를 넣을수있다}

    return(
        <ul>
            {
                students.map( (s) => {return <li key={s.id}>{s.name}</li>;})
            }
        </ul>

    );
}
