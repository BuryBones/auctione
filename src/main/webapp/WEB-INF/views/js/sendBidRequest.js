function sendBid() {

  // business data
  var dealId = document.getElementById("dealId").value;
  var offer = document.getElementById("offer").value;

  // csrf
  var csrfHeader = $("meta[name='_csrf_header']").attr("content");
  var csrfToken = $("meta[name='_csrf']").attr("content");

  // make and send a request
  var xhttp = new XMLHttpRequest();
  xhttp.open(
    "POST",
    "auctions/bid?" +
    "dealId=" + dealId +
    "&offer=" + offer,
    true);
  xhttp.setRequestHeader(csrfHeader, csrfToken);
  xhttp.setRequestHeader("Content-Type", "text/plain;charset=UTF-8");
  xhttp.send();
}
