/*
	Lightbox JS: Fullsize Image Overlays 
	by Lokesh Dhakar - http://www.huddletogether.com

	For more information on this script, visit:
	http://huddletogether.com/projects/lightbox/

	Licensed under the Creative Commons Attribution 2.5 License - http://creativecommons.org/licenses/by/2.5/
	(basically, do anything you want, just leave my name and link)
	
*/

//
// getPageScroll()
// Returns array with x,y page scroll values.
// Core code from - quirksmode.org
//
function getPageScroll()
{
	var yScroll;
	if (self.pageYOffset)
	{
		yScroll = self.pageYOffset;
	}
	else if (document.documentElement && document.documentElement.scrollTop)
	{
		yScroll = document.documentElement.scrollTop;
	}
	else if (document.body)
	{
		yScroll = document.body.scrollTop;
	}
	arrayPageScroll = new Array('',yScroll) 
	return arrayPageScroll;
}

//
// getPageSize()
// Returns array with page width, height and window width, height
// Core code from - quirksmode.org
// Edit for Firefox by pHaez
//
function getPageSize()
{
	var xScroll, yScroll;
	if (window.innerHeight && window.scrollMaxY)
	{
		xScroll = document.body.scrollWidth;
		yScroll = window.innerHeight + window.scrollMaxY;
	}
	else if (document.body.scrollHeight > document.body.offsetHeight)
	{
		xScroll = document.body.scrollWidth;
		yScroll = document.body.scrollHeight;
	}
	else
	{
		xScroll = document.body.offsetWidth;
		yScroll = document.body.offsetHeight;
	}
	var windowWidth, windowHeight;
	if (self.innerHeight)
	{
		windowWidth = self.innerWidth;
		windowHeight = self.innerHeight;
	}
	else if (document.documentElement && document.documentElement.clientHeight)
	{
		windowWidth = document.documentElement.clientWidth;
		windowHeight = document.documentElement.clientHeight;
	}
	else if (document.body)
	{
		windowWidth = document.body.clientWidth;
		windowHeight = document.body.clientHeight;
	}	
	if (yScroll < windowHeight)
	{
		pageHeight = windowHeight;
	}
	else
	{ 
		pageHeight = yScroll;
	}
	if (xScroll < windowWidth)
	{
		pageWidth = windowWidth;
	}
	else
	{
		pageWidth = xScroll;
	}
	arrayPageSize = new Array(pageWidth,pageHeight,windowWidth,windowHeight) 
	return arrayPageSize;
}

//
// pause(numberMillis)
// Pauses code execution for specified time. Uses busy code, not good.
// Code from http://www.faqts.com/knowledge_base/view.phtml/aid/1602
//
function pause(numberMillis)
{
	var now = new Date();
	var exitTime = now.getTime() + numberMillis;
	while (true)
	{
		now = new Date();
		if (now.getTime() > exitTime) return;
	}
}

//
// getKey(key)
// Gets keycode. If 'x' is pressed then it hides the lightbox.
//
function getKey(e)
{
	if (e == null)
	{
		keycode = event.keyCode;
	}
	else
	{
		keycode = e.which;
	}
	key = String.fromCharCode(keycode).toLowerCase();
	if (key == 'x')
	{
		hideLightbox();
	}
}

//
// listenKey()
//
function listenKey() { document.onkeypress = getKey; }

//
// showLightbox()
// Preloads images. Pleaces new image in lightbox then centers and displays.
//
function showLightbox(objLink)
{
	var objOverlay = document.getElementById('overlay');
	var objLightbox = document.getElementById('lightbox');
	var objCaption = document.getElementById('lightboxCaption');
	var objImage = document.getElementById('lightboxImage');
	var objLoadingImage = document.getElementById('loadingImage');
	var objLightboxDetails = document.getElementById('lightboxDetails');
	var arrayPageSize = getPageSize();
	var arrayPageScroll = getPageScroll();
	if (objLoadingImage)
	{
		objLoadingImage.style.top = (arrayPageScroll[1] + ((arrayPageSize[3] - 35 - objLoadingImage.height) / 2) + 'px');
		objLoadingImage.style.left = (((arrayPageSize[0] - 20 - objLoadingImage.width) / 2) + 'px');
		objLoadingImage.style.display = 'block';
	}
	objOverlay.style.height = (arrayPageSize[1] + 'px');
	objOverlay.style.display = 'block';
	imgPreload = new Image();
	imgPreload.onload=function()
	{
		objImage.src = objLink.href;
		var lightboxTop = arrayPageScroll[1] + ((arrayPageSize[3] - 35 - imgPreload.height) / 2);
		var lightboxLeft = ((arrayPageSize[0] - 20 - imgPreload.width) / 2);
		objLightbox.style.top = (lightboxTop < 0) ? "0px" : lightboxTop + "px";
		objLightbox.style.left = (lightboxLeft < 0) ? "0px" : lightboxLeft + "px";
		objLightboxDetails.style.width = imgPreload.width + 'px';
		if (objLink.getAttribute('title'))
		{
			objCaption.style.display = 'block';
			objCaption.innerHTML = objLink.getAttribute('title');
		}
		else
		{
			objCaption.style.display = 'none';
		}
		if (navigator.appVersion.indexOf("MSIE")!=-1)
		{
			pause(250);
		} 
		if (objLoadingImage)
		{
			objLoadingImage.style.display = 'none';
		}
		selects = document.getElementsByTagName("select");
        for (i = 0; i != selects.length; i++)
		{
			selects[i].style.visibility = "hidden";
		}
		objLightbox.style.display = 'block';
		arrayPageSize = getPageSize();
		objOverlay.style.height = (arrayPageSize[1] + 'px');
		listenKey();
		return false;
	}
	imgPreload.src = objLink.href;
}

//
// hideLightbox()
//
function hideLightbox()
{
	objOverlay = document.getElementById('overlay');
	objLightbox = document.getElementById('lightbox');
	objOverlay.style.display = 'none';
	objLightbox.style.display = 'none';
	selects = document.getElementsByTagName("select");
    for (i = 0; i != selects.length; i++)
	{
		selects[i].style.visibility = "visible";
	}
	document.onkeypress = '';
}

//
// initLightbox()
// Function runs on window load, going through link tags looking for rel="lightbox".
// These links receive onclick events that enable the lightbox display for their targets.
// The function also inserts html markup at the top of the page which will be used as a
// container for the overlay pattern and the inline image.
//
function initLightbox()
{
	if (!document.getElementsByTagName) return;
	var anchors = document.getElementsByTagName("a");
	for (var i=0; i<anchors.length; i++)
	{
		var anchor = anchors[i];
		if (anchor.getAttribute("href") && (anchor.getAttribute("rel") == "lightbox"))
		{
			anchor.onclick = function ()
			{
				showLightbox(this);
				return false;
			}
		}
	}
	var objBody = document.getElementsByTagName("body").item(0);
	var objOverlay = document.createElement("div");
	objOverlay.setAttribute('id','overlay');
	objOverlay.onclick = function () {hideLightbox(); return false;}
	objOverlay.style.display = 'none';
	objOverlay.style.position = 'absolute';
	objOverlay.style.top = '0';
	objOverlay.style.left = '0';
	objOverlay.style.zIndex = '90';
 	objOverlay.style.width = '100%';
	objBody.insertBefore(objOverlay, objBody.firstChild);
	var arrayPageSize = getPageSize();
	var arrayPageScroll = getPageScroll();
	var imgPreloader = new Image();
	imgPreloader.onload=function()
	{
		var objLoadingImageLink = document.createElement("a");
		objLoadingImageLink.setAttribute('href','#');
		objLoadingImageLink.onclick = function () {hideLightbox(); return false;}
		objOverlay.appendChild(objLoadingImageLink);
		var objLoadingImage = document.createElement("img");
		objLoadingImage.src = loadingImage;
		objLoadingImage.setAttribute('id','loadingImage');
		objLoadingImage.style.position = 'absolute';
		objLoadingImage.style.zIndex = '150';
		objLoadingImageLink.appendChild(objLoadingImage);
		imgPreloader.onload = function(){};
		return false;
	}
	imgPreloader.src = loadingImage;
	var objLightbox = document.createElement("div");
	objLightbox.setAttribute('id','lightbox');
	objLightbox.style.display = 'none';
	objLightbox.style.position = 'absolute';
	objLightbox.style.zIndex = '100';	
	objBody.insertBefore(objLightbox, objOverlay.nextSibling);
	var objLink = document.createElement("a");
	objLink.setAttribute('href','#');
	objLink.setAttribute('title','Click to close');
	objLink.onclick = function () {hideLightbox(); return false;}
	objLightbox.appendChild(objLink);
	var imgPreloadCloseButton = new Image();
	imgPreloadCloseButton.onload=function()
	{
		var objCloseButton = document.createElement("img");
		objCloseButton.src = closeButton;
		objCloseButton.setAttribute('id','closeButton');
		objCloseButton.style.position = 'absolute';
		objCloseButton.style.zIndex = '200';
		objLink.appendChild(objCloseButton);
		return false;
	}
	imgPreloadCloseButton.src = closeButton;
	var objImage = document.createElement("img");
	objImage.setAttribute('id','lightboxImage');
	objLink.appendChild(objImage);
	var objLightboxDetails = document.createElement("div");
	objLightboxDetails.setAttribute('id','lightboxDetails');
	objLightbox.appendChild(objLightboxDetails);
	var objCaption = document.createElement("div");
	objCaption.setAttribute('id','lightboxCaption');
	objCaption.style.display = 'none';
	objLightboxDetails.appendChild(objCaption);
	var objKeyboardMsg = document.createElement("div");
	objKeyboardMsg.setAttribute('id','keyboardMsg');
	objKeyboardMsg.innerHTML = 'press <a href="#" onclick="hideLightbox(); return false;"><kbd>x</kbd></a> to close';
	objLightboxDetails.appendChild(objKeyboardMsg);
}

//
// addLoadEvent()
// Adds event to window.onload without overwriting currently assigned onload functions.
// Function found at Simon Willison's weblog - http://simon.incutio.com/
//
function addLoadEvent(func)
{	
	var oldonload = window.onload;
	if (typeof window.onload != 'function')
	{
		window.onload = func;
	}
	else
	{
		window.onload = function()
		{
			oldonload();
			func();
		}
	}
}

addLoadEvent(initLightbox);