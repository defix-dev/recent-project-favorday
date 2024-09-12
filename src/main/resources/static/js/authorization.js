$(document).ready(() => {
   $("#register-form").submit((e) => {
       e.preventDefault();
       const formData = new FormData(e.target);
       const data = {
           username: formData.get("reg-username"),
           name: formData.get("reg-name"),
           surname: formData.get("reg-surname"),
           password: formData.get("reg-password"),
           confirmPassword: formData.get("reg-repeat-password")
       };

       $.ajax({
           url: "authorization/register",
           type: "POST",
           data: JSON.stringify(data),
           contentType: "application/json",
           success: () => {
               alert("SUCCESS");
           },
           error: () => {
               alert("ERROR");
           }
       });
   });
});