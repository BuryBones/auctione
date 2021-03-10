function setSortValue(sortValue) {
  sortBy = document.getElementById("sortBy");
  sortMode = document.getElementById("sortMode");
  if (sortBy.value == sortValue) {
    sortMode.value = (sortMode.value == "asc") ? "desc" : "asc";
  } else {
    sortBy.value = sortValue;
    sortMode = "asc";
  }
}