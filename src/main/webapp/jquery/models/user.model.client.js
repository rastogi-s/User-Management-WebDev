function User(username, password, firstName, lastName, email, phone, role, dateOfBirth) {
    this.username = username;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phone = phone;
    this.role = role;
    this.dateOfBirth = dateOfBirth;

    // username
    this.setUsername = setUsername;
    this.getUsername = getUsername;

    // password
    this.setPassword = setPassword;
    this.getPassword = getPassword;

    // first name
    this.setFirstName = setFirstName;
    this.getFirstName = getFirstName;

    // last name
    this.setLastName = setLastName;
    this.getLastName = getLastName;

    // email
    this.setEmail = setEmail;
    this.getEmail = getEmail;

    // phone
    this.setPhone = setPhone;
    this.getPhone = getPhone;

    // role
    this.setRole = setRole;
    this.getRole = getRole;

    // dateOfBirth
    this.setDateOfBirth = setDateOfBirth;
    this.getDateOfBirth = getDateOfBirth;


    // getter setter for username
    function setUsername(username) {
        this.username = username;
    }
    function getUsername() {
        return this.username;
    }

    // getter setter for password
    function setPassword(password) {
        this.password = password;
    }
    function getPassword() {
        return this.password;
    }

    // getter setter for first name
    function setFirstName(firstName) {
        this.firstName = firstName;
    }
    function getFirstName() {
        return this.firstName;
    }

    // getter setter for last name
    function setLastName(lastName) {
        this.lastName = lastName;
    }
    function getLastName() {
        return this.lastName;
    }

    // getter setter for email
    function setEmail(email) {
        this.email = email;
    }
    function getEmail() {
        return this.email;
    }

    // getter setter for phone
    function setPhone(phone) {
        this.phone = phone;
    }
    function getPhone() {
        return this.phone;
    }

    // getter setter for last name
    function setRole(role) {
        this.role = role;
    }
    function getRole() {
        return this.role;
    }

    // getter setter for last name
    function setDateOfBirth(dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    function getDateOfBirth() {
        return this.dateOfBirth;
    }

}
