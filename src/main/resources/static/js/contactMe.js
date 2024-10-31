document.getElementById("submit").addEventListener("click", function(Event) {
    submitForm(Event);
});

document.getElementById("clear").addEventListener("click", function() {
    document.getElementById("contactMeForm").reset();
});

function submitForm(event) {
    event.preventDefault();
    console.log("submit");

    const nome = document.getElementById("nome").value;
    const cognome = document.getElementById("cognome").value;
    const email = document.getElementById("email").value;
    const img = document.getElementById("img").value;
    const domanda = document.getElementById("domanda").value;

    alert("Nome: " + nome + "\nCognome: " + cognome + "\nEmail: " + email + "\nImg: " + img + "\nDomanda: " + domanda);

    document.getElementById("contactMeForm").reset();
}