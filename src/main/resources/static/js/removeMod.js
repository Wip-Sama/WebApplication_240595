document.querySelectorAll(".deleteButton").forEach(button => {
    button.addEventListener("click", function() {
        const row = button.closest("tr");
        row.parentNode.removeChild(row);
    });
});