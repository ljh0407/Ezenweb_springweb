
// 세션스토리지 호출
let bno = sessionStorage.getItem("bno");

// 2.
getboard()
function getboard(){
    $.ajax({
    url : "/board/getboard",
    type : "GET",
    data : {"bno": bno},
    success : function(re) {console.log(re)}
    })
}

function upboard(){
    let data = {
        btitle : document.querySelector('.btitle').value ,
        bcontent : document.querySelector('.bcontent').value,
        bfile : document.querySelector('.bfile').value,
        bno : bno
    }
    $.ajax({
    url: '/board/upboard' ,
    type: 'put' ,
    data: JSON.stringify(data) ,
    contentType : 'application/json' , // --> @RequestBody
    success : function(re) {

        if( re == true){
         alert("글수정 완료")
         location.href ="/board/view";}
        else{alert("글수정 실패")}
        }
    })
}