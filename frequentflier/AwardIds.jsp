
<%@page import="java.sql.*" %>

<%      
        String pid=request.getParameter("pid");
        DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
        String url="jdbc:oracle:thin:@artemis.vsnet.gmu.edu:1521/vse18c.vsnet.gmu.edu";
        Connection conn=DriverManager.getConnection(url,"nbillaka","xeewolag");
        Statement stmt=conn.createStatement();
        ResultSet rs=stmt.executeQuery("select distinct award_id from redemption_history where passid="+pid);
        String output="";
      
        while(rs.next()){
            
            output += rs.getObject(1)+"#";
            
            }
        conn.close();
        out.print(output);
    
%>


