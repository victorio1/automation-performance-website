function validarForma(forma){
    var environment = forma.environment;
    if(environment.value == ""){
        alert("Debe seleccionar un ambiente");
        return false;
    }

    return true;
}