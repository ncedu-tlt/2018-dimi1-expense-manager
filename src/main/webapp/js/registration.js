function validateAuthorization()
{
   var uname=document.loginForm.login.value;
   var pass=document.loginForm.pass.value;

   if(uname =="" || pass == "")
   {
       document.getElementById("errors").innerHTML = 'You have not entered a username or password!';
       $('#modalError').modal('show');
       return false;

   }
   if((uname.length < 1) || (uname.length > 15) ||
     (pass.length < 1) || (pass.length > 15))
   {
        document.getElementById("errors").innerHTML = 'Username and password must be greater than 5 and less than 15 characters!';
        $('#modalError').modal('show');
        return false;
   }
}

function validateRegistration()
{
       var uname=document.registrationForm.login.value;
       var pass=document.registrationForm.pass.value;
       var email=document.registrationForm.email.value;

       var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
       if(uname == "" || pass == "")
       {
           document.getElementById("errors").innerHTML = 'You have not entered a username or password!';
           $('#modalError').modal('show');
           return false;

       }
       if((uname.length < 5) || (uname.length > 15) ||
         (pass.length < 5) || (pass.length > 15))
       {
            document.getElementById("errors").innerHTML = 'Username and password must be greater than 5 and less than 15 characters!';
            $('#modalError').modal('show');
            return false;
       }
       if(reg.test(email) == false)
       {
           document.getElementById("errors").innerHTML = 'Incorrect mail!';
           $('#modalError').modal('show');
           return false;
       }
}