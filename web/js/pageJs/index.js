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


// 显示操作结果提示模态框
function showMsg(type, msg, append) {
    $('#info_success').removeClass("hide");
    $('#info_error').removeClass("hide");
    if (type == "success") {
        $('#info_error').addClass("hide");
    } else if (type == "error") {
        $('#info_success').addClass("hide");
    }
    $('#info_summary').text(msg);
    $('#info_content').text(append);
    $('#global_info_modal').modal("show");
}


