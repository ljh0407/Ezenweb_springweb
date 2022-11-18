alert('연동')

function setboard(){
    let data = {
        btitle : document.querySelector('.btitle').value ,
        bcontent : document.querySelector('.bcontent').value,
        bfile : document.querySelector('.bfile').value
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