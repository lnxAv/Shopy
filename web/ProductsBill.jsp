<%@ page import="org.Lib.UserBasket" %>
<%@ page import="org.sqldb.HistoryEntity" %>
<%@ page import="org.sqldb.ShopitemEntity" %>
<!DOCTYPE html>
<html>
    <head>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </head>
    <body>
        <% UserBasket userBasket = (UserBasket) request.getSession().getAttribute("UserBasket"); %>
        <% HistoryEntity[] itemBasket = userBasket.getHistoryBasket();%>
        <jsp:include page="header.jsp" />
        <% for(int i = 0; i < itemBasket.length; i++){%>
            <div class="card" style="width: 18rem;">
                <div class="card-body">
                    <h5 class="card-title"><%= userBasket.getShopItem(itemBasket[i].getIditem()).getNameItem()%></h5>
                    <h6 class="card-subtitle mb-2">Quantity :
                        <span class="badge badge-pill badge-dark">
                            <%= itemBasket[i].getQuantity()%>
                        </span>
                    </h6>
                    <form action="ProductsBill" method="get">
                        <input type="hidden" name="SelectedItem" value=<%= itemBasket[i].getIditem()%> >
                        <button type="submit" class="btn btn-primary btn-sm card-link" name="Action" value="remove"> Remove </button>
                        <button type="submit" class="btn btn-primary btn-sm card-link" name="Action" value="add"> Add </button>
                    </form>

                </div>
            </div>
        <%}%>
        <% if(itemBasket.length < 1){ %>
            <h1>Le panier est vide.</h1>
        <%} else {%>
            <form action="ProductsBill" method="get">
                <button type="submit" class="btn btn-success btn-sm card-link" name="Action" value="buy"> Buy </button>
            </form>
        <%}%>
    </body>
</html>

