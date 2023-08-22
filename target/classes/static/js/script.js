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