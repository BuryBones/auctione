function sendRegistrationRequest() {

  // gather data
  var login = document.getElementById("login").value;
  var password = document.getElementById("password").value;
  var email = document.getElementById("email").value;
  var firstName = document.getElementById("firstName").value;
  var lastName = document.getElementById("lastName").value;

  // make and send a request
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      console.log(this.response);
    }
  };
  xhttp.onload = function() {
      console.log(this.response);
  }
  body = "login=" + login +
         "&password=" + password +
         "&email=" + email +
         "&firstName=" + firstName +
         "&lastName=" + lastName;
  xhttp.open("POST","registration",true);
  xhttp.setRequestHeader("Content-Type", "text/plain;charset=UTF-8");
  xhttp.send(body);
}
