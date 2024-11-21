<%@page import="java.sql.*" %>

<%      
        String flightid=request.getParameter("flightid");

        DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
        String url="jdbc:oracle:thin:@artemis.vsnet.gmu.edu:1521/vse18c.vsnet.gmu.edu";
        Connection conn=DriverManager.getConnection(url,"nbillaka","xeewolag");
        Statement stmt=conn.createStatement();
        ResultSet rs=stmt.executeQuery("select f.arrival_datetime,f.dept_datetime,f.flight_miles,t.trip_id, t.trip_miles from flights f, flights_trips ft, trips t where f.flight_id=ft.flight_id and ft.trip_id=t.trip_id and f.flight_id='" + flightid+"'");
        String output="";
      
        while(rs.next()){
            
            output += rs.getObject(1)+","+rs.getObject(2)+","+rs.getObject(3)+","+rs.getObject(4)+","+rs.getObject(5)+"#";
            
            }
        rs.close();
        conn.close();
        out.print(output);
    
%>




