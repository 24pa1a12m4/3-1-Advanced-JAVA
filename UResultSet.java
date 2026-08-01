import java.sql.*;

class UResultSet {
    public static void main(String[] args) throws Exception {

        String url = "jdbc:mysql://localhost:3306/testdb";
        String user = "testuser";
        String password = "testpass";

        // Load MySQL JDBC Driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Connect to database
        Connection con = DriverManager.getConnection(url, user, password);

        // Create scrollable and updatable statement
        Statement st = con.createStatement(
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);

        // Execute query
        ResultSet rs = st.executeQuery("SELECT * FROM Student");

        // Delete last row
        rs.last();
        rs.deleteRow();
        System.out.println("Last student record deleted successfully.");

        // Insert new row
        rs.moveToInsertRow();
        rs.updateInt("RollNo", 105);
        rs.updateString("Name", "John Doe");
        rs.updateString("Address", "Hyderabad");
        rs.insertRow();
        System.out.println("New student record inserted successfully.");

        con.close();
    }
}
