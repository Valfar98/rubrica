    let campi=[
                {
                    label:"Nome",
                    nome_campo:"nome",
                    tipo:"text"
                },
                {
                    label:"Cognome",
                    nome_campo:"cognome",
                    tipo:"text"
                },
                {
                    label:"Cell.",
                    nome_campo:"cellulare",
                    tipo:"text"
                },
                {
                    label:"Email",
                    nome_campo:"email",
                    tipo:"email"
                },
                {
                    label:"Anni",
                    nome_campo:"anni",
                    tipo:"number"
                }
    ];


    let persone=localStorage.getItem("persone") || "[]";
    if(persone){
        try{
            persone=JSON.parse(persone);    
        }
        catch(e){
            console.error("Errore nel parsing dei dati delle persone:",e);
            persone=[];
        }
    }else{
        persone=[];
    }

  function renderForm(){
        let formDiv=document.getElementById("formDiv");
        formDiv.innerHTML="";

        const form=document.createElement("form");
        form.id="aggiungiForm"
        formDiv.appendChild(form);

        campi.forEach((campo,index)=>{
            const label=document.createElement("label");
            label.textContent=campo.label || campo.nome_campo.charAt(0).toUpperCase()+campo.nome_campo.slice(1);
            form.appendChild(label);

            const input=document.createElement("input");
            input.type=campo.tipo;
            input.name=campo.nome_campo;
            input.id=campo.nome_campo;
            input.required=true;
            form.appendChild(input);

            const removeButton=document.createElement("button");
            removeButton.textContent="Rimuovi";
            removeButton.type="button";
            removeButton.onclick=()=>{
                campi.splice(index,1);
                renderForm();
            }
            form.appendChild(removeButton);
            form.appendChild(document.createElement("br"));
        });

        const submitButton= document.createElement("button");
        submitButton.textContent="Salva";
        submitButton.type="button";
        form.appendChild(submitButton);

        submitButton.onclick=(e)=>{
            e.preventDefault();
            save();
            nascondi();
        }
    } 
    renderForm();

    let save = () => {
        let persona={};
        campi.forEach(campo => {
            const input = document.getElementById(campo.nome_campo);
            if (input) {
                persona[campo.nome_campo] = input.value;
            }
        });

        persone.push(persona);
        localStorage.setItem("persone", JSON.stringify(persone));

        alert("Dati salvati con successo!");
    }

   /* document.getElementById("addButton").addEventListener("click", () => {
        const nomeCampo = prompt("Inserisci il nome del campo:");
        const tipoCampo = prompt("Inserisci il tipo del campo (text, number, email):");
        const labelCampo = prompt("Inserisci l'etichetta del campo (opzionale):");
        
        if (nomeCampo && tipoCampo) {
            campi.push({
                nome: nomeCampo,
                tipo: tipoCampo,
                label: labelCampo || nomeCampo.charAt(0).toUpperCase() + nomeCampo.slice(1)
            });
            renderForm();
        } else {
            alert("Nome e tipo del campo sono obbligatori.");
        }
    });
    */
     
    function addField(){
        const tipo_campo = document.getElementById("tipo_campo").value;
        const nome_campo = document.getElementById("nome_campo").value;
        const testo_label = document.getElementById("testo_label").value;
        if (tipoCampo && nomeCampo) {
            const campo = {
                tipo: tipo_campo,
                nome_campo: nome_campo,
                label: testo_label || nome_campo.charAt(0).toUpperCase() + nome_campo.slice(1)
            }  
            campi.push(campo);
            renderForm();
        
        document.getElementById("addFieldModal").querySelector(".btn-close").click(); // Chiude il modal
                // Pulisce i campi del modal
                document.getElementById("tipo_campo").value = "Scegli";
                document.getElementById("nome_campo").value = "";
                document.getElementById("testo_label").value = "";
        } else {
            alert("Tipo e nome del campo sono obbligatori.");
        }
    }
    document.getElementById("savenewfield").addEventListener("click", addField);

    function nascondi(){
        let formElement = document.getElementById("formDiv"); // Ho cambiato il nome della variabile per chiarezza

            if (formElement.style.display === "none") {
                // Se è nascosto, mostralo
                formElement.style.display = "block"; // O "flex", "grid", a seconda di come vuoi che sia visualizzato
            } else {
                // Se è visibile, nascondilo
                formElement.style.display = "none";
            }
        }
