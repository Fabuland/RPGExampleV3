document.addEventListener("DOMContentLoaded", function() {
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

  var attackButton = document.getElementById("attackButton");
  attackButton.addEventListener("click", function() {
    // Make an AJAX request to "localhost:8080/combat/attack"
    var currentEnemyHealthElement = document.getElementsByClassName("enemy-health-combat")[0];
    var enemyHealthText = currentEnemyHealthElement.textContent.trim();
    var currentEnemyHealthText = enemyHealthText.charAt(0);
    if (currentEnemyHealthText == 0) {
      attackButton.href = "../combat/";
    } else {
      attackButton.href = "../combat/attack";
    }
    var currentUserHealthElement = document.getElementsByClassName("user-health-combat")[0];
    var userHealthText = currentUserHealthElement.textContent.trim();
    var currentUserHealthText = userHealthText.charAt(0);
    if (currentUserHealthText == 0) {
      attackButton.href = "..";
    }
  });

});