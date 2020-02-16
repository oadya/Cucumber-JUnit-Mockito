<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
  <script type="text/javascript">
  $(document).ready(function(){	
		});
</script>
<h3>Liste des formations</h3>
     <table id="formationsTable" border="1" class="table table-striped table-responsive">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Code</th>
                    <th>Libellé</th>
                    <th>Formateur</th>
                     <th>Salle</th>
                    <th>Nombre inscrits</th>
                    <th>Maximum inscrits</th>
                    <th>Statut</th>
                    <th>Ouverte</th>
                </tr>
            </thead>
           <c:if test="${jsonFormations.size() > 0}">
	           <c:forEach begin="0" end="${jsonFormations.size() -1}" var="index">
	            <tr>
	                <td>  ${jsonFormations[index].getId()}
	                <a class='dropdown-item' href='modification?id=${jsonFormations[index].getId()}' data-toggle='tooltip' data-placement='bottom' title='Editer la fiche formation'><i class='fas fa-edit'></i></a>
	                </td>
	                <td>${jsonFormations[index].getCode()}</td>
	                <td>${jsonFormations[index].getLibelle()}</td>
	                <td>${jsonFormations[index].getFormateur()}</td>
	                <td>${jsonFormations[index].getSalle()}</td>
	                <td>${jsonFormations[index].getNombreInscrits()}</td>
	                <td>${jsonFormations[index].getMaximumInscrits()}</td>
	                <td>${jsonFormations[index].getStatut()}</td>
	                <td>
	                	<c:if test="${jsonFormations[index].isOuverte() == true}">Disponible</c:if>
	                	<c:if test="${jsonFormations[index].isOuverte() == false}">Complet</c:if>
	                </td>
	            </tr>
	        </c:forEach>
        </c:if>
        <c:if test="${jsonFormations.size() == 0}">
        	<tr><td colspan=10>Aucune formation</td></tr>
        </c:if>
                 </table>
