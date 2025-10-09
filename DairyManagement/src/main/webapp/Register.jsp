<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Agent Registration</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #f4f6f9; padding-top: 80px; padding-bottom: 60px; }
        .form-container { max-width: 700px; margin: auto; background:#fff; padding:24px; border-radius:12px; box-shadow: 0 6px 18px rgba(0,0,0,0.06); }
        .error-msg{ color:#d63384; display:none; }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-success fixed-top">
    <div class="container-fluid">
        <a class="navbar-brand" href="agentDashboard.jsp">Farmer Fresh Family</a>
    </div>
</nav>

<div class="container">
    <div class="form-container">
        <h3 class="mb-4 text-center">Agent Registration</h3>

        <form id="registerFormStandalone" method="post" action="registerAgents" enctype="multipart/form-data">
            <div class="row g-3">
                <div class="col-md-6">
                    <label class="form-label">First Name</label>
                    <input type="text" class="form-control" id="firstName_s" name="firstName" required>
                    <div id="firstNameError_s" class="error-msg">First name must be at least 3 letters</div>
                </div>

                <div class="col-md-6">
                    <label class="form-label">Last Name</label>
                    <input type="text" class="form-control" id="lastName_s" name="lastName" required>
                    <div id="lastNameError_s" class="error-msg">Last name must be at least 3 letters</div>
                </div>

                <div class="col-md-6">
                    <label class="form-label">Email</label>
                    <input type="email" class="form-control" id="email_s" name="email" required>
                    <div id="emailError_s" class="error-msg">Enter a valid email (must contain @)</div>
                    <div id="emailExists_s" class="error-msg">This email is already registered</div>
                </div>

                <div class="col-md-6">
                    <label class="form-label">Phone Number</label>
                    <input type="text" class="form-control" id="phone_s" name="phoneNumber" required>
                    <div id="phoneError_s" class="error-msg">Phone number must be 10 digits</div>
                    <div id="phoneExists_s" class="error-msg">This phone number is already registered</div>
                </div>

                <div class="col-12">
                    <label class="form-label">Address</label>
                    <textarea class="form-control" id="address_s" name="address" rows="2" required></textarea>
                    <div id="addressError_s" class="error-msg">Address cannot be empty</div>
                </div>

                <div class="col-md-6">
                    <label class="form-label">Types of Milk</label>
                    <select class="form-select" id="milkType_s" name="milkType" required>
                        <option value="">-- Select Milk Type --</option>
                        <option>Full Cream</option>
                        <option>Toned Milk</option>
                        <option>Double Toned Milk</option>
                        <option>Skimmed Milk</option>
                        <option>Organic Milk</option>
                    </select>
                    <div id="milkTypeError_s" class="error-msg">Please select a milk type</div>
                </div>


            <div class="mt-4">
                <button id="submitStandalone" type="submit" class="btn btn-success w-100">Register</button>
            </div>
        </form>
    </div>
</div>

<!-- Bootstrap + JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<script>
    // reuse same validation logic but for standalone ids
    function showEl(id){ document.getElementById(id).style.display = 'block'; }
    function hideEl(id){ document.getElementById(id).style.display = 'none'; }

    document.getElementById('profilePic_s').addEventListener('change', function(evt){
        const f = evt.target.files && evt.target.files[0];
        if(f){
            const r = new FileReader();
            r.onload = function(e){ document.getElementById('preview_s').src = e.target.result; }
            r.readAsDataURL(f);
        }
    });

    function validateStandalone(){
        let ok = true;
        const fn = document.getElementById('firstName_s').value.trim();
        if(fn.length < 3){ showEl('firstNameError_s'); ok = false; } else hideEl('firstNameError_s');

        const ln = document.getElementById('lastName_s').value.trim();
        if(ln.length < 3){ showEl('lastNameError_s'); ok = false; } else hideEl('lastNameError_s');

        const email = document.getElementById('email_s').value.trim();
        if(!email.includes('@')){ showEl('emailError_s'); ok = false; } else hideEl('emailError_s');

        const phone = document.getElementById('phone_s').value.trim();
        if(!/^[0-9]{10}$/.test(phone)){ showEl('phoneError_s'); ok = false; } else hideEl('phoneError_s');

        const addr = document.getElementById('address_s').value.trim();
        if(addr === ''){ showEl('addressError_s'); ok = false; } else hideEl('addressError_s');

        const milk = document.getElementById('milkType_s').value;
        if(milk === ''){ showEl('milkTypeError_s'); ok = false; } else hideEl('milkTypeError_s');

        return ok;
    }

    document.getElementById('email_s').addEventListener('blur', function(){
        const email = this.value.trim();
        if(!email || !email.includes('@')) return;
        fetch('validateEmail?email=' + encodeURIComponent(email)).then(r=>r.text()).then(t=>{
            if(t.trim() === 'exists') showEl('emailExists_s'); else hideEl('emailExists_s');
        });
    });

    document.getElementById('phone_s').addEventListener('blur', function(){
        const phone = this.value.trim();
        if(!/^[0-9]{10}$/.test(phone)) return;
        fetch('validatePhone?phoneNumber=' + encodeURIComponent(phone)).then(r=>r.text()).then(t=>{
            if(t.trim() === 'exists') showEl('phoneExists_s'); else hideEl('phoneExists_s');
        });
    });

    document.getElementById('registerFormStandalone').addEventListener('submit', function(e){
        if(!validateStandalone()){
            e.preventDefault();
            return;
        }
        // optional: submit via AJAX similar to modal version
        // allow normal form submit (server side handles redirect to dashboard or returns JSON)
    });
</script>

</body>
</html>
