let code ;

$(function() {
    /*创建初始验证码*/
    createCode();
    /*表单验证*/
    $('#loginForm').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            name: {
                message: 'The username is not valid',
                validators: {
                    notEmpty: {
                        message: '用户名不能为空'
                    },
                    stringLength: {
                        min: 5,
                        max: 10,
                        message: '用户名最小长度为5，最大长度10'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_\.]+$/,
                        message: '用户名只能由数字和字母构成'
                    }
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    }
                }
            },
            IDcode: {
                validators: {
                    notEmpty: {
                        message: '验证码不能为空'
                    },
                    callback:{
                        message: '验证码错误',
                        callback:function(value, validator,$field){
                            let codeTemp = document.getElementById("code").innerHTML;
                           // console.log("生产的验证码",codeTemp)
                            return value===codeTemp;
                        }
                    }
                }
            }
        }
    });

})

function submitForm() {
    /*密码加密*/
    encryptPassword();
}

/*生产验证码*/
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


/*密码进行加密*/
function encryptPassword() {
    var hash=hex_md5(document.getElementById("password").value);
    document.getElementById("password").value=hash;
}