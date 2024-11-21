<%@page import="java.sql.*" %>

<%
    String pid = request.getParameter("pid");
    DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
    String url="jdbc:oracle:thin:@artemis.vsnet.gmu.edu:1521/vse18c.vsnet.gmu.edu";
    Connection conn = DriverManager.getConnection(url,"nbillaka","xeewolag");
    Statement stmt = conn.createStatement();
    String query = "select passid from passengers where passid!="+pid;
    ResultSet rs = stmt.executeQuery(query);
    String output = "";
    while(rs.next())
    {
        output+=rs.getObject(1)+"#";
}
conn.close();
out.println(output);
%>



