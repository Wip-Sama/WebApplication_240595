document.addEventListener("DOMContentLoaded", function() {
    fetch("/components/navbar.html")
        .then(response => response.text())
        .then(data => {
            const parser = new DOMParser();
            const doc = parser.parseFromString(data, 'text/html');
            const links = doc.querySelectorAll('a.nav-link');
            links.forEach(link => {
                if (link.getAttribute('href') === window.location.pathname) {
                    link.classList.add('active');
                }
            });
            document.getElementById("navbar").innerHTML = doc.body.innerHTML;
        });
    fetch("/components/footer.html")
        .then(response => response.text())
        .then(data => {
            document.getElementById("footer").innerHTML = data;
        });
});