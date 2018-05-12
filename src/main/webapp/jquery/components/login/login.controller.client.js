(function () {
    var $usernameFld, $passwordFld;
    var $loginBtn;
    var userService;
    $(main);

    function main() {
        $usernameFld = $('#username');
        $passwordFld = $('#password');
        $loginBtn = $('#login')
        userService = new UserServiceClient();
        $loginBtn.click(login);

    }

    function login(event) {
        var validation = disableBrowserValidations(event);
        if (validation == true) {
            userService.login($usernameFld.val(), $passwordFld.val(), success);
        }
    }

    function success(userObj) {
        var user = document.getElementById('username');
        var $errorMessage = $('.message');
        var password = document.getElementById('password');
        if (userObj == null) {
            user.classList.add('is-invalid');
            password.classList.add('is-invalid');
            $errorMessage.text("Entered a wrong username or password!!");

        }
        else {
            $('.needs-validation').submit();

        }

    }

    function disableBrowserValidations(event) {
        var form = document.getElementsByClassName('needs-validation')[0];
        if (form.checkValidity() === false) {
            event.preventDefault();
            event.stopPropagation();
            form.classList.add('was-validated');
            return false;
        }

        return true;
    }


})();

function initialize() {

    var password = document.getElementById('password');
    var username = document.getElementById('username');
    password.classList.remove('is-invalid');
    username.classList.remove('is-invalid');
    $('.message').text("");

}