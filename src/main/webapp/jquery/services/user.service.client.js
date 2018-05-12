function UserServiceClient() {
    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    this.findUserById = findUserById;
    this.deleteUser = deleteUser;
    this.updateUser = updateUser;
    this.register = register;
    this.login=login;
    this.url = 'http://localhost:8080/api/user';
    this.urlRegister='http://localhost:8080/api/register';
    this.urlLogin='http://localhost:8080/api/login';
    var self = this;

    function createUser(user, callback) {

        return fetch(self.url, {
            method: 'POST',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }

        }).then(function (response) {
            return response.json();
        }).then(callback);

    }

    function findAllUsers(callback) {
        return $.ajax({
            url: self.url,
            success: callback
        });
    }

    function findUserById(userId, callback) {
        return fetch(self.url + '/' + userId).then(function (response) {
            return response.json();
        }).then(callback);
    }

    function updateUser(userId, user, callback) {
        return fetch(self.url + '/' + userId, {
            method: 'PUT',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        }).then(function (response) {
            return response.json();
        }).then(callback);
    }

    function deleteUser(userId, callback) {
        return fetch(self.url + '/' + userId, {
            method: 'DELETE',
        }).then(callback);
    }

    function register(user,callback){
        return fetch(self.urlRegister , {
            method: 'POST',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        }).then(function (response) {
            return response.text();
        }).then(callback);

    }

    function login(username,password,callback){
        return fetch(self.urlLogin,{
            method:'POST',
            body:JSON.stringify({username:username,password:password}),
            headers:{
                'content-type':'application/json'
            }
        }).then(function (response) {
            //console.log(response);
            //console.log(response.headers.get("content-type"));
            if(response.headers.get("content-type")!=null)
            return response.json();
            else return null;
        }).then(callback);
    }
}
