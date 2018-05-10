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
        //alert($usernameFld);
        $usernameFld = $('#usernameFld');
        $passwordFld = $('#passwordFld');
        $firstNameFld = $('#firstNameFld');
        $lastNameFld = $('#lastNameFld');
        $roleFld = $('#roleFld');
        $editBtn = $('#wbdev-edit');
        $removeBtn = $('#wbdev-remove');
        $searchBtn = $('.wbdev-search');
        $createBtn = $('.wbdev-create')
        $updateBtn = $('.wbdev-update');
        $tbody = $('.wbdev-body');
        $userRowTemplate = $('.wbdev-user');
        $createBtn.click(createUser);
        $searchBtn.click(findUserById);
        $removeBtn.click(deleteUser);
        $editBtn.click(updateUser);
        $updateBtn.click();
        userService = new AdminUserServiceClient();
        findAllUsers();

    }

    function createUser() {
        console.log('create user');
        var user = new User($usernameFld.value, $passwordFld.value, $firstNameFld.value, $lastNameFld.value,
            '','',$roleFld.value,'');
        userService.createUser(user,renderUsers);
    }

    function findAllUsers() {
        console.log('find all users');
        userService.findAllUsers(renderUsers);

    }

    function findUserById() {
        console.log('find user by id');
    }

    function deleteUser() {
        console.log('remove user');
    }

    function selectUser() {
        console.log('select user');
    }

    function updateUser() {
        console.log('update user');
    }

    function renderUser(user) {
        console.log('render single user');
    }

    function renderUsers(users) {
        console.log('render all users');
        var $row = $userRowTemplate.clone();
        $userRowTemplate.remove();
        $tbody.empty(); // delete all the content of the body
        for (var u in users) {
            var user = new User(users[u].username, users[u].password, users[u].firstName,
                users[u].lastName, users[u].email, users[u].phone, users[u].role, users[u].dateOfBirth);
            $row.find('.wbdev-username').text(user.getUsername());
            $row.find('.wbdev-password').text(user.getPassword());
            $row.find('.wbdev-first-name').text(user.getFirstName());
            $row.find('.wbdev-last-name').text(user.getLastName());
            $row.find('.wbdev-role').text(user.getRole());
            $tbody.append($row);
        }
    }
})();


