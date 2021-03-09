function sendBid() {

  // sort data
  sortBy = document.getElementById("sortBy").value;
  sortMode = document.getElementById("sortMode").value;
  radios = document.getElementsByClassName("show-radio");
  for (btn in radios) {
    if (radios[btn].checked) {
      showDeals = radios[btn].value;
    }
  }

  // gather data
  var dealId = document.getElementById("dealId").value;
  var offer = document.getElementById("offer").value;

  // hardcoded user id for test
  var userId = 7;

  // make and send a request
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      console.log("Ok");
    } else {
      console.log("Error");
      console.log(this.status);
    }
  };
  xhttp.open(
    "POST",
    "auctions.bid?" +
    "status=" + showDeals +
    "&sortBy=" + sortBy +
    "&sortMode=" + sortMode +
    "&userId=" + userId +
    "&dealId=" + dealId +
    "&offer=" + offer,
    true);
  xhttp.setRequestHeader("Content-Type", "text/plain;charset=UTF-8");
  xhttp.send();
}
