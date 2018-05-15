(function () {
    var $usernameFld, $phoneFld, $emailFld, $role, $dob,$firstName,$lastName ;
    var $logoutBtn, $updateBtn;
    var userService;
    $(main);



    function main() {
        $usernameFld = $('#username');
        $phoneFld = $('#phone');
        $emailFld = $('#email');
        $role = $('#role');
        $dob = $('#dob');
        $firstName=$('#firstname');
        $lastName=$('#lastname');
        $logoutBtn = $('#logout');
        $updateBtn = $('#update');
        userService = new UserServiceClient();
        $logoutBtn.click(logout);
        $updateBtn.click(updateProfile);
        $('.form-control').focus(hidemessage);
        $phoneFld.keypress(phoneEdit);
        fetchUserProfile();

    }

    function fetchUserProfile(){
        var id=localStorage["id"];
        console.log(id);
        userService.findUserById(id,renderUser);
    }

    function logout() {
        //userService.logout(moveOut);
        localStorage["id"]="";
        var action="../login/login.template.client.html";
        $('.needs-validation').attr('action',action);
        $('.needs-validation').submit();
    }

    function updateProfile(event){
        var validation= disableBrowserValidations(event);
        console.log($dob.val());
        if(validation===true) {
            var user = new User($usernameFld.val(),null,$firstName.val(),
                            $lastName.val(),$emailFld.val(),$phoneFld.val(),
                            $role.val(),$dob.val()).getJsonData();
            var id = document.cookie.split('=')[1];
            console.log(id);
            userService.updateUser(id, user, renderUser);
            $('.alert').css('display','block');
        }
    }

    function renderUser(user){
        document.cookie='id='+user.id;
        $usernameFld.val(user.username);
        $firstName.val(user.firstName);
        $lastName.val(user.lastName);
        $phoneFld.val(user.phone);
        $emailFld.val(user.email);
        $role.val(user.role);
        console.log(user.dateOfBirth);
        if(user.dateOfBirth!=null && user.dateOfBirth!="") {
            var date = new Date(user.dateOfBirth);
            $dob.val(formatDate(date));
        }

    }

    function formatDate(date) {
        var d = new Date(date),
            month = '' + (d.getMonth() + 1),
            day = '' + (d.getDate()+1),
            year = d.getFullYear();

        if (month.length < 2) month = '0' + month;
        if (day.length < 2) day = '0' + day;

        return [year, month, day].join('-');
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

    function phoneEdit(){
        console.log($phoneFld.val());

        var text=$phoneFld.val().toString();
        var newPhone=text;
        if(text.length>2 && text.length<6 ){
            if(!(text.indexOf('-')>=0)) {
                newPhone = text.substring(0, 3) + '-' + text.substring(3, text.length+1);
            }
        }
        else if(text.length>7 && text.length<9   ){
            if(text.lastIndexOf('-')==3 ) {
                newPhone = text.substring(0, 7) + '-' + text.substring(7, text.length+1);
            }
        }
        else{
            newPhone=text;
        }

        $phoneFld.val(newPhone);

    }

    function hidemessage(event) {
        $('.alert').css('display','none');
    }

})();
