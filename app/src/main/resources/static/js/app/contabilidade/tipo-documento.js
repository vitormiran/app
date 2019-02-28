$(document).ready(function(){
	bindEventos();
})

function bindEventos(){
	$("#btn-add-metadado").on('click', addItem);
	$(".campo-nome-tpdoc").on('blur', completaDescricao);
	$(".btn-delete-metadado").on('click', deleteItem);
	
}

function completaDescricao(e){
	var input = $(e.target);
	input.parent().parent().find('.campo-desc-tpdoc').val(input.val());
}


function addItem(){
	
	
	var idx = $("#size-itens").val();
	
	var html = '<tr>'+
		'<td> <input type="text" class="form-control campo-nome-tpdoc" onblur="completaDescricao(event)" id="metadados'+idx+'.nome" name="metadados['+idx+'].nome" value=""> </td>'+
		'<td> <input type="text" class="form-control campo-desc-tpdoc" id="metadados'+idx+'.descricao" name="metadados['+idx+'].descricao" value=""> </td>'+
		'<td> <input type="checkbox" id="metadados'+idx+'.obrigatorio1" name="metadados['+idx+'].obrigatorio" value="true"><input type="hidden" name="_metadados['+idx+'].obrigatorio" value="on"> </td>'+
		'<td> <input type="checkbox" id="metadados'+idx+'.desabilitado1" name="metadados['+idx+'].desabilitado" value="true"><input type="hidden" name="_metadados['+idx+'].desabilitado" value="on"> </td>';
	
	if (isNew()){
		html += '<td class="right">'+
		'	<a href="#" onclick="deleteItem(event)"  class="btn-delete-metadado"><i class="fa fa-trash"></i> Excluir</a>'+
		'</td>';
	}
	html += '</tr>';
	
	$("#tbody-itens").append(html);
	$("#size-itens").val(parseInt(idx)+1);
	
}

function deleteItem(e){
	var btn = $(e.target);
	console.log(btn);
	btn.parent().parent().remove();
}

function isNew() {
	return $("#txt-id").length == 0;
} 