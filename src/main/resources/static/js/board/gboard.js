alert("비회원게시판")
let gbcno = 0;
// 게시글 등록
function setgboard() {
let data = {
        gbtitle : document.querySelector(".gbtitle").value,
        gbcontent : document.querySelector(".gbcontent").value,
        gbwrite : document.querySelector(".gbwrite").value,
        gbcno : gbcno
    }
    console.log(data)
    $.ajax({
     url : "/board/setgboard",
     type : "post",
     data : JSON.stringify(data),
     contentType : "application/json",
     success : function(re){
     if(re == true){
     alert("글등록완료")
       location.href ="/board/gboard"
     }else{alert('글등록실패')}
        }
    })
}
gboardlist()
// 비회원게시판 글 목록
function gboardlist(){
    console.log(gbcno)
    $.ajax({
    url : "/board/gboardlist",
    type : "get",
    data : {"gbcno" : gbcno},
    success : function(re){
    let html = "<tr><td>제목</td><td>내용</td><td>작성자</td></tr>";
    re.forEach( g => {
    console.log(g)
        html += '<tr><td>'+g.gbtitle+'</td><td>'+g.gbcontent+'</td><td>'+g.gbwrite+'</td></tr>'
    })
    document.querySelector('.gtable').innerHTML = html;
    }
    })
}

// 비회원게시판 카테고리 등록
function setgbcategory(){
    console.log("넘어와제발")
    let data = { gbcname : document.querySelector('.gbcname').value}
    console.log(data)
    $.ajax({
    url : "/board/setgbcategory",
    type : 'post',
    data : JSON.stringify(data) ,
    contentType : "application/json",
    success : function(re) {
        if( re == true ){
        alert("카테고리추가")
        location.href ="/board/gboard"
            }else{alert("카테고리추가실패")}
         }
    })
}

gbcategorylist()
function gbcategorylist(){
    $.ajax({
    url : "/board/gbcategorylist",
    type : 'get',
    success : function(re){
    let html = '<button type="button" onclick="gbcnochage(0)">전체보기</button>';
    alert(re)
    re.forEach( g => {
    console.log(g)
        html += '<button type="button" onclick="gbcnochage('+g.gbcno+')">'+g.gbcname+'</button>';
    })
    document.querySelector(".gbcategorybox").innerHTML = html;
         }
    })
}

// 선택된 카테 고리 번호
function gbcnochage(cno){ gbcno = cno;  alert(gbcno);}