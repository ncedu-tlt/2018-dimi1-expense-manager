<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<!DOCTYPE html>
<html lang="ru">
  <head>
   <!-- Required meta tags -->
   <meta http-equiv="Content-Type" content="text/html; charset=windows-1251" />
   <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
   <title>Sign in · Web Site</title>
   <!-- Bootstrap CSS -->
   <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous"></link>
   <link href="/css/menu.css" rel="stylesheet" type="text/css"/></link>

    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>


   <!-- jQuery first, then Tether, then Bootstrap JS. -->
   <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
   <script src="https://code.jquery.com/jquery-3.3.1.js" crossorigin="anonymous"></script>
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
   <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
   <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>

   <script src="/js/menu.js" rel="stylesheet" type="text/javascript"></script>
   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>
    <script src="https://www.google.com/jsapi"></script>


    <form name="purseForm" action="/newAccount" method="post">
        <div class="modal fade" id="modalPurse" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content tec">
                    <div class="modal-header bord">
                        <h5 class="modal-title" id="exampleModalLabel">Accounts</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <table class="table">
                            <thead>
                            <tr>
                                <td scope="col">Number Card</th>
                                <td scope="col">Currency</th>
                                <td scope="col">Balance</th>
                                <td scope="col">Description</th>
                                <td scope="col">Information</th>
                            </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${accounts}" var="account">
                                    <tr>
                                        <td>${account.getAccountNumber()}</td>
                                        <td>${account.getCurrency()}</td>
                                        <td>${account.getBalance()}</td>
                                        <td><p>${account.getDescription()}</p></td>
                                        <td><a href="/budget/${account.getAccountId()}" class="btn btn-primary" role="button" aria-pressed="true">budget</a></button></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <table class="table" id="id_table" style="display: none;">
                            <thead>
                                <tr>
                                    <td scope="col">Description</th>
                                    <td scope="col">Currency</th>
                                    <td scope="col">Number Card</th>
                                    <td scope="col">Balance</th>
                                    <td scope="col"></th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>
                                        <div class="form-group wid">
                                            <select onchange="getValue(this.value);"  name="description" class="custom-select" required>
                                                <option value="">Select currency</option>
                                                <option value="Debit Card">Debit Card</option>
                                                <option value="Credit Card">Credit Card</option>
                                                <option value="Cash">Cash</option>
                                            </select>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="form-group wid">
                                            <select class="custom-select" name="currency" required>
                                                <option value="">Select type</option>
                                                <option value="RUB">RUB</option>
                                                <option value="EUR">EUR</option>
                                                <option value="USD">USD</option>
                                            </select>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="form-group wid">
                                            <input type="hidden" class="form-control" id="txtHiddenCardNumber" name="card_number" placeholder="Number Card">
                                        </div>
                                    </td>
                                    <td>
                                        <div class="form-group wid">
                                            <input type="text" class="form-control" id="balance" name="balance" placeholder="Balance"/>
                                        </div>
                                    </td>
                                    <td>
                                        <button onClick="return validatePurse()" class="btn btn-success" type="submit">Save</button>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" id="firstChild" onClick="A(), toggleText(this.id)">New</button>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
    </form>
    <div class="modal fade" id="modalError" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Information</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
              <div class="modal-body">
                    <center><p id="errors"></p></center>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal" data-toggle="modal" data-target="#modalPurse">Ok</button>
              </div>
            </div>
          </div>
        </div>
<body>
    <nav class="navbar navbar-dark fixed-top bg-dark flex-md-nowrap p-0 shadow"></nav>

    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
      <a class="navbar-brand text-nowrap" href="">Manager</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav">
              <li class="nav-item active">
                <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="/accounts?showModal" id="btnModal">Purse</a>
              </li>
            </ul>
          </div>
        <ul class="navbar-nav sing-right">
          <li class="nav-item text-nowrap">
            <a class="nav-link " href="/logout">Sign out</a>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
          </li>
        </ul>
    </nav>

</body>
</html>