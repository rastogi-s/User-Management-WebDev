function AdminUserServiceClient() {
	this.createUser = createUser;
	this.findAllUsers = findAllUsers;
	this.findUserById = findUserById;
	this.deleteUser = deleteUser;
	this.updateUser = updateUser;
	this.url = 'http://localhost:8080/api/user';
	var self = this;
	function createUser(user, callback) {
		return fetch(self.url,{
			method:'POST',
			body:JSON.stringyfy(user)
		});
	}
	function findAllUsers(callback) {
		return $.ajax({
			url:self.url,
			success:callback
		});
	}
	function findUserById(userId, callback) {
		return fetch(self.url+'/'+userId)
	}
	function updateUser(userId, user, callback) {
		return fetch(self.url+'/'+userId,{
			method:'PUT',
			body:JSON.stringyfy(user)
		});
	}
	function deleteUser(userId, callback) {
	}
}
