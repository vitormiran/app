var tableItens = null;

$(document).ready(function(){
	initFields();
	initMasks();
	initTable();
});

function initMasks(){
	$('#texto-preco').mask('#.##0,00', {reverse: true});
}

function initFields(){
	$("#combo-produto").on('change', comboProdutoChange);
	$("#btn-add").on('click', addPedido);
}

function comboProdutoChange(){
	var preco = $("#combo-produto").find(':selected').data('preco');
	preco = $("#texto-preco").masked(preco);
	$("#texto-preco").val(preco);
}

function addPedido(){
	var idProduto   = $("#combo-produto").val();
	var descProduto = $("#combo-produto").find(":selected").text();
	var preco       = $("#texto-preco").val();
	var quantidade  = $("#texto-quantidade").val();
	var observacao  = $("#texto-observacao").val();
	var dias = recuperaArrayDias();
	
	var idx = recuperaProximoIdx();
	
	
	$(dias).each(function(i,dia){
		var html = "<tr>" +
		"<td>" +
		"	<input type='hidden' class='txt-idx' value='"+idx+"' />" +
		"	<input type='hidden' name='pedidoItens["+idx+"].descricaoProduto' value='"+descProduto+"' />" +
		"	<input type='hidden' name='pedidoItens["+idx+"].idProduto' value='"+idProduto+"' />"+descProduto+
		"</td>" +
		"<td><input type='hidden' name='pedidoItens["+idx+"].dia' value='"+dia+"' />"+dia+"</td>" +
		"<td><input type='hidden' name='pedidoItens["+idx+"].preco' value='"+preco+"' />"+preco+"</td>" +
		
		"<td><input type='hidden' name='pedidoItens["+idx+"].quantidade' value='"+quantidade+"' />"+quantidade+"</td>" +
		"<td><input type='hidden' name='pedidoItens["+idx+"].observacao' value='"+observacao+"' />"+observacao+"</td>" +
		"</tr>";
		
		//$("#tbody-produto").append(html);
		
		tableItens.row.add( [
			"	<input type='hidden' class='txt-idx' value='"+idx+"' />" +
			"	<input type='hidden' name='pedidoItens["+idx+"].descricaoProduto' value='"+descProduto+"' />" +
			"	<input type='hidden' name='pedidoItens["+idx+"].idProduto' value='"+idProduto+"' />"+descProduto,
			
			"<input type='hidden' name='pedidoItens["+idx+"].dia' value='"+dia+"' />"+dia,
			
			"<input type='hidden' name='pedidoItens["+idx+"].preco' value='"+preco+"' />"+preco,
			
			"<input type='hidden' name='pedidoItens["+idx+"].quantidade' value='"+quantidade+"' />"+quantidade,
			
			"<input type='hidden' name='pedidoItens["+idx+"].observacao' value='"+observacao+"' />"+observacao,
			
			"<a href='#' class='btn btn-xs btn-danger ft-pqn' onclick='deleteRow(this)'> <i class='fa fa-times ft-pqn'></i> </a>"
        ] ).draw(false);
		
		idx++;
		
		
		
	})
	resetFormPedido();
}

var row = null;
function deleteRow(btn){
	var tr = $(btn).parent().parent();
	row = tr;
}

function recuperaProximoIdx(){
	var maior = -1;
	$(".txt-idx").each(function(i,e){
		var valor = parseInt($(e).val());
		console.log(valor);
		if (valor > maior){
			maior = valor;
		}
	});
	return maior+1;
}

function resetFormPedido(){
	$("#combo-produto").val("");
	$("#texto-preco").val("");
	$("#texto-quantidade").val("");
	$("#texto-observacao").val("");
	$(".chk-dia").prop("checked", false)
}

function recuperaArrayDias(){
	var dias = [];
	$(".chk-dia").each(function(i, chk){
		if( $(chk).is(':checked') ){
			dias.push($(chk).data('dia'));
		}
	})
	return dias;
}

function initTable(){
	var responsiveHelper_dt_basic = undefined;
	var responsiveHelper_datatable_fixed_column = undefined;
	var responsiveHelper_datatable_col_reorder = undefined;
	var responsiveHelper_datatable_tabletools = undefined;
	var breakpointDefinition = {
			tablet : 1024,
			phone : 480
		};

	tableItens = $('#dt_basic').DataTable({
		"autoWidth" : true,
		searching: false, 
		paging: false, 
		info: false,
		"columns": [
		    null,
		    null,
		    { className: "right" },
		    { className: "right" },
		    null,
		    { className: "right" }
		  ]
	});
}
