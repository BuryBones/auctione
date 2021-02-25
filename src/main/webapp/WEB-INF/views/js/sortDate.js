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

			var xDateElement = rows[i].getElementsByTagName("TD")[n].getElementsByClassName("t-date")[0];
			var yDateElement = rows[i + 1].getElementsByTagName("TD")[n].getElementsByClassName("t-date")[0];
			var xTimeElement = rows[i].getElementsByTagName("TD")[n].getElementsByClassName("t-time")[0];
			var yTimeElement = rows[i + 1].getElementsByTagName("TD")[n].getElementsByClassName("t-time")[0];

			var xDate = new Date(xDateElement.innerHTML);
			var yDate = new Date(yDateElement.innerHTML);
			// var xTime = moment(xTimeElement.innerHTML,'HH:mm');
			// var yTime = moment(yTimeElement.innerHTML,'HH:mm');

			// console.log(xTime);
			// console.log(yTime);

			/*
				moment('7:00 am', ['h:m a', 'H:m']); // Wed Dec 30 2015 07:00:00 GMT-0600 (CST)
				moment('17:00', ['h:m a', 'H:m']);   // Wed Dec 30 2015 17:00:00 GMT-0600 (CST)
				moment('17:00 am', ['h:m a', 'H:m']);// Wed Dec 30 2015 17:00:00 GMT-0600 (CST)
				moment('17:00 pm', ['h:m a', 'H:m']);// Wed Dec 30 2015 17:00:00 GMT-0600 (CST)
			*/

			// console.log(xDate);
			// console.log(yDate);

			if (dir == "asc") {
				if (xDate > yDate) {
					shouldSwitch = true;
					break;
				// } else if (xDate == yDate) {
					// if (xTime > yTime) {
						// shouldSwitch = true;
						// break;
					// }
				}
			} else if (dir == "desc") {
				if (xDate < yDate) {
					shouldSwitch = true;
					break;
				// } else if (xDate == yDate) {
					// if (xTime < yTime) {
						// shouldSwitch = true;
						// break;
					// }
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