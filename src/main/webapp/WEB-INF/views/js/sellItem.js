function sellDialog() {
    var modal = document.getElementById("sell-modal");
    var closeBtn = document.getElementById("modal-close");
    document.getElementById("modal-message").textContent = "";
    var rowId = event.target.parentNode.parentNode.id;
    var data = document.getElementById(rowId).querySelectorAll(".row-data");

    document.getElementById("itemId").value = data[0].innerHTML;
    document.getElementById("item-name").textContent = "\"" + data[1].innerHTML + "\"";

    // reset inputs to defaults
    document.getElementById("init-price").value = "";
    document.getElementById("until-date").value = "";
    document.getElementById("until-time").value = "";

    document.getElementById("until-date").min = new Date(new Date().getTime() - new Date().getTimezoneOffset() * 60000).toISOString().split("T")[0];

    modal.style.display = "block";

    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }

    closeBtn.addEventListener("click",function(ev) {
            ev.preventDefault();
    });

    closeBtn.onclick = function() {
        modal.style.display = "none";
    }
}

function validateSellRequest() {
  var initPriceOk = checkInitPrice();
  var stopDateOk = checkStopDateTime();
  var result = initPriceOk && stopDateOk;
  var message = document.getElementById("modal-message");
  if (result) {
    // do nothing
  } else {
    message.style.color = "#ed4f1f";
    if (!initPriceOk) {
      message.textContent = "Lot has NOT been placed. Check initial price.";
    } else if (!stopDateOk) {
      message.textContent = "Lot has NOT been placed. Check close date.";
    }
  }
  return result;
}

function checkInitPrice() {

  var initPrice = document.getElementById("init-price").value;
  return initPrice > 0;
}

function checkStopDateTime() {

  var stopDate = document.getElementById("until-date").value;
  var stopTime = document.getElementById("until-time").value;
  var stopDateTime = stopDate + " " + stopTime;
  result = !(moment(stopDateTime, "YYYY-MM-DD HH:mm:ss").isBefore(moment().add(1,'hours')));
  return result;
}