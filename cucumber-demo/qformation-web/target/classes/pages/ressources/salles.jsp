<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
  <script type="text/javascript">
  $(document).ready(function(){	
		});
</script>
<h3>Liste des salles de formation</h3>
     <table id="sallesTable" border="1" class="table table-striped table-responsive">
            <thead>
                <tr>
                    <th>Salle</th>
                    <th>Capacité</th>
                     <th>Actions</th>
                </tr>
            </thead>
           <c:if test="${jsonSalles.size() > 0}">
	           <c:forEach begin="0" end="${jsonSalles.size() -1}" var="index">
	            <tr>
	                <td>${jsonSalles[index].getSalle()}</td>
	                <td>${jsonSalles[index].getCapacite()}</td>
	                <td></td>
	            </tr>
	        </c:forEach>
        </c:if>
        <c:if test="${jsonSalles.size() == 0}">
        	<tr><td colspan=10>Aucune salle</td></tr>
        </c:if>
        <tr>
           <form:form method="post"  modelAttribute="salle" action="/salles/ajout" class="form-group"> 
            <td> <form:input path="salle" class="form-control" value=""/></td>
            <td> <form:input path="capacite" class="form-control" value=""/></td>
            <td>                                  
            <input type="submit" class="btn btn-warning btn-lg btn-block" value="Ajouter"/>
            </td>
            </form:form>
         </tr>
        </table>
