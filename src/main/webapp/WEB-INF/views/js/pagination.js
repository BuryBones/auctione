function go(number) {

  sortBy = document.getElementById("sortBy").value;
  sortMode = document.getElementById("sortMode").value;
  radios = document.getElementsByClassName("show-radio");
  for (btn in radios) {
    if (radios[btn].checked) {
      status = radios[btn].value;
    }
  }
  currentPage = document.getElementById("currentPage").value;
  currentPage = number;
  pageSize = document.getElementById("pageSize").value;

  window.location.href =
    "auctions?status=" + status +
    "&sortBy=" + sortBy +
    "&sortMode=" + sortMode +
    "&currentPage=" + currentPage +
    "&pageSize=" + pageSize;
}