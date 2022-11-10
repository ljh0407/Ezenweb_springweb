alert("확인")
function GetMapping1(){
    $.ajax({
    url:"/api/v1/get-api/hello",
    success : function(re) {alert(re);}
    })
}

function GetMapping2(){
    $.ajax({
    url : "/api/v1/get-api/name",
    success : function(re) {alert(re);}

})

}

function GetMapping3(){
    $.ajax({
    url : "/api/v1/get-api/variable1/{variable}",
    success : function(re) {alert(re);}
    })
    }

function GetMapping4(){
    $.ajax({
    url : "/api/v1/get-api/variable2/{variable}",
    success : function(re) {alert(re);}
    })
    }
function GetMapping5(){
    $.ajax({
    url : "/api/v1/get-api/request1?name=이종훈&email=qweqwe@qwe&organization=zzzz",
    success : function(re) {alert(re);}
    })
    }
function GetMapping6(){
    $.ajax({
    url : "/api/v1/get-api/request2?haha",
    success : function(re) {alert(re);}
    })
    }

function GetMapping7(){
    $.ajax({
    url : "/api/v1/get-api/request3",
     success : function(re) {alert(re);}
    })
    }
function GetMapping8(){
    $.ajax({
    url : "/api/v1/get-api/request3",
    success : function(re) {alert(re);}
    })
    }

    // --------------------------------post----------------------
    function postMapping1(){

        let member = { name : "이종훈" , email : "member@example.com" , organization : "qweqweqwe"}
        $.ajax({
        url : "/api/v1/post-api/domain",
        type : "post",
        contentType : "application/json",
        success : function(re){alert(re); }
    })
    }

    function postMapping2(){

        let member = {
            name : "이종훈",
            email  : "member@example.com",
            organization : "qweqweqwe"
        }

        $.ajax({
        url : "/api/v1/post-api/member",
        type : "post",
        data : JSON.stringify(member),
        contentType : "application/json",   // 전송타입 : application/json
        success : function(re){alert(re); }
        })
    }

    function postMapping3(){
        let member = { name : "이종훈" , email : "member@example.com" , organization : "qweqweqwe"}
        $.ajax({
        url : "/api/v1/post-api/member2",
        type : "post",
        data : JSON.stringify(member),
        contentType : "application/json",
        success :   function(re) {alert(re);}

        })
        }


        //---------------------------------put------------------------------

    function putMapping1(){
            let member = { name : "이종훈" , email : "member@example.com" , organization : "qweqweqwe"}
           $.ajax({
           url : "/api/v1/put-api/member",
           type : "put",
           data : JSON.stringify(member),
           contentType : "application/json",
           success : function(re) {console.log(re)}
           })
      }

      function putMapping2(){
        let member = { name : "이종훈", email : "member@example.com", organization : "qweqweqwe"}
           $.ajax({
           url : "/api/v1/put-api/member1",
           type : "put",
           data : JSON.stringify(member),
           contentType : "application/json",
            success : function(re) {console.log(re)}
        })
      }

      function putMapping3(){
        let member = { name : "이종훈", email : "member@example.com", organization : "qweqweqwe"}
           $.ajax({
           url : "/api/v1/put-api/member2",
           type : "put",
           data : JSON.stringify(member),
           contentType : "application/json",
           success : function(re) {
           console.log(re);
           console.log(re.name);
          // let json = JSON.parse(re); console.log(json);
           }
        })
     }

// -------------------------delete ------------------------
function deleteMapping1(){
   $.ajax({
        url : "/api/v1/delete-api/낄낄",
        type : "delete",
        success : function(re) { alert(re);}
   })
}

function deleteMapping2(){
   $.ajax({
        url : "/api/v1/delete-api/request1?variable=깔깔",
        type : "delete",
        success : function(re) { alert(re);}
   })
}