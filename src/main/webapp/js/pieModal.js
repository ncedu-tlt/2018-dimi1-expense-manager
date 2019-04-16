$(document).ready(function() {
    if (document.location.href.indexOf('showModalpieRequired') != -1) {
        $('#Required').modal('show');
    }
    if (document.location.href.indexOf('showModalpieUnrequired') != -1) {
            $('#Unrequired').modal('show');
    }
});