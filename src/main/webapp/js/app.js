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
	 let username = frmCadastro.username.value
	 
	 if(nome === ""){
		 alert("Preencha o campo Nome")
		 frmCadastro.nome.focus()
		 return false
	 } else if(username === ""){
		 alert("Preencha o campo Username")
		 frmCadastro.username.focus()
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
	 }  else{
		 document.forms["frmCadastro"].submit()
	 }
 }
 
 function validarLogin(){
    let usuario = frmLogin.usuario.value;
    let senha = frmLogin.senha.value;
    
    if(usuario === ""){
        alert("Preencha o campo usuário");
        frmLogin.usuario.focus();
        return false;
    } else if(senha === ""){
        alert("Preencha o campo Senha");
        frmLogin.senha.focus();
        return false;
    } else{
		document.forms["frmLogin"].submit()
		}
}