function createItem() {

  // gather data
  var name = document.getElementById("name").value;
  var description = document.getElementById("description").value;

  // magic number
  var userId = 7;

  // make and send a request
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      console.log("Ok");
    } else {
      console.log("Error");
    }
  };
  xhttp.open(
    "POST",
    "new-item/new?" +
    "userId=" + userId +
    "&name=" + name +
    "&description=" + description,
    true);
  xhttp.setRequestHeader("Content-Type", "text/plain;charset=UTF-8");
  xhttp.send();
}