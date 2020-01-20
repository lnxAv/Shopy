import org.Lib.PageItems;
import org.Lib.UserBasket;
import org.sqldb.ShopitemEntity;
import org.sqldb.SqlDb;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Products", urlPatterns = "/Products")
public class Products extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoadServletProducts(req);
        req.getRequestDispatcher("/Products.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserBasket userBasket = null;
        int userId = -1;
        int itemId = Integer.parseInt(req.getParameter("ItemId"));

        try{
            userBasket = (UserBasket) req.getSession().getAttribute("UserBasket");
            userId = (int) req.getSession().getAttribute("UserID");
        }catch (Exception e){
            req.getRequestDispatcher("/Login").forward(req,resp);
        }

        userBasket.addItem(itemId,userId);
        LoadServletProducts(req);
        req.getRequestDispatcher("/Products.jsp").forward(req,resp);
    }

    public void LoadServletProducts(HttpServletRequest req){
        PageItems pageItems= new PageItems();
        req.setAttribute("PageItems", pageItems);
    }
}

