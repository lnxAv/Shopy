<!DOCTYPE html>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</head>
<body>
<% String LoginMessage = (String) request.getAttribute("login-message"); %>
<jsp:include page="header.jsp" />
<form action="Login" method="post" id="centered-form">
    <div class="container-login">
        <div class="container-header">
            <picture>
                <img src=""/>
            </picture>
        </div>
        <div>
            <label class="form-check-label container-label1" >
                Username
            </label>
            <div>
                <input name="log-username" id="log-username" class="form-control container-input1" placeholder="Enter username..."/>
            </div>
        </div>
        <div>
            <label class="form-check-label container-label2" >
                Password
            </label>
            <div>
                <input name="log-password" id="log-password" class="form-control container-input2" placeholder="Enter password..."/>
            </div>
        </div>
        <div>
            <button type="submit" class="btn btn-secondary btn-lg container-submit">
                Login
            </button>
        </div>
        <div>
            <span class="container-message">
                <%= LoginMessage %>
            </span>
        </div>
    </div>
</form>

</body>

</html>