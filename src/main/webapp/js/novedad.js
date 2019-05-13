function procesarElemento(){
    if ( $("[id$=labelEquipoBtn]").hasClass("active") ) {
        document.getElementById("equipoBtn").click();
    }
    if (!$("[id$=labelElementoBtn]").hasClass("active")){
        document.getElementById("elemBtn").click();
    }

}

function procesarEquipo(){
    if ( $("[id$=labelElementoBtn]").hasClass("active") ) {
        document.getElementById("elemBtn").click();
    }
    if (! $("[id$=labelEquipoBtn]").hasClass("active")){
        document.getElementById("equipoBtn").click();
    }
}
