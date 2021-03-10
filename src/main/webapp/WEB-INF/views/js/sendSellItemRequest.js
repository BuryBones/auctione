function sendSellRequest() {

  // gather data
  var itemId = document.getElementById("itemId").value;

  var initPrice = document.getElementById("init-price").value;
  var stopDate = document.getElementById("until-date").value;
  var stopTime = document.getElementById("until-time").value;

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
    "items.sell?" +
    "userId=" + userId +
    "&itemId=" + itemId +
    "&initPrice=" + initPrice +
    "&stopDate=" + stopDate +
    "&stopTime=" + stopTime,
    true);
  xhttp.setRequestHeader("Content-Type", "text/plain;charset=UTF-8");
  xhttp.send();
}