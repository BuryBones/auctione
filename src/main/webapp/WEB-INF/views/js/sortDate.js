function sortTableDate(n) {
	var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
	table = document.getElementById("deals-table");
	switching = true;
	dir = "asc";
	while (switching) {
		switching = false;
		rows = table.rows;
		for (i = 1; i < (rows.length - 1); i++) {
			shouldSwitch = false;

			var xDateElement = rows[i].getElementsByTagName("TD")[n];
			var yDateElement = rows[i + 1].getElementsByTagName("TD")[n];

			var xDate = moment(xDateElement.innerHTML, "YYYY-MM-DD hh:mm:ss");
			var yDate = moment(yDateElement.innerHTML, "YYYY-MM-DD hh:mm:ss");

			if (dir == "asc") {
				if (xDate.isAfter(yDate)) {
					shouldSwitch = true;
					break;
				}
			} else if (dir == "desc") {
				if (xDate.isBefore(yDate)) {
					shouldSwitch = true;
					break;
				}
			}
		}
		if (shouldSwitch) {
			rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
			switching = true;
			switchcount++;
		} else {
			if (switchcount == 0 && dir == "asc") {
				dir = "desc";
				switching = true;
			}
		}
	}
}