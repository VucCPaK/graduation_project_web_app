<template>
  <div>
    <!-- Header with image -->
    <div class="bgimg w3-display-container w3-grayscale-min">
      <div class="w3-display-left">
        <span class="w3-text-white w3-serif w3-hide-small" style="font-size:50px">Upgrade </span>
        <span class="w3-text-white w3-serif w3-hide-small" style="font-size:50px">Your </span>
        <span class="w3-text-white w3-serif w3-hide-small" style="font-size:50px">Computer</span>
      </div>
    </div>

    <p></p>

    <!-- Images-->
    <div class="models w3-display-container">
      <table>
        <tr>
          <td align="center" width="824" height="380"><img src="../pictures/motherboard.jpg" width="350px"
                                                           height="350px"/></td>
          <td class="w3-black" align="center" width="824" height="380"><img src="../pictures/processor.jpg"
                                                                            width="350px" height="350px"/></td>
        </tr>
        <tr>
          <td class="w3-black" align="center" width="824" height="380"><img src="../pictures/videocard.jpg"
                                                                            width="350px" height="350px"/></td>
          <td align="center" width="824" height="380"><img src="../pictures/powersupply.jpg" width="350px"
                                                           height="350px"/></td>
        </tr>
        <tr>
          <td align="center" width="824" height="380"><img src="../pictures/cooler.jpg" width="350px" height="350px"/>
          </td>
          <td class="w3-black" align="center" width="824" height="380"><img src="../pictures/ram.jpg" width="350px"
                                                                            height="350px"/></td>
        </tr>
      </table>
    </div>

    <p></p>

    <!-- Contact -->
    <div class="w3-container w3-padding-16 w3-grayscale-min w3-tiny">
      <div class="w3-content">
        <h1 class="w3-center w3-large" style="margin-bottom:64px">Contact</h1>
        <p>Find us at some address at some place or call us at 999999999</p>
        <p><span class="w3-tag">FYI!</span>We offer full-service for your PC. We
          understand your needs.</p>
      </div>
    </div>
  </div>
</template>

<style scoped>
@import "../css/home/w3schools.css";

body, html {
  height: 100%
}

body, h1, h2, h3, h4, h5, h6 {
  font-family: sans-serif
}

.bgimg {
  height: 900px;
  background-repeat: no-repeat;
  background-size: cover;
  background-image: url("../pictures/main.jpg");
  min-height: 100%;
}
</style>