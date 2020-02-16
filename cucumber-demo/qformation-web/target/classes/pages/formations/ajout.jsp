<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.17.0/jquery.validate.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.17.0/dist/additional-methods.min.js"></script>
<script type="text/javascript">
function choixCode() {
	var selectBox = document.getElementById("code");
	var libelle = selectBox.options[selectBox.selectedIndex].getAttribute("libelle");
	var formateur = selectBox.options[selectBox.selectedIndex].getAttribute("formateur");
	document.getElementById("libelle").value=libelle;
	document.getElementById("formateur").value=formateur;
}
function choixSalle() {
	var selectBox = document.getElementById("salle");
	var capacite = selectBox.options[selectBox.selectedIndex].getAttribute("capacite");
	document.getElementById("maximumInscrits").value=capacite;
}
</script>

<h2>Planifier une formation</h2>
        <form:form method="post" modelAttribute="formation" action="/formations/ajout" class="form-group"> 
            <div class="form-row">
           	<div class="form-group col-md-2">
		      <form:label cssClass="code"  path="code">Code <form:errors path="code"  class="alert alert-danger"/></form:label>
		     <form:select path="code" class="form-control" placeholder="" onchange="choixCode()">
		        <form:option value=""></form:option>
		        <c:forEach begin="0" end="${jsonSujets.size() -1}" var="index">
                    <form:option value="${jsonSujets[index].getCode()}" label="${jsonSujets[index].getCode()}-${jsonSujets[index].getLibelle()}" formateur="${jsonSujets[index].getFormateur()}" libelle="${jsonSujets[index].getLibelle()}"/>
                 </c:forEach>
		      </form:select>

		    </div>
		    <div class="form-group col-md-2">
		      <form:label cssClass="libelle"  path="libelle" >Libellé <form:errors path="libelle"  class="alert alert-danger"/></form:label>
		      <form:input path="libelle" class="form-control" placeholder=""/>
		    </div>
		    <div class="form-group col-md-4">
		     <form:label cssClass="date"  path="date" >Date de la formation</form:label>
			      <div class='input-group date' id='datetimepickerDateFormation'>
	                    <form:input path="date" class="form-control" placeholder=""/>
	                    <span class="input-group-addon">
	                        <span class="fas fa-calendar-alt"  onclick="$('#datetimepickerDateFormation').datepicker({language: 'fr'});"></span>
	                    </span>
                	</div>
		    </div>
		    
		  </div>
             <div class="form-row">
			    <div class="form-group col-md-4">
			      <form:label cssClass="salle"  path="salle" >Salle <form:errors path="salle"  class="alert alert-danger"/></form:label>
				  <form:select path="salle" class="form-control" placeholder="" onchange="choixSalle()">
				  	<form:option value=""></form:option>
				    	<c:forEach begin="0" end="${jsonSalles.size() -1}" var="index">
		                	<form:option value="${jsonSalles[index].getSalle()}" label="${jsonSalles[index].getSalle()}" capacite="${jsonSalles[index].getCapacite()}"/>
		                </c:forEach>
				 	</form:select>
			    </div>
			    <div class="form-group col-md-4">
			      <form:label cssClass="formateur"  path="formateur" >Formateur</form:label>
			      <form:input path="formateur" class="form-control" placeholder="-- -- -- -- --"/>
			    </div>
			    <div class="form-group col-md-4">
			      <form:label cssClass="maximumInscrits"  path="maximumInscrits" >Maximum de stagiaires</form:label>
			      <form:input path="maximumInscrits" class="form-control" placeholder=""/>
			    </div>
			   </div>
			   <div class="form-row">
			   <div class="form-group col-md-12">                     
            	<input type="submit" class="btn btn-warning btn-lg btn-block" value="Ajouter la formation"/>
            	</div>
            	</div>
        </form:form>