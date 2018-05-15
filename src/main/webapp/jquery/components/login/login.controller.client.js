(function () {
    var $usernameFld, $passwordFld, $usernameVerify, $emailReset;
    var $loginBtn, $sendEmailBtn,$forgotPassLink;
    var userService;
    var resetUrl='/jquery/components/passwordreset/reset-password.template.client.html';
    $(main);
    $('.form-control').focus(validate)

    function main() {
        $usernameFld = $('#username');
        $passwordFld = $('#password');
        $loginBtn = $('#login');
        $sendEmailBtn = $('#sendEmail');
        $usernameVerify = $('#usernameVerify');
        $emailReset = $('#resetEmail');
        $forgotPassLink = $('.forgot-pass');
        $forgotPassLink.click(clearFields);
        $sendEmailBtn.click(sendEmail);
        $loginBtn.click(login);
        userService = new UserServiceClient();

    }


    function login(event) {
        var validation = disableBrowserValidations(event,0);
        if (validation == true) {
            userService.login($usernameFld.val(), $passwordFld.val(), success);
        }
    }

    function success(userObj) {
        var user = document.getElementById('username');
        var password = document.getElementById('password');
        if (userObj == null) {
            user.classList.add('is-invalid');
            password.classList.add('is-invalid');
            $('.alert').css('display', 'block');

        }
        else {
            var action = "../profile/profile.template.client.html";
            $('.needs-validation').attr('action', action);
            localStorage["id"] = userObj.id;
            $('.needs-validation').submit();

        }

    }

    function disableBrowserValidations(event,i) {
        var form = document.getElementsByClassName('needs-validation')[i];
        if (form.checkValidity() === false) {
            event.preventDefault();
            event.stopPropagation();
            form.classList.add('was-validated');
            return false;
        }

        return true;
    }

    function sendEmail(event) {
        var validation = disableBrowserValidations(event,1);
        if (validation === true)
            userService.verifyUser($usernameVerify.val(), verifyUsername);
    }

    function verifyUsername(users) {
        var user=users[0];
        if (user != null) {
            var url=window.location.href;
            console.log(url);
            url=url.slice(0,url.lastIndexOf('jquery')-1);
            console.log(url);
            console.log(url+ resetUrl+"?id=" + user.id);
            userService.sendPasswordResetEmail($emailReset.val(),url+ resetUrl+"?id=" + user.id, emailsuccess);
        } else {
            var userVerifyFld = document.getElementById('usernameVerify');
            userVerifyFld.classList.add('is-invalid');

        }
    }

    function emailsuccess() {
        console.log('email-sent');
        $('.alert').css('display', 'block');
        $('.alert').text('Email to reset password sent successfully!!. Wait a few minutes the email will be right in your inbox!!');
        $('.modal').modal('hide');

    }

    function validate(){
        var username = document.getElementById('usernameVerify');
        username.classList.remove('is-invalid');
    }

    function clearFields(){
        $('.form-control').val('');
    }


})();

function initialize() {

    var password = document.getElementById('password');
    var username = document.getElementById('username');
    password.classList.remove('is-invalid');
    username.classList.remove('is-invalid');
    $('.alert').css('display', 'none');

}