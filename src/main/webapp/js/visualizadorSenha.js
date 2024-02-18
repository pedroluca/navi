const passwordInput = document.querySelector("#senha");
const togglePassword = document.querySelector("#togglePassword");

togglePassword.addEventListener("click", function() {
  if (passwordInput.type === "password") {
    passwordInput.type = "text";
    togglePassword.classList.replace("fa-eye", "fa-eye-slash");
  } else {
    passwordInput.type = "password";
    togglePassword.classList.replace("fa-eye-slash", "fa-eye");
  }
});

const passwordInput2 = document.querySelector("#senha2");
const togglePassword2 = document.querySelector("#togglePassword2");

togglePassword2.addEventListener("click", function() {
  if (passwordInput2.type === "password") {
    passwordInput2.type = "text";
    togglePassword2.classList.replace("fa-eye", "fa-eye-slash");
  } else {
    passwordInput2.type = "password";
    togglePassword2.classList.replace("fa-eye-slash", "fa-eye");
  }
});

const passwordInput3 = document.querySelector("#senha3");
const togglePassword3 = document.querySelector("#togglePassword3");

togglePassword3.addEventListener("click", function() {
  if (passwordInput3.type === "password") {
    passwordInput3.type = "text";
    togglePassword3.classList.replace("fa-eye", "fa-eye-slash");
  } else {
    passwordInput3.type = "password";
    togglePassword3.classList.replace("fa-eye-slash", "fa-eye");
  }
});