function addp(event){
    $.ajax({
       type: "POST",
       url: "addproduct",
     
       data: {
           Tipo : $("#Tipo").val(),
           Descrizione : $("#Descrizione").val(),
           Prezzo : $("#Prezzo").val(),
           Img : $("#Img").val()           
       },
       success:function(){
    	   alert("Prodotto Inserito corretamente");
    	   window.location.replace("gestioneProdotti.jsp");
    	  
       },
        error : function () {
            alert("il prodotto non è stato inserimento corretamente");
     	   window.location.replace("gestioneProdotti.jsp");

        }
    });
}
function updatep(event){
    $.ajax({
       type: "POST",
       url: "updateprodotto",
     
       data: {
    	   Pr1 : $("#pr1").val(),
           Tipo1 : $("#Tipo1").val(),
           Descrizione1 : $("#Descrizione1").val(),
           Prezzo1 : $("#Prezzo1").val()
       },
       success:function(){
    	   alert("Prodotto aggiornato corretamente");
    	   window.location.replace("gestioneProdotti.jsp");

    	  
       },
        error : function () {
            alert("il prodotto non è stato aggiornato corretamente o id prodotto non corretto");
     	   window.location.replace("gestioneProdotti.jsp");

        }
    });
}
function deletep(event){
    $.ajax({
       type: "POST",
       url: "deleteprodotto",
     
       data: {
           idprodo : $("#idprodo").val()
       },
       success:function(){
    	   alert("Prodotto eliminato corretamente");
    	   window.location.replace("gestioneProdotti.jsp");
    	  
       },
        error : function () {
            alert("il prodotto non è stato eliminato corretamente o id prodotto non corretto");
     	   window.location.replace("gestioneProdotti.jsp");

        }
    });
    
}
function returnhome(event){
    $.ajax({
       type: "POST",
       url: "index.jsp",
       success:function(){
    	   var answer =window.confirm("Vuoi tornare alla home? I dati nei form andrnno persi")
    	   if(answer)
    		   window.location.replace("index.jsp");
    	   else{}
    	  
       },
        error : function () {
           window.location.replace("gestioneProdotti.jsp");

        }
    });
    
}
function addc(event){
    $.ajax({
       type: "POST",
       url: "rooms_servlet",
     
       data: {
    	   Id1 : $("#Id1").val(),
    	   Tipo1 : $("#Tipo1").val(),
           Descrizione1 : $("#Descrizione1").val(),
           numMaxPersone1 : $("#numMaxPersone1").val(),
           Prezzo1 : $("#Prezzo1").val()
       },
       success:function(){
    	   alert("Camera Inserita corretamente");
    	   window.location.replace("gestioneCamere.jsp");
    	  
       },
        error : function () {
            alert("la camera  non è stata inserita corretamente");
     	   window.location.replace("gestioneCamere.jsp");

        }
    });
}
function updatec(event){
    $.ajax({
       type: "POST",
       url: "updaterooms_servlet",
     
       data: {
    	   Id2 : $("#Id2").val(),
    	   Tipo2 : $("#Tipo2").val(),
           Descrizione2 : $("#Descrizione2").val(),
           numMaxPersone2 : $("#numMaxPersone2").val(),
           Prezzo2 : $("#Prezzo2").val()
       },
       success:function(){
    	   alert("camera aggiornata corretamente");
    	   window.location.replace("gestioneCamere.jsp");

    	  
       },
        error : function () {
            alert("il prodotto non è stato aggiornato corretamente o id prodotto non corretto");
     	   window.location.replace("gestioneCamere.jsp");

        }
    });
}
function deletec(event){
    $.ajax({
       type: "POST",
       url: "deleterooms_servlet",
     
       data: {
    	   Id3 : $("#Id3").val()
       },
       success:function(){
    	   alert("Camera eliminata corretamente");
    	   window.location.replace("gestioneCamere.jsp");
    	  
       },
        error : function () {
            alert("la Camera non  e stata eliminato corretamente o id prodotto non corretto");
     	   window.location.replace("gestioneCamere.jsp");

        }
    });
    
}
function returnhomer(event){
    $.ajax({
       type: "POST",
       url: "index.jsp",
       success:function(){
    	   var answer =window.confirm("Vuoi tornare alla home? I dati nei form andrnno persi")
    	   if(answer)
    		   window.location.replace("index.jsp");
    	   else{}
    	  
       },
        error : function () {
           window.location.replace("gestioneCamere.jsp");

        }
    });
    
}