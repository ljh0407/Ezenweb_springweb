alert("리스트")

$.ajax({
    url: '/board/getboards',
    type: 'get',
    success: function(re) {
    console.log(re)
    }
})