let firstName = prompt("What is your first name?");
        let lastName = prompt("What is your last name?");
        let fullName = `${firstName} ${lastName}`;
        alert(`Hello ${fullName}!`);

        let contactButton = document.querySelector("button");
        contactButton.addEventListener("click", contactMe);

        function contactMe() {
            let email = prompt("What is your email address?");
            let h2 = document.querySelector("h2");
            h2.innerHTML = `Thank you ${fullName}, we'll be in touch by email!`;
        }