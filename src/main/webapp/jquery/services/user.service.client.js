function UserServiceClient() {
    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    this.findUserById = findUserById;
    this.deleteUser = deleteUser;
    this.updateUser = updateUser;
    this.register = register;
    this.login=login;
    this.sendPasswordResetEmail=sendPasswordResetEmail;
    this.verifyUser=verifyUser;
    this.logout=logout;
    this.findLoggedUser=findLoggedUser;
    this.updateUserProfile=updateUserProfile;
    this.url = '/api/user';
    this.urlRegister='/api/register';
    this.urlLoggedUser='/api/logged';
    this.urlUpdateProfile='/api/update';
    this.urlLogin='/api/login';
    this.urlPassReset="/api/reset";
    this.urlVerifyUsername="/api/verify";
    this.urlLogout='/api/logout'
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
            credentials:'same-origin',
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
            credentials:'same-origin',
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
        return fetch(self.urlPassReset,{
            method:'POST',
            body:emailId+" "+pagelink,
            // headers:{
            //     'content-type':'application/json'
            // }
        }).then(callback);
    }

    function verifyUser(username,callback){
        return fetch(self.urlVerifyUsername + '/' + username).then(function (response) {
            console.log(response);
            if(response.headers.get("content-type")!=null)
                return response.json();
            else return null;
        }).then(callback);

    }

     function logout(callback) {
         return fetch(self.urlLogout, {
             method:'POST',
             credentials:'same-origin'
         }).then(callback);
     }
    
     function findLoggedUser(callback){
         return fetch(self.urlLoggedUser,{
        	 credentials:'same-origin'
         }).then(function (response) {
        	 if(response.headers.get("content-type")!=null)
                 return response.json();
             else return null;
         }).then(callback);
     }
     
     function updateUserProfile(user, callback) {
         return fetch(self.urlUpdateProfile, {
             method: 'PUT',
             body: JSON.stringify(user),
             credentials:'same-origin',
             headers: {
                 'content-type': 'application/json'
             }
         }).then(function (response) {
             return response.json();
         }).then(callback);
     }
}
