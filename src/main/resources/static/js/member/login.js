alert('로그인')
function getmember(){
    let info = {
         memail : document.querySelector(".memail").value,
         mpassword : document.querySelector(".mpassword").value
    }
 $.ajax({
            url : "/member/getmember",
            type : "post",
            data : JSON.stringify(info),
            contentType : "application/json",
            success : function(re) {alert(re)}
        })
}