<%@page import="java.sql.*" %>

<%      
        String pid=request.getParameter("pid");
        DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
        String url="jdbc:oracle:thin:@artemis.vsnet.gmu.edu:1521/vse18c.vsnet.gmu.edu";
        Connection conn=DriverManager.getConnection(url,"nbillaka","xeewolag");
        Statement stmt=conn.createStatement();
        ResultSet rs=stmt.executeQuery("select p.pname, pa.total_points from passengers p, point_accounts pa where p.passid=pa.passid AND p.passid="+pid);
        String output="";
      
        while(rs.next()){
            
            output = rs.getObject(1)+","+rs.getObject(2);
            
            }
        conn.close();
        out.print(output);
    
%>


