window.addEventListener("load", function (){
    btn_aggiungi.addEventListener("click", aggiungiRistorante)
    }
);

window.addEventListener("load", function (){
    btn_aggiungi.addEventListener("click", aggiungiRistorante);
});

function aggiungiRistorante(){
    var txtNome = document.querySelector("#nome_rist");
    var txtDescrizione = document.querySelector("#desc_rist");
    var txtUbicazione = document.querySelector("#ub_ristorante");

    var nome = txtNome.value;
    var descrizione = txtDescrizione.value;
    var ubicazione = txtUbicazione.value;

    var ristorante = {
        "nome" : nome,
        "descrizione": descrizione,
        "ubicazione" : ubicazione
    };

    $.ajax({
        url: "/addRistorante",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(ristorante),
        success: function (risposta){
            console.log(risposta);
        }
    });
}