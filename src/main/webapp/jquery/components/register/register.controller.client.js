(function () {
    var $usernameFld, $passwordFld, $verifyPasswordFld;
    var $registerBtn;
    var userService;
    $(main);

    function main() {
        $usernameFld = $('#username');
        $passwordFld = $('#password');
        $verifyPasswordFld = $('#verifypass');
        $registerBtn = $('#signup');
        $registerBtn.click(register);
        userService = new UserServiceClient();
    }

    function register(event) {
        var validation = disableBrowserValidations(event);
        if (validation === true) {
            var user = new User($usernameFld.val(), $passwordFld.val(),
                null,null,null,null,null,null).getJsonData();
            userService.register(user, success);
            return true;
        }
        else {
            false;
        }
    }

    function success(message) {
        if (message == "FAIL") {
            var pass = document.getElementById('username');
            var $validDiv = $('.invalid-username');
            $validDiv.text("Username already exists!! Try another");
            pass.classList.add('is-invalid');

        }
        else{
            $('.form-control').val('');
            $('.message').text("User Registered Successfully");
            var form = document.getElementsByClassName('needs-validation')[0];
            form.classList.remove('was-validated');
        }

    }

    function matchPassword() {

        return $passwordFld.val() === $verifyPasswordFld.val();
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

function validatePassword(input) {
    var pass = document.getElementById('password');
    if (input.value != pass.value) {
        input.setCustomValidity("Password do not match!");
        var $validDiv = $('.pass-mismatch');
        $validDiv.text("Password do not match!");
    }
    else {
        input.setCustomValidity("");
    }
}

function initialize(input){
   input.classList.remove('is-invalid');
   $('.message').text("");

}