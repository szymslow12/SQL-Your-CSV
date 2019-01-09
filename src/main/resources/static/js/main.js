function adjustHeight(elem) {
    elem.style.height = (window.innerHeight - document.getElementById("footer").offsetHeight) + "px";
}


function sendQuery() {
    let query = document.getElementById("query").value;

    if (query) {
        send(query);
    } else {
        alert("Query can't be empty!");
    }
}


function send(query) {
    let httpExchange = new XMLHttpRequest();

    httpExchange.open("POST", "/sheetql", true);
    httpExchange.setRequestHeader("Content-Type", "text/plain");

    httpExchange.onreadystatechange = () => {
        if (httpExchange.status === 200 && httpExchange.readyState === 4) {
            document.write(httpExchange.response);
            document.close();
        }
    };

    httpExchange.send(query);
}