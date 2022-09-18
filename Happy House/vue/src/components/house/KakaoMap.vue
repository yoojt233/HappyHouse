<template>
  <div>
    <div class="map" ref="map"></div>
  </div>
</template>

<script>
import { mapState } from "vuex";

let kakao = window.kakao;
let mapInstance = null;
const houseStore = "houseStore";

export default {
  computed: {
    ...mapState(houseStore, ["house"]),
    // house() {
    //   return this.$store.state.house;
    // },
  },
  mounted() {
    kakao = kakao || window.kakao;
    console.log(this.$refs.map);

    var container = this.$refs.map;
    var options = {
      center: new kakao.maps.LatLng(this.house.lat, this.house.lng),
    };

    mapInstance = new kakao.maps.Map(container, options);
    console.log(mapInstance);
  },
  watch: {
    // 매물을 선택하면
    house() {
      // 마커 위치 지정
      let markerPosition = new kakao.maps.LatLng(
        this.house.lat,
        this.house.lng,
      );
      // 마커 생성
      let marker = new kakao.maps.Marker({
        position: markerPosition,
        clickable: true,
      });
      // 지도 위에 마커 그리기
      marker.setMap(mapInstance);

      // 마커에 간단한 아파트 정보 입력
      let iwContent =
          '<div style="padding:5px;">[' +
          this.house.aptCode +
          "] " +
          this.house.aptName +
          "</div>",
        iwRemoveable = true;

      let infowindow = new kakao.maps.InfoWindow({
        content: iwContent,
        removable: iwRemoveable,
      });

      kakao.maps.event.addListener(marker, "click", function () {
        infowindow.open(mapInstance, marker);
      });

      // 지도 중심 위치 이동
      mapInstance.panTo(new kakao.maps.LatLng(this.house.lat, this.house.lng));
    },
  },
};
</script>

<style scoped>
.map {
  height: 400px;
  width: 100%;
}
</style>
