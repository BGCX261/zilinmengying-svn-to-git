/**
 * confirm delete
 */
function confirmDel(title){
	if(window.confirm("confirm delete:[" + title + "]?")){
		return true;
	}else{
		return false;
	}
}

window.onload = function() {
    /* Initialize menus for IE */
    if ($("primary-nav")) {
        var navItems = $("primary-nav").getElementsByTagName("li");
    
        for (var i=0; i<navItems.length; i++) {
            if (navItems[i].className == "menubar") {
                navItems[i].onmouseover=function() { this.className += " over"; }
                navItems[i].onmouseout=function() { this.className = "menubar"; }
            }
        }
    }
}

/**
  * approve the comment
  */
  
function approveComment(commentId){
	var img = $("img_" + commentId); 
	if(img.title=="Against this comment"){
		againstComment(commentId);
		return;
	}
	BlogFacade.approveComment(commentId,function (){
		alert("Approve Ok!");
	});
	img.src="../img/accept.png";
	img.title="Against this comment";
}

/**
  * against the comment
  */
function againstComment(commentId){
	var img = $("img_" + commentId);
	if(img.title=="Approve this comment"){
		approveComment(commentId);
		return;
	}
	BlogFacade.againstComment(commentId,function (){
		alert("Against Ok!");
	}); 
	img.src="../img/not_accept.png";
	img.title="Approve this comment";
}

function useLoadingMessage(message){
	var loadingMessage;
  if (message) loadingMessage = message;
  else loadingMessage = "Loading";
  DWREngine.setPreHook(function() {
    var disabledZone = $('disabledZone');
    if (!disabledZone) {
      disabledZone = document.createElement('div');
      disabledZone.setAttribute('id', 'disabledZone');
      disabledZone.style.position = "absolute";
      disabledZone.style.zIndex = "1000";
      disabledZone.style.left = "0px";
      disabledZone.style.top = "0px";
      disabledZone.style.width = "100%";
      disabledZone.style.height = "100%";
      document.body.appendChild(disabledZone);
      var messageZone = document.createElement('div');
      messageZone.setAttribute('id', 'messageZone');
      messageZone.style.position = "absolute";
      messageZone.style.top = "0px";
      messageZone.style.right = "0px";
      messageZone.style.background = "red";
      messageZone.style.color = "white";
      messageZone.style.fontFamily = "Arial,Helvetica,sans-serif";
      messageZone.style.padding = "4px";
      disabledZone.appendChild(messageZone);
      var text = document.createTextNode(loadingMessage);
      messageZone.appendChild(text);
    }
    else {
      $('messageZone').innerHTML = loadingMessage;
      disabledZone.style.visibility = 'visible';
    }
  });
  DWREngine.setPostHook(function() {
    $('disabledZone').style.visibility = 'hidden';
  });
}