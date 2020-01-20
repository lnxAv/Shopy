import org.Lib.UserBasket;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ProductsBill", urlPatterns = "/ProductsBill")
public class ProductsBill extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/ProductsBill.jsp").forward(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = null;
        int selectedItem = -1;
        int userID = -1;
        UserBasket userBasket = null;

        try{
            action = req.getParameter("Action");
            selectedItem = Integer.parseInt(req.getParameter("SelectedItem"));
            userID = (int) req.getSession().getAttribute("UserID");
        }
        catch (Exception e){
        }

        try{
            userBasket = (UserBasket) req.getSession().getAttribute("UserBasket");
        }catch (Exception e){

        }

        if(userBasket == null)
            req.getRequestDispatcher("/Login").forward(req,resp);

        if(action == null)
            action = " ";

        switch (action){
            case "remove":
                userBasket.RemoveToQuantity(selectedItem, userID);
                break;
            case "add":
                userBasket.AddToQuantity(selectedItem);
                break;
            case "buy":
                userBasket.BuyBasket(userID);
                req.getRequestDispatcher("/ProductsPurchased").forward(req,resp);
                break;
            default:
                break;
        }

        req.getRequestDispatcher("/ProductsBill.jsp").forward(req,resp);
    }
}
