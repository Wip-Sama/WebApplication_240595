document.getElementById("addMod").addEventListener("click", function() {
    document.getElementById("modForm").style.display = "block";
});


document.getElementById("modForm").addEventListener("submit", function(event) {
    event.preventDefault();

    const nome = document.getElementById("nome").value;
    const anno = document.getElementById("anno").value;
    const gioco = document.getElementById("gioco").value;
    const stato = document.getElementById("stato").value;

    const tableBody = document.getElementById("modTable").getElementsByTagName('tbody')[0];

    addNewRow(tableBody, nome, anno, gioco, stato);

    document.getElementById("modForm").style.display = "none";
    document.getElementById("newModForm").reset();
});


function addNewRow(tableBody, nome, anno, gioco, stato){
    const newRowHTML = `
        <tr>
            <td>${nome}</td>
            <td><span class="badge bg-primary">${anno}</span></td>
            <td>${gioco}</td>
            <td>${stato}</td>
            <td><button class="btn btn-danger btn-sm deleteButton"><i class="fa-solid fa-trash"></i></button></td>
        </tr>
    `;
    tableBody.innerHTML += newRowHTML;
}

