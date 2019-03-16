$(document).ready(function() {
    if (document.location.href.indexOf('showModalError') != -1) {
        document.getElementById("errors").innerHTML = 'A card with this number already exists!';
        $('#modalPurse').modal('hide');
        $('#modalError').modal('show');
    }
    else if (document.location.href.indexOf('showModal') != -1) {
        $('#modalPurse').modal('show');
    }

    var p = document.querySelectorAll('td p');
     for (var i=0; i<p.length; i++)  {
        if(p[i].innerHTML == "Credit Card")
        {
            p[i].style.color = 'red';
        }
        else if(p[i].innerHTML == "Debit Card")
        {
            p[i].style.color = 'blue';
        }
        else if(p[i].innerHTML == "Cash")
        {
            p[i].style.color = 'green';
        }
     }


});

function getValue(value) {

    var ghost = document.getElementById("txtHiddenCardNumber");
    if(value !== "" && value != "Cash")
    {
        ghost.type = 'text';
    }
    else
    {
        ghost.type = 'hidden';
    }
}

    var a=0;
function A() {

    if (a==0) {
        document.all.id_table.style.display="block";
        a=1
    }
    else{
        document.all.id_table.style.display="none";
        a=0
    }
}

function toggleText(button_id){

   var el = document.getElementById(button_id);
   if (el.firstChild.data == "New")
   {
       el.firstChild.data = "Unlock";
   }
   else
   {
       el.firstChild.data = "New";
   }
}

function validatePurse()
{
   var uname=document.purseForm.balance.value;

   if(uname == "")
   {
       alert("test");
      return false;

   }
   else
   {
        alert( '111' );
   }

}
function validatePurse()
{
       var ghost = document.getElementById("txtHiddenCardNumber");
       var balance=document.purseForm.balance.value;
       var ghost1 = document.purseForm.txtHiddenCardNumber.value;

       var dotNumbers = /^\d+.?\d+$/;
       var numbers = /^\d+$/;
       if(balance == "")
       {
           document.getElementById("errors").innerHTML = 'You have not entered a username or password!';
           $('#modalPurse').modal('hide');
           $('#modalError').modal('show');
           return false;

       }
       if(dotNumbers.test(balance) == false)
       {
          document.getElementById("errors").innerHTML = 'Invalid balance!';
          $('#modalPurse').modal('hide');
          $('#modalError').modal('show');
          return false;
       }

       if(ghost.type == 'text')
       {
            if(numbers.test(ghost1) == false || ghost1.length < 16 || ghost1.length > 16)
            {
                document.getElementById("errors").innerHTML = 'Invalid card number!';
                  $('#modalPurse').modal('hide');
                  $('#modalError').modal('show');
                  return false;
            }
       }
}
