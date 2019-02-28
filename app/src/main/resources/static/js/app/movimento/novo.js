var tableItens = null;


$(document).ready(function(){
	initTable();
});

function initTable(){
	var breakpointDefinition = {
		tablet : 1024,
		phone : 480
	};

	tableItens = $('#dt_itens_movimento').DataTable({
		"autoWidth" : true,
		searching: true, 
		paging: false, 
		info: false,
		"columns": [
		    null,
		    null,
		    null,
		    { className: "right" },
		    { className: "right" }
		  ]
	});
}
