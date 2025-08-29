// JS for auto-refresh live data
// Auto-refresh live score every 5 seconds
function fetchLiveScore() {
  fetch("live.json")  // This file will be updated by Java backend
    .then(response => response.json())
    .then(data => {
      document.getElementById("live").innerHTML = `
        <h2>Live Score</h2>
        <p>${data.teamA} vs ${data.teamB}</p>
        <p>Score: ${data.score}/${data.wickets} in ${data.overs} overs</p>
      `;
    })
    .catch(err => console.log("Waiting for data..."));
}

setInterval(fetchLiveScore, 5000); // refresh every 5 sec
fetchLiveScore();
