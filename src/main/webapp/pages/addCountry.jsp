<%@ include file="header.jsp" %>

    <div class="container d-flex justify-content-center align-items-center" style="height: 100vh;">
        <div class="card shadow-lg p-4" style="width: 380px;">
            
            <h4 class="text-center mb-3">Add Country</h4>

            <form action="<%=mainPath %>/mainController?action=addCountry" method="post">
               <c:if test="${not empty sessionScope.errorMsg }">
                	<div class="alert alert-danger" role="alert">${sessionScope.errorMsg }</div>
               </c:if>
 					
                <div class="mb-3">
                    <label class="form-label fw-semibold">Country Name</label>
                    <input type="text" class="form-control form-control-lg" placeholder="Enter country..." name="countryName" required>
                </div>

                <button type="submit" class="btn btn-primary w-100 btn-lg">
                    Add Country
                </button>
            </form>

        </div>
    </div>

<%@ include file="footer.jsp" %>
