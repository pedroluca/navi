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
