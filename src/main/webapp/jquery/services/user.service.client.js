function UserServiceClient() {
    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    this.findUserById = findUserById;
    this.deleteUser = deleteUser;
    this.updateUser = updateUser;
    this.register = register;
    this.login=login;
    //this.logout=logout;
    //this.findLoggedUser=findLoggedUser;
    this.url = '/api/user';
    this.urlRegister='/api/register';
    //this.urlLoggedUser='http://localhost:8080/api/logged';
    this.urlLogin='/api/login';
    this.urlPassReset="/api/reset"
    //this.urlLogout='http://localhost:8080/api/logout'
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
            if(response.headers.get("content-type")!=null)
            return response.json();
            else return null;
        }).then(callback);
    }

    function sendPasswordResetEmail(emailId,pagelink,callback){
        return fetch(self.urlPassReset+'/'+emailId +'/link/'+pagelink,{
            method:'POST',
            headers:{
                'content-type':'application/json'
            }
        }).then(callback);
    }

    // function logout(callback) {
    //     return fetch(self.urlLogout, {
    //         method:'POST'
    //     });
    // }
    //
    // function findLoggedUser(callback){
    //     return fetch(self.urlLoggedUser).then(function (response) {
    //         return response.json();
    //     }).then(callback);
    // }
}
