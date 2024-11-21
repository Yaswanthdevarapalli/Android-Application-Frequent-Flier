
<%@page import="java.sql.*" %>

<%      
        String awardid=request.getParameter("awardid");
        String pid=request.getParameter("pid");
        
        DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
        String url="jdbc:oracle:thin:@artemis.vsnet.gmu.edu:1521/vse18c.vsnet.gmu.edu";
        Connection conn=DriverManager.getConnection(url,"nbillaka","xeewolag");
        Statement stmt=conn.createStatement();
        ResultSet rs=stmt.executeQuery("select a.a_description, a.points_required, rh.redemption_date, e.center_name from awards a, redemption_history rh,exchgcenters e where a.award_id=rh.award_id and rh.center_id=e.center_id and rh.passid="+ pid +"and a.award_id="+awardid);
        String output="";
      
        while(rs.next()){
            
            output += rs.getObject(1)+ ","+rs.getObject(2)+","+rs.getObject(3)+","+rs.getObject(4)+"#";
            
            }
        rs.close();
        conn.close();
        out.print(output);
    
%>




