let currentPanel=1;

$(function() {
    currentPanel = 1;
})

function choosePanel(i) {
    if(i!=currentPanel){
        document.getElementById("panel_" + i).hidden=false;
        document.getElementById("panel_"+currentPanel).hidden=true;
        currentPanel = i;
    }
}