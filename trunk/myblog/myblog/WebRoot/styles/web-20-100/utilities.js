sfHover = function()
{
	var sfEls = document.getElementById("wp-menu-main").getElementsByTagName("LI");
	for (var i=0; i<sfEls.length; i++)
	{
		sfEls[i].onmouseover = function()
		{
			this.className += " sfhover";
		}
		sfEls[i].onmouseout = function()
		{
			this.className = this.className.replace(new RegExp(" sfhover\\b"), "");
		}
	}
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
addLoadEvent(sfHover);