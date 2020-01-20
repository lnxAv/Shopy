<%@ page import="org.sqldb.ShopitemEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="org.Lib.PageItems" %>
<%@ page import="org.hibernate.Session" %>
<%@ page import="org.Lib.UserBasket" %>

<!DOCTYPE html>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <link href="shopitem.css" rel="stylesheet">
</head>
<body >
<% PageItems pageItems = (PageItems) request.getAttribute("PageItems"); %>
<% ShopitemEntity[] itemList = pageItems.getItemList(); %>
<% UserBasket userBasket = (UserBasket) request.getAttribute("UserBasket"); %>
<jsp:include page="header.jsp" />
    <% for(int i = 0; i < itemList.length; i++){%>
        <div class="col-xs-12 col-sm-6 col-md-3">
            <div class="col-item">
                <div class="photo">
                    <img src="http://placehold.it/350x260" class="img-responsive" alt="a" />
                </div>
                <div class="info">
                    <div class="row">
                        <div class="price col-md-6">
                            <h5> <%= itemList[i].getNameItem()%> </h5>
                            <h5 class="price-text-color"> <%= itemList[i].getPriceItem() %> </h5>
                        </div>
                    </div>
                    <div class="separator clear-left">
                        <form action="Products" method="post">
                            <input type="hidden" name="ItemId" value=<%=itemList[i].getIdItem()%>>
                            <button type="submit" class="btn-add">
                                <i class="fa fa-shopping-cart"></i><p>Add to cart</p>
                            </button>
                        </form>
                    </div>
                    <div class="clearfix">
                    </div>
                </div>
            </div>
        </div>
    <%};%>
</body>

<script>
    <%  %>
</script>
</html>