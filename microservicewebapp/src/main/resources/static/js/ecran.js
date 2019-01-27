var prioriteButton;
function getWindowHeight() {
            var windowHeight=0;
            if (typeof(window.innerHeight)=='number') {
                windowHeight=window.innerHeight;
            } else {
                if (document.documentElement&& document.documentElement.clientHeight) {
                    windowHeight = document.documentElement.clientHeight;
                } else {
                    if (document.body&&document.body.clientHeight) {
                        windowHeight=document.body.clientHeight;
                    }
                }
            }
            return windowHeight;
        }
function getWindowWidth() {
    var windowWidth=0;
    if (typeof(window.innerWidth)=='number') {
        windowWidth=window.innerWidth;
    } else {
        if (document.documentElement&& document.documentElement.clientWidth) {
            windowWidth = document.documentElement.clientWidth;
        } else {
            if (document.body&&document.body.clientWidth) {
                windowWidth=document.body.clientWidth;
            }
        }
    }
    return windowWidth;
}

 function changeImage(){
    //
     var ny=getWindowHeight();
     var nx=getWindowWidth();
         //base 4k
         var rapportZoomL=4096/nx;
         var rapportZoomH=2160/ny;
         if(rapportZoomL<rapportZoomH){
             //le ratio de dézoom est rapportZoomL
             document.body.style.backgroundImage="url('" + document.getElementById('image4K').src + "')";
             document.body.style.backgroundSize="100% auto";
             //recentrage
             document.body.style.backgroundPositionY="center";
         }else{
             //le ratio de dézoom est rapportZoomH
             document.body.style.backgroundImage="url('" + document.getElementById('image4K').src + "')";
             document.body.style.backgroundSize="auto 100%";
             //recentrage
             document.body.style.backgroundPositionX="center";
         }
 }

 function hideMenu() {
    var menu=document.getElementById('menuConnexion');
    if(prioriteButton==false){
        menu.style.display='none';
    }

 }
 function editPriorite(priorite){
    if(priorite==true){
        document.getElementById('menuConnexion').style.display='block';
        prioriteButton=true;
    }else{
        prioriteButton=false;
    }

}