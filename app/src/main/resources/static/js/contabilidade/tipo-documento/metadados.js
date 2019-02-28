function initPage(){
	bindBotoes();
}

function bindBotoes(){
	$("#btn-add-metadado").on('click', addRowMetadado)
}

function addRowMetadado(){
	var totalLinhas = $("#rows-metadata").children().length;
	
	var linha = '<tr>' + 
		'	<td> <input type="text" name="nomes['+totalLinhas+']" class="form-control" /> </td>' + 
		'	<td>' + 
		'		<select name="obrigatorios['+totalLinhas+']" class="form-control">' + 
		'			<option value=""></option>' + 
		'			<option value="true">Sim</option>' + 
		'			<option value="false">Não</option>' + 
		'		</select>' + 
		'	</td>' + 
		'	<td style="text-align:right">' + 
		'		<a class="btn-delete-linha btn btn-xs btn-danger"><i class="fa fa-times"></i> Excluir</a>' + 
		'	</td>' + 
		'</tr>';
	
	$("#rows-metadata").append(linha);	
}