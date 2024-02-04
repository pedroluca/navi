/**
 * Validação de formulario
 */

 function validarCadastro(){
	 let nome = frmCadastro.nome.value
	 let telefone = frmCadastro.telefone.value
	 let email = frmCadastro.email.value
	 let senha = frmCadastro.senha.value
	 let confirmaSenha = frmCadastro.confirmaSenha.value
	 let generoFeminino = frmCadastro.genero[0].checked;
	 let generoMasculino = frmCadastro.genero[1].checked;
	 let usuario = frmCadastro.usuario.value
	 
	 if(nome === ""){
		 alert("Preencha o campo Nome")
		 frmCadastro.nome.focus()
		 return false
	 } else if(usuario === ""){
		 alert("Preencha o campo Username")
		 frmCadastro.usuario.focus()
		 return false
	 } else if(telefone === ""){
		 alert("Preencha o campo Telefone")
		 frmCadastro.telefone.focus()
		 return false
	 } else if(email === ""){
	  	 alert("Preencha o campo Email")
		 frmCadastro.email.focus()
		 return false
	 } else if(senha === ""){
		 alert("Preencha o campo Senha")
		 frmCadastro.senha.focus()
		 return false
	 } else if(confirmaSenha === ""){
		 alert("Preencha o campo Confirme sua Senha")
		 frmCadastro.confirmaSenha.focus()
		 return false
	 } else if(!generoFeminino && !generoMasculino) {
         alert("Selecione o seu gênero");
         return false;
	 } else{
		 document.forms["frmCadastro"].submit()
	 }
 }
 
 function validarLogin(){
    let email = frmLogin.email.value;
    let senha = frmLogin.senha.value;
    
    if(email === ""){
        alert("Preencha o campo email");
        frmLogin.email.focus();
        return false;
    } else if(senha === ""){
        alert("Preencha o campo Senha");
        frmLogin.senha.focus();
        return false;
    } else{
		document.forms["frmLogin"].submit()
	}
}