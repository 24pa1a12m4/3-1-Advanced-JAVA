import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

@WebServlet("/users")
public class UserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        ArrayList<String> productList = new ArrayList<>();
        String query = "SELECT name, category, price, delivery_time_mins FROM products";
        
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                String details = "<b>" + rs.getString("name") + "</b> [" + rs.getString("category") + "]<br>" +
                                 "Price: ₹" + rs.getBigDecimal("price") + " | " +
                                 "<span style='color:green; font-weight:bold;'>Delivering in " + rs.getInt("delivery_time_mins") + " mins</span>";
                productList.add(details);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        request.setAttribute("users", productList);
        request.getRequestDispatcher("displayUsers.jsp").forward(request, response);
    }
}
