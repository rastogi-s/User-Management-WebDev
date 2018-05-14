(function () {
    var $passwordFld, $verifyPasswordFld;
    var $resetBtn;
    var userService;
    var id;
    $(main);

    function main() {
        $passwordFld = $('#password');
        $verifyPasswordFld = $('#verifypass');
        $resetBtn = $('#reset');
        $resetBtn.click(reset);
        userService = new UserServiceClient();
        id=fetchId();
    }

    function reset(event) {
        var validation = disableBrowserValidations(event);
        if (validation === true) {
            var user = new User(null, $passwordFld.val(),
                null,null,null,null,null,null).getJsonData();
            userService.updateUser(id,user, success);
            return true;
        }
        else {
            false;
        }
    }

    function success(user) {
        if (user != null) {
            $('.form-control').val('');
            $('.alert').css('display','block');
            var form = document.getElementsByClassName('needs-validation')[0];
            form.classList.remove('was-validated');

        }
        else{
           console.log("not updated");
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

    function fetchId()
    {
        var param = window.location.href.slice(window.location.href.indexOf('?') + 1);
        var values = param.split('=');
        console.log(values[1]);
        return values[1];

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
    $('.alert').css('display','none');

}