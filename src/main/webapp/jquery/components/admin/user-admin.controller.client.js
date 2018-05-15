//IIFE
(function () {
    var $usernameFld, $passwordFld, $roleFld;
    var $removeBtn, $editBtn, $createBtn;
    var $searchBtn, $updateBtn;
    var $firstNameFld, $lastNameFld;
    var $userRowTemplate, $tbody;
    var userService;
    $(main);

    function main() {
        $usernameFld = $('#usernameFld');
        $passwordFld = $('#passwordFld');
        $firstNameFld = $('#firstNameFld');
        $lastNameFld = $('#lastNameFld');
        $roleFld = $('#roleFld');
        $editBtn = $('#wbdev-edit');
        $removeBtn = $('.wbdev-remove');
        $searchBtn = $('.wbdev-search');
        $createBtn = $('.wbdev-create');
        $updateBtn = $('.wbdev-update');
        $tbody = $('.wbdev-body');
        $userRowTemplate = $('.wbdev-user');
        $createBtn.click(createUser);
        $searchBtn.click(selectUser);
        $updateBtn.click(updateUser);
        userService = new UserServiceClient();
        findAllUsers();

    }

    function clearFields(){
        $('.form-control').val('');
        $('.wbdev-form').attr('id','');
    }

    function editUser(event) {
        clearMessage();
        console.log('edit User');
        $removeBtn = $(event.currentTarget);
        var id = $removeBtn.parent().parent().parent().attr('id');
        userService.findUserById(id, populateValues);

    }

    function populateValues(user){
        var userObj=createUserObj(user);
        $usernameFld.val(userObj.getUsername());
        $passwordFld.val(userObj.getPassword());
        $firstNameFld.val(userObj.getFirstName());
        $lastNameFld.val(userObj.getLastName());
        $roleFld.val(userObj.getRole());
        $('.wbdev-form').attr('id',user.id);
    }
    function createUser(event) {
        clearMessage();
        console.log('create user');
        var validation=disableBrowserValidations(event);
        if(validation==true) {
            userService.verifyUser($usernameFld.val(), successVerification);
        }

    }

    function successVerification(users){

        if(users!=null && users[0]!=null){
            console.log('User exists');
            var $validDiv = $('.invalid-username');
            $validDiv.text("Username already exists!! Try another");
            var pass = document.getElementById('usernameFld');
            pass.classList.add('is-invalid');
        }
        else{
            var user = new User($usernameFld.val(), $passwordFld.val(),
                $firstNameFld.val(), $lastNameFld.val(), null, null,
                $roleFld.val(), null);
            console.log(JSON.stringify(user));
            userService.createUser(user, renderUser);
        }

    }

    function findAllUsers() {
        clearMessage();
        console.log('find all users');
        userService.findAllUsers(renderUsers);
    }

    function deleteUser(event) {
        clearMessage();
        console.log('remove user');
        $removeBtn = $(event.currentTarget);
        var id = $removeBtn.parent().parent().parent().attr('id');
        userService.deleteUser(id, findAllUsers);

    }

    function selectUser() {
        clearMessage();
        console.log('select user');
        if($usernameFld.val()!=null && $usernameFld.val()!=""){
            userService.verifyUser($usernameFld.val(),success);

        }
    }

    function success(users){
        if(users!=null && users[0]!=null){
            populateValues(users[0]);
        }
        else{

            var $validDiv = $('.invalid-username');
            $validDiv.text("No user with this username exists!!");
            var pass = document.getElementById('usernameFld');
            pass.classList.add('is-invalid');

        }
    }


    function updateUser(event) {
        clearMessage();
        console.log('update user');
        $updateBtn = $(event.currentTarget);
        var id = $('.wbdev-form').attr('id');
        console.log(id);
        if (id!= null && id!="") {
            var user = new User($usernameFld.val(), $passwordFld.val(),
                $firstNameFld.val(), $lastNameFld.val(),null, null,
                $roleFld.val(),null).getJsonData();

            userService.updateUser(id,user,findAllUsers)
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

    function renderUser(user) {
        console.log('render single user');
        var userObj=createUserObj(user);
        var $row = $userRowTemplate.clone();
        $row.find('.wbdev-username').text(userObj.getUsername());
        $row.find('.wbdev-password').text(userObj.getPassword());
        $row.find('.wbdev-first-name').text(userObj.getFirstName());
        $row.find('.wbdev-last-name').text(userObj.getLastName());
        $row.find('.wbdev-role').text(userObj.getRole());
        $row.attr('id',user.id);
        $row.find('#wbdev-edit').click(editUser);
        $row.find('.wbdev-remove').click(deleteUser);
        $tbody.append($row);
        $('.alert').css('display','block');
        clearFields();
    }

    function renderUsers(users) {
        console.log('render all users');
        $tbody.empty(); // delete all the content of the body
        for (var u in users) {
            var user = new User(users[u].username, users[u].password,
                users[u].firstName, users[u].lastName, users[u].email,
                users[u].phone, users[u].role, users[u].dateOfBirth);
            var $row = $userRowTemplate.clone();
            $row.find('.wbdev-username').text(user.getUsername());
            $row.find('.wbdev-password').text(user.getPassword());
            $row.find('.wbdev-first-name').text(user.getFirstName());
            $row.find('.wbdev-last-name').text(user.getLastName());
            $row.find('.wbdev-role').text(user.getRole());
            $row.attr('id',users[u].id);
            $row.find('#wbdev-edit').click(editUser);
            $row.find('.wbdev-remove').click(deleteUser);
            $tbody.append($row);
        }
        clearFields();
    }



    function createUserObj(jsonObj){

        var userObj = new User(jsonObj.username, jsonObj.password, jsonObj.firstName,
            jsonObj.lastName, jsonObj.email, jsonObj.phone, jsonObj.role,
            jsonObj.dateOfBirth);
        return userObj;
    }
})();

function clearMessage(){
    $('.alert').css('display','none');
    var pass = document.getElementById('usernameFld');
    pass.classList.remove('is-invalid');
    var form = document.getElementsByClassName('needs-validation')[0];
    form.classList.remove('was-validated');

}
