function addNewRow(tableBody, nome, anno, gioco, stato) {
    const newRow = document.createElement('tr');
    newRow.innerHTML = `
        <td>${nome}</td>
        <td><span class="badge bg-primary">${anno}</span></td>
        <td>${gioco}</td>
        <td>${stato}</td>
        <td><button class="btn btn-danger btn-sm deleteButton"><i class="fa-solid fa-trash"></i></button></td>
    `;

    // Add event listener to the new delete button
    const deleteButton = newRow.querySelector(".deleteButton");
    deleteButton.addEventListener("click", function() {
        const row = deleteButton.closest("tr");
        row.parentNode.removeChild(row);
    });

    tableBody.appendChild(newRow);
}
