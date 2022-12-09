import React , {useState , useEffect} from 'react';
import axios from 'axios';


let bcno = 0; // 선택한 카테고리 번호 [ 전역변수 ]

export default function  BoardWrite( props ) {

    /*
        우선순위
        1. class => className
        2. onclick => onClick
        3. <태그></태그> , <태그/>

    */

    const [ category , setCategory ] = useState(''); // 입력받은 카테고리 값
    const [ categoryList , setCategoryList ] = useState([]); // 서버로부터 가져온 카테고리 리스트


   // 1.카테고리 목록 가져오기 함수 [ 실행조건 : 페이지가 [열렸을때]렌더링 되었을때 ]
    const getbcategory = () => { // 1.카테고리 목록 가져오기 함수
        axios
            .get("/board/bcategorylist")
            .then( res => {setCategoryList(res.data); console.log(res)})
            .catch( err => {console.log(err);})
    }

    useEffect( getbcategory , [] ); // 페이지가 mount , unmount

    // 2. 카테고리 등록 함수
    const setbcategory = () => {
        alert(category)
        if( category == ''){alert("카테고리명을 입력후 등록해주세요"); return;}

        axios
              .post("/board/setbcategory", { bcname : category } )
              .then( res => {
                if( res.data == true ){alert("카테고리 등록성공"); console.log(res)}
                else{alert("카테고리 등록실패");}
              } )
              .catch( err => {console.log( err );})
     }


     // 3. 입력받은 게시물 등록 함수 [ 실행조건 : 글쓰기 등록 버튼 눌렀을때 ]
    const setboard = () => {
        if(bcno == 0){alert('카테고리를 선택해주세요'); return;}

        let boardform = document.querySelector('.boardform');
        let formdata = new FormData(boardform);
        formdata.set("bcno" , bcno);    // 폼데이터의 카테고리 번호 추가

        axios
            .post("/board/setboard" , formdata , { headers : {'Content-Type': 'multipart/form-data'} } )
            .then( res => {
                    console.log(res.data)
                    if(res.data == true){alert('글작성 성공');}
                    else{alert('글작성 실패');}
            })
            .catch( err => {console.log( err );} )
    }

    return(
        <div>
            <h1>글쓰기 페이지</h1>
                    <input type="text" value = {category} onChange={ (e)=> { setCategory(e.target.value ) }  } />
                    <button type="button" onClick={setbcategory}>카테고리추가</button>
                    <div className="bcategorybox">
                        {
                            categoryList.map( (c) => {
                                return (
                                    <button
                                        key = {c.bcno}
                                        type="button"
                                        onClick={()=>{ bcno = c.bcno; alert(bcno); } }>
                                        {c.bcname}
                                    </button>
                                )
                            })

                        }
                    </div>

                    <form className="boardform">
                        제목 : <input type="text" name="btitle" />
                        내용 : <input type="text" name="bcontent" />
                        첨부파일 : <input type="file" name="bfile" />
                        <button type="button" onClick={setboard}>글등록</button>
                    </form>
        </div>
    );
}