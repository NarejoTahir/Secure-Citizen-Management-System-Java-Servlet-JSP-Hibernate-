<%@ include file="header.jsp"%>

<div class="container mt-5">

    <div class="card shadow-sm p-3">

        <!-- CNIC Search Field -->
        <div class="mb-3">
            <input type="text" id="cnicInput" class="form-control"
                   placeholder="Enter CNIC..." onkeyup="searchByCnic()" />
        </div>
        
        

        <div class="table-responsive" id="tableContainer" style="display:none;">
            <table class="table table-bordered table-hover align-middle">
                <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Date of Birth</th>
                        <th>Country</th>
                        <th>Province</th>
                        <th>District</th>
                        <th>Tehsil</th>
                        <th>CNIC</th>
                    </tr>
                </thead>
                <tbody id="data-body">
                	
                </tbody>
            </table>
        </div>

    </div>

</div>

<%@ include file="footer.jsp"%>
