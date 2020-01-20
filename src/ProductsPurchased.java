import org.Lib.UserBasket;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ProductsPurchased", urlPatterns = "/ProductsPurchased")
public class ProductsPurchased extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserBasket userBasket = null;
        int userID = -1;
        try{
            userBasket = (UserBasket) req.getSession().getAttribute("UserBasket");
            userID = (int) req.getSession().getAttribute("UserID");
        }catch (Exception e){
        }

        if(userBasket == null)
            req.getRequestDispatcher("/Login").forward(req,resp);

        userBasket.LoadPurchasedBasket(userID);
        req.getRequestDispatcher("/ProductsPurchased.jsp").forward(req, resp);
    }

}
