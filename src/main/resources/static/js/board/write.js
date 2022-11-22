
// 게시글 등록
function setboard(){
    let data = {
        btitle : document.querySelector('.btitle').value ,
        bcontent : document.querySelector('.bcontent').value,
        bfile : document.querySelector('.bfile').value,
        bcno : bcno
    }
    $.ajax({
    url: '/board/setboard' ,
    type: 'POST' ,
    data: JSON.stringify(data) ,
    contentType : 'application/json' , // --> @RequestBody
    success : function(re) {

        if( re == true){
         alert("글등록 완료")
         location.href ="/board/list";}
        else{alert("글작성 실패")}
        }
    })
}

// 2. 게시물 카테고리 등록 메소드
function setbcategory(){
    let data = {bcname : document.querySelector(".bcname").value}
    $.ajax({
    url : "/board/setbcategory",
    type : "post",
    data : JSON.stringify(data),
    contentType : "application/json",
    success : function(re) {
       if(re == true){
       alert("카테고리추가성공")
       bcategorylist()
       }else{alert("카테고리추가실패")}
       }
    })
}
// 카테고리 기본값
let bcno = 2;  // 카테고리 번호 전역변수
// 3. 모든 카테고리 출력
bcategorylist()
function bcategorylist(){
    $.ajax({
    url : "/board/bcategorylist",
    type : "get",
    success : function(re){
    let html = "";
    re.forEach( c => {
    console.log(c)
            html += '<button type="button" onclick="bcnochage('+c.bcno+')">'+c.bcname+'</button>';
            })
            document.querySelector(".bcategorybox").innerHTML = html;
        }
    })
}

// 4. 카테고리를 선택했을때 선택된 카테고리 번호 eodlq
function bcnochage(cno) {bcno = cno; alert(bcno)}
