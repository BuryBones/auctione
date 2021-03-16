var ajaxInterval = setInterval(refresh, 5000);

function refresh() {
  sortBy = document.getElementById("sortBy").value;
  sortMode = document.getElementById("sortMode").value;
  radios = document.getElementsByClassName("show-radio");
  for (btn in radios) {
    if (radios[btn].checked) {
      showDeals = radios[btn].value;
    }
  }
  currentPage = document.getElementById("currentPage").value;
  pageSize = document.getElementById("pageSize").value;

  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      document.getElementById("table-body").innerHTML = this.response;
    }
  };
  xhttp.open(
    "GET",
     "auctions/ajax?status=" + showDeals +
     "&sortBy=" + sortBy +
     "&sortMode=" + sortMode +
     "&currentPage=" + currentPage +
     "&pageSize=" + pageSize,
      true);
  xhttp.send();
}
