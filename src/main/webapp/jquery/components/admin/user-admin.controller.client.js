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
        console.log('edit User');
        $updateBtn = $(event.currentTarget);
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
    function createUser() {
        console.log('create user');
        var user = new User($usernameFld.val(), $passwordFld.val(),
            $firstNameFld.val(), $lastNameFld.val(), "", "",
            $roleFld.val(), "");
        console.log(JSON.stringify(user));
        userService.createUser(user, renderUser);
    }

    function findAllUsers() {
        console.log('find all users');
        userService.findAllUsers(renderUsers);
    }

    function deleteUser(event) {

        console.log('remove user');
        $removeBtn = $(event.currentTarget);
        var id = $removeBtn.parent().parent().parent().attr('id');
        userService.deleteUser(id, findAllUsers);

    }

    function selectUser() {
        console.log('select user');
    }

    function updateUser(event) {
        console.log('update user');
        $updateBtn = $(event.currentTarget);
        var id = $('.wbdev-form').attr('id');
        console.log(id);
        if (id!= null && id!="") {
            var user = new User($usernameFld.val(), $passwordFld.val(),
                $firstNameFld.val(), $lastNameFld.val(), "", "",
                $roleFld.val(), "");

            userService.updateUser(id,user,findAllUsers)
        }

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
