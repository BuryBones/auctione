var x = setInterval(function() {

  var dateContainers = document.getElementsByClassName("stopDate");
  var now = new Date().getTime();

  for (row in dateContainers) {
    var destination = moment(dateContainers[row].innerHTML, "YYYY-MM-DD hh:mm:ss").toDate().getTime();
    var distance = destination - now;

    var days = Math.floor(distance / (1000 * 60 * 60 * 24));
    var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
    var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
    var seconds = Math.floor((distance % (1000 * 60)) / 1000);

    document.getElementsByClassName("countdown")[row].innerHTML = days + "d " + hours + "h "
      + minutes + "m " + seconds + "s ";

    if (distance < 0) {
      document.getElementsByClassName("countdown")[row].innerHTML = "CLOSED";
    }
  }
}, 1000);