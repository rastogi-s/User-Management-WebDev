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
        $searchBtn.click(findUserById);
        $removeBtn.click(function (){
            deleteUser($(this));
        });
        $editBtn.click(updateUser);
        $updateBtn.click();
        userService = new AdminUserServiceClient();
        findAllUsers();

    }

    function createUser() {
        console.log('create user');
        var user = new User($usernameFld.val(), $passwordFld.val(), $firstNameFld.val(), $lastNameFld.val(),
           "","",$roleFld.val(),"");
        console.log(JSON.stringify(user));
        userService.createUser(user,renderUser);
    }

    function findAllUsers() {
        console.log('find all users');
        userService.findAllUsers(renderUsers);

    }

    function findUserById() {
        console.log('find user by id');

    }

    function deleteUser($btn) {

        console.log('remove user');
        var $row=$btn.parents('.wbdev-user');
        var id=$row.find('.wbdev-hidden-id').text();
        userService.deleteUser(id,renderUsers);
        $row.remove();

    }

    function selectUser() {
        console.log('select user');
    }

    function updateUser() {
        console.log('update user');
    }

    function renderUser(user) {
        console.log('render single user');
        var userObj = new User(user.username, user.password, user.firstName,
            user.lastName, user.email, user.phone, user.role, user.dateOfBirth);
        var $row = $userRowTemplate.clone();
        $row.find('.wbdev-username').text(userObj.getUsername());
        $row.find('.wbdev-password').text(userObj.getPassword());
        $row.find('.wbdev-first-name').text(userObj.getFirstName());
        $row.find('.wbdev-last-name').text(userObj.getLastName());
        $row.find('.wbdev-role').text(userObj.getRole());
        $row.append("<td class='wbdev-hidden-id' hidden >"+ user.id+"</td>");
        $editBtn = $row.find('#wbdev-edit');
        $removeBtn = $row.find('.wbdev-remove');
        $removeBtn.click(function (){
            deleteUser($(this));
        });
        $editBtn.click(updateUser);
        $tbody.append($row);
    }

    function renderUsers(users) {
        console.log('render all users');
        $tbody.empty(); // delete all the content of the body
        for (var u in users) {
            var user = new User(users[u].username, users[u].password, users[u].firstName,
                users[u].lastName, users[u].email, users[u].phone, users[u].role, users[u].dateOfBirth);
            var $row = $userRowTemplate.clone();
            $row.find('.wbdev-username').text(user.getUsername());
            $row.find('.wbdev-password').text(user.getPassword());
            $row.find('.wbdev-first-name').text(user.getFirstName());
            $row.find('.wbdev-last-name').text(user.getLastName());
            $row.find('.wbdev-role').text(user.getRole());
            $row.append("<td class='wbdev-hidden-id' hidden >"+ users[u].id+"</td>");
            $editBtn = $row.find('#wbdev-edit');
            $removeBtn = $row.find('.wbdev-remove');
            $removeBtn.click(function (){
                deleteUser($(this));
            });
            $editBtn.click(updateUser);
            $tbody.append($row);
        }
    }
})();


