/* FUNCIONES PARA PROYECTO FEDATARIOS */

function aplicarEstiloMensajesNotificacion(){
	var panel = $('.msgPanel');
	if(panel.length > 0){
		var clase = "";
		var primerHijo = panel.children().first();
		if(primerHijo.hasClass('msgError')){
			clase = 'error';
		} else if(primerHijo.hasClass('msgInfo')){
			var mensaje = primerHijo.html();
			mensaje = $.trim(mensaje);
			var inicio = mensaje.substring(0,5);
			if(inicio == 'INFO-'){
				clase = 'info';
			}else{
				clase = 'success';
			}
		} else if(primerHijo.hasClass('msgWarn')){
			clase = 'warning';
		} else if(primerHijo.hasClass('msgFatal')){
			clase = 'fatal';
		}
		
		panel.addClass(clase);
		panel.addClass('msgPanelFloat');
        panel.draggable();

	panel.append('<div class="msgPanelCerrar">X</div>');
	panel.children().last().click(function(){
	    $(this).parent().hide();
	});
    }
}

function aplicarIngresoSoloNumeros(){
    $('.soloNumeros').soloNumeros();
}

function aplicarJavaScripts()
{
    $('.soloNumeros').soloNumeros();
}

//Plugins

(function($) {

	$.fn.soloNumeros = function(){
		return this.keypress($.fn.soloNumeros.keypress);
	};

	$.fn.soloNumeros.keypress = function(e){
	    if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
	        return false;
	    }
	};

	})(jQuery);

function aplicarMascaraSoloFecha(){
	$('.mascaraFecha').mascaraCalendario();
}

function aplicarMascaraFechaHora(){
	$('.mascaraFechaHora').mascaraCalendarioHora();
}

function aplicarJQueryMoneda(){
	$(".mascaraJQueryMoneda").maskMoney();
}

function inicilizarFuncionesMascaras(){
	aplicarEstiloMensajesNotificacion();
	aplicarIngresoSoloNumeros();
	aplicarMascaraSoloFecha();
	aplicarMascaraFechaHora();
	aplicarJQueryMoneda();
}

(function($) {

	$.fn.mascaraCalendario = function(){
		this.children("input").mask('99/99/9999');		
		return false;	
	};
	})(jQuery);

(function($) {

	$.fn.mascaraCalendarioHora = function(){
		this.children("input").mask('99/99/9999 99:99');		
		return false;	
	};

	})(jQuery);



function OnFocusIn(){
    $("#toptabs").on("focusin", "a", function(){
	$(this).css("color","black");
    });
    
    $("#menuAplicacion").on("focusin", "a", function(){
	
	$(this).css({
	    'color':'blue',
	   
	'font-family': 'helvetica',
	'font-size': '11px',
	'border-bottom-style': 'solid',
	'border-bottom-width': '1px'
	});
    });

    $("#frmListaTrabajo").on("focusin", "td", function(){
	$(this).css({
	    'background':'rgba(114, 158, 171, 0.4)'
	});
    });
    
    //radio
    $("#contenedorCentro").on("focusin", "input[type=radio]", function () {
	    $(this).next().css({
		'background':'rgba(114, 158, 171, 0.4)'
	    });
    });
    
    $("#contenedorCentro").on("focusin", "li>a", function(){
	$(this).css({
	    'color': 'rgba(55,107, 135, 0.9)'
	});

    });
    
          
    $("#contenedorBase").on("keypress", "button", function(){
	if (event.keyCode == 13){return false;}

    });


}

function OnFocusOut()
{
    $("#toptabs").on("focusout", "a", function(){
	$(this).css("color","white");
    });
    $("#menuAplicacion").on("focusout", "a", function(){
	$(this).css({
	    'color':'blue',
	   
	'font-family': 'helvetica',
	'font-size': '11px',
	'border-bottom-style': 'none'
	});
    })

     //radio
    $("#contenedorCentro").on("focusout", "input[type=radio]", function () {
	    $(this).next().css({
		'background':'inherit'
	    });
    });
    
    $("#frmListaTrabajo").on("focusout", "td", function(){
	$(this).css({
	    'background': 'inherit',
	    'text-shadow': 'none'
	});
    });
    
    $("#contenedorCentro").on("focusout", "li>a", function(){
	$(this).css({
	    'color': 'inherit'
	});
    });
}

function bloqueoBotonAtras(){
	   window.location.hash="no-back-button";
	   window.location.hash="Again-No-back-button"; //chrome
	   window.onhashchange=function(){window.location.hash="no-back-button";}
}

/****************** CalendarPopup.js*********************/
Calendar = function(firstDayOfWeek, dateStr, onSelected, onClose) {
	this.activeDiv = null;
	this.currentDateEl = null;
	this.getDateStatus = null;
	this.getDateToolTip = null;
	this.getDateText = null;
	this.timeout = null;
	this.onSelected = onSelected || null;
	this.onClose = onClose || null;
	this.dragging = false;
	this.hidden = false;
	this.minYear = 1970;
	this.maxYear = 2050;
	this.dateFormat = Calendar._TT["DEF_DATE_FORMAT"];
	this.ttDateFormat = Calendar._TT["TT_DATE_FORMAT"];
	this.isPopup = true;
	this.weekNumbers = true;
	this.firstDayOfWeek = typeof firstDayOfWeek == "number" ? firstDayOfWeek
			: Calendar._FD;
	this.showsOtherMonths = false;
	this.dateStr = dateStr;
	this.ar_days = null;
	this.showsTime = false;
	this.time24 = true;
	this.yearStep = 2; 
	this.hiliteToday = true;
	this.multiple = null;
	this.table = null;
	this.element = null;
	this.tbody = null;
	this.firstdayname = null;
	this.monthsCombo = null;
	this.yearsCombo = null;
	this.hilitedMonth = null;
	this.activeMonth = null;
	this.hilitedYear = null;
	this.activeYear = null;
	this.dateClicked = false;
	if (typeof Calendar._SDN == "undefined") {
		if (typeof Calendar._SDN_len == "undefined")
			Calendar._SDN_len = 3;
		var ar = new Array();
		for ( var i = 8; i > 0;) {
			ar[--i] = Calendar._DN[i].substr(0, Calendar._SDN_len)
		}
		Calendar._SDN = ar;
		if (typeof Calendar._SMN_len == "undefined")
			Calendar._SMN_len = 3;
		ar = new Array();
		for ( var i = 12; i > 0;) {
			ar[--i] = Calendar._MN[i].substr(0, Calendar._SMN_len)
		}
		Calendar._SMN = ar
	}
};
Calendar._C = null;
Calendar.is_ie = (/msie/i.test(navigator.userAgent) && !/opera/i
		.test(navigator.userAgent));
Calendar.is_ie5 = (Calendar.is_ie && /msie 5\.0/i.test(navigator.userAgent));
Calendar.is_opera = /opera/i.test(navigator.userAgent);
Calendar.is_khtml = /Konqueror|Safari|KHTML/i.test(navigator.userAgent);
Calendar.getAbsolutePos = function(el) {
	var SL = 0, ST = 0;
	var is_div = /^div$/i.test(el.tagName);
	if (is_div && el.scrollLeft)
		SL = el.scrollLeft;
	if (is_div && el.scrollTop)
		ST = el.scrollTop;
	var r = {
		x : el.offsetLeft - SL,
		y : el.offsetTop - ST
	};
	if (el.offsetParent) {
		var tmp = this.getAbsolutePos(el.offsetParent);
		r.x += tmp.x;
		r.y += tmp.y
	}
	return r
};
Calendar.isRelated = function(el, evt) {
	var related = evt.relatedTarget;
	if (!related) {
		var type = evt.type;
		if (type == "mouseover") {
			related = evt.fromElement
		} else if (type == "mouseout") {
			related = evt.toElement
		}
	}
	while (related) {
		if (related == el) {
			return true
		}
		related = related.parentNode
	}
	return false
};
Calendar.removeClass = function(el, className) {
	if (!(el && el.className)) {
		return
	}
	var cls = el.className.split(" ");
	var ar = new Array();
	for ( var i = cls.length; i > 0;) {
		if (cls[--i] != className) {
			ar[ar.length] = cls[i]
		}
	}
	el.className = ar.join(" ")
};
Calendar.addClass = function(el, className) {
	Calendar.removeClass(el, className);
	el.className += " " + className
};
Calendar.getElement = function(ev) {
	var f = Calendar.is_ie ? window.event.srcElement : ev.currentTarget;
	while (f.nodeType != 1 || /^div$/i.test(f.tagName))
		f = f.parentNode;
	return f
};
Calendar.getTargetElement = function(ev) {
	var f = Calendar.is_ie ? window.event.srcElement : ev.target;
	while (f.nodeType != 1)
		f = f.parentNode;
	return f
};
Calendar.stopEvent = function(ev) {
	ev || (ev = window.event);
	if (Calendar.is_ie) {
		ev.cancelBubble = true;
		ev.returnValue = false
	} else {
		ev.preventDefault();
		ev.stopPropagation()
	}
	return false
};
Calendar.addEvent = function(el, evname, func) {
	if (el.attachEvent) {
		el.attachEvent("on" + evname, func)
	} else if (el.addEventListener) {
		el.addEventListener(evname, func, true)
	} else {
		el["on" + evname] = func
	}
};
Calendar.removeEvent = function(el, evname, func) {
	if (el.detachEvent) {
		el.detachEvent("on" + evname, func)
	} else if (el.removeEventListener) {
		el.removeEventListener(evname, func, true)
	} else {
		el["on" + evname] = null
	}
};
Calendar.createElement = function(type, parent) {
	var el = null;
	if (document.createElementNS) {
		el = document.createElementNS("http://www.w3.org/1999/xhtml", type)
	} else {
		el = document.createElement(type)
	}
	if (typeof parent != "undefined") {
		parent.appendChild(el)
	}
	return el
};
Calendar._add_evs = function(el) {
	with (Calendar) {
		addEvent(el, "mouseover", dayMouseOver);
		addEvent(el, "mousedown", dayMouseDown);
		addEvent(el, "mouseout", dayMouseOut);
		if (is_ie) {
			addEvent(el, "dblclick", dayMouseDblClick);
			el.setAttribute("unselectable", true)
		}
	}
};
Calendar.findMonth = function(el) {
	if (typeof el.month != "undefined") {
		return el
	} else if (typeof el.parentNode.month != "undefined") {
		return el.parentNode
	}
	return null
};
Calendar.findYear = function(el) {
	if (typeof el.year != "undefined") {
		return el
	} else if (typeof el.parentNode.year != "undefined") {
		return el.parentNode
	}
	return null
};
Calendar.showMonthsCombo = function() {
	var cal = Calendar._C;
	if (!cal) {
		return false
	}
	var cal = cal;
	var cd = cal.activeDiv;
	var mc = cal.monthsCombo;
	if (cal.hilitedMonth) {
		Calendar.removeClass(cal.hilitedMonth, "hilite")
	}
	if (cal.activeMonth) {
		Calendar.removeClass(cal.activeMonth, "active")
	}
	var mon = cal.monthsCombo.getElementsByTagName("div")[cal.date.getMonth()];
	Calendar.addClass(mon, "active");
	cal.activeMonth = mon;
	var s = mc.style;
	s.display = "block";
	if (cd.navtype < 0)
		s.left = cd.offsetLeft + "px";
	else {
		var mcw = mc.offsetWidth;
		if (typeof mcw == "undefined")
			mcw = 50;
		s.left = (cd.offsetLeft + cd.offsetWidth - mcw) + "px"
	}
	s.top = (cd.offsetTop + cd.offsetHeight) + "px"
};
Calendar.showYearsCombo = function(fwd) {
	var cal = Calendar._C;
	if (!cal) {
		return false
	}
	var cal = cal;
	var cd = cal.activeDiv;
	var yc = cal.yearsCombo;
	if (cal.hilitedYear) {
		Calendar.removeClass(cal.hilitedYear, "hilite")
	}
	if (cal.activeYear) {
		Calendar.removeClass(cal.activeYear, "active")
	}
	cal.activeYear = null;
	var Y = cal.date.getFullYear() + (fwd ? 1 : -1);
	var yr = yc.firstChild;
	var show = false;
	for ( var i = 12; i > 0; --i) {
		if (Y >= cal.minYear && Y <= cal.maxYear) {
			yr.innerHTML = Y;
			yr.year = Y;
			yr.style.display = "block";
			show = true
		} else {
			yr.style.display = "none"
		}
		yr = yr.nextSibling;
		Y += fwd ? cal.yearStep : -cal.yearStep
	}
	if (show) {
		var s = yc.style;
		s.display = "block";
		if (cd.navtype < 0)
			s.left = cd.offsetLeft + "px";
		else {
			var ycw = yc.offsetWidth;
			if (typeof ycw == "undefined")
				ycw = 50;
			s.left = (cd.offsetLeft + cd.offsetWidth - ycw) + "px"
		}
		s.top = (cd.offsetTop + cd.offsetHeight) + "px"
	}
};
Calendar.tableMouseUp = function(ev) {
	var cal = Calendar._C;
	if (!cal) {
		return false
	}
	if (cal.timeout) {
		clearTimeout(cal.timeout)
	}
	var el = cal.activeDiv;
	if (!el) {
		return false
	}
	var target = Calendar.getTargetElement(ev);
	ev || (ev = window.event);
	Calendar.removeClass(el, "active");
	if (target == el || target.parentNode == el) {
		Calendar.cellClick(el, ev)
	}
	var mon = Calendar.findMonth(target);
	var date = null;
	if (mon) {
		date = new Date(cal.date);
		if (mon.month != date.getMonth()) {
			date.setMonth(mon.month);
			cal.setDate(date);
			cal.dateClicked = false;
			cal.callHandler()
		}
	} else {
		var year = Calendar.findYear(target);
		if (year) {
			date = new Date(cal.date);
			if (year.year != date.getFullYear()) {
				date.setFullYear(year.year);
				cal.setDate(date);
				cal.dateClicked = false;
				cal.callHandler()
			}
		}
	}
	with (Calendar) {
		removeEvent(document, "mouseup", tableMouseUp);
		removeEvent(document, "mouseover", tableMouseOver);
		removeEvent(document, "mousemove", tableMouseOver);
		cal._hideCombos();
		_C = null;
		return stopEvent(ev)
	}
};
Calendar.tableMouseOver = function(ev) {
	var cal = Calendar._C;
	if (!cal) {
		return
	}
	var el = cal.activeDiv;
	var target = Calendar.getTargetElement(ev);
	if (target == el || target.parentNode == el) {
		Calendar.addClass(el, "hilite active");
		Calendar.addClass(el.parentNode, "rowhilite")
	} else {
		if (typeof el.navtype == "undefined"
				|| (el.navtype != 50 && (el.navtype == 0 || Math
						.abs(el.navtype) > 2)))
			Calendar.removeClass(el, "active");
		Calendar.removeClass(el, "hilite");
		Calendar.removeClass(el.parentNode, "rowhilite")
	}
	ev || (ev = window.event);
	if (el.navtype == 50 && target != el) {
		var pos = Calendar.getAbsolutePos(el);
		var w = el.offsetWidth;
		var x = ev.clientX;
		var dx;
		var decrease = true;
		if (x > pos.x + w) {
			dx = x - pos.x - w;
			decrease = false
		} else
			dx = pos.x - x;
		if (dx < 0)
			dx = 0;
		var range = el._range;
		var current = el._current;
		var count = Math.floor(dx / 10) % range.length;
		for ( var i = range.length; --i >= 0;)
			if (range[i] == current)
				break;
		while (count-- > 0)
			if (decrease) {
				if (--i < 0)
					i = range.length - 1
			} else if (++i >= range.length)
				i = 0;
		var newval = range[i];
		el.innerHTML = newval;
		cal.onUpdateTime()
	}
	var mon = Calendar.findMonth(target);
	if (mon) {
		if (mon.month != cal.date.getMonth()) {
			if (cal.hilitedMonth) {
				Calendar.removeClass(cal.hilitedMonth, "hilite")
			}
			Calendar.addClass(mon, "hilite");
			cal.hilitedMonth = mon
		} else if (cal.hilitedMonth) {
			Calendar.removeClass(cal.hilitedMonth, "hilite")
		}
	} else {
		if (cal.hilitedMonth) {
			Calendar.removeClass(cal.hilitedMonth, "hilite")
		}
		var year = Calendar.findYear(target);
		if (year) {
			if (year.year != cal.date.getFullYear()) {
				if (cal.hilitedYear) {
					Calendar.removeClass(cal.hilitedYear, "hilite")
				}
				Calendar.addClass(year, "hilite");
				cal.hilitedYear = year
			} else if (cal.hilitedYear) {
				Calendar.removeClass(cal.hilitedYear, "hilite")
			}
		} else if (cal.hilitedYear) {
			Calendar.removeClass(cal.hilitedYear, "hilite")
		}
	}
	return Calendar.stopEvent(ev)
};
Calendar.tableMouseDown = function(ev) {
	if (Calendar.getTargetElement(ev) == Calendar.getElement(ev)) {
		return Calendar.stopEvent(ev)
	}
};
Calendar.calDragIt = function(ev) {
	var cal = Calendar._C;
	if (!(cal && cal.dragging)) {
		return false
	}
	var posX;
	var posY;
	if (Calendar.is_ie) {
		posY = window.event.clientY + document.body.scrollTop;
		posX = window.event.clientX + document.body.scrollLeft
	} else {
		posX = ev.pageX;
		posY = ev.pageY
	}
	cal.hideShowCovered();
	var st = cal.element.style;
	st.left = (posX - cal.xOffs) + "px";
	st.top = (posY - cal.yOffs) + "px";
	return Calendar.stopEvent(ev)
};
Calendar.calDragEnd = function(ev) {
	var cal = Calendar._C;
	if (!cal) {
		return false
	}
	cal.dragging = false;
	with (Calendar) {
		removeEvent(document, "mousemove", calDragIt);
		removeEvent(document, "mouseup", calDragEnd);
		tableMouseUp(ev)
	}
	cal.hideShowCovered()
};
Calendar.dayMouseDown = function(ev) {
	var el = Calendar.getElement(ev);
	if (el.disabled) {
		return false
	}
	var cal = el.calendar;
	cal.activeDiv = el;
	Calendar._C = cal;
	if (el.navtype != 300)
		with (Calendar) {
			if (el.navtype == 50) {
				el._current = el.innerHTML;
				addEvent(document, "mousemove", tableMouseOver)
			} else
				addEvent(document, Calendar.is_ie5 ? "mousemove" : "mouseover",
						tableMouseOver);
			addClass(el, "hilite active");
			addEvent(document, "mouseup", tableMouseUp)
		}
	else if (cal.isPopup) {
		cal._dragStart(ev)
	}
	if (el.navtype == -1 || el.navtype == 1) {
		if (cal.timeout)
			clearTimeout(cal.timeout);
		cal.timeout = setTimeout("Calendar.showMonthsCombo()", 250)
	} else if (el.navtype == -2 || el.navtype == 2) {
		if (cal.timeout)
			clearTimeout(cal.timeout);
		cal.timeout = setTimeout(
				(el.navtype > 0) ? "Calendar.showYearsCombo(true)"
						: "Calendar.showYearsCombo(false)", 250)
	} else {
		cal.timeout = null
	}
	return Calendar.stopEvent(ev)
};
Calendar.dayMouseDblClick = function(ev) {
	Calendar.cellClick(Calendar.getElement(ev), ev || window.event);
	if (Calendar.is_ie) {
		document.selection.empty()
	}
};
Calendar.dayMouseOver = function(ev) {
	var el = Calendar.getElement(ev);
	if (Calendar.isRelated(el, ev) || Calendar._C || el.disabled) {
		return false
	}
	if (el.ttip) {
		if (el.ttip.substr(0, 1) == "_") {
			el.ttip = el.caldate.print(el.calendar.ttDateFormat)
					+ el.ttip.substr(1)
		}
		el.calendar.tooltips.innerHTML = el.ttip
	}
	if (el.navtype != 300) {
		Calendar.addClass(el, "hilite");
		if (el.caldate) {
			Calendar.addClass(el.parentNode, "rowhilite")
		}
	}
	return Calendar.stopEvent(ev)
};
Calendar.dayMouseOut = function(ev) {
	with (Calendar) {
		var el = getElement(ev);
		if (isRelated(el, ev) || _C || el.disabled)
			return false;
		removeClass(el, "hilite");
		if (el.caldate)
			removeClass(el.parentNode, "rowhilite");
		if (el.calendar)
			el.calendar.tooltips.innerHTML = _TT["SEL_DATE"];
		return stopEvent(ev)
	}
};
Calendar.cellClick = function(el, ev) {
	var cal = el.calendar;
	var closing = false;
	var newdate = false;
	var date = null;
	if (typeof el.navtype == "undefined") {
		if (cal.currentDateEl) {
			Calendar.removeClass(cal.currentDateEl, "selected");
			Calendar.addClass(el, "selected");
			closing = (cal.currentDateEl == el);
			if (!closing) {
				cal.currentDateEl = el
			}
		}
		cal.date.setDateOnly(el.caldate);
		date = cal.date;
		var other_month = !(cal.dateClicked = !el.otherMonth);
		if (!other_month && !cal.currentDateEl)
			cal._toggleMultipleDate(new Date(date));
		else
			newdate = !el.disabled;
		if (other_month)
			cal._init(cal.firstDayOfWeek, date)
	} else {
		if (el.navtype == 200) {
			Calendar.removeClass(el, "hilite");
			cal.callCloseHandler();
			return
		}
		date = new Date(cal.date);
		if (el.navtype == 0)
			date.setDateOnly(new Date());
		cal.dateClicked = false;
		var year = date.getFullYear();
		var mon = date.getMonth();
		function setMonth(m) {
			var day = date.getDate();
			var max = date.getMonthDays(m);
			if (day > max) {
				date.setDate(max)
			}
			date.setMonth(m)
		}
		;
		switch (el.navtype) {
		case 400:
			Calendar.removeClass(el, "hilite");
			cal.callCloseHandler();
			return;
		case -2:
			if (year > cal.minYear) {
				date.setFullYear(year - 1)
			}
			break;
		case -1:
			if (mon > 0) {
				setMonth(mon - 1)
			} else if (year-- > cal.minYear) {
				date.setFullYear(year);
				setMonth(11)
			}
			break;
		case 1:
			if (mon < 11) {
				setMonth(mon + 1)
			} else if (year < cal.maxYear) {
				date.setFullYear(year + 1);
				setMonth(0)
			}
			break;
		case 2:
			if (year < cal.maxYear) {
				date.setFullYear(year + 1)
			}
			break;
		case 100:
			cal.setFirstDayOfWeek(el.fdow);
			return;
		case 50:
			var range = el._range;
			var current = el.innerHTML;
			for ( var i = range.length; --i >= 0;)
				if (range[i] == current)
					break;
			if (ev && ev.shiftKey) {
				if (--i < 0)
					i = range.length - 1
			} else if (++i >= range.length)
				i = 0;
			var newval = range[i];
			el.innerHTML = newval;
			cal.onUpdateTime();
			return;
		case 0:
			if ((typeof cal.getDateStatus == "function")
					&& cal.getDateStatus(date, date.getFullYear(), date
							.getMonth(), date.getDate())) {
				return false
			}
			break
		}
		if (!date.equalsTo(cal.date)) {
			cal.setDate(date);
			newdate = true
		} else if (el.navtype == 0)
			newdate = closing = true
	}
	if (newdate) {
		ev && cal.callHandler()
	}
	if (closing) {
		Calendar.removeClass(el, "hilite");
		ev && cal.callCloseHandler()
	}
};
Calendar.prototype.create = function(_par) {
	var parent = null;
	if (!_par) {
		parent = document.getElementsByTagName("body")[0];
		this.isPopup = true
	} else {
		parent = _par;
		this.isPopup = false
	}
	this.date = this.dateStr ? new Date(this.dateStr) : new Date();
	var table = Calendar.createElement("table");
	this.table = table;
	table.cellSpacing = 0;
	table.cellPadding = 0;
	table.calendar = this;
	Calendar.addEvent(table, "mousedown", Calendar.tableMouseDown);
	var div = Calendar.createElement("div");
	this.element = div;
	div.className = "calendar";
	if (this.isPopup) {
		div.style.position = "absolute";
		div.style.display = "none"
	}
	div.appendChild(table);
	var thead = Calendar.createElement("thead", table);
	var cell = null;
	var row = null;
	var cal = this;
	var hh = function(text, cs, navtype) {
		cell = Calendar.createElement("td", row);
		cell.colSpan = cs;
		cell.className = "button";
		if (navtype != 0 && Math.abs(navtype) <= 2)
			cell.className += " nav";
		Calendar._add_evs(cell);
		cell.calendar = cal;
		cell.navtype = navtype;
		cell.innerHTML = "<div unselectable='on'>" + text + "</div>";
		return cell
	};
	row = Calendar.createElement("tr", thead);
	var title_length = 6;
	(this.isPopup) && --title_length;
	(this.weekNumbers) && ++title_length;
	hh("&#x00d7;", 1, 400).ttip = Calendar._TT["CLOSE"];
	this.title = hh("", title_length, 300);
	this.title.className = "title";
	if (this.isPopup) {
		this.title.ttip = Calendar._TT["DRAG_TO_MOVE"];
		this.title.style.cursor = "move";
		hh("&#x00d7;", 1, 200).ttip = Calendar._TT["CLOSE"]
	}
	row = Calendar.createElement("tr", thead);
	row.className = "headrow";
	this._nav_py = hh("&#x00ab;", 1, -2);
	this._nav_py.ttip = Calendar._TT["PREV_YEAR"];
	this._nav_pm = hh("&#x2039;", 1, -1);
	this._nav_pm.ttip = Calendar._TT["PREV_MONTH"];
	this._nav_now = hh(Calendar._TT["TODAY"], this.weekNumbers ? 4 : 3, 0);
	this._nav_now.ttip = Calendar._TT["GO_TODAY"];
	this._nav_nm = hh("&#x203a;", 1, 1);
	this._nav_nm.ttip = Calendar._TT["NEXT_MONTH"];
	this._nav_ny = hh("&#x00bb;", 1, 2);
	this._nav_ny.ttip = Calendar._TT["NEXT_YEAR"];
	row = Calendar.createElement("tr", thead);
	row.className = "daynames";
	if (this.weekNumbers) {
		cell = Calendar.createElement("td", row);
		cell.className = "name wn";
		cell.innerHTML = Calendar._TT["WK"]
	}
	for ( var i = 7; i > 0; --i) {
		cell = Calendar.createElement("td", row);
		if (!i) {
			cell.navtype = 100;
			cell.calendar = this;
			Calendar._add_evs(cell)
		}
	}
	this.firstdayname = (this.weekNumbers) ? row.firstChild.nextSibling
			: row.firstChild;
	this._displayWeekdays();
	var tbody = Calendar.createElement("tbody", table);
	this.tbody = tbody;
	for (i = 6; i > 0; --i) {
		row = Calendar.createElement("tr", tbody);
		if (this.weekNumbers) {
			cell = Calendar.createElement("td", row)
		}
		for ( var j = 7; j > 0; --j) {
			cell = Calendar.createElement("td", row);
			cell.calendar = this;
			Calendar._add_evs(cell)
		}
	}
	if (this.showsTime) {
		row = Calendar.createElement("tr", tbody);
		row.className = "time";
		cell = Calendar.createElement("td", row);
		cell.className = "time";
		cell.colSpan = 2;
		cell.innerHTML = Calendar._TT["TIME"] || "&nbsp;";
		cell = Calendar.createElement("td", row);
		cell.className = "time";
		cell.colSpan = this.weekNumbers ? 4 : 3;
		( function() {
			function makeTimePart(className, init, range_start, range_end) {
				var part = Calendar.createElement("span", cell);
				part.className = className;
				part.innerHTML = init;
				part.calendar = cal;
				part.ttip = Calendar._TT["TIME_PART"];
				part.navtype = 50;
				part._range = [];
				if (typeof range_start != "number")
					part._range = range_start;
				else {
					for ( var i = range_start; i <= range_end; ++i) {
						var txt;
						if (i < 10 && range_end >= 10)
							txt = '0' + i;
						else
							txt = '' + i;
						part._range[part._range.length] = txt
					}
				}
				Calendar._add_evs(part);
				return part
			}
			;
			var hrs = cal.date.getHours();
			var mins = cal.date.getMinutes();
			var t12 = !cal.time24;
			var pm = (hrs > 12);
			if (t12 && pm)
				hrs -= 12;
			var H = makeTimePart("hour", hrs, t12 ? 1 : 0, t12 ? 12 : 23);
			var span = Calendar.createElement("span", cell);
			span.innerHTML = ":";
			span.className = "colon";
			var M = makeTimePart("minute", mins, 0, 59);
			var AP = null;
			cell = Calendar.createElement("td", row);
			cell.className = "time";
			cell.colSpan = 2;
			if (t12)
				AP = makeTimePart("ampm", pm ? "pm" : "am", [ "am", "pm" ]);
			else
				cell.innerHTML = "&nbsp;";
			cal.onSetTime = function() {
				var pm, hrs = this.date.getHours(), mins = this.date
						.getMinutes();
				if (t12) {
					pm = (hrs >= 12);
					if (pm)
						hrs -= 12;
					if (hrs == 0)
						hrs = 12;
					AP.innerHTML = pm ? "pm" : "am"
				}
				H.innerHTML = (hrs < 10) ? ("0" + hrs) : hrs;
				M.innerHTML = (mins < 10) ? ("0" + mins) : mins
			};
			cal.onUpdateTime = function() {
				var date = this.date;
				var h = parseInt(H.innerHTML, 10);
				if (t12) {
					if (/pm/i.test(AP.innerHTML) && h < 12)
						h += 12;
					else if (/am/i.test(AP.innerHTML) && h == 12)
						h = 0
				}
				var d = date.getDate();
				var m = date.getMonth();
				var y = date.getFullYear();
				date.setHours(h);
				date.setMinutes(parseInt(M.innerHTML, 10));
				date.setFullYear(y);
				date.setMonth(m);
				date.setDate(d);
				this.dateClicked = false;
				this.callHandler()
			}
		})()
	} else {
		this.onSetTime = this.onUpdateTime = function() {
		}
	}
	var tfoot = Calendar.createElement("tfoot", table);
	row = Calendar.createElement("tr", tfoot);
	row.className = "footrow";
	cell = hh(Calendar._TT["SEL_DATE"], this.weekNumbers ? 8 : 7, 300);
	cell.className = "ttip";
	if (this.isPopup) {
		cell.ttip = Calendar._TT["DRAG_TO_MOVE"];
		cell.style.cursor = "move"
	}
	this.tooltips = cell;
	div = Calendar.createElement("div", this.element);
	this.monthsCombo = div;
	div.className = "combo";
	for (i = 0; i < Calendar._MN.length; ++i) {
		var mn = Calendar.createElement("div");
		mn.className = Calendar.is_ie ? "label-IEfix" : "label";
		mn.month = i;
		mn.innerHTML = Calendar._SMN[i];
		div.appendChild(mn)
	}
	div = Calendar.createElement("div", this.element);
	this.yearsCombo = div;
	div.className = "combo";
	for (i = 12; i > 0; --i) {
		var yr = Calendar.createElement("div");
		yr.className = Calendar.is_ie ? "label-IEfix" : "label";
		div.appendChild(yr)
	}
	this._init(this.firstDayOfWeek, this.date);
	parent.appendChild(this.element)
};
Calendar._keyEvent = function(ev) {
	var cal = window._dynarch_popupCalendar;
	if (!cal || cal.multiple)
		return false;
	(Calendar.is_ie) && (ev = window.event);
	var act = (Calendar.is_ie || ev.type == "keypress"), K = ev.keyCode;
	if (ev.ctrlKey) {
		switch (K) {
		case 37:
			act && Calendar.cellClick(cal._nav_pm);
			break;
		case 38:
			act && Calendar.cellClick(cal._nav_py);
			break;
		case 39:
			act && Calendar.cellClick(cal._nav_nm);
			break;
		case 40:
			act && Calendar.cellClick(cal._nav_ny);
			break;
		default:
			return false
		}
	} else
		switch (K) {
		case 32:
			Calendar.cellClick(cal._nav_now);
			break;
		case 27:
			act && cal.callCloseHandler();
			break;
		case 37:
		case 38:
		case 39:
		case 40:
			if (act) {
				var prev, x, y, ne, el, step;
				prev = K == 37 || K == 38;
				step = (K == 37 || K == 39) ? 1 : 7;
				function setVars() {
					el = cal.currentDateEl;
					var p = el.pos;
					x = p & 15;
					y = p >> 4;
					ne = cal.ar_days[y][x]
				}
				;
				setVars();
				function prevMonth() {
					var date = new Date(cal.date);
					date.setDate(date.getDate() - step);
					cal.setDate(date)
				}
				;
				function nextMonth() {
					var date = new Date(cal.date);
					date.setDate(date.getDate() + step);
					cal.setDate(date)
				}
				;
				while (1) {
					switch (K) {
					case 37:
						if (--x >= 0)
							ne = cal.ar_days[y][x];
						else {
							x = 6;
							K = 38;
							continue
						}
						break;
					case 38:
						if (--y >= 0)
							ne = cal.ar_days[y][x];
						else {
							prevMonth();
							setVars()
						}
						break;
					case 39:
						if (++x < 7)
							ne = cal.ar_days[y][x];
						else {
							x = 0;
							K = 40;
							continue
						}
						break;
					case 40:
						if (++y < cal.ar_days.length)
							ne = cal.ar_days[y][x];
						else {
							nextMonth();
							setVars()
						}
						break
					}
					break
				}
				if (ne) {
					if (!ne.disabled)
						Calendar.cellClick(ne);
					else if (prev)
						prevMonth();
					else
						nextMonth()
				}
			}
			break;
		case 13:
			if (act)
				Calendar.cellClick(cal.currentDateEl, ev);
			break;
		default:
			return false
		}
	return Calendar.stopEvent(ev)
};
Calendar.prototype._init = function(firstDayOfWeek, date) {
	var today = new Date(), TY = today.getFullYear(), TM = today.getMonth(), TD = today
			.getDate();
	this.table.style.visibility = "hidden";
	var year = date.getFullYear();
	if (year < this.minYear) {
		year = this.minYear;
		date.setFullYear(year)
	} else if (year > this.maxYear) {
		year = this.maxYear;
		date.setFullYear(year)
	}
	this.firstDayOfWeek = firstDayOfWeek;
	this.date = new Date(date);
	var month = date.getMonth();
	var mday = date.getDate();
	var no_days = date.getMonthDays();
	date.setDate(1);
	var day1 = (date.getDay() - this.firstDayOfWeek) % 7;
	if (day1 < 0)
		day1 += 7;
	date.setDate(-day1);
	date.setDate(date.getDate() + 1);
	var row = this.tbody.firstChild;
	var MN = Calendar._SMN[month];
	var ar_days = this.ar_days = new Array();
	var weekend = Calendar._TT["WEEKEND"];
	var dates = this.multiple ? (this.datesCells = {}) : null;
	for ( var i = 0; i < 6; ++i, row = row.nextSibling) {
		var cell = row.firstChild;
		if (this.weekNumbers) {
			cell.className = "day wn";
			cell.innerHTML = date.getWeekNumber();
			cell = cell.nextSibling
		}
		row.className = "daysrow";
		var hasdays = false, iday, dpos = ar_days[i] = [];
		for ( var j = 0; j < 7; ++j, cell = cell.nextSibling, date
				.setDate(iday + 1)) {
			iday = date.getDate();
			var wday = date.getDay();
			cell.className = "day";
			cell.pos = i << 4 | j;
			dpos[j] = cell;
			var current_month = (date.getMonth() == month);
			if (!current_month) {
				if (this.showsOtherMonths) {
					cell.className += " othermonth";
					cell.otherMonth = true
				} else {
					cell.className = "emptycell";
					cell.innerHTML = "&nbsp;";
					cell.disabled = true;
					continue
				}
			} else {
				cell.otherMonth = false;
				hasdays = true
			}
			cell.disabled = false;
			cell.innerHTML = this.getDateText ? this.getDateText(date, iday)
					: iday;
			if (dates)
				dates[date.print("%Y%m%d")] = cell;
			if (this.getDateStatus) {
				var status = this.getDateStatus(date, year, month, iday);
				if (this.getDateToolTip) {
					var toolTip = this.getDateToolTip(date, year, month, iday);
					if (toolTip)
						cell.title = toolTip
				}
				if (status === true) {
					cell.className += " disabled";
					cell.disabled = true
				} else {
					if (/disabled/i.test(status))
						cell.disabled = true;
					cell.className += " " + status
				}
			}
			if (!cell.disabled) {
				cell.caldate = new Date(date);
				cell.ttip = "_";
				if (!this.multiple && current_month && iday == mday
						&& this.hiliteToday) {
					cell.className += " selected";
					this.currentDateEl = cell
				}
				if (date.getFullYear() == TY && date.getMonth() == TM
						&& iday == TD) {
					cell.className += " today";
					cell.ttip += Calendar._TT["PART_TODAY"]
				}
				if (weekend.indexOf(wday.toString()) != -1)
					cell.className += cell.otherMonth ? " oweekend"
							: " weekend"
			}
		}
		if (!(hasdays || this.showsOtherMonths))
			row.className = "emptyrow"
	}
	this.title.innerHTML = Calendar._MN[month] + ", " + year;
	this.onSetTime();
	this.table.style.visibility = "visible";
	this._initMultipleDates()
};
Calendar.prototype._initMultipleDates = function() {
	if (this.multiple) {
		for ( var i in this.multiple) {
			var cell = this.datesCells[i];
			var d = this.multiple[i];
			if (!d)
				continue;
			if (cell)
				cell.className += " selected"
		}
	}
};
Calendar.prototype._toggleMultipleDate = function(date) {
	if (this.multiple) {
		var ds = date.print("%Y%m%d");
		var cell = this.datesCells[ds];
		if (cell) {
			var d = this.multiple[ds];
			if (!d) {
				Calendar.addClass(cell, "selected");
				this.multiple[ds] = date
			} else {
				Calendar.removeClass(cell, "selected");
				delete this.multiple[ds]
			}
		}
	}
};
Calendar.prototype.setDateToolTipHandler = function(unaryFunction) {
	this.getDateToolTip = unaryFunction
};
Calendar.prototype.setDate = function(date) {
	if (!date.equalsTo(this.date)) {
		this._init(this.firstDayOfWeek, date)
	}
};
Calendar.prototype.refresh = function() {
	this._init(this.firstDayOfWeek, this.date)
};
Calendar.prototype.setFirstDayOfWeek = function(firstDayOfWeek) {
	this._init(firstDayOfWeek, this.date);
	this._displayWeekdays()
};
Calendar.prototype.setDateStatusHandler = Calendar.prototype.setDisabledHandler = function(
		unaryFunction) {
	this.getDateStatus = unaryFunction
};
Calendar.prototype.setRange = function(a, z) {
	this.minYear = a;
	this.maxYear = z
};
Calendar.prototype.callHandler = function() {
	if (this.onSelected) {
		this.onSelected(this, this.date.print(this.dateFormat))
	}
};
Calendar.prototype.callCloseHandler = function() {
	if (this.onClose) {
		this.onClose(this)
	}
	this.hideShowCovered()
};
Calendar.prototype.destroy = function() {
	var el = this.element.parentNode;
	el.removeChild(this.element);
	Calendar._C = null;
	window._dynarch_popupCalendar = null
};
Calendar.prototype.reparent = function(new_parent) {
	var el = this.element;
	el.parentNode.removeChild(el);
	new_parent.appendChild(el)
};
Calendar._checkCalendar = function(ev) {
	var calendar = window._dynarch_popupCalendar;
	if (!calendar) {
		return false
	}
	var el = Calendar.is_ie ? Calendar.getElement(ev) : Calendar
			.getTargetElement(ev);
	for (; el != null && el != calendar.element; el = el.parentNode)
		;
	if (el == null) {
		window._dynarch_popupCalendar.callCloseHandler();
		return Calendar.stopEvent(ev)
	}
};
Calendar.prototype.show = function() {
	var rows = this.table.getElementsByTagName("tr");
	for ( var i = rows.length; i > 0;) {
		var row = rows[--i];
		Calendar.removeClass(row, "rowhilite");
		var cells = row.getElementsByTagName("td");
		for ( var j = cells.length; j > 0;) {
			var cell = cells[--j];
			Calendar.removeClass(cell, "hilite");
			Calendar.removeClass(cell, "active")
		}
	}
	this.element.style.display = "block";
	this.hidden = false;
	if (this.isPopup) {
		window._dynarch_popupCalendar = this;
		Calendar.addEvent(document, "keydown", Calendar._keyEvent);
		Calendar.addEvent(document, "keypress", Calendar._keyEvent);
		Calendar.addEvent(document, "mousedown", Calendar._checkCalendar)
	}
	this.hideShowCovered()
};
Calendar.prototype.hide = function() {
	if (this.isPopup) {
		Calendar.removeEvent(document, "keydown", Calendar._keyEvent);
		Calendar.removeEvent(document, "keypress", Calendar._keyEvent);
		Calendar.removeEvent(document, "mousedown", Calendar._checkCalendar)
	}
	this.element.style.display = "none";
	this.hidden = true;
	this.hideShowCovered()
};
Calendar.prototype.showAt = function(x, y) {
	var s = this.element.style;
	s.left = x + "px";
	s.top = y + "px";
	this.show()
};
Calendar.prototype.showAtElement = function(el, opts) {
	var self = this;
	var p = Calendar.getAbsolutePos(el);
	if (!opts || typeof opts != "string") {
		this.showAt(p.x, p.y + el.offsetHeight);
		return true
	}
	function fixPosition(box) {
		if (box.x < 0)
			box.x = 0;
		if (box.y < 0)
			box.y = 0;
		var cp = document.createElement("div");
		var s = cp.style;
		s.position = "absolute";
		s.right = s.bottom = s.width = s.height = "0px";
		document.body.appendChild(cp);
		var br = Calendar.getAbsolutePos(cp);
		document.body.removeChild(cp);
		if (Calendar.is_ie) {
			br.y += document.body.scrollTop;
			br.x += document.body.scrollLeft
		} else {
			br.y += window.scrollY;
			br.x += window.scrollX
		}
		var tmp = box.x + box.width - br.x;
		if (tmp > 0)
			box.x -= tmp;
		tmp = box.y + box.height - br.y;
		if (tmp > 0)
			box.y -= tmp
	}
	;
	this.element.style.display = "block";
	Calendar.continuation_for_the_fucking_khtml_browser = function() {
		var w = self.element.offsetWidth;
		var h = self.element.offsetHeight;
		self.element.style.display = "none";
		var valign = opts.substr(0, 1);
		var halign = "l";
		if (opts.length > 1) {
			halign = opts.substr(1, 1)
		}
		switch (valign) {
		case "T":
			p.y -= h;
			break;
		case "B":
			p.y += el.offsetHeight;
			break;
		case "C":
			p.y += (el.offsetHeight - h) / 2;
			break;
		case "t":
			p.y += el.offsetHeight - h;
			break;
		case "b":
			break
		}
		switch (halign) {
		case "L":
			p.x -= w;
			break;
		case "R":
			p.x += el.offsetWidth;
			break;
		case "C":
			p.x += (el.offsetWidth - w) / 2;
			break;
		case "l":
			p.x += el.offsetWidth - w;
			break;
		case "r":
			break
		}
		p.width = w;
		p.height = h + 40;
		self.monthsCombo.style.display = "none";
		fixPosition(p);
		self.showAt(p.x, p.y)
	};
	if (Calendar.is_khtml)
		setTimeout("Calendar.continuation_for_the_fucking_khtml_browser()", 10);
	else
		Calendar.continuation_for_the_fucking_khtml_browser()
};
Calendar.prototype.setDateFormat = function(str) {
	this.dateFormat = str
};
Calendar.prototype.setTtDateFormat = function(str) {
	this.ttDateFormat = str
};
Calendar.prototype.parseDate = function(str, fmt) {
	if (!fmt)
		fmt = this.dateFormat;
	this.setDate(Date.parseDate(str, fmt))
};
Calendar.prototype.hideShowCovered = function() {
	if (!Calendar.is_ie && !Calendar.is_opera)
		return;
	function getVisib(obj) {
		var value = obj.style.visibility;
		if (!value) {
			if (document.defaultView
					&& typeof (document.defaultView.getComputedStyle) == "function") {
				if (!Calendar.is_khtml)
					value = document.defaultView.getComputedStyle(obj, "")
							.getPropertyValue("visibility");
				else
					value = ''
			} else if (obj.currentStyle) {
				value = obj.currentStyle.visibility
			} else
				value = ''
		}
		return value
	}
	;
	var tags = new Array("applet", "iframe", "select");
	var el = this.element;
	var p = Calendar.getAbsolutePos(el);
	var EX1 = p.x;
	var EX2 = el.offsetWidth + EX1;
	var EY1 = p.y;
	var EY2 = el.offsetHeight + EY1;
	for ( var k = tags.length; k > 0;) {
		var ar = document.getElementsByTagName(tags[--k]);
		var cc = null;
		for ( var i = ar.length; i > 0;) {
			cc = ar[--i];
			p = Calendar.getAbsolutePos(cc);
			var CX1 = p.x;
			var CX2 = cc.offsetWidth + CX1;
			var CY1 = p.y;
			var CY2 = cc.offsetHeight + CY1;
			if (this.hidden || (CX1 > EX2) || (CX2 < EX1) || (CY1 > EY2)
					|| (CY2 < EY1)) {
				if (!cc.__msh_save_visibility) {
					cc.__msh_save_visibility = getVisib(cc)
				}
				cc.style.visibility = cc.__msh_save_visibility
			} else {
				if (!cc.__msh_save_visibility) {
					cc.__msh_save_visibility = getVisib(cc)
				}
				cc.style.visibility = "hidden"
			}
		}
	}
};
Calendar.prototype._displayWeekdays = function() {
	var fdow = this.firstDayOfWeek;
	var cell = this.firstdayname;
	var weekend = Calendar._TT["WEEKEND"];
	for ( var i = 0; i < 7; ++i) {
		cell.className = "day name";
		var realday = (i + fdow) % 7;
		if (i) {
			cell.ttip = Calendar._TT["DAY_FIRST"].replace("%s",
					Calendar._DN[realday]);
			cell.navtype = 100;
			cell.calendar = this;
			cell.fdow = realday;
			Calendar._add_evs(cell)
		}
		if (weekend.indexOf(realday.toString()) != -1) {
			Calendar.addClass(cell, "weekend")
		}
		cell.innerHTML = Calendar._SDN[(i + fdow) % 7];
		cell = cell.nextSibling
	}
};
Calendar.prototype._hideCombos = function() {
	this.monthsCombo.style.display = "none";
	this.yearsCombo.style.display = "none"
};
Calendar.prototype._dragStart = function(ev) {
	if (this.dragging) {
		return
	}
	this.dragging = true;
	var posX;
	var posY;
	if (Calendar.is_ie) {
		posY = window.event.clientY + document.body.scrollTop;
		posX = window.event.clientX + document.body.scrollLeft
	} else {
		posY = ev.clientY + window.scrollY;
		posX = ev.clientX + window.scrollX
	}
	var st = this.element.style;
	this.xOffs = posX - parseInt(st.left);
	this.yOffs = posY - parseInt(st.top);
	with (Calendar) {
		addEvent(document, "mousemove", calDragIt);
		addEvent(document, "mouseup", calDragEnd)
	}
};
Date._MD = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
Date.SECOND = 1000;
Date.MINUTE = 60 * Date.SECOND;
Date.HOUR = 60 * Date.MINUTE;
Date.DAY = 24 * Date.HOUR;
Date.WEEK = 7 * Date.DAY;
Date.parseDate = function(str, fmt) {
	var today = new Date();
	var y = 0;
	var m = -1;
	var d = 0;
	var a = str.split(/\W+/);
	var b = fmt.match(/%./g);
	var i = 0, j = 0;
	var hr = 0;
	var min = 0;
	for (i = 0; i < a.length; ++i) {
		if (!a[i])
			continue;
		switch (b[i]) {
		case "%d":
		case "%e":
			d = parseInt(a[i], 10);
			break;
		case "%m":
			m = parseInt(a[i], 10) - 1;
			break;
		case "%Y":
		case "%y":
			y = parseInt(a[i], 10);
			(y < 100) && (y += (y > 29) ? 1900 : 2000);
			break;
		case "%b":
		case "%B":
			for (j = 0; j < 12; ++j) {
				if (Calendar._MN[j].substr(0, a[i].length).toLowerCase() == a[i]
						.toLowerCase()) {
					m = j;
					break
				}
			}
			break;
		case "%H":
		case "%I":
		case "%k":
		case "%l":
			hr = parseInt(a[i], 10);
			break;
		case "%P":
		case "%p":
			if (/pm/i.test(a[i]) && hr < 12)
				hr += 12;
			else if (/am/i.test(a[i]) && hr >= 12)
				hr -= 12;
			break;
		case "%M":
			min = parseInt(a[i], 10);
			break
		}
	}
	if (isNaN(y))
		y = today.getFullYear();
	if (isNaN(m))
		m = today.getMonth();
	if (isNaN(d))
		d = today.getDate();
	if (isNaN(hr))
		hr = today.getHours();
	if (isNaN(min))
		min = today.getMinutes();
	if (y != 0 && m != -1 && d != 0)
		return new Date(y, m, d, hr, min, 0);
	y = 0;
	m = -1;
	d = 0;
	for (i = 0; i < a.length; ++i) {
		if (a[i].search(/[a-zA-Z]+/) != -1) {
			var t = -1;
			for (j = 0; j < 12; ++j) {
				if (Calendar._MN[j].substr(0, a[i].length).toLowerCase() == a[i]
						.toLowerCase()) {
					t = j;
					break
				}
			}
			if (t != -1) {
				if (m != -1) {
					d = m + 1
				}
				m = t
			}
		} else if (parseInt(a[i], 10) <= 12 && m == -1) {
			m = a[i] - 1
		} else if (parseInt(a[i], 10) > 31 && y == 0) {
			y = parseInt(a[i], 10);
			(y < 100) && (y += (y > 29) ? 1900 : 2000)
		} else if (d == 0) {
			d = a[i]
		}
	}
	if (y == 0)
		y = today.getFullYear();
	if (m != -1 && d != 0)
		return new Date(y, m, d, hr, min, 0);
	return today
};
Date.prototype.getMonthDays = function(month) {
	var year = this.getFullYear();
	if (typeof month == "undefined") {
		month = this.getMonth()
	}
	if (((0 == (year % 4)) && ((0 != (year % 100)) || (0 == (year % 400))))
			&& month == 1) {
		return 29
	} else {
		return Date._MD[month]
	}
};
Date.prototype.getDayOfYear = function() {
	var now = new Date(this.getFullYear(), this.getMonth(), this.getDate(), 0,
			0, 0);
	var then = new Date(this.getFullYear(), 0, 0, 0, 0, 0);
	var time = now - then;
	return Math.floor(time / Date.DAY)
};
Date.prototype.getWeekNumber = function() {
	var d = new Date(this.getFullYear(), this.getMonth(), this.getDate(), 0, 0,
			0);
	var DoW = d.getDay();
	d.setDate(d.getDate() - (DoW + 6) % 7 + 3);
	var ms = d.valueOf();
	d.setMonth(0);
	d.setDate(4);
	return Math.round((ms - d.valueOf()) / (7 * 864e5)) + 1
};
Date.prototype.equalsTo = function(date) {
	return ((this.getFullYear() == date.getFullYear())
			&& (this.getMonth() == date.getMonth())
			&& (this.getDate() == date.getDate())
			&& (this.getHours() == date.getHours()) && (this.getMinutes() == date
			.getMinutes()))
};
Date.prototype.setDateOnly = function(date) {
	var tmp = new Date(date);
	this.setDate(1);
	this.setFullYear(tmp.getFullYear());
	this.setMonth(tmp.getMonth());
	this.setDate(tmp.getDate())
};
Date.prototype.print = function(str) {
	var m = this.getMonth();
	var d = this.getDate();
	var y = this.getFullYear();
	var wn = this.getWeekNumber();
	var w = this.getDay();
	var s = {};
	var hr = this.getHours();
	var pm = (hr >= 12);
	var ir = (pm) ? (hr - 12) : hr;
	var dy = this.getDayOfYear();
	if (ir == 0)
		ir = 12;
	var min = this.getMinutes();
	var sec = this.getSeconds();
	s["%a"] = Calendar._SDN[w];
	s["%A"] = Calendar._DN[w];
	s["%b"] = Calendar._SMN[m];
	s["%B"] = Calendar._MN[m];
	s["%C"] = 1 + Math.floor(y / 100);
	s["%d"] = (d < 10) ? ("0" + d) : d;
	s["%e"] = d;
	s["%H"] = (hr < 10) ? ("0" + hr) : hr;
	s["%I"] = (ir < 10) ? ("0" + ir) : ir;
	s["%j"] = (dy < 100) ? ((dy < 10) ? ("00" + dy) : ("0" + dy)) : dy;
	s["%k"] = hr;
	s["%l"] = ir;
	s["%m"] = (m < 9) ? ("0" + (1 + m)) : (1 + m);
	s["%M"] = (min < 10) ? ("0" + min) : min;
	s["%n"] = "\n";
	s["%p"] = pm ? "PM" : "AM";
	s["%P"] = pm ? "pm" : "am";
	s["%s"] = Math.floor(this.getTime() / 1000);
	s["%S"] = (sec < 10) ? ("0" + sec) : sec;
	s["%t"] = "\t";
	s["%U"] = s["%W"] = s["%V"] = (wn < 10) ? ("0" + wn) : wn;
	s["%u"] = w + 1;
	s["%w"] = w;
	s["%y"] = ('' + y).substr(2, 2);
	s["%Y"] = y;
	s["%%"] = "%";
	var re = /%./g;
	if (!Calendar.is_ie5 && !Calendar.is_khtml)
		return str.replace(re, function(par) {
			return s[par] || par
		});
	var a = str.match(re);
	for ( var i = 0; i < a.length; i++) {
		var tmp = s[a[i]];
		if (tmp) {
			re = new RegExp(a[i], 'g');
			str = str.replace(re, tmp)
		}
	}
	return str
};
Date.prototype.__msh_oldSetFullYear = Date.prototype.setFullYear;
Date.prototype.setFullYear = function(y) {
	var d = new Date(this);
	d.__msh_oldSetFullYear(y);
	if (d.getMonth() != this.getMonth())
		this.setDate(28);
	this.__msh_oldSetFullYear(y)
};
window._dynarch_popupCalendar = null;
Calendar._DN = new Array("Domingo", "Lunes", "Martes", "Mi&#233;rcoles", "Jueves",
		"Viernes", "S&#225;bado", "Domingo");
Calendar._SDN = new Array("Dom", "Lun", "Mar", "Mi&#233;", "Jue", "Vie", "S&#225;b",
		"Dom");
Calendar._FD = 1;
Calendar._MN = new Array("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
		"Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre");
Calendar._SMN = new Array("Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul",
		"Ago", "Sep", "Oct", "Nov", "Dic");
Calendar._TT = {};
Calendar._TT["INFO"] = "Acerca del calendario";
Calendar._TT["ABOUT"] = "Selector DHTML de Fecha/Hora\n"
		+ "(c) dynarch.com 2002-2005 / Author: Mihai Bazon\n"
		+ "Para conseguir la �ltima versi�n visite: http://www.dynarch.com/projects/calendar/\n"
		+ "Distribuido bajo licencia GNU LGPL. Visite http://gnu.org/licenses/lgpl.html para m�s detalles."
		+ "\n\n"
		+ "Selecci�n de fecha:\n"
		+ "- Use los botones \xab, \xbb para seleccionar el a�o\n"
		+ "- Use los botones "
		+ String.fromCharCode(0x2039)
		+ ", "
		+ String.fromCharCode(0x203a)
		+ " para seleccionar el mes\n"
		+ "- Mantenga pulsado el rat�n en cualquiera de estos botones para una selecci&#243;n r&#225;pida.";
Calendar._TT["ABOUT_TIME"] = "\n\n" + "Selecci&#243;n de hora:\n"
		+ "- Pulse en cualquiera de las partes de la hora para incrementarla\n"
		+ "- o pulse las may&#250;sculas mientras hace clic para decrementarla\n"
		+ "- o haga clic y arrastre el rat�n para una selecci�n m&#225;s r&#225;pida.";
Calendar._TT["PREV_YEAR"] = "A&#241;o anterior (mantener para men&#250;)";
Calendar._TT["PREV_MONTH"] = "Mes anterior (mantener para men&#250;)";
Calendar._TT["GO_TODAY"] = "Ir a hoy";
Calendar._TT["NEXT_MONTH"] = "Mes siguiente (mantener para men&#250;)";
Calendar._TT["NEXT_YEAR"] = "A&#241;o siguiente (mantener para men&#250;)";
Calendar._TT["SEL_DATE"] = "Seleccionar fecha";
Calendar._TT["DRAG_TO_MOVE"] = "Arrastrar para mover";
Calendar._TT["PART_TODAY"] = "";
Calendar._TT["DAY_FIRST"] = "Hacer primer d&#237;a de la semana";
Calendar._TT["WEEKEND"] = "0,6";
Calendar._TT["CLOSE"] = "Cerrar";
Calendar._TT["TODAY"] = "Hoy";
Calendar._TT["TIME_PART"] = "(May&#250;scula-)Clic o arrastre para cambiar valor";
Calendar._TT["DEF_DATE_FORMAT"] = "%d/%m/%Y";
Calendar._TT["TT_DATE_FORMAT"] = "%A, %e de %B de %Y";
Calendar._TT["WK"] = "sem";
Calendar._TT["TIME"] = "Hora:";
Calendar.setup = function(params) {
	function param_default(pname, def) {
		if (typeof params[pname] == "undefined") {
			params[pname] = def
		}
	}
	;
	param_default("inputField", null);
	param_default("displayArea", null);
	param_default("button", null);
	param_default("eventName", "click");
	param_default("ifFormat", "%Y/%m/%d");
	param_default("daFormat", "%Y/%m/%d");
	param_default("singleClick", true);
	param_default("disableFunc", null);
	param_default("dateStatusFunc", params["disableFunc"]);
	param_default("dateText", null);
	param_default("firstDay", null);
	param_default("align", "Br");
	param_default("range", [ 1900, 2999 ]);
	param_default("weekNumbers", true);
	param_default("flat", null);
	param_default("flatCallback", null);
	param_default("onSelect", null);
	param_default("onClose", null);
	param_default("onUpdate", null);
	param_default("date", null);
	param_default("showsTime", false);
	param_default("timeFormat", "24");
	param_default("electric", true);
	param_default("step", 2);
	param_default("position", null);
	param_default("cache", false);
	param_default("showOthers", false);
	param_default("multiple", null);
	var tmp = [ "inputField", "displayArea", "button" ];
	for ( var i in tmp) {
		if (typeof params[tmp[i]] == "string") {
			params[tmp[i]] = document.getElementById(params[tmp[i]])
		}
	}
	if (!(params.flat || params.multiple || params.inputField
			|| params.displayArea || params.button)) {
		alert("Calendar.setup:\n  Nothing to setup (no fields found).  Please check your code");
		return false
	}
	function onSelect(cal) {
		var p = cal.params;
		var update = (cal.dateClicked || p.electric);
		if (update && p.inputField) {
			p.inputField.value = cal.date.print(p.ifFormat);
			if (typeof p.inputField.onchange == "function")
				p.inputField.onchange()
		}
		if (update && p.displayArea)
			p.displayArea.innerHTML = cal.date.print(p.daFormat);
		if (update && typeof p.onUpdate == "function")
			p.onUpdate(cal);
		if (update && p.flat) {
			if (typeof p.flatCallback == "function")
				p.flatCallback(cal)
		}
		if (update && p.singleClick && cal.dateClicked)
			cal.callCloseHandler()
	}
	;
	if (params.flat != null) {
		if (typeof params.flat == "string")
			params.flat = document.getElementById(params.flat);
		if (!params.flat) {
			alert("Calendar.setup:\n  Flat specified but can't find parent.");
			return false
		}
		var cal = new Calendar(params.firstDay, params.date, params.onSelect
				|| onSelect);
		cal.showsOtherMonths = params.showOthers;
		cal.showsTime = params.showsTime;
		cal.time24 = (params.timeFormat == "24");
		cal.params = params;
		cal.weekNumbers = params.weekNumbers;
		cal.setRange(params.range[0], params.range[1]);
		cal.setDateStatusHandler(params.dateStatusFunc);
		cal.getDateText = params.dateText;
		if (params.ifFormat) {
			cal.setDateFormat(params.ifFormat)
		}
		if (params.inputField && typeof params.inputField.value == "string") {
			cal.parseDate(params.inputField.value)
		}
		cal.create(params.flat);
		cal.show();
		return false
	}
	var triggerEl = params.button || params.displayArea || params.inputField;
	triggerEl["on" + params.eventName] = function() {
		var dateEl = params.inputField || params.displayArea;
		var dateFmt = params.inputField ? params.ifFormat : params.daFormat;
		var mustCreate = false;
		var cal = window.calendar;
		if (dateEl)
			params.date = Date.parseDate(dateEl.value || dateEl.innerHTML,
					dateFmt);
		if (!(cal && params.cache)) {
			window.calendar = cal = new Calendar(params.firstDay, params.date,
					params.onSelect || onSelect, params.onClose
							|| function(cal) {
								cal.hide()
							});
			cal.showsTime = params.showsTime;
			cal.time24 = (params.timeFormat == "24");
			cal.weekNumbers = params.weekNumbers;
			mustCreate = true
		} else {
			if (params.date)
				cal.setDate(params.date);
			cal.hide()
		}
		if (params.multiple) {
			cal.multiple = {};
			for ( var i = params.multiple.length; --i >= 0;) {
				var d = params.multiple[i];
				var ds = d.print("%Y%m%d");
				cal.multiple[ds] = d
			}
		}
		cal.showsOtherMonths = params.showOthers;
		cal.yearStep = params.step;
		cal.setRange(params.range[0], params.range[1]);
		cal.params = params;
		cal.setDateStatusHandler(params.dateStatusFunc);
		cal.getDateText = params.dateText;
		cal.setDateFormat(dateFmt);
		if (mustCreate)
			cal.create();
		cal.refresh();
		if (!params.position)
			cal.showAtElement(params.button || params.displayArea
					|| params.inputField, params.align);
		else
			cal.showAt(params.position[0], params.position[1]);
		return false
	};
	return cal
};
function selected(cal, date) {
	cal.sel.value = date;
	if (cal.dateClicked && (cal.sel.id == "sel1" || cal.sel.id == "sel3"))
		cal.callCloseHandler()
}
function closeHandler(cal) {
	cal.hide();
	_dynarch_popupCalendar = null
}
function selected1(cal, date) {
	cal.sel.value = date;
	var diaMesAnio = cal.sel.value.split("/");
	var anio = diaMesAnio[2];
	var mes = diaMesAnio[1];
	var dia = diaMesAnio[0];
	var fechaDiasAdelante = new Date(anio + "/" + mes + "/" + dia);
	fechaDiasAdelante.setDate(fechaDiasAdelante.getDate() + (numDias));
	dia = fechaDiasAdelante.getDate();
	mes = fechaDiasAdelante.getMonth() + 1;
	anio = fechaDiasAdelante.getFullYear();
	if (dia < 10 && mes < 10) {
		fecha2.value = "0" + dia + "/0" + mes + "/" + anio
	} else {
		if (mes < 10) {
			fecha2.value = dia + "/0" + mes + "/" + anio
		} else {
			if (dia < 10) {
				fecha2.value = "0" + dia + "/" + mes + "/" + anio
			} else {
				fecha2.value = dia + "/" + mes + "/" + anio
			}
		}
	}
	if (cal.dateClicked && (cal.sel.id == "sel1" || cal.sel.id == "sel3"))
		cal.callCloseHandler()
}
function mostrarCalendario(id) {
	var el = document.getElementById(id);
	var showsOtherMonths = true;
	var format = '%d/%m/%Y';
	if (_dynarch_popupCalendar != null) {
		_dynarch_popupCalendar.hide()
	} else {
		var cal = new Calendar(1, null, selected, closeHandler);
		cal.weekNumbers = true;
		if (showsOtherMonths) {
			cal.showsOtherMonths = true
		}
		_dynarch_popupCalendar = cal;
		cal.setRange(1900, 2070);
		cal.create();
		cal.singleClick = true
	}
	_dynarch_popupCalendar.setDateFormat(format);
	_dynarch_popupCalendar.parseDate(el.value);
	_dynarch_popupCalendar.sel = el;
	_dynarch_popupCalendar.showAtElement(el, "BR");
	return false
}
function verCalendarioFechaInicialSumarDias(idFecha1, idFecha2, numeroDias) {
	fecha1 = document.getElementById(idFecha1);
	fecha2 = document.getElementById(idFecha2);
	numDias = numeroDias;
	var showsOtherMonths = true;
	var format = '%d/%m/%Y';
	if (_dynarch_popupCalendar != null) {
		_dynarch_popupCalendar.hide()
	} else {
		var cal = new Calendar(1, null, selected1, closeHandler);
		cal.weekNumbers = true;
		if (showsOtherMonths) {
			cal.showsOtherMonths = true
		}
		_dynarch_popupCalendar = cal;
		cal.setRange(1900, 2070);
		cal.create();
		cal.singleClick = true
	}
	_dynarch_popupCalendar.setDateFormat(format);
	_dynarch_popupCalendar.parseDate(fecha1.value);
	_dynarch_popupCalendar.sel = fecha1;
	_dynarch_popupCalendar.showAtElement(fecha1, "BR");
	return false
}

/************************** util.js *********************************/
var mensajeConfirmarBorrado = "Esta seguro que desea eliminar el registro seleccionado?";

function verificarAgregadoRechazado(obj) {
    var objChecked = obj.checked;
    if (objChecked) {
	var objId = obj.id;
	if (objId.indexOf('aceptado') > 0) {
	    objId = objId.replace('aceptado', 'rechazado');
	} else if (objId.indexOf('rechazado') > 0) {
	    objId = objId.replace('rechazado', 'aceptado');
	}

	var nObj = document.getElementById(objId);
	if (nObj) {
	    if (nObj.checked) {
		nObj.checked = false;
	    }
	}
    }
} 
function abrirPopup(url) {
    opciones = "left=0, top=0, screenX=0, screenY=100, width=950, height=500, "
	+ "copyhistory=no, directories=no, resizable=no, titlebar=no, "
	+ "toolbar=no, scrollbars=yes, location=0, menubar=0, dependent=yes";
    window.open(url, "", opciones);
}

function verificarSecretariaDireccion(obj) {
    var objChecked = obj.checked;
    if (objChecked) {
	var objId = obj.id;
	if (objId.indexOf('aSecretaria') > 0) {
	    objId = objId.replace('aSecretaria', 'aDireccion');
	} else if (objId.indexOf('aDireccion') > 0) {
	    objId = objId.replace('aDireccion', 'aSecretaria');
	}

	var nObj = document.getElementById(objId);
	if (nObj) {
	    if (nObj.checked) {
		nObj.checked = false;
	    }
	}
    }
}

function verificarFirmadoDevuelto(obj) {
    var objChecked = obj.checked;
    if (objChecked) {
	var objId = obj.id;
	if (objId.indexOf('firmado') > 0) {
	    objId = objId.replace('firmado', 'devuelto');
	} else if (objId.indexOf('devuelto') > 0) {
	    objId = objId.replace('devuelto', 'firmado');
	}

	var nObj = document.getElementById(objId);
	if (nObj) {
	    if (nObj.checked) {
		nObj.checked = false;
	    }
	}
    }
}

function TrimString(sInString) {
    sInString = sInString.replace(/^\s+/g, "");
    return sInString.replace(/\s+$/g, "");
}

function openReportWindow(url) {
    var reportWindow = window.open(url, "reportWindow",
    "menubar=0,resizable=0,status=0,width=640,height=480");
}

function toggleCheckAll(formName, controlName, value, exclude) {
    var formObject = document.getElementById(formName);
    if (formObject) {
	var elements = formObject.elements;
	for (var i = 0; i < elements.length; i++) {
	    var element = elements[i];
	    if (element.type == 'checkbox') {
		if (element.id != null && element.id.indexOf(controlName) != -1) {
		    element.checked = value;
		} else if (exclude) {
		    element.checked = false;
		}
	    }
	}
    }
}

function setFocus(componentName) {
    var component = document.getElementById(componentName);
    if (component) {
	component.focus();
    }
}

function esIE() {
    if (document.all) {
	return true;
    }
    return false;
}

function aceptarNumerosLetras(evt) {
    var key = null;
    if (esIE()) {
	key = evt.keyCode;
    } else {
	key = evt.which;
    }
    return (key == 95 || (key >= 48 && key <= 57) || (key >= 64 && key <= 90) || (key >= 97 && key <= 121));
}

function aceptarNumeros(evt) {
    var key = null;
    if (esIE()) {
	key = evt.keyCode;
    } else {
	key = evt.which;
    }
    return (key == 95 || (key >= 48 && key <= 57));
}


function popUp(url, alto, ancho) {
    var left   = (screen.width  - ancho)/2;
    var top    = (screen.height - alto)/2;
    var params = 'width='+ancho+', height='+alto;
    params += ', top='+top+', left='+left;
    params += ', directories=no';
    params += ', location=no';
    params += ', menubar=no';
    params += ', resizable=no';
    params += ', scrollbars=yes';
    params += ', status=no';
    params += ', toolbar=no';
    newwin=window.open(url,'SRI', params);
    if (window.focus) {newwin.focus();}
    return false;
}

function updateParentWindow(nombreForm,nombreBotonRenderizado) {
    var elementToGet = nombreForm + ":" + nombreBotonRenderizado;
    var bt= opener.document.forms[nombreForm].elements[elementToGet];
    bt.click();
    window.close();
}

function setValueAll(formName, controlName, value, type, exclude) {
    var formObject = document.getElementById(formName);
    if (formObject) {
	var elements = formObject.elements;
	for (var i=0; i < elements.length; i++) {
	    var element = elements[i];
	    if (element.id != null && element.id.indexOf(controlName) != -1) {
		if (element.type == 'checkbox') {
		    element.checked = value;
		} else {
		    element.value = value;
		}
	    }
	}
    }
}




function ismaxlength(obj, MaxLen){
    if (obj.value.length>MaxLen){
	obj.value=obj.value.substring(0,MaxLen);
    }
}

function permite(elEvento, permitidos) {
    var numeros = "0123456789";
    var monetario = "0123456789.";
    var caracteres = " abcdefghijklmn�opqrstuvwxyzABCDEFGHIJKLMN�OPQRSTUVWXYZ";
    var numeros_caracteres = numeros + caracteres;
    var teclas_especiales = [8];
    
    switch(permitidos) {
    case 'num':
	permitidos = numeros;
	break;
    case 'car':
	permitidos = caracteres;
	break;
    case 'num_car':
	permitidos = numeros_caracteres;
	break;
    case 'num_mon':
	permitidos = monetario;
	break;
    }
   
    var evento = elEvento || window.event;
    var codigoCaracter = evento.charCode || evento.keyCode;
    var caracter = String.fromCharCode(codigoCaracter);

    var tecla_especial = false;
    for(var i in teclas_especiales) {
	if(codigoCaracter == teclas_especiales[i]) {
	    tecla_especial = true;
	    break;
	}
    }
    return permitidos.indexOf(caracter) != -1 || tecla_especial;
}


/*********************** jquery.maskMoney ***************************/
/*
 *  jquery-maskmoney - v3.0.2
 *  jQuery plugin to mask data entry in the input text in the form of money (currency)
 *  https://github.com/plentz/jquery-maskmoney
 *
 *  Made by Diego Plentz
 *  Under MIT License (https://raw.github.com/plentz/jquery-maskmoney/master/LICENSE)
 */
!function($){"use strict";$.browser||($.browser={},$.browser.mozilla=/mozilla/.test(navigator.userAgent.toLowerCase())&&!/webkit/.test(navigator.userAgent.toLowerCase()),$.browser.webkit=/webkit/.test(navigator.userAgent.toLowerCase()),$.browser.opera=/opera/.test(navigator.userAgent.toLowerCase()),$.browser.msie=/msie/.test(navigator.userAgent.toLowerCase()));var a={destroy:function(){return $(this).unbind(".maskMoney"),$.browser.msie&&(this.onpaste=null),this},mask:function(a){return this.each(function(){var b,c=$(this);return"number"==typeof a&&(c.trigger("mask"),b=$(c.val().split(/\D/)).last()[0].length,a=a.toFixed(b),c.val(a)),c.trigger("mask")})},unmasked:function(){return this.map(function(){var a,b=$(this).val()||"0",c=-1!==b.indexOf("-");return $(b.split(/\D/).reverse()).each(function(b,c){return c?(a=c,!1):void 0}),b=b.replace(/\D/g,""),b=b.replace(new RegExp(a+"$"),"."+a),c&&(b="-"+b),parseFloat(b)})},init:function(a){return a=$.extend({prefix:"",suffix:"",affixesStay:!0,thousands:",",decimal:".",precision:2,allowZero:!1,allowNegative:!1},a),this.each(function(){function b(){var a,b,c,d,e,f=s.get(0),g=0,h=0;return"number"==typeof f.selectionStart&&"number"==typeof f.selectionEnd?(g=f.selectionStart,h=f.selectionEnd):(b=document.selection.createRange(),b&&b.parentElement()===f&&(d=f.value.length,a=f.value.replace(/\r\n/g,"\n"),c=f.createTextRange(),c.moveToBookmark(b.getBookmark()),e=f.createTextRange(),e.collapse(!1),c.compareEndPoints("StartToEnd",e)>-1?g=h=d:(g=-c.moveStart("character",-d),g+=a.slice(0,g).split("\n").length-1,c.compareEndPoints("EndToEnd",e)>-1?h=d:(h=-c.moveEnd("character",-d),h+=a.slice(0,h).split("\n").length-1)))),{start:g,end:h}}function c(){var a=!(s.val().length>=s.attr("maxlength")&&s.attr("maxlength")>=0),c=b(),d=c.start,e=c.end,f=c.start!==c.end&&s.val().substring(d,e).match(/\d/)?!0:!1,g="0"===s.val().substring(0,1);return a||f||g}function d(a){s.each(function(b,c){if(c.setSelectionRange)c.focus(),c.setSelectionRange(a,a);else if(c.createTextRange){var d=c.createTextRange();d.collapse(!0),d.moveEnd("character",a),d.moveStart("character",a),d.select()}})}function e(b){var c="";return b.indexOf("-")>-1&&(b=b.replace("-",""),c="-"),c+a.prefix+b+a.suffix}function f(b){var c,d,f,g=b.indexOf("-")>-1&&a.allowNegative?"-":"",h=b.replace(/[^0-9]/g,""),i=h.slice(0,h.length-a.precision);return i=i.replace(/^0*/g,""),i=i.replace(/\B(?=(\d{3})+(?!\d))/g,a.thousands),""===i&&(i="0"),c=g+i,a.precision>0&&(d=h.slice(h.length-a.precision),f=new Array(a.precision+1-d.length).join(0),c+=a.decimal+f+d),e(c)}function g(a){var b,c=s.val().length;s.val(f(s.val())),b=s.val().length,a-=c-b,d(a)}function h(){var a=s.val();s.val(f(a))}function i(){var b=s.val();return a.allowNegative?""!==b&&"-"===b.charAt(0)?b.replace("-",""):"-"+b:b}function j(a){a.preventDefault?a.preventDefault():a.returnValue=!1}function k(a){a=a||window.event;var d,e,f,h,k,l=a.which||a.charCode||a.keyCode;return void 0===l?!1:48>l||l>57?45===l?(s.val(i()),!1):43===l?(s.val(s.val().replace("-","")),!1):13===l||9===l?!0:!$.browser.mozilla||37!==l&&39!==l||0!==a.charCode?(j(a),!0):!0:c()?(j(a),d=String.fromCharCode(l),e=b(),f=e.start,h=e.end,k=s.val(),s.val(k.substring(0,f)+d+k.substring(h,k.length)),g(f+1),!1):!1}function l(c){c=c||window.event;var d,e,f,h,i,k=c.which||c.charCode||c.keyCode;return void 0===k?!1:(d=b(),e=d.start,f=d.end,8===k||46===k||63272===k?(j(c),h=s.val(),e===f&&(8===k?""===a.suffix?e-=1:(i=h.split("").reverse().join("").search(/\d/),e=h.length-i-1,f=e+1):f+=1),s.val(h.substring(0,e)+h.substring(f,h.length)),g(e),!1):9===k?!0:!0)}function m(){r=s.val(),h();var a,b=s.get(0);b.createTextRange&&(a=b.createTextRange(),a.collapse(!1),a.select())}function n(){setTimeout(function(){h()},0)}function o(){var b=parseFloat("0")/Math.pow(10,a.precision);return b.toFixed(a.precision).replace(new RegExp("\\.","g"),a.decimal)}function p(b){if($.browser.msie&&k(b),""===s.val()||s.val()===e(o()))a.allowZero?a.affixesStay?s.val(e(o())):s.val(o()):s.val("");else if(!a.affixesStay){var c=s.val().replace(a.prefix,"").replace(a.suffix,"");s.val(c)}s.val()!==r&&s.change()}function q(){var a,b=s.get(0);b.setSelectionRange?(a=s.val().length,b.setSelectionRange(a,a)):s.val(s.val())}var r,s=$(this);a=$.extend(a,s.data()),s.unbind(".maskMoney"),s.bind("keypress.maskMoney",k),s.bind("keydown.maskMoney",l),s.bind("blur.maskMoney",p),s.bind("focus.maskMoney",m),s.bind("click.maskMoney",q),s.bind("cut.maskMoney",n),s.bind("paste.maskMoney",n),s.bind("mask.maskMoney",h)})}};$.fn.maskMoney=function(b){return a[b]?a[b].apply(this,Array.prototype.slice.call(arguments,1)):"object"!=typeof b&&b?($.error("Method "+b+" does not exist on jQuery.maskMoney"),void 0):a.init.apply(this,arguments)}}(window.jQuery||window.Zepto);




