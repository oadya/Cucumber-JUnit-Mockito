<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.17.0/jquery.validate.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.17.0/dist/additional-methods.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
    $('#menuRessources').addClass("active");
    $('#salle').val("${formation.salle}");
});

function choixSalle() {
	var selectBox = document.getElementById("salle");
	var capacite = selectBox.options[selectBox.selectedIndex].getAttribute("capacite");
	document.getElementById("maximumInscrits").value=capacite;
}
</script>

<div class="row">
        <form:form method="post" modelAttribute="formation" action="/formations/modification" class="form-group"> 
        		<div class="row">
        		<div class="form-group col-md-4"><h3>Modifier une formation</h3></div>
				   <div class="form-group col-md-4">                     
	            	<input type="submit" class="btn btn-warning btn-lg btn-block" value="Modifier"/>
	            	</div>
	            	<div class="form-group col-md-4">  
	            	
	            	</div>
            	</div>
            <div class="form-row">
             <div class="form-group col-md-1">
		      <form:label cssClass="Identifiant"  path="id">Identifiant</form:label>
		      <form:input path="id" class="form-control" value="${formation.id}" readonly="true"/>
		    </div>
           	<div class="form-group col-md-1">
		      <form:label cssClass="code"  path="code">Code <form:errors path="code"  class="alert alert-danger"/></form:label>
		      <form:input path="code" class="form-control" value="${formation.code}" readonly="true"/>
		    </div>
		    <div class="form-group col-md-2">
		      <form:label cssClass="libelle"  path="libelle" >Libellé <form:errors path="libelle"  class="alert alert-danger"/></form:label>
		      <form:input path="libelle" class="form-control" value="${formation.libelle}" readonly="true"/>
		    </div>
		    <div class="form-group col-md-4">
		     <form:label cssClass="date"  path="date" >Date de la formation</form:label>
			      <div class='input-group date' id='datetimepickerDateFormation'>
	                    <form:input path="date" class="form-control" value="${formation.date}"/>
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
			      <form:input path="formateur" class="form-control" value="${formation.formateur}"/>
			    </div>
			    <div class="form-group col-md-4">
			      <form:label cssClass="maximumInscrits"  path="maximumInscrits" >Maximum de stagiaires</form:label>
			      <form:input path="maximumInscrits" class="form-control" value="${formation.maximumInscrits}"/>
			    </div>
			    <div class="form-group col-md-4">
			      <form:label cssClass="nombreInscrits"  path="nombreInscrits" >Nombre de stagiaires</form:label>
			      <form:input path="nombreInscrits" class="form-control" value="${formation.nombreInscrits}" readonly="true"/>
			    </div>
			   </div>

        </form:form>
 </div>
 <br><br>
 <div class="row">     
 <ul class="nav nav-tabs ">
    <li class="active" class="nav-iteam"><a class="nav-link active" data-toggle="tab" href="#tabStagiaires"><i class="fas fa-user"></i>  Stagiaires</a></li>
    <li class="nav-item"><a data-toggle="tab" href="#tabBilan" class="nav-link disabled" ><i class="fas fa-file-alt"></i>  Bilan</a></li>
  </ul>
  <br>
  <div class="container">
   <div class="tab-content">
    <div id="#tabStagiaires" class="tab-pane fade in active">
     <table border="1" class="table table-striped display">
            <thead>
                <tr>
                    <th>Matricule</th>
                    <th>Nom</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <c:if test="${jsonStagiaires.size() > 0}">
        <c:forEach begin="0" end="${jsonStagiaires.size() -1}"  var="row">
                  <tr>
                      <td>${jsonStagiaires[row].getMatricule()}</td>
                      <td>${jsonStagiaires[row].getNom()}</td>
                      <td>
                      <a class="dropdown-item" href="/formations/desinscrire?idFormation=${formation.id}&matricule=${jsonStagiaires[row].getMatricule()}" data-toggle="tooltip" data-placement="bottom" title="Désinscrire"><i class="fas fa-trash"></i></a>
                      </td>
                  </tr>
         </c:forEach>
         </c:if>
          <tr>
           <form:form method="post"  modelAttribute="stagiaire" action="/formations/inscription" class="form-group"> 
            <td> <form:input path="matricule" class="form-control" value=""/></td>
            <td> <form:input path="nom" class="form-control" value=""/></td>
            <td>
            <form:hidden path="idFormation" class="form-control" value="${formation.id}"/>                                   
            	<input type="submit" class="btn btn-warning btn-lg btn-block" value="Inscrire"/>
            </td>
            </form:form>
           </tr>
        </table>
	    </div>
		    <div id="#tabBilan" class="tab-pane fade">
		      <h3>Menu 1</h3>
		      <p>Voici le bilan</p>
		    </div>
		  </div>
		</div>           
 </div>
 <div class="row">
<form:form id="formAnnulation" method="post" modelAttribute="formation" action="/formations/annulation?id=${formation.id}" class="form-group">                   
    <div class="form-group col-md-4"></div>  <div class="form-group col-md-4"></div>
  <div class="form-group col-md-4">
<input type="submit" class="btn btn-danger btn-lg btn-block" value="Annuler la formation"/>
  </div>
</form:form> 
</div> 