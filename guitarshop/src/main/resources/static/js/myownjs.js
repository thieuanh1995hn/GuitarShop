function redirect(goto){
   
    if (goto != '') {
        window.location = goto;
    }
}
var selectEl = document.getElementById('slview');

selectEl.onchange = function(){
    var goto = this.value;
    redirect(goto);
};



