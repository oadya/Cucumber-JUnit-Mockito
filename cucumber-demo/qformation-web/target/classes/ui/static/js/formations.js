/**
 * 
 */

function afficherDataTableFormations(jsonFormations) {
	  var table = $('#FormationsTable').DataTable({
			"data":jsonFormations,
			"sAjaxDataProp": "",
			"order": [[ 0, "asc" ]],
			 "responsive": true,
			 "dom": "Blfrtip",
		     "buttons": ['excel', 'pdf', 'print'],
			"aoColumns": [
			    { "mData": "id"},
		      	{ 
			      "data" : null,
			      "defaultContent": "",
			      "render": function ( data, type, row ) {
		           		var str="<a class='dropdown-item' href='update?id=" + row.id+ "'>" + row.code + "</a>";
						return str;
	                	}
                	},
				{ "mData": "libelle" },
				{ "mData": "formateur" },
				{ "mData": "salle" },
				{ "mData": "nombreInscrits" },
				{ "mData": "maximumInscrits" },
				{ "mData": "nombreInscrits" },
				{ "mData": "statut" },
				{ "mData": "ouverte" },
		        {
		           "orderable":      false,
		           "data":           "id",
		           "defaultContent": "",
		           "render": function ( data, type, row ) {
			           		var str="<a class='dropdown-item' href='update?id=" + data + "' data-toggle='tooltip' data-placement='bottom' title='Editer la fiche'><i class='fas fa-edit'></i></a>  "
			           		str=str +"<a class='dropdown-item' href='/formations?id=" + data + "' data-toggle='tooltip' data-placement='bottom' title='Voir les stagiaires'><i class='fas fa-truck'></i></a>   "
			           		str= str +"<a class='dropdown-item' href='/formations/delete?id=" + data + "' data-toggle='tooltip' data-placement='bottom' title='Supprimer la formation'><i class='fas fa-trash'></i></a>"
							return str;
		                }
		   	    }
			],
		
			  "language": {
			        "search": "Recherche",
			        "zeroRecords":    "Aucun transporteur",
			        "info":           "Affichage _START_ à _END_ sur _TOTAL_ transporteurs",
			        "lengthMenu":     "Afficher _MENU_ lignes",
			        "processing":     "En cours de traitement...",
			        "paginate": {
			            "first":      "Premier",
			            "last":       "Dernier",
			            "next":       "Prochain",
			            "previous":   "Précédent"
			        }
			    }
		});
	  $('.dt-button').addClass("btn").addClass("btn-primary"); 
	}
