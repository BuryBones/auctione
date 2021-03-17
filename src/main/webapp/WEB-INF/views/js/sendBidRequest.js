function sendBid() {

  // business data
  var dealId = document.getElementById("dealId").value;
  var offer = document.getElementById("offer").value;

  // make and send a request
  var xhttp = new XMLHttpRequest();
  xhttp.open(
    "POST",
    "auctions/bid?" +
    "dealId=" + dealId +
    "&offer=" + offer,
    true);
  xhttp.setRequestHeader("Content-Type", "text/plain;charset=UTF-8");
  xhttp.send();
}
