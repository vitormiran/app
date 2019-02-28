$(document).ready(function(){
	bindEventos(); 
	
});

function bindEventos(){
	$("#administrador1").on('click', controleCheckboxAdm)
	$(".chk-pasta").on('click', checkPastas);
}

function controleCheckboxAdm(){
	controlePerfilAdm( $(this).is(":checked") );
}

function controlePerfilAdm(val){
	if (val){
		$(".chk-pasta").prop('checked', true);
	}
}

function checkPastas(){
	if ( $("#administrador1").is(":checked") ){
		return false;
	}
}