var code ;

$(function() {
    createCode();
})

function submitForm() {
    /*进行验证码验证*/
    validate();
    /*密码加密*/
    encryptPassword();
}

function createCode(){
    code = "";
    var codeLength = 4;
    var checkCode = document.getElementById("code");
    var random = new Array(0,1,2,3,4,5,6,7,8,9,'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R',
        'S','T','U','V','W','X','Y','Z');
    for(var i = 0; i < codeLength; i++) {
        var index = Math.floor(Math.random()*36);
        code += random[index];
    }
    checkCode.innerHTML = code;
}


function validate(){
    var inputCode = document.getElementById("idcode-btn").value.toUpperCase();
    if(inputCode.length <= 0) {
        alert("请输入验证码！");
    }
    else if(inputCode != code ) {
        alert("验证码输入错误！");
        createCode();//刷新验证码
        document.getElementById("input").value = "";
    }
    else {
        alert("^-^");
    }
}


function encryptPassword() {
    var hash=hex_md5(document.getElementById("password").value);
    document.getElementById("password").value=hash;
}