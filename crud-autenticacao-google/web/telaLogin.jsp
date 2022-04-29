<%-- 
    Document   : telaLogin
    Created on : 8 de abr de 2022, 19:58:57
    Author     : aluno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Login do sistema</h1>
        
        <div id="buttonDiv"></div> 
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

        <!-- login com google! -->
        <script src="https://accounts.google.com/gsi/client" async defer></script>

        <script>
            function handleCredentialResponse(response) {

                console.log("Encoded JWT ID token: " + response.credential);

                token = response.credential;

                resp = JSON.parse(atob(token.split('.')[1]));
                console.log(JSON.stringify(resp));
                //alert(resp.email);

                $("#email").text("Bem vindo, " + resp.email);

                // informar o servidor de que o front-end está logado
                $.get("sessao?op=login&email=" + resp.email+"&pic="+resp.picture, function (data) {
                    //alert("recebido: "+data);
                    if (data === "ok") {
                        $("#email-sessao").text("Sessão ativa via JS: " + resp.email);
                        // atualizar a página
                        //document.location.reload(true);
                    } else {
                        $("#email-sessao").text("Problema na sessão: " + data);
                    }
                });



                /*
                 // decodeJwtResponse() is a custom function defined by you
                 // to decode the credential response.
                 const responsePayload = decodeJwtResponse(response.credential);
                 
                 console.log("ID: " + responsePayload.sub);
                 console.log('Full Name: ' + responsePayload.name);
                 console.log('Given Name: ' + responsePayload.given_name);;
                 console.log('Family Name: ' + responsePayload.family_name);
                 console.log("Image URL: " + responsePayload.picture);
                 console.log("Email: " + responsePayload.email);
                 
                 */
                Location.reload();
            }
            window.onload = function () {
                google.accounts.id.initialize({
                    client_id: "523415345044-d0c904843j1aj1nsu8bkfvjcim42e2fe.apps.googleusercontent.com",
                    callback: handleCredentialResponse
                });
                google.accounts.id.renderButton(
                        document.getElementById("buttonDiv"),
                        {theme: "outline", size: "large"}  // customization attributes
                );
                google.accounts.id.prompt(); // also display the One Tap dialog
            };
        </script>

    </body>
</html>
