import org.Lib.UserBasket;
import org.sqldb.ShopitemEntity;
import org.sqldb.SqlDb;
import org.sqldb.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Login", urlPatterns = "/Login")
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("login-message", "Welcome, please login");
        req.getRequestDispatcher("/Login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String usr = req.getParameter("log-username");
        String psw = req.getParameter("log-password");

        if(usr == null || psw == null){
            req.setAttribute("login-message", "Please enter the username and the password.");
            req.getRequestDispatcher("/Login.jsp").forward(req, resp);
        }

        int userID = ConfirmLogin(usr,psw);

        if(userID >= 0){
            UserBasket userBasket = new UserBasket(userID);
            req.getSession().setAttribute("UserID", userID);
            req.getSession().setAttribute("UserBasket", userBasket);
            req.getRequestDispatcher("/hidden/index.jsp").forward(req, resp);
        }

        req.setAttribute("login-message", "The password or username was not recognize "+ userID + " " + usr + " " + psw);
        req.getRequestDispatcher("/Login.jsp").forward(req, resp);
    }

    public int ConfirmLogin(String usr, String psw){
        List<UserEntity> userList = null;
        EntityManager em = SqlDb.getSqlDb();
        em.getTransaction().begin();
        Query query = em.createQuery("from UserEntity ");
        em.getTransaction().commit();
        if(query != null)
            userList = query.getResultList();
        em.close();

        for(int i = 0; i < userList.size(); i++){
            if(userList.get(i).getNameUser().equals(usr) && userList.get(i).getPasswordUser().equals(psw)){
                return userList.get(i).getIdUser();
            }
        }
        return -1;
    }
}
