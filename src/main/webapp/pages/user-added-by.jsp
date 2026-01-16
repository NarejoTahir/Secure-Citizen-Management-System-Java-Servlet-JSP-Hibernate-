
<%@ include file="header.jsp"%>

<%

	
	
	 	Users use = null;
	    String msg = null;

	    String userIdStr = request.getParameter("userId");
	    if (userIdStr != null && !userIdStr.trim().isEmpty()) {
	        try {
	            int userIdByAudit = Integer.parseInt(userIdStr.trim());
	            addUser us = new addUser(HibernateDb.getFactory());
	            use = us.getUserById(userIdByAudit);
				
	            if (use == null) {
	                msg = "No User Found By This Record";
	            }
	            
	            
	        } catch (NumberFormatException e) {
	            msg = "Invalid userId parameter: must be a number";
	        }
	    } else {
	        msg = "User Added By Registering";
	    }
	    System.out.println("User from Jsp"+ use);
	    request.setAttribute("use", use);
	    request.setAttribute("msg", msg);
%>

<div class="container mt-5">

    <!-- Card for Users Table -->
    <div class="card shadow-sm">
        <div class="card-body">

            <h3 class="card-title mb-4">Added By</h3>

            <!-- Table -->
            <div class="table-responsive">
                <table class="table table-bordered table-hover align-middle" id="usersTable">
                    <thead class="table-dark">
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Date of Birth</th>
                            <th>CNIC</th>
                        </tr>
                    </thead>
                    <tbody>
                    	<c:if test="${not empty msg }">
                    	</c:if> 
                    	
                    	<c:choose>
							<c:when test="${not empty msg }">
								<h1>${msg }</h1>                    			
							</c:when>
						<c:otherwise> 
       				
                            <tr>
                                <td><%=use.getName() %></td>
            					<td><c:out value="${use.name}"/></td>
            					<td><c:out value="${use.dob}"/></td>
            					<td><c:out value="${use.cnic}"/></td>
            				</tr>
                          </c:otherwise>
						</c:choose>
                        
                    </tbody>
                </table>

            </div>
        </div>
    </div>
</div>



<%@ include file="footer.jsp"%>	