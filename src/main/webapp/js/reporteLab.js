function procesarGraficas(){

    if ( jQuery('[id$=labelLabsBtn]').hasClass('active')){
        document.getElementById('labsBtn').click();
    }
    if ( !jQuery('[id$=labelGraficasBtn]').hasClass('active') ) {
        document.getElementById('graficasBtn').click();
    }


}

function procesarLaboratorios(){

    if ( jQuery('[id$=labelGraficasBtn]').hasClass('active') ) {
        document.getElementById('graficasBtn').click();
    }
    if (!jQuery('[id$=labelLabsBtn]').hasClass('active')){
        document.getElementById('labsBtn').click();
    }
}
