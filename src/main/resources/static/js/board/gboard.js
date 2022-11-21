alert("비회원게시판")

function setgboard() {
    let data = {
        gbtitle : document.querySelector(".gbtitle").value,
        gbcontent : document.querySelector(".gbcontent").value,
        gbwrite : document.querySelector(".gbwrite").value
    }
    $.ajax({
     url : "/board/setgboard",
     type : "POST",
     data : JSON.stringify(data),
     ContentType : "application/json",
     success : function(re){
     alert(re)
        }
    })
}