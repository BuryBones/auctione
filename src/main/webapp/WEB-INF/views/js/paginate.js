$('#demo').pagination({
    dataSource: [1, 2, 3, 4, 5, 6, 7, ... , 40],
    pageSize: 5,
    showGoButton: true,
    autoHidePrevious: true,
    autoHideNext: true,
    callback: function(data, pagination) {
        // template method of yourself
        var html = template(data);
        dataContainer.html(html);
    }
})