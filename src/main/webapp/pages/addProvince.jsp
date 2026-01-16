<%@ include file="header.jsp" %>

    <div class="container mt-5">

    <div class="row justify-content-center">
        <div class="col-md-6">

            <div class="card shadow">
                <div class="card-header text-center bg-primary text-white fw-bold">
                    Add Province
                </div>

                <div class="card-body">

                    <form action="<%=mainPath %>/mainController?action=addProvince" method="post">

                       
                        <div class="mb-3">
                            <c:if test="${empty countries }">
										<option value="" disabled selected>No Country Found</option>
                            	</c:if>
			                     <label class="form-label fw-semibold">Select Country</label>

                            <select class="form-select" name="countryId" required>
                            	
                                <option value="" disabled selected>Select country</option>
										<c:forEach var="item" items="${countries}">
											 <option value="${item.countryId}"> ${item.country} </option>
										
										</c:forEach>
                            	
                            </select>
                        </div>

                        <!-- Province Name -->
                        <div class="mb-3">
                            <label class="form-label fw-semibold">Province Name</label>
                            <input type="text" name="provinceName" class="form-control" required
                                   placeholder="Enter province name">
                        </div>

                        <div class="text-center mt-4">
                            <button type="submit" class="btn btn-primary px-4 fw-semibold">
                                Add Province
                            </button>
                        </div>

                    </form>
                </div>

            </div>

        </div>
    </div>

</div>
    

<%@ include file="footer.jsp" %>
