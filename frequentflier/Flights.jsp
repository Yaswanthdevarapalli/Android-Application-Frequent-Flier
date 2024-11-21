
<%@page import="java.sql.*" %>

<%      
        String pid=request.getParameter("pid");
        DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
        String url="jdbc:oracle:thin:@artemis.vsnet.gmu.edu:1521/vse18c.vsnet.gmu.edu";
        Connection conn=DriverManager.getConnection(url,"nbillaka","xeewolag");
        Statement stmt=conn.createStatement();
        ResultSet rs=stmt.executeQuery("select flight_id,flight_miles,destination from flights where passid="+pid);
        String output="";
      
        while(rs.next()){
            
            output += rs.getObject(1)+ ","+rs.getObject(2)+","+rs.getObject(3)+","+"#";
            
            }
	  rs.close();
        conn.close();
        out.print(output);
    
%>


