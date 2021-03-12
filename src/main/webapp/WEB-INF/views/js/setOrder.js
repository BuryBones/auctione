function setSortValue(sortValue, cell) {
  // make invisible all arrows
  arrows = document.getElementsByClassName("sort-arrow");
  for (let i = 0; i < arrows.length; i++) {
    arrows[i].style.display = "none";
  }
  index = cell.cellIndex;
  sortBy = document.getElementById("sortBy");
  sortMode = document.getElementById("sortMode");
  if (sortBy.value == sortValue) {
    sortMode.value = (sortMode.value == "asc") ? "desc" : "asc";
  } else {
    sortBy.value = sortValue;
    sortMode = "asc";
  }
  arrow = (sortMode.value == "asc") ? "up_arrow_" : "down_arrow_";
  icon = document.getElementById(arrow + index);
  icon.style.display = "inline-block";
}