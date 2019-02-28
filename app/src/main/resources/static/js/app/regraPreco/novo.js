$(document).ready(function(){
	initMasks();
	bindEvents();
});

function bindEvents(){
	$('form').on('submit', function(e){
		e.preventDefault();
		alert("ok");
	});
}

function initMasks(){
	$('#texto-preco').mask('#.##0,00', {reverse: true});
}

function submit(e){
	e.preventDefault();
}
