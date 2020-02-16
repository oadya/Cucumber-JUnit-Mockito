<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
  <script type="text/javascript">
  $(document).ready(function(){	
		});
</script>
<h3>Liste des sujets de formation</h3>
     <table id="sujetsTable" border="1" class="table table-striped table-responsive">
            <thead>
                <tr>
                    <th>Code</th>
                    <th>Libéllé</th>
                    <th>Formateur</th>
                     <th>Prérequis</th>
                     <th>Actions</th>
                </tr>
            </thead>
           <c:if test="${jsonSujets.size() > 0}">
	           <c:forEach begin="0" end="${jsonSujets.size() -1}" var="index">
	            <tr>
	                <td>${jsonSujets[index].getCode()}</td>
	                <td>${jsonSujets[index].getLibelle()}</td>
	                <td>${jsonSujets[index].getFormateur()}</td>
	                <td>${jsonSujets[index].getPrerequis()}</td>
	                <td></td>
	            </tr>
	        </c:forEach>
        </c:if>
        <c:if test="${jsonSujets.size() == 0}">
        	<tr><td colspan=10>Aucun sujet</td></tr>
        </c:if>
        <tr>
           <form:form method="post"  modelAttribute="sujet" action="/sujets/ajout" class="form-group"> 
            <td> <form:input path="code" class="form-control" value=""/></td>
            <td> <form:input path="libelle" class="form-control" value=""/></td>
            <td> <form:input path="formateur" class="form-control" value=""/></td>
            <td> <form:input path="prerequis" class="form-control" value=""/></td>
            <td>                                  
            <input type="submit" class="btn btn-warning btn-lg btn-block" value="Ajouter"/>
            </td>
            </form:form>
         </tr>
        </table>
