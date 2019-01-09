function adjustHeight(elem) {
    elem.style.height = (window.innerHeight - document.getElementById("footer").offsetHeight) + "px";
}


function sendQuery() {
    let textArea = document.getElementById("query");
    let query = textArea.value;
    alert(query);
}