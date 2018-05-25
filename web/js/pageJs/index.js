$(function() {

    /*首先展示病人信息*/
    $('#panel').load("page/patient_info.jsp");

    $(".list-group-item").click(function() {
        var url = $(this).attr("name");
        $('#panel').mLoading('show');
        delay(function(){
            $('#panel').load(url);
        }, 500);
    });
})

// 动作延时
var delay = (function(){
    var timer = 0;
    return function(callback, ms){
        clearTimeout (timer);
        timer = setTimeout(callback, ms);
    };
})();


