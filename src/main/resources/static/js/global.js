var checkboxList = []
function addCheckbox(checkboxId){
    checkboxList.push(checkboxId)
}

function validateCheck(checkboxId){
    if(document.getElementById(checkboxId).checked){
       checkboxList.push(checkboxId)
    } else {
        checkboxList = checkboxList.filter(function(e){
        return e!==checkboxId
        })
    }
    console.log(checkboxList)
}

function activeOption(){
    console.log(checkboxList.length);
    if(checkboxList.length > 0){
         for(var x=0;x < checkboxList.length; x++){
                console.log(checkboxList[x]);
                document.getElementById(checkboxList[x]).checked = true;
         }
    }

}

activeOption();