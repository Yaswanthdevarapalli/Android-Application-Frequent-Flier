
<%@page import="java.sql.*" %>

<%
    String source_pid = request.getParameter("spid");
    String destination_pid=request.getParameter("dpid");
    int npoints=Integer.parseInt(request.getParameter("npoints"));
    
    DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
    String url="jdbc:oracle:thin:@artemis.vsnet.gmu.edu:1521/vse18c.vsnet.gmu.edu";
    Connection conn = DriverManager.getConnection(url,"nbillaka","xeewolag");
    Statement stmt = conn.createStatement();
    String query1="UPDATE Point_Accounts SET total_points = total_points-" +npoints+ " WHERE passid = '"+source_pid+"' ";
    ResultSet s1= stmt.executeQuery(query1);
    String query2="UPDATE Point_Accounts SET total_points = total_points+"+npoints+ "WHERE passid = '"+destination_pid+"'";
    ResultSet s2=stmt.executeQuery(query2);
    
    conn.close();
    out.println("Transfer successful");
%>



