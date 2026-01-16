<%@ include file="header.jsp" %>

   <div class="container mt-5">

    <div class="row justify-content-center">
        <div class="col-md-6">

            <div class="card shadow">
                <div class="card-header text-center bg-primary text-white fw-bold">
                    Add District
                </div>

                <div class="card-body">
                    <form action="<%=mainPath %>/mainController?action=addDistrict" method="post">

                        
                        <div class="mb-3">
                            <label class="form-label fw-semibold">Select Province</label>

                           <select class="form-select" name="provinceId" required>
                            	<c:if test="${empty provinces }">                            	
									<option value="" disabled selected>No Province Found</option>	
                            	</c:if>
                                <option value="" disabled selected>Select Province</option>
										<c:forEach var="item" items="${provinces}">
											 <option value="${item.provinceId}"> ${item.name} </option>
										
										</c:forEach>
                            	
                            </select>
                        </div>

                        <!-- District Name -->
                        <div class="mb-3">
                            <label class="form-label fw-semibold">District Name</label>
                            <input type="text" name="districtName" class="form-control" required
                                   placeholder="Enter district name">
                        </div>

                        <div class="text-center mt-4">
                            <button type="submit" class="btn btn-primary px-4 fw-semibold">
                                Add District
                            </button>
                        </div>

                    </form>
                </div>

            </div>

        </div>
    </div>

</div>


<%@ include file="footer.jsp" %>
