document.addEventListener("DOMContentLoaded", function () {
    var enemyName = document.getElementsByClassName("enemy-name-combat")[0];
    var skeleton = document.getElementsByClassName("enemy-skeleton-icon")[0];
    var rat = document.getElementsByClassName("enemy-rat-icon")[0];
    var slime = document.getElementsByClassName("enemy-slime-icon")[0];
    // Check if the text content of the <p> element is "Skeleton"
    if (enemyName.textContent.trim() === "Skeleton") {
        skeleton.removeAttribute("hidden");
    }
    if (enemyName.textContent.trim() === "Rat") {
        rat.removeAttribute("hidden");
    }
    if (enemyName.textContent.trim() === "Slime") {
        slime.removeAttribute("hidden");
    }
});

document.addEventListener("DOMContentLoaded", function() {
    var attackButton = document.getElementById("attackButton");
    console.log("Inside Javascript for attack");

    attackButton.addEventListener("click", function() {
        // Make an AJAX request to "localhost:8080/combat/attack"

        var currentHealthElement = document.getElementsByClassName("enemy-health-combat")[0];
        console.log(currentHealthElement);
        var healthText = currentHealthElement.textContent.trim();
        console.log(healthText);
        var currentHealthText = healthText.charAt(0);
        console.log(currentHealthText);

        if (currentHealthText == 0) {
            console.log("Redirect to combat");
            attackButton.href = "http://localhost:8080/combat/";
        }else{
            attackButton.href = "http://localhost:8080/combat/attack";
        }
    });
});
