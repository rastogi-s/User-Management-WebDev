//IIFE
(function () {
    var $usernameFld, $passwordFld, $roleFld;
    var $removeBtn, $editBtn, $createBtn;
    var $searchBtn, $updateBtn;
    var $firstNameFld, $lastNameFld;
    var $userRowTemplate, $tbody;
    //var userService = new AdminUserServiceClient();
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
        $userRowTemplate = $('.wbdev-user');
        $tbody = $('.table-body');
        $createBtn.click(function () {
            createUser();
        });
        $searchBtn.click(function () {
            findUserById();
        });
        $removeBtn.click(function () {
            deleteUser();
        });
        $editBtn.click(function () {
            updateUser();
        });

        $updateBtn.click(function () {

        });



    }

    function createUser() {
        alert('create user');
    }

    function findAllUsers() {
        alert('find all users');
    }

    function findUserById() {
        alert('find user by id');
    }

    function deleteUser() {
        alert('remove user');
    }

    function selectUser() {
        alert('select user');
    }

    function updateUser() {
        alert('update user');
    }

    function renderUser(user) {
        alert('render single user');
    }

    function renderUsers(users) {
        alert('render all users');
    }
})();


