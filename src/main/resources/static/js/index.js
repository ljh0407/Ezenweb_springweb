alert('인덱스')
getloginMno()
function getloginMno(){
    $.ajax({
    url : "/member/getloginMno",
    type : "get",
    success : function(re) {

        alert(re)
        let headerbox = ' ';
        if( re == "0"){ // 로그인 안했다
            headerbox +=
                        '<a href="/member/signup"><button type="button" onclick="">회원가입</button></a>'+
                             '<a href="/member/login"><button type="button"onclick="">로그인</button></a>'
        }else{ // 로그인 했을때
             headerbox +=
                                   ' <a href="/member/logout"> <button type="button" onclick="getlogoutMno()">로그아웃</button></a>'+
                                   '<a href="/member/signup"> <button type="button" onclick="">비밀번호찾기</button></a>'+
                                   '<a href="/member/findpassword"><button type="button" onclick="">비밀번호수정</button></a>'+
                                  ' <a href="/member/delete"> <button type="button" onclick="">회원탈퇴</button></a>'
        }
        document.querySelector(".headerbox").innerHTML = headerbox ;
        }
    })
}

 function getlogoutMno(){

alert('로그아웃')
 $.ajax({
    url : "/member/getlogoutMno",
    type : "get",
    success : function(re) { location.href="/"; }
 })

 }