
getloginMno()
function getloginMno(){
    $.ajax({
    url : "/member/getloginMno",
    type : "get",
    success : function(re) {
        alert(re);
        let headerbox = ' ';
        if( re == ""){ // 로그인 안했다
            headerbox +=
                             '<a href="/member/signup"><button type="button" onclick="">회원가입</button></a>'+
                             '<a href="/member/login"><button type="button"onclick="">로그인</button></a>'
        }else{ // 로그인 했을때
             headerbox +=
                                   ' <a href="/member/logout"> <button type="button">로그아웃</button></a>'+
                                   '<a href="/member/signup"> <button type="button" onclick="">비밀번호찾기</button></a>'+
                                   '<a href="/member/findpassword"><button type="button" onclick="">비밀번호수정</button></a>'+
                                   ' <a href="/member/delete"> <button type="button" onclick="">회원탈퇴</button></a>'

        }
        document.querySelector(".headerbox").innerHTML = headerbox ;
        }
    })
}

 function logout(){

alert('로그아웃')
 $.ajax({
    url : "/member/logout",
    type : "get",
    success : function(re) {
    location.href="/";  // location.href= URL
        }
    })
 }
list()
 // 회원목록
 list()
 function list(){
     $.ajax({
         url : "/member/list",
         type : "get" ,
         success : function(list) {
             let html = '<tr>  <th> 번호 </th> <th> 이메일 </th> <th> 비밀번호 </th></tr>';
             list.forEach( (m) => {
                 html +=
                 '<tr>  <td> '+m.mno+' </td> <td> '+m.memail+' </td> <td> '+m.mpassword+' </td></tr>';
             })
             document.querySelector(".mtable").innerHTML = html;
         }
     })
 }